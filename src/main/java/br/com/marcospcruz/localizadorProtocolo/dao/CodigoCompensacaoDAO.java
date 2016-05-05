package br.com.marcospcruz.localizadorProtocolo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.marcospcruz.localizadorProtocolo.model.CodigoCompensacaoTO;

public class CodigoCompensacaoDAO extends Database {

	public CodigoCompensacaoDAO() {

		super();

	}

	public List<CodigoCompensacaoTO> read() throws SQLException {

		Connection con = null;

		Statement stmt = null;

		ResultSet rs = null;

		List<CodigoCompensacaoTO> codigosCompensacao = null;

		try {

			con = getConnection();

			stmt = con.createStatement();

			rs = stmt.executeQuery(queriesProperties
					.getProperty("query.codigo_compensacao"));

			codigosCompensacao = populaLista(rs);

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			close(con, stmt, rs);

		}

		return codigosCompensacao;

	}

	@Override
	public List populaLista(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub

		List<CodigoCompensacaoTO> lista = null;

		while (rs.next()) {

			if (lista == null)
				lista = new ArrayList<CodigoCompensacaoTO>();

			CodigoCompensacaoTO codigo = new CodigoCompensacaoTO();

			codigo.setIdCodigoCompensacao(rs.getInt(1));

			codigo.setDescricao(rs.getString(2));

			lista.add(codigo);

		}

		return lista;
	}
}
