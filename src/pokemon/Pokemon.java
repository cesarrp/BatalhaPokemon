package pokemon;

public class Pokemon {
	private NomePokemon nome;
	private int hp;
	private Ataque[] listaAtaques = new Ataque[3];
	private Tipo tipoPokemon;
	private int velocidade;
	private int level;
	
	public Pokemon(NomePokemon nome, int hp, Ataque[] listaAtaques, Tipo tipoPokemon, int velocidade, int level){
		this.nome = nome;
		this.hp = hp;
		this.listaAtaques = listaAtaques;
		this.tipoPokemon = tipoPokemon;
		this.velocidade = velocidade;
		this.level = level;
	}
	
	
	public void atacar(){
		
	}
	
	public boolean desmaiado(){
		return true;
	}
	
	public void sofrerAtaque(){
		
	}
}
