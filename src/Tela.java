import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.border.TitledBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
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

public class Tela {

	private JFrame frame;
	private JPanel panelTipoSinal;
	private JLayeredPane panelGrafico1;
	private JLayeredPane panelGrafico2;
	
	private String tipoMalha;
	private String tipoSinal;
	private String tipoControle;
	private Tanque thread;
	private Dados dados;
	
	private double KP;
	
	private JTextField IPServidor;
	private JTextFieldAlterado Porta;
	
	private JCheckBox chckbxTensaoSat;
	private JCheckBox chckbxTensCalc;	
	private JCheckBox chckbxNivTanque1;
	private JCheckBox chckbxNivTanque2;
	private JCheckBox chckbxSetPoint;
	private JCheckBox chckbxErro;
	
	private JLabel lblExibirSinal;
	private JLabel lblPeriodo;
	private JLabel label;
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
	
	private JRadioButton rdbtnAberta;
	private JRadioButton rdbtnFechada;
	private JRadioButton rdbtnDegrau;
	private JRadioButton rdbtnSenoidal;
	private JRadioButton rdbtnQuadrada;
	private JRadioButton rdbtnDenteSerra;
	private JRadioButton rdbtnAleatorio;
	
	private JButton botaoAtualizar;
	private JButton btnStop;
	private JPanel panelTipoControlador;
	private JTextFieldAlterado textFieldKp;
	private JTextFieldAlterado textFieldKi;
	private JTextFieldAlterado textFieldTali;
	private JTextFieldAlterado textFieldKd;
	private JTextFieldAlterado textFieldTald;
	
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
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 894, 570);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelGraficos = new JPanel();
		panelGraficos.setBounds(333, 11, 538, 475);
		panelGraficos.setBorder(new TitledBorder(null, "Gr\u00E1ficos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		panelGrafico1 = new JLayeredPane();
		panelGrafico1.setForeground(Color.WHITE);
		panelGrafico1.setBounds(8, 18, 524, 220);
		panelGrafico1.setBackground(Color.WHITE);
		panelGrafico1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGrafico1.setLayout(null);
		
		panelGrafico2 = new JLayeredPane();
		panelGrafico2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGrafico2.setBounds(8, 244, 524, 220);
		panelGrafico1.setBackground(Color.WHITE);
		panelGrafico1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGrafico1.setLayout(null);
		
		chckbxTensaoSat = new JCheckBox("Tens\u00E3o Sat. ");
		chckbxTensaoSat.setBackground(Color.WHITE);
		chckbxTensaoSat.setBounds(424, 119, 79, 13);
		panelGrafico1.add(chckbxTensaoSat);
		chckbxTensaoSat.setVisible(false);
		chckbxTensaoSat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setTensaoSat(chckbxTensaoSat.isSelected());
			}
		});
		chckbxTensaoSat.setEnabled(false);
		chckbxTensaoSat.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxTensaoSat.setForeground(new Color(255, 0, 0));
		chckbxTensaoSat.setToolTipText("Sinal da Tens\u00E3o Saturada");
		chckbxTensCalc = new JCheckBox("Tens\u00E3o Calc.");
		chckbxTensCalc.setBackground(Color.WHITE);
		
		chckbxTensCalc.setBounds(424, 103, 79, 13);
		panelGrafico1.add(chckbxTensCalc);
		chckbxTensCalc.setEnabled(false);
		chckbxTensCalc.setVisible(false);
		chckbxTensCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dados.setTensao(chckbxTensCalc.isSelected());	}
		});
		chckbxTensCalc.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxTensCalc.setForeground(new Color(0, 0, 205));
		chckbxTensCalc.setToolTipText("Sinal da Tens\u00E3o Calculada");
		
		lblExibirSinal = new JLabel("");
		lblExibirSinal.setBounds(475, 128, 32, 43);
		panelGrafico1.add(lblExibirSinal);
		lblExibirSinal.setToolTipText("Exibir Sinal");
		lblExibirSinal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!chckbxTensaoSat.isVisible()){
					lblExibirSinal.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Delete-32.png")));
					chckbxTensCalc.setVisible(true);
					chckbxTensaoSat.setVisible(true);
				}else{
					lblExibirSinal.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Add-32.png")));
					chckbxTensCalc.setVisible(false);
					chckbxTensaoSat.setVisible(false);
				}
			}
		});
		
		lblExibirSinal.setIcon(new ImageIcon(Tela.class.getResource("/Icons/Chart-Curve-Add-32.png")));
		
		label = new JLabel("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!chckbxNivTanque1.isVisible()){
					label.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Delete-32.png")));

					chckbxNivTanque1.setVisible(true);
					chckbxNivTanque2.setVisible(true);
					chckbxSetPoint.setVisible(true);
					chckbxErro.setVisible(true);
				}else{
					label.setIcon(new ImageIcon(Tela.class.getResource("Icons/Chart-Curve-Add-32.png")));
					
					chckbxNivTanque1.setVisible(false);
					chckbxNivTanque2.setVisible(false);
					chckbxSetPoint.setVisible(false);
					chckbxErro.setVisible(false);
				}
			}
		});
		chckbxSetPoint = new JCheckBox("Set-Point");
		chckbxSetPoint.setBackground(Color.WHITE);
		
		chckbxSetPoint.setBounds(401, 103, 102, 13);
		chckbxSetPoint.setVisible(false);
		panelGrafico2.add(chckbxSetPoint);
		chckbxSetPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setSetPoint(chckbxSetPoint.isSelected());
			}
		});
		chckbxSetPoint.setEnabled(false);
		chckbxSetPoint.setToolTipText("Sinal do Set-Point");
		chckbxSetPoint.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxSetPoint.setForeground(Color.RED);
		
		chckbxNivTanque1 = new JCheckBox("N\u00EDvel do Tanque 1");
		chckbxNivTanque1.setBackground(Color.WHITE);
		chckbxNivTanque1.setHorizontalAlignment(SwingConstants.RIGHT);
		
		chckbxNivTanque1.setBounds(401, 71, 102, 13);
		chckbxNivTanque1.setVisible(false);
		panelGrafico2.add(chckbxNivTanque1);
		chckbxNivTanque1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setNivel1(chckbxNivTanque1.isSelected());
			}
		});
		chckbxNivTanque1.setEnabled(false);
		chckbxNivTanque1.setToolTipText("Sinal de N\u00EDvel do Tanque 1");
		chckbxNivTanque1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxNivTanque1.setForeground(Color.BLACK);
		chckbxNivTanque2 = new JCheckBox("N\u00EDvel do Tanque 2");
		chckbxNivTanque2.setBackground(Color.WHITE);
		
		chckbxNivTanque2.setBounds(401, 87, 102, 13);
		chckbxNivTanque2.setVisible(false);
		panelGrafico2.add(chckbxNivTanque2);
		chckbxNivTanque2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setNivel2(chckbxNivTanque2.isSelected());
			}
		});
		chckbxNivTanque2.setEnabled(false);
		chckbxNivTanque2.setToolTipText("Sinal de N\u00EDvel do Tanque 2");
		chckbxNivTanque2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxNivTanque2.setForeground(new Color(0, 0, 205));
		chckbxErro = new JCheckBox("Erro");
		chckbxErro.setBackground(Color.WHITE);
		
		chckbxErro.setBounds(401, 119, 102, 13);
		chckbxErro.setVisible(false);
		panelGrafico2.add(chckbxErro);
		chckbxErro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setErro(chckbxErro.isSelected());
			}
		});
		chckbxErro.setEnabled(false);
		chckbxErro.setToolTipText("Sinal de Erro");
		chckbxErro.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxErro.setForeground(new Color(50, 205, 50));
		label.setIcon(new ImageIcon(Tela.class.getResource("/Icons/Chart-Curve-Add-32.png")));
		label.setToolTipText("Exibir Sinal");
		label.setBounds(475, 128, 32, 43);
		panelGrafico2.add(label);
		panelGraficos.setLayout(null);
		panelGraficos.add(panelGrafico1);
		panelGraficos.add(panelGrafico2);

		//Método usado para expandir o frame de panelGrafico1	
		panelGrafico1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {			
				if(panelGrafico2.isVisible()){
					panelGrafico2.setVisible(false);
					panelGrafico1.setBounds(8, 20, 388, 400);
				}else{
					panelGrafico1.setBounds(8, 20, 388, 200);
					panelGrafico2.setVisible(true);
				}
			}
		});
		
		//Método usado para expandir o frame de panelGrafico2			
		panelGrafico2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(panelGrafico1.isVisible()){
					panelGrafico1.setVisible(false);
					panelGrafico2.setBounds(8, 20, 388, 400);
				}else{					
					panelGrafico2.setBounds(8, 225, 388, 200);
					panelGrafico1.setVisible(true);
				}
			}
		});
		
		JPanel panelAbas = new JPanel();
		panelAbas.setBounds(10, 86, 320, 399);
		panelAbas.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAbas.setLayout(null);
		
		JTabbedPane abas = new JTabbedPane(JTabbedPane.TOP);
		abas.setBounds(4, 5, 312, 377);
		panelAbas.add(abas);
		
		JPanel panelControlSimples = new JPanel();
		abas.addTab("Controle Simples", null, panelControlSimples, null);
		panelControlSimples.setLayout(null);
		
		JPanel panelDadosSinal = new JPanel();
		panelDadosSinal.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dados do Sinal", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDadosSinal.setBounds(156, 5, 148, 198);
		panelControlSimples.add(panelDadosSinal);
		
		amplitude = new JSpinner();
		amplitude.setEnabled(false);
		amplitude.setBounds(92, 23, 49, 20);
		//amplitude.setModel(new SpinnerNumberModel(0.0, -4.0, 4.0, 0.0));
		amplitude.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		
		lblAmplitude = new JLabel("Amplitude:");
		lblAmplitude.setBounds(6, 23, 85, 20);
		
		lblPeriodo = new JLabel("Per\u00EDodo:");
		lblPeriodo.setBounds(6, 95, 85, 20);
		
		periodo = new JSpinner();
		periodo.setEnabled(false);
		periodo.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		periodo.setBounds(92, 95, 49, 20);
		
		lblAmplitudeMin = new JLabel("Amplitude (M\u00EDn):");
		lblAmplitudeMin.setBounds(6, 59, 85, 20);
		
		amplitudeMin = new JSpinner();
		amplitudeMin.setEnabled(false);
		amplitudeMin.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		amplitudeMin.setBounds(91, 59, 51, 20);
		panelDadosSinal.setLayout(null);
		panelDadosSinal.add(lblAmplitude);
		panelDadosSinal.add(amplitude);
		panelDadosSinal.add(lblPeriodo);
		panelDadosSinal.add(periodo);
		panelDadosSinal.add(lblAmplitudeMin);
		panelDadosSinal.add(amplitudeMin);
		
		JLabel lblPeriodoMin = new JLabel("Per\u00EDodo (M\u00EDn):");
		lblPeriodoMin.setBounds(6, 131, 85, 20);
		panelDadosSinal.add(lblPeriodoMin);
		
		periodoMin = new JSpinner();
		periodoMin.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		periodoMin.setEnabled(false);
		periodoMin.setBounds(92, 131, 49, 20);
		panelDadosSinal.add(periodoMin);
		
		JLabel lblOffSet = new JLabel("Off-Set:");
		lblOffSet.setBounds(6, 167, 85, 20);
		panelDadosSinal.add(lblOffSet);
		
		offSet = new JSpinner();
		offSet.setBounds(92, 167, 49, 20);
		panelDadosSinal.add(offSet);
		offSet.setEnabled(false);
		offSet.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		
		JPanel panelTipoMalha = new JPanel();
		panelTipoMalha.setBounds(4, 5, 153, 84);
		panelControlSimples.add(panelTipoMalha);
		panelTipoMalha.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de Malha", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelTipoMalha.setLayout(null);	
		
		JPanel panelIO = new JPanel();
		panelIO.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Entradas e Sa\u00EDdas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelIO.setBounds(4, 90, 153, 113);
		panelControlSimples.add(panelIO);
		panelIO.setLayout(null);
		
		leitura1 = new JComboBox();
		leitura1.setEnabled(false);
		leitura1.setBounds(61, 23, 85, 20);
		leitura1.addItem("Selecione");
		leitura1.addItem(0);
		leitura1.addItem(1);
		leitura1.addItem(2);
		leitura1.addItem(3);
		leitura1.addItem(4);
		leitura1.addItem(5);
		leitura1.addItem(6);
		leitura1.addItem(7);
		panelIO.add(leitura1);
		
		leitura2 = new JComboBox();
		leitura2.setEnabled(false);
		leitura2.setBounds(61, 54, 85, 20);
		leitura2.addItem("Selecione");
		leitura2.addItem(0);
		leitura2.addItem(1);
		leitura2.addItem(2);
		leitura2.addItem(3);
		leitura2.addItem(4);
		leitura2.addItem(5);
		leitura2.addItem(6);
		leitura2.addItem(7);
		panelIO.add(leitura2);
		
		escrita = new JComboBox();
		escrita.setEnabled(false);
		escrita.setBounds(61, 85, 85, 20);
		escrita.addItem("Selecione");
		escrita.addItem(0);
		escrita.addItem(1);
		escrita.addItem(2);
		escrita.addItem(3);
		escrita.addItem(4);
		escrita.addItem(5);
		escrita.addItem(6);
		escrita.addItem(7);
		panelIO.add(escrita);
		
		JLabel lblLeitura1 = new JLabel("Leitura 1:");
		lblLeitura1.setBounds(6, 26, 56, 14);
		panelIO.add(lblLeitura1);
		
		JLabel lblLeitura2 = new JLabel("Leitura 2:");
		lblLeitura2.setBounds(6, 57, 56, 14);
		panelIO.add(lblLeitura2);
		
		JLabel lblEscrita = new JLabel("Escrita:");
		lblEscrita.setBounds(6, 88, 56, 14);
		panelIO.add(lblEscrita);
		
		panelTipoSinal = new JPanel();
		panelTipoSinal.setBounds(3, 205, 301, 140);
		panelControlSimples.add(panelTipoSinal);
		panelTipoSinal.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de Sinal", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelTipoSinal.setLayout(null);
		
		rdbtnAberta = new JRadioButton("Aberta");
		rdbtnAberta.setEnabled(false);
		rdbtnFechada = new JRadioButton("Fechada");
		rdbtnFechada.setEnabled(false);
		
		rdbtnDegrau = new JRadioButton("Degrau");
		rdbtnDegrau.setEnabled(false);
		rdbtnSenoidal = new JRadioButton("Senoidal");
		rdbtnSenoidal.setEnabled(false);
		rdbtnQuadrada = new JRadioButton("Quadrada");
		rdbtnQuadrada.setEnabled(false);
		rdbtnDenteSerra = new JRadioButton("Dente de Serra");
		rdbtnDenteSerra.setEnabled(false);
		rdbtnAleatorio = new JRadioButton("Aleat\u00F3rio");
		rdbtnAleatorio.setEnabled(false);
		
		rdbtnAberta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnFechada.setSelected(false);
				
				desabilitarPainelTiposControle();
				
				tipoMalha = "Malha Aberta";
			}
		});
		rdbtnAberta.setBounds(6, 20, 67, 23);
		panelTipoMalha.add(rdbtnAberta);
		
		rdbtnFechada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnAberta.setSelected(false);
				
				rdbtnControladorP.setEnabled(true);
				rdbtnControladorPI.setEnabled(true);
				rdbtnControladorPD.setEnabled(true);
				rdbtnControladorPID.setEnabled(true);
				rdbtnControladorPITracoD.setEnabled(true);
				chckbxWindUp.setEnabled(false);
				
				tipoMalha = "Malha Fechada";
			}
		});
		rdbtnFechada.setBounds(6, 52, 87, 23);
		panelTipoMalha.add(rdbtnFechada);
		
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
				
				tipoSinal = "Degrau";
			}
		});
		rdbtnDegrau.setBounds(24, 30, 109, 23);
		panelTipoSinal.add(rdbtnDegrau);
		
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
				
				tipoSinal = "Senoidal";
			}
		});
		rdbtnSenoidal.setBounds(24, 70, 109, 23);
		panelTipoSinal.add(rdbtnSenoidal);
		
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
				
				tipoSinal = "Quadrada";
			}
		});
		rdbtnQuadrada.setBounds(24, 110, 109, 23);
		panelTipoSinal.add(rdbtnQuadrada);
		
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
				
				tipoSinal = "Dente de serra";
			}
		});
		rdbtnDenteSerra.setBounds(159, 30, 109, 23);
		panelTipoSinal.add(rdbtnDenteSerra);
		
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
								
				tipoSinal = "Aleatoria";
			}
		});
		rdbtnAleatorio.setBounds(159, 70, 109, 23);
		panelTipoSinal.add(rdbtnAleatorio);
		
		JPanel panelControlPID = new JPanel();
		abas.addTab("Controle com PID", null, panelControlPID, null);
		panelControlPID.setLayout(null);
		
		panelTipoControlador = new JPanel();
		panelTipoControlador.setBorder(new TitledBorder(null, "Tipo de Controlador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelTipoControlador.setBounds(10, 11, 113, 169);
		panelControlPID.add(panelTipoControlador);
		panelTipoControlador.setLayout(null);
		
		rdbtnControladorP = new JRadioButton("P");
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
				tipoControle = "P";
				/*KP = Double.parseDouble(textFieldKp.getText());
				//dados.setKP(KP);
*/			}
		});
		rdbtnControladorP.setEnabled(false);
		rdbtnControladorP.setToolTipText("Controlador Proporcional");
		rdbtnControladorP.setBounds(6, 23, 76, 23);
		panelTipoControlador.add(rdbtnControladorP);
		
		rdbtnControladorPI = new JRadioButton("PI");
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
		rdbtnControladorPI.setEnabled(false);
		rdbtnControladorPI.setToolTipText("Controlador Proporcional Integral");
		rdbtnControladorPI.setBounds(6, 52, 76, 23);
		panelTipoControlador.add(rdbtnControladorPI);
		
		rdbtnControladorPD = new JRadioButton("PD");
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
		rdbtnControladorPD.setEnabled(false);
		rdbtnControladorPD.setToolTipText("Controlador Proporcional Derivativo");
		rdbtnControladorPD.setBounds(6, 81, 76, 23);
		panelTipoControlador.add(rdbtnControladorPD);
		
		rdbtnControladorPID = new JRadioButton("PID");
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
		rdbtnControladorPID.setEnabled(false);
		rdbtnControladorPID.setToolTipText("Controlador Proporcional Integral Derivativo");
		rdbtnControladorPID.setBounds(6, 110, 76, 23);
		panelTipoControlador.add(rdbtnControladorPID);
		
		rdbtnControladorPITracoD = new JRadioButton("PI-D");
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
		rdbtnControladorPITracoD.setEnabled(false);
		rdbtnControladorPITracoD.setToolTipText("Controlador ");
		rdbtnControladorPITracoD.setBounds(6, 139, 76, 23);
		panelTipoControlador.add(rdbtnControladorPITracoD);
		
		JPanel panelParamsControlador = new JPanel();
		panelParamsControlador.setBorder(new TitledBorder(null, "Par\u00E2metros do Controlador", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelParamsControlador.setBounds(10, 227, 287, 113);
		panelControlPID.add(panelParamsControlador);
		panelParamsControlador.setLayout(null);
		
		lblKp = new JLabel("Kp:");
		lblKp.setBounds(36, 23, 22, 14);
		panelParamsControlador.add(lblKp);
		
		textFieldKp = new JTextFieldAlterado();
		textFieldKp.setEnabled(false);
		textFieldKp.setBounds(68, 17, 66, 20);
		panelParamsControlador.add(textFieldKp);
		textFieldKp.setColumns(10);
		
		lblKi = new JLabel("Ki:");
		lblKi.setBounds(36, 55, 22, 14);
		panelParamsControlador.add(lblKi);
		
		textFieldKi = new JTextFieldAlterado();
		textFieldKi.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				if(!textFieldKp.getText().equals("") && !textFieldKi.getText().equals("")){
					textFieldTali.setText("" + Double.parseDouble(textFieldKp.getText())
							/Double.parseDouble(textFieldKi.getText()));
				}
			}
		});
		textFieldKi.setEnabled(false);
		textFieldKi.setColumns(10);
		textFieldKi.setBounds(68, 49, 66, 20);
		panelParamsControlador.add(textFieldKi);
		
		JLabel lblTali = new JLabel("\u03C4i:");
		lblTali.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		lblTali.setBounds(162, 52, 23, 14);
		panelParamsControlador.add(lblTali);
		
		textFieldTali = new JTextFieldAlterado();
		textFieldTali.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				if(!textFieldKp.getText().equals("") && !textFieldTali.getText().equals("")){
					textFieldKi.setText("" + Double.parseDouble(textFieldKp.getText())
							/Double.parseDouble(textFieldTali.getText()));
				}
			}
		});
		textFieldTali.setEnabled(false);
		textFieldTali.setColumns(10);
		textFieldTali.setBounds(195, 49, 66, 20);
		panelParamsControlador.add(textFieldTali);
		
		lblKd = new JLabel("Kd:");
		lblKd.setBounds(36, 87, 22, 14);
		panelParamsControlador.add(lblKd);
		
		textFieldKd = new JTextFieldAlterado();
		textFieldKd.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				if(!textFieldKp.getText().equals("") && !textFieldKd.getText().equals("")){
					textFieldTald.setText("" + Double.parseDouble(textFieldKp.getText())
							/Double.parseDouble(textFieldKd.getText()));
				}
			}
		});
		textFieldKd.setEnabled(false);
		textFieldKd.setColumns(10);
		textFieldKd.setBounds(68, 81, 66, 20);
		panelParamsControlador.add(textFieldKd);
		
		JLabel lblTald = new JLabel("\u03C4d:");
		lblTald.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 13));
		lblTald.setBounds(162, 84, 23, 14);
		panelParamsControlador.add(lblTald);
		
		textFieldTald = new JTextFieldAlterado();
		textFieldTald.addCaretListener(new CaretListener() {
			public void caretUpdate(CaretEvent arg0) {
				if(!textFieldKp.getText().equals("") && !textFieldTald.getText().equals("")){
					textFieldKd.setText("" + Double.parseDouble(textFieldKp.getText())
							/Double.parseDouble(textFieldTald.getText()));
				}else{
					
				}
			}
		});
		textFieldTald.setEnabled(false);
		textFieldTald.setColumns(10);
		textFieldTald.setBounds(195, 81, 66, 20);
		panelParamsControlador.add(textFieldTald);
		
		labelInterrogation = new JLabel("");
		labelInterrogation.setToolTipText("Para Atualizar os parametros, basta clicar nos campos.");
		labelInterrogation.setIcon(new ImageIcon(Tela.class.getResource("/Icons/question-icon.png")));
		labelInterrogation.setBounds(258, 8, 24, 26);
		panelParamsControlador.add(labelInterrogation);
		
		chckbxWindUp = new JCheckBox("Acionar Wind Up");
		chckbxWindUp.setBounds(10, 190, 113, 23);
		panelControlPID.add(chckbxWindUp);
		
		JPanel panelDadosServidor = new JPanel();
		panelDadosServidor.setBounds(8, 11, 324, 73);
		panelDadosServidor.setBorder(new TitledBorder(null, "Dados do Servidor", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelDadosServidor.setLayout(null);
		
		JLabel lblIPServidor = new JLabel("IP do Servidor:");
		lblIPServidor.setBounds(10, 17, 88, 20);
		panelDadosServidor.add(lblIPServidor);
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(10, 45, 46, 14);
		panelDadosServidor.add(lblPorta);
				
		IPServidor = new JTextField();
		IPServidor.setColumns(10);
		IPServidor.setBounds(87, 17, 112, 20);
		panelDadosServidor.add(IPServidor);
		
		Porta = new JTextFieldAlterado();
		Porta.setBounds(87, 42, 112, 20);
		panelDadosServidor.add(Porta);
		Porta.setColumns(10);
		
		botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setBounds(497, 497, 101, 23);
		botaoAtualizar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269378_gtk-refresh.png")));
		
		btnStop = new JButton("Stop");
		btnStop.setBounds(608, 497, 101, 23);
		btnStop.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439270049_stop.png")));
		
		final JButton btnConectarDesconectar = new JButton("Conectar");
		btnConectarDesconectar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269447_gtk-apply.png")));
		btnConectarDesconectar.setForeground(new Color(0, 128, 0));
		btnConectarDesconectar.setBackground(new Color(0, 128, 0));
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
												
						mudarPropriedadesBotoes("Conectar");
						
						btnConectarDesconectar.setForeground(Color.RED);
						btnConectarDesconectar.setBackground(Color.RED);
						btnConectarDesconectar.setText("Desconectar");
						btnConectarDesconectar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269745_gtk-dialog-error.png")));
					}else{
						
						mudarPropriedadesBotoes("Desconectar");
						
						btnConectarDesconectar.setForeground(new Color(0, 128, 0));
						btnConectarDesconectar.setBackground(new Color(0, 128, 0));
						btnConectarDesconectar.setText("Conectar");
						btnConectarDesconectar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269447_gtk-apply.png")));
					}
				}
			}
		});
		btnConectarDesconectar.setBounds(203, 16, 112, 46);
		panelDadosServidor.add(btnConectarDesconectar);
				
		botaoAtualizar.setEnabled(false);
		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				dados = new Dados();
				
				//Tipo de Malha
				dados.setTipoMalha(tipoMalha);
				
				//Tipo de Sinal
				dados.setTipoSinal(tipoSinal);
				
				//Tipo de Controle
				dados.setTipoDeControle(tipoControle);
				//dados.setKP(KP);
				
				if(rdbtnControladorP.isSelected()){
					KP = Double.parseDouble(textFieldKp.getText());
					dados.setKP(KP);
				}
					
				
				//Dados do Sinal
				dados.setAmplitude((double)amplitude.getValue());
				if(!dados.getTipoSinal().equals("Degrau")){
					dados.setPeriodo((double)periodo.getValue());
					dados.setPeriodoMinino((double) periodoMin.getValue());
					dados.setAmplitudeMinima(((double) amplitudeMin.getValue()));
					dados.setOffset((double)offSet.getValue());
				}
				
				//Dados de I/O
				dados.setPinoDeEscrita((int)((Integer)leitura1.getSelectedItem()));
				dados.setPinoDeLeitura((int)((Integer)escrita.getSelectedItem()));
								
				//grafico
				//dados.jhonnyTest();
				thread.setDados(dados);
				if (!thread.isAlive()) {
					
				
					thread.setPainelTensao(panelGrafico1);
					thread.setPainelAltura(panelGrafico2);
					thread.start();
				}
			}
		});
		
		btnStop.setEnabled(false);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				thread.interrupt();
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(panelDadosServidor);
		frame.getContentPane().add(panelAbas);
		frame.getContentPane().add(panelGraficos);
		frame.getContentPane().add(botaoAtualizar);
		frame.getContentPane().add(btnStop);
	}
	
	public void mudarPropriedadesBotoes(String acao){
		if(acao.equals("Conectar")){
			IPServidor.setEditable(false);
			Porta.setEditable(false);
			
			rdbtnAberta.setEnabled(true);
			rdbtnFechada.setEnabled(true);
			rdbtnAleatorio.setEnabled(true);
			rdbtnDegrau.setEnabled(true);
			rdbtnDenteSerra.setEnabled(true);
			rdbtnQuadrada.setEnabled(true);
			rdbtnSenoidal.setEnabled(true);
			
			leitura1.setEnabled(true);
			leitura2.setEnabled(true);
			escrita.setEnabled(true);
			
			botaoAtualizar.setEnabled(true);
			btnStop.setEnabled(true);
			
			chckbxTensCalc.setEnabled(true);
			chckbxTensaoSat.setEnabled(true);
			chckbxNivTanque1.setEnabled(true);
			chckbxNivTanque2.setEnabled(true);
			chckbxSetPoint.setEnabled(true);
			chckbxErro.setEnabled(true);
		}else{
			IPServidor.setEditable(true);
			Porta.setEditable(true);
			
			amplitude.setEnabled(false);
			amplitudeMin.setEnabled(false);
			periodo.setEnabled(false);
			periodoMin.setEnabled(false);
			offSet.setEnabled(false);
			
			rdbtnAberta.setEnabled(false);
			
			rdbtnFechada.setEnabled(false);
			rdbtnAleatorio.setEnabled(false);
			rdbtnDegrau.setEnabled(false);
			rdbtnDenteSerra.setEnabled(false);
			rdbtnQuadrada.setEnabled(false);
			rdbtnSenoidal.setEnabled(false);
			
			leitura1.setEnabled(false);
			leitura2.setEnabled(false);
			escrita.setEnabled(false);

			botaoAtualizar.setEnabled(false);
			btnStop.setEnabled(false);
			
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
			
			rdbtnAberta.setSelected(false);
			rdbtnFechada.setSelected(false);
			
			rdbtnAleatorio.setSelected(false);
			rdbtnDenteSerra.setSelected(false);
			rdbtnDegrau.setSelected(false);
			rdbtnQuadrada.setSelected(false);
			rdbtnSenoidal.setSelected(false);
			
			escrita.setSelectedItem("Selecione");
			leitura1.setSelectedItem("Selecione");
			leitura2.setSelectedItem("Selecione");
			
			desabilitarPainelTiposControle();
						
		}
	}
	
	public void desabilitarPainelTiposControle(){
		rdbtnControladorP.setEnabled(false);
		rdbtnControladorPI.setEnabled(false);
		rdbtnControladorPD.setEnabled(false);
		rdbtnControladorPID.setEnabled(false);
		rdbtnControladorPITracoD.setEnabled(false);
		
		rdbtnControladorP.setSelected(false);
		rdbtnControladorPI.setSelected(false);
		rdbtnControladorPD.setSelected(false);
		rdbtnControladorPID.setSelected(false);
		rdbtnControladorPITracoD.setSelected(false);
		
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
		
		chckbxWindUp.setSelected(false);
		chckbxWindUp.setEnabled(false);
	}
}
