package pokemon;
import java.util.ArrayList;
import java.util.Random;

import events.Event;


public class Treinador {
	private String nomeTreinador;
	private Pokemon[] listaPokemons = new Pokemon[6];
	private int pokemonAtivo;
	
	public int getPokemonAtivo() {
		return pokemonAtivo;
	}

	public void setPokemonAtivo(int pokemonAtivo) {
		this.pokemonAtivo = pokemonAtivo;
	}

	public String getNomeTreinador() {
		return nomeTreinador;
	}
	
	public Pokemon[] getListaPokemons() {
		return listaPokemons;
	}
	
	public Pokemon getPokemon(int i){
		return listaPokemons[i];
	}

	public Treinador(Pokemon[] listaPokemons){
		this.listaPokemons = listaPokemons;
		pokemonAtivo = 0;
	}
	
	
	private class TrocarPokemon extends Event{
		public TrocarPokemon(long eventTime){
			super(eventTime);
		}
		public void action(){
			Random random = new Random();
			boolean sorteou = false;
			if(listaPokemons.length == 1) sorteou = true;
			else{
				while(sorteou != true){
					int i = random.nextInt(listaPokemons.length);
					if (pokemonAtivo.equals(listaPokemons[i]) == false){
						pokemonAtivo = listaPokemons[i];
						sorteou = true;
					}
					
				}
			}

		}
		
		public String description(){
			return "Pokemon trocado para: "; //+ pokemonAtivo.getNome()
		}
	}
	private class UsarItem extends Event{
		public UsarItem(long eventTime){
			super(eventTime);
		}
		
		public void action(){
			//pokemonAtivo.setHP(pokemonAtivo.getHP()*potion);
		}
		
		public String description(){
			return "Item usado com sucesso pelo " + nomeTreinador;
		}
	}
	public void atacar(){
		
	}
	
	public boolean todosPokemonsDesmaiados(){
		return true;
	}
	
}
