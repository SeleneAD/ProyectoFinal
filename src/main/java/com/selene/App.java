package com.selene;

import java.util.Random;
import java.util.Scanner;

import Models.Productos;
import Models.Tarjetas;
import Models.Usuarios;

public class App 
{
    public static void main( String[] args )
    {
        ConexionMySQL conexionMySQL = new ConexionMySQL();
        conexionMySQL.conectarMySQL();
        Query query = new Query();
        boolean bucle = true;
        Usuarios user = null;

        Scanner scanner = new Scanner(System.in);

        while (bucle == true) {
            System.out.println("1.Registro para ingresar al sistema de compra");
            System.out.println("2.Ingresar productos o comprar productos (para ingresar productos debe registrarse como vendedor)");
            System.out.println("3.Salir del menu");
            int opcionElegida = scanner.nextInt();

            if (opcionElegida == 1) {
                System.out.println("1.Ingresar como vendedor");
                System.out.println("2.Ingresar como comprador");
                opcionElegida = scanner.nextInt();
                Usuarios register = new Usuarios();

                if (opcionElegida == 1) {
                    register.agregarUsuario(1);
                }
                if (opcionElegida == 2) {
                    register.agregarUsuario(2);
                }
            }
            else if (opcionElegida == 2) {
                Productos registerProduct = new Productos();
                user = query.searchRol();

                if (user != null && user.getRoles_id() == 1) {
                    registerProduct.agregarProducto();
                }
                else if(user != null && user.getRoles_id() == 2){
                    System.out.println("1.Ver catalogo de la tienda");
                    System.out.println("2.Ver historial de compra");
                    System.out.println("3.Registro de tarjeta bancaria");
                    opcionElegida = scanner.nextInt();

                    if (opcionElegida == 1) {

                        int productoId = 0;
                        Random r = new Random();
                        String cadena = "";
                        int i = 0;

                        while (i < 4) {
                            cadena += r.nextInt(9);
                            i++;
                        }

                        while (bucle) {
                            query.viewProductos();

                            System.out.println("Ingrese numero de producto que desea comprar.");
                            System.out.println("Para finalizar ingrese 0");
                            productoId = scanner.nextInt();
                            if (productoId == 0) {
                                break;
                            }
                            query.carrito(user.getId(), productoId, cadena);
                        }


                        System.out.println("1.Realizar pago");
                        opcionElegida = scanner.nextInt();

                        if (opcionElegida == 1) {
                            query.viewPedidos(user.getId());
                            System.out.println("Elige codigo de compra a pagar:");
                            String respuesta = scanner.next();
                            query.registerPedidos(productoId);
                            query.pago(respuesta, user.getId());

                            query.montoTarjeta(respuesta);
                        }
                    }
                    else if (opcionElegida == 2){
                        query.viewPagos(user.getId());
                        
                    }
                    else if(opcionElegida == 3){
                        Tarjetas tarjeta = new Tarjetas();
                        tarjeta.ingresoTarjeta(user.getId());
                    }
                }
            }
            else if (opcionElegida == 3) {
                break;
            }
            else {
                System.out.println("Opcion ingresada no es valida, ingrese la opcion nuevamente");
            }
        }
    }
}
