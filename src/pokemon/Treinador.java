package pokemon;

import java.util.ArrayList;

public class Treinador {
	private String nomeTreinador;
	private ArrayList<Pokemon> listaPokemons = new ArrayList<Pokemon>();
	private int pokemonAtivo;
	private boolean desmaiados = false;
	private boolean fugiuBatalha = false;
	private Acao prioridadeAcao;

	// Matriz com indices de [TipoAtaque.ordinal()][TipoPokemonAtacado.ordinal()]
	private static final double[][] multiplicadorDano = new double[][] {
			{ 1.0, 1.0, 1.0, 0.5, 0.5, 0.5, 0.5, 2.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0 },
			{ 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 },
			{ 1.0, 0.5, 0.5, 1.0, 1.0, 2.0, 1.0, 0.5, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0 },
			{ 0.5, 1.0, 1.0, 1.0, 1.0, 0.5, 0.0, 1.0, 1.0, 2.0, 2.0, 0.5, 0.5, 2.0, 1.0 },
			{ 2.0, 0.5, 1.0, 1.0, 0.5, 1.0, 1.0, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 0.5 },
			{ 2.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0 },
			{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0 },
			{ 0.5, 0.5, 1.0, 1.0, 0.5, 0.5, 2.0, 0.5, 2.0, 1.0, 1.0, 0.5, 1.0, 2.0, 2.0 },
			{ 0.5, 1.0, 2.0, 1.0, 2.0, 0.0, 1.0, 0.5, 1.0, 1.0, 1.0, 2.0, 1.0, 2.0, 1.0 },
			{ 1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.5 },
			{ 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0 },
			{ 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0 },
			{ 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0 },
			{ 2.0, 1.0, 1.0, 0.5, 2.0, 2.0, 1.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0 },
			{ 1.0, 0.5, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5 } };

	public Acao getPrioridadeAcao() {
		return prioridadeAcao;
	}

	public void setPrioridadeAcao(Acao prioridadeAcao) {
		this.prioridadeAcao = prioridadeAcao;
	}

	public boolean isFugiuBatalha() {
		return fugiuBatalha;
	}

	public void setFugiuBatalha(boolean fugiuBatalha) {
		this.fugiuBatalha = fugiuBatalha;
	}

	public int getPokemonAtivo() {
		return pokemonAtivo;
	}

	public Pokemon pokemonAtivo() {
		return listaPokemons.get(pokemonAtivo);
	}

	public void setPokemonAtivo(int pokemonAtivo) {
		this.pokemonAtivo = pokemonAtivo;
	}

	public String getNomeTreinador() {
		return nomeTreinador;
	}

	public ArrayList<Pokemon> getListaPokemons() {
		return listaPokemons;
	}

	public Pokemon getPokemon(int i) {
		return listaPokemons.get(i);
	}

	public Ataque getAtaqueEscolhido(int indiceAtaque) {
		return listaPokemons.get(pokemonAtivo).getAtaque(indiceAtaque);
	}

	public Treinador(ArrayList<Pokemon> listaPokemons) {
		this.listaPokemons = listaPokemons;
		pokemonAtivo = 0;
	}

	public boolean todosPokemonsDesmaiados() {
		for (Pokemon pokemon : listaPokemons) {
			if (!pokemon.desmaiado())
				return false;
		}
		return true;
	}

	public int danoTotal(Treinador treinadorDefensor, int indiceAtaque) {

		return (int) (this.getAtaqueEscolhido(indiceAtaque).getDano() * this.pokemonAtivo().getLevel()
				* multiplicadorDano	[this.getAtaqueEscolhido(indiceAtaque).getTipoAtaque().ordinal()]
									[treinadorDefensor.pokemonAtivo().getTipoPokemon().ordinal()]
				/ treinadorDefensor.pokemonAtivo().getLevel());
	}

	public Acao escolherAcao() {
		// TODO: gerar numero aleatorio
		double randomNumber = 0; // 0 a 1
		double porcentagemPokemonsMortos = (quantidadePokemons() - pokemonsVivos().size()) / quantidadePokemons();
		int danoPokemonAtivo = 100 - this.pokemonAtivo().getHp();
		// soma de tudo deve ser 1 (100%)
		double probFugir = 0.1 + 0.002 * danoPokemonAtivo + 0.4 * porcentagemPokemonsMortos;
		double probTrocar = 0.1 + 0.003 * danoPokemonAtivo + 0.2 * porcentagemPokemonsMortos;
		double probItem = 0.0 + 0.003 * danoPokemonAtivo + 0.2 * porcentagemPokemonsMortos;
		// double probAtacar = 0.8 + 0.002*danoPokemonAtivo + 0.4 * porcentagemPokemonsMortos;

		if (randomNumber >= 0.0 && randomNumber < probFugir)
			return Acao.FUGIR;
		else if (randomNumber >= probFugir && randomNumber < (probFugir + probTrocar))
			return Acao.TROCAR;
		else if (randomNumber >= (probFugir + probTrocar) && randomNumber < (probFugir + probTrocar + probItem))
			return Acao.ITEM;
		else
			return Acao.ATACAR;
	}

	public int escolherAtaque() {
		return 1;
	}

	public int velocidadeAtaqueEscolhido(int indiceAtaque) {
		return this.getAtaqueEscolhido(indiceAtaque).getVelocidade()
				* this.pokemonAtivo().getLevel()
				* this.pokemonAtivo().getVelocidade();
	}

	public ArrayList<Integer> pokemonsVivos() {
		ArrayList<Integer> indicesPokemonsVivos = new ArrayList<Integer>();
		for (Pokemon pokemon : listaPokemons) {
			if (!pokemon.desmaiado())
				indicesPokemonsVivos.add(listaPokemons.indexOf(pokemon));
		}
		return indicesPokemonsVivos;
	}

	public int quantidadePokemons() {
		return listaPokemons.size();
	}

}
