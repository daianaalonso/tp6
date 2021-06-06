package a.modelo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ObserverDisco implements Observer {
    private final String path;

    public ObserverDisco(String path) {
        this.path = path;
    }

    @Override
    public void actualizar(String temperatura) {
        try {
            File archivo = new File(this.path);
            Writer writer = new FileWriter(archivo, true);
            writer.write(temperatura + ", " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "\n");
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException("No pudo guardarse la temperatura.", e);
        }
    }
}
