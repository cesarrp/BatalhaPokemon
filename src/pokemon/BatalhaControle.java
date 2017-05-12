package pokemon;

import java.util.ArrayList;
import java.util.Random;

import events.*;

	public class BatalhaControle extends Controller{
		private int potion = 20;
		
		private class FugirBatalha extends Event{
			private Treinador desistente;
			public FugirBatalha (long eventTime, Treinador desistente){
				super(eventTime);
				this.desistente = desistente;
			}
			
			public void action(){
				desistente.setFugiuBatalha(true);
				desistente.setPrioridadeAcao(Acao.FUGIR);
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
				if(trocador.getListaPokemons().size() == 1) sorteou = true;
				else{
					while(sorteou != true){
						int i = random.nextInt(trocador.getListaPokemons().size());
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
		
		private class UsarItem extends Event{
			Treinador usuario;
			public UsarItem(long eventTime){
				super(eventTime);
			}
			
			public void action(){
				usuario.getPokemon(usuario.getPokemonAtivo()).setHP(usuario.getPokemon(usuario.getPokemonAtivo()).getHp() + potion); 
			}
			
			public String description(){
				return "Item usado com sucesso pelo " + usuario.getNomeTreinador();
			}
		}
		
		public Treinador treinadorA(){
			Treinador tA;
			tA = new Treinador(new ArrayList<Pokemon>() {
				{
					add(new Pokemon(NomePokemon.Charmander, 100, new ArrayList<Ataque>() {
						{
							add(new Ataque("SCRATCH", 40, 60, Tipo.NORMAL));
							add(new Ataque("GROWL", 0, 100, Tipo.NORMAL));
							add(new Ataque("EMBER", 40, 80, Tipo.FIRE));
						}
					}, Tipo.FIRE, 70, 9));
					add(new Pokemon(NomePokemon.Pikachu, 100, new ArrayList<Ataque>() {
						{
							add(new Ataque("THUNDER SHOCK", 40, 80, Tipo.ELECTRIC));
							add(new Ataque("GROWL", 0, 80, Tipo.NORMAL));
							add(new Ataque("THUNDER WAVE", 0, 80, Tipo.ELECTRIC));
							add(new Ataque("QUICK ATTACK", 40, 100, Tipo.NORMAL));
						}
					}, Tipo.ELECTRIC, 110, 16));
					add(new Pokemon(NomePokemon.Pidgeotto, 100, new ArrayList<Ataque>() {
						{
							add(new Ataque("SAND-ATTACK", 0, 70, Tipo.NORMAL));
							add(new Ataque("QUICK-ATTACK", 40, 100, Tipo.NORMAL));
						}
					}, Tipo.FLYING, 130, 18));
					add(new Pokemon(NomePokemon.Butterfree, 100, new ArrayList<Ataque>() {
						{
							add(new Ataque("TACKLE", 35, 70, Tipo.NORMAL));
							add(new Ataque("STRING SHOT", 0, 50, Tipo.BUG));
							add(new Ataque("HARDEN", 0, 50, Tipo.NORMAL));
							add(new Ataque("CONFUSION", 50, 80, Tipo.PSYCHIC));
						}
					}, Tipo.BUG, 90, 12));
				}
			});
			return tA;
		}
		
		public Treinador treinadorB(){
			Treinador tB;
			tB = new Treinador(new ArrayList<Pokemon>() {
				{
					add(new Pokemon(NomePokemon.Bulbasaur, 100, new ArrayList<Ataque>() {
						{
							add(new Ataque("TACKLE", 35, 60, Tipo.NORMAL));
							add(new Ataque("GROWL", 0, 100, Tipo.NORMAL));
							add(new Ataque("LEECH SEED", 0, 80, Tipo.GRASS));
							add(new Ataque("VINE WHIP", 35, 80, Tipo.GRASS));
						}
					}, Tipo.GRASS, 55, 13));
					add(new Pokemon(NomePokemon.Squirtle, 100, new ArrayList<Ataque>() {
						{
							add(new Ataque("TACKLE", 35, 60, Tipo.NORMAL));
							add(new Ataque("TAIL WHIP", 0, 80, Tipo.NORMAL));
							add(new Ataque("BUBBLE", 20, 70, Tipo.WATER));
							add(new Ataque("WATER GUN", 40, 80, Tipo.WATER));
						}
					}, Tipo.WATER, 60, 10));
					add(new Pokemon(NomePokemon.Magikarp, 100, new ArrayList<Ataque>() {
						{
							add(new Ataque("SPLASH", 0, 80, Tipo.NORMAL));
							add(new Ataque("TACKLE", 35, 60, Tipo.NORMAL));
						}
					}, Tipo.WATER, 100, 18));
					add(new Pokemon(NomePokemon.Diglett, 100, new ArrayList<Ataque>() {
						{
							add(new Ataque("SCRATCH", 40, 60, Tipo.NORMAL));
							add(new Ataque("GROWL", 0, 70, Tipo.NORMAL));
							add(new Ataque("DIG", 100, 90, Tipo.GROUND));
						}
					}, Tipo.GROUND, 110, 19));
				}
			});
			return tB;
		}
		
		public void comecaBatalha(Treinador tA, Treinador tB){
			System.out.println("Batalha Pokemon");
			System.out.println(tA.getNomeTreinador() + " vs " + tB.getNomeTreinador());
			System.out.println(tA.getNomeTreinador() + " escolheu " + tA.getPokemon(tA.getPokemonAtivo()));
			System.out.println(tB.getNomeTreinador() + " escolheu " + tB.getPokemon(tB.getPokemonAtivo()));
		}
		
		public boolean acabouBatalha(Treinador tA, Treinador tB){
			if (tA.todosPokemonsDesmaiados() || tB.todosPokemonsDesmaiados() || tA.isFugiuBatalha() || tB.isFugiuBatalha()){
				finalizaEventos();
				return true;
			}
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
				if(tA.todosPokemonsDesmaiados() || tA.isFugiuBatalha())
					System.out.println(tB.getNomeTreinador() + " venceu a batalha!");
				else if(tB.todosPokemonsDesmaiados() || tB.isFugiuBatalha())
					System.out.println(tA.getNomeTreinador() + " venceu a batalha!");
			}
			
			public String description(){
				return "---------------Batalha terminada-------------";
			}
		}
			
	}
		
			
