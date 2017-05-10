package pokemon;

import java.util.Random;

import events.Event;

	public class BatalhaControle extends Controller{
		private boolean fugiuBatalha = false;
		
		private class FugirBatalha extends Event{
			private Treinador desistente;
			public FugirBatalha (long eventTime, Treinador desistente){
				super(eventTime);
				this.desistente = desistente;
			}
			
			public void action(){
				fugiuBatalha = true;
			}
			
			public String description(){ 
				return ("O treinador " + desistente.getNomeTreinador() + " fugiu da batalha.");
			}
		}
		
		private class TrocarPokemon extends Event{
			Treinador trocador;
			public TrocarPokemon(long eventTime){
				super(eventTime);
			}
			public void action(){
				Random random = new Random();
				boolean sorteou = false;
				if(trocador.getListaPokemons().length == 1) sorteou = true;
				else{
					while(sorteou != true){
						int i = random.nextInt(trocador.getListaPokemons().length);
						if (trocador.getPokemon(trocador.getPokemonAtivo()).equals(trocador.getPokemon(i)) == false && trocador.getPokemon(i).desmaiado() == false){
							trocador.setPokemonAtivo(i);
							sorteou = true;
						}
					}
				}

			}
			
			public String description(){
				return "Pokemon trocado para: "; //+ pokemonAtivo.getNome()
			}
		}
		
	}
		
			
