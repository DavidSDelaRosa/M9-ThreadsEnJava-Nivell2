import java.util.List;

public class Cohete {

	private String idCodigo;
	private List<Propulsor> propulsores;
	private int numeroPropulsores; 
	private boolean acelerando;
	
	public Cohete(String idCodigo) {
		this.idCodigo = idCodigo;
	}
	
	public Cohete(String idCodigo, List<Propulsor> propulsores) throws Exception {
		this.idCodigo = idCodigo;
		this.propulsores = propulsores;
		numeroPropulsores = propulsores.size();
		acelerando = false;
	}
	
	public List<Propulsor> getPropulsores() {
		return propulsores;
	}

	public void setPropulsores(List<Propulsor> propulsores) {
		this.propulsores = propulsores;
	}

	public String getIdCodigo() {
		return idCodigo;
	}
	public void setIdCodigo(String idCodigo) {
		this.idCodigo= idCodigo;
	}
	public int getNumeroPropulsores() {
		return propulsores.size();
	}
	public void setNumeroPropulsores(int numeroPropulsores) {
		this.numeroPropulsores = numeroPropulsores;
	}
	
	public boolean isAcelerando() {
		return acelerando;
	}

	public void setAcelerando(boolean acelerando) {
		this.acelerando = acelerando;
	}
	
	public String getPotenciaActualCohete () {
		
		String potenciaIndividual = "";
		
		for (Propulsor p: propulsores) {
		
			potenciaIndividual += "[" + p.getPotenciaActual() + "/" + p.getPotenciaMaxima() + "]" ;
		}		
		return potenciaIndividual;
		
	}

	public void acelerar() {
		acelerando = true;
		for(Propulsor p: propulsores) {
			Thread acelerador = new Thread(p);
			acelerador.start();
			/*try {
				acelerador.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
	
	}
	
	public void frenar() {
		acelerando = false;
		for(Propulsor p: propulsores) {
			Thread frenada = new Thread(p);
			frenada.start();
		}
	}
	
	public int getMaximaPotenciaTotal() {
		int maximaPotencia = 0;
		for(Propulsor p: propulsores) {
			maximaPotencia+=p.getPotenciaMaxima();
		}
		return maximaPotencia;
	}
	
	public double getVelocidadActual () {
		
		double velocidadActual = 0;
		double potenciaTotalDePropulsores = 0;
		
		for (Propulsor p: propulsores) {
			
			potenciaTotalDePropulsores += p.getPotenciaActual();			
		}			
		velocidadActual = 800 + 100*Math.sqrt(potenciaTotalDePropulsores);
		
		return velocidadActual;		
	}
	
	public boolean speedAchieved() {
		
		boolean achieved;
		int contador = 0;
		for(Propulsor p: propulsores) {
			if(p.getPotenciaActual()==p.getPotenciaObjetivo()) {
				contador ++;
				System.out.println(p.getIdPropulsor() + " ha llegado a la velocidad objetivo");
				System.out.println("Contador: " + contador);
			}
		}
		achieved = contador==propulsores.size()? true: false;
		return achieved;
	}
	
	public int getTotalCurrentPower () {
		
		int totalPower = 0;
		
			for (Propulsor p: propulsores) {
			
				totalPower += p.getPotenciaActual();
			}		
			return totalPower;
	}
	
}
