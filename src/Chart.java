
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

	public LinkedList<Ponto> filaDeVP = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeVPSaturado = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeErroP = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeErroI = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeErroD = new LinkedList<Ponto>();
	
	
	
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
	
	public void atualizarDeVPSaturado(Ponto ponto){
		
		filaDeVPSaturado.addLast(ponto);
		
		if (filaDeVPSaturado.size() > 600) filaDeVPSaturado.removeFirst();
	
	}
	
	//novass
	

	public void atualizarFilaDeVP(Ponto ponto){
		
			filaDeVP.addLast(ponto);
			
			if (filaDeVP.size() > 600) filaDeVP.removeFirst();
		
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
	
	
	
	public void limparFilaDeVP(){
		
		while(!filaDeVP.isEmpty())
			filaDeVP.remove();
	
    }
	
	public void limparFilaDeP(){
		
		while(!filaDeErroP.isEmpty())
			filaDeErroP.remove();
	
    }
	
	public void limparFilaDeI(){
			
			while(!filaDeErroI.isEmpty())
				filaDeErroI.remove();
		
	}
	
	public void limparFilaDeD(){
		
		while(!filaDeErroD.isEmpty())
			filaDeErroD.remove();
	
    }
	public XYDataset criarDataset()
	{

		//XYSeries serieDePlot = new XYSeries("Tensão calculada");
		XYSeries serieTensaoCalc = new XYSeries("Tensao Calculada");
		XYSeries serieSaturada = new XYSeries("Tensão saturada");	
		XYSeries serieErroP = new XYSeries("Erro Proporcional");
		XYSeries serieErroI = new XYSeries("Erro Integral");
		XYSeries serieErroD = new XYSeries("Erro Derivativo");

		
		for (int i = 0; i < filaDeVP.size(); i++)
			serieTensaoCalc.add(filaDeVP.get(i).getX(), filaDeVP.get(i).getY());
		
		for (int i = 0; i < filaDeVPSaturado.size(); i++)
			serieSaturada.add(filaDeVPSaturado.get(i).getX(), filaDeVPSaturado.get(i).getY());
					
		for (int i = 0; i < filaDeErroP.size(); i++)
			serieErroP.add(filaDeErroP.get(i).getX(), filaDeErroP.get(i).getY());
		
		for (int i = 0; i < filaDeErroI.size(); i++)
			serieErroI.add(filaDeErroI.get(i).getX(), filaDeErroI.get(i).getY());
		
		for (int i = 0; i < filaDeErroD.size(); i++)
			serieErroD.add(filaDeErroD.get(i).getX(), filaDeErroD.get(i).getY());
				
		XYSeriesCollection dataset= new XYSeriesCollection();
		dataset.addSeries(serieTensaoCalc);
		dataset.addSeries(serieSaturada);
		dataset.addSeries(serieErroP);
		dataset.addSeries(serieErroI);
		dataset.addSeries(serieErroD);
		
		return dataset;
	}
	
	public JFreeChart criarGrafico(XYDataset dataset){
		
		JFreeChart graph = null;
		
		graph = ChartFactory.createXYLineChart("Gráfico de Tensão", "segundos", "volts", dataset, PlotOrientation.VERTICAL, false, false, false);
		graph.getXYPlot().setBackgroundPaint(Color.white);
			
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		
		
		//tensão calculada;
		renderer.setSeriesShapesVisible(1, false);
		
		if(dados.isTensao()){
			renderer.setSeriesPaint(0, Color.BLUE);
			renderer.setSeriesLinesVisible(0, true);
		}
		else{renderer.setSeriesLinesVisible(0, false);}
		
		
		//tensão saturada
		renderer.setSeriesShapesVisible(1, false);
		if(dados.isTensaoSat()){
			renderer.setSeriesPaint(1, Color.RED);
			renderer.setSeriesLinesVisible(1, true);
		}
		else{renderer.setSeriesLinesVisible(1, false);}
		
		//Erro ///ajeitar
		/*renderer.setSeriesShapesVisible(2, false);
		if(dados.isErro())
			renderer.setSeriesLinesVisible(2, true);
		else{renderer.setSeriesLinesVisible(2, false);}
		
		renderer.setSeriesPaint(2, Color.CYAN);*/
		
		//ErroP
		renderer.setSeriesShapesVisible(2, false);
		if(dados.isProporcional())
			renderer.setSeriesLinesVisible(2, true);
		else{renderer.setSeriesLinesVisible(2, false);}
		renderer.setSeriesPaint(2, Color.ORANGE);
		
		//ErroI
		renderer.setSeriesShapesVisible(3, false);
		if(dados.isIntegral())
			renderer.setSeriesLinesVisible(3, true);
		else{renderer.setSeriesLinesVisible(3, false);}
		renderer.setSeriesPaint(3, Color.MAGENTA);
		
		//ERROD	
		renderer.setSeriesShapesVisible(4, false);
		
		if(dados.isDerivativo())
			renderer.setSeriesLinesVisible(4, true);
		else{renderer.setSeriesLinesVisible(4, false);}
		renderer.setSeriesPaint(4, Color.GRAY);
		
		
		
	
	
    graph.getXYPlot().setRenderer(renderer);
        
		return graph;
	}
	
}
