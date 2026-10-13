public class LanchaTuristica extends Vehiculo {
    private String tipoMotor;

    public LanchaTuristica(String patente, String marca, int capacidadPasajeros, int anioFabricacion, String tipoMotor) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        setTipoMotor(tipoMotor);
    }

    public static void validarMotor(String motor) {
        if (motor == null) {
            throw new IllegalArgumentException("El Tipo de motor no puede ser nulo");
        }
        String m = motor.trim().toUpperCase();
        if (!m.equals("NAFTA") && !m.equals("DIESEL") && !m.equals("ELECTRICO")) {
            throw new IllegalArgumentException("El Tipo de motor tiene que ser Nafta, Diesel o Electrico");
        }
    }

    public String getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(String tipoMotor) {
        validarMotor(tipoMotor);
        this.tipoMotor = tipoMotor.trim().toUpperCase();
    }

    @Override
    public void realizarServicio() {
        System.out.println("La Lancha " + getPatente() + " Realiza una excursion por el rio con motor "
                + tipoMotor.toLowerCase());
    }

    @Override
    public String toString() {
        return "[Lancha] " + super.toString() + " | Motor: " + tipoMotor;
    }
}
