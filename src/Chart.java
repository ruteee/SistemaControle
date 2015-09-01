
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
	//public LinkedList<Ponto> filaSaturada = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeVPSaturado = new LinkedList<Ponto>();
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
	
	public void atualizarDeVPSaturado(Ponto ponto){
		
		filaDeVPSaturado.addLast(ponto);
		
		if (filaDeVPSaturado.size() > 600) filaDeVPSaturado.removeFirst();
	
	}
	
	public XYDataset criarDataset()
	{

		XYSeries serieDePlot = new XYSeries("Tensão calculada");
		XYSeries serieSaturada = new XYSeries("Tensão saturada");
		//erro saturado de malha fechada
		//XYSeries VPsaturado = new XYSeries("VP Saturado");
	
		for (int i = 0; i < filaDePontos.size(); i++)
			serieDePlot.add(filaDePontos.get(i).getX(), filaDePontos.get(i).getY());
		
		for (int i = 0; i < filaDeVPSaturado.size(); i++)
			serieSaturada.add(filaDeVPSaturado.get(i).getX(), filaDeVPSaturado.get(i).getY());
		
		/*for (int i = 0; i < filaDeVPSaturado.size(); i++)
			serieErroSaturado.add(filaDeVPSaturado.get(i).getX(), filaDeVPSaturado.get(i).getY());
		*/
		XYSeriesCollection dataset= new XYSeriesCollection();
		dataset.addSeries(serieDePlot);
		dataset.addSeries(serieSaturada);
		//dataset.addSeries(serieErroSaturado);
		
		
		return dataset;
	}
	
	public JFreeChart criarGrafico(XYDataset dataset){
		
		JFreeChart graph = null;
		
		graph = ChartFactory.createXYLineChart("Gráfico de Tensão", "segundos", "volts", dataset, PlotOrientation.VERTICAL, true, false, false);
		graph.getXYPlot().setBackgroundPaint(Color.white);
			
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		
		
		//tensão calculada;
		renderer.setSeriesShapesVisible(0, false);
		
		if(dados.isTensao()){
			renderer.setSeriesPaint(0, Color.BLUE);
			//renderer.setSeriesFillPaint(0, Color.BLUE);
			renderer.setSeriesLinesVisible(0, true);
		}
		else{renderer.setSeriesLinesVisible(0, false);}
		
		
		
		
		//tensao saturada, malha aberta
		renderer.setSeriesShapesVisible(1, false);
		if(dados.isTensaoSat()){
			renderer.setSeriesPaint(1, Color.RED);
			//renderer.setSeriesFillPaint(1, Color.RED);
			renderer.setSeriesLinesVisible(1, true);
		}
		else{renderer.setSeriesLinesVisible(1, false);}
		
		
		
		//tensao saturada malha fechada, rever na interface
		
		/*renderer.setSeriesShapesVisible(2, false);
		///if(dados.isTensaoSat() && dados.getTipoMalha().equals("Malha Fechada"))
			renderer.setSeriesLinesVisible(2, true);
	//	else{renderer.setSeriesLinesVisible(2, false);}
		renderer.setSeriesFillPaint(2, Color.MAGENTA);*/
		
		
        graph.getXYPlot().setRenderer(renderer);
        
		return graph;
	}
	
}
