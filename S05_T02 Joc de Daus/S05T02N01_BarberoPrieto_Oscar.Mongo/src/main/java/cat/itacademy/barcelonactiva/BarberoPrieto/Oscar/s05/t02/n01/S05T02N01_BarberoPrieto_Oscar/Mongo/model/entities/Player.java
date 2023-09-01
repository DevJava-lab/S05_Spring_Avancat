package cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;

@Document(collection = "Player")
@JsonFormat
public class Player implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private String nom;

	private Date data;

	private long rolls;

	private long winner;

	private double percentatge;

	@DBRef
	private List<Game> gamesPlayed = new ArrayList<>();

	public Player() {
		this.data = new Date();
	}

	public Player(String nom) {
		super();
		this.nom = nom;
		this.data = new Date();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {

		this.nom = nom;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public long getRolls() {
		return rolls;
	}

	public void setRolls(long rolls) {
		this.rolls = rolls;
	}

	public long getWinner() {
		return winner;
	}

	public void setWinner(long winner) {
		this.winner = winner;
	}

	public double getPercentatge() {

		return percentatge;
	}

	public void setPercentatge(double percentatge) {

		this.percentatge = percentatge;
	}

	public List<Game> getGamesPlayed() {
		return gamesPlayed;
	}

	public void setGamesPlayed(List<Game> gamesPlayed) {
		this.gamesPlayed = gamesPlayed;
	}

	public void addGame(Game game) {

		rolls++;
		if (game.getResultat() == "Winner") {
			winner++;
		}
		this.setPercentatge((double) winner / rolls * 100);
		gamesPlayed.add(game);

	}

	public void clear() {
		rolls = 0;
		winner = 0;
		percentatge = 0;
		gamesPlayed.removeAll(gamesPlayed);
	}
}
