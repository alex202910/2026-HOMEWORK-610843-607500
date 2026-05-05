package it.uniroma3.diadia;

/*
 * Classe che simula i comandi di lettura e scrittura di Input e Output
 */
public class IOSimulatore implements IO {
	
	private String[] righeDaLeggere; // Array per comandi 
    private int indiceRigaCorrente;  // Indice per scorrere l'array

    public IOSimulatore(String[] righeDaLeggere) {
        this.righeDaLeggere = righeDaLeggere;
        this.indiceRigaCorrente = 0;
    }

    @Override
    public String leggiRiga() {
        String riga = this.righeDaLeggere[indiceRigaCorrente];
        this.indiceRigaCorrente++;
        return riga;
    }

    @Override
    public void mostraMessaggio(String messaggio) {
        System.out.println(messaggio);
    }
}
