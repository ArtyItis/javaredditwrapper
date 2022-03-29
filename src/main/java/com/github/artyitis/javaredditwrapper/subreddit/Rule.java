package com.github.artyitis.javaredditwrapper.subreddit;

import org.json.simple.JSONObject;

public class Rule {
    private String kind, description, short_name, violation_reason, priority;

    public Rule(JSONObject data) {
        kind = (String) data.get("kind");
        description = (String) data.get("description");
        short_name = (String) data.get("short_name");
        violation_reason = (String) data.get("violation_reason");
        priority = (String) data.get("priority");
    }

    public String getKind() {
        return this.kind;
    }

    public String getDescription() {
        return this.description;
    }

    public String getShort_name() {
        return this.short_name;
    }

    public String getViolation_reason() {
        return this.violation_reason;
    }

    public String getPriority() {
        return this.priority;
    }

    @Override
    public String toString() {
        return "{" + " kind='" + kind + "'" + ", description='" + description + "'" + ", short_name='" + short_name
                + "'" + ", violation_reason='" + violation_reason + "'" + ", priority='" + priority + "'" + "}";
    }

}
