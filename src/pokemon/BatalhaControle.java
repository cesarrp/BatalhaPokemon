package pokemon;

import java.util.ArrayList;
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
				desistente.setPrioridadeAcao(Acao.FUGIR);
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
				if(trocador.getListaPokemons().size() == 1) sorteou = true;
				else{
					int i;
					while(sorteou != true){
						do{
							i = random.nextInt(trocador.getListaPokemons().size());
						}while(trocador.getListaPokemons().get(i) == null);
						if (i != trocador.getPokemonAtivo() && trocador.getListaPokemons().get(i).desmaiado() == false){
							trocador.setPokemonAtivo(i);
							sorteou = true;
						}
					}
				}
				trocador.setPrioridadeAcao(Acao.TROCAR);

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
				usuario.setPrioridadeAcao(Acao.ITEM);
			}
			
			public String description(){
				return "Item usado com sucesso pelo " + usuario.getNomeTreinador();
			}
		}
		public Treinador treinadorA(){
			Treinador tA;
			tA = new Treinador("Cesar", new ArrayList<Pokemon>() {
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
			tB = new Treinador("Renan", new ArrayList<Pokemon>() {
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
					t[i].setPrioridadeAcao(t[i].escolherAcao());
				}
				
				if(t[0].getPrioridadeAcao().ordinal() >= t[1].getPrioridadeAcao().ordinal()){
					for(int i = 0; i < 2; i++){
						if(t[i].getPrioridadeAcao() == Acao.FUGIR)
							addEvent(new FugirBatalha(tm + 1000 + 2000*i, t[i]));
						else if(t[i].getPrioridadeAcao() == Acao.TROCAR)
							addEvent(new TrocarPokemon(tm + 1000 + 2000*i, t[i]));
						else if(t[i].getPrioridadeAcao() == Acao.ITEM)
							addEvent(new UsarItem(tm + 1000 + 2000*i, t[i]));
						else System.out.println("Lutem!");
					}
				}
				else{
					for(int i = 1; i > -1; i--){
						if(t[i].getPrioridadeAcao() == Acao.FUGIR)
							addEvent(new FugirBatalha(tm + 1000 + 2000*i, t[i]));
						else if(t[i].getPrioridadeAcao() == Acao.TROCAR)
							addEvent(new TrocarPokemon(tm + 1000 + 2000*i, t[i]));
						else if(t[i].getPrioridadeAcao() == Acao.ITEM)
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