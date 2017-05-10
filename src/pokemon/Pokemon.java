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
	
	public NomePokemon getNome() {
		return nome;
	}

	public int getHp() {
		return hp;
	}
	
	public void setHP(int hp){
		this.hp = hp;
	}



	public Ataque[] getListaAtaques() {
		return listaAtaques;
	}



	public Tipo getTipoPokemon() {
		return tipoPokemon;
	}



	public int getVelocidade() {
		return velocidade;
	}



	public int getLevel() {
		return level;
	}



	public void atacar(){
		
	}
	
	public boolean desmaiado(){
		if(hp <= 0)
			return true;
		else
			return false;
	}
	
	public void sofrerAtaque(){
		
	}
}
