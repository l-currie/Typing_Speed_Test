package Model;

import java.io.*;


import Persistence.Writeable;
import org.json.JSONObject;

public class WordSet implements Writeable {

    private String name;
    private String rawtext;
    private String[] wordArr;


    public WordSet(String text, String name) {
        this.rawtext = text;
        this.wordArr = text.split(" ");
        this.name = name;
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("rawText", this.rawtext);
        json.put("name", this.name);
        return json;
    }

    public String getRawtext(){
        return this.rawtext;
    }

    public void setRawtext(String text){
        this.rawtext = text;
    }

    public String[] getWordArr(){
        return wordArr;
    }


}
