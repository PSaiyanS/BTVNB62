package com.example.btvnb62;

public class TinTuc {
    public String urlanh ;
    public String title ;
    public String url ;

    public String getUrlanh() {
        return urlanh;
    }

    public void setUrlanh(String urlanh) {
        this.urlanh = urlanh;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TinTuc(String urlanh, String title, String url) {
        this.urlanh = urlanh;
        this.title = title;
        this.url = url;
    }
}
