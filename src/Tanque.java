import java.lang.Thread;
import javax.swing.JLayeredPane;

public class Tanque extends Thread{
	
	private Dados dados = new Dados();
	private ChartNivel grafico_nivel = new ChartNivel(dados);
	private Chart grafico_controle = new Chart(dados);;
	private Tsunami sinal = new Tsunami(dados.getPeriodo(), dados.getPeriodoMinino(),dados.getOffset(),dados.getAmplitude(),dados.getAmplitudeMinima(),dados.getTipoSinal());;

	QuanserClient quanserclient;

	private double vp_sat;
	private double nivel_tanque_um;
	private double nivel_tanque_dois;
	public double nivel_coringa;

	public double t_pico = 0;
	public double t_acomoda = 0; 
	public double t_subida = 0;
    public double t_subida_i;
    public double t_subida_f;

	private String servidor;

	private int porta;
	private int faixa; //?

	JLayeredPane painelTensao, painelAltura;
	
	public double setPoint;
	public double newSetPoint;
	public double oldSetPoint = 0;
	public double deltaSetPoint = 0;
	public double settleTempo;
	public double tSetPoint = 0;
	public double nivel_passado = 0;
	public double nivel_pico = 0;

	public boolean controle = true;
    public boolean flagSubida = false;
    public boolean flagPico = false;
    public boolean flagSobreSinal = false;
    public boolean flagSettleTempo = false;
    public boolean flagTrI = false;
    public boolean flagTrF = false;

	public Tanque(String servidor, int porta){
		this.servidor = servidor;
		this.porta = porta;
	}

	public Tanque(Dados dados){
		this.dados = dados;
	}

	public QuanserClient getConexao(){
        try {
           quanserclient = new QuanserClient(servidor, porta);
        } catch (QuanserClientException ex) {
        	System.out.println("Conexão não realizada!");
            ex.printStackTrace();
        }
        
        if(quanserclient != null)
        	System.out.println("Conexão não realizada!");
        
        return quanserclient;
    }

	public void run(){
		while(true){

			try {
				
				getConexao();
				
				dados.setPV(quanserclient.read(dados.getPinoDeLeitura1()));
				dados.setPV_two(quanserclient.read(dados.getPinoDeLeitura2()));
				
				if(dados.getTipoMalha().equals("Malha Aberta")){
					
					dados.setVp(sinal.gerarPonto().getY());
					
				}
				
				sleep(100);
			} catch (QuanserClientException | InterruptedException e) {e.printStackTrace();}
		}
	}

	public void verificarRegras(){
    	

    	setVp_sat(dados.getVP());
    	
		if (dados.getVP() > 4)
			    setVp_sat(4);
		
		if (dados.getVP() < -4)
			    setVp_sat(-4);
			
		//LH
		if (nivel_tanque_um*6.25 > 28 && dados.getVP() > 3.15){
		   setVp_sat(3.15);
		}

		//LHH
		if (nivel_tanque_um*6.25 > 29 && dados.getVP() > 0) {
		    
		}

		//LL
		if (nivel_tanque_um*6.25 < 4 && dados.getVP()< 0){
		    setVp_sat(0);
		}
		
			dados.setVP(gettVp_sat());
	}

	//metódos que eu não mexi (ainda) pra não dar treta com a tela

	public Dados getDados() {
		return dados;
	}

	public void setDados(Dados dados) {
		this.dados = dados;
		this.sinal = new Tsunami(this.dados.getPeriodo(), this.dados.getPeriodoMinino(),this.dados.getOffset(),this.dados.getAmplitude(),this.dados.getAmplitudeMinima(),this.dados.getTipoSinal());
		this.grafico_controle.dados = dados;
		this.grafico_nivel.dados = dados;
	}
	
	public void setDadosGrafico(Dados dados) {
		this.grafico_controle.dados = dados;
		this.grafico_nivel.dados = dados;
	}
    
    public JLayeredPane getPainelTensao() {
		return painelTensao;
	}

	public void setPainelTensao(JLayeredPane graficoTensao) {
		this.painelTensao = graficoTensao;
		this.painelTensao.add(grafico_controle.painel, new Integer(0));
		grafico_controle.painel.setBounds(painelTensao.getVisibleRect());
	
	}

	public JLayeredPane getPainelAltura() {
		return painelAltura;
	}

	public void setPainelAltura(JLayeredPane painelAltura) {
		this.painelAltura = painelAltura;
		this.painelAltura.add(grafico_nivel.painelG2, new Integer(0));
		grafico_nivel.painelG2.setBounds(painelAltura.getVisibleRect());
	}
//tempos
	void tempoSubida(){
		if (setPoint - oldSetPoint > 0){
			if(!flagTrI && nivel_coringa*6.25 >= oldSetPoint + dados.getFatInf() * deltaSetPoint)
			{
				t_subida_i = sinal.getTempo() - tSetPoint - 0.2;
				flagTrI = true;
			}
			if (!flagTrF && nivel_coringa*6.25 >= oldSetPoint + dados.getFatSup() * deltaSetPoint){
				t_subida_f = sinal.getTempo() - tSetPoint - 0.2;
				flagTrF = true;
			}
			if (flagTrI && flagTrF)
			{
				t_subida = t_subida_f - t_subida_i;
				dados.gettSubida().setText(String.valueOf(t_subida));
				flagSubida = true;
			}
		}else{
			if(!flagTrI && nivel_coringa*6.25 <= oldSetPoint + dados.getFatInf() * deltaSetPoint)
			{
				t_subida_i = sinal.getTempo() - tSetPoint - 0.2;
				flagTrI = true;
			}
			if (!flagTrF && nivel_coringa*6.25 <= oldSetPoint + dados.getFatSup() * deltaSetPoint){
				t_subida_f = sinal.getTempo() - tSetPoint - 0.2;
				flagTrF = true;
			}
			if (flagTrI && flagTrF)
			{
				t_subida = t_subida_f - t_subida_i;
				dados.gettSubida().setText(String.valueOf(t_subida));
				flagSubida = true;
			}
		}
	}

	void tempoPico(){
		
		if(!flagPico){
		if (setPoint - oldSetPoint > 0){
			if (nivel_coringa*6.25 < nivel_passado && nivel_coringa*6.25 > setPoint)
			{
				t_pico = sinal.getTempo() - tSetPoint- 0.2;
				dados.gettPico().setText(String.valueOf(t_pico));
				nivel_pico = nivel_passado;
				System.out.println("sub");
				System.out.println(nivel_passado);
				System.out.println(nivel_coringa*6.25);
			
				
				if (dados.isPicoAbs())
					dados.getNivelPico().setText(String.valueOf((nivel_pico - setPoint)));
				else
					dados.getNivelPico().setText(String.valueOf(100 * (nivel_pico - setPoint)/(setPoint - oldSetPoint)));
				flagPico = true;
			}
		}
		else
		{
			System.out.println("outro");
			if (nivel_coringa*6.25 > nivel_passado && nivel_coringa*6.25 < setPoint)
			{
				t_pico = sinal.getTempo() - 0.2 - tSetPoint;
				nivel_pico = nivel_passado;
				if (dados.isPicoAbs())
					dados.getNivelPico().setText(String.valueOf(100 * (nivel_pico - setPoint)/(setPoint - oldSetPoint)));
				else
					dados.gettPico().setText(String.valueOf(t_pico));
				flagPico = true;
			}
		}
		}else{
			if (setPoint - oldSetPoint > 0){
				if (nivel_coringa*6.25 > nivel_passado && nivel_coringa*6.25 > setPoint && nivel_coringa*6.25 > nivel_pico){
					flagPico = false;
				}
			}else{
				if (nivel_coringa*6.25 < nivel_passado && nivel_coringa*6.25 < setPoint && nivel_coringa*6.25 < nivel_pico){
					flagPico = false;
				}
			}
		}
	}

	void settleTempo(){
		
		double nivelDeComparacao = 0; 
		if (dados.isFaixa2())
			nivelDeComparacao = setPoint*0.02; 

		if (dados.isFaixa5())
			nivelDeComparacao = setPoint*0.05; 

		if (dados.isFaixa7())
			nivelDeComparacao = setPoint*0.07;

		if (dados.isFaixa10())
			nivelDeComparacao = setPoint*0.1;
	
		if(!flagSettleTempo){
			if(nivel_coringa*6.25 <= nivelDeComparacao + setPoint && nivel_coringa*6.25 >= setPoint - nivelDeComparacao ){
					settleTempo = sinal.getTempo() - 0.1;
					flagSettleTempo = true; 
			}
		}else if(flagSettleTempo){
			
			if(!(nivel_coringa*6.25 <= nivelDeComparacao + setPoint && nivel_coringa*6.25 >= setPoint - nivelDeComparacao )){
				flagSettleTempo = false;
				
			}
		}


		if(flagSettleTempo)
			dados.gettAcomoda().setText(String.valueOf(settleTempo));
		else{dados.gettAcomoda().setText("");}
	}

	void sp_mudou(){
		//t_pico = t_subida = t_acomoda = 0;
		tSetPoint = sinal.getTempo() - 0.1;
		flagPico = flagSettleTempo = flagSubida = false;
		oldSetPoint = setPoint;
		setPoint = newSetPoint;
		deltaSetPoint = setPoint - oldSetPoint;
		
		flagTrI = false;
		flagTrF = false;
		
		dados.gettSubida().setText("");
		dados.gettAcomoda().setText("");
		dados.gettPico().setText("");
		dados.getNivelPico().setText("");
	}
	
//Getters and Setters
	void setVp_sat(double vp_sat){
		this.vp_sat = vp_sat;
	}

	double gettVp_sat(){

		return vp_sat;
	}

	void setNivel_tanque_um( double nivel_tanque_um){

		this.nivel_tanque_um = nivel_tanque_um;
	}

	double getNivel_tanque_um(){

		return nivel_tanque_um;
	}

	void setNivel_tanque_dois( double nivel_tanque_dois){

		this.nivel_tanque_dois = nivel_tanque_dois;
	}

	double getNivel_tanque_dois(){

		return nivel_tanque_dois;
	}

}