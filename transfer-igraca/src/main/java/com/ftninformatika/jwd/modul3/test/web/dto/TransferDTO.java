package com.ftninformatika.jwd.modul3.test.web.dto;

import javax.validation.constraints.Positive;

public class TransferDTO {

	private Long id;

	private Long igracId;

	private Long klubId;

	@Positive(message = "Cena nije pozitivan broj.")
    private double cena;

    public TransferDTO() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIgracId() {
		return igracId;
	}

	public void setIgracId(Long igracId) {
		this.igracId = igracId;
	}

	public Long getKlubId() {
		return klubId;
	}

	public void setKlubId(Long klubId) {
		this.klubId = klubId;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
	}
}
