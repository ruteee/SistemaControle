import java.lang.Thread;
import javax.swing.JLayeredPane;
//import javax.swing.SingleSelectionModel;

public class Tanque extends Thread{
	
	private Dados dados = new Dados();
	private ChartNivel grafico_nivel = new ChartNivel(dados);
	private Chart grafico_controle = new Chart(dados);;
	private Tsunami sinal = new Tsunami(dados.getPeriodo(), dados.getPeriodoMinino(),dados.getOffset(),dados.getAmplitude(),dados.getAmplitudeMinima(),dados.getTipoSinal());
	QuanserClient quanserclient;

	private double vp_sat;
	public double nivel_tanque_um;
	public double nivel_tanque_dois;
	public double nivel_coringa;
	public double erro;
	public double erro_controlador_dois;
	public double tempo = 0;

	public double t_pico = 0;
	public double t_acomoda = 0; 
	public double t_subida = 0;
    public double t_subida_i;
    public double t_subida_f;

	private String servidor;
	private int porta;
	JLayeredPane painelTensao, painelAltura;
	
	public double setPoint;
	public double newSetPoint;
	public double oldSetPoint = 0;
	public double deltaSetPoint = 0;
	public double settleTempo;
	public double tSetPoint = 0;
	public double nivel_passado = 0;
	public double nivel_pico = 0;
	public double nivel_um;
	public double nivel_dois;

	public boolean controle = true;
    public boolean flagSubida = false;
    public boolean flagPico = false;
    public boolean flagSobreSinal = false;
    public boolean flagSettleTempo = false;
    public boolean flagTrI = false;
    public boolean flagTrF = false;

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
        	System.out.println("Conexão realizada!");
        
        return quanserclient;
    }
	
	  public Tanque(String servidor, int porta) {
	        this.servidor = servidor;
	        this.porta = porta;

	    }

	//setar todos os atributos na interface como zero se não for ser utilizado;
	void test(){
		dados.setTipoMalha("Malha Fechada");
		dados.setTipoSinal("Degrau");
		dados.setTipoDeControle("Simples");
		dados.setTipoDeControlador("PID");
		dados.setAmplitude(20);
		dados.setFaixa5(true);
		dados.setKP(2);
		dados.setKI(0.05);
		dados.setKD(0.005);
		dados.setFatInf(0);
		dados.setFatSup(1);
		dados.setPicoAbs(true);
		
		dados.setTanque2(true);
		dados.setPinoDeEscrita(0);
		dados.setPinoDeLeitura1(0);
		dados.setPinoDeLeitura2(1);
		sinal = new Tsunami(dados.getPeriodo(), dados.getPeriodoMinino(),dados.getOffset(),dados.getAmplitude(),dados.getAmplitudeMinima(),dados.getTipoSinal());
	}
	
	void secarTanque(){
		
		while(nivel_tanque_um != 0){}
		while(nivel_tanque_dois != 0){}
	}
	void limparTela(){
		grafico_controle.filaDeErroD.clear();
		grafico_controle.filaDeErroD_c2.clear();
		grafico_controle.filaDeErroI.clear();
		grafico_controle.filaDeErroI_c2.clear();
		grafico_controle.filaDeVP.clear();
		grafico_controle.filaDeVPSaturado.clear();
		grafico_controle.filaDeErroP_c2.clear();
		grafico_controle.filaDeErroP.clear();
		grafico_controle.sinalCascata.clear();
		
		
		grafico_nivel.filaDeErro_c1.clear();
		grafico_nivel.filaDeErroMesmo.clear();
		grafico_nivel.filaDeNivelDois.clear();
		grafico_nivel.filaDeNivelUm.clear();
		grafico_nivel.filaDeSetPoint.clear();
		
		
		
		grafico_nivel.atualizarGrafico();
		grafico_controle.atualizarGrafico();
		painelAltura.validate();
		painelTensao.validate();
	}
	public void run(){
		
		Controlador  controladorUm = new Controlador(dados.getKP(), dados.getKI(),
				dados.getKD(), dados.getTt(), dados.isWindUP()); 
		
		Controlador  controladorDois = new Controlador(dados.getKpEscravo(), dados.getKiEscravo(),
				dados.getKdEscravo(), dados.getTt(), dados.isWindUpEscravo()); 
		//setServer("10.13.99.69", 20081);
		//test();

		getConexao();
		
		while(true){

			try {
				System.out.println("Ininicio");
				//System.out.println(dados.getTipoDeControle());
				//System.out.println(dados.getTipoDeControlador());		
				
			
				
				dados.setPV(quanserclient.read(dados.getPinoDeLeitura1()));
				dados.setPV_two(quanserclient.read(dados.getPinoDeLeitura2()));
				
				nivel_tanque_um = 6.25*dados.getPV();
				nivel_tanque_dois = 6.25*dados.getPV_two();
				
				while(!dados.isTanque_Seco())
				{
					quanserclient.write(0, 0);
					nivel_um = quanserclient.read(0);
					nivel_dois = quanserclient.read(0);
					if(nivel_um == 0 && nivel_dois == 0){
						dados.setTanque_Seco(true);
					}
				}
				
				if(dados.isTanque1()){
					nivel_coringa = nivel_tanque_um;
				}else{
					nivel_coringa = nivel_tanque_dois;
				}
				
				if(dados.getTipoMalha().equals("Malha Aberta")){
					
					dados.setVP(sinal.gerarPonto().getY());
					Ponto pto_nivel_tanque_um = new Ponto();
					Ponto pto_nivel_tanque_dois = new Ponto();
					Ponto pto_vp = new Ponto();
					
					
					
					//nivelTanque.setX(sinal.getTempo() - 0.1);
					//nivelTanque.setY(nivel_coringa);
					
					pto_nivel_tanque_um.setX(sinal.getTempo() - 0.1);
					pto_nivel_tanque_um.setY(nivel_tanque_um);
					
					pto_nivel_tanque_dois.setX(sinal.getTempo() - 0.1);
					pto_nivel_tanque_dois.setY(nivel_tanque_dois);
					
					grafico_nivel.atualizarFilaDeNivelUm(pto_nivel_tanque_um);
					grafico_nivel.atualizarFilaDeNivelDois(pto_nivel_tanque_dois);
					pto_vp.setX(sinal.getTempo() - 0.1);
					pto_vp.setY(dados.getVP());
					grafico_controle.atualizarFilaDeVP(pto_vp);

					
					verificarRegras();
					Ponto pto_vp_sat = new Ponto();
					pto_vp_sat.setY(dados.getVP());
					pto_vp_sat.setX(sinal.getTempo() - 0.1);
					grafico_controle.atualizarDeVPSaturado(pto_vp_sat);		
					
				}else if(dados.getTipoMalha().equals("Malha Fechada")){
					
					//Ponto que irá popular o gráfico do setPoint.
					Ponto pto_setPoint = new Ponto(sinal.gerarPonto());
					if(pto_setPoint.getY() < 0)
						pto_setPoint.setY(0);
					//System.out.println(dados.isSetPoint());
					
					newSetPoint = pto_setPoint.getY();
					
					grafico_nivel.atualizarFilaDeSetPoint(pto_setPoint);
					erro =  grafico_nivel.filaDeSetPoint.get(grafico_nivel.filaDeSetPoint.size() - 1).getY() - nivel_coringa;
					
					Ponto pto_nvl_tanque_um_clsd = new Ponto();
					Ponto pto_nvl_tanque_dois_clsd = new Ponto();
					
					pto_nvl_tanque_um_clsd.setX(sinal.getTempo() - 0.1);
					pto_nvl_tanque_um_clsd.setY(nivel_tanque_um);
					
					pto_nvl_tanque_dois_clsd.setX(sinal.getTempo() - 0.1);
					pto_nvl_tanque_dois_clsd.setY(nivel_tanque_dois);
					
					grafico_nivel.atualizarFilaDeNivelUm(pto_nvl_tanque_um_clsd);
					grafico_nivel.atualizarFilaDeNivelDois(pto_nvl_tanque_dois_clsd);
					
					if(!dados.getTipoSinal().equals("Dente de serra") && !dados.getTipoSinal().equals("Senoidal")){

						if (setPoint != newSetPoint)
							sp_mudou();
						
						if (!flagSubida)
							tempoSubida();
						
						tempoPico();
						settleTempo();

	    				
						nivel_passado = nivel_coringa;
	    			}
					
					if(dados.getTipoDeControle().equals("Sem Controle")){
						dados.setVP(erro);
						
						Ponto pto_sem_controle = new Ponto();
						pto_sem_controle.setY(dados.getVP());
						pto_sem_controle.setX(sinal.getTempo() - 0.1);
						grafico_controle.atualizarFilaDeVP(pto_sem_controle);								
						
					}else if (dados.getTipoDeControle().equals("Simples")){	
						
						Ponto pto_erro_ct_smpl = new Ponto();
						pto_erro_ct_smpl.setX(sinal.getTempo() - 0.1);
						pto_erro_ct_smpl.setY(erro);
						grafico_nivel.atualizarFilaDeErroMesmo(pto_erro_ct_smpl);
						
						
						if(dados.getTipoDeControlador().equals("PI-D")){
							dados.setVP(controladorUm.calcularAcao(erro, nivel_coringa));
						}else{dados.setVP(controladorUm.calcularAcao(erro, 0));}
						
						Ponto pto_controle_simples = new Ponto();
						pto_controle_simples.setY(dados.getVP());
						pto_controle_simples.setX(sinal.getTempo() - 0.1);
						
						grafico_controle.atualizarFilaDeVP(pto_controle_simples);
						
						//Plot das ações
						
						Ponto pto_acaoP = new Ponto(); 
						Ponto pto_acaoI = new Ponto();
						Ponto pto_acaoD = new Ponto();
						
						pto_acaoP.setY(controladorUm.getProporcional());
						pto_acaoI.setY(controladorUm.getIntegral());
						pto_acaoD.setY(controladorUm.getDerivada());
						
						tempo = sinal.getTempo() - 0.1;
				
						pto_acaoP.setX(tempo);
						pto_acaoI.setX(tempo);
						pto_acaoD.setX(tempo);
						
						grafico_controle.atualizarFilaDeErroP(pto_acaoP);
						grafico_controle.atualizarFilaDeErroI(pto_acaoI);
						grafico_controle.atualizarFilaDeErroD(pto_acaoD);
						
						
						
						controladorUm.setControleNaoSaturado(dados.getVP());
						
						verificarRegras();
						Ponto pto_vp_sat_simples = new Ponto();
						pto_vp_sat_simples.setY(dados.getVP());
						pto_vp_sat_simples.setX(sinal.getTempo() - 0.1);
						grafico_controle.atualizarDeVPSaturado(pto_vp_sat_simples);
						
						controladorUm.setControleAnteriorSaturado(dados.getVP());
						
						
							
					}else if(dados.getTipoDeControle().equals("Cascata")){
						
						double erro_tanque_dois = grafico_nivel.filaDeSetPoint.getLast().getY() - nivel_tanque_dois;
						if(dados.getTipoDeControlador().equals("PI-D")){
							erro_controlador_dois = controladorUm.calcularAcao(erro_tanque_dois, nivel_tanque_dois) - nivel_tanque_um;
							dados.setVP(controladorDois.calcularAcao(erro_controlador_dois, nivel_tanque_um));
						}else{	
							erro_controlador_dois = controladorUm.calcularAcao(erro_tanque_dois, 0) - nivel_tanque_um;
							dados.setVP(controladorDois.calcularAcao(erro_controlador_dois, 0));
						}
						tempo = sinal.getTempo() - 0.1;
						
						//erros
						
						Ponto pto_erro_c1 = new Ponto();
						pto_erro_c1.setY(erro_tanque_dois);
						pto_erro_c1.setX(tempo);
						grafico_nivel.atualizarFilaDeErro_c1(pto_erro_c1);
						
						Ponto pto_erro_c2 = new Ponto();
						pto_erro_c2.setY(erro_controlador_dois);
						pto_erro_c2.setX(tempo);
						grafico_nivel.atualizarFilaDeErroMesmo(pto_erro_c2);
						
						Ponto pto_P_c1 = new Ponto();
						Ponto pto_I_c1 = new Ponto();
						Ponto pto_D_c1 = new Ponto();
						Ponto pto_sinal_cascata = new Ponto();
						
						pto_P_c1.setY(controladorUm.getProporcional());
						pto_I_c1.setY(controladorUm.getIntegral());
						pto_D_c1.setY(controladorUm.getDerivada());
						pto_sinal_cascata.setY(erro_controlador_dois + nivel_tanque_um);
						
						
						pto_P_c1.setX(tempo);
						pto_I_c1.setX(tempo);
						pto_D_c1.setX(tempo);
						pto_sinal_cascata.setX(tempo);					
						
						Ponto pto_P_c2 = new Ponto(); 
						Ponto pto_I_c2 = new Ponto();
						Ponto pto_D_c2 = new Ponto();
						
						
						pto_P_c2.setY(controladorDois.getProporcional());
						pto_I_c2.setY(controladorDois.getIntegral());
						pto_D_c2.setY(controladorDois.getDerivada());
						
						pto_P_c2.setX(tempo);
						pto_I_c2.setX(tempo);
						pto_D_c2.setX(tempo);
						
						
						//plot dos controles 1 e 2
						Ponto pto_vp = new Ponto();
						pto_vp.setY(dados.getVP());
						pto_vp.setX(tempo);
						grafico_controle.atualizarFilaDeVP(pto_vp); //plot do sinal antes de saturar
						
						Ponto pto_sinal_c1 = new Ponto();
						pto_sinal_c1.setY(erro_controlador_dois);
						pto_sinal_c1.setX(tempo);
						grafico_controle.atualizarFilaDeSinalCascata( pto_sinal_c1);//plot do sinal que vai para c2
						
						
						grafico_controle.atualizarFilaDeErroP(pto_P_c1);
						grafico_controle.atualizarFilaDeErroI(pto_I_c1);
						grafico_controle.atualizarFilaDeErroD(pto_D_c1);
						grafico_controle.atualizarFilaDeErroP_c2(pto_P_c2);
						grafico_controle.atualizarFilaDeErroI_c2(pto_I_c2);
						grafico_controle.atualizarFilaDeErroD_c2(pto_D_c2);
						
						controladorDois.setControleNaoSaturado(dados.getVP());
						
						verificarRegras();
						Ponto pto_vp_sat_cascata = new Ponto();
						pto_vp_sat_cascata.setY(dados.getVP());
						pto_vp_sat_cascata.setX(sinal.getTempo() - 0.1);
						grafico_controle.atualizarDeVPSaturado(pto_vp_sat_cascata);
						
						controladorDois.setControleAnteriorSaturado(dados.getVP());
						
					}
					
				}
				
				grafico_nivel.atualizarGrafico();
				grafico_controle.atualizarGrafico();
				painelAltura.validate();
				painelTensao.validate();
				
				//para calculo de windUP
			
				quanserclient.write(dados.getPinoDeEscrita(), dados.getVP());
				
				sleep(100);
			} catch (QuanserClientException | InterruptedException e) {e.printStackTrace();}
		}
	}

	public void verificarRegras(){
    	
    	setVp_sat(dados.getVP());
    	
		if (dados.getVP() > 4) setVp_sat(4);

		if (dados.getVP() < -4) setVp_sat(-4);		
		//LH
		if (nivel_tanque_um > 28 && dados.getVP() > 3.15) setVp_sat(3.15);
		//LHH
		if (nivel_tanque_um > 29 && dados.getVP() > 0) setVp_sat(0);

		//LL
		if (nivel_tanque_um < 4 && dados.getVP()< 0) setVp_sat(0);
		
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
			if(!flagTrI && nivel_coringa >= oldSetPoint + dados.getFatInf() * deltaSetPoint)
			{
				t_subida_i = sinal.getTempo() - tSetPoint - 0.2;
				flagTrI = true;
			}
			if (!flagTrF && nivel_coringa >= oldSetPoint + dados.getFatSup() * deltaSetPoint){
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
			if(!flagTrI && nivel_coringa <= oldSetPoint + dados.getFatInf() * deltaSetPoint)
			{
				t_subida_i = sinal.getTempo() - tSetPoint - 0.2;
				flagTrI = true;
			}
			if (!flagTrF && nivel_coringa <= oldSetPoint + dados.getFatSup() * deltaSetPoint){
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
			if (nivel_coringa < nivel_passado && nivel_coringa > setPoint)
			{
				t_pico = sinal.getTempo() - tSetPoint- 0.2;
				dados.gettPico().setText(String.valueOf(t_pico));
				nivel_pico = nivel_passado;
				
				
				if (dados.isPicoAbs())
					dados.getNivelPico().setText(String.valueOf((nivel_pico - setPoint)));
				else
					dados.getNivelPico().setText(String.valueOf(100 * (nivel_pico - setPoint)/(setPoint - oldSetPoint)));
				flagPico = true;
			}
		}
		else
		{
			/*System.out.println("outro");*/
			if (nivel_coringa > nivel_passado && nivel_coringa < setPoint)
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
				if (nivel_coringa > nivel_passado && nivel_coringa > setPoint && nivel_coringa > nivel_pico){
					flagPico = false;
				}
			}else{
				if (nivel_coringa < nivel_passado && nivel_coringa < setPoint && nivel_coringa < nivel_pico){
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
			if(nivel_coringa <= nivelDeComparacao + setPoint && nivel_coringa >= setPoint - nivelDeComparacao ){
					settleTempo = sinal.getTempo() - 0.1;
					flagSettleTempo = true; 
			}
		}else if(flagSettleTempo){
			
			if(!(nivel_coringa <= nivelDeComparacao + setPoint && nivel_coringa >= setPoint - nivelDeComparacao )){
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
	
	void setVp_sat(double vp_sat){this.vp_sat = vp_sat;}

	double gettVp_sat(){return vp_sat;}
	
	public void setServer(String servidor, int porta){
		this.servidor = servidor;
        this.porta = porta;
	}
	
	public Tanque() {}
}