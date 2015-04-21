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

	private int[] calcSinkTreeOne(int nodoR){
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

		int j=0;
		while(j<tabAdiacenze.length){
			for(int i=0;i<tabAdiacenze.length;i++){
				if(i!=nodoRadice && i!=nextNodo){
					if(tabAdiacenze[nextNodo][i].getPeso() != Integer.MAX_VALUE)
						sinkTree[j][i] = new CoppiaValori(tabAdiacenze[nextNodo][i].getPeso()+distanzaPeso,j+1);
					else
						sinkTree[j][i] = new CoppiaValori(tabAdiacenze[nextNodo][i].getPeso(),j+1);
				}
			}
			pesoAttuale = Integer.MAX_VALUE;
			for(int z=0;z<tabAdiacenze.length;z++){
				if(sinkTree[j][z].getDistanza() < pesoAttuale && sinkTree[j][z].getDistanza() > 0){
					nextNodo = z;
					pesoAttuale = sinkTree[j][z].getDistanza();
				}

			}
			distanzaPeso = pesoAttuale;
			j +=1;
		}

		int[] valoriMinimi = new int[6];
		int c=0;
		for(int g=0; g<numNodi; g++){
			int valoreMinimo = Integer.MAX_VALUE;
			if(g!=nodoRadice){
				for(int i=0; i<numNodi; i++){
					if(sinkTree[i][g].getDistanza() < valoreMinimo && sinkTree[i][g].getDistanza() != 0){
						valoreMinimo = sinkTree[i][g].getDistanza();
					}
				}
				valoriMinimi[c] = valoreMinimo;
				c +=1;
			}
			else{
				valoriMinimi[c] = 0;
				c +=1;
			}
		}

		return valoriMinimi;
	}

	private int[] calcSinkTreeTwo(int nodoR){
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

		int j=0;
		while(j<tabAdiacenze.length){
			for(int i=0;i<tabAdiacenze.length;i++){
				if(i!=nodoRadice && i!=nextNodo){
					if(tabAdiacenze[nextNodo][i].getPeso() != Integer.MAX_VALUE)
						sinkTree[j][i] = new CoppiaValori(tabAdiacenze[nextNodo][i].getPeso()+distanzaPeso,j+1);
					else
						sinkTree[j][i] = new CoppiaValori(tabAdiacenze[nextNodo][i].getPeso(),j+1);
				}
			}
			pesoAttuale = Integer.MAX_VALUE;
			for(int z=0;z<tabAdiacenze.length;z++){
				if(sinkTree[j][z].getDistanza() <= pesoAttuale && sinkTree[j][z].getDistanza() > 0){
					nextNodo = z;
					pesoAttuale = sinkTree[j][z].getDistanza();
				}

			}
			distanzaPeso = pesoAttuale;
			j +=1;
		}

		/**
		 * utilizzato per il debug
		 */
		//stmpSinkTree(sinkTree, nodoRadice);

		int[] valoriMinimi = new int[6];
		int c=0;
		for(int g=0; g<numNodi; g++){
			int valoreMinimo = Integer.MAX_VALUE;
			if(g!=nodoRadice){
				for(int i=0; i<numNodi; i++){
					if(sinkTree[i][g].getDistanza() < valoreMinimo && sinkTree[i][g].getDistanza() != 0){
						valoreMinimo = sinkTree[i][g].getDistanza();
					}
				}
				valoriMinimi[c] = valoreMinimo;
				c +=1;
			}
			else{
				valoriMinimi[c] = 0;
				c +=1;
			}
		}

		return valoriMinimi;
	}

	public int[] dijkstra(int nodoR){
		int[] sinkTree1 = calcSinkTreeOne(nodoR);
		int[] sinkTree2 = calcSinkTreeTwo(nodoR);
		int counter1=0;
		int counter2=0;

		for(int i=0;i<numNodi;i++){
			if(sinkTree1[i] < sinkTree2[i])
				counter1++;
			else if(sinkTree1[i] > sinkTree2[i])
				counter2++;
			else {
				counter1++;
				counter2++;
			}
		}
		if(counter1 >= counter2)
			return sinkTree1;
		else
			return sinkTree2;
	}

	private void stmpSinkTree(CoppiaValori[][] sinkTree,int nodoRadice){
		for(int g=0; g<numNodi; g++){
			for(int i=0; i<numNodi; i++){
				if(i != nodoRadice){
					if(sinkTree[g][i].getDistanza() == Integer.MAX_VALUE)
						System.out.print("\u221E  ");
					else
						System.out.print(sinkTree[g][i].getDistanza()+"  ");
				}
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	

	/*
		int[] prova = ma.dijkstra(6);
		System.out.println(prova[0]+" "+prova[1]+" "+prova[2]+" "+prova[3]+" "+prova[4]+" "+prova[5]);
	*/
}
