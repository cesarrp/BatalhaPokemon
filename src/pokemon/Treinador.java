package pokemon;

public class Treinador {
	private Pokemon[] listaPokemons = new Pokemon[5];
	private Pokemon pokemonAtivo;
	
	public Treinador(Pokemon[] listaPokemons){
		this.listaPokemons = listaPokemons;
		this.pokemonAtivo = listaPokemons[0];
	}
	
	public void fugirBatalha(){
		
	}
	public void trocarPokemon(){
		
	}
	public void usarItem(){
		
	}
	public void atacar(){
		
	}
	
	public boolean todosPokemonsDesmaiados(){
		return true;
	}
	
}
