package com.bridgelabz.spring.dao;

import java.util.List;

import com.bridgelabz.spring.model.Notes;

public interface NotesDao {
	int createdNotes(Notes usernotes);
    Object usernotes=null;
    void deleteNotes(int id);
    Notes getUserByID(int id);
    Notes updateNotes(int id,Notes usernotes1);
    List<Notes> retrieve();
}
