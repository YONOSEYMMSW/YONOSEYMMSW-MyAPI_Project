package BSU.EDU.cs;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;

public class ArticleParser {

    // Method to parse redirects from JSON string
    public JSONArray parseRedirects(String jsonString) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonString);
        JSONObject query = (JSONObject) jsonObject.get("query");
        JSONArray redirects = (JSONArray) query.get("redirects");
        return redirects != null ? redirects : new JSONArray(); // Return empty array if null
    }

    // Method to parse title from JSON string
    public String parseTitle(String jsonString) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonString);
        JSONObject query = (JSONObject) jsonObject.get("query");
        JSONObject page = (JSONObject) query.get("pages");
        JSONObject firstPage = (JSONObject) page.values().iterator().next(); // Get the first page object
        return firstPage.getAsString("title");
    }

    // Method to parse missing flag from JSON string
    public boolean parseMissing(String jsonString) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonString);
        JSONObject query = (JSONObject) jsonObject.get("query");
        JSONObject page = (JSONObject) query.get("pages");
        JSONObject firstPage = (JSONObject) page.values().iterator().next(); // Get the first page object
        return firstPage.containsKey("missing");
    }

    // Method to parse usernames from JSON string
    public JSONArray parseUsernames(String jsonString) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonString);
        JSONObject query = (JSONObject) jsonObject.get("query");
        JSONObject page = (JSONObject) query.get("pages");
        JSONObject firstPage = (JSONObject) page.values().iterator().next(); // Get the first page object
        JSONArray revisions = (JSONArray) firstPage.get("revisions");
        JSONArray usernames = new JSONArray();

        if (revisions != null) {
            for (Object revision : revisions) {
                JSONObject revisionObj = (JSONObject) revision;
                usernames.add(revisionObj.getAsString("user"));
            }
        }
        return usernames;
    }

    // Method to parse timestamps from JSON string
    public JSONArray parseTimestamps(String jsonString) {
        JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonString);
        JSONObject query = (JSONObject) jsonObject.get("query");
        JSONObject page = (JSONObject) query.get("pages");
        JSONObject firstPage = (JSONObject) page.values().iterator().next(); // Get the first page object
        JSONArray revisions = (JSONArray) firstPage.get("revisions");
        JSONArray timestamps = new JSONArray();

        if (revisions != null) {
            for (Object revision : revisions) {
                JSONObject revisionObj = (JSONObject) revision;
                timestamps.add(revisionObj.getAsString("timestamp"));
            }
        }
        return timestamps;
    }
}
