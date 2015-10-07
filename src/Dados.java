import javax.swing.JLabel;

public class Dados {

	private double Amplitude;
	private double amplitudeMinima;
	private double periodo;
	private double periodoMinino;
	private double offset;
	private double tempoDePico;
	private double tempoDeSubida;
	private double sobreSinalMax;
	private double settleTempo;
	
	private int pinoDeLeitura1;
	private int pinoDeLeitura2;
	private int pinoDeEscrita;
	
	private String tipoSinal;
	private String tipoMalha;
	private String tipoDeControle;
	private String tipoDeControlador;
	
	private boolean windUP, tensao, tensaoSat, nivel1, nivel2, setPoint, proporcional, integral, derivativo, erroMesmo;
	private boolean faixa2, faixa5, faixa7, faixa10, tanque1, tanque2;
	

	private double KP;
	private double KI;
	private double KD;	
	private double Tt;
	
	private double PV;
	private double PV_two;
	private double VP;
	
	
	private double duracao;
	
	private JLabel tPico, tSubida, tAcomoda, nivelPico;

	private double fatSup = 1, fatInf = 0; 
	private boolean picoAbs;
	
	public Dados(){
	}

	public double getPV() {
		return PV;
	}

	public void setPV(double PV) {
		this.PV = PV;
	}

	public double getVP() {
		return VP;
	}

	public void setVP(double VP) {
		this.VP = VP;
	}

	public String getTipoSinal() {
		return tipoSinal;
	}

	public void setTipoSinal(String tipoSinal) {
		this.tipoSinal = tipoSinal;
	}

	public String getTipoMalha() {
		return tipoMalha;
	}

	public void setTipoMalha(String tipoMalha) {
		this.tipoMalha = tipoMalha;
	}

	public double getAmplitude() {
		return Amplitude;
	}

	public void setAmplitude(double amplitude) {
		Amplitude = amplitude;
	}

	public int getPinoDeLeitura1() {
		return pinoDeLeitura1;
	}

	public void setPinoDeLeitura1(int pinoDeLeitura1) {
		this.pinoDeLeitura1 = pinoDeLeitura1;
	}

	public int getPinoDeLeitura2() {
		return pinoDeLeitura2;
	}

	public void setPinoDeLeitura2(int pinoDeLeitura2) {
		this.pinoDeLeitura2 = pinoDeLeitura2;
	}

	public int getPinoDeEscrita() {
		return pinoDeEscrita;
	}

	public void setPinoDeEscrita(int pinoDeEscrita) {
		this.pinoDeEscrita = pinoDeEscrita;
	}

	public double getDuracao() {
		return duracao;
	}

	public void setDuracao(double duracao) {
		this.duracao = duracao;
	}

	public double getPeriodo() {
		return periodo;
	}

	public void setPeriodo(double periodo) {
		this.periodo = periodo;
	}

	public double getOffset() {
		return offset;
	}

	public void setOffset(double offset) {
		this.offset = offset;
	}

	public double getAmplitudeMinima() {
		return amplitudeMinima;
	}

	public void setAmplitudeMinima(double amplitudeMinima) {
		this.amplitudeMinima = amplitudeMinima;
	}

	public double getPeriodoMinino() {
		return periodoMinino;
	}

	public void setPeriodoMinino(double periodoMinino) {
		this.periodoMinino = periodoMinino;
	}
	
	public boolean isTensao() {
		return tensao;
	}

	public void setTensao(boolean tensao) {
		this.tensao = tensao;
	}

	public boolean isTensaoSat() {
		return tensaoSat;
	}

	public void setTensaoSat(boolean tensaoSat) {
		this.tensaoSat = tensaoSat;
	}

	public boolean isNivel1() {
		return nivel1;
	}

	public void setNivel1(boolean nivel1) {
		this.nivel1 = nivel1;
	}

	public boolean isNivel2() {
		return nivel2;
	}

	public void setNivel2(boolean nivel2) {
		this.nivel2 = nivel2;
	}

	public boolean isSetPoint() {
		return setPoint;
	}

	public void setSetPoint(boolean setPoint) {
		this.setPoint = setPoint;
	}

	
	public boolean isErroMesmo() {
		return erroMesmo;
	}

	public void setErroMesmo(boolean erroMesmo) {
		this.erroMesmo = erroMesmo;
	}
	
	public boolean isProporcional() {
		return proporcional;
	}

	public void setProporcional(boolean proporcional) {
		this.proporcional = proporcional;
	}
	
	public boolean isIntegral() {
		return integral;
	}

	public void setIntegral(boolean integral) {
		this.integral = integral;
	}
	
	public boolean isDerivativo() {
		return derivativo;
	}

	public void setDerivativo(boolean derivativo) {
		this.derivativo = derivativo;
	}
	
	public boolean isWindUP() {
		return windUP;
	}

	public void setFaixa2(boolean faixa2) {
		this.faixa2 = faixa2;
	}

	public boolean isFaixa2() {
		return faixa2;
	}

	public void setFaixa5(boolean faixa5) {
		this.faixa5 = faixa5;
	}

	public boolean isFaixa5() {
		return faixa5;
	}


	public void setFaixa7(boolean faixa7) {
		this.faixa7 = faixa7;
	}

	public boolean isFaixa7() {
		return faixa7;
	}

	public void setFaixa10(boolean faixa10) {
		this.faixa10 = faixa10;
	}

	public boolean isFaixa10() {
		return faixa10;
	}

	
	public void setWindUP(boolean windUP) {
		this.windUP = windUP;
	}
	
	
	public double getKP() {
		return KP;
	}

	public void setKP(double kP) {
		KP = kP;
	}

	public double getKI() {
		return KI;
	}

	public void setKI(double kI) {
		KI = kI;
	}

	public double getKD() {
		return KD;
	}

	public void setKD(double kD) {
		KD = kD;
	}

	public String getTipoDeControle() {
		return tipoDeControle;
	}

	public void setTipoDeControle(String tipoDeControle) {
		this.tipoDeControle = tipoDeControle;
	}

	/*public boolean isComControle() {
		return comControle;
	}*/

	/*public void setComControle(boolean comControle) {
		this.comControle = comControle;
	}*/

	public double getTt() {
		return Tt;
	}

	public void setTt(double tt) {
		Tt = tt;
	}

	public double getTempoDePico() {
		return tempoDePico;
	}

	public void setTempoDePico(double tempoDePico) {
		this.tempoDePico = tempoDePico;
	}

	public double getTempoDeSubida() {
		return tempoDeSubida;
	}

	public void setTempoDeSubida(double tempoDeSubida) {
		this.tempoDeSubida = tempoDeSubida;
	}

	public double getSobreSinalMax() {
		return sobreSinalMax;
	}

	public void setSobreSinalMax(double sobreSinalMax) {
		this.sobreSinalMax = sobreSinalMax;
	}

	public double getSettleTempo() {
		return settleTempo;
	}

	public void setSettleTempo(double settleTempo) {
		this.settleTempo = settleTempo;
	}

	public double getVp() {
		return VP;
	}

	public void setVp(double vp) {
		this.VP = vp;
	}

	public JLabel gettPico() {
		return tPico;
	}

	public void settPico(JLabel tPico) {
		this.tPico = tPico;
	}

	public JLabel gettSubida() {
		return tSubida;
	}

	public void settSubida(JLabel tSubida) {
		this.tSubida = tSubida;
	}

	public JLabel gettAcomoda() {
		return tAcomoda;
	}

	public void settAcomoda(JLabel tAcomoda) {
		this.tAcomoda = tAcomoda;
	}

	public JLabel getNivelPico() {
		return nivelPico;
	}

	public void setNivelPico(JLabel nivelPico) {
		this.nivelPico = nivelPico;
	}

	
	public double getFatSup() {
		return fatSup;
	}

	public void setFatSup(double fatSup) {
		this.fatSup = fatSup;
	}

	public double getFatInf() {
		return fatInf;
	}

	public void setFatInf(double fatInf) {
		this.fatInf = fatInf;
	}

	public boolean isPicoAbs() {
		return picoAbs;
	}

	public void setPicoAbs(boolean picoAbs) {
		this.picoAbs = picoAbs;
	}

	public double getPV_two() {
		return PV_two;
	}

	public void setPV_two(double pV_two) {
		PV_two = pV_two;
	}

	public boolean isTanque1() {
		return tanque1;
	}

	public void setTanque1(boolean tanque1) {
		this.tanque1 = tanque1;
	}

	public boolean isTanque2() {
		return tanque2;
	}

	public void setTanque2(boolean tanque2) {
		this.tanque2 = tanque2;
	}

	public String getTipoDeControlador() {
		return tipoDeControlador;
	}

	public void setTipoDeControlador(String tipoDeControlador) {
		this.tipoDeControlador = tipoDeControlador;
	}
}
