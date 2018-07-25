package it.polito.tdp.bar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;



public class Simulatore {
	
	// Coda degli eventi
	private PriorityQueue<Event> queue = new PriorityQueue<>();
	
	/* Parametri di simulazione (sono le uniche cose che possiamo modificare dall'esterno)
	 impostati all'inizio, costanti durante la simulazione*/
	private int DURATA_MIN;
	private int DURATA_MAX;
	private Map <Integer,Tavolo> mappaTavoli;
	
	
	// Modello del mondo // Stato del sistema 
	private int tavoli_disponibili;
	
	
	// Valori da calcolare // Output
	private int numero_totale_clienti;
	private int numero_clienti_soddisfatti; 
	private int numero_clienti_insoddisfatti;
	
	public void init() {
		// inizializza la coda degli eventi
		queue.clear();
		int tempo = 0;
		this.DURATA_MIN = 60;
		this.DURATA_MAX = 120;
		
		// inizializzo i tavoli
		this.mappaTavoli = new HashMap<>();
		this.creaTavoli();
		
		
		for(int i=0; i<2000; i++) {
			int time = (int) (1 + 10 * Math.random()); 
			int num_persone = (int)(1 + 10 * Math.random()); 
			int durata = (int) (this.DURATA_MAX + this.DURATA_MIN * Math.random()); 
			float tolleranza = (float) Math.random();
			// float tolleranza = (float) 0.1;
			tempo += time;
			Gruppo g = new Gruppo(tempo, num_persone, durata, tolleranza);
			Event e = new Event(tempo, g, EventType.ARRIVO_GRUPPO_CLIENTI);
			queue.add(e);
		}
		
		// inizializzo le variabili di simulazione
		this.numero_totale_clienti = 0;
		this.numero_clienti_soddisfatti = 0;
		this.numero_clienti_insoddisfatti = 0;
	}
	
	
	public void run() {
		Event e;
		while((e = queue.poll()) != null) {
			this.processEvent(e);
			System.out.println(e);
		}
	}


	private void processEvent(Event e) {
		switch (e.getTipo()){
		case ARRIVO_GRUPPO_CLIENTI:
			
			this.numero_totale_clienti += e.getGruppo().getNum_persone();
			Tavolo tavolo = this.isDisponibile(e.getGruppo().getNum_persone());
			
			if(tavolo != null) { // tavolo disponibile
				tavolo.setOccupato(true);
				this.numero_clienti_soddisfatti += e.getGruppo().getNum_persone();
				e.getGruppo().setTavolo(tavolo);
				int time = e.getTime() + e.getGruppo().getDurata();
				queue.add(new Event(time ,e.getGruppo(), EventType.USCITA_GRUPPO_CLIENTI));
				
			} else { // tavolo NON disponibile
				float probabilita = (float) Math.random();
				
				if(e.getGruppo().getTolleranza() >= probabilita) { // restano al bancone
					this.numero_clienti_soddisfatti+=e.getGruppo().getNum_persone();
					int time = e.getTime() + e.getGruppo().getDurata();
					queue.add(new Event(time ,e.getGruppo(), EventType.USCITA_GRUPPO_CLIENTI));
				}
				else {
					this.numero_clienti_insoddisfatti+=e.getGruppo().getNum_persone();
				//	queue.add(new Event(e.getTime(), e.getGruppo(), EventType.USCITA_GRUPPO_CLIENTI));
				}
			}
			break;
			
		case USCITA_GRUPPO_CLIENTI:
			Tavolo t = e.getGruppo().getTavolo();
			if(t	 != null)
				t.setOccupato(false);
			
			break;
		}
	}

	
	// metodo che verifica se il tavolo è disponibile
	private Tavolo isDisponibile(int num_persone) {
		
		List<Tavolo> tavoli = new ArrayList<>();
		for(Tavolo t: this.mappaTavoli.values()) {
			tavoli.add(t);
		}
		Collections.sort(tavoli);
		
		for(Tavolo t: this.mappaTavoli.values()) {
			if(t.getCapienza() >= num_persone && t.isOccupato() == false && num_persone >= t.getCapienza()*0.5) {
				return t;
			}
		}
		return null;
	}


	// funzione per i tavoli
	public void creaTavoli(){
		
		for(int i=0; i<14; i++) {
			Tavolo t = new Tavolo();
			t.setId(i);
			t.setOccupato(false);
			
			if(i<2) 
				t.setCapienza(10);
			else if(i>1 && i<6) 
				t.setCapienza(8);
			else if(i>5 && i<10) 
				t.setCapienza(6);
			else 
				t.setCapienza(4);
			
			mappaTavoli.put(t.getId(), t);
		}
	}


	public Map<Integer, Tavolo> getMappaTavoli() {
		return mappaTavoli;
	}


	public int getNumero_totale_clienti() {
		return numero_totale_clienti;
	}


	public int getNumero_clienti_soddisfatti() {
		return numero_clienti_soddisfatti;
	}


	public int getNumero_clienti_insoddisfatti() {
		return numero_clienti_insoddisfatti;
	}
	
	
	
}
