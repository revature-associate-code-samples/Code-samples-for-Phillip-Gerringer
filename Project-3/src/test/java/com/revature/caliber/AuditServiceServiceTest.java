/**
 * 
 */
package com.revature.caliber;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.revature.caliber.beans.Note;
import com.revature.caliber.service.NoteService;

/**
 * @author pgerringer
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuditServiceApplication.class)
public class AuditServiceServiceTest {
	
	@Autowired
	private NoteService service;
	
	private Note note; 
	List<Note> listNote;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	@Before
	public void setUp() throws Exception {
		service = new NoteService();
		note = new Note();
		
		note.setBatchId(1);
		
		
		listNote.add(note);
		
	}
	
	@Test
	public void test() {
		
	}

}
