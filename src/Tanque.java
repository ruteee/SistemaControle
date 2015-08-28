import java.lang.Thread;

import javax.swing.JLayeredPane;


public class Tanque extends Thread {

    private String servidor;
    
    private int porta;
   
	public double vps;
	public double volt = 0;
	public double erroI = 0;
	public double erroAnterior = 0;
	public double erroD;
	public double erro;
	
	private Dados dados = new Dados();
    public Chart grafico = new Chart(dados);
    public ChartNivel graficoAltura = new ChartNivel(dados);
    
    public Boolean controle = true;
    
    
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
    	int cont = 0;
    //    getConexao();
        
    	while(true){
	       	try {
	        		
	    	//	dados.setPV( quanserclient.read(dados.getPinoDeLeitura()));
	    	//	volt = dados.getPV();
	       		volt = 1;
	       		
	    		
	    		if(dados.getTipoMalha().equals("Malha Aberta")){
	    			
	    			//fila de tensão
	    			grafico.atualizarFila(new Ponto(onda.gerarPonto()));        			
	    			grafico.atualizarGrafico();
	    			painelTensao.validate();
	    			dados.setVP(grafico.filaDePontos.get(grafico.filaDePontos.size() - 1).getY()); 
	    			
	    			//fila de nivel
	    			Ponto pontoNivelAberta = new Ponto();
	    			pontoNivelAberta.setY(volt*6.25);
	    			pontoNivelAberta.setX(onda.getTempo() - 0.1);
	    			graficoAltura.atualizarFilaDeNivelUm(new Ponto(pontoNivelAberta));
	    			
	    			graficoAltura.atualizarGrafico();
	    			painelAltura.validate();
	    			
	    			
				}else if (dados.getTipoMalha().equals("Malha Fechada")){
				
					// plot do set point
					//Ponto pontoSet = new Ponto();
					Ponto pontoSet = new Ponto(onda.gerarPonto());
					
					if(pontoSet.getY() < 0){ //djabo é isso? lembrei
						pontoSet.setY(0);
					}
					
					//Controle dos erros com e sem PID
					
					graficoAltura.atualizarFilaDeSetPoint(pontoSet);
					erro = -volt*6.25 + (graficoAltura.filaDeSetPoint.get(graficoAltura.filaDeSetPoint.size() - 1).getY());
					
					Ponto erroP = new Ponto(); 
					Ponto erroI = new Ponto();
					Ponto erroD = new Ponto();
	    			
					/*//colocar radio button na interface para selcionar 
					switch (dados.getComControle()){
					
						case 0:
							dados.setVP(erro);
						break;
						
						case 1:*/
							switch (dados.getTipoDeControle()){
							
								case "P" : 
									dados.setVP(acaoP(erro));
								break;
								
								case "PI":
									dados.setVP(acaoP(erro) + acaoI(erro));
								break;
								
								case "PD":
									dados.setVP(acaoP(erro) + acaoI(erro));
								break;
								
								case "PID":
									dados.setVP(acaoP(erro) + acaoI(erro) + acaoD(erro));
								break;
								
								case "PI-D":
									dados.setVP(acaoP(erro) + acaoI(erro) + acaoD(volt));
								break;
							}		
				/*			break;	
					}*/
		
					/*if(dados.getComControle() == 1){// influencia dos controladores, plot;
*/						
			    		erroP.setX(onda.getTempo() - 0.1); 
			    		//erroP.setY(acaoP(erro));
			    		erroP.setY(2);
			    		
			    		erroI.setX(onda.getTempo() - 0.1); 
			    		erroI.setY(acaoI(erro));
			    		
			    		erroD.setX(onda.getTempo() - 0.1); 
			    		erroD.setY(acaoD(erro));
			    		
			    		graficoAltura.atualizarFilaDeErroP(erroP);
			    		graficoAltura.atualizarFilaDeErroI(erroI);
			    		graficoAltura.atualizarFilaDeErroD(erroD);
			    		
			    	/*	graficoAltura.atualizarGrafico();
		    			painelAltura.validate();*/
					/*}
					*/
	    			//fila de Erro - para uso sem controle
	    			
		    			Ponto pontoErro = new Ponto();
			    		pontoErro.setX(onda.getTempo() - 0.1); //nao sei se funciona, rever
			    		pontoErro.setY(dados.getVP());
			    		graficoAltura.atualizarFilaDeErro(pontoErro);
			    		/*graficoAltura.atualizarGrafico();
		    			painelAltura.validate();*/
		    			
		    			graficoAltura.atualizarGrafico();
		    			painelAltura.validate();
				
				}
	    		
	    		verificarRegras();
	    		//quanserclient.write(dados.getPinoDeEscrita(), dados.getVP());
	    		
	    		if(dados.getTipoMalha().equals("Malha Aberta")){
	    			//graficos de tensão
		    		Ponto ponto = new Ponto();
		    		ponto.setX(onda.getTempo() - 0.1); 
		    		ponto.setY(dados.getVP());
		    		grafico.atualizarSaturada(ponto);
		    		grafico.atualizarGrafico();
	    			painelTensao.validate();
	    			
	    		}else if (dados.getTipoMalha().equals("Malha Fechada")){
    			
	    			//grafico de tensão enviada na malha fechada (Erro saturado)
	    			
	    			Ponto erroSat = new Ponto();
		    		erroSat.setX(onda.getTempo() - 0.1); //nao sei se funciona, rever
		    		erroSat.setY(dados.getVP());
		    		grafico.atualizarErroSaturado(erroSat);
	    			
	    			grafico.atualizarGrafico();
	    			painelTensao.validate();
	    			
	    			//gráficos de nível	
	    			
	    			
	    			//plot de nivel do tanque
	    			
	    			Ponto pontoNivel = new Ponto();
	    			pontoNivel.setY(volt*6.25);
	    			pontoNivel.setX(onda.getTempo() - 0.1);
	    			graficoAltura.atualizarFilaDeNivelUm(new Ponto(pontoNivel));
	    			
	    			graficoAltura.atualizarGrafico();
	    			painelAltura.validate();
			
	    		}
	    	
				Thread.sleep(100);
				System.out.println(cont++);
			} catch (/*QuanserClientException |*/ InterruptedException e) {e.printStackTrace();}
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
