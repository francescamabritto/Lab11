package it.polito.tdp.bar;

import java.util.Map;

public class TestSimulatore {

	public static void main(String[] args) {
		
		Simulatore s = new Simulatore();
		
		
		s.init();

//		for(Tavolo t: s.getMappaTavoli().values())
//			System.out.println(t.toString());

		s.run();
		
		System.out.format("\nclienti tot = %d\n soddisfatti = %d\n insoddisfatti = %d", 
				s.getNumero_totale_clienti(), s.getNumero_clienti_soddisfatti(), s.getNumero_clienti_insoddisfatti());
		
	}
}
