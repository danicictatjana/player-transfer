package com.ftninformatika.jwd.modul3.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Igrac;
import com.ftninformatika.jwd.modul3.test.service.IgracService;
import com.ftninformatika.jwd.modul3.test.service.KlubService;
import com.ftninformatika.jwd.modul3.test.web.dto.IgracDTO;

@Component
public class IgracDTOToIgrac implements Converter<IgracDTO, Igrac>{

	@Autowired
    private IgracService igracService;

    @Autowired
    private KlubService klubService;

	@Override
	public Igrac convert(IgracDTO dto) {

		Igrac igrac;
		if(dto.getId() == null){
            igrac = new Igrac();
        }else{
        	igrac = igracService.findOne(dto.getId());
        }

		if(igrac != null){
			igrac.setImePrezime(dto.getImePrezime());
			igrac.setPozicija(dto.getPozicija());
			igrac.setBrojDresa(dto.getBrojDresa());
			igrac.setDatumRodjenja(dto.getDatumRodjenja());
			igrac.setNaProdaju(dto.isNaProdaju());
			igrac.setKlub(klubService.findOne(dto.getKlubId()));
        }
        return igrac;
	}

}
