package io.toro.WRITEONME.Services;

import java.util.List;

import io.toro.WRITEONME.Model.Note;

public interface NoteService {

    List<Note> findAllNotes();

    List<Note> findByTitle(String title);

    Note findById(Long id);

    Note newNote(Note note);

    void deleteNote(Long id);

    void updateNote(Long id, Note note);

}
