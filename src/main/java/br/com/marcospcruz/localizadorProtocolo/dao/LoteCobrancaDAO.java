package br.com.marcospcruz.localizadorProtocolo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OraclePreparedStatement;
import br.com.marcospcruz.localizadorProtocolo.model.LoteCobrancaTO;

public class LoteCobrancaDAO extends Database {

	public LoteCobrancaDAO() {
		// TODO Auto-generated constructor stub

		super();
	}

	@Override
	List<LoteCobrancaTO> populaLista(ResultSet rs) throws SQLException {
		// TODO Auto-generated method stub
		List<LoteCobrancaTO> lista = null;
		while (rs.next()) {

			if (lista == null)
				lista = new ArrayList<LoteCobrancaTO>();

			LoteCobrancaTO lote = new LoteCobrancaTO();

			lote.setIdLoteCobranca(rs.getInt(1));

			lista.add(lote);

		}
		return lista;
	}

	public List<LoteCobrancaTO> read(List idCodigoCompensacaoList,
			List locaisServico, Date from, Date to, int idClasseContrato)
			throws SQLException {

		List<LoteCobrancaTO> protocolos = null;

		// Connection con = null;
		Connection con = null;

		PreparedStatement pstmt = null;
		// OraclePreparedStatement pstmt = null;

		ResultSet rs = null;

		try {

			String sql = queriesProperties.getProperty("query.7050");

			con = getConnection();

			pstmt = (OraclePreparedStatement) con.prepareStatement(sql);

			// int truncatedFrom = dateToInt(from);
			// System.out.println("parametro 1 " + truncatedFrom);
			// pstmt.setInt(1, truncatedFrom);

			// int truncatedTo = dateToInt(to);
			// System.out.println("parametro 2 " + truncatedTo);
			// pstmt.setInt(2, truncatedTo);

			// Array locais = con.createArrayOf("NUMBER",
			// locaisServico.toArray());

			// pstmt.setArray(3, locais);

			Object locaisServicosString = gambis(locaisServico);
			// System.out.println("parametro 3 " + locaisServicosString);

			pstmt.setString(3, locaisServicosString.toString());

			// pstmt.setArray(
			// 4,
			// con.createArrayOf("number",
			// idCodigoCompensacaoList.toArray()));

			Object codigoCompensacaoString = gambis(idCodigoCompensacaoList);
			// System.out.println("parametro 4 " + codigoCompensacaoString);
			pstmt.setString(4, codigoCompensacaoString.toString());

			// System.out.println("parametro 5 " + idClasseContrato);
			pstmt.setInt(5, idClasseContrato);

			rs = pstmt.executeQuery();

			protocolos = populaLista(rs);

		} catch (SQLException e) {

			e.printStackTrace();

		} finally {

			close(con, pstmt, rs);

		}

		return protocolos;

	}

	private Object gambis(List objetos) {
		// TODO Auto-generated method stub

		StringBuffer parametro = new StringBuffer();

		for (int i = 0; i < objetos.size(); i++) {

			Object valor = objetos.get(i);

			parametro.append(valor.toString());

			if (i < (objetos.size() - 1))
				parametro.append(",");

		}

		// parametro = parametro.substring(0, parametro.length() - 1);

		return (Object) parametro.toString().trim();

	}

	@Override
	public List read() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<LoteCobrancaTO> read(Object[] parametros) throws SQLException {
		// TODO Auto-generated method stub

		List<LoteCobrancaTO> lotes = null;

		String sql = queriesProperties.getProperty("query.7050");

		sql = fillSqlParamethers(sql, parametros);

		Connection con = null;

		Statement pstmt = null;

		ResultSet rs = null;

		try {
			con = getConnection();

			pstmt = con.createStatement();

			rs = pstmt.executeQuery(sql);

			lotes = populaLista(rs);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {

			close(con, pstmt, rs);

		}

		return lotes;
	}

	@SuppressWarnings("rawtypes")
	private String fillSqlParamethers(String sql, Object[] parametros) {
		// TODO Auto-generated method stub

		StringBuffer newSql = new StringBuffer();

		int contador = 0;

		for (int i = 0; i < sql.length(); i += 2) {

			StringBuffer trecho = new StringBuffer(sql.substring(i, i + 2));

			if (trecho.toString().contains("(?")
					|| trecho.toString().contains("?)")) {

				trecho.replace(0, 1, gambis((List) parametros[contador])
						.toString());

				contador++;

			} else if (trecho.toString().contains("?")) {

				trecho.replace(0, 1, parametros[contador].toString());

				contador++;

				//
			}

			newSql.append(trecho.toString().replace('?', ' '));

		}

		return newSql.toString();
	}
}
