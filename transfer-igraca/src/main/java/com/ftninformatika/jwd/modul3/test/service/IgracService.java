package com.ftninformatika.jwd.modul3.test.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.ftninformatika.jwd.modul3.test.model.Igrac;

public interface IgracService {

	Igrac findOne(Long id);

    Page<Igrac> findAll(Integer pageNo);

    Igrac save(Igrac igrac);

    Igrac update(Igrac igrac);

    Igrac delete(Long id);

    Page<Igrac> find(String pozicija, String klubNaziv, Integer pageNo);

    List<Igrac> findByKlubId(Long klubId);

}
