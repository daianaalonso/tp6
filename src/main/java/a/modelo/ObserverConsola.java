package a.modelo;

public class ObserverConsola implements Observer {

    @Override
    public void actualizar(String temperatura) {
        try {
            int temp = Integer.parseInt(temperatura.replaceAll("[^\\d]", ""));
            if (temp < 12) {
                System.out.println("Hace frio, se encenderá la caldera");
            }
            if (temp > 17) {
                System.out.println("Hace calor, se encenderá el aire acondicionado");
            }
        } catch (NumberFormatException e) {
            throw new RuntimeException("Formato de temperatura invalido.", e);
        }
    }
}
