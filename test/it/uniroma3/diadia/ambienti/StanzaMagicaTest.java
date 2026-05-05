package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	
	private Stanza atrio;
	private Attrezzo chiave;
	
	private Attrezzo finto1;
	private Attrezzo finto2;
	private Attrezzo finto3;
	
	
	@BeforeEach
	public void setUp() {
		atrio = new StanzaMagica("atrio"); //soglia = 3 (DEFULT)
		chiave = new Attrezzo("chiave",2);
		
		finto1 = new Attrezzo("finto1",1);
		finto2 = new Attrezzo("finto2",1);
		finto3 = new Attrezzo("finto3",1);
	}
	
	@Test
	void testAddAttrezzoConSogliaNONSuperata() {
		atrio.addAttrezzo(chiave);
		
		//Non avendo superato la soglia, il nome e il peso dell'attrezzo rimangono invariabili
		assertEquals("chiave", atrio.getAttrezzo("chiave").getNome());
		assertEquals(2, atrio.getAttrezzo("chiave").getPeso());
	}
	
	@Test
	void testAddAttrezzoConSogliaLimite() {
		//Aggiunge attrezzi finti per raggiungere VICINO la soglia della stanza Magica che è 3
		atrio.addAttrezzo(finto1);
		atrio.addAttrezzo(finto2);
		
		//Aggiungendo l'attrezzo chiave, arrivo al limite della soglia (3) ma l'oggetto rimane ancora normale
		atrio.addAttrezzo(chiave);
		
		//il nome e il peso dell'attrezzo NON cambiano
		assertTrue(atrio.hasAttrezzo("chiave"));
		assertEquals(2, atrio.getAttrezzo("chiave").getPeso());
		
		//l'attrezzo chiave con nome inverito NON dovrebbe esistere
		assertNull(atrio.getAttrezzo("evaihc"));
	}
	
	@Test
	void testAddAttrezzoConSogliaSuperata() {
		//Aggiunge attrezzi finti per raggiungere la soglia della stanza Magica che è 3
		atrio.addAttrezzo(finto1);
		atrio.addAttrezzo(finto2);
		atrio.addAttrezzo(finto3);
		
		//chiave diventa un oggetto magico
		atrio.addAttrezzo(chiave);
		
		//Avendo superato la soglia, 
		//l'attrezzo di nome "chiave" non esiste più 
		assertNull(atrio.getAttrezzo("chiave"));
		
		//pertanto esiste quello col nome invertito
		assertNotNull(atrio.getAttrezzo("evaihc"));

		//il nome e il peso dell'attrezzo si inverte e si raddoppia rispettivamente
		assertTrue(atrio.hasAttrezzo("evaihc"));
		assertEquals(4, atrio.getAttrezzo("evaihc").getPeso());
	}

}
