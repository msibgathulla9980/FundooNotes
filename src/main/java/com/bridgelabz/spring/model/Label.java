package com.bridgelabz.spring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings({ "serial" })
@Entity
@Table(name= "Label")
public class Label implements Serializable {

	@Id//indicates it is primary key	
	@GeneratedValue
	@Column(name = "label_id")
	private int label_id;

	@Column(name="label_name")
	private String label_name;
	
	@ManyToOne
	@JoinColumn(name="user_id",nullable=false)
	private UserDetails userId;
	
	public int getLabel_id() {
		return label_id;
	}

	public void setLabel_id(int label_id) {
		this.label_id = label_id;
	}

	public String getLabel_name() {
		return label_name;
	}

	public void setLabel_name(String label_name) {
		this.label_name = label_name;
	}

	public UserDetails getUserId() {
		return userId;
	}

	public void setUserId(UserDetails userId) {
		this.userId = userId;
	}


	
}
