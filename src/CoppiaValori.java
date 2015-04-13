public class CoppiaValori {
	private int distanza;
	private int nodoPrecedente;
	
	public CoppiaValori(){}
	
	public CoppiaValori(int distanza, int nodoPrecedente){
		this.distanza = distanza;
		this.nodoPrecedente = nodoPrecedente;
	}
	
	public int getDistanza(){
		return distanza;
	}
	
	public int getNodoPrecedente(){
		return nodoPrecedente;
	}
}
