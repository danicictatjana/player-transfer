package com.ftninformatika.jwd.modul3.test.service;

import java.util.List;

import com.ftninformatika.jwd.modul3.test.model.Transfer;

public interface TransferService {

	List<Transfer> findAll();

	Transfer findOne(Long id);

	Transfer save(Transfer transfer);

}
