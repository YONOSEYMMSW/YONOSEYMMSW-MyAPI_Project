package BSU.EDU.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class RevisionObjectParserTest {

    private RevisionObjectParser revisionObjectParser;
    private byte[] jsonData;

    @BeforeEach
    public void setup() throws Exception {
        // Initialize RevisionObjectParser before each test
        revisionObjectParser = new RevisionObjectParser();

        // Load the test JSON file from resources as byte[]
        InputStream jsonInputStream = getClass().getClassLoader().getResourceAsStream("test.json");
        assertNotNull(jsonInputStream, "Test JSON file should be available in resources");

        // Convert InputStream to byte[]
        jsonData = jsonInputStream.readAllBytes();
        jsonInputStream.close();
    }

    @Test
    public void testParseRevisions() throws Exception {
        // Test parsing of revisions from byte[] data
        List<RevisionObject> revisions = revisionObjectParser.parse(Arrays.toString(jsonData));
        assertNotNull(revisions, "Revisions list should not be null");
        assertFalse(revisions.isEmpty(), "Revisions list should not be empty");
        assertEquals(1, revisions.size(), "Expected 1 revision in the test JSON");

        // Validate content of the first revision
        RevisionObject firstRevision = revisions.getFirst();
        assertEquals("Editor123", firstRevision.getUsername(), "Expected username 'Editor123'");
        assertEquals("2020-01-01T12:00:00Z", firstRevision.getTimestamp(), "Expected timestamp '2020-01-01T12:00:00Z'");
    }
}
