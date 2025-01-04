package exception;
import java.util.Map;
import java.util.HashMap;

public enum ErrorCode {
    INVALID_PARAM("invalid parameters"),
    MAX_DEPTH("Max depth reached exception");
    private final String label;
    private static final Map<String, ErrorCode> LabelToValueMap = new HashMap<String, ErrorCode>();
    static {
        for (ErrorCode e: values()) {
            LabelToValueMap.put(e.label.toLowerCase(), e);
        }
    }
    ErrorCode(String label){
        this.label=label;
    }

    public static ErrorCode valueOfLabel(String label){
        return LabelToValueMap.get(label);
    }

}
