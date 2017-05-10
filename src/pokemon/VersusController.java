package pokemon;

public class VersusController {
	public static void main(String[] args) {
		
		// Matriz com indices de [TipoAtaque.ordinal()][TipoPokemonAtacado.ordinal()]
		double[][] multiplicadorDano = new double[][]{
			{1.0, 1.0, 1.0, 0.5, 0.5, 0.5, 0.5, 2.0, 1.0, 1.0, 1.0, 2.0, 2.0, 1.0, 1.0},
			{1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0},
			{1.0, 0.5, 0.5, 1.0, 1.0, 2.0, 1.0, 0.5, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0},
			{0.5, 1.0, 1.0, 1.0, 1.0, 0.5, 0.0, 1.0, 1.0, 2.0, 2.0, 0.5, 0.5, 2.0, 1.0},
			{2.0, 0.5, 1.0, 1.0, 0.5, 1.0, 1.0, 2.0, 1.0, 2.0, 1.0, 1.0, 1.0, 0.5, 0.5},
			{2.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0},
			{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0},
			{0.5, 0.5, 1.0, 1.0, 0.5, 0.5, 2.0, 0.5, 2.0, 1.0, 1.0, 0.5, 1.0, 2.0, 2.0},
			{0.5, 1.0, 2.0, 1.0, 2.0, 0.0, 1.0, 0.5, 1.0, 1.0, 1.0, 2.0, 1.0, 2.0, 1.0},
			{1.0, 2.0, 1.0, 1.0, 1.0, 2.0, 1.0, 2.0, 1.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.5},
			{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0},
			{2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 0.5, 2.0, 0.5, 1.0, 1.0, 1.0, 1.0, 0.5, 1.0},
			{1.0, 1.0, 1.0, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5, 1.0, 1.0},
			{2.0, 1.0, 1.0, 0.5, 2.0, 2.0, 1.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 1.0},
			{1.0, 0.5, 1.0, 1.0, 2.0, 1.0, 1.0, 0.5, 2.0, 1.0, 1.0, 1.0, 1.0, 2.0, 0.5}
			};
		
		Treinador treinadorA = 
				new Treinador(
						new Pokemon[]{
								new Pokemon(NomePokemon.Charmander, 60,
										new Ataque[] { 
												new Ataque("SCRATCH", 40, 60, Tipo.NORMAL), 
												new Ataque("GROWL", 0, 100, Tipo.NORMAL),
												new Ataque("EMBER", 40, 80, Tipo.FIRE), 
												null 
												},
										Tipo.FIRE, 
										70,
										9),
								new Pokemon(NomePokemon.Pikachu, 100,
										new Ataque[] { 
												new Ataque("THUNDER SHOCK", 40, 80, Tipo.ELECTRIC), 
												new Ataque("GROWL", 0, 80, Tipo.NORMAL),
												new Ataque("THUNDER WAVE", 0, 80, Tipo.ELECTRIC), 
												new Ataque("QUICK ATTACK", 40, 100, Tipo.NORMAL) 
												},
										Tipo.ELECTRIC, 
										110,
										16),
								new Pokemon(NomePokemon.Pidgeotto, 120,
										new Ataque[] { 
												new Ataque("SAND-ATTACK", 0, 70, Tipo.NORMAL), 
												new Ataque("QUICK-ATTACK", 40, 100, Tipo.NORMAL),
												null,
												null
												},
										Tipo.FLYING, 
										130,
										18),
								new Pokemon(NomePokemon.Butterfree, 100,
										new Ataque[] { 
												new Ataque("TACKLE", 35, 70, Tipo.NORMAL), 
												new Ataque("STRING SHOT", 0, 50, Tipo.BUG),
												new Ataque("HARDEN", 0, 50, Tipo.NORMAL), 
												new Ataque("CONFUSION", 50, 80, Tipo.PSYCHIC) 
												},
										Tipo.BUG, 
										90,
										12),
								null,
								null
								
						});
		
		Treinador treinadorB = 
				new Treinador(
						new Pokemon[] { 
								new Pokemon(NomePokemon.Bulbasaur, 90,
										new Ataque[] { 
												new Ataque("TACKLE", 35, 60, Tipo.NORMAL), 
												new Ataque("GROWL", 0, 100, Tipo.NORMAL),
												new Ataque("LEECH SEED", 0, 80, Tipo.GRASS), 
												new Ataque("VINE WHIP", 35, 80, Tipo.GRASS), 
												},
										Tipo.GRASS, 
										55,
										13),
								new Pokemon(NomePokemon.Squirtle, 100,
										new Ataque[] { 
												new Ataque("TACKLE", 35, 60, Tipo.NORMAL), 
												new Ataque("TAIL WHIP", 0, 80, Tipo.NORMAL),
												new Ataque("BUBBLE", 20, 70, Tipo.WATER), 
												new Ataque("WATER GUN", 40, 80, Tipo.WATER) 
												},
										Tipo.WATER, 
										60,
										10),
								new Pokemon(NomePokemon.Magikarp, 60,
										new Ataque[] { 
												new Ataque("SPLASH", 0, 80, Tipo.NORMAL), 
												new Ataque("TACKLE", 35, 60, Tipo.NORMAL),
												null,
												null
												},
										Tipo.WATER, 
										100,
										18),
								new Pokemon(NomePokemon.Diglett, 130,
										new Ataque[] { 
												new Ataque("SCRATCH", 40, 60, Tipo.NORMAL), 
												new Ataque("GROWL", 0, 70, Tipo.NORMAL),
												new Ataque("DIG", 100, 90, Tipo.GROUND),
												null
												},
										Tipo.GROUND, 
										110,
										19),
								null,
								null
								}
						);
	}
}
