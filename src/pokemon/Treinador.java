package pokemon;

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
	
	public boolean todosPokemonsDesmaiados(){
		return true;
	}
	
}
