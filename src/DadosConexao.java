import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class DadosConexao {

	private JTextField ip;
	private JTextFieldAlterado porta;
	@SuppressWarnings("rawtypes")
	private JComboBox leitura1, leitura2, escrita;
	
	private JLabel lblIp, lblPorta, lblLeitura1, lblLeitura2, lblEscrita;
	
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
	public JLabel getLblIp() {
		return lblIp;
	}
	public void setLblIp(JLabel lblIp) {
		this.lblIp = lblIp;
	}
	public JLabel getLblPorta() {
		return lblPorta;
	}
	public void setLblPorta(JLabel lblPorta) {
		this.lblPorta = lblPorta;
	}
	public JLabel getLblLeitura1() {
		return lblLeitura1;
	}
	public void setLblLeitura1(JLabel lblLeitura1) {
		this.lblLeitura1 = lblLeitura1;
	}
	public JLabel getLblLeitura2() {
		return lblLeitura2;
	}
	public void setLblLeitura2(JLabel lblLeitura2) {
		this.lblLeitura2 = lblLeitura2;
	}
	public JLabel getLblEscrita() {
		return lblEscrita;
	}
	public void setLblEscrita(JLabel lblEscrita) {
		this.lblEscrita = lblEscrita;
	}
	public String getTextLeitura1(){
		if(leitura1 == null){
			return leitura1.getSelectedItem() + "";
		}else{
			return "";
		}
	}
	
	public String getTextLeitura2(){
		if(leitura2 == null){
			return leitura2.getSelectedItem() + "";
		}else{
			return "";
		}
	}
	
	public String getTextEscrita(){
		if(escrita == null){
			return escrita.getSelectedItem() + "";
		}else{
			return "";
		}
	}
}
