package com.ftninformatika.jwd.modul3.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Igrac;
import com.ftninformatika.jwd.modul3.test.model.Transfer;
import com.ftninformatika.jwd.modul3.test.repository.IgracRepository;
import com.ftninformatika.jwd.modul3.test.repository.TransferRepository;
import com.ftninformatika.jwd.modul3.test.service.TransferService;

@Service
public class JpaTransferService implements TransferService {

	@Autowired
    private TransferRepository transferRepository;

	@Autowired
    private IgracRepository igracRepository;

	@Override
	public List<Transfer> findAll() {
		return transferRepository.findAll();
	}

	@Override
	public Transfer findOne(Long id) {
		return transferRepository.findOneById(id);
	}

	@Override
	public Transfer save(Transfer transfer) {
		Transfer sacuvanTransfer = transferRepository.save(transfer);
		Igrac igrac = sacuvanTransfer.getIgrac();
		igrac.setKlub(sacuvanTransfer.getKlub());
		igracRepository.save(igrac);
		return sacuvanTransfer;
	}

}
