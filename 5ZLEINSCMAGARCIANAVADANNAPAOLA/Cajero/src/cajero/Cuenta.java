/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cajero;

/**
 *
 * @author paola
 */
public class Cuenta {
    private double saldo = 1000.0; // Saldo inicial

    public synchronized void depositar(double monto) {
        saldo += monto;
        System.out.println("Deposito realizado con exito. Saldo actual: $" + saldo);
    }

    public synchronized void transferir(double monto) {
        if (monto > saldo) {
            System.out.println("No se puede realizar la transferencia. Saldo insuficiente.");
        } else {
            saldo -= monto;
            System.out.println("Transferencia realizada con exito. Saldo actual: $" + saldo);
        }
    }

    public synchronized void consultarSaldo() {
        System.out.println("Saldo actual: $" + saldo);
    }

    public synchronized void retirar(double monto) {
        if (monto > saldo) {
            System.out.println("No se puede realizar el retiro. Saldo insuficiente.");
        } else {
            saldo -= monto;
            System.out.println("Retiro realizado con exito. Saldo actual: $" + saldo);
        }
    }
}

