package com.ftninformatika.jwd.modul3.test.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Igrac {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false)
	private String imePrezime;

	@Column
	private String pozicija;

	@Column
	private int brojDresa;

	@Column(nullable = false)
	private LocalDate datumRodjenja;

	@Column
	private boolean naProdaju;

	@ManyToOne
	private Klub klub;

	@OneToMany(mappedBy = "igrac", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Transfer> transferi = new ArrayList<>();

	public Igrac() {
        super();
    }

	public Igrac(Long id, String imePrezime, String pozicija, int brojDresa, LocalDate datumRodjenja, boolean naProdaju,
			Klub klub) {
		super();
		this.id = id;
		this.imePrezime = imePrezime;
		this.pozicija = pozicija;
		this.brojDresa = brojDresa;
		this.datumRodjenja = datumRodjenja;
		this.naProdaju = naProdaju;
		this.klub = klub;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getImePrezime() {
		return imePrezime;
	}

	public void setImePrezime(String imePrezime) {
		this.imePrezime = imePrezime;
	}

	public String getPozicija() {
		return pozicija;
	}

	public void setPozicija(String pozicija) {
		this.pozicija = pozicija;
	}

	public int getBrojDresa() {
		return brojDresa;
	}

	public void setBrojDresa(int brojDresa) {
		this.brojDresa = brojDresa;
	}

	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public boolean isNaProdaju() {
		return naProdaju;
	}

	public void setNaProdaju(boolean naProdaju) {
		this.naProdaju = naProdaju;
	}

	public Klub getKlub() {
		return klub;
	}

	public void setKlub(Klub klub) {
		this.klub = klub;
	}

	public List<Transfer> getTransferi() {
		return transferi;
	}

	public void setTransferi(List<Transfer> transferi) {
		this.transferi = transferi;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Igrac other = (Igrac) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Igrac [id=" + id + ", imePrezime=" + imePrezime + ", pozicija=" + pozicija + ", brojDresa=" + brojDresa
				+ ", datumRodjenja=" + datumRodjenja + ", naProdaju=" + naProdaju + ", klub=" + klub + "]";
	}

}
