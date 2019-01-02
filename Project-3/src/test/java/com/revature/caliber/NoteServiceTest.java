/**
 * 
 */
package com.revature.caliber;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.revature.caliber.beans.Note;
import com.revature.caliber.beans.NoteType;
import com.revature.caliber.beans.QCStatus;
import com.revature.caliber.dao.NoteRepository;
import com.revature.caliber.service.NoteService;

/**
 * jUnit tests to test the NoteService code.  Uses Mockito to set up mock objects
 * and compares execution to those mock results. 
 * 
 * NOTE!!!   This testing is based on the test data from the H2
 * 
 * @author pgerringer
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class NoteServiceTest {
	private static final int BATCH_ID = 2150;
	private static final short WEEK_NUMBER = 5;
	
	@Mock
    private NoteRepository repo;
	
	@InjectMocks
	@Autowired
	private NoteService service;
	
	private static Note note5500, note5501, note5503, note5502, note5540, note5457; 
	private static List<Note> notes;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		notes = new ArrayList<Note>();
		
		note5500 = new Note();		
		note5500.setNoteId(2);
		note5500.setContent("Benefits of Microservices? Answered: Eliminate single point of failure");
		note5500.setWeek(WEEK_NUMBER);
		note5500.setBatchId(BATCH_ID);
		note5500.setTraineeId(5500);
		note5500.setType(NoteType.QC_TRAINEE);
		note5500.setQcStatus(QCStatus.Good);
		notes.add(note5500);
		
		// note5501 used for delete
		note5501 = new Note();		
		note5501.setNoteId(3);
		note5501.setContent("Is JavaScript OOP? Answered: Idk. confusing...");
		note5501.setWeek(WEEK_NUMBER);
		note5501.setBatchId(BATCH_ID);
		note5501.setTraineeId(5501);
		note5501.setType(NoteType.QC_TRAINEE);
		note5501.setQcStatus(QCStatus.Poor);
		notes.add(note5501);
		
		// note5503 used for create
		note5503 = new Note();		
		note5503.setNoteId(7);
		note5503.setContent("Great person.");
		note5503.setWeek(WEEK_NUMBER);
		note5503.setBatchId(BATCH_ID);
		note5503.setTraineeId(5503);
		note5503.setType(NoteType.QC_TRAINEE);
		note5503.setQcStatus(QCStatus.Good);
		notes.add(note5503);
		
		// note5502 used for update
		note5502 = new Note();		
		note5502.setNoteId(4);
		note5502.setContent("Something smells.  I think it is the trainee");
		note5502.setWeek(WEEK_NUMBER);
		note5502.setBatchId(BATCH_ID);
		note5502.setTraineeId(5502);
		note5502.setType(NoteType.QC_TRAINEE);
		note5502.setQcStatus(QCStatus.Average);
		notes.add(note5502);
		
		note5540 = new Note();		
		note5540.setNoteId(5);
		note5540.setContent("Asked questions about collections.  Answered all with confidence.");
		note5540.setWeek(WEEK_NUMBER);
		note5540.setBatchId(BATCH_ID);
		note5540.setTraineeId(5540);
		note5540.setType(NoteType.QC_TRAINEE);
		note5540.setQcStatus(QCStatus.Good);
		notes.add(note5540);
		
		note5457 = new Note();		
		note5457.setNoteId(6);
		note5457.setContent("Asked about AWS AIM.  correct");
		note5457.setWeek(WEEK_NUMBER);
		note5457.setBatchId(BATCH_ID);
		note5457.setTraineeId(5457);
		note5457.setType(NoteType.QC_TRAINEE);
		note5457.setQcStatus(QCStatus.Good);
		notes.add(note5457);
	}
	
	@Before
	public void setUp() throws Exception {		
		when(service.getAllNotes()).thenReturn(notes);
		
		when(service.createNote(note5501)).thenReturn(note5501);
		
		when(service.findById(2)).thenReturn(note5500);
		when(service.findById(3)).thenReturn(note5501);
		
		when(service.findByBatchAndWeek(BATCH_ID, WEEK_NUMBER)).thenReturn(notes);
	}
	
	@Test
	public void testGetAllNotes() {
		List<Note> list = service.getAllNotes();
		assertEquals(notes, list);
	}
	
	@Test
	public void testCreateNote() {
		service.createNote(note5503);
		verify(repo).save(note5503);
	}
	
	@Test
	public void testDeleteNote() {
		service.deleteNote(3);
		verify(repo).delete(3);
	}
	
	@Test
	public void testFindByID() {
		Note note = service.findById(2);
		assertEquals(note5500, note);
	}
	
	@Test
	public void testUpdateNote() {
		service.updateNote(note5502);
		verify(repo).save(note5502);
	}
	
	@Test
	public void testFindByBatchAndWeek() {
		List<Note> list = service.findByBatchAndWeek(BATCH_ID, WEEK_NUMBER);
		assertEquals(notes, list);
	}
}
