import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Color;

import javax.swing.JRadioButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JSpinner;


import javax.swing.JComboBox;
import javax.swing.SpinnerNumberModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JCheckBox;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import javax.swing.JTextPane;

public class Tela extends TelaGeral{

	private JFrame frame;
	private JPanel panelTipoSinal;
	private JPanel panelGraficos;
	private JLayeredPane panelGrafico1;
	private JLayeredPane panelGrafico2;
	private JPanel panelOpcoesEntrada;
	private JPanel panelIO;
	private JPanel panelTipoMalha;
	private JPanel panelDadosSinal;
	private JPanel panelParamsControlador;
	private JPanel panelBombas;
	
	private Tanque thread;
	private Dados dados;
	
	private JTextField IPServidor;
	private JTextFieldAlterado Porta;
	
	private JCheckBox chckbxTensaoSat;
	private JCheckBox chckbxTensCalc;	
	private JCheckBox chckbxNivTanque1;
	private JCheckBox chckbxNivTanque2;
	private JCheckBox chckbxSetPoint;
	private JCheckBox chckbxErro;
	private JCheckBox chckbxControle; 
	
	private JCheckBox chckbxP, chckbxI, chckbxD;
	
	private JLabel lblExibirCheckSinalGrafico1;
	private JLabel lblPeriodo;
	private JLabel lblExibirCheckSinalGrafico2;
	private JLabel lblAmplitude;
	private JLabel lblAmplitudeMin;
	
	@SuppressWarnings("rawtypes")
	private JComboBox leitura1;
	@SuppressWarnings("rawtypes")
	private JComboBox leitura2;
	@SuppressWarnings("rawtypes")
	private JComboBox escrita;
	
	private JSpinner amplitudeMin;
	private JSpinner amplitude;
	private JSpinner periodo;
	private JSpinner periodoMin;
	private JSpinner offSet;
	
	JRadioButton rdbtnTanque2;
	JRadioButton rdbtnTanque1;
	
	private JRadioButton rdbtnAberta;
	private JRadioButton rdbtnFechada;
	
	private JRadioButton rdbtnDegrau;
	private JRadioButton rdbtnSenoidal;
	private JRadioButton rdbtnQuadrada;
	private JRadioButton rdbtnDenteSerra;
	private JRadioButton rdbtnAleatorio;
	
	private JButton botaoAtualizar;
	private JButton btnReset;
	private JPanel panelTipoControlador;
	private JTextField textFieldKp;
	private JTextField textFieldKi;
	private JTextField textFieldTali;
	private JTextField textFieldKd;
	private JTextField textFieldTald;
	
	private JRadioButton rdbtnControladorP;
	private JRadioButton rdbtnControladorPI;
	private JRadioButton rdbtnControladorPD;
	private JRadioButton rdbtnControladorPID;
	private JRadioButton rdbtnControladorPITracoD;
	
	private JLabel lblKp;
	private JLabel lblKi;
	private JLabel lblKd;	
	private JLabel labelInterrogation;
	
	private JCheckBox chckbxWindUp;
	@SuppressWarnings("rawtypes")
	private JComboBox comboTipoOnda;
	private JCheckBox chckbxComControle;
	
	@SuppressWarnings("rawtypes")
	private JComboBox comboTipoControlador;
	
	private JPanel panelDadosServidor;
	private JTextField textFieldTt;
	
	private JPanel panelMp, panelTs, panelValores;
	
	private JRadioButton rdbtnTempoSubida1, rdbtnTempoSubida2, rdbtnTempoSubida3;
	
	private JRadioButton rdbtnPorcentagem, rdbtnAbs;
	private JSpinner spinnerTs;
	
	private JTextPane textPaneTr, textPaneMp, textPaneTp, textPaneTs;
	
	public String onda_limpa_tanque;
	public double amplitude_limpa_tanque;
	
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
				window.frame.setVisible(true);
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
		
		frame = new JFrame();
		frame.getContentPane().setLocation(0, -113);
		frame.setBounds(100, 100, 1071, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		inicializarPainelDadosServidor();
		frame.getContentPane().add(panelDadosServidor);
		
		inicializeBotõesPainelPrincipal();
		
		inicializePainelOpcoesEntrada();
		
		inicializaPainelGraficos();
		frame.getContentPane().add(panelGraficos);
		
		inicializePaineisParametrosSinal();
		
		inicializaPainelCheckSinaisGraficos();
		
//		redimensionarPainelGrafico2(panelGrafico1, panelGrafico2);
	}
	
	private void inicializePaineisParametrosSinal(){
		//Inicializando Painel Tr
		JPanel panelTr = new JPanel();
		panelTr.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tempo de Subida (Tr)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelTr.setBounds(333, 513, 122, 117);		
		panelTr.setLayout(null);
		frame.getContentPane().add(panelTr);
		
		rdbtnTempoSubida1 = new JRadioButton("0 - 100%");
		rdbtnTempoSubida1.setEnabled(false);
		rdbtnTempoSubida1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnTempoSubida2.setSelected(false);
				rdbtnTempoSubida3.setSelected(false);
			}
		});
		rdbtnTempoSubida1.setBounds(18, 32, 76, 23);
		panelTr.add(rdbtnTempoSubida1);
		
		rdbtnTempoSubida2 = new JRadioButton("5 - 95%");
		rdbtnTempoSubida2.setEnabled(false);
		rdbtnTempoSubida2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnTempoSubida1.setSelected(false);
				rdbtnTempoSubida3.setSelected(false);
			}
		});
		rdbtnTempoSubida2.setBounds(18, 61, 76, 23);
		panelTr.add(rdbtnTempoSubida2);
		
		rdbtnTempoSubida3 = new JRadioButton("10 - 90%");
		rdbtnTempoSubida3.setEnabled(false);
		rdbtnTempoSubida3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnTempoSubida1.setSelected(false);
				rdbtnTempoSubida2.setSelected(false);
			}
		});
		rdbtnTempoSubida3.setBounds(18, 87, 76, 23);
		panelTr.add(rdbtnTempoSubida3);
		
		//Inicializando Painel Mp
		panelMp = new JPanel();
		panelMp.setLayout(null);
		panelMp.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "M\u00E1x. Sobre-Sinal Percentual (Mp)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelMp.setBounds(465, 513, 199, 59);
		frame.getContentPane().add(panelMp);
		
		rdbtnPorcentagem = new JRadioButton("Porcentagem");
		rdbtnPorcentagem.setEnabled(false);
		rdbtnPorcentagem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnAbs.setSelected(false);
			}
		});
		rdbtnPorcentagem.setBounds(21, 29, 89, 23);
		panelMp.add(rdbtnPorcentagem);
		
		rdbtnAbs = new JRadioButton("ABS");
		rdbtnAbs.setEnabled(false);
		rdbtnAbs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnPorcentagem.setSelected(false);
			}
		});
		rdbtnAbs.setBounds(131, 29, 45, 23);
		panelMp.add(rdbtnAbs);
		
		//Inicializando Painel Ts
		panelTs = new JPanel();
		panelTs.setLayout(null);
		panelTs.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tempo de Acomoda\u00E7\u00E3o (Ts)", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelTs.setBounds(465, 571, 199, 59);
		frame.getContentPane().add(panelTs);
		
		JLabel lblTsPorcentagem = new JLabel("Ts (%):");
		lblTsPorcentagem.setBounds(31, 28, 56, 20);
		panelTs.add(lblTsPorcentagem);
		
		spinnerTs = new JSpinner();
		spinnerTs.setEnabled(false);
		spinnerTs.setModel(new SpinnerNumberModel(new Integer(5), null, null, new Integer(1)));
		spinnerTs.setBounds(87, 28, 56, 20);
		panelTs.add(spinnerTs);
		
		//Inicializando Painel Painel de exibição dos Valores
		panelValores = new JPanel();
		panelValores.setBorder(new TitledBorder(null, "Valores Atuais", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelValores.setBounds(670, 513, 375, 117);
		frame.getContentPane().add(panelValores);
		panelValores.setLayout(null);
		
		JLabel lblTr = new JLabel("Tr:");
		lblTr.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTr.setBounds(31, 26, 24, 36);
		panelValores.add(lblTr);
		
		textPaneTr = new JTextPane();
		textPaneTr.setBounds(59, 26, 101, 36);
		textPaneTr.setEditable(false);
		panelValores.add(textPaneTr);
		
		JLabel lblMp = new JLabel("Mp:");
		lblMp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMp.setBounds(25, 70, 30, 36);
		panelValores.add(lblMp);
		
		textPaneMp = new JTextPane();
		textPaneMp.setBounds(59, 70, 101, 36);
		textPaneMp.setEditable(false);
		panelValores.add(textPaneMp);
		
		JLabel lblTp = new JLabel("Tp:");
		lblTp.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTp.setBounds(194, 26, 28, 36);
		panelValores.add(lblTp);
		
		textPaneTp = new JTextPane();
		textPaneTp.setBounds(225, 26, 101, 36);
		textPaneTp.setEditable(false);
		panelValores.add(textPaneTp);
		
		JLabel lblTs = new JLabel("Ts:");
		lblTs.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTs.setBounds(194, 70, 28, 36);
		panelValores.add(lblTs);
		
		textPaneTs = new JTextPane();
		textPaneTs.setBounds(225, 70, 101, 36);
		textPaneTs.setEditable(false);
		panelValores.add(textPaneTs);		
	}
	
	private void inicializarPainelDadosServidor(){
		panelDadosServidor = new JPanel();
		panelDadosServidor.setBounds(6, 11, 327, 73);
		panelDadosServidor.setBorder(new TitledBorder(null, "Dados do Servidor", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelDadosServidor.setLayout(null);
		
		JLabel lblIPServidor = new JLabel("IP do Servidor:");
		lblIPServidor.setBounds(10, 17, 88, 20);
		panelDadosServidor.add(lblIPServidor);
		
		IPServidor = new JTextField();
		IPServidor.setColumns(10);
		IPServidor.setBounds(87, 17, 112, 20);
		panelDadosServidor.add(IPServidor);
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(10, 45, 46, 14);
		panelDadosServidor.add(lblPorta);
		
		Porta = new JTextFieldAlterado();
		Porta.setBounds(87, 42, 112, 20);
		panelDadosServidor.add(Porta);
		Porta.setColumns(10);
		
		final JButton btnConectarDesconectar = new JButton("Conectar");
		btnConectarDesconectar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269447_gtk-apply.png")));
		btnConectarDesconectar.setForeground(new Color(0, 128, 0));
		btnConectarDesconectar.setBackground(new Color(0, 128, 0));
		btnConectarDesconectar.setBounds(203, 16, 112, 46);
		btnConectarDesconectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(IPServidor.getText().equals("") || Porta.getText().equals("")){
					JOptionPane.showMessageDialog(frame, "                          Conexão não Realizada! " +
							"\nInforme o Ip do Servidor e/ou a Porta e tente novamente.");
				}else{
					thread = new Tanque();
					thread.setServer(IPServidor.getText(), Integer.parseInt(Porta.getText()));
					
					//Muda nome (conectar ou desconectar) e cor (verde ou vermelho) do botão.
					//Também desabilita alguns componentes da tela.			
					if(btnConectarDesconectar.getText().equals("Conectar")){
						JOptionPane.showMessageDialog(frame, "Conexão Realizada com Sucesso!");
												
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
	}
	
	/** 
	 * Valida o tanque que será controlado. Depois, Popula os parâmetros de Leitura e Escrita na classe Dados.
	 */
	public boolean validaTanque(){
		if(!rdbtnTanque1.isSelected() && !rdbtnTanque2.isSelected()){
			JOptionPane.showMessageDialog(frame, "Informe a planta que você deseja controlar.");
		
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
		if(leitura1.getSelectedIndex() == 0){
				JOptionPane.showMessageDialog(frame, "Informe o porta de Leitura 1.");
			dados.setPinoDeLeitura1((int)((Integer)leitura1.getSelectedItem()));
			return false;
		}else{
			dados.setPinoDeLeitura1((int)((Integer)leitura1.getSelectedItem()));
		}
		
		if(leitura2.getSelectedIndex() == 0){
		
			JOptionPane.showMessageDialog(frame, "Informe o porta de Leitura 2.");
			dados.setPinoDeLeitura2((int)((Integer)leitura2.getSelectedItem()));
			return false;
		}else{
			dados.setPinoDeLeitura2((int)((Integer)leitura2.getSelectedItem()));
		}
			
		if(escrita.getSelectedIndex() == 0){
			JOptionPane.showMessageDialog(frame, "Informe a porta de Escrita.");
			return false;
		}else{
			dados.setPinoDeEscrita((int)((Integer)escrita.getSelectedItem()));
		}
		
		return true;
	}
	
	/** 
	 * Valida campos e Popula o tipo de malha na classe Dados.
	 */
	public boolean validaTipoMalha(){
		if(!rdbtnAberta.isSelected() && !rdbtnFechada.isSelected()){
			JOptionPane.showMessageDialog(frame, "Informe o Tipo de Malha.");
			
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
			JOptionPane.showMessageDialog(frame, "Informe o Tipo de Onda.");
			
			return false;
		}else {
			dados.setTipoSinal(comboTipoOnda.getSelectedItem().toString());
			
			if(amplitude.getValue().equals("")){
				String amplitude = comboTipoOnda.getSelectedItem().equals("Degrau") ? "Amplitude (Máx)" : "Amplitude";  

				JOptionPane.showMessageDialog(frame, "Informe a " + amplitude + " do sinal.");
				
				return false;
			}else{
				dados.setAmplitude((double)amplitude.getValue());
			}
			
			if(comboTipoOnda.getSelectedItem().equals("Quadrada") || 
					comboTipoOnda.getSelectedItem().equals("Senoidal") || comboTipoOnda.getSelectedItem().equals("Dente de Serra")){
				
				if(periodo.getValue().equals("")){
					JOptionPane.showMessageDialog(frame, "Informe o Período do sinal.");
					
					return false;
				}else{
					dados.setPeriodo((double)periodo.getValue());
				}
				
				if(offSet.getValue().equals("")){
					JOptionPane.showMessageDialog(frame, "Informe a OffSet do sinal.");
					
					return false;
				}else{
					dados.setOffset((double)offSet.getValue());
				}
			}
			
			if(comboTipoOnda.getSelectedItem().equals("Aleatória")){
				
				if(amplitudeMin.getValue().equals("")){
					JOptionPane.showMessageDialog(frame, "Informe a Amplitude (Mín) do sinal.");
				
					return false;
				}else{
					dados.setAmplitudeMinima(((double) amplitudeMin.getValue()));
				}

				if(periodo.getValue().equals("")){
					JOptionPane.showMessageDialog(frame, "Informe o Período do sinal.");
				
					return false;
				}else{
					dados.setPeriodo((double)periodo.getValue());
				}
				
				if(periodoMin.getValue().equals("")){
					JOptionPane.showMessageDialog(frame, "Informe o Período (Mín) do sinal.");
					
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
				JOptionPane.showMessageDialog(frame, "Informe o tipo de controlador!");
				
				return false;
			}else{
				dados.setTipoDeControle(tipoControlador.getSelectedItem().toString());
				
				if(textFieldKp.getText().equals("")){
					JOptionPane.showMessageDialog(frame, "Informe o valor de Kp.");
					
					return false;
				}else{
					dados.setKP(Double.parseDouble(textFieldKp.getText()));
					
					if((tipoControlador.getSelectedItem().equals("PI") || tipoControlador.getSelectedItem().equals("PID") || tipoControlador.getSelectedItem().equals("PI-D"))
							&& (textFieldKi.getText().equals("") || textFieldTali.getText().equals(""))){
						
						JOptionPane.showMessageDialog(frame, "Informe todos os parâmetros do controlador integrativo (Ki e Ti).");
						
						return false;
					}else if((tipoControlador.getSelectedItem().equals("PI") || tipoControlador.getSelectedItem().equals("PID") || tipoControlador.getSelectedItem().equals("PI-D"))
							&& !(textFieldKi.getText().equals("") || textFieldTali.getText().equals(""))){
				
						dados.setKI(Double.parseDouble(textFieldKi.getText()));
					}
					
					if((tipoControlador.getSelectedItem().equals("PD") || tipoControlador.getSelectedItem().equals("PID") || tipoControlador.getSelectedItem().equals("PI-D"))
							&& (textFieldKd.getText().equals("") || textFieldTald.getText().equals(""))){
						
						JOptionPane.showMessageDialog(frame, "Informe todos os parâmetros do controlador derivativo (Kd e Td).");
						
						return false;
					}else if((tipoControlador.getSelectedItem().equals("PD") || tipoControlador.getSelectedItem().equals("PID") || tipoControlador.getSelectedItem().equals("PI-D"))
							&& !(textFieldKd.getText().equals("") || textFieldTald.getText().equals(""))){
						
						dados.setKD(Double.parseDouble(textFieldKd.getText()));
					}
				}
			}
		}
		
		return true;
	}
	
	private void inicializePainelOpcoesTanque(){
		panelBombas = new JPanel();
		panelBombas.setBounds(5, 5, 151, 110);
		panelBombas.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Opções de Tanque", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panelBombas.setLayout(null);
		
		rdbtnTanque1 = new JRadioButton("Tanque 1");
		rdbtnTanque1.setBounds(29, 35, 71, 23);
		rdbtnTanque1.setEnabled(false);
		rdbtnTanque1.setSelected(false);
		rdbtnTanque1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnTanque2.setSelected(false);
			}
		});
		panelBombas.add(rdbtnTanque1);
		
		rdbtnTanque2 = new JRadioButton("Tanque 2");
		rdbtnTanque2.setBounds(29, 65, 71, 23);
		rdbtnTanque2.setEnabled(false);
		rdbtnTanque2.setSelected(false);
		rdbtnTanque2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnTanque1.setSelected(false);
			}
		});
		panelBombas.add(rdbtnTanque2);
	}
	
	private void inicializePainelOpcoesEntrada(){
		JTabbedPane abas = new JTabbedPane(JTabbedPane.TOP);
		abas.setBounds(6, 86, 327, 544);
		frame.getContentPane().add(abas);
		
		panelOpcoesEntrada = new JPanel();
		abas.addTab("Opções de Entrada", null, panelOpcoesEntrada, null);
		panelOpcoesEntrada.setLayout(null);
		
		inicializarPainelEntradasSaidas();
		panelOpcoesEntrada.add(panelIO);
		
		inicializePainelOpcoesTanque();
		panelOpcoesEntrada.add(panelBombas);
		
		inicializarPainelTiposMalha();
		panelOpcoesEntrada.add(panelTipoMalha);
		
		inicializarPainelDadosSinal();
		panelOpcoesEntrada.add(panelDadosSinal);
		
//		inicializarPainelTipoControlador();
//		panelOpcoesEntrada.add(panelTipoControlador);
		
		inicializarPainelParamsControlador();
		panelOpcoesEntrada.add(panelParamsControlador);		
		
		inicializarOutrosComponentesPainelOpcoesEntrada();
		panelOpcoesEntrada.add(comboTipoOnda);
		panelOpcoesEntrada.add(chckbxComControle);
		panelOpcoesEntrada.add(chckbxWindUp);
		panelOpcoesEntrada.add(comboTipoControlador);		
		botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(32, 462, 101, 43);
		panelOpcoesEntrada.add(botaoAtualizar);
		botaoAtualizar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269378_gtk-refresh.png")));		
		botaoAtualizar.setEnabled(false);
		
		btnReset = new JButton("Reset");
		btnReset.setBounds(173, 462, 101, 43);
		panelOpcoesEntrada.add(btnReset);
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
				
				if(validaDadosDeIO() && validaTipoMalha() && validaOnda() && validaParamsControlador(comboTipoControlador)){
					
					dados.setComControle(chckbxComControle.isSelected());
					
					thread.graficoAltura.limparFilaDeErroMesmo();
					thread.graficoAltura.limparFilaDeNivelDois();
					thread.graficoAltura.limparFilaDeNivelUm();
					thread.grafico.limparFilaDeSetPoint();
					thread.grafico.limparFilaDeVP();
					thread.grafico.limparFilaDeP();
					thread.grafico.limparFilaDeI();
					thread.grafico.limparFilaDeD();
					
					
					// Setar na dados os checkBox dos gráficos
					dados.setTensao(chckbxTensCalc.isSelected());
					dados.setTensaoSat(chckbxTensaoSat.isSelected());
					dados.setSetPoint(chckbxSetPoint.isSelected());
					dados.setErroMesmo(chckbxErro.isSelected());
					dados.setErro(chckbxControle.isSelected());
					dados.setProporcional(chckbxP.isSelected());
					dados.setIntegral(chckbxI.isSelected());
					dados.setDerivativo(chckbxD.isSelected());
					dados.setNivel1(chckbxNivTanque1.isSelected());
					dados.setWindUP(chckbxWindUp.isSelected());
					
					dados.settPico(textPaneTp);
					dados.settAcomoda(textPaneTs);
					dados.settSubida(textPaneTr);
					dados.setNivelPico(textPaneMp);
					
					dados.setPicoAbs(rdbtnAbs.isSelected());
					
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
						dados.setTt(Double.parseDouble(textFieldTt.getText()));
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void inicializarPainelEntradasSaidas(){
		panelIO = new JPanel();
		panelIO.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Entradas e Sa\u00EDdas", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panelIO.setBounds(5, 5, 185, 110);
		panelIO.setLayout(null);
		
		JLabel lblLeitura1 = new JLabel("Leitura 1:");
		lblLeitura1.setBounds(25, 24, 50, 14);
		panelIO.add(lblLeitura1);
		
		leitura1 = new JComboBox(getItensComboLeituraEscrita());
		leitura1.setEnabled(false);
		leitura1.setBounds(80, 21, 70, 20);
		panelIO.add(leitura1);
		
		JLabel lblLeitura2 = new JLabel("Leitura 2:");
		lblLeitura2.setBounds(25, 56, 50, 14);
		panelIO.add(lblLeitura2);
		
		leitura2 = new JComboBox(getItensComboLeituraEscrita());
		leitura2.setEnabled(false);
		leitura2.setBounds(80, 50, 70, 20);
		panelIO.add(leitura2);
		
		JLabel lblEscrita = new JLabel("Escrita:");
		lblEscrita.setBounds(25, 85, 50, 14);
		panelIO.add(lblEscrita);
		
		escrita = new JComboBox(getItensComboLeituraEscrita());
		escrita.setEnabled(false);
		escrita.setBounds(80, 79, 70, 20);
		panelIO.add(escrita);
	}
	
	private void inicializarPainelTiposMalha(){
		panelTipoMalha = new JPanel();
		panelTipoMalha.setBounds(195, 5, 124, 110);
		panelTipoMalha.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de Malha", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panelTipoMalha.setLayout(null);	
				
		rdbtnAberta = new JRadioButton("Aberta");
		rdbtnAberta.setEnabled(false);
		rdbtnAberta.setBounds(28, 35, 67, 23);		
		rdbtnAberta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnFechada.setSelected(false);
				
				comboTipoControlador.setEnabled(false);
				comboTipoControlador.setSelectedIndex(0);
				
				chckbxWindUp.setEnabled(false);
				chckbxWindUp.setSelected(false);
				
				chckbxComControle.setEnabled(false);
				chckbxComControle.setSelected(false);
				
				desabilitarParamsControle();
			}
		});
		panelTipoMalha.add(rdbtnAberta);
		
		rdbtnFechada = new JRadioButton("Fechada");
		rdbtnFechada.setEnabled(false);
		rdbtnFechada.setBounds(28, 65, 68, 23);		
		rdbtnFechada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnAberta.setSelected(false);
			
				chckbxWindUp.setEnabled(true);
				chckbxComControle.setEnabled(true);
			}
		});
		panelTipoMalha.add(rdbtnFechada);
	}
	
	private void inicializarPainelDadosSinal(){
		panelDadosSinal = new JPanel();
		panelDadosSinal.setBounds(5, 150, 314, 115);		
		panelDadosSinal.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Par\u00E2metros do Sinal", TitledBorder.RIGHT, TitledBorder.TOP, null, Color.GRAY));
		panelDadosSinal.setLayout(null);
		
		lblAmplitude = new JLabel("Amplitude:");
		lblAmplitude.setBounds(9, 27, 84, 20);
		panelDadosSinal.add(lblAmplitude);
		
		amplitude = new JSpinner();
		amplitude.setEnabled(false);
		amplitude.setBounds(94, 27, 51, 20);
		//amplitude.setModel(new SpinnerNumberModel(0.0, -4.0, 4.0, 0.0));
		amplitude.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		panelDadosSinal.add(amplitude);
		
		lblAmplitudeMin = new JLabel("Amplitude (M\u00EDn):");
		lblAmplitudeMin.setBounds(155, 27, 85, 20);
		panelDadosSinal.add(lblAmplitudeMin);
		
		amplitudeMin = new JSpinner();
		amplitudeMin.setEnabled(false);
		amplitudeMin.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		amplitudeMin.setBounds(256, 27, 51, 20);
		panelDadosSinal.add(amplitudeMin);
		
		lblPeriodo = new JLabel("Per\u00EDodo:");
		lblPeriodo.setBounds(8, 56, 85, 20);
		panelDadosSinal.add(lblPeriodo);
		
		periodo = new JSpinner();
		periodo.setEnabled(false);
		periodo.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		periodo.setBounds(94, 56, 51, 20);
		panelDadosSinal.add(periodo);
		
		JLabel lblPeriodoMin = new JLabel("Per\u00EDodo (M\u00EDn):");
		lblPeriodoMin.setBounds(155, 56, 85, 20);
		panelDadosSinal.add(lblPeriodoMin);
		
		periodoMin = new JSpinner();
		periodoMin.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		periodoMin.setEnabled(false);
		periodoMin.setBounds(256, 56, 51, 20);
		panelDadosSinal.add(periodoMin);
		
		JLabel lblOffSet = new JLabel("Off-Set:");
		lblOffSet.setBounds(8, 85, 67, 20);
		panelDadosSinal.add(lblOffSet);
		
		offSet = new JSpinner();
		offSet.setBounds(94, 85, 51, 20);
		offSet.setEnabled(false);
		offSet.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		panelDadosSinal.add(offSet);		
	}
		
	private void inicializarPainelParamsControlador(){
		panelParamsControlador = new JPanel();
		panelParamsControlador.setBounds(5, 335, 314, 103);
		panelParamsControlador.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Par\u00E2metros do Controlador", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(128, 128, 128)));
		panelParamsControlador.setLayout(null);
		
		lblKp = new JLabel("Kp:");
		lblKp.setBounds(31, 26, 22, 14);
		panelParamsControlador.add(lblKp);
		
		textFieldKp = new JTextField();
		textFieldKp.setEnabled(false);
		textFieldKp.setBounds(57, 20, 66, 20);
		textFieldKp.setColumns(10);
		panelParamsControlador.add(textFieldKp);		
		
		lblKi = new JLabel("Ki:");
		lblKi.setBounds(31, 51, 22, 14);
		panelParamsControlador.add(lblKi);
		
		textFieldKi = new JTextField();
		textFieldKi.setEnabled(false);
		textFieldKi.setColumns(10);
		textFieldKi.setBounds(57, 45, 66, 20);	
		panelParamsControlador.add(textFieldKi);
		textFieldKi.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				try{
					textFieldTali.setText("" + Double.parseDouble(textFieldKp.getText())
							/Double.parseDouble(textFieldKi.getText()));
				}catch (Exception e){}
			}
		});	
		
		JLabel lblTali = new JLabel("\u03C4i:");
		lblTali.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		lblTali.setBounds(163, 51, 23, 14);
		panelParamsControlador.add(lblTali);
		
		textFieldTali = new JTextField();
		textFieldTali.setEnabled(false);
		textFieldTali.setColumns(10);
		textFieldTali.setBounds(196, 45, 66, 20);		
		textFieldTali.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				
				try{
					textFieldKi.setText("" + Double.parseDouble(textFieldKp.getText())
							/Double.parseDouble(textFieldTali.getText()));
				}catch(Exception e){}
			}
		});
		panelParamsControlador.add(textFieldTali);
		
		lblKd = new JLabel("Kd:");
		lblKd.setBounds(31, 77, 22, 14);
		panelParamsControlador.add(lblKd);
		
		textFieldKd = new JTextField();
		textFieldKd.setEnabled(false);
		textFieldKd.setColumns(10);
		textFieldKd.setBounds(57, 71, 66, 20);
		textFieldKd.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				try {
					textFieldTald.setText("" + Double.parseDouble(textFieldKd.getText())
							/Double.parseDouble(textFieldKp.getText()));
				} catch (Exception e) {	}
			}
		});
		panelParamsControlador.add(textFieldKd);
		
		JLabel lblTald = new JLabel("\u03C4d:");
		lblTald.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		lblTald.setBounds(163, 77, 23, 14);
		panelParamsControlador.add(lblTald);
		
		textFieldTald = new JTextField();
		textFieldTald.setEnabled(false);
		textFieldTald.setColumns(10);
		textFieldTald.setBounds(196, 71, 66, 20);
		textFieldTald.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				try {
					textFieldKd.setText("" + Double.parseDouble(textFieldKp.getText())
							*Double.parseDouble(textFieldTald.getText()));
				} catch (Exception e) {}
			}
		});
		panelParamsControlador.add(textFieldTald);
		
		textFieldTt = new JTextField();
		textFieldTt.setEnabled(false);
		textFieldTt.setBounds(196, 20, 66, 20);
		panelParamsControlador.add(textFieldTt);
		textFieldTt.setColumns(10);
		
		JLabel lblTt = new JLabel("Tt:");
		lblTt.setBounds(164, 20, 22, 20);
		panelParamsControlador.add(lblTt);
		
		labelInterrogation = new JLabel("");
		labelInterrogation.setToolTipText("Para Atualizar os parametros, basta clicar nos campos.");
		labelInterrogation.setIcon(new ImageIcon(Tela.class.getResource("/Icons/question-icon.png")));
		labelInterrogation.setBounds(282, 11, 24, 26);
		panelParamsControlador.add(labelInterrogation);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void inicializarOutrosComponentesPainelOpcoesEntrada(){
		chckbxComControle = new JCheckBox("Acionar Controlador");
		chckbxComControle.setBounds(28, 272, 124, 23);
		chckbxComControle.setEnabled(false);
		chckbxComControle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxComControle.isSelected()){
					comboTipoControlador.setEnabled(true);
				}else{
					comboTipoControlador.setEnabled(false);
					comboTipoControlador.setSelectedIndex(0);
					desabilitarParamsControle();
				}
			}
		});
		
		chckbxWindUp = new JCheckBox("Acionar Wind Up");
		chckbxWindUp.setBounds(177, 272, 113, 23);
		chckbxWindUp.setEnabled(false);
		chckbxWindUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxWindUp.isSelected()){
					textFieldTt.setEnabled(true);
				}else{ textFieldTt.setEnabled(false);}
			}
		});
		
		JLabel lblTipoOnda = new JLabel("Tipo de Onda:");
		lblTipoOnda.setBounds(49, 120, 78, 23);
		panelOpcoesEntrada.add(lblTipoOnda);
		
		comboTipoOnda = new JComboBox(getItensComboTiposOnda());
		comboTipoOnda.setEnabled(false);
		comboTipoOnda.setBounds(128, 120, 151, 23);
		comboTipoOnda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboTipoOnda.getSelectedItem().equals("Selecione")){
					lblAmplitude.setText("Amplitude:");	
					amplitude.setEnabled(false);
					amplitudeMin.setEnabled(false);
					amplitudeMin.setValue(0);
					lblPeriodo.setText("Período:");
					periodo.setEnabled(false);
					periodo.setValue(0);
					periodoMin.setEnabled(false);
					periodoMin.setValue(0);
					offSet.setEnabled(false);
					offSet.setValue(0);
				}else if(comboTipoOnda.getSelectedItem().equals("Quadrada")){
					lblAmplitude.setText("Amplitude:");
					amplitude.setEnabled(true);
					amplitudeMin.setEnabled(false);
					amplitudeMin.setValue(0);
					lblPeriodo.setText("Período:");
					periodo.setEnabled(true);
					periodoMin.setEnabled(false);
					periodoMin.setValue(0);
					offSet.setEnabled(true);
				}else if(comboTipoOnda.getSelectedItem().equals("Degrau")){
					lblAmplitude.setText("Amplitude:");	
					amplitude.setEnabled(true);
					amplitudeMin.setEnabled(false);
					amplitudeMin.setValue(0);
					lblPeriodo.setText("Período:");
					periodo.setEnabled(false);
					periodo.setValue(0);
					periodoMin.setEnabled(false);
					periodoMin.setValue(0);
					offSet.setEnabled(false);
					offSet.setValue(0);
				}else if(comboTipoOnda.getSelectedItem().equals("Aleatória")){
					lblAmplitude.setText("Amplitude (Máx):");
					amplitude.setEnabled(true);
					amplitudeMin.setEnabled(true);
					lblPeriodo.setText("Período (Máx):");
					periodo.setEnabled(true);
					periodoMin.setEnabled(true);
					offSet.setEnabled(false);
					offSet.setValue(0);
				}else if(comboTipoOnda.getSelectedItem().equals("Senoidal")){
					lblAmplitude.setText("Amplitude:");	
					amplitude.setEnabled(true);
					amplitudeMin.setEnabled(false);
					amplitudeMin.setValue(0);
					lblPeriodo.setText("Período:");
					periodo.setEnabled(true);
					periodoMin.setEnabled(false);
					periodoMin.setValue(0);
					offSet.setEnabled(true);
				}else{
					lblAmplitude.setText("Amplitude:");
					amplitude.setEnabled(true);
					amplitudeMin.setEnabled(false);
					amplitudeMin.setValue(0);
					lblPeriodo.setText("Período:");
					periodo.setEnabled(true);
					periodoMin.setEnabled(false);
					periodoMin.setValue(0);
					offSet.setEnabled(true);	
				}
			}
		});
		
		JLabel lblTipoControlador = new JLabel("Tipo do Controlador:");
		lblTipoControlador.setBounds(32, 303, 100, 23);
		panelOpcoesEntrada.add(lblTipoControlador);
		
		comboTipoControlador = new JComboBox(getItensComboTiposControle());
		comboTipoControlador.setBounds(145, 303, 151, 23);
		comboTipoControlador.setEnabled(false);
		comboTipoControlador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboTipoControlador.getSelectedItem().equals("Selecione")){
					textFieldKp.setEnabled(false);
					textFieldKp.setText("");
					textFieldKi.setEnabled(false);
					textFieldKi.setText("");
					textFieldKd.setEnabled(false);
					textFieldKd.setText("");
					textFieldTali.setEnabled(false);
					textFieldTali.setText("");
					textFieldTald.setEnabled(false);
					textFieldTald.setText("");
				}else if(comboTipoControlador.getSelectedItem().equals("P")){
					textFieldKp.setEnabled(true);
					textFieldKi.setEnabled(false);
					textFieldKi.setText("");
					textFieldKd.setEnabled(false);
					textFieldKd.setText("");
					textFieldTali.setEnabled(false);
					textFieldTali.setText("");
					textFieldTald.setEnabled(false);
					textFieldTald.setText("");
					
					//KP = Double.parseDouble(textFieldKp.getText());
					//dados.setKP(KP);
				}else if(comboTipoControlador.getSelectedItem().equals("PI")){
					textFieldKp.setEnabled(true);
					textFieldKi.setEnabled(true);
					textFieldKd.setEnabled(false);
					textFieldKd.setText("");
					textFieldTali.setEnabled(true);
					textFieldTald.setEnabled(false);
					textFieldTald.setText("");
				}else if(comboTipoControlador.getSelectedItem().equals("PD")){					
					textFieldKp.setEnabled(true);
					textFieldKi.setEnabled(false);
					textFieldKi.setText("");
					textFieldKd.setEnabled(true);
					textFieldTali.setEnabled(false);
					textFieldTali.setText("");
					textFieldTald.setEnabled(true);
				}else if(comboTipoControlador.getSelectedItem().equals("PID")){
					textFieldKp.setEnabled(true);
					textFieldKi.setEnabled(true);
					textFieldKd.setEnabled(true);
					textFieldTali.setEnabled(true);
					textFieldTald.setEnabled(true);				
				}else{
					textFieldKp.setEnabled(true);
					textFieldKi.setEnabled(true);
					textFieldKd.setEnabled(true);
					textFieldTali.setEnabled(true);
					textFieldTald.setEnabled(true);
				}
			}
		});
	}
	
	private void inicializaPainelGraficos(){
		panelGraficos = new JPanel();
		panelGraficos.setBounds(343, 0, 712, 502);
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
		panelGrafico1.setBounds(8, 18, 586, 235);
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
		panelGrafico2.setBounds(8, 256, 586, 235);
	}
	
	public void mudarPropriedadesBotoes(String acao){
		if(acao.equals("Conectar")){
			IPServidor.setEditable(false);
			Porta.setEditable(false);
			
			habilitarComponentesPainelTipoMalha(true);
			
			leitura1.setEnabled(true);
			leitura2.setEnabled(true);
			escrita.setEnabled(true);
			
			//rdbtnTanque1.setEnabled(true);
			//rdbtnTanque2.setEnabled(true);
			
			comboTipoOnda.setEnabled(true);
			
			botaoAtualizar.setEnabled(true);
			btnReset.setEnabled(true);
			
			rdbtnTempoSubida1.setEnabled(true);
			rdbtnTempoSubida2.setEnabled(true);
			rdbtnTempoSubida3.setEnabled(true);
			
			rdbtnPorcentagem.setEnabled(true);
			rdbtnAbs.setEnabled(true);
			
			spinnerTs.setEnabled(true);
			
			chckbxTensCalc.setEnabled(true);
			chckbxTensaoSat.setEnabled(true);
			chckbxNivTanque1.setEnabled(true);
			chckbxNivTanque2.setEnabled(true);
			chckbxSetPoint.setEnabled(true);
			chckbxErro.setEnabled(true);
			chckbxControle.setEnabled(true);
			chckbxP.setEnabled(true);
			chckbxD.setEnabled(true);
			chckbxI.setEnabled(true);
						
//			rdbtnAleatorio.setEnabled(true);
//			rdbtnDegrau.setEnabled(true);
//			rdbtnDenteSerra.setEnabled(true);
//			rdbtnQuadrada.setEnabled(true);
//			rdbtnSenoidal.setEnabled(true);
		}else{
			IPServidor.setEditable(true);
			Porta.setEditable(true);
			
			rdbtnAberta.setEnabled(false);			
			rdbtnFechada.setEnabled(false);
			
			amplitude.setEnabled(false);
			amplitudeMin.setEnabled(false);
			periodo.setEnabled(false);
			periodoMin.setEnabled(false);
			offSet.setEnabled(false);
			
			escrita.setEnabled(false);
			leitura1.setEnabled(false);
			leitura2.setEnabled(false);
			
			escrita.setSelectedItem("Selecione");
			leitura1.setSelectedItem("Selecione");
			leitura2.setSelectedItem("Selecione");
			
	//		rdbtnTanque1.setEnabled(false);
		//	rdbtnTanque1.setSelected(false);
			//rdbtnTanque2.setEnabled(false);
		//	rdbtnTanque2.setSelected(false);

			comboTipoOnda.setEnabled(false);
			comboTipoOnda.setSelectedIndex(0);
			
			comboTipoControlador.setEnabled(false);
			comboTipoControlador.setSelectedIndex(0);
			
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
			
			rdbtnPorcentagem.setEnabled(false);
			rdbtnPorcentagem.setSelected(false);
			rdbtnAbs.setEnabled(false);
			rdbtnAbs.setSelected(false);
			
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
		chckbxTensaoSat = new JCheckBox("Tens\u00E3o Sat. ");
		chckbxTensaoSat.setBounds(619, 185, 79, 13);		
		chckbxTensaoSat.setBackground(Color.WHITE);
		chckbxTensaoSat.setVisible(false);
		chckbxTensaoSat.setEnabled(false);
		chckbxTensaoSat.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxTensaoSat.setForeground(new Color(255, 0, 0));
		chckbxTensaoSat.setToolTipText("Sinal da Tens\u00E3o Saturada");
		panelGraficos.add(chckbxTensaoSat);
		
		chckbxTensCalc = new JCheckBox("Tens\u00E3o Calc.");
		chckbxTensCalc.setBounds(619, 185, 79, 13);
		chckbxTensCalc.setBackground(Color.WHITE);
		chckbxTensCalc.setEnabled(false);
		chckbxTensCalc.setVisible(false);
		chckbxTensCalc.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxTensCalc.setForeground(new Color(0, 0, 205));
		chckbxTensCalc.setToolTipText("Sinal da Tens\u00E3o Calculada");
		panelGraficos.add(chckbxTensCalc);
		
		//novos pos mudanca de chk box para esse grafico do grafico de baixo
		
		chckbxControle = new JCheckBox("Controle");
		chckbxControle.setBounds(619, 118, 102, 13);
		chckbxControle.setBackground(Color.WHITE);
		chckbxControle.setVisible(false);
		chckbxControle.setEnabled(false);
		chckbxControle.setToolTipText("Sinal de Controle");
		chckbxControle.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxControle.setForeground(Color.CYAN);
		panelGraficos.add(chckbxControle);
		
		chckbxSetPoint = new JCheckBox("Set-Point");
		chckbxSetPoint.setBounds(619, 102, 102, 13);		
		chckbxSetPoint.setBackground(Color.WHITE);
		chckbxSetPoint.setVisible(false);
		chckbxSetPoint.setEnabled(false);
		chckbxSetPoint.setToolTipText("Sinal do Set-Point");
		chckbxSetPoint.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxSetPoint.setForeground(Color.RED);
		panelGraficos.add(chckbxSetPoint);
		
		chckbxP = new JCheckBox("Ação P");
		chckbxP.setEnabled(false);
		chckbxP.setBounds(619, 134, 102, 13);
		chckbxP.setBackground(Color.WHITE);
		chckbxP.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxP.setVisible(false);
		chckbxP.setToolTipText("Sinal da ação Proporcional");
		chckbxP.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxP.setForeground(Color.ORANGE);
		panelGraficos.add(chckbxP);
		
		chckbxI = new JCheckBox("Ação I");
		chckbxI.setEnabled(false);
		chckbxI.setBounds(619, 150, 102, 13);
		chckbxI.setBackground(Color.WHITE);
		chckbxI.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxI.setVisible(false);
		chckbxI.setToolTipText("Sinal da ação Integrativa");
		chckbxI.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxI.setForeground(Color.MAGENTA);
		panelGraficos.add(chckbxI);
		
		chckbxD = new JCheckBox("Ação D");
		chckbxD.setEnabled(false);
		chckbxD.setBounds(619, 166, 102, 13);
		chckbxD.setBackground(Color.WHITE);
		chckbxD.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxD.setVisible(false);
		chckbxD.setToolTipText("Sinal da ação Derivativa");
		chckbxD.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxD.setForeground(Color.GRAY);
		panelGraficos.add(chckbxD);
		
		
		
		
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
		
		//novos pos mudança
		
		chckbxSetPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setSetPoint(chckbxSetPoint.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		
		chckbxControle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setErro(chckbxControle.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
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
	}
	
	private void inicializaCheckSinaisGrafico2(){
		
		chckbxNivTanque2 = new JCheckBox("N\u00EDvel do Tanque 2");
		chckbxNivTanque2.setBounds(600, 428, 102, 13);
		chckbxNivTanque2.setBackground(Color.WHITE);
		chckbxNivTanque2.setVisible(false);
		chckbxNivTanque2.setEnabled(false);
		chckbxNivTanque2.setToolTipText("Sinal de N\u00EDvel do Tanque 2");
		chckbxNivTanque2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxNivTanque2.setForeground(new Color(0, 0, 205));
		panelGraficos.add(chckbxNivTanque2);
		
		chckbxNivTanque1 = new JCheckBox("N\u00EDvel do Tanque 1");
		chckbxNivTanque1.setBounds(600, 411, 102, 13);
		chckbxNivTanque1.setBackground(Color.WHITE);
		chckbxNivTanque1.setHorizontalAlignment(SwingConstants.RIGHT);
		chckbxNivTanque1.setVisible(false);
		chckbxNivTanque1.setEnabled(false);
		chckbxNivTanque1.setToolTipText("Sinal de N\u00EDvel do Tanque 1");
		chckbxNivTanque1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxNivTanque1.setForeground(Color.BLACK);
		panelGraficos.add(chckbxNivTanque1);
		
		chckbxErro = new JCheckBox("Erro");
		chckbxErro.setBounds(600, 395, 102, 13);
		chckbxErro.setBackground(Color.WHITE);
		chckbxErro.setVisible(false);
		chckbxErro.setEnabled(false);
		chckbxErro.setToolTipText("ERRO");
		chckbxErro.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxErro.setForeground(Color.PINK);
		panelGraficos.add(chckbxErro);
		
		
		chckbxNivTanque1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setNivel1(chckbxNivTanque1.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		chckbxNivTanque2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setNivel2(chckbxNivTanque2.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		
		chckbxErro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setErroMesmo(chckbxErro.isSelected());
				thread.setDadosGrafico(dados);
			}
		});
		
	}
	
	private void inicializaPainelCheckSinaisGraficos(){		
		lblExibirCheckSinalGrafico1 = new JLabel("");
		lblExibirCheckSinalGrafico1.setBounds(670, 210, 32, 43);
		lblExibirCheckSinalGrafico1.setToolTipText("Exibir Sinal");
		lblExibirCheckSinalGrafico1.setIcon(new ImageIcon(Tela.class.getResource("/Icons/Chart-Curve-Add-32.png")));
		panelGraficos.add(lblExibirCheckSinalGrafico1);
		
		inicializaCheckSinaisGrafico1();
		
		lblExibirCheckSinalGrafico1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!chckbxTensaoSat.isVisible()){
					lblExibirCheckSinalGrafico1.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Delete-32.png")));
					chckbxTensCalc.setVisible(true);
					chckbxTensaoSat.setVisible(true);
					chckbxSetPoint.setVisible(true);
					
					chckbxControle.setVisible(true);
					chckbxP.setVisible(true);
					chckbxI.setVisible(true);
					chckbxD.setVisible(true);
				}else{
					lblExibirCheckSinalGrafico1.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Add-32.png")));
					chckbxTensCalc.setVisible(false);
					chckbxTensaoSat.setVisible(false);
					chckbxSetPoint.setVisible(false);
					
					chckbxControle.setVisible(false);
					chckbxP.setVisible(false);
					chckbxI.setVisible(false);
					chckbxD.setVisible(false);
				}
			}
		});
		
		lblExibirCheckSinalGrafico2 = new JLabel("");
		lblExibirCheckSinalGrafico2.setBounds(670, 448, 32, 43);
		lblExibirCheckSinalGrafico2.setIcon(new ImageIcon(Tela.class.getResource("/Icons/Chart-Curve-Add-32.png")));
		lblExibirCheckSinalGrafico2.setToolTipText("Exibir Sinal");
		panelGraficos.add(lblExibirCheckSinalGrafico2);
		
		inicializaCheckSinaisGrafico2();
		
		lblExibirCheckSinalGrafico2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!chckbxNivTanque1.isVisible()){
					lblExibirCheckSinalGrafico2.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Delete-32.png")));

					chckbxNivTanque1.setVisible(true);
					chckbxNivTanque2.setVisible(true);
					chckbxErro.setVisible(true);
					
				}else{
					lblExibirCheckSinalGrafico2.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Add-32.png")));
					
					chckbxNivTanque1.setVisible(false);
					chckbxNivTanque2.setVisible(false);
					chckbxErro.setVisible(false);
				}
			}
		});
	}
	
	private void desabilitarParamsControle(){		
		textFieldKp.setText("");
		textFieldKi.setText("");
		textFieldKd.setText("");
		textFieldTali.setText("");
		textFieldTald.setText("");
		
		textFieldKp.setEnabled(false);
		textFieldKi.setEnabled(false);
		textFieldKd.setEnabled(false);
		textFieldTali.setEnabled(false);
		textFieldTald.setEnabled(false);
		
//		rdbtnControladorP.setEnabled(false);
//		rdbtnControladorPI.setEnabled(false);
//		rdbtnControladorPD.setEnabled(false);
//		rdbtnControladorPID.setEnabled(false);
//		rdbtnControladorPITracoD.setEnabled(false);
//		
//		rdbtnControladorP.setSelected(false);
//		rdbtnControladorPI.setSelected(false);
//		rdbtnControladorPD.setSelected(false);
//		rdbtnControladorPID.setSelected(false);
//		rdbtnControladorPITracoD.setSelected(false);
	}
	
	private void habilitarComponentesPainelTipoMalha(Boolean bool){
		rdbtnAberta.setEnabled(bool);
		rdbtnFechada.setEnabled(bool);
		
		if(!bool){
			rdbtnAberta.setSelected(bool);
			rdbtnFechada.setSelected(bool);
		}
	}
	
	@SuppressWarnings("unused")
	private void inicializaPainelTipoSinal(){
		panelTipoSinal = new JPanel();
		panelTipoSinal.setBounds(39, 118, 314, 73);
		panelTipoSinal.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de Sinal", TitledBorder.RIGHT, TitledBorder.TOP, null, Color.GRAY));
		panelTipoSinal.setLayout(null);
		
		rdbtnDegrau = new JRadioButton("Degrau");
		rdbtnDegrau.setEnabled(false);		
		rdbtnDegrau.setBounds(109, 18, 89, 23);
		rdbtnDegrau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnSenoidal.setSelected(false);
				rdbtnQuadrada.setSelected(false);
				rdbtnDenteSerra.setSelected(false);
				rdbtnAleatorio.setSelected(false);

				lblAmplitude.setText("Amplitude:");	
				amplitude.setEnabled(true);
				amplitudeMin.setEnabled(false);
				amplitudeMin.setValue(0);
				lblPeriodo.setText("Período:");
				periodo.setEnabled(false);
				periodo.setValue(0);
				periodoMin.setEnabled(false);
				periodoMin.setValue(0);
				offSet.setEnabled(false);
				offSet.setValue(0);
			}
		});
		panelTipoSinal.add(rdbtnDegrau);
		
		rdbtnSenoidal = new JRadioButton("Senoidal");
		rdbtnSenoidal.setEnabled(false);
		rdbtnSenoidal.setBounds(10, 18, 89, 23);
		rdbtnSenoidal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnDegrau.setSelected(false);
				rdbtnQuadrada.setSelected(false);
				rdbtnDenteSerra.setSelected(false);
				rdbtnAleatorio.setSelected(false);

				lblAmplitude.setText("Amplitude:");	
				amplitude.setEnabled(true);
				amplitudeMin.setEnabled(false);
				amplitudeMin.setValue(0);
				lblPeriodo.setText("Período:");
				periodo.setEnabled(true);
				periodoMin.setEnabled(false);
				periodoMin.setValue(0);
				offSet.setEnabled(true);
			}
		});
		panelTipoSinal.add(rdbtnSenoidal);
		
		rdbtnQuadrada = new JRadioButton("Quadrada");
		rdbtnQuadrada.setEnabled(false);
		rdbtnQuadrada.setBounds(10, 44, 89, 23);
		rdbtnQuadrada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnDegrau.setSelected(false);
				rdbtnSenoidal.setSelected(false);
				rdbtnDenteSerra.setSelected(false);
				rdbtnAleatorio.setSelected(false);
				
				lblAmplitude.setText("Amplitude:");
				amplitude.setEnabled(true);
				amplitudeMin.setEnabled(false);
				amplitudeMin.setValue(0);
				lblPeriodo.setText("Período:");
				periodo.setEnabled(true);
				periodoMin.setEnabled(false);
				periodoMin.setValue(0);
				offSet.setEnabled(true);
			}
		});
		panelTipoSinal.add(rdbtnQuadrada);
		
		rdbtnDenteSerra = new JRadioButton("Dente de Serra");
		rdbtnDenteSerra.setEnabled(false);
		rdbtnDenteSerra.setBounds(109, 44, 109, 23);
		rdbtnDenteSerra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rdbtnDegrau.setSelected(false);
				rdbtnSenoidal.setSelected(false);
				rdbtnQuadrada.setSelected(false);
				rdbtnAleatorio.setSelected(false);
				
				lblAmplitude.setText("Amplitude:");
				amplitude.setEnabled(true);
				amplitudeMin.setEnabled(false);
				amplitudeMin.setValue(0);
				lblPeriodo.setText("Período:");
				periodo.setEnabled(true);
				periodoMin.setEnabled(false);
				periodoMin.setValue(0);
				offSet.setEnabled(true);
			}
		});		
		panelTipoSinal.add(rdbtnDenteSerra);
		
		rdbtnAleatorio = new JRadioButton("Aleat\u00F3rio");
		rdbtnAleatorio.setEnabled(false);
		rdbtnAleatorio.setBounds(208, 18, 89, 23);
		rdbtnAleatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnDegrau.setSelected(false);
				rdbtnSenoidal.setSelected(false);
				rdbtnQuadrada.setSelected(false);
				rdbtnDenteSerra.setSelected(false);
								
				lblAmplitude.setText("Amplitude (Máx):");
				amplitude.setEnabled(true);
				amplitudeMin.setEnabled(true);
				lblPeriodo.setText("Período (Máx):");
				periodo.setEnabled(true);
				periodoMin.setEnabled(true);
				offSet.setEnabled(false);
				offSet.setValue(0);
			}
		});
		panelTipoSinal.add(rdbtnAleatorio);		
	}

	@SuppressWarnings("unused")
	private void inicializarPainelTipoControlador(){
		panelTipoControlador = new JPanel();
		panelTipoControlador.setBounds(114, 102, 100, 99);
		panelTipoControlador.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Controlador", TitledBorder.LEADING, TitledBorder.TOP, null, Color.GRAY));
		panelTipoControlador.setLayout(null);
		
		rdbtnControladorPD = new JRadioButton("PD");
		rdbtnControladorPD.setEnabled(false);
		rdbtnControladorPD.setToolTipText("Controlador Proporcional Derivativo");
		rdbtnControladorPD.setBounds(6, 50, 38, 14);
		rdbtnControladorPD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				rdbtnControladorP.setSelected(false);
				rdbtnControladorPI.setSelected(false);
				rdbtnControladorPD.setSelected(true);
				rdbtnControladorPID.setSelected(false);
				rdbtnControladorPITracoD.setSelected(false);
				
				textFieldKp.setEnabled(true);
				textFieldKi.setEnabled(false);
				textFieldKi.setText("");
				textFieldKd.setEnabled(true);
				textFieldTali.setEnabled(false);
				textFieldTali.setText("");
				textFieldTald.setEnabled(true);
			}
		});		
		panelTipoControlador.add(rdbtnControladorPD);
		
		rdbtnControladorPID = new JRadioButton("PID");
		rdbtnControladorPID.setEnabled(false);
		rdbtnControladorPID.setToolTipText("Controlador Proporcional Integral Derivativo");
		rdbtnControladorPID.setBounds(51, 50, 43, 14);
		rdbtnControladorPID.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				rdbtnControladorP.setSelected(false);
				rdbtnControladorPI.setSelected(false);
				rdbtnControladorPD.setSelected(false);
				rdbtnControladorPID.setSelected(true);
				rdbtnControladorPITracoD.setSelected(false);
				
				textFieldKp.setEnabled(true);
				textFieldKi.setEnabled(true);
				textFieldKd.setEnabled(true);
				textFieldTali.setEnabled(true);
				textFieldTald.setEnabled(true);
			}
		});		
		panelTipoControlador.add(rdbtnControladorPID);
		
		rdbtnControladorPITracoD = new JRadioButton("PI-D");
		rdbtnControladorPITracoD.setEnabled(false);
		rdbtnControladorPITracoD.setToolTipText("Controlador ");
		rdbtnControladorPITracoD.setBounds(6, 78, 48, 14);
		rdbtnControladorPITracoD.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				rdbtnControladorP.setSelected(false);
				rdbtnControladorPI.setSelected(false);
				rdbtnControladorPD.setSelected(false);
				rdbtnControladorPID.setSelected(false);
				rdbtnControladorPITracoD.setSelected(true);
				
				textFieldKp.setEnabled(true);
				textFieldKi.setEnabled(true);
				textFieldKd.setEnabled(true);
				textFieldTali.setEnabled(true);
				textFieldTald.setEnabled(true);
			}
		});
		panelTipoControlador.add(rdbtnControladorPITracoD);
		
		rdbtnControladorP = new JRadioButton("P");
		rdbtnControladorP.setBounds(6, 24, 35, 14);
		rdbtnControladorP.setEnabled(false);
		rdbtnControladorP.setToolTipText("Controlador Proporcional");
		rdbtnControladorP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				rdbtnControladorP.setSelected(true);
				rdbtnControladorPI.setSelected(false);
				rdbtnControladorPD.setSelected(false);
				rdbtnControladorPID.setSelected(false);
				rdbtnControladorPITracoD.setSelected(false);
				
				textFieldKp.setEnabled(true);
				textFieldKi.setEnabled(false);
				textFieldKi.setText("");
				textFieldKd.setEnabled(false);
				textFieldKd.setText("");
				textFieldTali.setEnabled(false);
				textFieldTali.setText("");
				textFieldTald.setEnabled(false);
				textFieldTald.setText("");
				/*KP = Double.parseDouble(textFieldKp.getText());
				//dados.setKP(KP);
*/			}
		});
		panelTipoControlador.add(rdbtnControladorP);
		
		rdbtnControladorPI = new JRadioButton("PI");
		rdbtnControladorPI.setBounds(51, 24, 35, 14);
		rdbtnControladorPI.setEnabled(false);
		rdbtnControladorPI.setToolTipText("Controlador Proporcional Integral");
		rdbtnControladorPI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {				
				rdbtnControladorP.setSelected(false);
				rdbtnControladorPI.setSelected(true);
				rdbtnControladorPD.setSelected(false);
				rdbtnControladorPID.setSelected(false);
				rdbtnControladorPITracoD.setSelected(false);
				
				textFieldKp.setEnabled(true);
				textFieldKi.setEnabled(true);
				textFieldKd.setEnabled(false);
				textFieldKd.setText("");
				textFieldTali.setEnabled(true);
				textFieldTald.setEnabled(false);
				textFieldTald.setText("");
			}
		});
		panelTipoControlador.add(rdbtnControladorPI);
	}
}
