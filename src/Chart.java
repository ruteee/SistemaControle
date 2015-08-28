
import java.awt.Color;
import java.util.LinkedList;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Chart{
	
	public LinkedList<Ponto> filaDePontos = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaSaturada = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeErroSaturado = new LinkedList<Ponto>();
	Ponto ponto = new Ponto();
	public ChartPanel  painel;
	public Dados dados = new Dados();
	

	
	
	public Chart(Dados dados) {
		XYDataset dataset = criarDataset();
		JFreeChart graph = criarGrafico(dataset);
		this.painel = new ChartPanel(graph);			
	}	
	
	public void atualizarGrafico(){
		painel.setChart(criarGrafico(criarDataset()));
	}
	
	public void atualizarFila(Ponto ponto){
		
			filaDePontos.addLast(ponto);
			
			if (filaDePontos.size() > 600) filaDePontos.removeFirst();
		
	}
	public void atualizarSaturada(Ponto ponto){
		
		filaSaturada.addLast(ponto);
		
		if (filaSaturada.size() > 600) filaSaturada.removeFirst();
	
	}
	
	public void atualizarErroSaturado(Ponto ponto){
		
		filaDeErroSaturado.addLast(ponto);
		
		if (filaDeErroSaturado.size() > 600) filaDeErroSaturado.removeFirst();
	
	}
	
	
	public XYDataset criarDataset()
	{

		XYSeries serieDePlot = new XYSeries("Serie");
		XYSeries serieSaturada = new XYSeries("Saturada");
		XYSeries serieErroSaturado = new XYSeries("Erro Saturado");
	
		for (int i = 0; i < filaDePontos.size(); i++)
			serieDePlot.add(filaDePontos.get(i).getX(), filaDePontos.get(i).getY());
		
		for (int i = 0; i < filaSaturada.size(); i++)
			serieSaturada.add(filaSaturada.get(i).getX(), filaSaturada.get(i).getY());
		
		for (int i = 0; i < filaDeErroSaturado.size(); i++)
			serieErroSaturado.add(filaDeErroSaturado.get(i).getX(), filaDeErroSaturado.get(i).getY());
		
		XYSeriesCollection dataset= new XYSeriesCollection();
		dataset.addSeries(serieDePlot);
		dataset.addSeries(serieSaturada);
		dataset.addSeries(serieErroSaturado);
		
		
		return dataset;
	}
	
	public JFreeChart criarGrafico(XYDataset dataset){
		
		JFreeChart graph = null;
		
		graph = ChartFactory.createXYLineChart("Gráfico de Tensão", "segundos", "volts", dataset, PlotOrientation.VERTICAL, false, false, false);
		graph.getXYPlot().setBackgroundPaint(Color.white);
			
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		
		
		//tensão calculada;
		renderer.setSeriesShapesVisible(0, false);
		
		//if(dados.isTensao())
			renderer.setSeriesLinesVisible(0, true);
		//else{renderer.setSeriesLinesVisible(0, false);}
		
		renderer.setSeriesFillPaint(0, Color.BLUE);
		
		
		//tensao saturada, malha aberta
		renderer.setSeriesShapesVisible(1, false);
		//if(dados.isTensaoSat())
			renderer.setSeriesLinesVisible(1, true);
		//else{renderer.setSeriesLinesVisible(1, false);}
		renderer.setSeriesFillPaint(1, Color.RED);
		
		
		//tensao saturada malha fechada, rever na interface
		
		renderer.setSeriesShapesVisible(2, false);
		///if(dados.isTensaoSat() && dados.getTipoMalha().equals("Malha Fechada"))
			renderer.setSeriesLinesVisible(2, true);
	//	else{renderer.setSeriesLinesVisible(2, false);}
		renderer.setSeriesFillPaint(2, Color.MAGENTA);
		
		
        graph.getXYPlot().setRenderer(renderer);
        
		return graph;
	}
	
	public static  void main (String[] args){
		
		
		
/*		EnvioDenteDeSerra dente = new EnvioDenteDeSerra(10, 2, 40, 1);
		
		while (dente.duracao > 0)
		{
			filaDePontos.addLast(new Ponto(dente.envioDenteDeSerra())); 
		}*/
		
		/*Chart plot = new Chart("Teste");
		
		plot.pack();
		RefineryUtilities.centerFrameOnScreen(plot);
		plot.setVisible(true);	*/
		
		/*Ponto p = new Ponto();
		Ponto p1 = new Ponto();
		
		p.setY(4);
		p.setX(6);
		
		p1.setX(8);
		p1.setY(5);*/
		
		/*filaDePontos.addLast(p);
		filaDePontos.addLast(p1);
		
		plot.atualizarGrafico();*/
		
	}
		
	

}
