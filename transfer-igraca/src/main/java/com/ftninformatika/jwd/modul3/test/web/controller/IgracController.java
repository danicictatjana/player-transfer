package com.ftninformatika.jwd.modul3.test.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ftninformatika.jwd.modul3.test.model.Igrac;
import com.ftninformatika.jwd.modul3.test.service.IgracService;
import com.ftninformatika.jwd.modul3.test.support.IgracDTOToIgrac;
import com.ftninformatika.jwd.modul3.test.support.IgracToIgracDTO;
import com.ftninformatika.jwd.modul3.test.web.dto.IgracDTO;

@RestController
@RequestMapping(value = "/api/igraci", produces = MediaType.APPLICATION_JSON_VALUE)
public class IgracController {

	 	@Autowired
	    private IgracService igracService;

	    @Autowired
	    private IgracDTOToIgrac toIgrac;

	    @Autowired
	    private IgracToIgracDTO toIgracDTO;

	    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	    @GetMapping
	    public ResponseEntity<List<IgracDTO>> getAll(
	    		@RequestParam(required=false, defaultValue="") String pozicija,
	    		@RequestParam(required=false) String klubNaziv,
	            @RequestParam(value = "pageNo", defaultValue = "0") int pageNo){

	        Page<Igrac> page;
	        if(pozicija != null || klubNaziv != null) {
	        	page = igracService.find(pozicija, klubNaziv, pageNo);
	        }else {
	        	page = igracService.findAll(pageNo);
	        }

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

	        return new ResponseEntity<>(toIgracDTO.convert(page.getContent()), headers, HttpStatus.OK);
	    }

	    @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
	    @GetMapping("/{id}")
	    public ResponseEntity<IgracDTO> getOne(@PathVariable Long id){
	        Igrac igrac = igracService.findOne(id);

	        if(igrac != null) {
	            return new ResponseEntity<>(toIgracDTO.convert(igrac), HttpStatus.OK);
	        }else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

	    @PreAuthorize("hasRole('ADMIN')")
	    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<IgracDTO> create(@Valid @RequestBody IgracDTO igracDTO){
	    	Igrac igrac = toIgrac.convert(igracDTO);

	        if(igrac.getKlub() == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Igrac sacuvanIgrac = igracService.save(igrac);

	        return new ResponseEntity<>(toIgracDTO.convert(sacuvanIgrac), HttpStatus.CREATED);
	    }

	    @PreAuthorize("hasRole('ADMIN')")
	    @PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	    public ResponseEntity<IgracDTO> update(@PathVariable Long id, @Valid @RequestBody IgracDTO igracDTO){

	        if(!id.equals(igracDTO.getId())) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Igrac igrac = toIgrac.convert(igracDTO);

	        if(igrac.getKlub() == null) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        Igrac sacuvanIgrac = igracService.update(igrac);

	        return new ResponseEntity<>(toIgracDTO.convert(sacuvanIgrac),HttpStatus.OK);
	    }

	    @PreAuthorize("hasRole('ADMIN')")
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> delete(@PathVariable Long id){

	    	Igrac obrisanIgrac = igracService.delete(id);

	        if(obrisanIgrac != null) {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }

}
