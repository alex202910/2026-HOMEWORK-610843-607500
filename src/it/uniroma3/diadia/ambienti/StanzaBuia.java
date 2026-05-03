package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	private String nomeAttrezzoNecessario;
	
	public StanzaBuia(String nome, String nomeAttrezzo) {
		super(nome);
		this.nomeAttrezzoNecessario = nomeAttrezzo;
		
	}
	/*
	 * Verifico se nella stanza c'è l'oggetto per vedere altrimenti 
	 * ritorna la descrizione della stanza
	 */
	@Override
	public String getDescrizione() {
		if(!this.hasAttrezzo(nomeAttrezzoNecessario))
			return "Qui c'è un buio pesto";
	
	 return super.getDescrizione();
	}
	
}
