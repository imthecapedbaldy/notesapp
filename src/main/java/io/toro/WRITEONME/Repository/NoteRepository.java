package io.toro.WRITEONME.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.toro.WRITEONME.Model.Note;
import io.toro.WRITEONME.Services.NoteService;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByTitle(String title);

    Note findById(Long id);

}
