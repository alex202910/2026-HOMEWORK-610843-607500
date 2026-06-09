package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class DiaDiaTest {

	@Test
	public void testVittoria() {
		// Costruiamo il labirinto specifico richiesto dal test usando il Builder
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanzaVincente("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", "nord")
				.getLabirinto();
		
		List<String> comandi = Arrays.asList("vai nord", "fine"); 
		IOSimulatore io = new IOSimulatore(comandi);
		
		// Iniezione speculare alla slide del professore: (labirinto, io)
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.gioca();

		assertTrue(gioco.getPartita().vinta());
		assertEquals("Biblioteca", gioco.getPartita().getStanzaCorrente().getNome());
	}

	@Test
	public void testSconfittaConCfuInsufficienti() {
		// Labirinto ad hoc per ricreare il percorso: Atrio -> sud N10 -> est N11 -> est Laboratorio
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.addStanza("Aula N10")
				.addStanza("Aula N11")
				.addStanza("Laboratorio")
				.addAdiacenza("Atrio", "Aula N10", "sud")
				.addAdiacenza("Aula N10", "Aula N11", "est")
				.addAdiacenza("Aula N11", "Laboratorio", "est")
				.getLabirinto();
				
		List<String> comandi = Arrays.asList("vai sud", "vai est", "vai est");
		IOSimulatore io = new IOSimulatore(comandi);
		
		// Abbassiamo i CFU a inizio partita per mandarlo subito a zero nel percorso breve,
		// oppure lasciamo che consumi muovendosi a vuoto.
		DiaDia gioco = new DiaDia(labirinto, io);
		gioco.getPartita().getGiocatore().setCfu(3); // Impostiamo 3 CFU così con 3 passi va a 0!
		
		gioco.gioca();
		
		assertEquals(0, gioco.getPartita().getGiocatore().getCfu());
		assertFalse(gioco.getPartita().giocatoreIsVivo());
	}
	
	@Test
	public void testComandoInesistenteNonConsumaCfu() {
		Labirinto labirinto = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
				
		List<String> comandi = Arrays.asList("comandoSconosciuto", "fine");
		IOSimulatore io = new IOSimulatore(comandi);
		DiaDia gioco = new DiaDia(labirinto, io);
		
		int cfuIniziali = gioco.getPartita().getGiocatore().getCfu();
		gioco.gioca();
        
		// Verifico che dato un comando sconosciuto non toglie CFU al giocatore
		assertEquals(cfuIniziali, gioco.getPartita().getGiocatore().getCfu());
	}
}