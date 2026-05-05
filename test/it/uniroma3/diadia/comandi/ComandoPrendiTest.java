package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class ComandoPrendiTest {

	private Partita partita;
	private Attrezzo axe;
	private Stanza atrio;
	private IOConsole io;

	@BeforeEach
	public void setUp() {
		partita = new Partita();
		axe = new Attrezzo("axe",5);
		atrio = partita.getStanzaCorrente();
		io = new IOConsole();
	}

	
	@Test
	void testPrendiOggettoInStanza() {
		
		atrio.addAttrezzo(axe);
		
		ComandoPrendi comando = new ComandoPrendi();
		comando.setParametro("axe");
		comando.esegui(partita, io);
		
		//Axe presente nell'atrio, ma col comando viene preso 
		//e non si trova più nella stanza atrio
		assertFalse(atrio.hasAttrezzo("axe"));
		
		//Axe presente nella borsa del giocatore
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("axe"));
	}
	
	@Test
	void testPrendiOggettoNonEsistenteInStanza() {
		
		ComandoPrendi comando = new ComandoPrendi();
		comando.setParametro("axe");
		comando.esegui(partita, io);
		
		//Axe NON trovato nell'atrio
		assertFalse(atrio.hasAttrezzo("axe"));
		
		//Axe NON presente nella borsa del giocatore
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("axe"));
	}
	
	@Test
	void testPrendiOggettoPesanteInStanza() {
		
		Attrezzo tavolo = new Attrezzo("tavolo",15);
		atrio.addAttrezzo(tavolo);
		
		ComandoPrendi comando = new ComandoPrendi();
		comando.setParametro("tavolo");
		comando.esegui(partita, io);
		
		//Tavolo rimane presente nell'atrio
		assertTrue(atrio.hasAttrezzo("tavolo"));
		
		//Poichè la borsa ha la capacità massima di 10kg e il tavolo pesa 15kg,
		//quest'ultimo NON può essere messo nella borsa perchè e troppo pensante.
		assertFalse(partita.getGiocatore().getBorsa().addAttrezzo(tavolo));
	}
}
