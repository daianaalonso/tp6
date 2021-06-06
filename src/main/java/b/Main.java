package b;

import b.modelo.*;
import b.servicios.WeatherChannelService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Observer> observers = new ArrayList<>();
        observers.add(new ObserverDisco("temperaturaB.txt"));
        observers.add(new ObserverConsola());
        MedidorInterfaz medidorInterfaz = new MedidorDecorador(observers, new Medidor(new WeatherChannelService()));
        String temperatura = medidorInterfaz.leerTemperatura();
        System.out.println(temperatura);
    }
}
