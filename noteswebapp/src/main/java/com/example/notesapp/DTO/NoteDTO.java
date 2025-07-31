package com.example.notesapp.DTO;

public class NoteDTO {
    private int id;
    private String title;
    private String content;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setid(int id) {
        this.id = id;
    }

    public int getid(){
        return id;
    }
}
