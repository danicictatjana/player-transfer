package com.ftninformatika.jwd.modul3.test.model;

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
import javax.persistence.OneToMany;

@Entity
public class Klub {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(nullable = false, unique = true)
	private String naziv;

	@Column(nullable = false)
	private double budzet;

	@OneToMany(mappedBy = "klub", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Igrac> igraci = new ArrayList<>();

	@OneToMany(mappedBy = "klub2", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Transfer> transferi = new ArrayList<>();

	public Klub() {
		super();
	}

	public Klub(Long id, String naziv, int budzet) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.budzet = budzet;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public double getBudzet() {
		return budzet;
	}

	public void setBudzet(double budzet) {
		this.budzet = budzet;
	}

	public List<Igrac> getIgraci() {
		return igraci;
	}

	public void setIgraci(List<Igrac> igraci) {
		this.igraci = igraci;
	}

	public List<Transfer> getTransferi() {
		return transferi;
	}

	public void setTransferi(List<Transfer> transferi) {
		this.transferi = transferi;
	}

	@Override
	public String toString() {
		return "Klub [id=" + id + ", naziv=" + naziv + ", budzet=" + budzet + "]";
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
		Klub other = (Klub) obj;
		return Objects.equals(id, other.id);
	}

}
