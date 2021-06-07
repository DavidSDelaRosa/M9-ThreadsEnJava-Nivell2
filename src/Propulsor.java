import javax.swing.JOptionPane;

public class Propulsor implements Runnable {

	private int idPropulsor;
	private int potenciaMaxima;
	private int potenciaActual;
	private Cohete cohete;
	private double potenciaObjetivo;
	private int velocidadInicial;

	public Propulsor(int idPropulsor, int potenciaMaxima, Cohete cohete) {
		this.idPropulsor = idPropulsor;
		this.potenciaMaxima = potenciaMaxima;
		this.cohete = cohete;
		potenciaActual = 0;
	}

	public synchronized void speedUp() {

		System.err.println("ACELERANDO PROPULSOR " + idPropulsor + " DEL COHETE " + cohete.getIdCodigo() + "!");

		while (potenciaActual < potenciaMaxima && (getPotenciaObjetivo() > getCohete().getVelocidadActual())) {

			if (!cohete.isAcelerando()) {
				System.out.println("Deteniendo la aceleración...");
				break;
			}
			potenciaActual++;

			if (potenciaActual == potenciaMaxima) {
				System.err.println("\nPROPULSOR " + idPropulsor + " DEL COHETE " + cohete.getIdCodigo()
						+ " HA ALCANZADO LA POTENCIA MÁXIMA\n");
				break;
			}

			System.out.println("Potencia del propulsor nº: " + idPropulsor + " del Cohete -> " + cohete.getIdCodigo()
					+ ": " + potenciaActual + " kW / " + potenciaMaxima + " kW");
			System.err.println("Potencia actual del cohete " + getCohete().getIdCodigo() + ": " + cohete.getPotenciaActualCohete());
			System.err.println("\nPotencia total actual del cohete " + getCohete().getIdCodigo()+ " : " + cohete.getTotalCurrentPower());

			if (cohete.getVelocidadActual() == potenciaObjetivo) {
				System.err.println("Se ha llegado a la velocidad objetivo con el cohete " + getCohete().getIdCodigo() +"!");
				break;
			}

			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}

	public synchronized void speedDown() {

		System.err.println("FRENANDO PROPULSOR " + idPropulsor + " DEL COHETE " + cohete.getIdCodigo() + "!");

		while (potenciaActual < potenciaMaxima && (getPotenciaObjetivo() > getCohete().getVelocidadActual())) {

			if (cohete.isAcelerando()) {
				System.out.println("Deteniendo la frenada...");
				break;
			}

			potenciaActual--;

			System.out.println("Potencia del propulsor nº : " + idPropulsor + " del Cohete -> " + cohete.getIdCodigo()
					+ ": " + potenciaActual + " kW / " + potenciaMaxima + " kW");

			System.err.println("El cohete " + getCohete().getIdCodigo() + "vuela a una velocidad de: " + cohete.getVelocidadActual() + " m/s");

			if (potenciaActual == 0) {
				System.err.println("\nPROPULSOR " + idPropulsor + " DEL COHETE " + cohete.getIdCodigo()
						+ " ESTÁ DETENIDO (POTENCIA ACTUAL -> 0)\n");
				break;
				}

			System.out.println("Potencia del propulsor nº: " + idPropulsor + " del Cohete -> " + cohete.getIdCodigo()
					+ ": " + potenciaActual + " kW / " + potenciaMaxima + " kW");
			System.err.println("Potencia actual del cohete " + getCohete().getIdCodigo() + ": " + cohete.getPotenciaActualCohete());
			System.err.println("\nPotencia total actual del cohete " + getCohete().getIdCodigo()+ " : " + cohete.getTotalCurrentPower());

			try {
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
				return;
			}
		}
	}

	@Override
	public void run() {
		if (cohete.isAcelerando())
			speedUp();
		else if (!cohete.isAcelerando())
			speedDown();
		System.err.println("El cohete "+ getCohete().getIdCodigo() + "vuela a una velocidad de: " + cohete.getVelocidadActual() + " m/s");
		if ((getPotenciaObjetivo() > getCohete().getTotalCurrentPower())
				&& (getPotenciaObjetivo() > getCohete().getMaximaPotenciaTotal())
				&& (getCohete().getTotalCurrentPower() == getCohete().getMaximaPotenciaTotal())
				&& (getCohete().isAcelerando())) {
			System.err.println("Los propulsores del cohete " +getCohete().getIdCodigo() + " están al máximo y no se ha podido llegar a la velocidad objetivo");
		}
	}

	public int getVelocidadInicial() {
		return velocidadInicial;
	}

	public void setVelocidadInicial(int velocidadInicial) {
		this.velocidadInicial = velocidadInicial;
	}

	public double getPotenciaObjetivo() {
		return potenciaObjetivo;
	}

	public void setPotenciaObjetivo(double potenciaObjetivo) {
		this.potenciaObjetivo = potenciaObjetivo;
	}

	public int getIdPropulsor() {
		return idPropulsor;
	}

	public void setIdPropulsor(int idPropulsor) {
		this.idPropulsor = idPropulsor;
	}

	public Cohete getCohete() {
		return cohete;
	}

	public void setCohete(Cohete cohete) {
		this.cohete = cohete;
	}

	public int getPotenciaMaxima() {
		return potenciaMaxima;
	}

	public void setPotenciaMaxima(int potenciaMaxima) {
		this.potenciaMaxima = potenciaMaxima;
	}

	public int getPotenciaActual() {
		return potenciaActual;
	}

	public void setPotenciaActual(int potenciaActual) {
		this.potenciaActual = potenciaActual;
	}

}
