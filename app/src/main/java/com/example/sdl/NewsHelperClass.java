package com.example.sdl;

public class NewsHelperClass {
    String Title,Content,Date;

    public NewsHelperClass() {

    }

    public NewsHelperClass(String title, String content, String date) {
        Title = title;
        Content = content;
        Date = date;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
