package com.selene;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import Models.Usuarios;

public class Query {
    
    ConexionMySQL conexionMySQL = new ConexionMySQL();
    Connection conn = conexionMySQL.conectarMySQL();

    public void createUsuario(String nombre, String direccion, String correo, int nombre_rol){

        try {
            String query = "INSERT INTO usuarios (Nombre,Direccion,Correo,roles_id) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setString(2, direccion);
            ps.setString(3, correo);
            ps.setInt(4, nombre_rol);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void createProducto(String nombre, double precio, String descripcion, int stock){

        try {
            String query = "INSERT INTO productos (nombre,precio,descripcion,stock) VALUES(?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, nombre);
            ps.setDouble(2, precio);
            ps.setString(3, descripcion);
            ps.setInt(4, stock);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Usuarios searchRol(){

        try {
            Statement statement = conn.createStatement();
            Scanner scan = new Scanner(System.in); 
            System.out.println("Favor, ingrese su correo para validar su identidad.");
            String correo = scan.nextLine();

            String query = "SELECT * FROM usuarios WHERE Correo = '"+ correo +"'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {

                System.out.println("El usuario " +resultSet.getString("nombre") + " ha ingresado con exito al sistema");
                return new Usuarios(resultSet.getInt("id"),
                                    resultSet.getString("nombre"),
                                    resultSet.getString("direccion"),
                                    resultSet.getString("correo"),
                                    Long.valueOf(resultSet.getInt("roles_id")));
            }
            if (!resultSet.next()) {
                System.out.println("Los datos ingresados no son validos");
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return null;
    }

    public void viewProductos(){
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM productos");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("id")+ " | " + resultSet.getString("nombre")+ " | " + resultSet.getDouble("precio")+ " | " + resultSet.getString("descripcion")+ " | " + resultSet.getInt("stock"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void carrito(int usuarioId, int productoId, String codigoCompra){

        try {
            String query = "INSERT INTO pedidos (usuario_id, producto_id, codigo_compra) VALUES(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, usuarioId);
            ps.setInt(2, productoId);
            ps.setString(3, codigoCompra);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void pago(String codigoCompra, int usuario_id){
        
        try {
            String query = "INSERT INTO pagos (precio_total,codigo_compra,usuario_id, estado) VALUES (?,?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, precioTotal(codigoCompra));
            ps.setString(2, codigoCompra);
            ps.setInt(3, usuario_id);
            ps.setString(4, "PENDIENTE");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public double precioTotal(String codigoCompra){
        double precioFinal = 0;

        try {
            Statement statement = conn.createStatement();
            String query = "SELECT SUM(pr.precio) as suma FROM pedidos pe INNER JOIN productos pr ON pe.producto_id = pr.id WHERE pe.codigo_compra = '" + codigoCompra + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                precioFinal = resultSet.getInt("suma");
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        return precioFinal;
    }

    public void registerPedidos(int pedidoId){

        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM pedidos WHERE id = " + pedidoId;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                
                System.out.println(resultSet.getInt("id"));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void viewPedidos(int usuarioId){
        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM multisales.pedidos where usuario_id = " + usuarioId;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("El codigo de compra es : " + resultSet.getString("codigo_compra"));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void viewPagos(int usuarioId){

        try {
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM pagos WHERE usuario_id = " + usuarioId;
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id")+ " | " +resultSet.getDouble("precio_total")+ " | " +resultSet.getString("codigo_compra")+ " | " +resultSet.getInt("usuario_id"));
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void registerTarjeta(int numeroTarjeta, double disnero_disponible, int usuarioId){

        try {
            String query = "INSERT INTO tarjetas (numero_tarjeta,dinero_disponible,usuario_id) VALUES (?,?,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, numeroTarjeta);
            ps.setInt(2, (int) disnero_disponible);
            ps.setInt(3, usuarioId);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void montoTarjeta(String codigoCompra){

        double montoCobro = 0;
        int tarjetaId = 0;
        double montoTarjeta = 0;

        try {
            Statement statement = conn.createStatement();
            String query = "SELECT tarjetas.id, pagos.precio_total, tarjetas.dinero_disponible FROM pagos INNER JOIN tarjetas ON pagos.usuario_id = tarjetas.usuario_id WHERE pagos.codigo_compra = '" + codigoCompra + "'";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                montoCobro += resultSet.getDouble("precio_total"); 
                tarjetaId = resultSet.getInt("id");
                montoTarjeta = resultSet.getDouble("dinero_disponible"); 
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        
        cobroTareta(montoCobro, tarjetaId, montoTarjeta);
    }

    public void cobroTareta(double montoCobro, int idTarejeta, double montoTarjeta){

        try {
            String query = "UPDATE tarjetas SET dinero_disponible = ?, estado = ? WHERE id = " + idTarejeta;
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, montoTarjeta - montoCobro);
            ps.setString(2, "PAGADO");
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stock(int stock, int productoId){

        try {
            String query = "UPDATE productos SET stock = ? WHERE id = " + productoId;
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, stock - 1);
            ps.executeQuery();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

