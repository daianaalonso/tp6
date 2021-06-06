package b.modelo;

import java.util.List;

public class MedidorDecorador extends Observable implements MedidorInterfaz {
    private MedidorInterfaz medidorInterfaz;

    public MedidorDecorador(List<Observer> observadores, MedidorInterfaz medidorInterfaz) {
        super(observadores);
        this.medidorInterfaz = medidorInterfaz;
    }

    @Override
    public String leerTemperatura() {
        String temperatura = medidorInterfaz.leerTemperatura();
        notificar(temperatura);
        return temperatura;
    }
}
