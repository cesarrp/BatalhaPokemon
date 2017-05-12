package pokemon;

import java.util.ArrayList;

public class Treinador {
	private String nomeTreinador;
	private ArrayList<Pokemon> listaPokemons = new ArrayList<Pokemon>();
	private int indicePokemonAtivo;
	private boolean fugiuBatalha = false;
	private Acao prioridadeAcao;
	private int indiceAtaqueEscolhido;
	
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
			
	
	public int getIndiceAtaqueEscolhido() {
		return indiceAtaqueEscolhido;
	}

	public void setIndiceAtaqueEscolhido(int indiceAtaqueEscolhido) {
		this.indiceAtaqueEscolhido = indiceAtaqueEscolhido;
	}			
	public Acao getPrioridadeAcao() {
		return prioridadeAcao;
	}

	public void setPrioridadeAcao(Acao prioridadeAcao) {
		this.prioridadeAcao = prioridadeAcao;
	}

	public boolean getFugiuBatalha() {
		return fugiuBatalha;
	}

	public void setFugiuBatalha(boolean fugiuBatalha) {
		this.fugiuBatalha = fugiuBatalha;
	}

	public int getIndicePokemonAtivo() {
		return indicePokemonAtivo;
	}

	public void setIndicePokemonAtivo(int pokemonAtivo) {
		this.indicePokemonAtivo = pokemonAtivo;
	}
	
	public Pokemon getPokemonAtivo() {
		return listaPokemons.get(indicePokemonAtivo);
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

	public Ataque getAtaque(int indiceAtaque) {
		return getPokemonAtivo().getAtaque(indiceAtaque);
	}
	
	public Ataque getAtaqueEscolhido() {
		return getPokemonAtivo().getAtaque(indiceAtaqueEscolhido);
	}

	public Treinador(String nomeTreinador, ArrayList<Pokemon> listaPokemons){ 
	    this.nomeTreinador = nomeTreinador; 
		this.listaPokemons = listaPokemons;
		indicePokemonAtivo = 0;
	}

	public boolean todosPokemonsDesmaiados() {
		for (Pokemon pokemon : listaPokemons) {
			if (!pokemon.desmaiado())
				return false;
		}
		return true;
	}
	
	public double calculaMultiplicador(Treinador treinadorDefensor, int indiceAtaque){
		
		return multiplicadorDano[this.getAtaque(indiceAtaque).getTipoAtaque().ordinal()]
								[treinadorDefensor.getPokemonAtivo().getTipoPokemon().ordinal()];
	}

	public int danoTotal(Treinador treinadorDefensor) {

		return (int) (this.getAtaqueEscolhido().getDano() * this.getPokemonAtivo().getLevel()
				* calculaMultiplicador(treinadorDefensor, indiceAtaqueEscolhido)
				/ treinadorDefensor.getPokemonAtivo().getLevel());
	}

	public Acao escolherAcao() {
		double randomNumber = Math.random(); // 0 a 1
		double porcentagemPokemonsMortos = (quantidadePokemons() - pokemonsVivos().size()) / quantidadePokemons();
		int danoPokemonAtivo = 100 - this.getPokemonAtivo().getHp();
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

	public int escolherAtaque(Treinador treinadorDefensor){
		double[] ataqueTotal = new double[4];
		double somaAtaques = 0;
		Ataque[] ataqueArray = getPokemonAtivo().getListaAtaques().toArray(new Ataque[4]);
		for(int i=0; i<4; i++){
			if(ataqueArray[i] == null){
				ataqueTotal[i] = 0;
			}
			else{
				ataqueTotal[i] = ataqueArray[i].getDano()*calculaMultiplicador(treinadorDefensor, i);
			}
			somaAtaques += ataqueTotal[i];
		}
		double randomNumber = Math.random(); // 0 a 1
		if(randomNumber >= 0 && randomNumber < ataqueTotal[0]/somaAtaques)
			return 0;
		else if(randomNumber >= ataqueTotal[0]/somaAtaques && randomNumber < (ataqueTotal[0]/somaAtaques + ataqueTotal[1]/somaAtaques))
			return 1;
		else if(randomNumber >= (ataqueTotal[0]/somaAtaques + ataqueTotal[1]/somaAtaques) && randomNumber < (ataqueTotal[0]/somaAtaques + ataqueTotal[1]/somaAtaques + ataqueTotal[2]/somaAtaques))
			return 2;
		else if(randomNumber >= (ataqueTotal[0]/somaAtaques + ataqueTotal[1]/somaAtaques + ataqueTotal[2]/somaAtaques) && randomNumber < (ataqueTotal[0]/somaAtaques + ataqueTotal[1]/somaAtaques + ataqueTotal[2]/somaAtaques + ataqueTotal[3]/somaAtaques))
			return 3;
		else
			return 0;
	}

	public int velocidadeAtaqueEscolhido() {
		return this.getAtaqueEscolhido().getVelocidade()
				* this.getPokemonAtivo().getLevel()
				* this.getPokemonAtivo().getVelocidade();
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
