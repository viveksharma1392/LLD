package enums;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    New("new"),
    InProgress("in progress"),
    Closed("closed"),
    Completed("completed"),
    Blocked("blocked"),
    Waiting("waiting");

    public final String label;
    private static final Map<String, Status> BY_LABEL = new HashMap<>();
    private Status(String label){
        this.label = label;
    }


    static {
        for (Status e: values()) {
            BY_LABEL.put(e.label.toLowerCase(), e);
        }
    }
    public static Status valueOfLabel(String label) {
        return BY_LABEL.get(label.toLowerCase());
    }
}
