import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConfigControlador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ConfigControlador dialog = new ConfigControlador(null, new DadosControlador());
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ConfigControlador(String titulo, DadosControlador dadosControlador) {
		setTitle(titulo);
		setBounds(100, 100, 274, 194);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblKi = new JLabel("Kp:");
			lblKi.setBounds(28, 34, 26, 14);
			contentPanel.add(lblKi);
		}
		{
			JLabel label = new JLabel("Ki:");
			label.setBounds(28, 65, 26, 14);
			contentPanel.add(label);
		}
		{
			JLabel lblKd = new JLabel("Kd:");
			lblKd.setBounds(28, 89, 26, 20);
			contentPanel.add(lblKd);
		}
		{
			JLabel lblTt = new JLabel("Tt:");
			lblTt.setBounds(124, 34, 26, 14);
			contentPanel.add(lblTt);
		}
		{
			JLabel lblTi = new JLabel("Ti:");
			lblTi.setBounds(124, 65, 26, 14);
			contentPanel.add(lblTi);
		}
		{
			JLabel lblTd = new JLabel("Td:");
			lblTd.setBounds(124, 89, 26, 20);
			contentPanel.add(lblTd);
		}
		
		textField = new JTextField();
		textField.setBounds(48, 28, 66, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(48, 59, 66, 20);
		contentPanel.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(48, 89, 66, 20);
		contentPanel.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(148, 28, 66, 20);
		contentPanel.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(148, 59, 66, 20);
		contentPanel.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(148, 89, 66, 20);
		contentPanel.add(textField_5);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
