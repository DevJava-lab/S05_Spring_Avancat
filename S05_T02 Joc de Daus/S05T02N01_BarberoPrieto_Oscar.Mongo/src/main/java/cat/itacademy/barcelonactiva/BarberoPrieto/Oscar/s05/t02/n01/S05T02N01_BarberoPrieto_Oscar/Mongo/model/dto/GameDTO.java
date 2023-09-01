package cat.itacademy.barcelonactiva.BarberoPrieto.Oscar.s05.t02.n01.S05T02N01_BarberoPrieto_Oscar.Mongo.model.dto;

public class GameDTO {

	private int valorDau1;
	private int valorDau2;
	private String resultat;
	private double percentatge;

	public GameDTO(int valorDau1, int valorDau2, String resultat) {
		super();
		this.valorDau1 = valorDau1;
		this.valorDau2 = valorDau2;
		this.resultat = resultat;
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
		return resultat;
	}

	public void setResultat(String resultat) {
		this.resultat = resultat;
	}

	public double getPercentatge() {
		return percentatge;
	}

	public void setPercentatge(double percentatge) {
		this.percentatge = percentatge;
	}

}
