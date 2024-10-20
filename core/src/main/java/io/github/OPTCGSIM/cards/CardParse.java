package io.github.OPTCGSIM.cards;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CardParse {
    public static Array<Card> parseJSON() {
        Array<Card> cards = null;
        Texture cardBackTexture = new Texture("card_back.png");

        try {
            String content = new String(Files.readAllBytes(Paths.get("WebScraper/data.json")));
            JSONArray jsonArr = new JSONArray(content);


            for (int i = 0; i < jsonArr.length(); i++){
                JSONObject jsonObject = jsonArr.getJSONObject(i);
                String title = jsonObject.getString("name");
                String cost = jsonObject.getString("cost");
                String power = jsonObject.getString("power");
                String counter = jsonObject.getString("counter");
                String color = jsonObject.getString("color");
                String type = jsonObject.getString("type");
                String effect = jsonObject.getString("effect");
                String set = jsonObject.getString("set");
                String attribute = jsonObject.getString("attribute");
                int cardNo = jsonObject.getInt("cardNo");
                String imgPath = jsonObject.getString("imgPath");
                String info = jsonObject.getString("info");

                int newCost = Integer.parseInt(cost);
                int newPower = Integer.parseInt(power);
                int newCounter = Integer.parseInt(counter);

                switch (info){
                    case "CHARACTER":
                        CharacterCard card = new CharacterCard(cardBackTexture, title, newCost, newPower, attribute, color, newCounter, attribute, effect, info, type);
                        cards.add(card);
                    case "LEADER":
                        continue;
                    case "EVENT":
                        continue;
                    case "STAGE":
                        continue;
                    default:
                        System.out.println("Something went wrong");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cards;
    }
}
