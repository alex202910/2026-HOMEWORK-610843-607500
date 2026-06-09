package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.Map;
import java.util.HashMap;

/**
 * Classe LabirintoBuilder
 * Implementa il pattern Builder tramite un'interfaccia fluida (method chaining).
 */
public class LabirintoBuilder {

	private Labirinto labirinto;
	private Map<String, Stanza> nome2stanza;
	private Stanza ultimaStanzaAggiunta;

	public LabirintoBuilder() {
		this.labirinto = new Labirinto();
		this.nome2stanza = new HashMap<>();
	}

	/**
	 * Restituisce l'oggetto Labirinto assemblato.
	 */
	public Labirinto getLabirinto() {
		return this.labirinto;
	}

	/**
	 * Restituisce la mappa interna delle stanze create dal Builder.
	 */
	public Map<String, Stanza> getListaStanze() {
		return this.nome2stanza;
	}

	/**
	 * Definisce o aggiorna la stanza iniziale del labirinto.
	 */
	public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
		Stanza iniziale = this.nome2stanza.get(nomeStanza);
		if (iniziale == null) {
			iniziale = new Stanza(nomeStanza);
			this.registraStanza(iniziale);
		}
		this.labirinto.setStanzaIniziale(iniziale);
		this.ultimaStanzaAggiunta = iniziale;
		return this;
	}

	/**
	 * Definisce la stanza vincente del labirinto.
	 */
	public LabirintoBuilder addStanzaVincente(String nomeStanza) {
		Stanza vincente = this.nome2stanza.get(nomeStanza);
		if (vincente == null) {
			vincente = new Stanza(nomeStanza);
			this.registraStanza(vincente);
		}
		this.labirinto.setStanzaVincente(vincente);
		this.ultimaStanzaAggiunta = vincente;
		return this;
	}

	/**
	 * Aggiunge una stanza normale se non è già presente.
	 */
	public LabirintoBuilder addStanza(String nomeStanza) {
		if (!this.nome2stanza.containsKey(nomeStanza)) {
			Stanza stanza = new Stanza(nomeStanza);
			this.registraStanza(stanza);
		} else {
			this.ultimaStanzaAggiunta = this.nome2stanza.get(nomeStanza);
		}
		return this;
	}

	/**
	 * Aggiunge una StanzaMagica al labirinto.
	 */
	public LabirintoBuilder addStanzaMagica(String nomeStanza, int sogliaMagica) {
		if (!this.nome2stanza.containsKey(nomeStanza)) {
			StanzaMagica stanzaMagica = new StanzaMagica(nomeStanza, sogliaMagica);
			this.registraStanza(stanzaMagica);
		} else {
			this.ultimaStanzaAggiunta = this.nome2stanza.get(nomeStanza);
		}
		return this;
	}

	/**
	 * Aggiunge una StanzaBuia al labirinto.
	 */
	public LabirintoBuilder addStanzaBuia(String nomeStanza, String attrezzoSbloccante) {
		if (!this.nome2stanza.containsKey(nomeStanza)) {
			StanzaBuia stanzaBuia = new StanzaBuia(nomeStanza, attrezzoSbloccante);
			this.registraStanza(stanzaBuia);
		} else {
			this.ultimaStanzaAggiunta = this.nome2stanza.get(nomeStanza);
		}
		return this;
	}

	/**
	 * Aggiunge una StanzaBloccata al labirinto.
	 */
	public LabirintoBuilder addStanzaBloccata(String nomeStanza, String direzioneBloccata, String attrezzoSbloccante) {
		if (!this.nome2stanza.containsKey(nomeStanza)) {
			StanzaBloccata stanzaBloccata = new StanzaBloccata(nomeStanza, direzioneBloccata, attrezzoSbloccante);
			this.registraStanza(stanzaBloccata);
		} else {
			this.ultimaStanzaAggiunta = this.nome2stanza.get(nomeStanza);
		}
		return this;
	}

	/**
	 * Inserisce un attrezzo nell'ultima stanza modificata/aggiunta.
	 */
	public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
		if (this.ultimaStanzaAggiunta != null) {
			Attrezzo attrezzo = new Attrezzo(nomeAttrezzo, peso);
			this.ultimaStanzaAggiunta.addAttrezzo(attrezzo);
		}
		return this;
	}

	/**
	 * Stabilisce un'uscita monodirezionale da una stanza a un'altra.
	 */
	public LabirintoBuilder addAdiacenza(String stanzaNome, String stanzaAdiacenteNome, String direzione) {
		Stanza stanza = this.nome2stanza.get(stanzaNome);
		Stanza adiacente = this.nome2stanza.get(stanzaAdiacenteNome);
		
		if (stanza != null && adiacente != null) {
			stanza.impostaStanzaAdiacente(direzione, adiacente);
		}
		return this;
	}

	/**
	 * Metodo di supporto privato per indicizzare la stanza creata.
	 */
	private void registraStanza(Stanza stanza) {
		this.nome2stanza.put(stanza.getNome(), stanza);
		this.ultimaStanzaAggiunta = stanza;
	}
}