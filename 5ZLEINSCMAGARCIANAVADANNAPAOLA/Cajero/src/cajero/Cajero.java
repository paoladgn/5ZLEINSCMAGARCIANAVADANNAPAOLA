/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cajero;

/**
 *
 * @author paola
 */
import java.util.Scanner;
public class Cajero {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    Cuenta cuenta = new Cuenta();
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\nBienvenido al Cajero LEOSHAM");
            System.out.println("1. Depositar");
            System.out.println("2. Transferir");  
            System.out.println("3. Consultar saldo"); 
            System.out.println("4. Retirar");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");
            
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el monto a depositar: ");
                    double montoDeposito = scanner.nextDouble();
                    new Depositar(cuenta, montoDeposito).start();
                    break;
                case 2:
                    System.out.print("Ingrese el monto a transferir: ");
                    double montoTransferencia = scanner.nextDouble();
                    new Transferir(cuenta, montoTransferencia).start();
                    break;
                case 3:
                    new ConsultarSaldo(cuenta).start();
                    break;  
                case 4:
                    System.out.print("Ingrese el monto a retirar: ");
                    double montoRetiro = scanner.nextDouble();
                    new Retirar(cuenta, montoRetiro).start();
                    break;
                case 5:
                    continuar = false;
                    System.out.println("Gracias por usar el Cajero  LEOSHAM. Hasta luego!");
                    break;
                default:
                    System.out.println("Opcion no valida. Por favor intente de nuevo.");
                    break;
            }
        }
        scanner.close();
    }
}

