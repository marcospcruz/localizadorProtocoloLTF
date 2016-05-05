package br.com.marcospcruz.localizadorProtocolo.model;

public class ClasseContratoTO {

	private Integer idClasseContrato;

	private String descricao;

	public Integer getIdClasseContrato() {
		return idClasseContrato;
	}

	public void setIdClasseContrato(int idClasseContrato) {
		this.idClasseContrato = idClasseContrato;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "ClasseContratoTO [idClasseContrato=" + idClasseContrato
				+ ", descricao=" + descricao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + idClasseContrato;
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
		ClasseContratoTO other = (ClasseContratoTO) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idClasseContrato != other.idClasseContrato)
			return false;
		return true;
	}

}
