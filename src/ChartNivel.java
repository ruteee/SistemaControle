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

public class ChartNivel {


	public LinkedList<Ponto> filaDeNivelUm = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeSetPoint = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeErro = new LinkedList<Ponto>();
	
	Ponto ponto = new Ponto();
	public ChartPanel  painelG2;
	public Dados dados = new Dados();
	

	
	
	public ChartNivel(Dados dados) {
		XYDataset dataset = criarDataset();
		JFreeChart graph = criarGrafico(dataset);
		this.painelG2 = new ChartPanel(graph);
		
				
	}	

	public void atualizarGrafico(){
		painelG2.setChart(criarGrafico(criarDataset()));
	}
	
	public void atualizarFilaDeNivelUm(Ponto ponto){
		
			filaDeNivelUm.addLast(ponto);
			
			if (filaDeNivelUm.size() > 40) filaDeNivelUm.removeFirst();
		
	}
	
	public void atualizarFilaDeSetPoint(Ponto ponto){
		
		filaDeSetPoint.addLast(ponto);
		
		if (filaDeSetPoint.size() > 40) filaDeSetPoint.removeFirst();
	
}

	public void atualizarFilaDeErro(Ponto ponto){
		
			filaDeErro.addLast(ponto);
			
			if (filaDeErro.size() > 40) filaDeErro.removeFirst();
		
	}
		
	public XYDataset criarDataset()
	{

		XYSeries serieNivelUm = new XYSeries("NivelUm");
		XYSeries serieSetPoint = new XYSeries("SetPoint");
		XYSeries serieErro = new XYSeries("Erro");
		
		//isso trava a thread?
		
		for (int i = 0; i < filaDeNivelUm.size(); i++)
			serieNivelUm.add(filaDeNivelUm.get(i).getX(), filaDeNivelUm.get(i).getY());
		
		for (int i = 0; i < filaDeSetPoint.size(); i++)
			serieSetPoint.add(filaDeSetPoint.get(i).getX(), filaDeSetPoint.get(i).getY());
		
		for (int i = 0; i < filaDeErro.size(); i++)
			serieErro.add(filaDeErro.get(i).getX(), filaDeErro.get(i).getY());
		
		XYSeriesCollection dataset= new XYSeriesCollection();
		dataset.addSeries(serieNivelUm);
		dataset.addSeries(serieSetPoint);
		dataset.addSeries(serieErro);
		
		
		return dataset;
	}
	
	public JFreeChart criarGrafico(XYDataset dataset){
		
		JFreeChart graph = null;
		
		graph = ChartFactory.createXYLineChart("Gráfico de Nível", "segundos", "centimetros", dataset, PlotOrientation.VERTICAL, false, false, false);
		graph.getXYPlot().setBackgroundPaint(Color.white);
			
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		
		//Nivel
		renderer.setSeriesShapesVisible(0, false);
		renderer.setSeriesLinesVisible(0, true);
		renderer.setSeriesPaint(0, Color.BLACK);
		
		//SetPoint
		renderer.setSeriesShapesVisible(1, false);
		renderer.setSeriesLinesVisible(1, true);
		renderer.setSeriesPaint(1, Color.RED);
		
		//Erro
		renderer.setSeriesShapesVisible(2, false);
		renderer.setSeriesLinesVisible(2, true);
		renderer.setSeriesPaint(2, Color.GREEN);
		
        graph.getXYPlot().setRenderer(renderer);
        
		return graph;
	}
	


}
