package it.polito.tdp.bar;



public class Event implements Comparable <Event>{
	
	private int time; //(minuti)istante temporale in cui si verificherà l’evento creato. 
	// L’intervallo tra due eventi dovrà essere compreso tra 1 e 10 minuti
	private Gruppo gruppo;
	
	
		private EventType tipo;
			public Event(int time, Gruppo gruppo, EventType tipo) {
				this.time = time;
				this.gruppo = gruppo;
				this.tipo = tipo;
			}
			public int getTime() {
				return time;
			}
			
			public EventType getTipo() {
				return tipo;
			}
			
			
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((gruppo == null) ? 0 : gruppo.hashCode());
				result = prime * result + time;
				result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
				return result;
			}
			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Event other = (Event) obj;
				if (gruppo == null) {
					if (other.gruppo != null)
						return false;
				} else if (!gruppo.equals(other.gruppo))
					return false;
				if (time != other.time)
					return false;
				if (tipo != other.tipo)
					return false;
				return true;
			}
			@Override
			public int compareTo(Event other) {
				return this.time - other.time; //???
			}
			@Override
			public String toString() {
				return "Event [time=" + time + ", gruppo=" + gruppo + ", tipo=" + tipo + "]";
			}
			public Gruppo getGruppo() {
				return gruppo;
			}
			
			
			
			
	}

