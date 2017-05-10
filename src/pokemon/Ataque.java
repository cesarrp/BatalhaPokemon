package pokemon;

public class Ataque {
	private String nome;
	private int dano;
	private int velocidade;
	private Tipo tipoAtaque;
	
	public Ataque(String nome, int dano, int velocidade, Tipo tipoAtaque){
		this.nome = nome;
		this.dano = dano;
		this.velocidade = velocidade;
		this.tipoAtaque = tipoAtaque;
	}

	public String getNome() {
		return nome;
	}

	public int getDano() {
		return dano;
	}

	public int getVelocidade() {
		return velocidade;
	}

	public Tipo getTipoAtaque() {
		return tipoAtaque;
	}
	
	
}
