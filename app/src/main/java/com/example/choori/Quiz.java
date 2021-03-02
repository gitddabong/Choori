package com.example.choori;

public class Quiz {
    String title;
    int coin;
    String author;
    String documentID;

    public Quiz(String title, int coin, String author, String documentID) {
        this.title = title;
        this.coin = coin;
        this.author = author;
        this.documentID = documentID;
    }

    public String getTitle() {
        return title;
    }

    public int getCoin() {
        return coin;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDocumentID() {
        return documentID;
    }

    public void setDocumentID(String documentID) {
        this.documentID = documentID;
    }
}
