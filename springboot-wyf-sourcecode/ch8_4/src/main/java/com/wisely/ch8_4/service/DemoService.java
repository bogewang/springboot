package com.wisely.ch8_4.service;

import com.wisely.ch8_4.domain.Person;

public interface DemoService {
	Person savePersonWithRollBack(Person person);
	Person savePersonWithoutRollBack(Person person);

}
