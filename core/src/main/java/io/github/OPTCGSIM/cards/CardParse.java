package io.github.OPTCGSIM.cards;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class CardParse {
    private AssetManager assetManager;

    public CardParse(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    //TODO: Should probably move the assigment of images over here, rather than in DeckBuilder.java
    public ArrayList<Card> parseJSON() {
        System.out.println("Working directory: " + Paths.get("").toAbsolutePath());

        ArrayList<Card> cards = new ArrayList<>();
        Texture cardTexture = null;

        try {
            //testing fix
            Path currentDir = Paths.get("").toAbsolutePath();
            Path dataJsonPath = currentDir.resolve("../WebScraper/data.json");
            String content = new String(Files.readAllBytes(dataJsonPath));

//            String content = new String(Files.readAllBytes(Paths.get("WebScraper/data.json")));
            JSONArray jsonArr = new JSONArray(content);


            for (int i = 1; i < jsonArr.length(); i++) {
                JSONObject jsonObject = jsonArr.getJSONObject(i);
                String name = jsonObject.getString("name");
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
                String cardType = jsonObject.getString("info");

                int newCost = 0;
                int newPower = 0;
                int newCounter = 0;

                if (!cost.equals("-")){
                    newCost = Integer.parseInt(cost.replaceAll("[\\D]", ""));
                }

                if (!power.equals("-")){
                    newPower = Integer.parseInt(power.replaceAll("[\\D]", ""));
                }

                if (!counter.equals("-")){
                    newCounter = Integer.parseInt(counter.replaceAll("[\\D]", ""));
                }

                String texturePath = "cards/" + i + ".jpg";
                cardTexture = new Texture(texturePath);

                switch (cardType) {
                    case "CHARACTER":
                        CharacterCard characterCard = new CharacterCard(cardTexture, name, cardNo, cardType, color, newCost, newPower, newCounter, type, effect, set, attribute);
                        cards.add(characterCard);
                        break;
                    case "LEADER":
                        LeaderCard leaderCard = new LeaderCard(cardTexture, name, cardNo, cardType, color, newCost, newPower,  type, effect, set, attribute);
                        cards. add(leaderCard);
                        break;
                    case "EVENT":
                        EventCard eventCard = new EventCard(cardTexture, name, cardNo, cardType, color, newCost, type, effect, set);
                        cards.add(eventCard);
                        break;
                    case "STAGE":
                        StageCard stageCard = new StageCard(cardTexture, name, cardNo, cardType, color, newCost, type, effect, set);
                        cards.add(stageCard);
                        break;
                    default:
                        System.out.println("Something went wrong");
                }
            }

        } catch (IOException e) {
            System.err.println("IO error occurred while processing the file: " + e.getMessage());
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Null pointer encountered! Please check the object initialization.");
            e.printStackTrace();
        }

        return cards;
    }


    //Filters out the given cards depending on the filter specified
    public ArrayList<Card> filterCards(ArrayList<Card> cards, String filter) {
        ArrayList<Card> updatedCards = new ArrayList<>();

        for (Card card: cards) {
            if (card.getCardType().equalsIgnoreCase(filter)) {
                updatedCards.add(card);
            }
        }

        return updatedCards;
    }
}
