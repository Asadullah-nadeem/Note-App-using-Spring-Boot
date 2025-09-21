package org.example.notes_app.Service;

import org.example.notes_app.Repository.NoteRepository;
import org.example.notes_app.controller.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService
{
    private final NoteRepository repository;

    public NoteService(NoteRepository repository)
    {
        this.repository = repository;
    }

    private String autoTag(String content, String givenTags)
    {
        StringBuilder tags = new StringBuilder(givenTags == null ? " " : givenTags);

        if (content.toLowerCase()
                .contains("java") && !tags.toString()
                .contains("java")) tags.append(",java");
        if (content.toLowerCase()
                .contains("spring") && !tags.toString()
                .contains("spring")) tags.append("spring");
        return tags
                .toString()
                .replaceAll("^,", "");
    }

    public Note save(String noteText, String tags, boolean important)
    {
        String finalTags = autoTag(noteText, tags);
        Note note = new Note(noteText, finalTags, important);
        return repository.save(note);
    }

    public List<Note> all()
    {
        return repository.findAll();
    }

    public List<Note> filterByTag(String tag)
    {
        return repository.findTag(tag);
    }

    public List<Note> filterByImportance(boolean important)
    {
        return repository.findImportance(important);
    }

    public Note like(Long id)
    {
        Note note = repository.findById(id);
        if (note == null)
            throw new
                    RuntimeException("Note Not Found");
        note.like();
        return repository.save(note);
    }

    public List<Note> trending()
    {
        return repository.trending();
    }
}
