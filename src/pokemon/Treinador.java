package pokemon;

public class Treinador {
	private String nomeTreinador;
	private Pokemon[] listaPokemons = new Pokemon[6];
	private int pokemonAtivo;
	private boolean desmaiados = false;
	
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
		for (int i = 0; i < listaPokemons.length; i++){
			if(listaPokemons[i].desmaiado() == false)
				return false;
		}
		return true;
	}
	
}
