public class Controlador {
	
	private double KI, KD, KP, Td, Ti, Tt;
	private double controleAnteriorSaturado, controleNaoSaturado; 
	private boolean windUP;
	private double integral, derivada, erroAnterior, proporcional;
	
	
	public Controlador( double kP, double kI, double kD, double tt, boolean windUP){
	
		KI = kI;
		KD = kD;
		KP = kP;
		Tt = tt;
		this.controleAnteriorSaturado = 0;
		this.controleNaoSaturado = 0;
		this.windUP = windUP;
		this.integral = 0;
		this.erroAnterior = 0;
	
	}
	
	public void acaoP(double erro){
		this.proporcional = KP*erro;
	}
	
	public void acaoI(double erro){
		if(!windUP)
			this.integral = this.integral + KI*erro*0.1;
		else{
			this.integral = this.integral + KI*erro*0.1 + 0.1*(1/(Tt)*(this.controleAnteriorSaturado - this.controleNaoSaturado));
		}	
	}
	
	public void acaoD(double erro){
		
		this.derivada = KD*((erro - this.erroAnterior)/(0.1));
		this.erroAnterior = erro;
		
	}
	
	double calcularAcao(double erro, double PV){
		if(PV != 0){
			acaoP(erro); acaoI(erro); acaoD(erro);
		}
		else{
			acaoP(erro); acaoI(erro); acaoD(PV);
		}
			
		return (getProporcional() + getIntegral() + getDerivada());
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

	public double getKP() {
		return KP;
	}

	public void setKP(double kP) {
		KP = kP;
	}

	public double getTd() {
		return Td;
	}

	public void setTd(double td) {
		Td = td;
	}

	public double getTi() {
		return Ti;
	}

	public void setTi(double ti) {
		Ti = ti;
	}

	public double getTt() {
		return Tt;
	}

	public void setTt(double tt) {
		Tt = tt;
	}

	public boolean isWindUP() {
		return windUP;
	}

	public void setWindUP(boolean windUP) {
		this.windUP = windUP;
	}

	public double getProporcional(){
		return proporcional;
	}
	public double getIntegral() {
		return integral;
	}

	public double getDerivada() {
		return derivada;
	}

	public double getControleAnteriorSaturado() {
		return controleAnteriorSaturado;
	}

	public void setControleAnteriorSaturado(double controleAnteriorSaturado) {
		this.controleAnteriorSaturado = controleAnteriorSaturado;
	}

	public double getControleNaoSaturado() {
		return controleNaoSaturado;
	}

	public void setControleNaoSaturado(double controleNaoSaturado) {
		this.controleNaoSaturado = controleNaoSaturado;
	}
}

