package io.github.OPTCGSIM.lwjgl3;

import org.json.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Card {
    private String name;
    private int cost;
    private int power;
    private int counter;
    private String color;
    private String type;
    private String effect;
    private String set;
    private String attribute;

    public Card(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
