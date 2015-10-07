import javax.swing.JComboBox;
import javax.swing.JTextField;


public class DadosConexao {

	private JTextField ip;
	private JTextFieldAlterado porta;
	@SuppressWarnings("rawtypes")
	private JComboBox leitura1, leitura2, escrita;
	
	public JTextFieldAlterado getPorta() {
		return porta;
	}
	public void setPorta(JTextFieldAlterado porta) {
		this.porta = porta;
	}
	public JTextField getIp() {
		return ip;
	}
	public void setIp(JTextField ip) {
		this.ip = ip;
	}
	@SuppressWarnings("rawtypes")
	public JComboBox getLeitura1() {
		return leitura1;
	}
	@SuppressWarnings("rawtypes")
	public void setLeitura1(JComboBox leitura1) {
		this.leitura1 = leitura1;
	}
	@SuppressWarnings("rawtypes")
	public JComboBox getLeitura2() {
		return leitura2;
	}
	@SuppressWarnings("rawtypes")
	public void setLeitura2(JComboBox leitura2) {
		this.leitura2 = leitura2;
	}
	@SuppressWarnings("rawtypes")
	public JComboBox getEscrita() {
		return escrita;
	}
	@SuppressWarnings("rawtypes")
	public void setEscrita(JComboBox escrita) {
		this.escrita = escrita;
	}	
}
