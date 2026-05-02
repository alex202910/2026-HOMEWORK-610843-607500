package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

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

	private IO IO;
	private Partita partita;

	public DiaDia(IO IO) {
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
		Comando comandoDaEseguire;
		FabbricaDiComandiFisarmonica factory = new FabbricaDiComandiFisarmonica();
		
		comandoDaEseguire = factory.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
			return true;
		}
		
		if (!this.partita.giocatoreIsVivo()) {
			System.out.println("Hai esaurito i CFU...");
			return true;
		}

		return this.partita.isFinita();
	}

	// implementazioni dei comandi dell'utente:


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
		IO io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}