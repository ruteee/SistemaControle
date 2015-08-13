
public class Ponto {
	
	private double x;
	private double y;
	
	Ponto(){
	}
	
	Ponto(Ponto p){
		this.x = p.getX();
		this.y = p.getY();
	}
	
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getY() {
		return y;
	}
	
	
	

}
