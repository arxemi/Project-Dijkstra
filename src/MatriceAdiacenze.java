public class MatriceAdiacenze {
	
	private CoppiaVertici[][] tabAdiacenze;
	private int numNodi;
	
	public MatriceAdiacenze(int numNodi){
		this.numNodi = numNodi;
		this.tabAdiacenze = new CoppiaVertici[numNodi][numNodi];
		defaultTabAdiacenze();
	}
	
	public void defaultTabAdiacenze(){
		for(int i=0; i<tabAdiacenze.length; i++){
			for(int j=0; j<tabAdiacenze.length; j++){
				if(i!=j)
					tabAdiacenze[i][j] = new CoppiaVertici(i,j,Integer.MAX_VALUE);
				else
					tabAdiacenze[i][j] = new CoppiaVertici(i,j,0);
			}
		}
	}
	
	public void addCoppiaVertici(CoppiaVertici cV){
		if(cV != null){
			if(cV.getVerticePrimo() != cV.getVerticeSecondo()){
				tabAdiacenze[cV.getVerticePrimo()-1][cV.getVerticeSecondo()-1] = cV;
				tabAdiacenze[cV.getVerticeSecondo()-1][cV.getVerticePrimo()-1] = cV;
			}
		}
	}
	
	public void stmpTabAdiacenze(){
		for(int i=0; i<tabAdiacenze.length; i++){
			for(int j=0; j<tabAdiacenze.length; j++){
				if(tabAdiacenze[i][j].getPeso() == Integer.MAX_VALUE)
					System.out.print("\u221E ");
				else
					System.out.print(tabAdiacenze[i][j].getPeso()+" ");
			}
			System.out.print("\n");
		}
	}
	
	public void dijkstra(int nodoR){
		
		final int nodoRadice = nodoR-1; //nodo di partenza per il calcolo del cammino minimo
		int nextNodo = nodoR-1; //nodo che cambia per ogni riga scansionata
		int pesoAttuale; // varibiale di controllo per il calcolo del peso minimo in ogni riga
		int distanzaPeso = 0; //peso: counter
		
		
		CoppiaValori[][] sinkTree = new CoppiaValori[numNodi][numNodi];
		
		for(int i=0;i<numNodi;i++){
			for(int j=0;j<numNodi;j++){
				sinkTree[i][j] = new CoppiaValori();
			}
		}
		
		
		for(int j=0;j<tabAdiacenze.length;j++){
			for(int i=0;i<tabAdiacenze.length;i++){
				if(i!=nodoRadice && i!=nextNodo){
					if(tabAdiacenze[nextNodo][i].getPeso() != Integer.MAX_VALUE)
						sinkTree[j][i] = new CoppiaValori(tabAdiacenze[nextNodo][i].getPeso()+distanzaPeso,j+1);
					else
						sinkTree[j][i] = new CoppiaValori(tabAdiacenze[nextNodo][i].getPeso(),j+1);
				}
			}
			pesoAttuale = Integer.MAX_VALUE;
			for(int z=0;z<numNodi;z++){
				if(sinkTree[j][z].getDistanza() < pesoAttuale && sinkTree[j][z].getDistanza() > 0){
					nextNodo = z;
					pesoAttuale = sinkTree[j][z].getDistanza();
				}
			}
			distanzaPeso = pesoAttuale;
		}
		
		
		//stampa provvisoria
		
		for(int i=0; i<numNodi; i++){
			for(int j=0; j<numNodi; j++){
				if(j != nodoRadice){
					if(sinkTree[i][j].getDistanza() == Integer.MAX_VALUE)
						System.out.print("\u221E ");
					else
						System.out.print(sinkTree[i][j].getDistanza()+" ");
				}
			}
			System.out.print("\n");
		}
		
		System.out.print("\n");
		
		int priorNodo = 0; // nodo precedente 
		for(int j=0; j<numNodi; j++){
			int valoreMinimo = Integer.MAX_VALUE;
			if(j!=nodoRadice){
				for(int i=0; i<numNodi; i++){
					if(sinkTree[i][j].getDistanza() < valoreMinimo && sinkTree[i][j].getDistanza() != 0){
						valoreMinimo = sinkTree[i][j].getDistanza();
						priorNodo = sinkTree[i][j].getNodoPrecedente();
					}
				}
				System.out.print(valoreMinimo+","+priorNodo+" - ");
			}
		}
		
	}
	
	/*
	public static void main(String[] argv){
		MatriceAdiacenze ma = new MatriceAdiacenze(6);
		
		ma.addCoppiaVertici(new CoppiaVertici(1, 2, 1));
		ma.addCoppiaVertici(new CoppiaVertici(1, 6, 2));
		ma.addCoppiaVertici(new CoppiaVertici(1, 5, 5));
		ma.addCoppiaVertici(new CoppiaVertici(2, 3, 1));
		ma.addCoppiaVertici(new CoppiaVertici(2, 5, 3));
		ma.addCoppiaVertici(new CoppiaVertici(2, 6, 2));
		ma.addCoppiaVertici(new CoppiaVertici(3, 5, 1));
		ma.addCoppiaVertici(new CoppiaVertici(3, 4, 2));
		ma.addCoppiaVertici(new CoppiaVertici(4, 5, 5));
		ma.addCoppiaVertici(new CoppiaVertici(5, 6, 3));
		
		ma.stmpTabAdiacenze();
		
		System.out.print("\n");
		
		ma.dijkstra(1);
		
	}*/
}
