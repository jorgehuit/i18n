package com.axa.condiciones.model.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
//@Table(name = "USER_EXA", schema = "SCOTT")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserExa {
	@Id
	/*Se comenta secuencia Oracle*/
	/*
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_EXA")
    @SequenceGenerator(sequenceName = "SEQ_EXA", allocationSize = 1, name = "SEQ_EXA", schema = "SCOTT")
    */
	@GeneratedValue
	private Long id;
	private String ap;
	private String username;
	private String address;
	private String email;

}