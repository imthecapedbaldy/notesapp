package io.toro.WRITEONME.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.toro.WRITEONME.Model.Note;
import io.toro.WRITEONME.Services.NoteService;

@RestController
@RequestMapping("/api/notes/")
public class NoteController {



    private NoteService service;

    @Autowired
    public NoteController( NoteService service ) {this.service = service;}

    @GetMapping("/{title}")
    public List<Note> getByTitle(String title){
        return service.findByTitle( title );
    }

    @GetMapping("/")
    public List<Note> getAll(){
        return service.findAllNotes();
    }

    @PostMapping("/")
    public Note createNote(@RequestBody Note note){
        return service.newNote( note );
    }

    @PutMapping("/{id}")
    public void updateNote(@PathVariable(name = "id") Long id, @RequestBody Note note){
        service.updateNote( id, note );
    }

    @DeleteMapping("/{id}")
    public void deleteNoteById(@PathVariable(name = "id") Long id){
        service.deleteNote( id );
    }


}
