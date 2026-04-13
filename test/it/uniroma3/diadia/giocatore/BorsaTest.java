package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	
	private Borsa borsa;
	private Attrezzo penna;
	private Attrezzo martello;
	
	@BeforeEach
	public void setUp() {
		this.borsa = new Borsa();
	}
	
	@Test
	void testBorsaVuota() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	void testBorsaNonVuota() {
		this.penna = new Attrezzo("Penna",2);
		this.borsa.addAttrezzo(penna);
		assertFalse(this.borsa.isEmpty());
	}
	
	@Test
    void testBorsaPesoMassimo() {
        this.martello = new Attrezzo("martello", 10);
        this.borsa.addAttrezzo(martello);
        assertTrue(this.borsa.getPesoMax() == this.borsa.getPeso());
    }
	
}
