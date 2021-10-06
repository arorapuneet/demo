package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "developer")
public class DeveloperEntity {
	private String name;
	private long id;
	private long teamId;
	private String phoneNumber;
}
