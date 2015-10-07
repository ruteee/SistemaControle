import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;

import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSpinner;

import javax.swing.JComboBox;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;

public class Tela extends TelaGeral{
	private JFrame frmSupervisrioDeTanques;
	
	private JCheckBoxMenuItem menu2porcento, menu5porcento, menu7porcento, menu10porcento;
	private JCheckBoxMenuItem menuPorcentagem, menuAbs;
	private JCheckBoxMenuItem menu0a100, menu5a95, menu10a90;	
	
	private JPanel panelDadosServidor;
	private JLabel labelIp, labelPorta;
	private JLabel labelLeitura1, labelLeitura2, labelEscrita;
	
	private JPanel panelGraficos;
	private JLayeredPane panelGrafico1, panelGrafico2;
	private JLabel lblExibirCheckSinalGrafico1, lblExibirCheckSinalGrafico2;
	private JCheckBox chckbxTensaoSat, chckbxTensCalc, chckbxNivTanque1, chckbxNivTanque2, chckbxSetPoint, chckbxErro;
	private JCheckBox chckbxP, chckbxI, chckbxD;
	
	private JRadioButton rdbtnTempoSubida1, rdbtnTempoSubida2, rdbtnTempoSubida3;
	
	private JSpinner spinnerTs;
	
	private JLabel textPaneTr, textPaneMp, textPaneTp, textPaneTs;
	
	public String onda_limpa_tanque;
	public double amplitude_limpa_tanque;
	
	private JRadioButton rdbtnTanque2, rdbtnTanque1;
	private JRadioButton rdbtnAberta, rdbtnFechada;
	private JLabel lblPeriodo, lblAmplitude;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboTipoOnda;
	
	private JSpinner amplitude, amplitudeMin, periodo, periodoMin, offSet;
	private JCheckBox chckbxComControle, chckbxWindUp;
	
	private Tanque thread;
	private Dados dados;
	private DadosConexao dadosConexao;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboTipoControladorMestre;
	private JLabel kpMestre, kiMestre, taliMestre, kdMestre, taldMestre, taltMestre;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboTipoControladorEscravo;
	private JLabel kpEscravo, kiEscravo, taliEscravo, kdEscravo, taldEscravo, taltEscravo;
	
	private JButton botaoAtualizar, btnReset;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				//Muda disgn para o disgn padrão do SO.
				try {   
				    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());  
				} catch (UnsupportedLookAndFeelException ex1) {  
				    ex1.printStackTrace();  
				} catch (IllegalAccessException ex2) {  
				    ex2.printStackTrace();  
				} catch (InstantiationException ex3) {  
				    ex3.printStackTrace();  
				} catch (ClassNotFoundException ex4) {  
					ex4.printStackTrace();  
				} 
				
				Tela window = new Tela();
				window.frmSupervisrioDeTanques.setVisible(true);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Tela() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		dados = new Dados();
		dadosConexao = new DadosConexao();
		dadosConexao.setLblIp(labelIp);
		dadosConexao.setLblPorta(labelPorta);
		dadosConexao.setLblLeitura1(labelLeitura1);
		dadosConexao.setLblLeitura2(labelLeitura2);
		dadosConexao.setLblEscrita(labelEscrita);
		
		frmSupervisrioDeTanques = new JFrame();
		frmSupervisrioDeTanques.setTitle("Supervis\u00F3rio de Tanques de \u00C1gua");
		frmSupervisrioDeTanques.getContentPane().setLocation(0, -113);
		frmSupervisrioDeTanques.setBounds(100, 100, 1169, 721);
		frmSupervisrioDeTanques.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmSupervisrioDeTanques.getContentPane().setLayout(null);
		
		inicializarMenuBar();
		
		inicializarPainelDadosServidor();
		frmSupervisrioDeTanques.getContentPane().add(panelDadosServidor);
		
		JLabel lblLeitura_1 = new JLabel("Leitura 1:");
		lblLeitura_1.setBounds(129, 20, 46, 14);
		panelDadosServidor.add(lblLeitura_1);
		
		labelLeitura1 = new JLabel();
		labelLeitura1.setBounds(176, 20, 26, 14);
		labelLeitura1.setText(" ");
		panelDadosServidor.add(labelLeitura1);
		
		JLabel lblLeitura_2 = new JLabel("Leitura 2:");
		lblLeitura_2.setBounds(229, 20, 46, 14);		
		panelDadosServidor.add(lblLeitura_2);
		
		labelEscrita = new JLabel();
		labelEscrita.setBounds(176, 50, 26, 14);
		labelEscrita.setText(" ");
		panelDadosServidor.add(labelEscrita);
		
		labelLeitura2 = new JLabel();
		labelLeitura2.setBounds(281, 20, 26, 14);
		labelLeitura2.setText(" ");
		panelDadosServidor.add(labelLeitura2);
		
		JLabel lblEscrita = new JLabel("Escrita:");
		lblEscrita.setBounds(129, 50, 40, 14);
		panelDadosServidor.add(lblEscrita);
		
		inicializeBotõesPainelPrincipal();
		
		inicializePainelOpcoesEntrada();
		
		inicializaPainelGraficos();
		frmSupervisrioDeTanques.getContentPane().add(panelGraficos);
		
		inicializarPainelEstatísticas();
		
		inicializaPainelCheckSinaisGraficos();
		
//		redimensionarPainelGrafico2(panelGrafico1, panelGrafico2);
	}
	
	private void inicializarMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.LIGHT_GRAY);
		menuBar.setBounds(0, 0, 1153, 21);
		frmSupervisrioDeTanques.getContentPane().add(menuBar);
		
		JMenu menuConexao = new JMenu("Conex\u00E3o");
		menuBar.add(menuConexao);
		
		JMenuItem menuConfig = new JMenuItem("Configurar");
		menuConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				ConfigConexao conf = new ConfigConexao(dadosConexao);
				conf.setVisible(true);
			}
		});
		menuConexao.add(menuConfig);
		
		JMenu menuEstatisticas = new JMenu("Estat\u00EDsticas");
		menuBar.add(menuEstatisticas);
		
		JMenu menuTr = new JMenu("Tempo de Subida");
		menuEstatisticas.add(menuTr);
		
		menu0a100 = new JCheckBoxMenuItem("0 - 100%");
		menuTr.add(menu0a100);
		
		menu5a95 = new JCheckBoxMenuItem("5 - 95%");
		menuTr.add(menu5a95);
		
		menu10a90 = new JCheckBoxMenuItem("10 - 90%");
		menuTr.add(menu10a90);
		
		JMenu menuTs = new JMenu("Tempo de Acomoda\u00E7\u00E3o");
		menuEstatisticas.add(menuTs);
		
		menu2porcento = new JCheckBoxMenuItem("2%");
		menuTs.add(menu2porcento);
		
		menu5porcento = new JCheckBoxMenuItem("5%");
		menuTs.add(menu5porcento);
		
		menu7porcento = new JCheckBoxMenuItem("7%");
		menuTs.add(menu7porcento);
		
		menu10porcento = new JCheckBoxMenuItem("10%");
		menuTs.add(menu10porcento);
		
		JMenu menuMp = new JMenu("MP");
		menuEstatisticas.add(menuMp);
		
		menuPorcentagem = new JCheckBoxMenuItem("Porcentagem (%)");
		menuMp.add(menuPorcentagem);
		
		menuAbs = new JCheckBoxMenuItem("ABS");
		menuMp.add(menuAbs);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes"})
	private void inicializarPainelEstatísticas(){
		//Inicializando Painel Painel de exibição dos Valores
		JPanel panelValores = new JPanel();
		panelValores.setBorder(new TitledBorder(null, "Valores Atuais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelValores.setBounds(343, 608, 800, 56);
		frmSupervisrioDeTanques.getContentPane().add(panelValores);
		panelValores.setLayout(null);
		
		JLabel lblTr = new JLabel("Tr:");
		lblTr.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTr.setBounds(31, 12, 24, 36);
		panelValores.add(lblTr);
		
		textPaneTr = new JLabel();
		textPaneTr.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPaneTr.setBounds(59, 12, 101, 36);
		panelValores.add(textPaneTr);
		
		JLabel lblMp = new JLabel("Mp:");
		lblMp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMp.setBounds(360, 12, 30, 36);
		panelValores.add(lblMp);
		
		textPaneMp = new JLabel();
		textPaneMp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPaneMp.setBounds(394, 12, 101, 36);
		panelValores.add(textPaneMp);
		
		JLabel lblTp = new JLabel("Tp:");
		lblTp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTp.setBounds(194, 12, 28, 36);
		panelValores.add(lblTp);
		
		textPaneTp = new JLabel();
		textPaneTp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPaneTp.setBounds(225, 12, 101, 36);
		panelValores.add(textPaneTp);
		
		JLabel lblTs = new JLabel("Ts:");
		lblTs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTs.setBounds(529, 12, 28, 36);
		panelValores.add(lblTs);
		
		textPaneTs = new JLabel();
		textPaneTs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		textPaneTs.setBounds(560, 12, 101, 36);
		panelValores.add(textPaneTs);	
		
		JPanel painelTipoOnda = new JPanel();
		painelTipoOnda.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		painelTipoOnda.setLayout(null);
		painelTipoOnda.setBounds(6, 194, 336, 34);
		frmSupervisrioDeTanques.getContentPane().add(painelTipoOnda);
		
		JLabel label_TipoOnda = new JLabel("Tipo de Onda:");
		label_TipoOnda.setBounds(52, 5, 78, 23);
		painelTipoOnda.add(label_TipoOnda);
		
		comboTipoOnda = new JComboBox(getItensComboTiposOnda());
		comboTipoOnda.setBounds(131, 5, 151, 23);
		painelTipoOnda.add(comboTipoOnda);
		comboTipoOnda.setEnabled(false);
		
		botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(45, 640, 101, 23);
		frmSupervisrioDeTanques.getContentPane().add(botaoAtualizar);
		botaoAtualizar.setEnabled(false);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(186, 640, 101, 23);
		frmSupervisrioDeTanques.getContentPane().add(btnReset);
		btnReset.setEnabled(false);
		
		JPanel painelTipoMalha = new JPanel();
		painelTipoMalha.setBounds(6, 108, 336, 42);
		frmSupervisrioDeTanques.getContentPane().add(painelTipoMalha);
		painelTipoMalha.setLayout(null);
		painelTipoMalha.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de Malha", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		
		rdbtnAberta = new JRadioButton("Aberta");
		rdbtnAberta.setEnabled(false);
		rdbtnAberta.setBounds(63, 16, 67, 16);
		painelTipoMalha.add(rdbtnAberta);
		
		JRadioButton rdbtnFechada = new JRadioButton("Fechada");
		rdbtnFechada.setEnabled(false);
		rdbtnFechada.setBounds(193, 16, 68, 16);
		painelTipoMalha.add(rdbtnFechada);
		
		JPanel painelOpcoesTanque = new JPanel();
		painelOpcoesTanque.setBounds(6, 150, 336, 42);
		frmSupervisrioDeTanques.getContentPane().add(painelOpcoesTanque);
		painelOpcoesTanque.setLayout(null);
		painelOpcoesTanque.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Opções de Tanque", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		
		JRadioButton rdbtnTanque1 = new JRadioButton("Tanque 1");
		rdbtnTanque1.setSelected(false);
		rdbtnTanque1.setEnabled(false);
		rdbtnTanque1.setBounds(61, 19, 71, 16);
		painelOpcoesTanque.add(rdbtnTanque1);
		
		rdbtnTanque2 = new JRadioButton("Tanque 2");
		rdbtnTanque2.setSelected(false);
		rdbtnTanque2.setEnabled(false);
		rdbtnTanque2.setBounds(193, 19, 71, 16);
		painelOpcoesTanque.add(rdbtnTanque2);
		
		JPanel painelParamsMestre = new JPanel();
		painelParamsMestre.setBounds(6, 382, 336, 118);
		frmSupervisrioDeTanques.getContentPane().add(painelParamsMestre);
		painelParamsMestre.setLayout(null);
		painelParamsMestre.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Par\u00E2metros do Controlador Mestre", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)));
		
		JLabel labelMestre_Kp = new JLabel("Kp:");
		labelMestre_Kp.setBounds(32, 53, 22, 14);
		painelParamsMestre.add(labelMestre_Kp);
		
		kpMestre = new JLabel();
		kpMestre.setEnabled(false);
		kpMestre.setBounds(58, 53, 47, 14);
		painelParamsMestre.add(kpMestre);
		
		JLabel labelMestre_Ki = new JLabel("Ki:");
		labelMestre_Ki.setBounds(32, 73, 22, 14);
		painelParamsMestre.add(labelMestre_Ki);
		
		kiMestre = new JLabel();
		kiMestre.setEnabled(false);
		kiMestre.setBounds(58, 73, 47, 14);
		painelParamsMestre.add(kiMestre);
		
		JLabel labelMestre_Tali = new JLabel("\u03C4i:");
		labelMestre_Tali.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		labelMestre_Tali.setBounds(164, 73, 23, 14);
		painelParamsMestre.add(labelMestre_Tali);
		
		taliMestre = new JLabel();
		taliMestre.setEnabled(false);
		taliMestre.setBounds(197, 73, 47, 14);
		painelParamsMestre.add(taliMestre);
		
		JLabel labelMestre_Kd = new JLabel("Kd:");
		labelMestre_Kd.setBounds(32, 93, 22, 14);
		painelParamsMestre.add(labelMestre_Kd);
		
		kdMestre = new JLabel();
		kdMestre.setEnabled(false);
		kdMestre.setBounds(58, 93, 47, 14);
		painelParamsMestre.add(kdMestre);
		
		JLabel labelMestre_Tald = new JLabel("\u03C4d:");
		labelMestre_Tald.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		labelMestre_Tald.setBounds(164, 93, 23, 14);
		painelParamsMestre.add(labelMestre_Tald);
		
		taldMestre = new JLabel();
		taldMestre.setEnabled(false);
		taldMestre.setBounds(197, 93, 47, 14);
		painelParamsMestre.add(taldMestre);
		
		taltMestre = new JLabel();
		taltMestre.setEnabled(false);
		taltMestre.setBounds(197, 53, 47, 14);
		painelParamsMestre.add(taltMestre);
		
		JLabel labelMestre_Talt = new JLabel("Tt:");
		labelMestre_Talt.setBounds(165, 53, 22, 14);
		painelParamsMestre.add(labelMestre_Talt);
		
		JLabel lblConfMestre = new JLabel("");
		lblConfMestre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DadosControlador dadosControlador = new DadosControlador();
				ConfigControlador conf = new ConfigControlador("Configuração do Mestre", dadosControlador);
				
				conf.show();
				conf.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
				frmSupervisrioDeTanques.getContentPane().add(conf);
			}
		});
		lblConfMestre.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1444113669_Pinion.png")));
		lblConfMestre.setToolTipText("Para Atualizar os parametros, basta clicar nos campos.");
		lblConfMestre.setBounds(282, 11, 32, 31);
		painelParamsMestre.add(lblConfMestre);
		
		JLabel lblTipoControladorMestre = new JLabel("Tipo do Controlador:");
		lblTipoControladorMestre.setBounds(10, 19, 100, 23);
		painelParamsMestre.add(lblTipoControladorMestre);
		
		comboTipoControladorMestre = new JComboBox(getItensComboTiposControle());
		comboTipoControladorMestre.setBounds(111, 19, 151, 23);
		painelParamsMestre.add(comboTipoControladorMestre);
		comboTipoControladorMestre.setEnabled(false);
		
		JPanel painelParamsSinal = new JPanel();
		painelParamsSinal.setBounds(6, 260, 336, 115);
		frmSupervisrioDeTanques.getContentPane().add(painelParamsSinal);
		painelParamsSinal.setLayout(null);
		painelParamsSinal.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Par\u00E2metros do Sinal", TitledBorder.RIGHT, TitledBorder.TOP, null, Color.GRAY));
		
		lblAmplitude = new JLabel("Amplitude:");
		lblAmplitude.setBounds(9, 27, 84, 20);
		painelParamsSinal.add(lblAmplitude);
		
		amplitude = new JSpinner();
		amplitude.setEnabled(false);
		amplitude.setBounds(94, 27, 51, 20);
		painelParamsSinal.add(amplitude);
		
		JLabel lblAmplitudeMin = new JLabel("Amplitude (M\u00EDn):");
		lblAmplitudeMin.setBounds(155, 27, 85, 20);
		painelParamsSinal.add(lblAmplitudeMin);
		
		amplitudeMin = new JSpinner();
		amplitudeMin.setEnabled(false);
		amplitudeMin.setBounds(256, 27, 51, 20);
		painelParamsSinal.add(amplitudeMin);
		
		JLabel lblPeriodo = new JLabel("Per\u00EDodo:");
		lblPeriodo.setBounds(8, 56, 85, 20);
		painelParamsSinal.add(lblPeriodo);
		
		periodo = new JSpinner();
		periodo.setEnabled(false);
		periodo.setBounds(94, 56, 51, 20);
		painelParamsSinal.add(periodo);
		
		JLabel lblPeriodoMin = new JLabel("Per\u00EDodo (M\u00EDn):");
		lblPeriodoMin.setBounds(155, 56, 85, 20);
		painelParamsSinal.add(lblPeriodoMin);
		
		periodoMin = new JSpinner();
		periodoMin.setEnabled(false);
		periodoMin.setBounds(256, 56, 51, 20);
		painelParamsSinal.add(periodoMin);
		
		JLabel lblOffSet = new JLabel("Off-Set:");
		lblOffSet.setBounds(8, 85, 67, 20);
		painelParamsSinal.add(lblOffSet);
		
		offSet = new JSpinner();
		offSet.setEnabled(false);
		offSet.setBounds(94, 85, 51, 20);
		painelParamsSinal.add(offSet);
		
		JPanel painelParamsEscravo = new JPanel();
		painelParamsEscravo.setLayout(null);
		painelParamsEscravo.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Par\u00E2metros do Controlador Escravo", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)));
		painelParamsEscravo.setBounds(6, 501, 336, 118);
		frmSupervisrioDeTanques.getContentPane().add(painelParamsEscravo);
		
		JLabel labelEscravo_Kp = new JLabel("Kp:");
		labelEscravo_Kp.setBounds(29, 53, 22, 14);
		painelParamsEscravo.add(labelEscravo_Kp);
		
		kpEscravo = new JLabel();
		kpEscravo.setEnabled(false);
		kpEscravo.setBounds(55, 53, 47, 14);
		painelParamsEscravo.add(kpEscravo);
		
		JLabel labelEscravo_Ki = new JLabel("Ki:");
		labelEscravo_Ki.setBounds(29, 73, 22, 14);
		painelParamsEscravo.add(labelEscravo_Ki);
		
		kiEscravo = new JLabel();
		kiEscravo.setEnabled(false);
		kiEscravo.setBounds(55, 73, 47, 14);
		painelParamsEscravo.add(kiEscravo);
		
		JLabel labelEscravo_Tali = new JLabel("\u03C4i:");
		labelEscravo_Tali.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		labelEscravo_Tali.setBounds(161, 73, 23, 14);
		painelParamsEscravo.add(labelEscravo_Tali);
		
		taliEscravo = new JLabel();
		taliEscravo.setEnabled(false);
		taliEscravo.setBounds(194, 73, 47, 14);
		painelParamsEscravo.add(taliEscravo);
		
		JLabel labelEscravo_Kd = new JLabel("Kd:");
		labelEscravo_Kd.setBounds(29, 93, 22, 14);
		painelParamsEscravo.add(labelEscravo_Kd);
		
		kdEscravo = new JLabel();
		kdEscravo.setEnabled(false);
		kdEscravo.setBounds(55, 93, 47, 14);
		painelParamsEscravo.add(kdEscravo);
		
		JLabel labelEscravo_Tald = new JLabel("\u03C4d:");
		labelEscravo_Tald.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		labelEscravo_Tald.setBounds(161, 93, 23, 14);
		painelParamsEscravo.add(labelEscravo_Tald);
		
		taldEscravo = new JLabel();
		taldEscravo.setEnabled(false);
		taldEscravo.setBounds(194, 93, 47, 14);
		painelParamsEscravo.add(taldEscravo);
		
		taltEscravo = new JLabel();
		taltEscravo.setEnabled(false);
		taltEscravo.setBounds(194, 53, 47, 14);
		painelParamsEscravo.add(taltEscravo);
		
		JLabel labelEscravo_Talt = new JLabel("Tt:");
		labelEscravo_Talt.setBounds(162, 53, 22, 14);
		painelParamsEscravo.add(labelEscravo_Talt);
		
		JLabel lblConfEscravo = new JLabel("");
		lblConfEscravo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				DadosControlador dadosControlador = new DadosControlador();
				ConfigControlador conf = new ConfigControlador("Configuração do Escravo", dadosControlador);
				
				conf.show();
				conf.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);
				frmSupervisrioDeTanques.getContentPane().add(conf);
			}
		});
		lblConfEscravo.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1444113669_Pinion.png")));
		lblConfEscravo.setToolTipText("Para Atualizar os parametros, basta clicar nos campos.");
		lblConfEscravo.setBounds(282, 11, 32, 31);
		painelParamsEscravo.add(lblConfEscravo);
		
		JLabel lblTipoControladorEscravo = new JLabel("Tipo do Controlador:");
		lblTipoControladorEscravo.setBounds(10, 19, 100, 23);
		painelParamsEscravo.add(lblTipoControladorEscravo);
		
		comboTipoControladorEscravo = new JComboBox(getItensComboTiposControle());
		comboTipoControladorEscravo.setEnabled(false);
		comboTipoControladorEscravo.setBounds(111, 19, 151, 23);
		painelParamsEscravo.add(comboTipoControladorEscravo);
		
		chckbxComControle = new JCheckBox("Acionar Controlador");
		chckbxComControle.setBounds(45, 235, 124, 23);
		frmSupervisrioDeTanques.getContentPane().add(chckbxComControle);
		chckbxComControle.setEnabled(false);
		
		chckbxWindUp = new JCheckBox("Acionar Wind Up");
		chckbxWindUp.setBounds(190, 235, 113, 23);
		frmSupervisrioDeTanques.getContentPane().add(chckbxWindUp);
		chckbxWindUp.setEnabled(false);
	}
	
	private void inicializarPainelDadosServidor(){
		panelDadosServidor = new JPanel();
		panelDadosServidor.setBounds(6, 25, 336, 83);
		panelDadosServidor.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dados do Servidor", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDadosServidor.setLayout(null);
		
		JLabel lblIPServidor = new JLabel("IP:");
		lblIPServidor.setBounds(10, 20, 20, 14);
		panelDadosServidor.add(lblIPServidor);
		
		labelIp = new JLabel();
		labelIp.setBounds(31, 20, 88, 14);
		labelIp.setText(" ");
		panelDadosServidor.add(labelIp);
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(10, 50, 46, 14);
		panelDadosServidor.add(lblPorta);
		
		labelPorta = new JLabel();
		labelPorta.setBounds(51, 50, 68, 14);
		labelPorta.setText(" ");
		panelDadosServidor.add(labelPorta);
		
		final JButton btnConectarDesconectar = new JButton("Conectar");
		btnConectarDesconectar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269447_gtk-apply.png")));
		btnConectarDesconectar.setForeground(new Color(0, 128, 0));
		btnConectarDesconectar.setBackground(new Color(0, 128, 0));
		btnConectarDesconectar.setBounds(229, 42, 97, 30);
		btnConectarDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(dadosConexao.getIp().getText().equals("") || dadosConexao.getPorta().equals("")){
					JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "                          Conexão não Realizada! " +
							"\nInforme o Ip do Servidor e/ou a Porta e tente novamente.");
				}else{
					thread = new Tanque();
					thread.setServer(labelIp.getText(), Integer.parseInt(labelPorta.getText()));
					
					//Muda nome (conectar ou desconectar) e cor (verde ou vermelho) do botão.
					//Também desabilita alguns componentes da tela.			
					if(btnConectarDesconectar.getText().equals("Conectar")){
						JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Conexão Realizada com Sucesso!");
						
						labelIp.setText(dadosConexao.getIp().getText());
						labelPorta.setText(dadosConexao.getPorta().getText());
						labelLeitura1.setText(dadosConexao.getLeitura1().getSelectedItem() + "");
						labelLeitura2.setText(dadosConexao.getLeitura2().getSelectedItem() + "");
						labelEscrita.setText(dadosConexao.getEscrita().getSelectedItem() + "");
												
						btnConectarDesconectar.setForeground(Color.RED);
						btnConectarDesconectar.setBackground(Color.RED);
						btnConectarDesconectar.setText("Desconectar");
						btnConectarDesconectar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269745_gtk-dialog-error.png")));
						mudarPropriedadesBotoes("Conectar");
					}else{
						
						btnConectarDesconectar.setForeground(new Color(0, 128, 0));
						btnConectarDesconectar.setBackground(new Color(0, 128, 0));
						btnConectarDesconectar.setText("Conectar");
						btnConectarDesconectar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269447_gtk-apply.png")));
						mudarPropriedadesBotoes("Desconectar");
					}
				}
			}
		});
		panelDadosServidor.add(btnConectarDesconectar);
	}
	
	private void inicializeBotõesPainelPrincipal(){
		botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(37, 482, 101, 23);
		botaoAtualizar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269378_gtk-refresh.png")));		
		botaoAtualizar.setEnabled(false);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(178, 482, 101, 23);
		btnReset.setEnabled(false);
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//onda_limpa_tanque = "Degrau";
				//amplitude_limpa_tanque = 0;
				
			}
		});
		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { 
				dados = new Dados();					
				
				if(validaDadosDeIO() && validaTipoMalha() && validaOnda() && validaParamsControlador(comboTipoControladorMestre)){
					
					//dados.setComControle(chckbxComControle.isSelected());
					
					/*thread.graficoAltura.limparFilaDeErroMesmo();
					thread.graficoAltura.limparFilaDeNivelDois();
					thread.graficoAltura.limparFilaDeNivelUm();
					thread.graficoAltura.limparFilaDeSetPoint();
					thread.grafico.limparFilaDeVP();
					thread.grafico.limparFilaDeP();
					thread.grafico.limparFilaDeI();
					thread.grafico.limparFilaDeD();*/
					
					
					// Setar na dados os checkBox dos gráficos
					dados.setTensao(chckbxTensCalc.isSelected());
					dados.setTensaoSat(chckbxTensaoSat.isSelected());
					dados.setSetPoint(chckbxSetPoint.isSelected());
					dados.setErroMesmo(chckbxErro.isSelected());
					//dados.setErro(chckbxControle.isSelected());
					dados.setProporcional(chckbxP.isSelected());
					dados.setIntegral(chckbxI.isSelected());
					dados.setDerivativo(chckbxD.isSelected());
					dados.setNivel1(chckbxNivTanque1.isSelected());
					dados.setWindUP(chckbxWindUp.isSelected());
					
					dados.settPico(textPaneTp);
					dados.settAcomoda(textPaneTs);
					dados.settSubida(textPaneTr);
					dados.setNivelPico(textPaneMp);
					
					dados.setPicoAbs(menuAbs.isSelected());
					
					if (rdbtnTempoSubida1.isSelected())
					{
						dados.setFatInf(0);
						dados.setFatSup(1);
					}
					else if (rdbtnTempoSubida2.isSelected())
					{
						dados.setFatInf(0.05);
						dados.setFatSup(0.95);
					}
					else if (rdbtnTempoSubida3.isSelected())
					{
						dados.setFatInf(0.1);
						dados.setFatSup(0.9);
					}
					if(chckbxWindUp.isSelected()){
						dados.setTt(Double.parseDouble(taltMestre.getText()));
					}
					
					if(rdbtnTanque1.isSelected()){
						dados.setTanque1(true);
					}					
					if(rdbtnTanque2.isSelected()){
						dados.setTanque2(true);
					}
					
					int a = (int) spinnerTs.getValue();
					switch(a){
					
						case 2: 
							dados.setFaixa2(true);
						break;
						
						case 5: 
							dados.setFaixa5(true);
						break;
						
						case 7: 
							dados.setFaixa7(true);
						break;
						
						case 10: 
							dados.setFaixa10(true);
						break;
					}					
					
					//grafico
					thread.setDados(dados);
					thread.setDadosGrafico(dados);
					if (!thread.isAlive()) {		
						thread.setPainelTensao(panelGrafico1);
						thread.setPainelAltura(panelGrafico2);
						thread.start();
					}
				}
			}
		});
	}
	
	/** 
	 * Valida o tanque que será controlado. Depois, Popula os parâmetros de Leitura e Escrita na classe Dados.
	 */
	public boolean validaTanque(){
		if(!rdbtnTanque1.isSelected() && !rdbtnTanque2.isSelected()){
			JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Informe a planta que você deseja controlar.");
		
			return false;
		}
		
		if(rdbtnTanque1.isSelected()){		
			dados.setPinoDeLeitura1(0);
		
			return true;
		}
		
		if(rdbtnTanque2.isSelected()){
			dados.setPinoDeLeitura2(1);
			
			return true;
		}
		
		return true;
	}	

	/** 
	 * Valida campos e Popula os parâmetros de Leitura(1 e 2) e Escrita na classe Dados.
	 */
	public boolean validaDadosDeIO(){			
		if(dadosConexao.getLeitura1().getSelectedIndex() == 0){
				JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Informe o porta de Leitura 1.");
			dados.setPinoDeLeitura1((int)((Integer)dadosConexao.getLeitura1().getSelectedItem()));
			return false;
		}else{
			dados.setPinoDeLeitura1((int)((Integer)dadosConexao.getLeitura1().getSelectedItem()));
		}
		
		if(dadosConexao.getLeitura2().getSelectedIndex() == 0){
		
			JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Informe o porta de Leitura 2.");
			dados.setPinoDeLeitura2((int)((Integer)dadosConexao.getLeitura2().getSelectedItem()));
			return false;
		}else{
			dados.setPinoDeLeitura2((int)((Integer)dadosConexao.getLeitura2().getSelectedItem()));
		}
			
		if(dadosConexao.getEscrita().getSelectedIndex() == 0){
			JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Informe a porta de Escrita.");
			return false;
		}else{
			dados.setPinoDeEscrita((int)((Integer)dadosConexao.getEscrita().getSelectedItem()));
		}
		
		return true;
	}
	
	/** 
	 * Valida campos e Popula o tipo de malha na classe Dados.
	 */
	public boolean validaTipoMalha(){
		if(!rdbtnAberta.isSelected() && !rdbtnFechada.isSelected()){
			JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Informe o Tipo de Malha.");
			
			return false;
		}else{
			dados.setTipoMalha(rdbtnAberta.isSelected() ? "Malha Aberta" : "Malha Fechada");
		}
		
		return true;
	}
	
	/** 
	 * Valida campos e Popula os parâmetros do sinal na classe Dados.
	 */
	public boolean validaOnda(){
		
		if(comboTipoOnda.getSelectedIndex() == 0){
			JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Informe o Tipo de Onda.");
			
			return false;
		}else {
			dados.setTipoSinal(comboTipoOnda.getSelectedItem().toString());
			
			if(amplitude.getValue().equals("")){
				String amplitude = comboTipoOnda.getSelectedItem().equals("Degrau") ? "Amplitude (Máx)" : "Amplitude";  

				JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Informe a " + amplitude + " do sinal.");
				
				return false;
			}else{
				dados.setAmplitude((double)amplitude.getValue());
			}
			
			if(comboTipoOnda.getSelectedItem().equals("Quadrada") || 
					comboTipoOnda.getSelectedItem().equals("Senoidal") || comboTipoOnda.getSelectedItem().equals("Dente de Serra")){
				
				if(periodo.getValue().equals("")){
					JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Informe o Período do sinal.");
					
					return false;
				}else{
					dados.setPeriodo((double)periodo.getValue());
				}
				
				if(offSet.getValue().equals("")){
					JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Informe a OffSet do sinal.");
					
					return false;
				}else{
					dados.setOffset((double)offSet.getValue());
				}
			}
			
			if(comboTipoOnda.getSelectedItem().equals("Aleatória")){
				
				if(amplitudeMin.getValue().equals("")){
					JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Informe a Amplitude (Mín) do sinal.");
				
					return false;
				}else{
					dados.setAmplitudeMinima(((double) amplitudeMin.getValue()));
				}

				if(periodo.getValue().equals("")){
					JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Informe o Período do sinal.");
				
					return false;
				}else{
					dados.setPeriodo((double)periodo.getValue());
				}
				
				if(periodoMin.getValue().equals("")){
					JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Informe o Período (Mín) do sinal.");
					
					return false;
				}else{
					dados.setPeriodoMinino((double) periodoMin.getValue());
				}
			}
		}
		
		return true;
	}
	
		
	@SuppressWarnings("rawtypes")
	private boolean validaParamsControlador(JComboBox tipoControlador){
		if(!rdbtnAberta.isSelected() && chckbxComControle.isSelected()){
			if(tipoControlador.getSelectedIndex() == 0){
				JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Informe o tipo de controlador!");
				
				return false;
			}else{
				dados.setTipoDeControle(tipoControlador.getSelectedItem().toString());
				
				if(kpMestre.getText().equals("")){
					JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Informe o valor de Kp.");
					
					return false;
				}else{
					dados.setKP(Double.parseDouble(kpMestre.getText()));
					
					if((tipoControlador.getSelectedItem().equals("PI") || tipoControlador.getSelectedItem().equals("PID") || tipoControlador.getSelectedItem().equals("PI-D"))
							&& (kiMestre.getText().equals("") || taliMestre.getText().equals(""))){
						
						JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Informe todos os parâmetros do controlador integrativo (Ki e Ti).");
						
						return false;
					}else if((tipoControlador.getSelectedItem().equals("PI") || tipoControlador.getSelectedItem().equals("PID") || tipoControlador.getSelectedItem().equals("PI-D"))
							&& !(kiMestre.getText().equals("") || taliMestre.getText().equals(""))){
				
						dados.setKI(Double.parseDouble(kiMestre.getText()));
					}
					
					if((tipoControlador.getSelectedItem().equals("PD") || tipoControlador.getSelectedItem().equals("PID") || tipoControlador.getSelectedItem().equals("PI-D"))
							&& (kdMestre.getText().equals("") || taldMestre.getText().equals(""))){
						
						JOptionPane.showMessageDialog(frmSupervisrioDeTanques, "Informe todos os parâmetros do controlador derivativo (Kd e Td).");
						
						return false;
					}else if((tipoControlador.getSelectedItem().equals("PD") || tipoControlador.getSelectedItem().equals("PID") || tipoControlador.getSelectedItem().equals("PI-D"))
							&& !(kdMestre.getText().equals("") || taldMestre.getText().equals(""))){
						
						dados.setKD(Double.parseDouble(kdMestre.getText()));
					}
				}
			}
		}
		
		return true;
	}
	
	private void inicializePainelOpcoesTanque(){
	}
	
	private void inicializePainelOpcoesEntrada(){
		
		inicializePainelOpcoesTanque();
		
		inicializarPainelTiposMalha();
		
		inicializarPainelDadosSinal();
		
//		inicializarPainelTipoControlador();
//		panelOpcoesEntrada.add(panelTipoControlador);
		
		inicializarPainelParamsControlador();
		
		inicializarOutrosComponentesPainelOpcoesEntrada();
		
		inicializeBotõesPainelPrincipal();
		
	}
	
	private void inicializarPainelTiposMalha(){
	}
	
	private void inicializarPainelDadosSinal(){
	}
		
	private void inicializarPainelParamsControlador(){
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void inicializarOutrosComponentesPainelOpcoesEntrada(){
	}
	
	private void inicializaPainelGraficos(){
		panelGraficos = new JPanel();
		panelGraficos.setBounds(343, 25, 800, 572);
		panelGraficos.setBorder(new TitledBorder(null, "Gr\u00E1ficos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGraficos.setLayout(null);
		
		inicializaPainelGrafico1();
		panelGraficos.add(panelGrafico1);
		
		inicializaPainelGrafico2();
		panelGraficos.add(panelGrafico2);
	}
	
	private void inicializaPainelGrafico1(){
		panelGrafico1 = new JLayeredPane();
		panelGrafico1.setForeground(Color.WHITE);
		panelGrafico1.setBounds(8, 18, 663, 265);
		panelGrafico1.setBackground(Color.WHITE);
		panelGrafico1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGrafico1.setLayout(null);
		
//		redimensionarPainelGrafico1(panelGrafico1, panelGrafico2);
		
//		inicializaPainelTipoSinal();
//		panelGrafico1.add(panelTipoSinal);
	}
	
	private void inicializaPainelGrafico2(){
		panelGrafico2 = new JLayeredPane();
		panelGrafico2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGrafico2.setBounds(8, 296, 663, 265);
	}
	
	public void mudarPropriedadesBotoes(String acao){
		if(acao.equals("Conectar")){			
			habilitarComponentesPainelTipoMalha(true);
			
			//rdbtnTanque1.setEnabled(true);
			//rdbtnTanque2.setEnabled(true);
			
			comboTipoOnda.setEnabled(true);
			
			botaoAtualizar.setEnabled(true);
			btnReset.setEnabled(true);
			
			rdbtnTempoSubida1.setEnabled(true);
			rdbtnTempoSubida2.setEnabled(true);
			rdbtnTempoSubida3.setEnabled(true);
			
			spinnerTs.setEnabled(true);
			
			chckbxTensCalc.setEnabled(true);
			chckbxTensaoSat.setEnabled(true);
			chckbxNivTanque1.setEnabled(true);
			chckbxNivTanque2.setEnabled(true);
			chckbxSetPoint.setEnabled(true);
			chckbxErro.setEnabled(true);
			//chckbxControle.setEnabled(true);
			chckbxP.setEnabled(true);
			chckbxD.setEnabled(true);
			chckbxI.setEnabled(true);
						
//			rdbtnAleatorio.setEnabled(true);
//			rdbtnDegrau.setEnabled(true);
//			rdbtnDenteSerra.setEnabled(true);
//			rdbtnQuadrada.setEnabled(true);
//			rdbtnSenoidal.setEnabled(true);
		}else{			
			rdbtnAberta.setEnabled(false);			
			rdbtnFechada.setEnabled(false);
			
			amplitude.setEnabled(false);
			amplitudeMin.setEnabled(false);
			periodo.setEnabled(false);
			periodoMin.setEnabled(false);
			offSet.setEnabled(false);
			
	//		rdbtnTanque1.setEnabled(false);
		//	rdbtnTanque1.setSelected(false);
			//rdbtnTanque2.setEnabled(false);
		//	rdbtnTanque2.setSelected(false);

			comboTipoOnda.setEnabled(false);
			comboTipoOnda.setSelectedIndex(0);
			
			comboTipoControladorMestre.setEnabled(false);
			comboTipoControladorMestre.setSelectedIndex(0);
			
			chckbxComControle.setEnabled(false);
			chckbxComControle.setSelected(false);
			
			chckbxWindUp.setEnabled(false);
			chckbxWindUp.setSelected(false);
			
			botaoAtualizar.setEnabled(false);
			btnReset.setEnabled(false);
			
			rdbtnTempoSubida1.setEnabled(false);
			rdbtnTempoSubida1.setSelected(false);
			rdbtnTempoSubida2.setEnabled(false);
			rdbtnTempoSubida2.setSelected(false);
			rdbtnTempoSubida3.setEnabled(false);
			rdbtnTempoSubida3.setSelected(false);
			
			spinnerTs.setEnabled(false);
			spinnerTs.setValue(5);
			
			chckbxTensCalc.setEnabled(false);
			chckbxTensaoSat.setEnabled(false);
			chckbxNivTanque1.setEnabled(false);
			chckbxNivTanque2.setEnabled(false);
			chckbxSetPoint.setEnabled(false);
			chckbxErro.setEnabled(false);
			
			lblAmplitude.setText("Amplitude:");
			lblPeriodo.setText("Período:");
			
			amplitude.setValue(0);
			amplitudeMin.setValue(0);
			periodo.setValue(0);
			periodoMin.setValue(0);
			offSet.setValue(0);
			
			habilitarComponentesPainelTipoMalha(false);
			
			desabilitarParamsControle();
			
//			rdbtnAleatorio.setSelected(false);
//			rdbtnDenteSerra.setSelected(false);
//			rdbtnDegrau.setSelected(false);
//			rdbtnQuadrada.setSelected(false);
//			rdbtnSenoidal.setSelected(false);

//			rdbtnAleatorio.setEnabled(false);
//			rdbtnDegrau.setEnabled(false);
//			rdbtnDenteSerra.setEnabled(false);
//			rdbtnQuadrada.setEnabled(false);
//			rdbtnSenoidal.setEnabled(false);						
		}
	}
	
	private void inicializaCheckSinaisGrafico1(){
		
		//novos pos mudanca de chk box para esse grafico do grafico de baixo
		
	/*	chckbxControle = new JCheckBox("Controle");
		chckbxControle.setBounds(619, 121, 102, 13);
		chckbxControle.setBackground(Color.WHITE);
		chckbxControle.setVisible(false);
		chckbxControle.setEnabled(false);
		chckbxControle.setToolTipText("Sinal de Controle");
		chckbxControle.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxControle.setForeground(Color.CYAN);
		panelGraficos.add(chckbxControle);*/
		
		//novos pos mudança
		
		
		
		/*chckbxControle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setErro(chckbxControle.isSelected());
				thread.setDadosGrafico(dados);
			}
		});*/
	}
	
	private void inicializaCheckSinaisGrafico2(){
		
		JPanel panel_checkGrafico2 = new JPanel();
		panel_checkGrafico2.setBounds(681, 434, 109, 127);
		panelGraficos.add(panel_checkGrafico2);
		panel_checkGrafico2.setLayout(null);
		
		chckbxSetPoint = new JCheckBox("Set-Point");
		chckbxSetPoint.setBounds(6, 18, 102, 13);
		panel_checkGrafico2.add(chckbxSetPoint);
		chckbxSetPoint.setBackground(Color.WHITE);
		chckbxSetPoint.setVisible(false);
		chckbxSetPoint.setEnabled(false);
		chckbxSetPoint.setToolTipText("Sinal do Set-Point");
		chckbxSetPoint.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxSetPoint.setForeground(Color.RED);
		
		chckbxErro = new JCheckBox("Erro");
		chckbxErro.setBounds(6, 34, 102, 13);
		panel_checkGrafico2.add(chckbxErro);
		chckbxErro.setBackground(Color.WHITE);
		chckbxErro.setVisible(false);
		chckbxErro.setEnabled(false);
		chckbxErro.setToolTipText("ERRO");
		chckbxErro.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxErro.setForeground(Color.PINK);
		
		chckbxNivTanque1 = new JCheckBox("N\u00EDvel do Tanque 1");
		chckbxNivTanque1.setBounds(6, 50, 102, 13);
		panel_checkGrafico2.add(chckbxNivTanque1);
		chckbxNivTanque1.setBackground(Color.WHITE);
		chckbxNivTanque1.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxNivTanque1.setVisible(false);
		chckbxNivTanque1.setEnabled(false);
		chckbxNivTanque1.setToolTipText("Sinal de N\u00EDvel do Tanque 1");
		chckbxNivTanque1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxNivTanque1.setForeground(Color.BLACK);
		
		chckbxNivTanque2 = new JCheckBox("N\u00EDvel do Tanque 2");
		chckbxNivTanque2.setBounds(6, 67, 102, 13);
		panel_checkGrafico2.add(chckbxNivTanque2);
		chckbxNivTanque2.setBackground(Color.WHITE);
		chckbxNivTanque2.setVisible(false);
		chckbxNivTanque2.setEnabled(false);
		chckbxNivTanque2.setToolTipText("Sinal de N\u00EDvel do Tanque 2");
		chckbxNivTanque2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxNivTanque2.setForeground(new Color(0, 0, 205));
		
		lblExibirCheckSinalGrafico2 = new JLabel("");
		lblExibirCheckSinalGrafico2.setBounds(76, 76, 32, 43);
		panel_checkGrafico2.add(lblExibirCheckSinalGrafico2);
		lblExibirCheckSinalGrafico2.setIcon(new ImageIcon(Tela.class.getResource("/Icons/Chart-Curve-Add-32.png")));
		lblExibirCheckSinalGrafico2.setToolTipText("Exibir Sinal");
		
		JPanel panel_checkGrafico1 = new JPanel();
		panel_checkGrafico1.setBounds(681, 156, 109, 127);
		panelGraficos.add(panel_checkGrafico1);
		panel_checkGrafico1.setLayout(null);
		lblExibirCheckSinalGrafico1 = new JLabel("");
		lblExibirCheckSinalGrafico1.setBounds(76, 84, 32, 43);
		panel_checkGrafico1.add(lblExibirCheckSinalGrafico1);
		lblExibirCheckSinalGrafico1.setToolTipText("Exibir Sinal");
		lblExibirCheckSinalGrafico1.setIcon(new ImageIcon(Tela.class.getResource("/Icons/Chart-Curve-Add-32.png")));
		chckbxTensaoSat = new JCheckBox("Tens\u00E3o Sat. ");
		chckbxTensaoSat.setBounds(6, 71, 79, 13);
		panel_checkGrafico1.add(chckbxTensaoSat);
		chckbxTensaoSat.setBackground(Color.WHITE);
		chckbxTensaoSat.setVisible(false);
		chckbxTensaoSat.setEnabled(false);
		chckbxTensaoSat.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxTensaoSat.setForeground(new Color(255, 0, 0));
		chckbxTensaoSat.setToolTipText("Sinal da Tens\u00E3o Saturada");
		
		chckbxTensCalc = new JCheckBox("Tens\u00E3o Calc.");
		chckbxTensCalc.setBounds(6, 55, 79, 13);
		panel_checkGrafico1.add(chckbxTensCalc);
		chckbxTensCalc.setBackground(Color.WHITE);
		chckbxTensCalc.setEnabled(false);
		chckbxTensCalc.setVisible(false);
		chckbxTensCalc.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxTensCalc.setForeground(new Color(0, 0, 205));
		chckbxTensCalc.setToolTipText("Sinal da Tens\u00E3o Calculada");
		
		chckbxD = new JCheckBox("Ação D");
		chckbxD.setBounds(6, 39, 102, 13);
		panel_checkGrafico1.add(chckbxD);
		chckbxD.setEnabled(false);
		chckbxD.setBackground(Color.WHITE);
		chckbxD.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxD.setVisible(false);
		chckbxD.setToolTipText("Sinal da ação Derivativa");
		chckbxD.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxD.setForeground(Color.GRAY);
		
		chckbxI = new JCheckBox("Ação I");
		chckbxI.setBounds(6, 23, 102, 13);
		panel_checkGrafico1.add(chckbxI);
		chckbxI.setEnabled(false);
		chckbxI.setBackground(Color.WHITE);
		chckbxI.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxI.setVisible(false);
		chckbxI.setToolTipText("Sinal da ação Integrativa");
		chckbxI.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxI.setForeground(Color.MAGENTA);
		
		
		
		chckbxP = new JCheckBox("Ação P");
		chckbxP.setBounds(6, 7, 102, 13);
		panel_checkGrafico1.add(chckbxP);
		chckbxP.setEnabled(false);
		chckbxP.setBackground(Color.WHITE);
		chckbxP.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxP.setVisible(false);
		chckbxP.setToolTipText("Sinal da ação Proporcional");
		chckbxP.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxP.setForeground(Color.ORANGE);
		chckbxP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setProporcional(chckbxP.isSelected());
				thread.setDadosGrafico(dados);
			}
		});		
		chckbxI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setIntegral(chckbxI.isSelected());
				thread.setDadosGrafico(dados);
			}
		});		
		chckbxD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setDerivativo(chckbxD.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		
		
		
		
		chckbxTensCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dados.setTensao(chckbxTensCalc.isSelected());
				thread.setDadosGrafico(dados);}
		});
		chckbxTensaoSat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setTensaoSat(chckbxTensaoSat.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		
		lblExibirCheckSinalGrafico1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!chckbxTensaoSat.isVisible()){
					lblExibirCheckSinalGrafico1.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Delete-32.png")));
					chckbxTensCalc.setVisible(true);
					chckbxTensaoSat.setVisible(true);
					
					//chckbxControle.setVisible(true);
					chckbxP.setVisible(true);
					chckbxI.setVisible(true);
					chckbxD.setVisible(true);
				}else{
					lblExibirCheckSinalGrafico1.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Add-32.png")));
					chckbxTensCalc.setVisible(false);
					chckbxTensaoSat.setVisible(false);
					
				//	chckbxControle.setVisible(false);
					chckbxP.setVisible(false);
					chckbxI.setVisible(false);
					chckbxD.setVisible(false);
				}
			}
		});
		
		lblExibirCheckSinalGrafico2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!chckbxNivTanque1.isVisible()){
					lblExibirCheckSinalGrafico2.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Delete-32.png")));
					chckbxNivTanque1.setVisible(true);
					chckbxNivTanque2.setVisible(true);
					chckbxErro.setVisible(true);
					chckbxSetPoint.setVisible(true);
					
				}else{
					lblExibirCheckSinalGrafico2.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Add-32.png")));
					chckbxSetPoint.setVisible(false);
					chckbxNivTanque1.setVisible(false);
					chckbxNivTanque2.setVisible(false);
					chckbxErro.setVisible(false);
				}
			}
		});
		chckbxNivTanque2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setNivel2(chckbxNivTanque2.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		
		
		chckbxNivTanque1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setNivel1(chckbxNivTanque1.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		
		chckbxErro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setErroMesmo(chckbxErro.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		
		chckbxSetPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setSetPoint(chckbxSetPoint.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		
	}
	
	private void inicializaPainelCheckSinaisGraficos(){		
		
		inicializaCheckSinaisGrafico1();
		
		inicializaCheckSinaisGrafico2();
	}
	
	private void desabilitarParamsControle(){		
		kpMestre.setText("");
		kiMestre.setText("");
		kdMestre.setText("");
		taliMestre.setText("");
		taldMestre.setText("");
		
		kpMestre.setEnabled(false);
		kiMestre.setEnabled(false);
		kdMestre.setEnabled(false);
		taliMestre.setEnabled(false);
		taldMestre.setEnabled(false);
	}
	
	private void habilitarComponentesPainelTipoMalha(Boolean bool){
		rdbtnAberta.setEnabled(bool);
		rdbtnFechada.setEnabled(bool);
		
		if(!bool){
			rdbtnAberta.setSelected(bool);
			rdbtnFechada.setSelected(bool);
		}
	}
}
