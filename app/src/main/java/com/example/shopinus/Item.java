package com.example.shopinus;

public class Item {
    private String name;
    private String URL;
    private String sourceURL;

    public Item() {
    }

    public Item(String name, String URL, String sourceURL) {
        this.name = name;
        this.URL = URL;
        this.sourceURL = sourceURL;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getSourceURL() {
        return sourceURL;
    }

    public void setSourceURL(String sourceURL) {
        this.sourceURL = sourceURL;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", URL='" + URL + '\'' +
                '}';
    }
}
