package com.restful.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private int year;
	@Column(nullable = false)
	private String title;
	@Column(nullable = false)
	private String studio;
	@Column(nullable = false)
	private String producer;
	@Column(nullable = true)
	private String winner;
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStudio() {
		return studio;
	}
	public void setStudio(String studio) {
		this.studio = studio;
	}
	public String getProducer() {
		return producer;
	}
	public void setProducer(String producer) {
		this.producer = producer;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}


	
	
}

