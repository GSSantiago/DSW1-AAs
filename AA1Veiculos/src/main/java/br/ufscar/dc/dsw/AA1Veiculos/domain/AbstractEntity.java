package br.ufscar.dc.dsw.AA1Veiculos.domain;

import java.io.Serializable;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@SuppressWarnings("serial")
@MappedSuperclass  
public abstract class AbstractEntity<ID extends Serializable> implements Serializable {

	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return (id == null) ? 0 : id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;
		AbstractEntity<?> other = (AbstractEntity<?>) obj;
		return (id != null && id.equals(other.id));
	}

	@Override
	public String toString() {
		return "id=" + id;
	}
}
