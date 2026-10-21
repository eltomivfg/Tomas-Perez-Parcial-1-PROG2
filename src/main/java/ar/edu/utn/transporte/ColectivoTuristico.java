package ar.edu.utn.transporte;

public class ColectivoTuristico extends Vehiculo {
    private int cantidadPisos;

    public ColectivoTuristico(String patente, String marca, int capacidadPasajeros, int anioFabricacion, int cantidadPisos) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        setCantidadPisos(cantidadPisos);
    }

    public static void validarPisos(int pisos) {
        if (pisos != 1 && pisos != 2) {
            throw new IllegalArgumentException("La cantidad de pisos tiene que ser 1 o 2");
        }
    }

    public int getCantidadPisos() {
        return cantidadPisos;
    }

    public void setCantidadPisos(int cantidadPisos) {
        validarPisos(cantidadPisos);
        this.cantidadPisos = cantidadPisos;
    }

    @Override
    public void realizarServicio() {
        System.out.println("El colectivo " + getPatente() + " Realiza un recorrido turistico por la ciudad con "
                + getCapacidadPasajeros() + " Pasajeros");
    }

    @Override
    public String toString() {
        return "[Colectivo] " + super.toString() + " | Pisos: " + cantidadPisos;
    }
}
