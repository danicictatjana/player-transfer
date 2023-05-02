package com.ftninformatika.jwd.modul3.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.test.model.Klub;

@Repository
public interface KlubRepository extends JpaRepository<Klub,Long>{

	@Override
	List<Klub> findAll();

	Klub findOneById (Long id);

}
