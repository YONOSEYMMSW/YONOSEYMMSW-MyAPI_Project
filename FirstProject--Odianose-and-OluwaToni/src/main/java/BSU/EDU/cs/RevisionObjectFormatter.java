package BSU.EDU.cs;

import java.util.List;

public class RevisionObjectFormatter {

    // Formats a single Revision object for display
    public String formatRevision(RevisionObject revision) {
        return String.format("User: %s, Timestamp: %s", revision.getUsername(), revision.getTimestamp());
    }

    // Formats a list of Revision objects for display
    public String formatRevisions(List<RevisionObject> revisionList) {
        StringBuilder revisionOutput = new StringBuilder();

        if (revisionList.isEmpty()) {
            revisionOutput.append("No revisions available.\n");
        } else {
            for (RevisionObject revision : revisionList) {
                revisionOutput.append(formatRevision(revision)).append("\n");
            }
        }

        return revisionOutput.toString();
    }
}
