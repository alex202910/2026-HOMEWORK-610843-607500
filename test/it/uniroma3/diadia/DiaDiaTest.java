package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DiaDiaTest {

	@Test
    public void testVittoria() {
        String[] comandi = {"vai nord", "fine"}; 
        IOSimulatore io = new IOSimulatore(comandi);
        DiaDia gioco = new DiaDia(io);

        gioco.gioca();

        assertTrue(gioco.getPartita().vinta());
        assertEquals("Biblioteca", gioco.getPartita().getStanzaCorrente().getNome());
    }

	@Test
	public void testSconfittaConCfuInsuffiscienti() {
		//Atrio -> sud N10 -> est N11 -> est Laboratorio -> Partita Persa
		String[] comandi = {"vai sud", "vai est", "vai est"};
		IOSimulatore io = new IOSimulatore(comandi);
		DiaDia gioco = new DiaDia(io);
		
		gioco.gioca();
		
		int zeroCFU = 0;
		assertEquals(zeroCFU, gioco.getPartita().getGiocatore().getCfu());
		//partita persa, giocatore non vivo
		assertFalse(gioco.getPartita().giocatoreIsVivo());
	}
	
    @Test
    public void testComandoInesistenteNonConsumaCfu() {
        String[] comandi = {"comandoSconosciuto", "fine"};
        IOSimulatore io = new IOSimulatore(comandi);
        DiaDia gioco = new DiaDia(io);
        
        int cfuIniziali = gioco.getPartita().getGiocatore().getCfu();
        gioco.gioca();
        
      //Verifico che dato un comando sconosciuto non toglie CFU al giocatore
        assertEquals(cfuIniziali, gioco.getPartita().getGiocatore().getCfu());
    }
    

}
