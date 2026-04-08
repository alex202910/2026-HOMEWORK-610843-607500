package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GiocatoreTest {
	private Giocatore Kinn;
	
	@BeforeEach
	public void setUp() {
		this.Kinn = new Giocatore();
		
	}
	
	@Test
	void testVerificaCfuIniziali() {
		assertTrue(this.Kinn.getCfu() == 20);
	}
	
	@Test
	void testModificaCfu() {
		this.Kinn.setCfu(0);
		assertTrue(this.Kinn.getCfu() == 0);
	}
}
