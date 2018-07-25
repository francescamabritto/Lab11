package it.polito.tdp.bar;

public class Gruppo {
	
	
	private int arrivo;
	private int num_persone;//indica il numero di persone facenti parte del gruppo che vogliono sedersi al tavolo.
	//Valore casuale compreso tra 1 e 10;
	private int durata; // tempo in minuti indicante la permanenza dei clienti al tavolo del bar (tra 60 e 120 minuti);
	private float tolleranza; 
		/*: indica la tolleranza di ogni gruppo di clienti
		 a restare al bar accomodandosi al bancone, nel caso in cui il tavolo 
		 richiesto non sia disponibile. Valore float tra 0.0 
		 (se trovano il posto al tavolo restano al bar, altrimenti vanno via immediatamente insoddisfatti)
		  e 0.9 (90% di probabilità di accomodarsi al bancone del bar anche senza potersi sedere al tavolo, restando comunque soddisfatti).
		*/
	private Tavolo tavolo;
	
	public Gruppo() {	
	}
	
	public Gruppo(int arrivo, int num_persone, int durata, float tolleranza) {
		super();
		this.arrivo = arrivo;
		this.num_persone = num_persone;
		this.durata = durata;
		this.tolleranza = tolleranza;
	}
	
	public int getArrivo() {
		return arrivo;
	}

	public void setArrivo(int arrivo) {
		this.arrivo = arrivo;
	}

	public int getNum_persone() {
		return num_persone;
	}
	public void setNum_persone(int num_persone) {
		this.num_persone = num_persone;
	}
	public int getDurata() {
		return durata;
	}
	public void setDurata(int durata) {
		this.durata = durata;
	}
	public float getTolleranza() {
		return tolleranza;
	}
	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}
	
	@Override
	public String toString() {
		return arrivo + ", " + num_persone + ", " + durata + ", " + tolleranza;
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}
	
	
	
}
