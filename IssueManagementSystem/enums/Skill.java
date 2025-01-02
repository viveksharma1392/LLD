package enums;

import java.util.HashMap;
import java.util.Map;

public enum Skill {
    QA("quality assurance"),
    JAVA("java"),
    PYTHON("python");
    public final String label;
    private static final Map<String, Skill> BY_LABEL = new HashMap<String, Skill>();
    static {
        for (Skill e: values()) {
            BY_LABEL.put(e.label.toLowerCase(), e);
        }
    }
    private Skill(String label){
        this.label = label;
    }

    public static Skill validOfLabel(String label){
        return BY_LABEL.get(label.toLowerCase());
    }
}
