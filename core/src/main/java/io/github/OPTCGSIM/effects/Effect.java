package io.github.OPTCGSIM.effects;

import io.github.OPTCGSIM.cards.Card;

public class Effect {
    
    public boolean checkKeyWord(Card card) {
        String input = card.getEffect();
        if (input == null || input.isEmpty()) {
            return false;
        }

        String result = "";

        if (input.contains("Activate Main")) {
            result = extractText(input, "Activate Main");
            //ActivateActivateMain();
        } else if (input.contains("Banish")) {
            result = extractText(input, "Banish");
            //ActivateBanish();
        } else if (input.contains("Blocker")) {
            result = extractText(input, "Blocker");
        } else if (input.contains("Don -")) {
            result = extractText(input, "Don -");
        } else if (input.contains("Don x")) {
            result = extractText(input, "Don x");
        } else if (input.contains("Double Attack")) {
            result = extractText(input, "Double Attack");
            
        } else if (input.contains("End of Your Turn")) {
            result = extractText(input, "End of Your Turn");
        } else if (input.contains("On Play")) {
            result = extractText(input, "On Play");
        } else if (input.contains("Rush")) {
            result = extractText(input, "Rush");
        } else if (input.contains("When Attacking")) {
            result = extractText(input, "When Attacking");
        } else if (input.contains("Your Turn")) {
            result = extractText(input, "Your Turn");
        } else {
            result = "No match found.";
        }

        System.out.println("Parsed Effect: " + result);
        return !result.isEmpty();
    }

    private static String extractText(String input, String keyword) {
        int start = input.indexOf(keyword);
        if (start == -1) return ""; 

        start += keyword.length();
        int end = input.indexOf(".", start);

        if (end == -1) end = input.length(); 

        return input.substring(start, end).trim();
    }

}