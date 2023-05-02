package com.ftninformatika.jwd.modul3.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.test.model.Transfer;

@Repository
public interface TransferRepository extends JpaRepository<Transfer,Long>{

	@Override
	List<Transfer> findAll();

	Transfer findOneById (Long id);

}
