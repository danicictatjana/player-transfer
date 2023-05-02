package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Transfer;
import com.ftninformatika.jwd.modul3.test.web.dto.TransferDTO;

@Component
public class TransferToTransferDTO implements Converter<Transfer, TransferDTO>{

	@Override
	public TransferDTO convert(Transfer transfer) {
		TransferDTO transferDTO = new TransferDTO();
		transferDTO.setId(transfer.getId());
		transferDTO.setIgracId(transfer.getIgrac().getId());
		transferDTO.setKlubId(transfer.getKlub().getId());
		transferDTO.setCena(transfer.getCena());
        return transferDTO;
	}

	public List<TransferDTO> convert(List<Transfer> transferi){
        List<TransferDTO> transferiDTO = new ArrayList<>();

        for(Transfer transfer : transferi) {
        	transferiDTO.add(convert(transfer));
        }

        return transferiDTO;
    }

}
