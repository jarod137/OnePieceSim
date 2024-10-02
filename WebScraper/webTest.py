import requests
from bs4 import BeautifulSoup
import json
import os
import getpass

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'
}

URL = "https://en.onepiece-cardgame.com/cardlist/"
page = requests.get(URL, headers=headers)

soup = BeautifulSoup(page.content, "html.parser")
results = soup.find(class_="resultCol")

card_elements = results.find_all("dl", class_="modalCol")
cards = []

def isEmpty(item):
    if item is None or len(item) == 0:
        return "N/A"
    return item.get_text(strip=True)

class Card:

    def __init__(self, name, cost, power, counter, color, type, effect, set, attribute, count):
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
            "cardNo": self.count
        }

    def to_string(self):
        return f"{self.name} \n {self.cost} \n {self.power} \n {self.counter} \n {self.color} \n {self.type} \n {self.effect} \n {self.set} \n {self.attribute} \n {self.count}"

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
        cardCount
    )

    cards.append(newCard)
    cardCount = cardCount + 1


for card in cards:
    print(card.to_string() + "\n")

cards_json = [card.to_dict() for card in cards]


user = getpass.getuser()
path = f"/Users/{user}/Documents/personalProjects/OnePieceSim/WebScraper/data.json"

with open(path, 'w+', encoding='utf-8') as file:
    json.dump(cards_json, file, ensure_ascii=False, indent=4)
