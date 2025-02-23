package br.AtendimentoLugares;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.sun.xml.bind.CycleRecoverable;

@Entity(name = "bairro")
public class Bairro implements Serializable, CycleRecoverable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBairro;

	@ManyToOne
	@JoinColumn(name = "idCidade")
	private Cidade cidade;

	private String descBairro;

	public int getIdBairro() {
		return idBairro;
	}

	public void setIdBairro(int idBairro) {
		this.idBairro = idBairro;
	}

	public Cidade getCidade() {
		if (cidade == null) {
			cidade = new Cidade();
		}
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public String getDescBairro() {
		return descBairro;
	}

	public void setDescBairro(String descBairro) {
		this.descBairro = descBairro;
	}

	public String toString() {
		return descBairro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result
				+ ((descBairro == null) ? 0 : descBairro.hashCode());
		result = prime * result + idBairro;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bairro other = (Bairro) obj;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (descBairro == null) {
			if (other.descBairro != null)
				return false;
		} else if (!descBairro.equals(other.descBairro))
			return false;
		if (idBairro != other.idBairro)
			return false;
		return true;
	}

	@Override
	public Object onCycleDetected(Context arg0) {

		Bairro b = new Bairro();
		b.setIdBairro(this.idBairro);

		return b;
	}

}
