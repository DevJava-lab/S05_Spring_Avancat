package cat.itacademy.barcelonactiva.BarberoPrieto_Oscar.s05.t02.n01.S05T02N01.BarberoPrieto_Oscar.model.dto;

public class PlayerDTO {

	private String nom;
	private double percentatge;
	private long rolls;
	
	public PlayerDTO() {
		
	}

	public PlayerDTO(String nom, double percentatge, long rolls) {
		super();
		this.nom = nom;
		this.percentatge = percentatge;
		this.rolls = rolls;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPercentatge() {
		return percentatge;
	}

	public void setPercentatge(double percentatge) {
		this.percentatge = percentatge;
	}

	public long getRolls() {
		return rolls;
	}

	public void setRolls(long rolls) {
		this.rolls = rolls;
	}

}
