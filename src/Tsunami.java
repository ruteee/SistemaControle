import java.util.Random;


public class Tsunami {
	
	public double periodo;
	public double offset;
	public double amplitude;
	public static double tempo = 0;
	public double passo;
	public double passoNovo;
	public double contadorDePeriodo = 0;
	public double amplitudeMinima;
	public double periodoMinimo;
	
	public String tipoDeOnda;
	
	public int randomAmplitude;
	public int randomPeriodo;
	
	
	
	
	Random random = new Random();
	
	
	Ponto ponto = new Ponto();
	
	Tsunami(double periodo, double periodoMinimo, double offset, double amplitude, double amplitudeMinima,  String tipoDeOnda){

		this.periodo = periodo;
		this.periodoMinimo = periodoMinimo;
		this.offset = offset;
		this.amplitude = amplitude;
		this.amplitudeMinima = amplitudeMinima;		
		this.randomAmplitude = random.nextInt((int)((amplitude - amplitudeMinima) + 1)) + (int) amplitudeMinima;
		this.randomPeriodo = random.nextInt((int)(periodo - periodoMinimo + 1)) + (int) periodoMinimo;
		this.passoNovo  = amplitude;
		this.passo =  (2*amplitude/periodo)/10;
	
		this.tipoDeOnda = tipoDeOnda;
	}
	
	public Ponto gerarPonto(){
		
		switch (tipoDeOnda){
			
			case "Degrau":
				
				ponto.setY(amplitude + offset);
				ponto.setX(tempo);
				tempo = tempo + 0.1;
				
			break;
			
			case "Aleatória":
				
				if(contadorDePeriodo <= randomPeriodo)
					ponto.setY(randomAmplitude + offset);
				else if (contadorDePeriodo > randomPeriodo){
					
					contadorDePeriodo = 0;
					randomAmplitude = random.nextInt((int)((amplitude - amplitudeMinima) + 1)) + (int) amplitudeMinima;
					randomPeriodo = random.nextInt((int)(periodo - periodoMinimo + 1)) + (int) periodoMinimo;
				}
				
				contadorDePeriodo = contadorDePeriodo + 0.1;
				ponto.setX(tempo);
				tempo = tempo + 0.1;
				
			break;
			
			case "Quadrada":
				
				if (contadorDePeriodo <= periodo/2)
					ponto.setY(amplitude + offset);
				else if( contadorDePeriodo >= periodo/2){
					ponto.setY(-amplitude + offset);
					if(contadorDePeriodo > periodo){contadorDePeriodo = 0;}
				}
				
				ponto.setX(tempo);
				tempo = tempo + 0.1;
				contadorDePeriodo = contadorDePeriodo + 0.1;
				
			break;
			
			case "Senoidal":
				
				double frequencia = (1/periodo);
				double arg = (2*Math.PI*frequencia*tempo);
				
				ponto.setY(amplitude*(Math.sin(arg)) + offset);
				
				ponto.setX(tempo);
				tempo = tempo + 0.1;
				
			break;
			
			case "Dente de Serra":
				
				if (passoNovo  < amplitude){
					ponto.setY(passoNovo + offset);
					passoNovo += passo;
				}else{
					ponto.setY(passoNovo + offset);
					passoNovo = -amplitude;
				}
				
				ponto.setX(tempo);
				tempo = tempo + 0.1;
				
			break;
			
		}
		
		return ponto;
	}

	public double getTempo() {
		return tempo;
	}
}
