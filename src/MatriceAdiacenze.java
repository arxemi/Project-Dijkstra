public class MatriceAdiacenze {
	
	private Vertex[][] tabAdiacenze;
	private int numNodi;
	
	public MatriceAdiacenze(int numNodi){
		this.numNodi = numNodi;
		this.tabAdiacenze = new Vertex[numNodi][numNodi];
		defaultTabAdiacenze();
	}
	
	public void defaultTabAdiacenze(){
		for(int i=0; i<tabAdiacenze.length; i++){
			for(int j=0; j<tabAdiacenze.length; j++){
				if(i!=j)
					tabAdiacenze[i][j] = new Vertex(i,j,Integer.MAX_VALUE);
				else
					tabAdiacenze[i][j] = new Vertex(i,j,0);
			}
		}
	}
	
	public void addVertex(Vertex cV){
		if(cV != null && cV.getVerticePrimo() != cV.getVerticeSecondo()){
			tabAdiacenze[cV.getVerticePrimo()-1][cV.getVerticeSecondo()-1] = cV;
			tabAdiacenze[cV.getVerticeSecondo()-1][cV.getVerticePrimo()-1] = cV;
		}
	}
	
	public void stmpTabAdiacenze(){
		for(int i=0; i<tabAdiacenze.length; i++){
			for(int j=0; j<tabAdiacenze.length; j++){
				if(tabAdiacenze[i][j].getPeso() == Integer.MAX_VALUE)
					System.out.print("\u221E   ");
				else
					System.out.print(tabAdiacenze[i][j].getPeso()+"   ");
			}
			System.out.print("\n");
		}
	}

	public int[] dijkstra(int nodoR){
		//costante, nodo radice
		final int nodoRadice = nodoR-1;


		int nextNodo = nodoRadice, pesoAttuale, distanzaPeso = 0;

		// dichiarazione di due vettori, etichette temporanee e permanenti
		int et_perm[] = new int[numNodi];
		int et_temp[] = new int[numNodi];

		//inizializzazione vettore etichette temporanee
		for(int i=0;i<numNodi;i++) et_temp[i] = 999;
		for(int i=0;i<numNodi;i++) et_perm[i] = 999;

		// il nodo radice viene subito inserito nelle etichette permanenti con valore 0
		et_perm[nodoRadice] = 0;

		// iterazione continua fino a quando il vettore etichette permanenti non viene riempito tutto
		while(empty(et_perm)){
			for(int i=0;i<numNodi;i++){
				if(tabAdiacenze[nextNodo][i].getPeso()+distanzaPeso < et_temp[i] && tabAdiacenze[nextNodo][i].getPeso()+distanzaPeso > 0){
					et_temp[i] = tabAdiacenze[nextNodo][i].getPeso()+distanzaPeso;
				}
			}
			pesoAttuale = 999;
			for(int i=0;i<numNodi;i++){
				if(et_temp[i] < pesoAttuale && ctrlNodo(et_perm,i)){
					pesoAttuale = et_temp[i];
					nextNodo = i;
				}
			}
			et_perm[nextNodo] = pesoAttuale;
			distanzaPeso = pesoAttuale;
		}

		return et_perm;
	}

	private boolean ctrlNodo(int vet[],int nodo){
		boolean flag = false;
		if(vet[nodo] == 999){
			flag = true;
		}
		return flag;
	}

	//verifica se il vettore etichette permanenti e ancora vuoto o meno
	private boolean empty(int vet[]){
		boolean flag = false;
		for(int i=0;i<numNodi;i++){
			if(vet[i] == 999) {
				flag = true;
				break;
			}
		}
		return flag;
	}

	/*
	public static void main(String[] argv){
		MatriceAdiacenze mtr = new MatriceAdiacenze(6);

		//****** RETE 1
		mtr.addVertex(new Vertex(1,2,2));
		mtr.addVertex(new Vertex(1,6,4));
		mtr.addVertex(new Vertex(2,3,10));
		mtr.addVertex(new Vertex(2,5,8));
		mtr.addVertex(new Vertex(2,6,1));
		mtr.addVertex(new Vertex(3,5,2));
		mtr.addVertex(new Vertex(3,4,2));
		mtr.addVertex(new Vertex(4,5,6));
		mtr.addVertex(new Vertex(5,6,5));

		//***** RETE 2
		mtr.addVertex(new Vertex(1,2,1));
		mtr.addVertex(new Vertex(1,6,2));
		mtr.addVertex(new Vertex(1,5,5));
		mtr.addVertex(new Vertex(2,3,1));
		mtr.addVertex(new Vertex(2,6,2));
		mtr.addVertex(new Vertex(2,5,3));
		mtr.addVertex(new Vertex(3,5,1));
		mtr.addVertex(new Vertex(3,4,2));
		mtr.addVertex(new Vertex(4,5,5));
		mtr.addVertex(new Vertex(5,6,3));

		mtr.stmpTabAdiacenze();
		int[] prova = mtr.dijkstra(5);
		System.out.println(prova[0]+" "+prova[1]+" "+prova[2]+" "+prova[3]+" "+prova[4]+" "+prova[5]);
	}*/
}
