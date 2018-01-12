package io.toro.WRITEONME;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import io.toro.WRITEONME.Model.Note;
import io.toro.WRITEONME.Repository.NoteRepository;
import io.toro.WRITEONME.Services.NoteService;
import io.toro.WRITEONME.Services.NoteServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@DataJpaTest
public class NoteImplTests extends WriteonmeApplicationTests{

    private NoteService service;

    private NoteRepository repository;

    List<Note> list = new ArrayList<>(  );



    @Before
    public void setupMock() {
        repository = mock( NoteRepository.class );
        service = new NoteServiceImpl( repository );
        list.add( new Note("first book") );
        list.add( new Note("second book") );
        list.add( new Note("third book") );
    }

    @Test
    public void createNew() throws Exception {
        Note note = new Note("return me");
        when( repository.save( note)).thenReturn( note );
        assertEquals( note, service.newNote( note ) );
    }

    @Test
    public void findAll() throws Exception {
        when( repository.findAll() ).thenReturn( list );
        assertEquals(3, service.findAllNotes().size(),0);
    }

    @Test
    public void findByTitleNotFound() throws Exception {
        when( repository.findByTitle( "first book" )).thenReturn( list );
        assertNotEquals( "not found", repository.findByTitle("first book"), service.findByTitle( "bok" ) );
    }

    @Test
    public void findByTitleFound() throws Exception {
        when( repository.findByTitle( "first book" )).thenReturn( list );
        assertEquals( repository.findByTitle("first book"), service.findByTitle( "first book" ) );
    }

    @Test
    public void deleteById() throws Exception {
        doNothing().when( repository ).delete( isA( Long.class ) );
        Note note = new Note("return me");
        when( repository.save( note)).thenReturn( note );
        Note note2 = new Note("to the sea");
        when( repository.save( note2)).thenReturn( note2 );
        repository.save(note);
        repository.save(note2);
        service.deleteNote( ( long ) 2 );
        verify( service, times( 1 ) ).deleteNote( ( long ) 2 );
    }

    @Test
    public void updateNote() throws Exception {
        Note note = new Note("return me");
        when( repository.save( note)).thenReturn( note );
        when( repository.findById( ( long ) 1 ) ).thenReturn( note );
        service.updateNote( ( long ) 1, new Note( "binalik ko" ) );
        assertEquals( "binalik ko", repository.findById( ( long ) 1 ).getTitle() );

    }
}
