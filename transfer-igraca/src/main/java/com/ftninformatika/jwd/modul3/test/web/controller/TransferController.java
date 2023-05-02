package com.ftninformatika.jwd.modul3.test.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.model.Transfer;
import com.ftninformatika.jwd.modul3.test.service.TransferService;
import com.ftninformatika.jwd.modul3.test.support.TransferDTOToTransfer;
import com.ftninformatika.jwd.modul3.test.support.TransferToTransferDTO;
import com.ftninformatika.jwd.modul3.test.web.dto.TransferDTO;

@RestController
@RequestMapping(value = "/api/transferi", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class TransferController {

	 @Autowired
	 private TransferService transferService;

     @Autowired
     private TransferToTransferDTO toTransferDTO;

     @Autowired
     private TransferDTOToTransfer toTransfer;

     @PreAuthorize("hasRole('ADMIN')")
	    @GetMapping("/{id}")
	    public ResponseEntity<TransferDTO> getOne(@PathVariable Long id){
	        Transfer transfer = transferService.findOne(id);

	        if(transfer != null) {
	            return new ResponseEntity<>(toTransferDTO.convert(transfer), HttpStatus.OK);
	        }else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

     @PreAuthorize("hasRole('ADMIN')")
	    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<TransferDTO> create(@Valid @RequestBody TransferDTO transferDTO){
	    	Transfer transfer = toTransfer.convert(transferDTO);

	        if(transfer.getCena() > transfer.getKlub().getBudzet()) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Transfer sacuvanTransfer = transferService.save(transfer);

	        return new ResponseEntity<>(toTransferDTO.convert(sacuvanTransfer), HttpStatus.CREATED);
	    }

}
