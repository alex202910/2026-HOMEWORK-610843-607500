package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	
	private Borsa borsa;
	private Attrezzo penna;
	private Attrezzo martello;
	private Attrezzo piuma;
	private Attrezzo libro;
	private Attrezzo ps;
	private Attrezzo piombo;
	
	@BeforeEach
	public void setUp() {
		this.borsa = new Borsa();
		this.penna = new Attrezzo("Penna",2);
		this.martello = new Attrezzo("martello", 10);
		this.piuma = new Attrezzo("piuma", 1);
		this.libro = new Attrezzo("libro", 2);
		this.ps = new Attrezzo("ps", 2);
		this.piombo = new Attrezzo("piombo", 3);
	}
	
	@Test
	void testBorsaVuota() {
		assertTrue(this.borsa.isEmpty());
	}
	
	@Test
	void testBorsaNonVuota() {
		this.borsa.addAttrezzo(penna);
		assertFalse(this.borsa.isEmpty());
	}
	
	@Test
    void testBorsaPesoMassimo() {
        this.borsa.addAttrezzo(martello);
        assertTrue(this.borsa.getPesoMax() == this.borsa.getPeso());
    }
	
	@Test
    void testBorsaAggiungiAttrezzoPoiRimuoviAttrezzo() {
        this.borsa.addAttrezzo(martello);
        assertTrue(this.borsa.hasAttrezzo("martello"));
        this.borsa.removeAttrezzo("martello");
        assertFalse(this.borsa.hasAttrezzo("martello"));
    }
	
	// ==========================================
	// TEST ESERCIZIO 3: getContenutoOrdinatoPerPeso()
	// ==========================================

	@Test
	void testGetContenutoOrdinatoPerPeso() {
		// Inseriamo in ordine sparso
		this.borsa.addAttrezzo(piombo);
		this.borsa.addAttrezzo(piuma);
		this.borsa.addAttrezzo(ps);
		this.borsa.addAttrezzo(libro);

		List<Attrezzo> ordinati = this.borsa.getContenutoOrdinatoPerPeso();

		// L'ordine atteso per peso (e poi nome) è: 
		// piuma (1), libro (2), ps (2), piombo (3)
		assertEquals(4, ordinati.size());
		assertEquals(piuma, ordinati.get(0));
		assertEquals(libro, ordinati.get(1)); // "libro" viene prima di "ps" in ordine alfabetico
		assertEquals(ps, ordinati.get(2));
		assertEquals(piombo, ordinati.get(3));
	}

	// ==========================================
	// TEST ESERCIZIO 3: getContenutoOrdinatoPerNome()
	// ==========================================

	@Test
	void testGetContenutoOrdinatoPerNome() {
		this.borsa.addAttrezzo(piombo);
		this.borsa.addAttrezzo(piuma);
		this.borsa.addAttrezzo(ps);
		this.borsa.addAttrezzo(libro);

		SortedSet<Attrezzo> ordinati = this.borsa.getContenutoOrdinatoPerNome();

		// L'ordine alfabetico atteso è: libro, piombo, piuma, ps
		assertEquals(4, ordinati.size());

		// Verifichiamo l'ordine sequenziale convertendo in array temporaneo
		Attrezzo[] array = ordinati.toArray(new Attrezzo[0]);
		assertEquals(libro, array[0]);
		assertEquals(piombo, array[1]);
		assertEquals(piuma, array[2]);
		assertEquals(ps, array[3]);
	}

	// ==========================================
	// TEST ESERCIZIO 3: getContenutoRaggruppatoPerPeso()
	// ==========================================

	@Test
	void testGetContenutoRaggruppatoPerPeso() {
		this.borsa.addAttrezzo(piuma); // 1kg
		this.borsa.addAttrezzo(libro); // 2kg
		this.borsa.addAttrezzo(ps); // 2kg

		Map<Integer, Set<Attrezzo>> mappa = this.borsa.getContenutoRaggruppatoPerPeso();

		// Ci aspettiamo due gruppi: una chiave per 1kg e una per 5kg
		assertEquals(2, mappa.size());

		// Verifica gruppo da 1kg
		Set<Attrezzo> gruppo1 = mappa.get(1);
		assertEquals(1, gruppo1.size());
		assertTrue(gruppo1.contains(piuma));

		// Verifica gruppo da 2kg (deve contenere sia libro che ps)
		Set<Attrezzo> gruppo2 = mappa.get(2);
		assertEquals(2, gruppo2.size());
		assertTrue(gruppo2.contains(libro));
		assertTrue(gruppo2.contains(ps));
	}

	// ==========================================
	// TEST ESERCIZIO 4: getSortedSetOrdinatoPerPeso()
	// ==========================================

	@Test
	void testGetSortedSetOrdinatoPerPeso_ElementiDistintiConStessoPeso() {
		// Questo test verifica espressamente la richiesta dell'esercizio 4:
		// due attrezzi con lo stesso peso (libro e ps) non devono essere considerati
		// duplicati dal TreeSet
		this.borsa.addAttrezzo(libro);
		this.borsa.addAttrezzo(ps);

		SortedSet<Attrezzo> setOrdinato = this.borsa.getSortedSetOrdinatoPerPeso();

		// Se il comparatore riflette l'uguaglianza solo sul peso, la dimensione sarebbe
		// 1 (errore)
		// Se il comparatore è corretto, la dimensione deve essere 2.
		assertEquals(2, setOrdinato.size());

		Attrezzo[] array = setOrdinato.toArray(new Attrezzo[0]);
		assertEquals(libro, array[0]); // Peso 5, nome "libro" viene prima
		assertEquals(ps, array[1]); // Peso 5, nome "ps" viene dopo
	}
	
	@Test
	void testVerificaQuantiElementiHaLaMappaDellaBorsa() {
	    this.borsa.addAttrezzo(piuma);
	    this.borsa.addAttrezzo(libro);
	    this.borsa.addAttrezzo(ps);
	    this.borsa.addAttrezzo(piombo);
	    
	    assertEquals(4, this.borsa.getContenutoOrdinatoPerPeso().size()); 
	}
	
	

}
