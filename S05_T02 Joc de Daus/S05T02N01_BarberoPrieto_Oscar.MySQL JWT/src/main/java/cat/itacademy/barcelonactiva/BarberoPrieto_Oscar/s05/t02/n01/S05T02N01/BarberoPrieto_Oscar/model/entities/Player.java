package cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "PLAYERS")
public class Player implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME", nullable = false)
	private String nom;

	@Column(name = "PERCENTAGE")
	private double percentatge;

	@Column(name = "ROLLS")
	private long rolls;

	@Column(name = "WINNER")
	private long winner;

	@Temporal(TemporalType.DATE)
	@Column(name = "REGIST DATE")
	private Date data;

	@OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<Game> gamesPlayed = new ArrayList<>();

	public Player() {
		this.data = new Date();
	}

	public Player(String nom) {
		super();
		this.nom = nom;
		this.data = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
