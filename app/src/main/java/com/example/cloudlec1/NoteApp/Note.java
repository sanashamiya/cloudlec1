package com.example.cloudlec1.NoteApp;

public class Note {
    static String id;
    String Titel;
    String Description ;
    private  Note(){}
    Note(String id, String Titel , String Description) {
        this.id = id;
        this.Titel = Titel;
        this.Description = Description;


    }

    public void setId(String id) {
        this.id = id;
    }

    public static String getId() {
        return id;
    }

    public String getTitele() {
        return Titel;
    }
    public String getDescription() {
        return Description;
    }


}
