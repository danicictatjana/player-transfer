package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Igrac;
import com.ftninformatika.jwd.modul3.test.web.dto.IgracDTO;

@Component
public class IgracToIgracDTO implements Converter<Igrac, IgracDTO>{

	@Override
	public IgracDTO convert(Igrac igrac) {
		IgracDTO igracDTO = new IgracDTO();
		igracDTO.setId(igrac.getId());
		igracDTO.setImePrezime(igrac.getImePrezime());
		igracDTO.setPozicija(igrac.getPozicija());
		igracDTO.setBrojDresa(igrac.getBrojDresa());
		igracDTO.setDatumRodjenja(igrac.getDatumRodjenja());
		igracDTO.setNaProdaju(igrac.isNaProdaju());
		igracDTO.setKlubId(igrac.getKlub().getId());
		igracDTO.setKlubNaziv(igrac.getKlub().getNaziv());
        return igracDTO;
	}

	public List<IgracDTO> convert(List<Igrac> igraci){
        List<IgracDTO> igraciDTO = new ArrayList<>();

        for(Igrac igrac : igraci) {
        	igraciDTO.add(convert(igrac));
        }

        return igraciDTO;
    }

}
