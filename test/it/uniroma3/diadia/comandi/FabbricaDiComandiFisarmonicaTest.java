package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FabbricaDiComandiFisarmonicaTest {

	@Test
	void testCostruisciComandoNonValido() {
	    FabbricaDiComandiFisarmonica fabbrica = new FabbricaDiComandiFisarmonica();
	    Comando comando = fabbrica.costruisciComando("via sole");
	    Comando comandoVuoto = fabbrica.costruisciComando(" ");
	    Comando comandoNull = fabbrica.costruisciComando(null);
	    
	    assertEquals("non valido", comando.getNome());
	    assertEquals("non valido", comandoVuoto.getNome());
	    assertEquals("non valido", comandoNull.getNome());
	}
	
	@Test
	void testCostruisciComandoVai() {
	    FabbricaDiComandiFisarmonica fabbrica = new FabbricaDiComandiFisarmonica();
	    Comando comando = fabbrica.costruisciComando("vai nord");
	    
	    assertEquals("vai", comando.getNome());
	    assertEquals("nord", comando.getParametro());
	}
	
	@Test
	void testCostruisciComandoPrendi() {
		FabbricaDiComandiFisarmonica fabbrica = new FabbricaDiComandiFisarmonica();
		Comando comando = fabbrica.costruisciComando("prendi osso");
		
		assertEquals("prendi", comando.getNome());
		assertEquals("osso", comando.getParametro());
	}
	
	@Test
	void testCostruisciComandoPosa() {
		FabbricaDiComandiFisarmonica fabbrica = new FabbricaDiComandiFisarmonica();
		Comando comando = fabbrica.costruisciComando("posa spada");
		
		assertEquals("posa", comando.getNome());
		assertEquals("spada", comando.getParametro());
	}
	
	@Test
	void testCostruisciComandoAiuto() {
	    FabbricaDiComandiFisarmonica fabbrica = new FabbricaDiComandiFisarmonica();
	    Comando comando = fabbrica.costruisciComando("aiuto");
	    
	    assertEquals("aiuto", comando.getNome());
	}
	
	@Test
	void testCostruisciComandoFine() {
	    FabbricaDiComandiFisarmonica fabbrica = new FabbricaDiComandiFisarmonica();
	    Comando comando = fabbrica.costruisciComando("fine");
	    
	    assertEquals("fine", comando.getNome());
	    assertNull(comando.getParametro());
	}
	
	@Test
	void testCostruisciComandoGuarda() {
	    FabbricaDiComandiFisarmonica fabbrica = new FabbricaDiComandiFisarmonica();
	    Comando comando = fabbrica.costruisciComando("guarda");
	    
	    assertEquals("guarda", comando.getNome());
	}
	
	
}
