import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class ConfigControlador extends JDialog {

	private final JPanel contentPanel = new JPanel();

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
		setBounds(100, 100, 326, 225);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblKi = new JLabel("Kp:");
			lblKi.setBounds(58, 37, 26, 14);
			contentPanel.add(lblKi);
		}
		{
			JLabel label = new JLabel("Ki:");
			label.setBounds(58, 62, 26, 14);
			contentPanel.add(label);
		}
		{
			JLabel lblKd = new JLabel("Kd:");
			lblKd.setBounds(58, 90, 26, 14);
			contentPanel.add(lblKd);
		}
		{
			JLabel label = new JLabel("Kp:");
			label.setBounds(154, 37, 26, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Ki:");
			label.setBounds(154, 62, 26, 14);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("Kd:");
			label.setBounds(154, 90, 26, 14);
			contentPanel.add(label);
		}
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
