import java.util.*;
public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int eleccion = 0;
		
		
		//Creo los dos cohetes, sin propulsores:
		Cohete cohete1 = new Cohete("32WESSDS");
		Cohete cohete2 = new Cohete("LDSFJA32");

		//Creo los propulsores del cohete 1:
		Propulsor p1_c1 = new Propulsor(1,10,cohete1); //propulsor 1 del cohete 1 tiene 10kW de potencia maxima
		Propulsor p2_c1 = new Propulsor(2,30, cohete1);//tiene 30kW de potencia maxima
		Propulsor p3_c1 = new Propulsor(3, 80, cohete1);//tiene 80kW de potencia maxima
		
		//Propulsores del cohete 2:
		Propulsor p1_c2 = new Propulsor(4, 40, cohete2);
		Propulsor p2_c2= new Propulsor(5, 40, cohete2);
		Propulsor p3_c2 = new Propulsor(6, 50, cohete2);
		Propulsor p4_c2 = new Propulsor(7, 50, cohete2);
		Propulsor p5_c2 = new Propulsor(8, 30, cohete2);
		Propulsor p6_c2 = new Propulsor(9, 10, cohete2);
		
		//Los junto en su correspondiente lista 
		List<Propulsor> propulsoresCohete1 = new ArrayList<>();
		propulsoresCohete1.add(p1_c1);
		propulsoresCohete1.add(p2_c1);
		propulsoresCohete1.add(p3_c1);
		
		List<Propulsor> propulsoresCohete2 = new ArrayList<>();
		propulsoresCohete2.add(p1_c2);
		propulsoresCohete2.add(p2_c2);
		propulsoresCohete2.add(p3_c2);
		propulsoresCohete2.add(p4_c2);
		propulsoresCohete2.add(p5_c2);
		propulsoresCohete2.add(p6_c2);
		
		//Equipo los propulsores a sus cohetes:
		cohete1.setPropulsores(propulsoresCohete1);
		cohete2.setPropulsores(propulsoresCohete2);
		
		//Suponemos que el cohete se encuentra a una velocidad de 800 m/s: 
		System.out.println("El cohete " + cohete1.getIdCodigo() + " con " + cohete1.getNumeroPropulsores() 
		+ " propulsores, se mueve a una velocidad de: " + cohete1.getVelocidadActual());
		
		System.out.println("El cohete " + cohete2.getIdCodigo() + " con " + cohete2.getNumeroPropulsores() 
		+ " se mueve a una velocidad de: " + cohete2.getVelocidadActual());
		
		
		System.out.println("\nHasta qué velocidad quieres ir? ");
		double velocidadObjetivo = sc.nextDouble();
		
		cohete1.getPropulsores().forEach((Propulsor p) -> p.setPotenciaObjetivo(velocidadObjetivo));
		cohete2.getPropulsores().forEach((Propulsor p) -> p.setPotenciaObjetivo(velocidadObjetivo));
		
		while(eleccion!=3) {
			System.out.println("Qué quieres hacer con el cohete: \n1. Acelerar\n2. Frenar\n3. Salir");
			eleccion = sc.nextInt();
			sc.nextLine();
			if(eleccion==1) {
				cohete1.acelerar();
				cohete2.acelerar();
			}else if(eleccion==2){
				cohete1.frenar();
				cohete2.frenar();
			}else {
				try {
					cohete1.wait();
					cohete2.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Saliendo");
				System.exit(0);
			}
		}
		sc.close();
	}
	
}
