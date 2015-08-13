

//import java.awt.BorderLayout;
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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class Tela {

	private JFrame frame;
	private JTextField Porta;
	private String tipoMalha;
	private String tipoSinal;
	private Tanque thread;
	private Dados dados;
	private JTextField IPServidor;
	
	
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
	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 894, 519);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelGraficos = new JPanel();
		panelGraficos.setBorder(new TitledBorder(null, "Gr\u00E1ficos", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGraficos.setBounds(333, 11, 538, 435);
		frame.getContentPane().add(panelGraficos);
		
		final JLayeredPane panelGrafico1 = new JLayeredPane();
		panelGrafico1.setBackground(Color.WHITE);
		panelGrafico1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGrafico1.setLayout(null);
		
		final JLayeredPane panelGrafico2 = new JLayeredPane();
		panelGrafico1.setBackground(Color.WHITE);
		panelGrafico1.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGrafico1.setLayout(null);
		
		/*final JPanel panelGrafico2 = new JPanel();
		panelGrafico2.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelGrafico2.setLayout(null);*/
		
		
		final JPanel panelExibirSinalGraf1 = new JPanel();
		panelExibirSinalGraf1.setBackground(new Color(0,0,0,0));
		panelExibirSinalGraf1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Exibir Sinal", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		final JCheckBox chckbxTensCalc = new JCheckBox("Tens\u00E3o Calc.");
		chckbxTensCalc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dados.setTensao(chckbxTensCalc.isSelected());	}
		});
		chckbxTensCalc.setEnabled(false);
		chckbxTensCalc.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxTensCalc.setForeground(new Color(0, 0, 205));
		panelExibirSinalGraf1.add(chckbxTensCalc);
		chckbxTensCalc.setToolTipText("Sinal da Tens\u00E3o Calculada");
		
		final JCheckBox chckbxTensaoSat = new JCheckBox("Tens\u00E3o Sat. ");
		chckbxTensaoSat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setTensaoSat(chckbxTensaoSat.isSelected());
			}
		});
		chckbxTensaoSat.setEnabled(false);
		chckbxTensaoSat.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxTensaoSat.setForeground(new Color(255, 0, 0));
		panelExibirSinalGraf1.add(chckbxTensaoSat);
		chckbxTensaoSat.setToolTipText("Sinal da Tens\u00E3o Saturada");
		
		final JPanel panelExibirSinalGraf2 = new JPanel();
		panelExibirSinalGraf2.setBorder(new TitledBorder(null, "Exibir Sinal", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelExibirSinalGraf2.setLayout(null);
		
		final JCheckBox chckbxNivTanque1 = new JCheckBox("N\u00EDvel do Tanque 1");
		chckbxNivTanque1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setNivel1(chckbxNivTanque1.isSelected());
			}
		});
		chckbxNivTanque1.setEnabled(false);
		chckbxNivTanque1.setToolTipText("Sinal de N\u00EDvel do Tanque 1");
		chckbxNivTanque1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxNivTanque1.setForeground(Color.BLACK);
		chckbxNivTanque1.setBounds(6, 22, 102, 23);
		panelExibirSinalGraf2.add(chckbxNivTanque1);
		
		final JCheckBox chckbxNivTanque2 = new JCheckBox("N\u00EDvel do Tanque 2");
		chckbxNivTanque2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setNivel2(chckbxNivTanque2.isSelected());
			}
		});
		chckbxNivTanque2.setEnabled(false);
		chckbxNivTanque2.setToolTipText("Sinal de N\u00EDvel do Tanque 2");
		chckbxNivTanque2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxNivTanque2.setForeground(new Color(0, 0, 205));
		chckbxNivTanque2.setBounds(6, 48, 102, 23);
		panelExibirSinalGraf2.add(chckbxNivTanque2);
		
		final JCheckBox chckbxSetPoint = new JCheckBox("Set-Point");
		chckbxSetPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setSetPoint(chckbxSetPoint.isSelected());
			}
		});
		chckbxSetPoint.setEnabled(false);
		chckbxSetPoint.setToolTipText("Sinal do Set-Point");
		chckbxSetPoint.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxSetPoint.setForeground(Color.RED);
		chckbxSetPoint.setBounds(6, 74, 102, 23);
		panelExibirSinalGraf2.add(chckbxSetPoint);
		
		final JCheckBox chckbxErro = new JCheckBox("Erro");
		chckbxErro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dados.setErro(chckbxErro.isSelected());
			}
		});
		chckbxErro.setEnabled(false);
		chckbxErro.setToolTipText("Sinal de Erro");
		chckbxErro.setFont(new Font("Tahoma", Font.PLAIN, 9));
		chckbxErro.setForeground(new Color(50, 205, 50));
		chckbxErro.setBounds(6, 100, 102, 23);
		panelExibirSinalGraf2.add(chckbxErro);
		GroupLayout gl_panelGraficos = new GroupLayout(panelGraficos);
		gl_panelGraficos.setHorizontalGroup(
			gl_panelGraficos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelGraficos.createSequentialGroup()
					.addGap(2)
					.addGroup(gl_panelGraficos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelGraficos.createSequentialGroup()
							.addComponent(panelGrafico1, GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
							.addGap(2)
							.addComponent(panelExibirSinalGraf1, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelGraficos.createSequentialGroup()
							.addComponent(panelGrafico2, GroupLayout.PREFERRED_SIZE, 410, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(panelExibirSinalGraf2, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)))
					.addGap(1))
		);
		gl_panelGraficos.setVerticalGroup(
			gl_panelGraficos.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelGraficos.createSequentialGroup()
					.addGap(2)
					.addGroup(gl_panelGraficos.createParallelGroup(Alignment.LEADING)
						.addComponent(panelGrafico1, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
						.addComponent(panelExibirSinalGraf1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
					.addGap(7)
					.addGroup(gl_panelGraficos.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelGraficos.createSequentialGroup()
							.addGap(5)
							.addComponent(panelGrafico2, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelExibirSinalGraf2, GroupLayout.PREFERRED_SIZE, 205, GroupLayout.PREFERRED_SIZE)))
		);
		panelGraficos.setLayout(gl_panelGraficos);

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
		panelAbas.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelAbas.setBounds(10, 86, 313, 387);
		frame.getContentPane().add(panelAbas);
		panelAbas.setLayout(null);
		
		JTabbedPane abas = new JTabbedPane(JTabbedPane.TOP);
		abas.setBounds(4, 5, 307, 377);
		panelAbas.add(abas);
		
		JPanel panelOpcoesMalha = new JPanel();
		abas.addTab("Opções de Malha Aberta", null, panelOpcoesMalha, null);
		panelOpcoesMalha.setLayout(null);
		
		JPanel panelDadosSinal = new JPanel();
		panelDadosSinal.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Dados do Sinal", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelDadosSinal.setBounds(156, 5, 142, 198);
		panelOpcoesMalha.add(panelDadosSinal);
		
		final JSpinner amplitude = new JSpinner();
		amplitude.setEnabled(false);
		amplitude.setBounds(87, 23, 49, 20);
		//amplitude.setModel(new SpinnerNumberModel(0.0, -4.0, 4.0, 0.0));
		amplitude.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		
		JLabel lblTensao = new JLabel("Amplitude:");
		lblTensao.setBounds(6, 23, 79, 20);
		
		JLabel lblPeriodo = new JLabel("Per\u00EDodo:");
		lblPeriodo.setBounds(6, 95, 79, 20);
		
		final JSpinner periodo = new JSpinner();
		periodo.setEnabled(false);
		periodo.setModel(new SpinnerNumberModel(new Double(0), new Double(0), null, new Double(1)));
		periodo.setBounds(87, 95, 49, 20);
		
		JLabel lblAmplitudeMin = new JLabel("Amplitude (M\u00EDn):");
		lblAmplitudeMin.setBounds(6, 59, 79, 20);
		
		final JSpinner amplitudeMin = new JSpinner();
		amplitudeMin.setEnabled(false);
		amplitudeMin.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		amplitudeMin.setBounds(86, 59, 51, 20);
		panelDadosSinal.setLayout(null);
		panelDadosSinal.add(lblTensao);
		panelDadosSinal.add(amplitude);
		panelDadosSinal.add(lblPeriodo);
		panelDadosSinal.add(periodo);
		panelDadosSinal.add(lblAmplitudeMin);
		panelDadosSinal.add(amplitudeMin);
		
		JLabel lblPeriodoMin = new JLabel("Per\u00EDodo (M\u00EDn):");
		lblPeriodoMin.setBounds(6, 131, 79, 20);
		panelDadosSinal.add(lblPeriodoMin);
		
		final JSpinner periodoMin = new JSpinner();
		periodoMin.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		periodoMin.setEnabled(false);
		periodoMin.setBounds(87, 131, 49, 20);
		panelDadosSinal.add(periodoMin);
		
		JLabel lblOffSet = new JLabel("Off-Set:");
		lblOffSet.setBounds(6, 167, 79, 20);
		panelDadosSinal.add(lblOffSet);
		
		final JSpinner offSet = new JSpinner();
		offSet.setBounds(87, 167, 49, 20);
		panelDadosSinal.add(offSet);
		offSet.setEnabled(false);
		offSet.setModel(new SpinnerNumberModel(new Double(0), null, null, new Double(1)));
		
		JPanel panelTipoMalha = new JPanel();
		panelTipoMalha.setBounds(4, 5, 153, 84);
		panelOpcoesMalha.add(panelTipoMalha);
		panelTipoMalha.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de Malha", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelTipoMalha.setLayout(null);	
		
		JPanel panelIO = new JPanel();
		panelIO.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Entradas e Sa\u00EDdas", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelIO.setBounds(4, 90, 153, 113);
		panelOpcoesMalha.add(panelIO);
		panelIO.setLayout(null);
		
		@SuppressWarnings("rawtypes")
		final JComboBox leitura1 = new JComboBox();
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
		
		@SuppressWarnings("rawtypes")
		final JComboBox leitura2 = new JComboBox();
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
		
		@SuppressWarnings("rawtypes")
		final JComboBox escrita = new JComboBox();
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
		
		final JPanel panelTipoSinal = new JPanel();
		panelTipoSinal.setBounds(3, 205, 295, 140);
		panelOpcoesMalha.add(panelTipoSinal);
		panelTipoSinal.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Tipo de Sinal", TitledBorder.RIGHT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelTipoSinal.setLayout(null);
		
		final JRadioButton rdbtnAberta = new JRadioButton("Aberta");
		rdbtnAberta.setEnabled(false);
		final JRadioButton rdbtnFechada = new JRadioButton("Fechada");
		rdbtnFechada.setEnabled(false);
		
		final JRadioButton rdbtnDegrau = new JRadioButton("Degrau");
		rdbtnDegrau.setEnabled(false);
		final JRadioButton rdbtnSenoidal = new JRadioButton("Senoidal");
		rdbtnSenoidal.setEnabled(false);
		final JRadioButton rdbtnQuadrada = new JRadioButton("Quadrada");
		rdbtnQuadrada.setEnabled(false);
		final JRadioButton rdbtnDenteSerra = new JRadioButton("Dente de Serra");
		rdbtnDenteSerra.setEnabled(false);
		final JRadioButton rdbtnAleatorio = new JRadioButton("Aleat\u00F3rio");
		rdbtnAleatorio.setEnabled(false);
		
		rdbtnAberta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnFechada.setSelected(false);
				
				tipoMalha = "Malha Aberta";
			}
		});
		rdbtnAberta.setBounds(6, 20, 67, 23);
		panelTipoMalha.add(rdbtnAberta);
		
		rdbtnFechada.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rdbtnAberta.setSelected(false);
				
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
				tipoSinal = "Aleatoria";
			}
		});
		rdbtnAleatorio.setBounds(159, 70, 109, 23);
		panelTipoSinal.add(rdbtnAleatorio);
		
		JPanel panelDadosServidor = new JPanel();
		panelDadosServidor.setBorder(new TitledBorder(null, "Dados do Servidor", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelDadosServidor.setBounds(8, 11, 317, 73);
		frame.getContentPane().add(panelDadosServidor);
		panelDadosServidor.setLayout(null);
		
		JLabel lblIPServidor = new JLabel("IP do Servidor:");
		lblIPServidor.setBounds(10, 17, 88, 20);
		panelDadosServidor.add(lblIPServidor);
		
		JLabel lblPorta = new JLabel("Porta:");
		lblPorta.setBounds(10, 45, 46, 14);
		panelDadosServidor.add(lblPorta);
				
		IPServidor = new JTextField();
		IPServidor.setColumns(10);
		IPServidor.setBounds(95, 17, 96, 20);
		panelDadosServidor.add(IPServidor);
		
		Porta = new JTextField();
		Porta.setBounds(95, 42, 96, 20);
		panelDadosServidor.add(Porta);
		Porta.setColumns(10);
		
		final JButton botaoAtualizar = new JButton("Atualizar");
		botaoAtualizar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269378_gtk-refresh.png")));
		final JButton btnStop = new JButton("Stop");
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
						amplitude.setEnabled(true);
						amplitudeMin.setEnabled(true);
						periodo.setEnabled(true);
						periodoMin.setEnabled(true);
						offSet.setEnabled(true);
						
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
						
						btnConectarDesconectar.setForeground(Color.RED);
						btnConectarDesconectar.setBackground(Color.RED);
						btnConectarDesconectar.setText("Desconectar");
						btnConectarDesconectar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269745_gtk-dialog-error.png")));
					}else{					
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
						
						btnConectarDesconectar.setForeground(new Color(0, 128, 0));
						btnConectarDesconectar.setBackground(new Color(0, 128, 0));
						btnConectarDesconectar.setText("Conectar");
						btnConectarDesconectar.setIcon(new ImageIcon(Tela.class.getResource("/Icons/1439269447_gtk-apply.png")));
					}
				}
			}
		});
		btnConectarDesconectar.setBounds(195, 16, 112, 46);
		panelDadosServidor.add(btnConectarDesconectar);
				
		botaoAtualizar.setEnabled(false);
		botaoAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panelExibirSinalGraf1.setBackground(Color.WHITE);
				panelExibirSinalGraf2.setBackground(Color.WHITE);
				
				chckbxTensCalc.setBackground(Color.WHITE);
				chckbxTensaoSat.setBackground(Color.WHITE);
				chckbxNivTanque1.setBackground(Color.WHITE);
				chckbxNivTanque2.setBackground(Color.WHITE);
				chckbxSetPoint.setBackground(Color.WHITE);
				chckbxErro.setBackground(Color.WHITE);
				
				dados = new Dados();
				
				//Tipo de Malha
				dados.setTipoMalha(tipoMalha);
				
				//Tipo de Sinal
				dados.setTipoSinal(tipoSinal);
				
				//Dados do Sinal
				dados.setAmplitude((double)amplitude.getValue());
				dados.setPeriodo((double)periodo.getValue());
				dados.setPeriodoMinino((double) periodoMin.getValue());
				dados.setAmplitudeMinima(((double) amplitudeMin.getValue()));
				dados.setOffset((double)offSet.getValue());
				
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
		
		botaoAtualizar.setBounds(497, 450, 101, 23);
		frame.getContentPane().add(botaoAtualizar);
		
		btnStop.setEnabled(false);
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				thread.interrupt();
			}
		});
		btnStop.setBounds(608, 450, 101, 23);
		frame.getContentPane().add(btnStop);
	}
	
//	teste1 git
}
