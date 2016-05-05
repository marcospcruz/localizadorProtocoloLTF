package br.com.marcospcruz.localizadorProtocolo.buscador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import br.com.marcospcruz.localizadorProtocolo.model.LoteCobrancaTO;

public class FinancialProtocolSeeker {

	public String buscarCopiarArquivos(List<LoteCobrancaTO> lotes,
			String extensao, File pastaOrigem, File pastaDestino) {
		// TODO Auto-generated method stub

		int contador = 0;

		if (lotes != null)

			for (LoteCobrancaTO lote : lotes) {

				String nomeLote = lote.getIdLoteCobranca() + extensao;
				if (buscaLote(pastaDestino, nomeLote) == null) {
					File arquivoLote = buscaLote(pastaOrigem, nomeLote);

					if (arquivoLote != null)
						// if (buscaLote(pastaDestino, arquivoLote.getName()) ==
						// null)
						try {
							contador += copiaArquivo(arquivoLote, pastaDestino);

						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

				}
			}

		if (contador > 1)
			return "Foram copiados " + contador + " de " + lotes.size()
					+ " protocolos financeiros.";
		else
			return "Não foram encontratos protocolos financeiros para os critérios selecionados.";

	}

	private int copiaArquivo(File arquivoLote, File pastaDestino)
			throws IOException {
		// TODO Auto-generated method stub

		String fileName = arquivoLote.getName();

		InputStream in = null;

		OutputStream out = null;

		int retorno = 0;

		try {

			File destino = new File(pastaDestino.getCanonicalPath() + "\\"
					+ fileName);

			in = new FileInputStream(arquivoLote);

			out = new FileOutputStream(destino);

			byte[] buf = new byte[1024];

			int len;

			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}

			retorno = 1;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} finally {

			in.close();

			out.close();

		}

		return retorno;

	}

	/**
	 * 
	 * @param pastaOrigem
	 * @param nomeLote
	 * @return
	 */
	private File buscaLote(File pastaOrigem, String nomeLote) {
		// TODO Auto-generated method stub

		for (File file : pastaOrigem.listFiles()) {

			if (file.isDirectory()) {

				File protocolo = buscaLote(file, nomeLote);

				if (protocolo != null && protocolo.isFile())
					return protocolo;

			} else {

				String fileName = file.getName();

				if (fileName.contains(nomeLote)) {

					return file;

				}

			}

		}

		return null;
	}

}
