package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;

	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}

	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>(); // Uso di HashMap anziché array fisso
	}
	
	/**
	 * Aggiunge un attrezzo alla borsa fino a una capienza massima fissa
	 * @param Attrezzo attrezzo
	 * @return true se è stato aggiunto l'attrezzo, false altrimenti
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		//Rimosso il vincolo del numero massimo di 10 attrezzi
		this.attrezzi.put(attrezzo.getNome(), attrezzo);
		return true;
	}

	public int getPesoMax() {
		return pesoMax;
	}

	/**
	 * Prende un attrezzo per nome
	 * @param String nomeAttrezzo
	 * @return attrezzo se trova l'attrezzo, null altrimenti
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo == null)
				return null;
		//Ricerca immediata senza cicli lineari
		return this.attrezzi.get(nomeAttrezzo);
	}
	
	/**
	 * @return peso totale degli attrezzi nella borsa
	 */
	public int getPeso() {
		int peso = 0;
		//for-each sulla mappa di attrezzi
		for (Attrezzo a : this.attrezzi.values())
			peso += a.getPeso();
		return peso;
	}

	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	/**
	 * Controlla l'esistenza di un attrezzo nella Borsa
	 * @param String nomeAttrezzo
	 * @return true se esiste l'attrezzo, false atrimenti
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}

	/**
	 * Rimuove l'attrezzo dalla Borsa
	 * @param String nomeAttrezzo
	 * @return true se l'attrezzo è stato rimosso, null altrimenti
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo == null)
			return null;
		//Rimozione immediata senza cicli lineari
		return this.attrezzi.remove(nomeAttrezzo);
	}
	/**
	 * Restituisci la lista degli attrerzzi nella borsa ordinati per peso e quindi,
	 * a parità di peso, per nome
	 * @return lista degli attrazzi nella borsa ordinati per peso
	 */
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> elencoOrdinato = new ArrayList<>(this.attrezzi.values());
		ComparatorePerPesoENome comp = new ComparatorePerPesoENome();
		Collections.sort(elencoOrdinato, comp);
		return elencoOrdinato;
	}
	
	/**
	 * restituisce l'insieme degli attrezzi nella borsa ordinati per nome
	 * @return insieme degli attrezzi nella borsa ordinati per nome
	 */
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		//TreeSet chiama automaticamente il compareTo() di Attrezzo per ordinare per nome
		return new TreeSet<>(this.attrezzi.values());
	}
	
	/**
	 * restituisce una mappa che associa un intero (rappresentante un peso) con l’insieme (comunque non vuoto) 
	 * degli attrezzi di tale peso: tutti gli attrezzi dell'insieme che figura come 
	 * valore hanno lo stesso peso pari all'intero che figura come chiave
	 * @return mappa degli attrezzi raggruppato per stesso peso
	 */
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer, Set<Attrezzo>> mappaPeso2Attrezzi = new HashMap<>();
		
		for (Attrezzo a : this.attrezzi.values()) {
			int peso = a.getPeso();
			Set<Attrezzo> insiemeAttrezziCorrenti = mappaPeso2Attrezzi.get(peso);
			
			if (insiemeAttrezziCorrenti == null) {
				insiemeAttrezziCorrenti = new HashSet<>();
				mappaPeso2Attrezzi.put(peso, insiemeAttrezziCorrenti);
			}
			insiemeAttrezziCorrenti.add(a);
		}
		
		return mappaPeso2Attrezzi;
	}
	
	/**
	 * restituisce l'insieme gli attrezzi nella borsa ordinati per peso e quindi, a parità di peso, per nome
	 * @return insieme degli attrezzi della borsa ordinati per peso
	 */
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		// Passando il comparatore personalizzato, il TreeSet ordinerà per peso/nome 
		// senza perdere elementi con pesi identici ma nomi differenti
		SortedSet<Attrezzo> setOrdinatoPerPeso = new TreeSet<>(new ComparatorePerPesoENome());
		setOrdinatoPerPeso.addAll(this.attrezzi.values());
		return setOrdinatoPerPeso;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): \n");
			
			//1. Stampa Ordinato per Peso (uso le parentesi quadre [ ] per la List)
			s.append(" - Lista di contenuti ordinata per peso: [ ");
			for (Attrezzo a : this.getContenutoOrdinatoPerPeso())
				s.append(a.toString() + " ");
			s.append("]\n");
			
			//2. Stampa Ordinato Per Nome (uso le parentesi graffe { } per il SortedSet)
			s.append(" - Insieme di contenuti ordinata per nome: { ");
			for (Attrezzo a : this.getContenutoOrdinatoPerNome()) {
				s.append(a.toString() + " ");
			}
			s.append("}\n");
			
			//3. Stampa Raggruppato Per Peso (Mappa di coppie (chiave, {valori}))
			s.append(" - Raggruppamento per peso: ");
			Map<Integer, Set<Attrezzo>> raggruppati = this.getContenutoRaggruppatoPerPeso();
			for (Map.Entry<Integer, Set<Attrezzo>> entry : raggruppati.entrySet()) {
				s.append("(" + entry.getKey() + ", { ");
				for (Attrezzo a : entry.getValue()) {
					s.append(a.getNome() + " ");
				}
				s.append("}) ");
			}
		} else
			s.append("Borsa vuota");
		
		return s.toString();
	}

}
