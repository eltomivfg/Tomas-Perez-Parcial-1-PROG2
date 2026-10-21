package ar.edu.utn.transporte;

public class VanEjecutiva extends Vehiculo {
    private boolean aireAcondicionado;

    public VanEjecutiva(String patente, String marca, int capacidadPasajeros, int anioFabricacion, boolean aireAcondicionado) {
        super(patente, marca, capacidadPasajeros, anioFabricacion);
        this.aireAcondicionado = aireAcondicionado;
    }

    public boolean isAireAcondicionado() {
        return aireAcondicionado;
    }

    public void setAireAcondicionado(boolean aireAcondicionado) {
        this.aireAcondicionado = aireAcondicionado;
    }

    @Override
    public void realizarServicio() {
        String aire = aireAcondicionado ? "Con aire acondicionado" : "Sin aire acondicionado";
        System.out.println("La Van " + getPatente() + " Realiza un traslado ejecutivo " + aire);
    }

    @Override
    public String toString() {
        return "[Van] " + super.toString() + " | aire: " + (aireAcondicionado ? "si" : "no");
    }
}
