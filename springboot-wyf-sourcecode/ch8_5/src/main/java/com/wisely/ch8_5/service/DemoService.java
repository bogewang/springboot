package com.wisely.ch8_5.service;

import com.wisely.ch8_5.domain.Person;

public interface DemoService {
	Person save(Person person);
	
	void remove(Long id);
	
	Person findOne(Person person);

}
