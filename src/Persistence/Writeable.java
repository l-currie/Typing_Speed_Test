package Persistence;

import org.json.JSONObject;

public interface Writeable {
   //returns this as a JSON object
   JSONObject toJson();
}
