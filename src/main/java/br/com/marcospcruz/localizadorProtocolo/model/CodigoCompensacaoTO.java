package br.com.marcospcruz.localizadorProtocolo.model;

public class CodigoCompensacaoTO {

	private int idCodigoCompensacao;
	private String descricao;

	public void setIdCodigoCompensacao(int idCodigoCompensacao) {
		// TODO Auto-generated method stub

		this.idCodigoCompensacao = idCodigoCompensacao;

	}

	public int getIdCodigoCompensacao() {
		return idCodigoCompensacao;
	}

	public void setDescricao(String descricao) {

		this.descricao = descricao;

	}

	public String getDescricao() {
		return descricao;
	}

	@Override
	public String toString() {
		return "CodigoCompensacaoTO [idCodigoCompensacao="
				+ idCodigoCompensacao + ", descricao=" + descricao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + idCodigoCompensacao;
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
		CodigoCompensacaoTO other = (CodigoCompensacaoTO) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idCodigoCompensacao != other.idCodigoCompensacao)
			return false;
		return true;
	}

}
