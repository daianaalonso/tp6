package c;

import c.modelo.EstacionDeServicio;
import c.modelo.Observer;
import c.modelo.ObserverEmail;
import c.persistencia.VentasEnDisco;
import c.servicios.Email;
import c.ui.Principal;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainDisco {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                List<Observer> observers = new ArrayList<>();
                observers.add(new ObserverEmail(new Email()));
                EstacionDeServicio estacionDeServicio = new EstacionDeServicio(new VentasEnDisco("ventas.txt"), observers);
                new Principal(estacionDeServicio);
            }
        });

    }
}
