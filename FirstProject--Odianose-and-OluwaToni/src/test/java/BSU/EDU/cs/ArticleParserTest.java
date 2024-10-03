package BSU.EDU.cs;

import net.minidev.json.JSONArray;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ArticleParserTest {

    private ArticleParser articleParser;
    private String jsonString;

    @BeforeEach
    public void setup() {
        // Initialize ArticleParser before each test
        articleParser = new ArticleParser();

        // Load the test JSON file from resources and convert it to a String
        InputStream jsonInputStream = getClass().getClassLoader().getResourceAsStream("test.json");
        assertNotNull(jsonInputStream, "Test JSON file should be available in resources");

        jsonString = new Scanner(jsonInputStream, StandardCharsets.UTF_8).useDelimiter("\\A").next();
    }

    @Test
    public void testParseRedirects() throws Exception {
        JSONArray result = articleParser.parseRedirects(jsonString);
        assertEquals(new JSONArray(), result, "Expected empty redirects array");
    }

    @Test
    public void testParseTitle() throws Exception {
        String result = articleParser.parseTitle(jsonString);
        assertEquals("Soup", result, "Expected title 'Soup'");
    }

    @Test
    public void testParseMissing() throws Exception {
        boolean result = articleParser.parseMissing(jsonString);
        assertEquals(false, result, "Expected 'missing' to be false");
    }

    @Test
    public void testParseUsernames() throws Exception {
        JSONArray result = articleParser.parseUsernames(jsonString);
        assertEquals("Editor123", result.getFirst(), "Expected username 'Editor123'");
    }

    @Test
    public void testParseTimestamps() throws Exception {
        JSONArray result = articleParser.parseTimestamps(jsonString);
        assertEquals("2020-01-01T12:00:00Z", result.getFirst(), "Expected timestamp '2020-01-01T12:00:00Z'");
    }
}
