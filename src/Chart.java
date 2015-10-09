
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
	public LinkedList<Ponto> filaDeErroP_c2 = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeErroI_c2 = new LinkedList<Ponto>();
	public LinkedList<Ponto> filaDeErroD_c2 = new LinkedList<Ponto>();
	public LinkedList<Ponto> sinalCascata = new LinkedList<Ponto>();
	
	
	
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
	
	public void atualizarFilaDeErroP_c2(Ponto ponto){
		
		filaDeErroP_c2.addLast(ponto);
		
		if (filaDeErroP_c2.size() > 600) filaDeErroP_c2.removeFirst();
	
	}

	public void atualizarFilaDeErroI_c2(Ponto ponto){
		
		filaDeErroI_c2.addLast(ponto);
		
		if (filaDeErroI_c2.size() > 600) filaDeErroI_c2.removeFirst();
	
	}

	public void atualizarFilaDeErroD_c2(Ponto ponto){
		
		filaDeErroD_c2.addLast(ponto);
		
		if (filaDeErroD_c2.size() > 600) filaDeErroD_c2.removeFirst();
	
	}
	
	public void atualizarFilaDeSinalCascata(Ponto ponto){
		
		sinalCascata.addLast(ponto);
		
		if (sinalCascata.size() > 600) sinalCascata	.removeFirst();
	
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
		
		XYSeries serieErroP_c2 = new XYSeries("Erro Proporcional escravo");
		XYSeries serieErroI_c2 = new XYSeries("Erro Integral escravo");
		XYSeries serieErroD_c2 = new XYSeries("Erro Derivativo escravo");
		XYSeries serieSinalCascata = new XYSeries("Sinal Casccata escravo");

		
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
		
		
		for (int i = 0; i < filaDeErroP_c2.size(); i++)
			serieErroP_c2.add(filaDeErroP_c2.get(i).getX(), filaDeErroP_c2.get(i).getY());
		
		for (int i = 0; i < filaDeErroI_c2.size(); i++)
			serieErroI_c2.add(filaDeErroI_c2.get(i).getX(), filaDeErroI_c2.get(i).getY());
		
		for (int i = 0; i < filaDeErroD_c2.size(); i++)
			serieErroD_c2.add(filaDeErroD_c2.get(i).getX(), filaDeErroD_c2.get(i).getY());
		
		for (int i = 0; i < sinalCascata.size(); i++)
			serieSinalCascata.add(sinalCascata.get(i).getX(), sinalCascata.get(i).getY());
		
		
				
		XYSeriesCollection dataset= new XYSeriesCollection();
		dataset.addSeries(serieTensaoCalc);
		dataset.addSeries(serieSaturada);
		dataset.addSeries(serieErroP);
		dataset.addSeries(serieErroI);
		dataset.addSeries(serieErroD);
		
		dataset.addSeries(serieErroP_c2);
		dataset.addSeries(serieErroI_c2);
		dataset.addSeries(serieErroD_c2);
		dataset.addSeries(serieSinalCascata);
		
		return dataset;
	}
	
	public JFreeChart criarGrafico(XYDataset dataset){
		
		JFreeChart graph = null;
		
		graph = ChartFactory.createXYLineChart("Gráfico de Tensão", "segundos", "volts", dataset, PlotOrientation.VERTICAL, false, false, false);
		graph.getXYPlot().setBackgroundPaint(Color.white);
			
		
		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		
		
		//tensão calculada;
		renderer.setSeriesShapesVisible(0, false);
		
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
		
		
		
		//ErroP
		renderer.setSeriesShapesVisible(5, false);
		if(dados.isProporcional_c2())
			renderer.setSeriesLinesVisible(5, true);
		else{renderer.setSeriesLinesVisible(5, false);}
		renderer.setSeriesPaint(5, Color.CYAN);
		
		//ErroI
		renderer.setSeriesShapesVisible(6, false);
		if(dados.isIntegral_c2())
			renderer.setSeriesLinesVisible(6, true);
		else{renderer.setSeriesLinesVisible(6, false);}
		renderer.setSeriesPaint(6, Color.BLACK);
		
		//ERROD	
		renderer.setSeriesShapesVisible(7, false);
		
		if(dados.isDerivativo_c2())
			renderer.setSeriesLinesVisible(7, true);
		else{renderer.setSeriesLinesVisible(7, false);}
		renderer.setSeriesPaint(7, Color.GREEN);
		
		//sinalCascata C1 to C2
		renderer.setSeriesShapesVisible(8, false);
		
		if(dados.isSinalCascata())
			renderer.setSeriesLinesVisible(8, true);
		else{renderer.setSeriesLinesVisible(8, false);}
		renderer.setSeriesPaint(8, Color.PINK);
		
		
	
	
    graph.getXYPlot().setRenderer(renderer);
        
		return graph;
	}
	
}
