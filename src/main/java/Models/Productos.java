package Models;

import java.util.Scanner;

import com.selene.Query;

public class Productos {
    
    private String nombre;
    private double precio;
    private String descripcion;
    private int stock;

    public void agregarProducto(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese nombre de producto que desea subir a la tienda");
        nombre = scanner.nextLine();
        System.out.println("Ingrese precio del producto");
        precio = scanner.nextDouble();
        System.out.println("Ingrese descripcion del producto");
        descripcion = scanner.next();
        System.out.println("Ingrese stock del producto subido");
        stock = scanner.nextInt();

        Query query = new Query();
        query.createProducto(this.nombre, this.precio, this.descripcion, this.stock);
    }

    

}
