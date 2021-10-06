package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "team")
public class TeamEntity {
	private String teamName;
	
	@Autointe = true;
	private long teamId;
}
