import java.util.ArrayList;

public class Empresa {
    private ArrayList<Vehiculo> vehiculos = new ArrayList<>();

    public void agregar(Vehiculo v) {
        if (existePatente(v.getPatente())) {
            throw new IllegalArgumentException("Ya existe un vehiculo con la Patente " + v.getPatente());
        }
        vehiculos.add(v);
    }

    public boolean existePatente(String patente) {
        return buscarPorPatente(patente) != null;
    }

    public Vehiculo buscarPorPatente(String patente) {
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getPatente().equalsIgnoreCase(patente.trim())) {
                return vehiculos.get(i);
            }
        }
        return null;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public ArrayList<Vehiculo> filtrarPorCapacidad(int minimo) {
        ArrayList<Vehiculo> resultado = new ArrayList<>();
        for (int i = 0; i < vehiculos.size(); i++) {
            if (vehiculos.get(i).getCapacidadPasajeros() > minimo) {
                resultado.add(vehiculos.get(i));
            }
        }
        return resultado;
    }

    // ordenamientos sobre una copia para no tocar el orden original
    public ArrayList<Vehiculo> ordenarPorAnioDesc() {
        ArrayList<Vehiculo> copia = new ArrayList<>(vehiculos);
        for (int i = 0; i < copia.size() - 1; i++) {
            int mayor = i;
            for (int j = i + 1; j < copia.size(); j++) {
                if (copia.get(j).getAnioFabricacion() > copia.get(mayor).getAnioFabricacion()) {
                    mayor = j;
                }
            }
            intercambiar(copia, i, mayor);
        }
        return copia;
    }

    public ArrayList<Vehiculo> ordenarPorCapacidadDesc() {
        ArrayList<Vehiculo> copia = new ArrayList<>(vehiculos);
        for (int i = 0; i < copia.size() - 1; i++) {
            int mayor = i;
            for (int j = i + 1; j < copia.size(); j++) {
                if (copia.get(j).getCapacidadPasajeros() > copia.get(mayor).getCapacidadPasajeros()) {
                    mayor = j;
                }
            }
            intercambiar(copia, i, mayor);
        }
        return copia;
    }

    private void intercambiar(ArrayList<Vehiculo> lista, int a, int b) {
        Vehiculo aux = lista.get(a);
        lista.set(a, lista.get(b));
        lista.set(b, aux);
    }
}
