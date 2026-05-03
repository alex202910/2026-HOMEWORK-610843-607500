package it.uniroma3.diadia.ambienti;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	@Test
	void testStanzaBuiaSenzaLanterna() {
		StanzaBuia stanza = new StanzaBuia("N1","lanterna");
		String messaggioAtteso = "Qui c'è un buio pesto";
		
		assertEquals(messaggioAtteso,stanza.getDescrizione());
	}
	
	@Test
	void testStanzaBuiaConLanterna() {
		StanzaBuia stanza = new StanzaBuia("N1","lanterna");
		Stanza stanzaLuce = new Stanza("N1");
		Attrezzo lanterna = new Attrezzo("lanterna",2);

		stanza.addAttrezzo(lanterna);
		stanzaLuce.addAttrezzo(lanterna);

		String messaggioAtteso = stanzaLuce.getDescrizione();
		
		assertEquals(messaggioAtteso,stanza.getDescrizione());
	}

}
