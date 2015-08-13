import java.lang.Thread;

import javax.swing.JLayeredPane;


public class Tanque extends Thread {

    private String servidor;
    
    private int porta;
   
	public double vps;
	public double volt = 0;
	
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
        getConexao();
        
    	while(true){
	       	try {
	        		
	    		dados.setPV( quanserclient.read(dados.getPinoDeLeitura()));
	    		volt = dados.getPV();
	       		
	    		
	    		if(dados.getTipoMalha().equals("Malha Aberta")){
	    			
	    			grafico.atualizarFila(new Ponto(onda.gerarPonto()));        			
	    			grafico.atualizarGrafico();
	    			painelTensao.validate();
	    			dados.setVP(grafico.filaDePontos.get(grafico.filaDePontos.size() - 1).getY()); 
	    			
	    			
				}else if (dados.getTipoMalha().equals("Malha Fechada")){
				
					// plot do set point
					graficoAltura.atualizarFilaDeSetPoint(new Ponto(onda.gerarPonto()));
					
					
	    			dados.setVP(-volt*6.25 + (graficoAltura.filaDeSetPoint.get(graficoAltura.filaDeSetPoint.size() - 1).getY()));
	    			
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
		    		grafico.atualizarSaturada(ponto);
		    		grafico.atualizarGrafico();
	    			painelTensao.validate();
	    			
	    		}else if (dados.getTipoMalha().equals("Malha Fechada")){
    			
	    			//gráficos de nível
	    			
	    			//plot de erro
	    			Ponto pontoErro = new Ponto();
		    		pontoErro.setX(onda.getTempo() - 0.1); //nao sei se funciona, rever
		    		pontoErro.setY(dados.getVP());
		    		graficoAltura.atualizarFilaDeErro(pontoErro);
		    		graficoAltura.atualizarGrafico();
	    			painelAltura.validate();
	    			
	    			//plot de nivel -- precisa ser no formato de onda?
	    			
	    			Ponto pontoNivel = new Ponto();
	    			pontoNivel.setY(volt*6.25);
	    			pontoNivel.setX(onda.getTempo() - 0.1);
	    			graficoAltura.atualizarFilaDeNivelUm(new Ponto(pontoNivel));
	    			
	    			graficoAltura.atualizarGrafico();
	    			painelAltura.validate();
			
	    		}
	    	
				Thread.sleep(100);
				System.out.println(cont++);
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
        	System.out.println("Conexão realizada!");
        
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
}
