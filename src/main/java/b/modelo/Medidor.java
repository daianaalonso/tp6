package b.modelo;

public class Medidor implements MedidorInterfaz {
    private String temperatura;
    private final ClimaOnline clima;

    public Medidor(ClimaOnline clima) {
        this.clima = clima;
    }

    @Override
    public String leerTemperatura() {
        //leo la temperatura del servicio web
        this.temperatura = this.clima.temperatura();
        return this.temperatura;
    }

}
