package BSU.EDU.cs;

public class RevisionObject {
    private final String username;
    private final String timestamp;

    public RevisionObject(String username, String timestamp) {
        this.username = username;
        this.timestamp = timestamp;
    }

    public String getUsername() {
        return username;
    }

    public String getTimestamp() {
        return timestamp;
    }

    // Optionally, override toString for easier debugging
    @Override
    public String toString() {
        return String.format("User: %s, Timestamp: %s", username, timestamp);
    }
}
