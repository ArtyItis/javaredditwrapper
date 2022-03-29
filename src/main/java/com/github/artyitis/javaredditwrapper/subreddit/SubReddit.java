package com.github.artyitis.javaredditwrapper.subreddit;

import org.json.simple.JSONObject;

public class SubReddit {
    private String display_name, name, title, description, lang, url, id;
    private boolean over18;
    private int subscribers, created, accounts_active;
    private SubReddit_Type subreddit_type;

    public SubReddit(JSONObject subRedditJSON) {
        JSONObject data = (JSONObject) subRedditJSON.get("data");
        display_name = (String) data.get("display_name");
        name = (String) data.get("name");
        title = (String) data.get("title");
        description = (String) data.get("description");
        lang = (String) data.get("lang");
        url = (String) data.get("url");
        id = (String) data.get("id");
        over18 = (boolean) data.get("over18");
        subscribers = ((Number) data.get("subscribers")).intValue();
        created = ((Number) data.get("created")).intValue();
        accounts_active = ((Number) data.get("accounts_active")).intValue();
        String type = (String) data.get("subreddit_type");
        subreddit_type = SubReddit_Type.valueOf(type.toUpperCase());
    }

    public String getDisplay_name() {
        return this.display_name;
    }

    public String getName() {
        return this.name;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLang() {
        return this.lang;
    }

    public String getUrl() {
        return this.url;
    }

    public String getId() {
        return this.id;
    }

    public boolean isOver18() {
        return this.over18;
    }

    public long getSubscribers() {
        return this.subscribers;
    }

    public long getCreated() {
        return this.created;
    }

    public long getAccounts_active() {
        return this.accounts_active;
    }

    public SubReddit_Type getSubreddit_type() {
        return this.subreddit_type;
    }

    @Override
    public String toString() {
        return "{" + " display_name='" + display_name + "'" + ", name='" + name + "'" + ", title='" + title + "'"
                + ", description='" + description + "'" + ", lang='" + lang + "'" + ", url='" + url + "'" + ", id='"
                + id + "'" + ", over18='" + over18 + "'" + ", subscribers='" + subscribers + "'" + ", created='"
                + created + "'" + ", accounts_active='" + accounts_active + "'" + ", subreddit_type='" + subreddit_type
                + "'" + "}";
    }
}
