package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	
	private String nomeDirezioneBloccata;
	private String nomeAttrezzoPerAprire;
	
	public StanzaBloccata(String nome, String nomeDirezione, String nomeAttrezzo) {
		super(nome);
		this.nomeDirezioneBloccata = nomeDirezione;
		this.nomeAttrezzoPerAprire = nomeAttrezzo;
	}
	
	/*
	 * Se la direzione scelta è bloccata e l'attrezzo sbloccante NON è presente nella stanza
	 * resto fermo nella stanza in cui sono altrimenti vado nella stanza scelta
	 */
	@Override
	public Stanza getStanzaAdiacente(String dir) {
		if(this.nomeDirezioneBloccata.equals(dir)) {
			if(!this.hasAttrezzo(this.nomeAttrezzoPerAprire))
				return this; //Resto fermo nella stanza dove sono
		}
		
		return super.getStanzaAdiacente(dir);
	}
	
	@Override
	public String getDescrizione() {
		String descrizioneBase = super.getDescrizione();
		
		return "Attenzione! La direzione " + this.nomeDirezioneBloccata + " è bloccata. Serve l'attrezzo: " 
				+ this.nomeAttrezzoPerAprire + "\n" + descrizioneBase; 
	}
}
