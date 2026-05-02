package it.uniroma3.diadia.comandi;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;

class ComandoVaiTest {
	
		private Partita partita;
		private ComandoVai comando;
	
	@BeforeEach
	public void setUp(){
		comando = new ComandoVai();
		partita = new Partita();
	}
	
	
	@Test
    public void testEseguiDirezioneEsistente() {
        // Supponendo che nella stanza iniziale ci sia un'uscita a "nord"
        comando.setParametro("nord");
        int cfuIniziali = partita.getGiocatore().getCfu();
        comando.esegui(partita);
        
        assertNotEquals("Dovrei essermi spostato", "Atrio", partita.getStanzaCorrente().getNome());
        assertEquals("I CFU devono essere diminuiti", cfuIniziali - 1, partita.getGiocatore().getCfu());
    }

    @Test
    public void testEseguiDirezioneInesistente() {
        comando.setParametro("direzione_fantasma");
        Stanza stanzaIniziale = partita.getStanzaCorrente();
        comando.esegui(partita);
        
        assertEquals("La stanza non deve cambiare", stanzaIniziale, partita.getStanzaCorrente());
    }

}
