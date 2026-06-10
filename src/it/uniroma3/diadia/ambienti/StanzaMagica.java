package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagica extends Stanza {
	
	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;

	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}

	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati = 0;
		this.sogliaMagica = soglia;
	}

	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		
		//condizione quando il numero di attrezzi posati supera la soglia
		if (this.contatoreAttrezziPosati>this.sogliaMagica)
			attrezzo = this.modificaAttrezzo(attrezzo);
		
		return super.addAttrezzo(attrezzo);   
		//super significa che voglio usare il metodo di addAttrezzo ma della super classe (Stanza)
		//L'errore è usare this.addAttrezzo(attrezzo) ma andrebbe a chiamare il metodo di StanzaMagica
		//e quindi di se stesso all'infinito pure: infatti non va bene usare this. bensì super

	}

	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2 = attrezzo.getPeso() * 2;
		nomeInvertito = new StringBuilder(attrezzo.getNome());
		nomeInvertito = nomeInvertito.reverse();
		attrezzo = new Attrezzo(nomeInvertito.toString(),
				pesoX2);
		return attrezzo;
	}
	
	public boolean isMagica() {
	    return true; 
	}
}

