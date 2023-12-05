package Models;

import java.util.Scanner;

import com.selene.Query;

public class Tarjetas {
    
    private int numero_tarjeta;
    private double dinero_disponible;
    private int usuario_id;

    public Tarjetas(int numero_tarjeta, double dinero_disponible, int usuario_id){
        this.numero_tarjeta = numero_tarjeta;
        this.dinero_disponible = dinero_disponible;
        this.usuario_id = usuario_id;
    }

    public Tarjetas(){

    }

    public void ingresoTarjeta(int userId){

        this.usuario_id = userId;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese numero de tarjeta");
        this.numero_tarjeta = scanner.nextInt();
        System.out.println("Ingrese monto a depositar");
        this.dinero_disponible = scanner.nextDouble();

        Query query = new Query();
        query.registerTarjeta(this.numero_tarjeta, this.dinero_disponible, userId);
    }
}
