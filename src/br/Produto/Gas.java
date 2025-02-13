package br.Produto;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import br.Empresa.Categoria.CategoriaENUM;

@Entity(name = "gas")
@PrimaryKeyJoinColumn(name = "idProduto")
public class Gas extends Produto {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int estoque;

	public Gas() {
		setQualificacao(CategoriaENUM.Gas);
		setAtivo(true);
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + estoque;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gas other = (Gas) obj;
		if (estoque != other.estoque)
			return false;
		return true;
	}

	@Override
	public Object onCycleDetected(Context arg0) {
		Gas gas = new Gas();
		gas.setIdProduto(getIdProduto());
		return gas;
	}
}
