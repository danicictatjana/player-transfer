package com.ftninformatika.jwd.modul3.test.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Transfer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne
    private Igrac igrac;

	@JoinColumn(name="klub_id")
	@ManyToOne
	private Klub klub2;

	@Column(nullable = false)
	private double cena;

	public Transfer() {
		super();
	}

	public Transfer(Long id, Igrac igrac, Klub klub, double cena) {
		super();
		this.id = id;
		this.igrac = igrac;
		this.klub2 = klub;
		this.cena = cena;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Igrac getIgrac() {
		return igrac;
	}

	public void setIgrac(Igrac igrac) {
		this.igrac = igrac;
	}

	public Klub getKlub() {
		return klub2;
	}

	public void setKlub(Klub klub) {
		this.klub2 = klub;
	}

	public double getCena() {
		return cena;
	}

	public void setCena(double cena) {
		this.cena = cena;
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
		Transfer other = (Transfer) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Transfer [id=" + id + ", igrac=" + igrac + ", klub=" + klub2 + ", cena=" + cena + "]";
	}
}
