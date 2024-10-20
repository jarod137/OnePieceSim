package io.github.OPTCGSIM.lwjgl3;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ScriptRunner {
    public static void startScript() {
        ProcessBuilder processBuild = new ProcessBuilder();
        processBuild.command("python3", "WebScraper/webTest.py");

        try {
            Process process = processBuild.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            int exitCode = process.waitFor();
            System.out.println("\nExited with error code : " + exitCode);

        } catch(IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void printDeck(ArrayList<Card> deck) {
        if (deck == null) {
            return;
        }

        for (Card card: deck) {
            System.out.println(card.getName());
        }
    }

    public static void main(String[] args) {
        //Uncomment to run
        //startScript();
    }
}
