package com.ftninformatika.jwd.modul3.test.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.model.Klub;
import com.ftninformatika.jwd.modul3.test.service.KlubService;
import com.ftninformatika.jwd.modul3.test.support.KlubToKlubDTO;
import com.ftninformatika.jwd.modul3.test.web.dto.KlubDTO;

@RestController
@RequestMapping(value = "/api/klubovi", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class KlubController {

	 @Autowired
	 private KlubService klubService;

	 @Autowired
	 private KlubToKlubDTO toKlubDTO;

	 @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	 @GetMapping
	 public ResponseEntity<List<KlubDTO>> getAll(){

		List<Klub> klubovi = klubService.findAll();

		return new ResponseEntity<>(toKlubDTO.convert(klubovi), HttpStatus.OK);
	}
}
