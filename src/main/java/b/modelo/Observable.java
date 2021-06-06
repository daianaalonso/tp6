package b.modelo;

import java.util.List;

public abstract class Observable {
    private final List<Observer> observadores;

    public Observable(List<Observer> observadores) {
        this.observadores = observadores;
    }

    protected void notificar(String temperatura) {
        for (Observer observer : this.observadores) {
            observer.actualizar(temperatura);
        }
    }
}
