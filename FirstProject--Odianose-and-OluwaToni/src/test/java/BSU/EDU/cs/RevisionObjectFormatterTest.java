package BSU.EDU.cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RevisionObjectFormatterTest {

    private RevisionObjectFormatter formatter;
    private RevisionObject revision;

    @BeforeEach
    public void setup() {
        formatter = new RevisionObjectFormatter();
        revision = new RevisionObject("Editor123", "2020-01-01T12:00:00Z");
    }

    @Test
    public void testFormatRevision() {
        // Test formatting of a revision object
        String formatted = formatter.formatRevision(revision);
        assertEquals("User: Editor123, Timestamp: 2020-01-01T12:00:00Z",
                formatted, "Formatted revision output should match expected format");
    }
}
