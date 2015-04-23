public class Vertex {
	
	private int verticePrimo;
	private int verticeSecondo;
	private int peso;
	
	public Vertex(int verticePrimo, int verticeSecondo, int peso){
		this.setVerticePrimo(verticePrimo);
		this.setVerticeSecondo(verticeSecondo);
		this.peso = peso;
	}

	public int getVerticePrimo() {
		return verticePrimo;
	}

	public void setVerticePrimo(int verticePrimo) {
		this.verticePrimo = verticePrimo;
	}

	public int getVerticeSecondo() {
		return verticeSecondo;
	}

	public void setVerticeSecondo(int verticeSecondo) {
		this.verticeSecondo = verticeSecondo;
	}
	
	public int getPeso(){
		return peso;
	}
	
}
