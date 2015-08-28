public class Dados {

	private double PV;
	//private double PV1;
	private double vp;
	private double Amplitude;
	private double duracao;
	private double periodo;
	private double offset;
	private double amplitudeMinima;
	private double periodoMinino;
	private double KP;
	private double KI;
	private double KD;
	
	
	
	private int pinoDeLeitura;
	private int pinoDeEscrita;
	private int comControle;
	
	private String tipoSinal;
	private String tipoMalha;
	private String tipoDeControle;
	
	private boolean tensao, tensaoSat, nivel1, nivel2, setPoint, erro, proporcional, integral, derivativo;

	public double getVp() {
		return vp;
	}

	public void setVp(double vp) {
		this.vp = vp;
	}

	public Dados(){
	}

	public double getPV() {
		return PV;
	}

	public void setPV(double PV) {
		this.PV = PV;
	}

	/*public double getPV1() {
		return PV1;
	}
*/
	/*public void setPV1(double PV1) {
		this.PV1 = PV1;
	}*/

	public double getVP() {
		return vp;
	}

	public void setVP(double vp) {
		this.vp = vp;
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



	public int getPinoDeLeitura() {
		return pinoDeLeitura;
	}

	public void setPinoDeLeitura(int pinoDeLeitura) {
		this.pinoDeLeitura = pinoDeLeitura;
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
	
/*	public void jhonnyTest(){
		this.PV = 1;
		//private double PV1;
		this.vp = 1;
		this.Amplitude = 1;
		this.periodo = 1;
		this.offset = 1;
		this.amplitudeMinima = 1;
		this.periodoMinino = 1;
		this.pinoDeLeitura = 1;
		this.pinoDeEscrita = 1;
		this.tipoSinal = "Senoidal";
		this.tipoMalha = "Malha Aberta";
	}
*/
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

	public boolean isErro() {
		return erro;
	}

	public void setErro(boolean erro) {
		this.erro = erro;
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

	public int getComControle() {
		return comControle;
	}

	public void setComControle(int comControle) {
		this.comControle = comControle;
	}
}
