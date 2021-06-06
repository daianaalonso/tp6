package c.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class EstacionDeServicio extends Observable {
    private final Ventas ventas;

    public EstacionDeServicio(Ventas ventas, List<Observer> observadores) {
        this.ventas = ventas;
        for (Observer observer : observadores) {
            this.agregarObservador(observer);
        }
    }

    public double obtenerMonto(LocalDateTime fecha, String litrosCargados, Combustible combustible, String email) {
        return new Venta(fecha, litrosCargados, combustible, email).monto();
    }

    public void registrarVenta(LocalDateTime fecha, String litrosCargados, Combustible combustible, String email) {
        VentaPagada ventaPagada = new Venta(fecha, litrosCargados, combustible, email).pagar();
        this.ventas.guardarVentas(ventaPagada);
        this.notificar(ventaPagada);
    }

    public List<VentaPagada> obtenerVentasEntreFechas(String inicio, String fin) {
        if (inicio == null || inicio.isEmpty())
            throw new RuntimeException("Debe ingresar una fecha de inicio.");
        if (fin == null || fin.isEmpty())
            throw new RuntimeException("Debe ingresar una fecha de fin.");
        try {
            LocalDate fechaInicio = LocalDate.parse(inicio, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate fechaFin = LocalDate.parse(fin, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            if (fechaInicio.isAfter(fechaFin))
                throw new RuntimeException("La fecha de inicio debe ser menor a la fecha de fin.");
            return ventas.obtenerVentasPorFechas(fechaInicio, fechaFin);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("Debe ingresar la fecha con el formato correspondiente.", e);
        }
    }

}
