package com.ftninformatika.jwd.modul3.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Transfer;
import com.ftninformatika.jwd.modul3.test.service.IgracService;
import com.ftninformatika.jwd.modul3.test.service.KlubService;
import com.ftninformatika.jwd.modul3.test.service.TransferService;
import com.ftninformatika.jwd.modul3.test.web.dto.TransferDTO;

@Component
public class TransferDTOToTransfer implements Converter<TransferDTO, Transfer>{

	@Autowired
    private TransferService transferService;

	@Autowired
    private IgracService igracService;

	@Autowired
    private KlubService klubService;


	@Override
	public Transfer convert(TransferDTO dto) {

		Transfer transfer;
		if(dto.getId() == null){
			transfer = new Transfer();
        }else{
        	transfer = transferService.findOne(dto.getId());
        }

		if(transfer != null){
			transfer.setIgrac(igracService.findOne(dto.getIgracId()));
			transfer.setKlub(klubService.findOne(dto.getKlubId()));
			transfer.setCena(dto.getCena());
        }
        return transfer;
	}

}
