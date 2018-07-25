package it.polito.tdp.bar;

public class Tavolo implements Comparable<Tavolo>{
	
	private int capienza;
	private int id;
	private boolean occupato;
	
	public Tavolo() {
	}
	public Tavolo(int capienza, int id, boolean occupato) {
		super();
		this.capienza = capienza;
		this.id = id;
		this.occupato = occupato;
	}

	public int getCapienza() {
		return capienza;
	}

	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isOccupato() {
		return occupato;
	}

	public void setOccupato(boolean occupato) {
		this.occupato = occupato;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Tavolo other = (Tavolo) obj;
		if (id != other.id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return capienza + ", " + id + ", " + occupato;
	}
	
	@Override
	public int compareTo(Tavolo other) {
		return (this.getCapienza() - other.getCapienza());
	}
	
	
	
}
