package org.example.notes_app.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.example.notes_app.controller.Note;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class NoteRepository {
    @PersistenceContext
    private EntityManager e;

    public Note save(Note note) {
        if (note.getId() == null) {
            e.persist(note);
            return note;
        } else {
            return e.merge(note);
        }
    }

    public Note findById(Long id) {
        return e.find(Note.class, id);
    }

    public List<Note> findAll() {
        return e.createQuery("SELECT n FROM Note n", Note.class).getResultList();
    }

    public List<Note> findTag(String tag) {
        return e.createQuery("SELECT n from Note n where n.tags like :tag", Note.class)
                .setParameter("tag", "%" + tag + "%")
                .getResultList();
    }

    public List<Note> findImportance(boolean important) {
        return e.createQuery("SELECT n FROM Note n where n.important = :important", Note.class)
                .setParameter("important", important)
                .getResultList();
    }

    public List<Note> trending()
    {
        return e.createQuery("select n from Note n order by n.likes desc ", Note.class)
                .getResultList();
    }
}
