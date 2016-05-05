package br.com.marcospcruz.localizadorProtocolo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.marcospcruz.localizadorProtocolo.model.ClasseContratoTO;

public class ClasseContratoDAO extends Database {

	public ClasseContratoDAO() {

		super();

	}

	@Override
	List populaLista(ResultSet rs) throws SQLException {

		List<ClasseContratoTO> lista = null;

		while (rs.next()) {

			if (lista == null)
				lista = new ArrayList<ClasseContratoTO>();

			ClasseContratoTO classe = new ClasseContratoTO();

			classe.setIdClasseContrato(rs.getInt(1));

			classe.setDescricao(rs.getString(2));

			lista.add(classe);

		}

		return lista;
	}

	@Override
	public List<ClasseContratoTO> read() throws SQLException {
		// TODO Auto-generated method stub

		List<ClasseContratoTO> lista = null;

		Connection con = null;

		Statement st = null;

		ResultSet rs = null;

		try {

			con = getConnection();

			st = con.createStatement();

			rs = st.executeQuery(queriesProperties
					.getProperty("query.classeContrato"));

			lista = populaLista(rs);

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			close(con, st, rs);

		}

		return lista;
	}
}
