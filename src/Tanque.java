import java.lang.Thread;

import javax.swing.JLayeredPane;


public class Tanque extends Thread {

    private String servidor;
    
    private int porta;
   
	public double vps;
	public double volt = 0;
	public double volt1 = 0;
	public static  double IntErro = 0;
	public static  double erroAnterior = 0;
	public static double derivada = 0;
	public double controleWindUP;
	public double controleAnteriorSaturado;
	public double erroD;
	public double erro;
	
	public double setPoint;
	public double newSetPoint;
	public double oldSetPoint;
	public double settleTempo;
	
	public double nivel_passado;
	public double nivel_pico = 0;
	
	private Dados dados = new Dados();
    public Chart grafico = new Chart(dados);
    public ChartNivel graficoAltura = new ChartNivel(dados);
    
    public boolean controle = true;
    public boolean flagSubida = false;
    public boolean flagPico = false;
    public boolean flagSobreSinal = false;
    public boolean flagSettleTempo = false;

    public int faixa;
    
    public Ponto vp;
    
    double t_pico = 0, t_acomoda = 0, t_subida = 0;
   // boolean t_pico_set = false, t_acomoda_set = false, t_subida_set = false;

	QuanserClient quanserclient;
	JLayeredPane painelTensao, painelAltura;
	Tsunami onda = new Tsunami(dados.getPeriodo(), dados.getPeriodoMinino(),dados.getOffset(),dados.getAmplitude(),dados.getAmplitudeMinima(),dados.getTipoSinal());

	

	
	public Tanque() {}
	
    public Tanque(String servidor, int porta) {
        this.servidor = servidor;
        this.porta = porta;

    }
    
    public Tanque(Dados dados) {
        this.dados = dados;
    }
    

    public void run() {
    //	int cont = 0;
        getConexao();
     //  setPoint = dados.getAmplitude();
    	while(true){
	       	try {
	        		
	    		dados.setPV( quanserclient.read(dados.getPinoDeLeitura2()));
	    		volt = dados.getPV();
	    		volt1 = quanserclient.read(0);
	       		       		
	    		
	    		if(dados.getTipoMalha().equals("Malha Aberta")){
	    			
	    			//fila de tensão - Sinal de controle
	    			grafico.atualizarFila(new Ponto(onda.gerarPonto()));    
	    			
	    			grafico.atualizarGrafico();
	    			painelTensao.validate();
	    			
	    			dados.setVP(grafico.filaDePontos.get(grafico.filaDePontos.size() - 1).getY()); 
	    			
	    			//fila de nivel - nível do tanque
	    			Ponto pontoNivelAberta = new Ponto();
	    			pontoNivelAberta.setY(volt*6.25);
	    			pontoNivelAberta.setX(onda.getTempo() - 0.1);
	    			graficoAltura.atualizarFilaDeNivelUm(new Ponto(pontoNivelAberta));
	    			
	    			graficoAltura.atualizarGrafico();
	    			painelAltura.validate();
	    			
	    			
				}else if (dados.getTipoMalha().equals("Malha Fechada")){
				
					// plot do set point
					
					
					//sinal de controle do set-point
					Ponto pontoSet = new Ponto(onda.gerarPonto());
					
					if(pontoSet.getY() < 0){
						pontoSet.setY(0);
					}
					//Controle dos erros com e sem PID
					
					grafico.atualizarFilaDeSetPoint(pontoSet);
					erro = -volt*6.25 + (grafico.filaDeSetPoint.get(grafico.filaDeSetPoint.size() - 1).getY());
					
					newSetPoint = pontoSet.getY();
					
	    			if(!dados.getTipoSinal().equals("Dente de serra") && !dados.getTipoSinal().equals("Senoidal")){
	    				
					
						if (setPoint != newSetPoint)
							sp_mudou();
						
						if (!flagSubida)
							tempoSubida();
						if (!flagPico)
							tempoPico();
						if (!flagSettleTempo)
							settleTempo();

	    				//}
	    				nivel_passado = pontoSet.getY(); //??????
	    			}
					//colocar radio button na interface para selcionar 
					
					Ponto vpSemControle = new Ponto();
					vpSemControle.setX(onda.getTempo() - 0.1);
					vpSemControle.setY(erro);
					grafico.atualizarFilaDeErroMesmo(vpSemControle);

					//painelAltura.validate();
					
					//Ponto vp;
					
					switch (dados.getTipoDeControle()){
					
						case "P" : 
							dados.setVP(acaoP(erro));
							vp = new Ponto();
				    		vp.setX(onda.getTempo() - 0.1); 
				    		vp.setY(dados.getVP());
						break;
						
						case "PI":
							dados.setVP(acaoP(erro) + acaoI(erro));
							
							vp = new Ponto();
				    		vp.setX(onda.getTempo() - 0.1); 
				    		vp.setY(dados.getVP());
						break;
						
						case "PD":
							derivada = acaoD(erro);
							dados.setVP(acaoP(erro) + derivada);
							
							vp = new Ponto();
				    		vp.setX(onda.getTempo() - 0.1); 
				    		vp.setY(dados.getVP());
						break;
						
						case "PID":
							derivada = acaoD(erro);
							dados.setVP(acaoP(erro) + acaoI(erro) + derivada);
							
							vp = new Ponto();
				    		vp.setX(onda.getTempo() - 0.1); 
				    		vp.setY(dados.getVP());
						break;
						
						case "PI-D":
							derivada = acaoD(volt);
							dados.setVP(acaoP(erro) + acaoI(erro) + derivada);
							
							vp = new Ponto();
							vp.setX(onda.getTempo() - 0.1); 
				    		vp.setY(dados.getVP());
						break;
						
						case "Sem Controle":
							dados.setVP(erro);
							
							Ponto erroPonto = new Ponto();
				    		erroPonto.setX(onda.getTempo() - 0.1); 
				    		erroPonto.setY(dados.getVP());
				    		grafico.atualizarFilaDeErroMesmo(erroPonto);
						break;
						
						default:
							vp = new Ponto();
						break;
					}
					
					grafico.atualizarFilaDeVP(vp);
					
					Ponto justP;
					Ponto justI;
					Ponto justD;
					
					//set das filas das ações separadas
					switch (dados.getTipoDeControle()){
					
						case "P":
							
							Ponto base = new Ponto();
							base.setX(onda.getTempo() - 0.1);
							base.setY(0);
							
							justP = new Ponto();
							justP.setX(onda.getTempo() - 0.1);
							justP.setY(acaoP(erro));
							grafico.atualizarFilaDeErroP(justP);
							grafico.atualizarFilaDeErroD(base);
							grafico.atualizarFilaDeErroI(base);
						break;
						
						case "PI":
							
							Ponto base1 = new Ponto();
							base1.setX(onda.getTempo() - 0.1);
							base1.setY(0);
							
							justP = new Ponto();
							justP.setX(onda.getTempo() - 0.1);
							justP.setY(acaoP(erro));
							
							justI = new Ponto();
							justI.setX(onda.getTempo() - 0.1);
							justI.setY(acaoI(erro));
							
							grafico.atualizarFilaDeErroP((justP));
							grafico.atualizarFilaDeErroI((justI));
							grafico.atualizarFilaDeErroD(base1);
						break;
						
						case "PD":
							
							Ponto base2 = new Ponto();
							base2.setY(0);
							base2.setX(onda.getTempo() - 0.1);
							
							
							justP = new Ponto();
							justP.setX(onda.getTempo() - 0.1);
							justP.setY(acaoP(erro));
							
							justD = new Ponto();
							justD.setX(onda.getTempo() - 0.1);
							justD.setY(derivada);
							
							grafico.atualizarFilaDeErroP(justP);
							grafico.atualizarFilaDeErroD(justD);
							grafico.atualizarFilaDeErroI(base2);
						break;
						
						case "PID":
							
							justP = new Ponto();
							justP.setX(onda.getTempo() - 0.1);
							justP.setY(acaoP(erro));
							
							justI = new Ponto();
							justI.setX(onda.getTempo() - 0.1);
							justI.setY(acaoI(erro));
							
							justD = new Ponto();
							justD.setX(onda.getTempo() - 0.1);
							justD.setY(derivada);
							
							grafico.atualizarFilaDeErroP(justP);
							grafico.atualizarFilaDeErroI(justI);
							grafico.atualizarFilaDeErroD(justD);
						break;
						
						case "PI-D":
							
							justP = new Ponto();
							justP.setX(onda.getTempo() - 0.1);
							justP.setY(acaoP(erro));
							
							justI = new Ponto();
							justI.setX(onda.getTempo() - 0.1);
							justI.setY(acaoI(erro));
							
							justD = new Ponto();
							justD.setX(onda.getTempo() - 0.1);
							justD.setY(derivada);
							
							grafico.atualizarFilaDeErroP(justP);
							grafico.atualizarFilaDeErroI(justI);
							grafico.atualizarFilaDeErroD(justD);
						break;
					}
	    			
					graficoAltura.atualizarGrafico();
	    			painelAltura.validate();
				}
	    		controleWindUP = dados.getVP();
	    		verificarRegras();
	    		
	    		controleAnteriorSaturado = dados.getVP();
	    		quanserclient.write(dados.getPinoDeEscrita(), dados.getVP());
	    		
	    		if(dados.getTipoMalha().equals("Malha Aberta")){
	    			//graficos de tensão
		    		Ponto ponto = new Ponto();
		    		ponto.setX(onda.getTempo() - 0.1); 
		    		ponto.setY(dados.getVP());
		    		grafico.atualizarDeVPSaturado(ponto);
		    		grafico.atualizarGrafico();
	    			painelTensao.validate();
	    			
	    		}else if (dados.getTipoMalha().equals("Malha Fechada")){
	    			
	    			Ponto vpSaturado = new Ponto();
					vpSaturado.setX(onda.getTempo() - 0.1); 
					vpSaturado.setY(dados.getVP());

					grafico.atualizarDeVPSaturado(new Ponto(vpSaturado));
					grafico.atualizarGrafico();
	    			painelTensao.validate();
	    			
	    			//plot de nivel do tanque
	    			
	    			Ponto nivel = new Ponto();
	    			nivel.setY(volt*6.25);
	    			nivel.setX(onda.getTempo() - 0.1);
	    			graficoAltura.atualizarFilaDeNivelUm(new Ponto(nivel));
	    			
	    			graficoAltura.atualizarGrafico();
	    			painelAltura.validate();
	    		}
	    		
				Thread.sleep(100);
				//System.out.println(cont++);
				System.out.println(volt);
			} catch (QuanserClientException | InterruptedException e) {e.printStackTrace();}
		}
    	
    }
    
    public void verificarRegras(){
            //SaturaÃ§Ã£o
    	vps = dados.getVP();
    	
    	if (dados.getVP() > 4)
			    vps = 4;

		if (dados.getVP() < -4)
			    vps = -4;
			
			//LH
			if (volt1*6.25 > 28 && dados.getVP() > 3.15)
			    vps = 3.15;

			//LHH
			if (volt1*6.25 > 29 && dados.getVP() > 0) 
			    vps = 0;
	
			//LL
			if (volt1*6.25 < 4 && dados.getVP()< 0)
			    vps = 0;
			
			dados.setVP(vps);
			
		
			
			
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

	public Dados getDados() {
		return dados;
	}

	public void setDados(Dados dados) {
		this.dados = dados;
		this.onda = new Tsunami(this.dados.getPeriodo(), this.dados.getPeriodoMinino(),this.dados.getOffset(),this.dados.getAmplitude(),this.dados.getAmplitudeMinima(),this.dados.getTipoSinal());
		this.grafico.dados = dados;
		this.graficoAltura.dados = dados;
	}
	
	public void setDadosGrafico(Dados dados) {
		this.grafico.dados = dados;
		this.graficoAltura.dados = dados;
	}
    
    public JLayeredPane getPainelTensao() {
		return painelTensao;
	}

	public void setPainelTensao(JLayeredPane graficoTensao) {
		this.painelTensao = graficoTensao;
		this.painelTensao.add(grafico.painel, new Integer(0));
		grafico.painel.setBounds(painelTensao.getVisibleRect());
	
	}

	public JLayeredPane getPainelAltura() {
		return painelAltura;
	}

	public void setPainelAltura(JLayeredPane painelAltura) {
		this.painelAltura = painelAltura;
		this.painelAltura.add(graficoAltura.painelG2, new Integer(0));
		graficoAltura.painelG2.setBounds(painelAltura.getVisibleRect());
	}
	
	public void setServer(String servidor, int porta){
		this.servidor = servidor;
        this.porta = porta;
	}
	
	public double acaoP(double erro){
		return dados.getKP()*erro;
	}
	
	public double acaoI(double erro){
		if(!dados.isWindUP())
			IntErro = IntErro + dados.getKI()*erro*0.1;
		else{
			IntErro = IntErro + dados.getKI()*erro*0.1 + (1/(dados.getTt()))*(controleAnteriorSaturado - controleWindUP);
		}
		return IntErro;	
	}
	
	public double acaoD(double erro){
		
		erroD = dados.getKD()*((erro - erroAnterior)/(0.1));
		erroAnterior = erro;
		
		return erroD;
	}

	void tempoAcomoda(){
		if (true)
		{
			t_acomoda = onda.getTempo() - t_acomoda - 0.1;

			dados.gettAcomoda().setText(String.valueOf(t_acomoda));
			flagSettleTempo = true;
		}
	}
	
	void tempoSubida(){
		if(/*dados.getPV()*/ volt >= dados.getFatSup() * setPoint)
		{
			t_subida = onda.getTempo() - t_subida;
			dados.gettSubida().setText(String.valueOf(t_subida));
			flagSubida = true;
		}
		else if (/*dados.getPV()*/ volt <= dados.getFatInf() * setPoint)
		{
			t_subida = onda.getTempo();
		}
	}
	
	void tempoPico(){
		if (setPoint - oldSetPoint > 0){
			if (/*dados.getPV()*/ volt < nivel_passado)
			{
				t_pico = onda.getTempo() - t_pico - 0.1;
				nivel_pico = nivel_passado;
				if (dados.isPicoAbs())
					dados.getNivelPico().setText(String.valueOf(100 * (nivel_pico - setPoint)/(setPoint - oldSetPoint)));
				else
					dados.gettPico().setText(String.valueOf(t_pico));
				flagPico = true;
			}
		}
		else
		{
			if (/*dados.getPV()*/ volt > nivel_passado)
			{
				t_pico = onda.getTempo() - t_pico - 0.1;
				nivel_pico = nivel_passado;
				if (dados.isPicoAbs())
					dados.getNivelPico().setText(String.valueOf(100 * (nivel_pico - setPoint)/(setPoint - oldSetPoint)));
				else
					dados.gettPico().setText(String.valueOf(t_pico));
				flagPico = true;
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
			if(dados.getPV() <= nivelDeComparacao + setPoint && dados.getPV() >= setPoint - nivelDeComparacao ){
					settleTempo = onda.getTempo() - 0.1;
					flagSettleTempo = true; 
			}
		}


		if(flagSettleTempo)
			dados.gettAcomoda().setText(String.valueOf(settleTempo));
		else{dados.gettAcomoda().setText("");}
	}
	
	void sp_mudou(){
		//t_pico = t_subida = t_acomoda = 0;
		flagPico = flagSettleTempo = flagSubida = false;
		oldSetPoint = setPoint;
		setPoint = newSetPoint;
		dados.gettSubida().setText("");
		dados.gettAcomoda().setText("");
		dados.gettPico().setText("");
		dados.getNivelPico().setText("");
	}
	
	
}