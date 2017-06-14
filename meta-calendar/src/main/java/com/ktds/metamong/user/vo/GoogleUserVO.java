package com.ktds.metamong.user.vo;

import org.springframework.social.google.api.plus.Person;

public class GoogleUserVO extends UserVO {

	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

}
