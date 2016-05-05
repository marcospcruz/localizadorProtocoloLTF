package br.com.marcospcruz.localizadorProtocolo.model;

public class LoteCobrancaTO {

	private int idLoteCobranca;

	public int getIdLoteCobranca() {
		return idLoteCobranca;
	}

	public void setIdLoteCobranca(int idLoteCobranca) {
		this.idLoteCobranca = idLoteCobranca;
	}

	@Override
	public String toString() {
		return "LoteCobrancaTO [idLoteCobranca=" + idLoteCobranca + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idLoteCobranca;
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
		LoteCobrancaTO other = (LoteCobrancaTO) obj;
		if (idLoteCobranca != other.idLoteCobranca)
			return false;
		return true;
	}

}
