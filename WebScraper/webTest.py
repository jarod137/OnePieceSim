import requests
from bs4 import BeautifulSoup
import json
import os
import getpass
from PIL import Image
from concurrent.futures import ThreadPoolExecutor

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'
}

URL = "https://en.onepiece-cardgame.com/cardlist/"
page = requests.get(URL, headers=headers)

soup = BeautifulSoup(page.content, "html.parser")
results = soup.find(class_="resultCol")

card_elements = results.find_all("dl", class_="modalCol")
card_images = results.find_all("a", class_="modalOpen")

cards = []

def isEmpty(item):
    if item is None or len(item) == 0:
        return "N/A"
    return item.get_text(strip=True)

def download_image(image_info):
    image_index, full_img_url = image_info
    img_data = requests.get(full_img_url).content
    image_filename = f"{image_index}.jpg"

    with open(image_filename, 'wb') as img_file:
        img_file.write(img_data)

    print(f"Saved {image_filename} from {full_img_url}")

class Card:

    def __init__(self, name, cost, power, counter, color, type, effect, set, attribute, count, img):
        self.name = name
        self.cost = cost
        self.power = power
        self.counter = counter
        self.color = color
        self.type = type
        self.effect = effect
        self.set = set
        self.attribute = attribute
        self.count = count
        self.img = img

    def to_dict(self):
        return {
            "name": self.name,
            "cost": self.cost,
            "power": self.power,
            "counter": self.counter,
            "color": self.color,
            "type": self.type,
            "effect": self.effect,
            "set": self.set,
            "attribute": self.attribute,
            "cardNo": self.count,
            "imgPath": self.img
        }

    def to_string(self):
        return f"{self.name} \n {self.cost} \n {self.power} \n {self.counter} \n {self.color} \n {self.type} \n {self.effect} \n {self.set} \n {self.attribute} \n {self.count} \n {self.img} \n"

cardCount = 1

for card in card_elements:
    title_element = card.find("div", class_="cardName")
    cost_element = card.find("div", class_="cost")
    power_element = card.find("div", class_="power")
    counter_element = card.find("div", class_="counter")
    color_element = card.find("div", class_="color")
    type_element = card.find("div", class_="feature")
    effect_element = card.find("div", class_="text")
    info_element = card.find("div", class_="getInfo")
    attribute_element = card.find("div", class_="attribute")

    newCard = Card(
        isEmpty(title_element),
        isEmpty(cost_element).replace("Cost", ""),
        isEmpty(power_element).replace("Power", ""),
        isEmpty(counter_element).replace("Counter", ""),
        isEmpty(color_element).replace("Color", ""),
        isEmpty(type_element).replace("Type", ""),
        isEmpty(effect_element),
        isEmpty(info_element),
        isEmpty(attribute_element).replace("Attribute", ""),
        cardCount,
        "OnePieceSim/assets/" + str(cardCount) + ".jpg"
    )

    cards.append(newCard)
    cardCount = cardCount + 1

img_urls = []

for idx, images in enumerate(card_images, start=1):
    image = images.find("img", class_="lazy")
    image_link = image["data-src"]
    full_img_url = URL.rstrip('/cardlist') + image_link.replace("..", "")
    img_urls.append((idx, full_img_url))

os.chdir("../assets/cards/")
with ThreadPoolExecutor() as executor:
    executor.map(download_image, img_urls)

#for card in cards:
#    print(card.to_string() + "\n")

cards_json = [card.to_dict() for card in cards]

os.chdir("../../WebScraper/")

with open("data.json", 'w+', encoding='utf-8') as file:
    json.dump(cards_json, file, ensure_ascii=False, indent=4)
