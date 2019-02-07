package com.bridgelabz.spring.dao;

import java.util.List;

import com.bridgelabz.spring.model.Label;
import com.bridgelabz.spring.model.Notes;

public interface NotesDao {
	int createdNotes(Notes usernotes);

    void deleteNotes(int id);
    Notes getNotesByID(int id);
    Notes updateNotes(int id,Notes usernotes1);
    List<Notes> retrieve();
    
    int createLabel(Label label);
   
   
    Label getLabelByID(int id);
    
	Label updateLabel(int id,Label label1);
	
	List<Label> retrieveLabel();
	
	  void deleteLabel(int id);
}
