package cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.entities;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "Game")
@JsonFormat
public class Game implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private int valorDau1;

	private int valorDau2;

	private String resultat;

	public Game() {
		super();
		valorDau1 = (int) Math.floor(Math.random() * 6 + 1);
		valorDau2 = (int) Math.floor(Math.random() * 6 + 1);
		this.resultat=getResultat();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

}
