package org.example.notes_app.controller;

import jakarta.persistence.*;

@Entity
@Table(name = "notes")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String note;
    private String tags;
    private boolean important;
    private int likes = 0;

    protected Note() {} // JPA needs this

    public Note(String note, String tags, boolean important) {
        this.note = note;
        this.tags = tags;
        this.important = important;
    }

    public Long getId() { return id; }
    public String getNote() { return note; }
    public String getTags() { return tags; }
    public boolean isImportant() { return important; }
    public int getLikes() { return likes; }

    public void like() { this.likes++; }
}
