package br.com.marcospcruz.localizadorProtocolo.controller;

import java.sql.SQLException;
import java.util.List;

import br.com.marcospcruz.localizadorProtocolo.dao.CodigoCompensacaoDAO;
import br.com.marcospcruz.localizadorProtocolo.model.CodigoCompensacaoTO;

public class CodigoCompensacaoController {

	private CodigoCompensacaoDAO dao;

	public CodigoCompensacaoController() {

		dao = new CodigoCompensacaoDAO();

	}

	public List<CodigoCompensacaoTO> getCodigosCompensacao() throws SQLException {
		// TODO Auto-generated method stub
		return dao.read();
	}

}
