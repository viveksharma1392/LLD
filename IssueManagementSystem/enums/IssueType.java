package enums;

import java.util.HashMap;
import java.util.Map;

public enum IssueType {
    Bug("bug"),
    WorkItem("work item");

    public final String label;

    private IssueType(String label) {
        this.label = label;
    }

    private static final Map<String, IssueType> BY_LABEL = new HashMap<>();

    static {
        for (IssueType e: values()) {
            BY_LABEL.put(e.label.toLowerCase(), e);
        }
    }

    public static IssueType valueOfLabel(String label) {
        return BY_LABEL.get(label.toLowerCase());
    }

}
