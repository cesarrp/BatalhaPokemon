package pokemon;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Random;
import events.*;

public class Ex1Controle extends Controller {
	private int potion = 20;
	private Treinador t[] = new Treinador[2];
	private int cont_round = 0;

	private class FugirBatalha extends Event {
		private Treinador desistente;
		

		public FugirBatalha(long eventTime, Treinador desistente) {
			super(eventTime);
			this.desistente = desistente;
		}

		public void action() {
			desistente.setFugiuBatalha(true);
			desistente.setPrioridadeAcao(Acao.FUGIR);
		}

		public String description() {
			return ("O " + desistente.getNomeTreinador() + " fugiu da batalha.");
		}
	}

	private class TrocarPokemon extends Event {
		Treinador trocador;
		boolean sorteou = false;

		public TrocarPokemon(long eventTime, Treinador trocador) {
			super(eventTime);
			this.trocador = trocador;
		}

		public void action() {
			Random random = new Random();
			if(trocador.pokemonsVivos().size() == 1 && trocador.getPokemonAtivo().desmaiado()){
				addEvent(new FimBatalha(System.currentTimeMillis() + 1000,trocador));
			}
			else if (trocador.pokemonsVivos().size() != 1) {
				int i;
				if(trocador.todosPokemonsDesmaiados())
					addEvent(new FimBatalha(System.currentTimeMillis() + 1000, trocador));
				else{
					while (sorteou != true) {
						do {
							i = random.nextInt(trocador.getListaPokemons().size());
						} while (trocador.getListaPokemons().get(i) == null);
						if (i != trocador.getIndicePokemonAtivo() && trocador.getListaPokemons().get(i).desmaiado() == false) {
							trocador.setIndicePokemonAtivo(i);
							sorteou = true;
						}
					}
				}
			}
			trocador.setPrioridadeAcao(Acao.TROCAR);

		}

		public String description() {
			if(sorteou)
				return trocador.getNomeTreinador() + " trocou para: "
					+ trocador.getPokemonAtivo().getNome();
			else
				return trocador.getPokemonAtivo().getNome().name() + " do treinador " + trocador.getNomeTreinador() + " desmaiou! \n"
						+ trocador.getNomeTreinador() + " nao possui mais pokemons vivos!";
		}
	}

	private class UsarItem extends Event {
		Treinador usuario;

		public UsarItem(long eventTime, Treinador usuario) {
			super(eventTime);
			this.usuario = usuario;
		}

		public void action() {
			if(usuario.getPokemonAtivo().getHp() + potion <= 100)
			usuario.getPokemonAtivo().setHP(usuario.getPokemonAtivo().getHp() + potion);
			else
				usuario.getPokemonAtivo().setHP(100);
			usuario.setPrioridadeAcao(Acao.ITEM);
		}

		public String description() {
			return "Item usado com sucesso pelo " + usuario.getNomeTreinador();
		}
	}

	@SuppressWarnings("serial")
	public Treinador treinadorA() {
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

	@SuppressWarnings("serial")
	public Treinador treinadorB() {
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

	public void comecaBatalha(Treinador tA, Treinador tB) {
		t[0] = tA;
		t[1] = tB;
		System.out.println("Batalha Pokemon");
		System.out.println(t[0].getNomeTreinador() + " vs " + t[1].getNomeTreinador());
		System.out.println(t[0].getNomeTreinador() + " escolheu " + t[0].getPokemonAtivo().getNome());
		System.out.println(t[1].getNomeTreinador() + " escolheu " + t[1].getPokemonAtivo().getNome());
	}

	public boolean acabouBatalha() {
		if ((t[0].pokemonsVivos().size() == 1 && t[0].getPokemonAtivo().desmaiado()) ||( t[1].pokemonsVivos().size() == 1 && t[1].getPokemonAtivo().desmaiado() )|| 
				t[0].isFugiuBatalha() || t[1].isFugiuBatalha())
			return true;
		else
			return false;
	}

	private class FimBatalha extends Event {
		Treinador tA;
		Treinador tB;

		public FimBatalha(long eventTime, Treinador tA, Treinador tB) {
			super(eventTime);
			this.tA = tA;
			this.tB = tB;
		}
		
		public FimBatalha(long eventTime, Treinador tA) {
			super(eventTime);
			this.tA = tA;
			if(tA.equals(t[0])) this.tB = t[1];
			else this.tB = t[0];
		}

		public void action() {
			finalizaEventos();
		}

		public String description() {
			if ((tA.pokemonsVivos().size() == 1 && tA.getPokemonAtivo().desmaiado()) || tA.isFugiuBatalha())
				return tB.getNomeTreinador() + " venceu a batalha!";
			else
				return tA.getNomeTreinador() + " venceu a batalha!";
		}
	}

	private class Atacar extends Event {
		Treinador tA; // atacante
		Treinador tB; // defensor

		public Atacar(long eventTime, Treinador tA, Treinador tB) {
			super(eventTime);
			this.tA = tA;
			this.tB = tB;
		}

		public void action() {
			tA.setIndiceAtaqueEscolhido(tA.escolherAtaque(tB));
			if(tA.getPokemonAtivo().getHp() > 0)
				tB.getPokemonAtivo().sofrerAtaque(tA.danoTotal(tB));
			
			if(tB.getPokemonAtivo().getHp() == 0){
				addEvent(new TrocarPokemon(System.currentTimeMillis() + 4000,tB));
			}

		}

		public String description() {		
			String superEff = tA.getPokemonAtivo().getNome().name() + " used " + tA.getAtaqueEscolhido().getNome()+ "\nIt's super effective!";
			String notEff = tA.getPokemonAtivo().getNome().name() + " used " + tA.getAtaqueEscolhido().getNome()+ "\nIt's not very effective!";
			String eff = tA.getPokemonAtivo().getNome().name() + " used " + tA.getAtaqueEscolhido().getNome();
			if(tA.getPokemonAtivo().getHp() > 0){
				if (tA.calculaMultiplicador(tB, tA.getIndiceAtaqueEscolhido()) == 2.0)
					return superEff;
				else if(tA.calculaMultiplicador(tB, tA.getIndiceAtaqueEscolhido()) == 0.5 && tB.getPokemonAtivo().getHp() <= 0)
					return notEff;
				else
					return eff;
		
			}
			else return "";
		}

	}

	private class NovoRound extends Event {

		public NovoRound(long eventTime) {
			super(eventTime);
		}

		public void action() {
			long tm = System.currentTimeMillis();
			for (int i = 0; i <= 1; i++) {
				t[i].setPrioridadeAcao(t[i].escolherAcao());
				if (t[i].getPrioridadeAcao() == Acao.ATACAR)
					t[i].setIndiceAtaqueEscolhido(t[i].escolherAtaque(t[(i + 1) % 2]));
			}
			int order = ordem();
			if(order == 0)
				for (int i = 0; i <= 1; i++) {
					switch (t[i].getPrioridadeAcao()) {
					case FUGIR:
						addEvent(new FugirBatalha(tm + 1000 + 2000 * (i), t[i]));
						break;
					case TROCAR:
						if(t[0].getPrioridadeAcao() != Acao.FUGIR)
						addEvent(new TrocarPokemon(tm + 1000 + 2000 * (i), t[i]));
						break;
					case ITEM:
						if(t[0].getPrioridadeAcao() != Acao.FUGIR)
						addEvent(new UsarItem(tm + 1000 + 2000 * (i), t[i]));
						break;
					case ATACAR:
						if(t[0].getPrioridadeAcao() != Acao.FUGIR)
						addEvent(new Atacar(tm + 1000 + 2000 * (i), t[i],t[(i + 1) % 2]));
					}
				}
			else
				for (int i = 1; i >= 0; i--) {
					switch (t[i].getPrioridadeAcao()) {
					case FUGIR:
						addEvent(new FugirBatalha(tm + 3000 - 2000 * (i), t[i]));
						break;
					case TROCAR:
						if(t[1].getPrioridadeAcao() != Acao.FUGIR)
						addEvent(new TrocarPokemon(tm + 3000 - 2000 * (i), t[i]));
						break;
					case ITEM:
						if(t[1].getPrioridadeAcao() != Acao.FUGIR)
						addEvent(new UsarItem(tm + 3000 - 2000 * (i), t[i]));
						break;
					case ATACAR:
						if(t[1].getPrioridadeAcao() != Acao.FUGIR)
						addEvent(new Atacar(tm + 3000 - 2000 * (i), t[i], t[(i+1) % 2]));
					}
				}

			addEvent(new FinalizaRound(tm + 6000));

		}

		public String description() {
			cont_round++;
			return "Round " + cont_round;
		}

		// se 0 -> t[0] primeiro
		// se 1 -> t[1] primeiro
		public int ordem() {
			if (t[0].getPrioridadeAcao().ordinal() <= t[1].getPrioridadeAcao().ordinal())
				return 0;
			else if (t[0].getPrioridadeAcao() == t[1].getPrioridadeAcao() && t[0].getPrioridadeAcao() == Acao.ATACAR
					&& t[0].getAtaqueEscolhido().getVelocidade() >= t[1].getAtaqueEscolhido().getVelocidade())
				return 0;
			else
				return 1;
		}
	}

	private class FinalizaRound extends Event {
		public FinalizaRound(long eventTime) {
			super(eventTime);
		}

		public void action() {
			if (acabouBatalha())
				addEvent(new FimBatalha(System.currentTimeMillis() + 2000, t[0], t[1]));
		}

		public String description() {
			if (!acabouBatalha()) {		
				String str1 = t[0].getPokemonAtivo().getNome().name() + " do treinador "
						+ t[0].getNomeTreinador() + " esta com " + t[0].getPokemonAtivo().getHp()
						+ " de HP\n";
				String str2 = t[1].getPokemonAtivo().getNome().name() + " do treinador "
						+ t[1].getNomeTreinador() + " esta com " + t[1].getPokemonAtivo().getHp()
						+ " de HP\n";
				if(t[0].getPokemonAtivo().getHp() == 0){
					String str3 = t[0].getPokemonAtivo().getNome().name() + " do treinador " + t[0].getNomeTreinador() + " desmaiou!\n";
					return str3 + str1 + str2;
				}
				else if(t[1].getPokemonAtivo().getHp() == 0){
					String str4 = t[1].getPokemonAtivo().getNome().name() + " do treinador " + t[1].getNomeTreinador() + " desmaiou!\n";
					return str4 + str1 + str2;
				}
				else
					return str1 + str2;
			} else
				return "";
		}

	}

	public static void main(String[] args) {
		
		//reproduz video com musica de batalha
		Desktop desktop = null;
		desktop = Desktop.getDesktop();
		URI uri = null;
		try {
			uri = new URI("https://www.youtube.com/watch?v=2Jmty_NiaXc");
			desktop.browse(uri);
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (URISyntaxException use) {
			use.printStackTrace();
		}
		
		Ex1Controle bc = new Ex1Controle();
		long tm = System.currentTimeMillis();
		bc.comecaBatalha(bc.treinadorA(), bc.treinadorB());
		while (bc.acabouBatalha() == false) {
			bc.addEvent(bc.new NovoRound(tm));
			bc.run();
		}
	}

}