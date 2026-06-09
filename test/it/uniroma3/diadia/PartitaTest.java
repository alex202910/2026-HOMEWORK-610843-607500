package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

class PartitaTest {
	
	private Partita partita;
	private Giocatore P1;
	
	@BeforeEach
	public void setUp(){
		this.partita = new Partita();
		this.P1 = this.partita.getGiocatore();
	}
	
	@Test
	void testNuovaPartitaNonVinta() {
		assertFalse(this.partita.vinta());
	}
	
	@Test
	void testVerificaPresenzaCFU() {
		assertFalse(this.partita.isFinita());
	}
	
	@Test
	void testNuovaPartitaPoiFinita() {
		assertFalse(this.partita.isFinita());
		this.partita.setFinita();
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	void testPartitaFinitaConZeroCFU() {
		this.P1.setCfu(0);
		assertTrue(this.partita.isFinita());
	}
	
	@Test
	void testPartitaFinitaConCfuNegativo() {
		this.P1.setCfu(-1);
		assertTrue(this.partita.isFinita());
	}
}
