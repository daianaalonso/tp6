package c.ui;

import c.modelo.Combustible;
import c.modelo.Comun;
import c.modelo.EstacionDeServicio;
import c.modelo.Super;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

public class CargaDeCombustible extends JFrame {

    private final JPanel contentPane;
    private final JTextField litros;
    private final JTextField email;
    private final JComboBox<Combustible> nafta;
    private final JLabel litrosCargados;
    private final JLabel tipoDeNafta;
    private final JLabel emailCliente;
    private final JButton totalAPagar;
    private final JButton confirmar;
    private final JButton cancelar;
    private final EstacionDeServicio estacionDeServicio;

    public CargaDeCombustible(EstacionDeServicio estacionDeServicio) {
        this.estacionDeServicio = estacionDeServicio;
        setTitle("Carga de combustible");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 445, 240);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setResizable(false);
        setLocationRelativeTo(null);

        litros = new JTextField();
        litros.setBounds(168, 15, 170, 30);
        contentPane.add(litros);
        litros.setColumns(10);

        email = new JTextField();
        email.setBounds(168, 55, 170, 30);
        contentPane.add(email);
        email.setColumns(10);

        litrosCargados = new JLabel("Litros cargados:");
        litrosCargados.setBounds(35, 15, 131, 30);
        contentPane.add(litrosCargados);

        emailCliente = new JLabel("Email:");
        emailCliente.setBounds(35, 55, 131, 30);
        contentPane.add(emailCliente);

        tipoDeNafta = new JLabel("Tipo de nafta:");
        tipoDeNafta.setBounds(35, 95, 90, 30);
        contentPane.add(tipoDeNafta);

        nafta = new JComboBox<>();
        nafta.setBounds(168, 95, 170, 30);
        contentPane.add(nafta);
        nafta.addItem(new Comun());
        nafta.addItem(new Super());

        totalAPagar = new JButton("Total a pagar");
        totalAPagar.addActionListener(e -> {
            try {
                double monto = estacionDeServicio.obtenerMonto(LocalDateTime.now(), litros.getText(), (Combustible) nafta.getSelectedItem(), email.getText());
                JOptionPane.showMessageDialog(null, "Monto total a pagar: $" + monto, "AVISO",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (RuntimeException r) {
                JOptionPane.showMessageDialog(null, r.getMessage());
            }
        });
        totalAPagar.setBounds(123, 164, 137, 23);
        contentPane.add(totalAPagar);

        confirmar = new JButton("Confirmar pago");
        confirmar.addActionListener(e -> {
            try {
                estacionDeServicio.registrarVenta(LocalDateTime.now(), litros.getText(), (Combustible) nafta.getSelectedItem(), email.getText());
                JOptionPane.showMessageDialog(null, "Se registró el pago correctamente.", "AVISO",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (RuntimeException r) {
                JOptionPane.showMessageDialog(null, r.getMessage());
            }
        });
        confirmar.setBounds(270, 164, 137, 23);
        contentPane.add(confirmar);

        cancelar = new JButton("Cancelar");
        cancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Principal ventana = new Principal(estacionDeServicio);
                ventana.setVisible(true);
                dispose();
            }
        });
        cancelar.setBounds(10, 164, 103, 23);
        contentPane.add(cancelar);
    }
}
