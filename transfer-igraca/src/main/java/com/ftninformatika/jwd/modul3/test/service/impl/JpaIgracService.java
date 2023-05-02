package com.ftninformatika.jwd.modul3.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ftninformatika.jwd.modul3.test.model.Igrac;
import com.ftninformatika.jwd.modul3.test.repository.IgracRepository;
import com.ftninformatika.jwd.modul3.test.service.IgracService;

@Service
@Transactional
public class JpaIgracService implements IgracService {

	@Autowired
    private IgracRepository igracRepository;

	@Override
	public Igrac findOne(Long id) {
		return igracRepository.findOneById(id);
	}

	@Override
	public Page<Igrac> findAll(Integer pageNo) {
		return igracRepository.findAll(PageRequest.of(pageNo,3));
	}

	@Override
	public Igrac update(Igrac igrac) {
		 return igracRepository.save(igrac);
	}

	@Override
	public Igrac delete(Long id) {
		Igrac igrac = igracRepository.findOneById(id);
		if(igrac != null) {
			igrac.getKlub().getIgraci().remove(igrac);
			igrac.setKlub(null);
			igrac = igracRepository.save(igrac);
			igracRepository.delete(igrac);
			return igrac;
		}
		return null;
	}

	@Override
	public List<Igrac> findByKlubId(Long klubId) {
		return igracRepository.findByKlubId(klubId);
	}

	@Override
	public Igrac save(Igrac igrac) {
		return igracRepository.save(igrac);
	}

	@Override
	public Page<Igrac> find(String pozicija, String klubNaziv, Integer pageNo) {

		if(pozicija == null) {
			pozicija = "%%";
		}else {
			pozicija = "%" + pozicija + "%";
		}
		if(klubNaziv == null) {
			klubNaziv = "%%";
		}else {
			klubNaziv = "%" + klubNaziv + "%";
		}

		return igracRepository.findByPozicijaLikeAndKlubNazivLike(pozicija, klubNaziv, PageRequest.of(pageNo,3));

    }

}
