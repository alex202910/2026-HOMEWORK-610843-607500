package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version HW1
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa"};
	
	private IOConsole IO;
	private Partita partita;

	public DiaDia(IOConsole IO) {
        this.partita = new Partita();
        this.IO = IO;
    }

	public void gioca() {
		String istruzione;
		Scanner scannerDiLinee;

		IO.mostraMessaggio(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);
		do
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome() == null) {
			IO.mostraMessaggio("Nessun Comando inserito! Riprova");
			return false;
		}
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		}

		else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());

		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());

		else
			IO.mostraMessaggio("Comando sconosciuto");

		if (this.partita.vinta()) {
			IO.mostraMessaggio("Hai vinto!");
			return true;
		} 
		else if (this.partita.persa()) {
			IO.mostraMessaggio("Hai perso. CFU Insufficienti");
			return false;
		}
			
		
		else
			return false;
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for (int i = 0; i < elencoComandi.length; i++)
			IO.mostraMessaggio(elencoComandi[i] + " ");
		IO.mostraMessaggio("");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null)
			IO.mostraMessaggio("Dove vuoi andare ?");
		
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			IO.mostraMessaggio("-> Hai inserito una direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu - 1);
		}
		
		IO.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		IO.mostraMessaggio("CFU = " + partita.getGiocatore().getCfu());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		IO.mostraMessaggio("Grazie di aver giocato!"); // si desidera smettere
	}

	private void prendi(String nomeAttrezzo) {

		if (nomeAttrezzo != null) {

			if (this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo) != null) {

				Attrezzo AttrezzoDaEliminare = this.partita.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
				if (this.partita.getGiocatore().getBorsa().addAttrezzo(AttrezzoDaEliminare)) {
					this.partita.getStanzaCorrente().removeAttrezzo(AttrezzoDaEliminare);
					IO.mostraMessaggio("l'oggetto" + " " + nomeAttrezzo + " " + "è stato preso dalla stanza");
				} else
					IO.mostraMessaggio("la borsa è troppo piena, svuotala");
			} else
				IO.mostraMessaggio("l'oggetto" + " " + nomeAttrezzo + " " + "non è stato trovato nella stanza");
		} else
			IO.mostraMessaggio("il comando inesistente");

	}

	private void posa(String nomeAttrezzo) {

		if (nomeAttrezzo != null) {

			if (this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo) != null) {

				Attrezzo AttrezzoDaPosare = this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);

				if (this.partita.getStanzaCorrente().addAttrezzo(AttrezzoDaPosare)) {
					this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);
					IO.mostraMessaggio("l'oggetto" + " " + nomeAttrezzo + " " + "è stato posato");
				} else
					IO.mostraMessaggio("la stanza è troppo piena, prova altrove");
			} else
				IO.mostraMessaggio("l'oggetto" + " " + nomeAttrezzo + " " + "non è presente nella tua borsa");
		}

	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia(new IOConsole());
		gioco.gioca();
	}
}