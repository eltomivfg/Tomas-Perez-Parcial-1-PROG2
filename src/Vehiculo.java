import java.time.Year;

public abstract class Vehiculo {
    private String patente;
    private String marca;
    private int capacidadPasajeros;
    private int anioFabricacion;

    public Vehiculo(String patente, String marca, int capacidadPasajeros, int anioFabricacion) {
        setPatente(patente);
        setMarca(marca);
        setCapacidadPasajeros(capacidadPasajeros);
        setAnioFabricacion(anioFabricacion);
    }

    public static void validarPatente(String patente) {
        if (patente == null || patente.trim().isEmpty()) {
            throw new IllegalArgumentException("La patente no puede estar vacia");
        }
    }

    public static void validarMarca(String marca) {
        if (marca == null || marca.trim().isEmpty()) {
            throw new IllegalArgumentException("La marca no puede estar vacia");
        }
    }

    public static void validarCapacidad(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException("La capacidad tiene que ser mayor a cero");
        }
    }

    public static void validarAnio(int anio) {
        int actual = Year.now().getValue();
        if (anio < 1990 || anio > actual) {
            throw new IllegalArgumentException("El anio tiene que estar entre 1990 y " + actual);
        }
    }

    public abstract void realizarServicio();

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        validarPatente(patente);
        this.patente = patente.trim().toUpperCase();
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        validarMarca(marca);
        this.marca = marca.trim();
    }

    public int getCapacidadPasajeros() {
        return capacidadPasajeros;
    }

    public void setCapacidadPasajeros(int capacidadPasajeros) {
        validarCapacidad(capacidadPasajeros);
        this.capacidadPasajeros = capacidadPasajeros;
    }

    public int getAnioFabricacion() {
        return anioFabricacion;
    }

    public void setAnioFabricacion(int anioFabricacion) {
        validarAnio(anioFabricacion);
        this.anioFabricacion = anioFabricacion;
    }

    @Override
    public String toString() {
        return "Patente: " + patente + " | Marca: " + marca
                + " | Capacidad: " + capacidadPasajeros
                + " | Anio: " + anioFabricacion;
    }
}
