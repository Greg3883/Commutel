package main.commutel;

import java.util.concurrent.atomic.AtomicInteger;

public class posteAbonne {
	private static final AtomicInteger count = new AtomicInteger(0); 
	private int id;
	private Abonne abonne;

	
	public posteAbonne(Abonne abonne) {
		super();
		this.id = count.incrementAndGet(); 
		this.abonne = abonne;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Abonne getAbonne() {
		return abonne;
	}

	public void setAbonne(Abonne abonne) {
		this.abonne = abonne;
	}
	
	

}
