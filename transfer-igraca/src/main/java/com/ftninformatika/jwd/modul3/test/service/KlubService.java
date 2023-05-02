package com.ftninformatika.jwd.modul3.test.service;

import java.util.List;

import com.ftninformatika.jwd.modul3.test.model.Klub;

public interface KlubService {

	List<Klub> findAll();

	Klub findOne(Long id);
}
