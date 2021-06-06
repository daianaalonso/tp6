package c.modelo;

public class ObserverEmail implements Observer {
    private final ServicioEmail servicioEmail;

    public ObserverEmail(ServicioEmail servicioEmail) {
        this.servicioEmail = servicioEmail;
    }

    @Override
    public void actualizar(VentaPagada ventaPagada) {
        this.servicioEmail.enviarEmail(ventaPagada);
    }
}
