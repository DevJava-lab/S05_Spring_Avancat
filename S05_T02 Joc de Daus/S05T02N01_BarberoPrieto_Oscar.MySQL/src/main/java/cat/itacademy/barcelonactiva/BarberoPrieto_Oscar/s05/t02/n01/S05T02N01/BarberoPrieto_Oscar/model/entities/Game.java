package cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.entities;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "GAMES")
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "DICE 1")
	private int valorDau1;
	@Column(name = "DICE 2")
	
	private int valorDau2;

	@Column(name = "RESULT")
	private String resultat;

	@ManyToOne
	@JoinColumn(name = "id_player")
	@JsonBackReference 
	private Player player;

	public Game() {
		super();
		valorDau1 = (int) Math.floor(Math.random() * 6 + 1);
		valorDau2 = (int) Math.floor(Math.random() * 6 + 1);
		this.resultat = getResultat();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getValorDau1() {
		return valorDau1;
	}

	public void setValorDau1(int valorDau1) {
		this.valorDau1 = valorDau1;
	}

	public int getValorDau2() {
		return valorDau2;
	}

	public void setValorDau2(int valorDau2) {
		this.valorDau2 = valorDau2;
	}

	public String getResultat() {
		return valorDau1 + valorDau2 == 7 ? "Winner" : "Loser";
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	

}
