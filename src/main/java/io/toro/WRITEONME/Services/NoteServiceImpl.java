package io.toro.WRITEONME.Services;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import io.toro.WRITEONME.Model.Note;
import io.toro.WRITEONME.Repository.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService{

    private NoteRepository repository;

    public NoteServiceImpl(NoteRepository repository){
        this.repository = repository;
    }

    @Override
    public List<Note> findAllNotes() {
        return repository.findAll();
    }

    @Override
    public List<Note> findByTitle( String title ) {
        return repository.findByTitle(title);
    }

    @Override
    public Note findById( Long id ) {
        return repository.findById( id );
    }

    @Override
    public Note newNote(Note note) {
        return repository.save( note );
    }
//add count
    @Override
    public void deleteNote( Long id ) {
        repository.delete( id );
    }

    @Override
    public void updateNote( Long id, Note note ) {
        for(Note existingNote: repository.findAll()){
            if( Objects.equals( id, existingNote.getId() ) )
                repository.save( new Note(id, note.getTitle()) );
        }
    }

   // public int

}
