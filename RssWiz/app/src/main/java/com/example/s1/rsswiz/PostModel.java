package com.example.s1.rsswiz;

/**
 * Created by S1 on 1/18/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class PostModel {

    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("time")
    @Expose
    private String time;

    /**
     * @return The url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url The url
     */
    public void setUrl(String url) {
        this.url = url;
    }



    /**
     * @return desc description
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @param desc Site description
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * @return The time
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time The link
     */
    public void setTime(String time) {
        this.time = time;
    }
}