package com.example.sql;

//this is model class

public class Todo {


    private int id;
    private String title,description ;
    private long start,end;

    public  Todo(){

    }
    public Todo(int id, String title, String description, long start, long end) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
    }

    public Todo(String title, String description, long start, long end) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
    }
 //setters


    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }
}
