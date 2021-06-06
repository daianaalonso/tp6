package a.modelo;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {
    private final List<Observer> observadores;

    public Observable() {
        this.observadores = new ArrayList<>();
    }

    public void agregarObservador(Observer observer) {
        this.observadores.add(observer);
    }

    protected void notificar(String temperatura) {
        for (Observer observer : this.observadores) {
            observer.actualizar(temperatura);
        }
    }
}
