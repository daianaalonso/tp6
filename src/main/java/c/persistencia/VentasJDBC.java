package c.persistencia;

import c.modelo.VentaPagada;
import c.modelo.Ventas;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VentasJDBC implements Ventas {

    @Override
    public void guardarVentas(VentaPagada venta) {
        Connection conexion;
        try {
            conexion = obtenerConexion();
            PreparedStatement st = conexion
                    .prepareStatement("Insert into ventas (fecha, litros_cargados, monto, email) values(?, ?, ?, ?)");
            st.setTimestamp(1, Timestamp.valueOf(venta.fecha()));
            st.setDouble(2, venta.litrosCargados());
            st.setDouble(3, venta.monto());
            st.setString(4, venta.email());
            st.executeUpdate();
            st.close();
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo guardar la venta", e);
        }
    }

    @Override
    public List<VentaPagada> obtenerVentasPorFechas(LocalDate fechaInicio, LocalDate fechaFin) {
        List<VentaPagada> ventas = new ArrayList<>();
        Connection conexion;
        try {
            conexion = obtenerConexion();
            PreparedStatement st = conexion.prepareStatement("SELECT * FROM ventas ");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                if (rs.getTimestamp("fecha").toLocalDateTime().toLocalDate().isAfter(fechaInicio)
                        || rs.getTimestamp("fecha").toLocalDateTime().toLocalDate().equals(fechaInicio)
                        && rs.getTimestamp("fecha").toLocalDateTime().toLocalDate().isBefore(fechaFin)
                        || rs.getTimestamp("fecha").toLocalDateTime().toLocalDate().equals(fechaFin))
                    ventas.add(new VentaPagada(rs.getTimestamp("fecha").toLocalDateTime(), rs.getDouble("litros_cargados"),
                            rs.getDouble("monto"), rs.getString("email")));
            }
            rs.close();
            st.close();
            conexion.close();
        } catch (SQLException e) {
            throw new RuntimeException("No se pudo obtener la lista de ventas", e);
        }
        return ventas;
    }

    private Connection obtenerConexion() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/bd_alonso?useSSL=false";
        String user = "root";
        String password = "";
        return DriverManager.getConnection(url, user, password);
    }

}