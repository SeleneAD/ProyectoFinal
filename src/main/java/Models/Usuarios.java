package Models;

import java.util.Scanner;

import com.selene.Query;

public class Usuarios {
    
    private int id;
    private String nombre;
    private String direccion;
    private String correo;
    private Long roles_id;

    public Usuarios(){
    }
    

    public Usuarios(int id,String nombre, String direccion, String correo, Long roles_id){
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.correo = correo;
        this.roles_id = roles_id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(){
        this.nombre = nombre;
    }

    public String getDireccion(){
        return direccion;
    }

    public void setDireccion(){
        this.direccion = direccion;
    }
    
    public String getCorreo(){
        return correo;
    }

    public void setCorreo(){
        this.correo = correo;
    }

    public Long getRoles_id(){
        return roles_id;
    }

    public void setRoles_id(){
        this.roles_id = roles_id;
    }
    public int getId(){
        return this.id;
    }

    public void agregarUsuario(int rol){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese nombre de usuario");
        nombre = scanner.nextLine();
        System.out.println("Ingrese direccion");
        direccion = scanner.nextLine();
        System.out.println("Ingrese correo");
        correo = scanner.nextLine();

        Query query = new Query();
        query.createUsuario(nombre, direccion, correo, rol);
    }

}
