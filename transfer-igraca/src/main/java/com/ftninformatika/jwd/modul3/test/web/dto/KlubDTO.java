package com.ftninformatika.jwd.modul3.test.web.dto;

public class KlubDTO {

    private Long id;

    private String naziv;

    private double budzet;

    public KlubDTO() {
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
}
