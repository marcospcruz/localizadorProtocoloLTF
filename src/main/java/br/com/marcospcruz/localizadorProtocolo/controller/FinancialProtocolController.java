package br.com.marcospcruz.localizadorProtocolo.controller;

import java.io.File;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.marcospcruz.localizadorProtocolo.buscador.FinancialProtocolSeeker;
import br.com.marcospcruz.localizadorProtocolo.dao.LoteCobrancaDAO;
import br.com.marcospcruz.localizadorProtocolo.model.LoteCobrancaTO;

public class FinancialProtocolController {

	private LoteCobrancaDAO dao;

	public FinancialProtocolController() {

		dao = new LoteCobrancaDAO();

	}

	private Integer dateToInt(Date from) {
		// TODO Auto-generated method stub

		Calendar cal = new GregorianCalendar();
		cal.setTime(from);
		String retorno = Integer.toString(cal.get(Calendar.YEAR));
		int mes = cal.get(Calendar.MONTH);
		retorno += mes < 9 ? "0" + Integer.toString(mes + 1) : mes + 1;
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		retorno += dia < 9 ? "0" + Integer.toString(dia) : dia;

		return new Integer(retorno);
	}

	public String procurarLotesTransacoesCompensadas(
			List idCodigoCompensacaoList, Date from, Date to,
			List locaisServico, int idClasseContrato, File pastaOrigem,
			File pastaDestino) {

		from = truncaData(from);
		to = truncaData(to);
		String message = null;
		try {

			List<LoteCobrancaTO> lotes = dao.read(new Object[] {
					dateToInt(new java.sql.Date(from.getTime())),
					dateToInt(new java.sql.Date(to.getTime())), locaisServico,
					idCodigoCompensacaoList, idClasseContrato });

			FinancialProtocolSeeker fps = new FinancialProtocolSeeker();

			message = fps.buscarCopiarArquivos(lotes, ".LTF", pastaOrigem,
					pastaDestino);

		} catch (SQLException e) {

			e.printStackTrace();

		}

		return message;

	}

	private Date truncaData(Date date) {
		// TODO Auto-generated method stub

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		return cal.getTime();
	}

}
