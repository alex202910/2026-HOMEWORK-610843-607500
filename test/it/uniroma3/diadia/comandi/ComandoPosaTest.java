package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;

class ComandoPosaTest {
	
	private Partita partita;
	private ComandoVai comando;

	@BeforeEach
	public void setUp() {
		comando = new ComandoVai();
		partita = new Partita();
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
