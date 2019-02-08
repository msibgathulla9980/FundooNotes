package com.bridgelabz.spring.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings({ "serial", "unused" })
@Entity
@Table(name = "Notes")
public class Note implements Serializable {

	@Id // indicates it is primary key
	@GeneratedValue
	@Column(name = "noteId")
	private int noteId;

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	@Column(name = "description")
	private String description;

	@Column(name = "title")
	private String title;

	@Column(name = "isPinned")
	private boolean isPinned;

	@Column(name = "inTrash")
	private boolean inTrash;

	@Column(name = "isArchive")
	private boolean isArchive;

	@Column(name = "updatedTime")
	@UpdateTimestamp
	private Timestamp updatedTime;

	@Column(name = "createdTime")
	@UpdateTimestamp
	private Timestamp createdTime;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserDetails user_id;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = Label.class, cascade = { CascadeType.ALL })
	@JoinTable(name = "Note_Label", joinColumns = { @JoinColumn(name = "note_Id") }, inverseJoinColumns = {
			@JoinColumn(name = "label_Id") })
	private List<Label> listOfLabels;

	public List<Label> getListOfLabels() {
		return listOfLabels;
	}

	public void setListOfLabels(List<Label> listOfLabels) {
		this.listOfLabels = listOfLabels;
	}

	public int getLabelId() {
		return labelId;
	}

	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}

	private int labelId;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isPinned() {
		return isPinned;
	}

	public void setPinned(boolean isPinned) {
		this.isPinned = isPinned;
	}

	public boolean isInTrash() {
		return inTrash;
	}

	public void setInTrash(boolean inTrash) {
		this.inTrash = inTrash;
	}

	public boolean isArchive() {
		return isArchive;
	}

	public void setArchive(boolean isArchive) {
		this.isArchive = isArchive;
	}

	public Timestamp getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(Timestamp updatedTime) {
		this.updatedTime = updatedTime;
	}

	public Timestamp getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}

	@Override
	public String toString() {
		return "Notes [id=" + noteId + ", title=" + title + ", description=" + description + ", isPinned=" + isPinned
				+ ", inTrash=" + inTrash + ", updatedTime=" + updatedTime + ", createdTime=" + createdTime
				+ ", isArchive=" + isArchive + ", user_id=" + user_id + "]";
	}

	public UserDetails getUser_id() {
		return user_id;
	}

	public void setUser_id(UserDetails user_id) {
		this.user_id = user_id;
	}

}