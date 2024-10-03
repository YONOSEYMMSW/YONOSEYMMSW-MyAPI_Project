package BSU.EDU.cs;

import net.minidev.json.JSONArray;
import net.minidev.json.JSONObject;
import net.minidev.json.JSONValue;
import java.util.ArrayList;
import java.util.List;

public class RevisionObjectParser {

    // Parse the revisions from the input JSON string and return a list of RevisionObject
    public List<RevisionObject> parse(String jsonString) throws Exception {
        // Parse the JSON string to a JSONObject
        JSONObject jsonObject = (JSONObject) JSONValue.parse(jsonString);

        // Check for the presence of the 'query' key in the JSON response
        if (!jsonObject.containsKey("query")) {
            throw new Exception("Expected a 'query' property in the JSON response.");
        }

        JSONObject query = (JSONObject) jsonObject.get("query");

        // Check if the query object is empty
        if (query == null || query.isEmpty()) {
            throw new Exception("No pages found in the 'query' property.");
        }

        // Extract pages from the query
        JSONObject pages = (JSONObject) query.get("pages");
        if (pages == null || pages.isEmpty()) {
            throw new Exception("No pages found in the 'query' property.");
        }

        // Get the first page
        JSONObject firstPage = (JSONObject) pages.get(pages.keySet().iterator().next());
        JSONArray revisions = (JSONArray) firstPage.get("revisions");
        List<RevisionObject> revisionList = new ArrayList<>();

        // Check if revisions are present
        if (revisions != null) {
            for (Object revision : revisions) {
                JSONObject revisionObj = (JSONObject) revision;
                String username = (String) revisionObj.get("user");
                String timestamp = (String) revisionObj.get("timestamp");
                revisionList.add(new RevisionObject(username, timestamp));
            }
        }

        return revisionList;
    }
}
