package com.github.artyitis.javaredditwrapper;

import com.github.artyitis.javaredditwrapper.subreddit.SubReddit;

import org.json.simple.JSONObject;

public class RedditPost {
    /*
     * if is_video == true get video url from fallback_url
     * -----------------------------------------------------------------------------
     * if post_hint isEquals image get image url form url or url_overridden_by_dest
     */
    private SubReddit subReddit;
    private String title, permalink, url, url_overridden_by_dest, fallback_url, id, author, post_hint;
    private boolean is_video, stickied, hidden, is_original_content, is_created_from_ads_ui, spoiler, over_18;
    private int downs, ups, num_comments, total_awards_received, score;
    private float upvote_ratio;

    public RedditPost(JSONObject redditPostJSON) {
        JSONObject data = (JSONObject) redditPostJSON.get("data");
        title = (String) data.get("title");
        permalink = "https://www.reddit.com" + (String) data.get("permalink");
        url = (String) data.get("url");
        url_overridden_by_dest = (String) data.get("url_overridden_by_dest");
        fallback_url = (String) data.get("fallback_url");
        id = (String) data.get("id");
        author = (String) data.get("author");
        post_hint = (String) data.get("post_hint");

        is_video = (boolean) data.get("is_video");
        stickied = (boolean) data.get("stickied");
        hidden = (boolean) data.get("hidden");
        is_original_content = (boolean) data.get("is_original_content");
        is_created_from_ads_ui = (boolean) data.get("is_created_from_ads_ui");
        spoiler = (boolean) data.get("spoiler");
        over_18 = (boolean) data.get("over_18");

        downs = ((Number) data.get("downs")).intValue();
        ups = ((Number) data.get("ups")).intValue();
        num_comments = ((Number) data.get("num_comments")).intValue();
        total_awards_received = ((Number) data.get("total_awards_received")).intValue();
        score = ((Number) data.get("score")).intValue();

        upvote_ratio = ((Number) data.get("upvote_ratio")).floatValue();
    }

    public boolean containsFile() {
        return is_video || post_hint.equalsIgnoreCase("image");
    }

    public SubReddit getSubReddit() {
        return this.subReddit;
    }

    public String getTitle() {
        return this.title;
    }

    public String getPermalink() {
        return this.permalink;
    }

    public String getUrl() {
        return this.url;
    }

    public String getUrl_overridden_by_dest() {
        return this.url_overridden_by_dest;
    }

    public String getFallback_url() {
        return this.fallback_url;
    }

    public String getId() {
        return this.id;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getPost_hint() {
        return this.post_hint;
    }

    public boolean is_video() {
        return this.is_video;
    }

    public boolean isStickied() {
        return this.stickied;
    }

    public boolean isHidden() {
        return this.hidden;
    }

    public boolean is_original_content() {
        return this.is_original_content;
    }

    public boolean is_created_from_ads_ui() {
        return this.is_created_from_ads_ui;
    }

    public boolean isSpoiler() {
        return this.spoiler;
    }

    public boolean isOver18() {
        return this.over_18;
    }

    public int getDowns() {
        return this.downs;
    }

    public int getUps() {
        return this.ups;
    }

    public int getNum_comments() {
        return this.num_comments;
    }

    public int getTotal_awards_received() {
        return this.total_awards_received;
    }

    public int getScore() {
        return this.score;
    }

    public float getUpvote_ratio() {
        return this.upvote_ratio;
    }

    @Override
    public String toString() {
        return "{" + " subReddit='" + subReddit + "'" + ", title='" + title + "'" + ", permalink='" + permalink + "'"
                + ", url='" + url + "'" + ", url_overridden_by_dest='" + url_overridden_by_dest + "'"
                + ", fallback_url='" + fallback_url + "'" + ", id='" + id + "'" + ", author='" + author + "'"
                + ", post_hint='" + post_hint + "'" + ", is_video='" + is_video + "'" + ", stickied='" + stickied + "'"
                + ", hidden='" + hidden + "'" + ", is_original_content='" + is_original_content + "'"
                + ", is_created_from_ads_ui='" + is_created_from_ads_ui + "'" + ", spoiler='" + spoiler + "'"
                + ", downs='" + downs + "'" + ", ups='" + ups + "'" + ", num_comments='" + num_comments + "'"
                + ", total_awards_received='" + total_awards_received + "'" + ", score='" + score + "'"
                + ", upvote_ratio='" + upvote_ratio + "'" + "}";
    }

}
