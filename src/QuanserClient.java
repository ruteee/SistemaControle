/*
 * QuanserClient.java
 *
 * Created on 27 de Novembro de 2007, 22:35
 */



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;


/**
 * Classe responsável pela conexão com o sistema de tanques da Quanser.
 * @author Leonardo Dantas de Oliveira <lodantas@gmail.com>
 * @see QuanserClientException
 */
public class QuanserClient {

	private Socket socket;
	private String servidor;
	private int porta;
	
	private InputStream input;
	private OutputStream output;

	/**
         * Construtor da classe (único)
         * @param servidor String contendo o servidor do sistema. Ex.: "10.13.97.69"
         * @param porta Porta TCP de conexão. A padrão é 20072
         */
	public QuanserClient(String servidor, int porta) throws QuanserClientException {
		this.servidor = servidor;
		this.porta = porta;
		try {
			this.socket = new Socket(this.servidor, this.porta);
			this.input = this.socket.getInputStream();
			this.output = this.socket.getOutputStream();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			throw new QuanserClientException("Host n�o encontrado!");
		} catch (IOException e) {
			throw new QuanserClientException("Erro de I/O!");
			// TODO Auto-generated catch block
		}
		
		
	}
	
       /** 
         *Le o valor em volts que está no canal especificado.
         * @param  channel Canal que deverá ter o valor lido.
         */
	public double read(int channel) throws QuanserClientException {
		sendLine("READ " + channel + " ");
		String _toReturn = receiveLine();
		try {
			return Double.parseDouble(_toReturn);
		}
		catch (NumberFormatException ex){
			throw new QuanserClientException("Erro ao receber dados. Erro de conversao de tipo (Not Float/Double)");
		}
	}
       /**
         * Grava o valor especificado no canal. (Em volts)
         * @param channel Canal que deverá ter o valor gravado.
         * @param value Valor a ser gravado no canal (double - em volts)
         */
	public void write(int channel, double value) throws QuanserClientException{
		sendLine("WRITE " + channel + " " + value);
		String _toReturn = receiveLine();
		if (!_toReturn.contains("ACK")){
			throw new QuanserClientException("Erro ao gravar na placa. Erro: ["+_toReturn+"]");
		}
	}
	
	private void sendLine(String _toSend) throws QuanserClientException{
		try {
			if (!_toSend.contains("\n")){
				_toSend += "\n";
			}
			BufferedWriter _bufferedWriter = 
				new BufferedWriter(new OutputStreamWriter(this.output));
			_bufferedWriter.write(_toSend);
			_bufferedWriter.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new QuanserClientException("Erro de I/O ao enviar dados.");
		}
	}
	
	private String receiveLine() throws QuanserClientException{
		BufferedReader _bufferedReader = 
			new BufferedReader(new InputStreamReader(this.input));

		try {
			return _bufferedReader.readLine();
		} 
		catch (IOException e) {
			throw new QuanserClientException("Erro de I/O ao receber dados");
		}
	}

}
