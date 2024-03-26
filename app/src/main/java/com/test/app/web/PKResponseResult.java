package com.test.app.web;

public class PKResponseResult {

    public PKResponseResult() {
        this.name = "";
        this.url = "";
    }

    public PKResponseResult(String name, String url)
    {
        this.name = name;
        this.url = url;
    }

    @Override
    public String toString() {
        return name + " " + url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String name;
    private String url;
}
