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
	public LinkedList<Ponto> filaDeNivelDois = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeErroMesmo = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeSetPoint = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeErro_c1 = new LinkedList<Ponto>();
	
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
			
			if (filaDeNivelUm.size() > 600) filaDeNivelUm.removeFirst();
		
	}
	
	public void atualizarFilaDeSetPoint(Ponto ponto){
		
		filaDeSetPoint.addLast(ponto);
		
		if (filaDeSetPoint.size() > 600) filaDeSetPoint.removeFirst();
	
	}
	
	public void atualizarFilaDeNivelDois(Ponto ponto){
		
		filaDeNivelDois.addLast(ponto);
		
		if (filaDeNivelDois.size() > 600) filaDeNivelDois.removeFirst();
	
    }
	
	public void atualizarFilaDeErroMesmo(Ponto ponto){
		
		filaDeErroMesmo.addLast(ponto);
		
		if (filaDeErroMesmo.size() > 600) filaDeErroMesmo.removeFirst();
	
	}
	
	public void atualizarFilaDeErro_c1(Ponto ponto){
		
		filaDeErro_c1.addLast(ponto);
		
		if (filaDeErro_c1.size() > 600) filaDeErro_c1.removeFirst();
	
	}
	
	public void limparFilaDeNivelDois(){
		
		while(!filaDeNivelDois.isEmpty())
			filaDeNivelDois.remove();
	
    }
	
	public void limparFilaDeNivelUm(){
		
		while(!filaDeNivelUm.isEmpty())
			filaDeNivelUm.remove();
	
    }
	
	public void limparFilaDeErroMesmo(){
		
		while(!filaDeErroMesmo.isEmpty())
			filaDeErroMesmo.remove();
	
    }
	
	public void limparFilaDeSetPoint(){
		
		while(!filaDeSetPoint.isEmpty())
			filaDeSetPoint.remove();
	
}


		
	public XYDataset criarDataset()
	{

		XYSeries serieNivelUm = new XYSeries("NivelUm");
		XYSeries serieNivelDois = new XYSeries("Nivel 2");
		XYSeries serieErroMesmo = new XYSeries("Erro");
		XYSeries serieSetPoint = new XYSeries("SetPoint");
		XYSeries serieErro_c1 = new XYSeries("Erro controlador 2");
		
		
		for (int i = 0; i < filaDeNivelUm.size(); i++)
			serieNivelUm.add(filaDeNivelUm.get(i).getX(), filaDeNivelUm.get(i).getY());
		
		for (int i = 0; i < filaDeNivelDois.size(); i++)
			serieNivelDois.add(filaDeNivelDois.get(i).getX(), filaDeNivelDois.get(i).getY());
		
		for (int i = 0; i < filaDeErroMesmo.size(); i++)
			serieErroMesmo.add(filaDeErroMesmo.get(i).getX(), filaDeErroMesmo.get(i).getY());
		

		for (int i = 0; i < filaDeSetPoint.size(); i++)
			serieSetPoint.add(filaDeSetPoint.get(i).getX(), filaDeSetPoint.get(i).getY());
		
		for (int i = 0; i < filaDeErro_c1.size(); i++)
			serieErro_c1.add(filaDeErro_c1.get(i).getX(), filaDeErro_c1.get(i).getY());
		
		XYSeriesCollection dataset= new XYSeriesCollection();
		dataset.addSeries(serieNivelUm);
		dataset.addSeries(serieNivelDois);
		dataset.addSeries(serieErroMesmo);
		dataset.addSeries(serieSetPoint);
		dataset.addSeries(serieErro_c1);
		
		
		return dataset;
	}
	
	public JFreeChart criarGrafico(XYDataset dataset){
		
		JFreeChart graph = null;
		
		graph = ChartFactory.createXYLineChart("Gráfico de Nível", "segundos", "centimetros", dataset, PlotOrientation.VERTICAL, false, false, false);
		graph.getXYPlot().setBackgroundPaint(Color.white);
			
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		
		//Nivel 1
		renderer.setSeriesShapesVisible(0, false);
		if(dados.isNivel1())
			renderer.setSeriesLinesVisible(0, true);
		else{renderer.setSeriesLinesVisible(0, false);}
		renderer.setSeriesPaint(0, Color.BLACK);
		
		//Nivel 2
		renderer.setSeriesShapesVisible(1, false);
		if(dados.isNivel2())
			renderer.setSeriesLinesVisible(1, true);
		else{renderer.setSeriesLinesVisible(1, false);}
		renderer.setSeriesPaint(1, Color.BLUE);
		
		//Erro Mesmo
		renderer.setSeriesShapesVisible(2, false);
			if(dados.isErroMesmo()){
				renderer.setSeriesLinesVisible(2, true);
				renderer.setSeriesPaint(2, Color.PINK);
			}
			else{renderer.setSeriesLinesVisible(2, false);}
	
			
		//SetPoint
		renderer.setSeriesShapesVisible(3, false);
		if(dados.isSetPoint())
			renderer.setSeriesLinesVisible(3, true);
		else{renderer.setSeriesLinesVisible(3, false);}
		renderer.setSeriesPaint(3, Color.RED);
		
        graph.getXYPlot().setRenderer(renderer);
        
        //Erro_c1
        
        renderer.setSeriesShapesVisible(4, false);
		if(dados.isErro_c1())
			renderer.setSeriesLinesVisible(4, true);
		else{renderer.setSeriesLinesVisible(4, false);}
		renderer.setSeriesPaint(4, Color.ORANGE);
		
        graph.getXYPlot().setRenderer(renderer);
        
		return graph;
	}
	


}
