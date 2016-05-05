package br.com.marcospcruz.localizadorProtocolo.controller;

import java.sql.SQLException;
import java.util.List;

import br.com.marcospcruz.localizadorProtocolo.dao.ClasseContratoDAO;
import br.com.marcospcruz.localizadorProtocolo.model.ClasseContratoTO;

public class ClasseContratoController {

	private ClasseContratoDAO dao;

	public ClasseContratoController() {

		dao = new ClasseContratoDAO();

	}

	public List<ClasseContratoTO> carregaClasseContrato() {

		List<ClasseContratoTO> lista = null;
		try {
			lista = dao.read();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return lista;
	}

}
