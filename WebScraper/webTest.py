import json
import os
import time
from concurrent.futures import ThreadPoolExecutor

import requests
from bs4 import BeautifulSoup
from selenium import webdriver
from selenium.common import TimeoutException
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.support.ui import Select
from selenium.webdriver.support.ui import WebDriverWait

"""
Author: Alex Rivera
Notes: This could be improved on massively, but will revisit to improve later.
"""

headers = {
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'
}

URL = "https://en.onepiece-cardgame.com/cardlist/"

driver_options = webdriver.ChromeOptions()
driver_options.add_argument("--headless")
driver_options.add_argument("--no-sandbox")
driver_options.add_argument("--disable-dev-shm-usage")

driver = webdriver.Chrome(options=driver_options)
driver.set_page_load_timeout(20)

start_time = time.time()

try:
    print(f"Getting {URL}")
    driver.get(URL)
    print(f"Got {URL}")

    cookie_button = WebDriverWait(driver, 20).until(
        EC.presence_of_element_located((By.CLASS_NAME, "onetrust-close-btn-handler"))
    )
    cookie_button.click()
    print("Cookie button clicked!")

except TimeoutException:
    print(f"Timeout while loading {URL}")

page_source = driver.page_source

# --- BeautifulSoup Part ---
soup = BeautifulSoup(page_source, "html.parser")
results = soup.find(class_="resultCol")
options = soup.find(class_="searchCol")

select_element = driver.find_element(By.ID, "series")
select_obj = Select(select_element)

card_elements = results.find_all("dl", class_="modalCol")
card_images = results.find_all("a", class_="modalOpen")

cards = []
img_urls = []

def isEmpty(item):
    if item is None or len(item) == 0:
        return "N/A"
    return item.get_text(strip=True)

def extract_card_type(info_element):
    if info_element is None:
        return ""

    # Extract all the text from individual <span> elements
    spans = info_element.find_all("span")

    if len(spans) < 3:
        return ""

    # Extract the third <span> (card type) and process it
    card_type = spans[2].get_text(strip=True).upper()
    print(card_type)

    # Check if the card type is one of the valid types
    if card_type in ["CHARACTER", "LEADER", "STAGE", "EVENT"]:
        return card_type

    return ""

def download_image(image_info):
    image_index, full_img_url = image_info
    img_data = requests.get(full_img_url).content
    image_filename = f"{image_index}.jpg"

    with open(image_filename, 'wb') as img_file:
        img_file.write(img_data)

    print(f"Saved {image_filename} from {full_img_url}")

class Card:

    def __init__(self, name, cost, power, counter, color, type, effect, set, attribute, count, img, card):
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
        self.card = card

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
            "imgPath": self.img,
            "info": self.card
        }

cardCount = 1

try:
    options = driver.find_elements(By.XPATH, "//li[@class='selModalClose']")
    print(len(options) - 1)

except Exception as e:
    print(f"Error: {e}")

optionDropDown = WebDriverWait(driver, 20).until(
    EC.element_to_be_clickable((By.CLASS_NAME, "selModalButton"))
)

print("Clicking dropdown click")
optionDropDown.click()

for i in range(2, len(options) - 2):
    print(f"--Viewing fresh sheet--")

    options = driver.find_elements(By.XPATH, "//li[@class='selModalClose']")
    page_source = driver.page_source
    soup = BeautifulSoup(page_source, "html.parser")
    results = soup.find(class_="resultCol")
    card_elements = results.find_all("dl", class_="modalCol")
    card_images = results.find_all("a", class_="modalOpen")

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
        card_element = card.find("div", class_="infoCol")

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
            "OnePieceSim/assets/" + str(cardCount) + ".jpg",
            extract_card_type(card_element)
        )

        cards.append(newCard)
        cardCount += 1

    for idx, images in enumerate(card_images, start=len(img_urls)+1):
        image = images.find("img", class_="lazy")
        image_link = image["data-src"]
        full_img_url = URL.rstrip('/cardlist') + image_link.replace("..", "")
        img_urls.append((idx, full_img_url))

    # with ThreadPoolExecutor() as executor:
    #     executor.map(download_image, img_urls)

    print(f"Visible: {options[i].is_displayed()}")

    if options[i].is_displayed() == True:
        print("Detected as true")
        options[i].click()
        time.sleep(2)

    search_button = WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.XPATH, "//*[@id=\"cardlist\"]/main/article/div/div[1]/form/div[3]/input"))
    )

    print(f"Visible: {search_button.is_displayed()}")
    search_button.click()
    # time.sleep(2)

    optionDropDown = WebDriverWait(driver, 20).until(
        EC.element_to_be_clickable((By.CLASS_NAME, "selModalButton"))
    )

    optionDropDown.click()
    print(f"Dropdown visible: {optionDropDown.is_displayed()}")

    # time.sleep(2)
    print("Finished round")

cards_json = [card.to_dict() for card in cards]

# os.chdir("../../WebScraper")

with open("data.json", 'w+', encoding='utf-8') as file:
    json.dump(cards_json, file, ensure_ascii=False, indent=4)

os.chdir("../assets/cards/")

print(f"Amount of cards: {len(cards)}")
print(f"Amount of card images: {len(img_urls)}")

num_workers = os.cpu_count() * 2
print(f"Num of CPU: {num_workers}")
with ThreadPoolExecutor(max_workers=num_workers) as executor:
    executor.map(download_image, img_urls)

end_time = time.time()
print("Scraping complete.")
print(f"Completed in: {end_time - start_time}")
time.sleep(10)
driver.quit()
