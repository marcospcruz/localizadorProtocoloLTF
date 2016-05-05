package br.com.marcospcruz.localizadorProtocolo.view;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import br.com.marcospcruz.localizadorProtocolo.controller.ClasseContratoController;
import br.com.marcospcruz.localizadorProtocolo.controller.CodigoCompensacaoController;
import br.com.marcospcruz.localizadorProtocolo.controller.FinancialProtocolController;
import br.com.marcospcruz.localizadorProtocolo.model.ClasseContratoTO;
import br.com.marcospcruz.localizadorProtocolo.model.CodigoCompensacaoTO;

public class MainJFrame extends JFrame implements ActionListener {

	private File pastaOrigem;
	private JButton btnDestino;
	private JButton btnOrigem;
	private File pastaDestino;
	private JButton btnListaArquivo;
	private HashMap<String, Integer> codigosCompensacaoMap;
	private List<JCheckBox> checkBoxes;
	private JDatePickerImpl fromDatePicker;
	private JDatePickerImpl toDatePicker;
	private JCheckBox localServicoTP1;
	private JCheckBox localServicoTP2;
	private ButtonGroup classeContratoOptionGroup;
	private HashMap classeContratoMap;

	public MainJFrame() {

		super("Localizador Protocolo Financeiro");

		setLayout(new FlowLayout());

		setSize(800, 600);

		configuraJanela();

		setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	private void configuraJanela() {
		// TODO Auto-generated method stub

		add(configuraJPanelClasseContrato());

		add(configuraJPanelLocaisServico());

		add(configuraJPanelPeriodo());

		add(configuraJPanelTipoCompensacao());

		add(configuraJPanelBtns());

	}

	private Component configuraJPanelClasseContrato() {
		// TODO Auto-generated method stub

		JPanel panel = createJPanel("Classe Contrato");

		ClasseContratoController ctrl = new ClasseContratoController();

		List<ClasseContratoTO> lista = ctrl.carregaClasseContrato();

		classeContratoMap = new HashMap();

		classeContratoOptionGroup = new ButtonGroup();

		for (ClasseContratoTO classe : lista) {

			JRadioButton chk = new JRadioButton(classe.getIdClasseContrato()
					+ "-" + classe.getDescricao());

			chk.setActionCommand(classe.getIdClasseContrato().toString());

			classeContratoOptionGroup.add(chk);

			classeContratoMap.put(classe.getDescricao(),
					classe.getIdClasseContrato());

			panel.add(chk);

		}

		return panel;
	}

	private Component configuraJPanelLocaisServico() {
		// TODO Auto-generated method stub

		JPanel panel = createJPanel("Locais Serviços");

		// panel.setPreferredSize(new Dimension(200, 200));

		localServicoTP1 = new JCheckBox();

		localServicoTP1.setText("TP1");

		localServicoTP2 = new JCheckBox();

		localServicoTP2.setText("TP2");

		panel.add(localServicoTP1);

		panel.add(localServicoTP2);

		return panel;
	}

	private JPanel createJPanel(String title) {
		// TODO Auto-generated method stub

		JPanel jPanel = new JPanel();

		jPanel.setBorder(new TitledBorder(title));
		return jPanel;
	}

	private Component configuraJPanelPeriodo() {

		JPanel panel = createJPanel("Period");

		JPanel panelFrom = createJPanel("From:");

		JPanel panelTo = createJPanel("To:");

		fromDatePicker = createDatePicker("");

		toDatePicker = createDatePicker("To:");

		panelFrom.add(fromDatePicker);

		panelTo.add(toDatePicker);

		panel.add(panelFrom);

		panel.add(panelTo);

		return panel;

	}

	private JDatePickerImpl createDatePicker(String title) {

		UtilDateModel model = new UtilDateModel();

		JDatePanelImpl datePanel = new JDatePanelImpl(model);

		datePanel.setBorder(new TitledBorder(title));

		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

		return datePicker;
	}

	private Component configuraJPanelTipoCompensacao() {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();

		// panel.setPreferredSize(new Dimension(300, 200));

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		// panel.setBorder(new TitledBorder("Compensation Status"));

		CodigoCompensacaoController codigoCompensacao = new CodigoCompensacaoController();

		try {

			List<CodigoCompensacaoTO> codigosCompensacao = codigoCompensacao
					.getCodigosCompensacao();

			codigosCompensacaoMap = new HashMap<String, Integer>();
			checkBoxes = new ArrayList<JCheckBox>();

			for (CodigoCompensacaoTO codigo : codigosCompensacao) {

				JCheckBox checkBox = new JCheckBox(
						codigo.getIdCodigoCompensacao() + "-"
								+ codigo.getDescricao());

				codigosCompensacaoMap.put(codigo.getDescricao(),
						codigo.getIdCodigoCompensacao());

				panel.add(checkBox);
				checkBoxes.add(checkBox);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JScrollPane jsPane = new JScrollPane(panel);

		jsPane.setPreferredSize(new Dimension(300, 300));

		jsPane.setBorder(new TitledBorder("Compensation Status"));

		return jsPane;
	}

	private Component configuraJPanelBtns() {
		// TODO Auto-generated method stub
		JPanel jPanel = new JPanel();

		btnOrigem = createJButton("Abrir Pasta Origem..");

		btnDestino = createJButton("Abrir Pasta Destino...");

		btnListaArquivo = createJButton("Procurar arquivos");

		jPanel.add(btnOrigem);

		jPanel.add(btnDestino);

		jPanel.add(btnListaArquivo);

		// File pastaOrigem=

		return jPanel;
	}

	private JButton createJButton(String string) {
		// TODO Auto-generated method stub

		JButton btn = new JButton(string);

		btn.addActionListener(this);

		return btn;
	}

	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

		JFileChooser fileChooser = null;

		if (e.getActionCommand().equals(btnOrigem.getText())) {

			fileChooser = createFileChooser();

			pastaOrigem = fileChooser.getSelectedFile();

		} else if (e.getActionCommand().equals(btnDestino.getText())) {

			fileChooser = createFileChooser();

			pastaDestino = fileChooser.getSelectedFile();

		} else if (e.getActionCommand().equals(btnListaArquivo.getText())) {

			List idCodigosCompensacao = getSelectedCompensation();

			List locaisServico = getLocaisServico();

			Date from = (Date) fromDatePicker.getModel().getValue();

			Date to = (Date) toDatePicker.getModel().getValue();

			FinancialProtocolController fpc = new FinancialProtocolController();

			int idClasseContrato = getIdClasseContrato();

			if (validaParametrosPreenchidos(idCodigosCompensacao, from, to,
					locaisServico, idClasseContrato, pastaOrigem, pastaDestino)) {

				String message = fpc.procurarLotesTransacoesCompensadas(
						idCodigosCompensacao, from, to, locaisServico,
						idClasseContrato, pastaOrigem, pastaDestino);
				JOptionPane.showMessageDialog(this, message);
			}

		}

	}

	private boolean validaParametrosPreenchidos(List idCodigosCompensacao,
			Date from, Date to, List locaisServico, int idClasseContrato,
			File pastaOrigem2, File pastaDestino2) {
		// TODO Auto-generated method stub

		if (idClasseContrato == 0) {

			JOptionPane.showMessageDialog(this,
					"Necessário selecionar a Classe de Contrato!");

			return false;

		}

		if (locaisServico != null && locaisServico.size() < 1) {

			JOptionPane.showMessageDialog(this,
					"Necessário selecionar um ou mais Locais de Serviço!");

			return false;

		}

		if (from == null) {

			JOptionPane.showMessageDialog(this,
					"Necessário selecionar a Data inicial!");

			return false;

		}

		if (to == null) {

			JOptionPane.showMessageDialog(this,
					"Necessário selecionar a Data Final!");

			return false;

		}

		if (idCodigosCompensacao != null && idCodigosCompensacao.size() < 1) {

			JOptionPane.showMessageDialog(this,
					"Necessário selecionar os status de compensação!");

			return false;

		}

		if (pastaOrigem == null) {

			JOptionPane.showMessageDialog(this,
					"Necessário selecionar a pasta de origem!");

			return false;

		}

		if (pastaDestino == null) {

			JOptionPane.showMessageDialog(this,
					"Necessário selecionar a pasta de destino!");

			return false;

		}

		return true;
	}

	private int getIdClasseContrato() {
		// TODO Auto-generated method stub

		String value = "0";

		try {

			value = classeContratoOptionGroup.getSelection().getActionCommand();
		} catch (NullPointerException e) {

			e.printStackTrace();

		}

		return new Integer(value);
	}

	private List getLocaisServico() {
		// TODO Auto-generated method stub

		List lista = new ArrayList();

		if (localServicoTP1.isSelected())
			lista.add(79);

		if (localServicoTP2.isSelected())
			lista.add(80);

		return lista;
	}

	private List getSelectedCompensation() {
		// TODO Auto-generated method stub

		List codigosCompensacao = null;

		for (JCheckBox chk : checkBoxes) {

			if (codigosCompensacao == null)
				codigosCompensacao = new ArrayList();

			if (chk.isSelected()) {

				codigosCompensacao.add(codigosCompensacaoMap.get(chk.getText()
						.substring(chk.getText().indexOf('-') + 1)));

			}

		}

		return codigosCompensacao;
	}

	private JFileChooser createFileChooser() {
		// TODO Auto-generated method stub

		JFileChooser fc = new JFileChooser();

		fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

		// fc.setFileFilter(new FileNameExtensionFilter(descExtensao,
		// extensao));

		fc.showOpenDialog(this);

		return fc;
	}

	/**
	 * x
	 */

	public void showFrame() {
		// TODO Auto-generated method stub

		setVisible(true);

	}

}
