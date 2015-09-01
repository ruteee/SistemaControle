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
	public LinkedList<Ponto> filaDeVP = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeErroP = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeErroI = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeErroD = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeErroMesmo = new LinkedList<Ponto>();
	
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

	public void atualizarFilaDeVP(Ponto ponto){
		
			filaDeVP.addLast(ponto);
			
			if (filaDeVP.size() > 600) filaDeVP.removeFirst();
		
	}
	
	public void atualizarFilaDeErroMesmo(Ponto ponto){
		
		filaDeErroMesmo.addLast(ponto);
		
		if (filaDeErroMesmo.size() > 600) filaDeErroMesmo.removeFirst();
	
	}
	public void atualizarFilaDeErroP(Ponto ponto){
			
			filaDeErroP.addLast(ponto);
			
			if (filaDeErroP.size() > 600) filaDeErroP.removeFirst();
		
	}
	
	

	public void atualizarFilaDeErroI(Ponto ponto){
		
		filaDeErroI.addLast(ponto);
		
		if (filaDeErroI.size() > 600) filaDeErroI.removeFirst();
	
	}
	
	public void atualizarFilaDeErroD(Ponto ponto){
		
		filaDeErroD.addLast(ponto);
		
		if (filaDeErroD.size() > 600) filaDeErroD.removeFirst();
	
	}


		
	public XYDataset criarDataset()
	{

		XYSeries serieNivelUm = new XYSeries("NivelUm");
		XYSeries serieSetPoint = new XYSeries("SetPoint");
		XYSeries serieErro = new XYSeries("VP não saturado");
		XYSeries serieErroP = new XYSeries("Erro Proporcional");
		XYSeries serieErroI = new XYSeries("Erro Integral");
		XYSeries serieErroD = new XYSeries("Erro Derivativo");
		XYSeries serieErroMesmo = new XYSeries("Erro");
		
		//isso trava a thread?
		
		for (int i = 0; i < filaDeNivelUm.size(); i++)
			serieNivelUm.add(filaDeNivelUm.get(i).getX(), filaDeNivelUm.get(i).getY());
		
		for (int i = 0; i < filaDeSetPoint.size(); i++)
			serieSetPoint.add(filaDeSetPoint.get(i).getX(), filaDeSetPoint.get(i).getY());
		
		for (int i = 0; i < filaDeVP.size(); i++)
			serieErro.add(filaDeVP.get(i).getX(), filaDeVP.get(i).getY());
		
		for (int i = 0; i < filaDeErroP.size(); i++)
			serieErroP.add(filaDeErroP.get(i).getX(), filaDeErroP.get(i).getY());
		
		for (int i = 0; i < filaDeErroI.size(); i++)
			serieErroI.add(filaDeErroI.get(i).getX(), filaDeErroI.get(i).getY());
		
		for (int i = 0; i < filaDeErroD.size(); i++)
			serieErroD.add(filaDeErroD.get(i).getX(), filaDeErroD.get(i).getY());
		
		for (int i = 0; i < filaDeErroMesmo.size(); i++)
			serieErroMesmo.add(filaDeErroMesmo.get(i).getX(), filaDeErroMesmo.get(i).getY());
		
		
		
		XYSeriesCollection dataset= new XYSeriesCollection();
		dataset.addSeries(serieNivelUm);
		dataset.addSeries(serieSetPoint);
		dataset.addSeries(serieErro);
		dataset.addSeries(serieErroP);
		dataset.addSeries(serieErroI);
		dataset.addSeries(serieErroD);
		dataset.addSeries(serieErroMesmo);
		
		
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
		
		//SetPoint
		renderer.setSeriesShapesVisible(1, false);
		if(dados.isSetPoint())
			renderer.setSeriesLinesVisible(1, true);
		else{renderer.setSeriesLinesVisible(1, false);}
		renderer.setSeriesPaint(1, Color.RED);
		
		//Erro
		renderer.setSeriesShapesVisible(2, false);
		if(dados.isErro())
			renderer.setSeriesLinesVisible(2, true);
		else{renderer.setSeriesLinesVisible(2, false);}
		
		renderer.setSeriesPaint(2, Color.CYAN);
		
		//ErroP
		renderer.setSeriesShapesVisible(3, false);
		if(dados.isProporcional())
			renderer.setSeriesLinesVisible(3, true);
		else{renderer.setSeriesLinesVisible(3, false);}
		renderer.setSeriesPaint(3, Color.ORANGE);
		
		//ErroI
		renderer.setSeriesShapesVisible(4, false);
		if(dados.isIntegral())
			renderer.setSeriesLinesVisible(4, true);
		else{renderer.setSeriesLinesVisible(4, false);}
		renderer.setSeriesPaint(4, Color.MAGENTA);
		
		//ERROD	
		renderer.setSeriesShapesVisible(5, false);
		
		if(dados.isDerivativo())
			renderer.setSeriesLinesVisible(5, true);
		else{renderer.setSeriesLinesVisible(5, false);}
		renderer.setSeriesPaint(5, Color.GRAY);
		
		
		//Erro Mesmo
			renderer.setSeriesShapesVisible(6, false);
				if(dados.isErroMesmo()){
					renderer.setSeriesLinesVisible(6, true);
					renderer.setSeriesPaint(6, Color.PINK);
				}
				else{renderer.setSeriesLinesVisible(6, false);}
				
				
		
		
        graph.getXYPlot().setRenderer(renderer);
        
		return graph;
	}
	


}
