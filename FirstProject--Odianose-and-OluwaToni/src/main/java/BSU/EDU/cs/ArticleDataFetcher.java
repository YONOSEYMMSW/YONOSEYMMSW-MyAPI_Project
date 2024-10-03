package BSU.EDU.cs;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ArticleDataFetcher {

    public String fetchArticleData(String articleName) throws IOException {
        // Encode the article name to handle spaces and illegal characters
        String encodedArticleName = URLEncoder.encode(articleName, StandardCharsets.UTF_8);

        // Build the API URL using the encoded article name
        String apiUrl = String.format(
                "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=" +
                        encodedArticleName + "&rvprop=timestamp%%7Cuser&rvlimit=15&redirects"
            );

        // Open a connection to the Wikipedia API
        URL url = URI.create(apiUrl).toURL();
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent", "ArticleDataFetcher/0.1 (dllargent@bsu.edu)");
        connection.connect();

        // Fetch the article data
        try (InputStream inputStream = connection.getInputStream()) {
            // Cache the InputStream content in memory
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                baos.write(buffer, 0, bytesRead);
            }
            byte[] data = baos.toByteArray();



            // Parse article metadata using ArticleParser
            ArticleParser articleParser = new ArticleParser();
            String title = articleParser.parseTitle(new String(data, StandardCharsets.UTF_8));
            String redirects = articleParser.parseRedirects(new String(data, StandardCharsets.UTF_8)).toJSONString();

            // Parse revisions using RevisionObjectParser
            RevisionObjectParser revisionParser = new RevisionObjectParser();
            List<RevisionObject> revisions = revisionParser.parse(new String(data, StandardCharsets.UTF_8));

            // Format the revisions for display
            RevisionObjectFormatter formatter = new RevisionObjectFormatter();
            StringBuilder formattedResult = new StringBuilder();
            formattedResult.append("Title: ").append(title).append("\n");
            formattedResult.append("Redirects: ").append(redirects).append("\n");
            formattedResult.append("Revisions:\n");

            for (RevisionObject revision : revisions) {
                formattedResult.append(formatter.formatRevision(revision)).append("\n");
            }

            // Return the formatted result
            return formattedResult.toString();
        } catch (Exception e) {
            // Handle exceptions and rethrow as a runtime exception
            throw new RuntimeException("Error fetching article data: " + e.getMessage(), e);
        }
    }
}
