package com.ftninformatika.jwd.modul3.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.ftninformatika.jwd.modul3.test.model.Klub;
import com.ftninformatika.jwd.modul3.test.web.dto.KlubDTO;

@Component
public class KlubToKlubDTO implements Converter<Klub, KlubDTO> {

	@Override
	public KlubDTO convert(Klub klub) {
		KlubDTO dto = new KlubDTO();
        dto.setId(klub.getId());
        dto.setNaziv(klub.getNaziv());
        dto.setBudzet(klub.getBudzet());
        return dto;
	}

	public List<KlubDTO> convert(List<Klub> klubovi){
        List<KlubDTO> kluboviDto = new ArrayList<>();

        for(Klub klub : klubovi) {
        	kluboviDto.add(convert(klub));
        }

        return kluboviDto;
    }

}
