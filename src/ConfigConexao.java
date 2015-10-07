import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class ConfigConexao extends JDialog {

	private final JPanel contentPanel = new JPanel();
	@SuppressWarnings("rawtypes")
	private JComboBox escrita, leitura2, leitura1;
	private JTextFieldAlterado textField_porta;
	private JTextField textField_ip;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {

			ConfigConexao dialog = new ConfigConexao(new DadosConexao());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ConfigConexao(final DadosConexao dadosConexao) {
		setTitle("Configura\u00E7\u00E3o");		
		textField_porta = new JTextFieldAlterado();
		textField_porta.setColumns(10);
		textField_porta.setBounds(85, 33, 111, 20);
		getContentPane().add(textField_porta);
		
		leitura1 = new JComboBox(getItensComboLeituraEscrita());
		leitura1.setBounds(85, 58, 111, 20);
		getContentPane().add(leitura1);
		
		leitura2 = new JComboBox(getItensComboLeituraEscrita());
		leitura2.setBounds(85, 83, 111, 20);
		getContentPane().add(leitura2);
		
		escrita = new JComboBox(getItensComboLeituraEscrita());
		escrita.setBounds(85, 109, 111, 20);
		getContentPane().add(escrita);
		
		setBounds(100, 100, 232, 212);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel labelIp = new JLabel("IP:");
		labelIp.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelIp.setBounds(20, 14, 55, 14);
		contentPanel.add(labelIp);
		
		JLabel labelPorta = new JLabel("Porta:");
		labelPorta.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelPorta.setBounds(20, 36, 55, 14);
		contentPanel.add(labelPorta);
		
		JLabel labelLeitura = new JLabel("Leitura 1:");
		labelLeitura.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelLeitura.setBounds(20, 61, 55, 14);
		contentPanel.add(labelLeitura);
		
		JLabel labelLeitura2 = new JLabel("Leitura 2:");
		labelLeitura2.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelLeitura2.setBounds(20, 86, 55, 14);
		contentPanel.add(labelLeitura2);
		
		JLabel labelEscrita = new JLabel("Escrita:");
		labelEscrita.setFont(new Font("Tahoma", Font.PLAIN, 13));
		labelEscrita.setBounds(20, 111, 55, 14);
		contentPanel.add(labelEscrita);
		textField_ip = new JTextField();
		contentPanel.add(textField_ip);
		textField_ip.setBounds(85, 8, 111, 20);
		textField_ip.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						dadosConexao.setIp(textField_ip);
						dadosConexao.setPorta(textField_porta);
						dadosConexao.setLeitura1(leitura1);
						dadosConexao.setLeitura2(leitura2);
						dadosConexao.setEscrita(escrita);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	/**
	 * @param args
	 */
	protected Object[] getItensComboLeituraEscrita() {
		Object[] io = {"Selecione", 0, 1, 2, 3, 4, 5, 6, 7};
		
		return io;
	}
}
