package com.ftninformatika.jwd.modul3.test.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftninformatika.jwd.modul3.test.model.Igrac;

@Repository
public interface IgracRepository extends JpaRepository<Igrac,Long>{

	Igrac findOneById(Long id);

	List<Igrac> findByKlubId(Long klubId);

	Page<Igrac> findByPozicijaLikeAndKlubNazivLike(String pozicija, String klubNaziv, Pageable pageable);

	Page<Igrac> findByPozicija(String pozicija, Pageable pageable);

}
