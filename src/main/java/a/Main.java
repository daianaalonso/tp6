package a;

import a.modelo.Medidor;
import a.modelo.Observer;
import a.modelo.ObserverConsola;
import a.modelo.ObserverDisco;
import a.servicios.WeatherChannelService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Observer> observers = new ArrayList<>();
        observers.add(new ObserverDisco("temperaturaA.txt"));
        observers.add(new ObserverConsola());
        Medidor medidor = new Medidor(new WeatherChannelService(), observers);
        String temperatura = medidor.leerTemperatura();
        System.out.println(temperatura);
    }
}
