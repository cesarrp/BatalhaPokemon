package pokemon;

import java.util.Random;

import events.*;

	public class BatalhaControle extends Controller{
		private int potion = 20;
		private Treinador t[] = new Treinador[2];
		private int cont_round = 0;
		
		private class FugirBatalha extends Event{
			private Treinador desistente;
			public FugirBatalha (long eventTime, Treinador desistente){
				super(eventTime);
				this.desistente = desistente;
			}
			
			public void action(){
				desistente.setFugiuBatalha(true);
				desistente.setPrioridadeAcao(1);
			}
			
			public String description(){ 
				return ("O " + desistente.getNomeTreinador() + " fugiu da batalha.");
			}
		}
		
		private class TrocarPokemon extends Event{
			Treinador trocador;
			public TrocarPokemon(long eventTime, Treinador trocador){
				super(eventTime);
				this.trocador = trocador;
			}
			public void action(){
				Random random = new Random();
				boolean sorteou = false;
				if(trocador.getListaPokemons().length == 1) sorteou = true;
				else{
					int i;
					while(sorteou != true){
						do{
							i = random.nextInt(trocador.getListaPokemons().length);
						}while(trocador.getListaPokemons()[i] == null);
						if (i != trocador.getPokemonAtivo() && trocador.getListaPokemons()[i].desmaiado() == false){
							trocador.setPokemonAtivo(i);
							sorteou = true;
						}
					}
				}
				trocador.setPrioridadeAcao(2);

			}
			
			public String description(){
				return trocador.getNomeTreinador() + " trocou para: " + trocador.getPokemon(trocador.getPokemonAtivo()).getNome();
			}
		}
		
		private class UsarItem extends Event{
			Treinador usuario;
			public UsarItem(long eventTime, Treinador usuario){
				super(eventTime);
				this.usuario = usuario;
			}
			
			public void action(){
				usuario.getPokemon(usuario.getPokemonAtivo()).setHP(usuario.getPokemon(usuario.getPokemonAtivo()).getHp() + potion);
				usuario.setPrioridadeAcao(3);
			}
			
			public String description(){
				return "Item usado com sucesso pelo " + usuario.getNomeTreinador();
			}
		}
		public Treinador treinadorA(){
			Treinador tA;
			tA = new Treinador("TreinadorA",
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
			return tA;
		}
		
		public Treinador treinadorB(){
			Treinador tB;
			tB = new Treinador("TreinadorB",
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
			return tB;
		}
		
		public void comecaBatalha(Treinador tA, Treinador tB){
			t[0] = tA;
			t[1] = tB;
			System.out.println("Batalha Pokemon");
			System.out.println(t[0].getNomeTreinador() + " vs " + t[1].getNomeTreinador());
			System.out.println(t[0].getNomeTreinador() + " escolheu " + t[0].getPokemon(t[0].getPokemonAtivo()).getNome());
			System.out.println(t[1].getNomeTreinador() + " escolheu " + t[1].getPokemon(t[1].getPokemonAtivo()).getNome());
		}
		
		public boolean acabouBatalha(){
			if (t[0].todosPokemonsDesmaiados() || t[1].todosPokemonsDesmaiados() || t[0].isFugiuBatalha() || t[1].isFugiuBatalha())
				return true;
			else
				return false;
		}
		
		private class FimBatalha extends Event{
			Treinador tA;
			Treinador tB;
			public FimBatalha(long eventTime, Treinador tA, Treinador tB){
				super(eventTime);
				this.tA = tA;
				this.tB = tB;
			}
			
			public void action(){
				finalizaEventos();
			}
			
			public String description(){
				if(tA.todosPokemonsDesmaiados() || tA.isFugiuBatalha())
					return tB.getNomeTreinador()  + "venceu a batalha!";
				else
					return tA.getNomeTreinador() + " venceu a batalha!";
			}
		}
			
		private class NovoRound extends Event{
			
			public NovoRound(long eventTime){
				super(eventTime);
			}
			
			public void action(){
				long tm = System.currentTimeMillis();
				for (int i = 0; i < 2; i++){
					double rand = Math.random();
					if(rand <= 0.08)//fugir da batalha
						t[i].setPrioridadeAcao(1);
					else if(rand <= 0.5)//trocar pokemon
						t[i].setPrioridadeAcao(2);
					else if(rand <= 0.8)
						t[i].setPrioridadeAcao(3);
					else
						t[i].setPrioridadeAcao(4);
				}
				
				if(t[0].getPrioridadeAcao() >= t[1].getPrioridadeAcao()){
					for(int i = 0; i < 2; i++){
						if(t[i].getPrioridadeAcao() == 1)
							addEvent(new FugirBatalha(tm + 1000 + 2000*i, t[i]));
						else if(t[i].getPrioridadeAcao() == 2)
							addEvent(new TrocarPokemon(tm + 1000 + 2000*i, t[i]));
						else if(t[i].getPrioridadeAcao() == 3)
							addEvent(new UsarItem(tm + 1000 + 2000*i, t[i]));
						else System.out.println("Lutem!");
					}
				}
				else{
					for(int i = 1; i > -1; i--){
						if(t[i].getPrioridadeAcao() == 1)
							addEvent(new FugirBatalha(tm + 1000 + 2000*i, t[i]));
						else if(t[i].getPrioridadeAcao() == 2)
							addEvent(new TrocarPokemon(tm + 1000 + 2000*i, t[i]));
						else if(t[i].getPrioridadeAcao() == 3)
							addEvent(new UsarItem(tm + 1000 + 2000*i, t[i]));
						//else LUTA
					}					
				}
				
			}

			
			public String description(){
				cont_round ++;
				return "Round " + cont_round;
			}
		}
		
		private class FinalizaRound extends Event{
			public FinalizaRound(long eventTime){
				super(eventTime);
			}
			
			public void action(){
				if (acabouBatalha())
					addEvent(new FimBatalha(System.currentTimeMillis() + 2000,t[0],t[1]));
			}
			
			public String description(){
				String str1 = t[0].getPokemon(t[0].getPokemonAtivo()).getNome().name() + " do treinador " + t[0].getNomeTreinador() + " esta com " + t[0].getPokemon(t[0].getPokemonAtivo()).getHp() + " de HP\n";
				String str2 = t[1].getPokemon(t[1].getPokemonAtivo()).getNome().name() + " do treinador " + t[1].getNomeTreinador() + " esta com " + t[1].getPokemon(t[1].getPokemonAtivo()).getHp() + " de HP\n";
				return str1 + str2;
			}
			
		}
		public static void main(String[] args) {
			BatalhaControle bc = new BatalhaControle();
			long tm = System.currentTimeMillis();
			bc.comecaBatalha(bc.treinadorA(),bc.treinadorB());
			while(bc.acabouBatalha() == false){
				bc.addEvent(bc.new NovoRound(tm));
				bc.run();
			}
		}		
			
	}