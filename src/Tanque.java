import java.lang.Thread;

import javax.swing.JLayeredPane;


public class Tanque extends Thread {

    private String servidor;
    
    private int porta;
   
	public double vps;
	public double volt = 0;
	public static  double erroI = 0;
	public static  double erroAnterior = 0;
	public double erroD;
	public double erro;
	
	private Dados dados = new Dados();
    public Chart grafico = new Chart(dados);
    public ChartNivel graficoAltura = new ChartNivel(dados);
    
    public Boolean controle = true;
    
    public Ponto vp;
    
    
	QuanserClient quanserclient;
	JLayeredPane painelTensao, painelAltura;
	Tsunami onda = new Tsunami(dados.getPeriodo(), dados.getPeriodoMinino(),dados.getOffset(),dados.getAmplitude(),dados.getAmplitudeMinima(),dados.getTipoSinal());

	
	public Tanque() {
    }
	
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
        
    	while(true){
	       	try {
	        		
	    		dados.setPV( quanserclient.read(dados.getPinoDeLeitura1()));
	    		volt = dados.getPV();
	       		//volt = 1;
	       		
	    		
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
					//Ponto pontoSet = new Ponto();
					
					//sinal de controle do set-point
					Ponto pontoSet = new Ponto(onda.gerarPonto());
					
					if(pontoSet.getY() < 0){
						pontoSet.setY(0);
					}
					//Controle dos erros com e sem PID
					
					graficoAltura.atualizarFilaDeSetPoint(pontoSet);
					erro = -volt*6.25 + (graficoAltura.filaDeSetPoint.get(graficoAltura.filaDeSetPoint.size() - 1).getY());
					
	    			
					//colocar radio button na interface para selcionar 
					
					Ponto vpSemControle = new Ponto();
					vpSemControle.setX(onda.getTempo() - 0.1);
					vpSemControle.setY(erro);
					graficoAltura.atualizarFilaDeErroMesmo(vpSemControle);
					//painelAltura.validate();
					
					
					
//					Ponto vp;
					
					
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
							dados.setVP(acaoP(erro) + acaoI(erro));
							
							vp = new Ponto();
				    		vp.setX(onda.getTempo() - 0.1); 
				    		vp.setY(dados.getVP());
				    		
						break;
						
						case "PID":
							dados.setVP(acaoP(erro) + acaoI(erro) + acaoD(erro));
							
							vp = new Ponto();
				    		vp.setX(onda.getTempo() - 0.1); 
				    		vp.setY(dados.getVP());
				    		
						break;
						
						case "PI-D":
							dados.setVP(acaoP(erro) + acaoI(erro) + acaoD(volt));
							
							vp = new Ponto();
							vp.setX(onda.getTempo() - 0.1); 
				    		vp.setY(dados.getVP());
						break;
						
						case "Sem Controle":
							dados.setVP(erro);
							
							Ponto erroPonto = new Ponto();
				    		erroPonto.setX(onda.getTempo() - 0.1); 
				    		erroPonto.setY(dados.getVP());
				    		graficoAltura.atualizarFilaDeErroMesmo(erroPonto);
						break;
						
						default:
							vp = new Ponto();
						break;
					}
					
					graficoAltura.atualizarFilaDeVP(vp);
					
					Ponto justP;
					Ponto justI;
					Ponto justD;
					
					//set das filas das ações separadas
					switch (dados.getTipoDeControle()){
					
						case "P":
							
							justP = new Ponto();
							justP.setX(onda.getTempo() - 0.1);
							justP.setY(acaoP(erro));
							graficoAltura.atualizarFilaDeErroP(justP);
							
						break;
						
						case "PI":
							
							justP = new Ponto();
							justP.setX(onda.getTempo() - 0.1);
							justP.setY(acaoP(erro));
							
							justI = new Ponto();
							justI.setX(onda.getTempo() - 0.1);
							justI.setY(acaoI(erro));
							
							graficoAltura.atualizarFilaDeErroP((justP));
							graficoAltura.atualizarFilaDeErroI((justI));
							
						break;
						
						case "PD":
							
							justP = new Ponto();
							justP.setX(onda.getTempo() - 0.1);
							justP.setY(acaoP(erro));
							
							justD = new Ponto();
							justD.setX(onda.getTempo() - 0.1);
							justD.setY(acaoD(erro));
							
							graficoAltura.atualizarFilaDeErroP(justP);
							graficoAltura.atualizarFilaDeErroD(justD);
						
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
							justD.setY(acaoD(erro));
							
							graficoAltura.atualizarFilaDeErroP(justP);
							graficoAltura.atualizarFilaDeErroI(justI);
							graficoAltura.atualizarFilaDeErroD(justD);
							
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
							justD.setY(acaoD(volt));
							
							graficoAltura.atualizarFilaDeErroP(justP);
							graficoAltura.atualizarFilaDeErroI(justI);
							graficoAltura.atualizarFilaDeErroD(justD);
							
						
						break;
					}
	    			
					graficoAltura.atualizarGrafico();
	    			painelAltura.validate();
				
				}
	    		
	    		verificarRegras();
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
			if (volt*6.25 > 28 && dados.getVP() > 3.15)
			    vps = 3.15;

			//LHH
			if (volt*6.25 > 29 && dados.getVP() > 0) 
			    vps = 0;
	
			//LL
			if (volt*6.25 < 4 && dados.getVP()< 0)
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
		erroI = erroI + dados.getKI()*erro;
		return erroI;	
	}
	
	public double acaoD(double erro){
		
		erroD = dados.getKD()*((erro - erroAnterior)/(0.1));
		erroAnterior = erro;
		
		return erroD;
	}
}
