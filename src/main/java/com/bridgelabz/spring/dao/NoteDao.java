package com.bridgelabz.spring.dao;

import java.math.BigInteger;
import java.util.List;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Note;

public interface NoteDao {
	int createNote(Note usernote);

	void deleteNote(int newId);
	Note getNoteByID(int noteId);
	Note updateNote(int id,Note existingusernote);
	List<Note> retrieve(int noteId,Note newNote);

	int createLabel(Label label);


	Label getLabelByID(int id);

	Label updateLabel(int id,Label updatedlabel);

	List<Label> retrieveLabel();

	void deleteLabel(int id);

}
