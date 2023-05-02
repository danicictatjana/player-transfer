package com.ftninformatika.jwd.modul3.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Klub;
import com.ftninformatika.jwd.modul3.test.repository.KlubRepository;
import com.ftninformatika.jwd.modul3.test.service.KlubService;

@Service
public class JpaKlubService implements KlubService{

	@Autowired
    private KlubRepository klubRepository;

	@Override
	public List<Klub> findAll() {
		return klubRepository.findAll();
	}

	@Override
	public Klub findOne(Long id) {
		return klubRepository.findOneById(id);
	}

}
