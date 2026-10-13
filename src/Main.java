import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final boolean CARGAR_DATOS_PRUEBA = true;
    private static Scanner sc = new Scanner(System.in);
    private static Empresa empresa = new Empresa();

    public static void main(String[] args) {
        if (CARGAR_DATOS_PRUEBA) {
            cargarDatosPrueba();
        }

        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Elegi una opcion: ");
            switch (opcion) {
                case 1:
                    agregarVehiculo();
                    break;
                case 2:
                    mostrarLista(empresa.getVehiculos());
                    break;
                case 3:
                    // no entendí muy bien si era este el resultado que buscaban, no estaba muy claro en la consigna
                    realizarServicios();
                    break;
                case 4:
                    buscarVehiculo();
                    break;
                case 5:
                    mostrarMayoresACapacidad();
                    break;
                case 6:
                    mostrarLista(empresa.ordenarPorAnioDesc());
                    break;
                case 7:
                    mostrarLista(empresa.ordenarPorCapacidadDesc());
                    break;
                case 8:
                    System.out.println("chau!");
                    break;
                default:
                    System.out.println("Opcion invalida, elegi entre 1 y 8");
            }
        } while (opcion != 8);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("---- Transporte Turistico ----");
        System.out.println("1. Agregar Vehiculo");
        System.out.println("2. Mostrar todos los Vehiculos");
        System.out.println("3. Realizar servicios Turisticos");
        System.out.println("4. Buscar vehiculo por Patente");
        System.out.println("5. Mostrar vehiculos con Capacidad Mayor a un valor");
        System.out.println("6. Mostrar vehiculos por Anio de Fabricacion Descendente");
        System.out.println("7. Mostrar vehiculos por Capacidad Descendente");
        System.out.println("8. Salir");
    }

    private static void cargarDatosPrueba() {
        empresa.agregar(new ColectivoTuristico("AA123BB", "Mercedes Benz", 45, 2020, 2));
        empresa.agregar(new ColectivoTuristico("AB456CD", "Scania", 50, 2022, 1));
        empresa.agregar(new VanEjecutiva("AC789EF", "Renault", 15, 2021, true));
        empresa.agregar(new VanEjecutiva("AD321GH", "Toyota", 12, 2019, false));
        empresa.agregar(new LanchaTuristica("LAN001", "Yamaha", 8, 2023, "NAFTA"));
        empresa.agregar(new LanchaTuristica("LAN002", "Mercury", 10, 2022, "ELECTRICO"));
    }

    private static void agregarVehiculo() {
        System.out.println("1. Colectivo  2. Van  3. Lancha");
        int tipo = leerEntero("Tipo de vehiculo: ");
        if (tipo < 1 || tipo > 3) {
            System.out.println("Tipo invalido");
            return;
        }

        String patente = leerPatente();
        String marca = leerMarca();
        int capacidad = leerCapacidad();
        int anio = leerAnio();

        Vehiculo nuevo;
        if (tipo == 1) {
            nuevo = new ColectivoTuristico(patente, marca, capacidad, anio, leerPisos());
        } else if (tipo == 2) {
            nuevo = new VanEjecutiva(patente, marca, capacidad, anio, leerAire());
        } else {
            nuevo = new LanchaTuristica(patente, marca, capacidad, anio, leerMotor());
        }

        empresa.agregar(nuevo);
        System.out.println("Vehiculo agregado correctamente");
    }

    private static void realizarServicios() {
        ArrayList<Vehiculo> lista = empresa.getVehiculos();
        if (lista.isEmpty()) {
            System.out.println("No hay vehiculos cargados");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            lista.get(i).realizarServicio();
        }
    }

    private static void buscarVehiculo() {
        System.out.print("Patente a buscar: ");
        String patente = sc.nextLine();
        if (patente.trim().isEmpty()) {
            System.out.println("La patente no puede estar vacia");
            return;
        }
        Vehiculo v = empresa.buscarPorPatente(patente);
        if (v == null) {
            System.out.println("No se encontro ningun vehiculo con esa patente");
        } else {
            System.out.println(v);
        }
    }

    private static void mostrarMayoresACapacidad() {
        int minimo = leerEntero("Capacidad minima: ");
        mostrarLista(empresa.filtrarPorCapacidad(minimo));
    }

    private static void mostrarLista(ArrayList<Vehiculo> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay vehiculos para mostrar");
            return;
        }
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Tenes que ingresar un numero entero");
            }
        }
    }

    private static String leerPatente() {
        while (true) {
            System.out.print("Patente: ");
            String patente = sc.nextLine();
            try {
                Vehiculo.validarPatente(patente);
                if (empresa.existePatente(patente)) {
                    System.out.println("Ya existe un vehiculo con esa patente");
                } else {
                    return patente;
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String leerMarca() {
        while (true) {
            System.out.print("Marca: ");
            String marca = sc.nextLine();
            try {
                Vehiculo.validarMarca(marca);
                return marca;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int leerCapacidad() {
        while (true) {
            int capacidad = leerEntero("Capacidad de pasajeros: ");
            try {
                Vehiculo.validarCapacidad(capacidad);
                return capacidad;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int leerAnio() {
        while (true) {
            int anio = leerEntero("Anio de fabricacion: ");
            try {
                Vehiculo.validarAnio(anio);
                return anio;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int leerPisos() {
        while (true) {
            int pisos = leerEntero("Cantidad de pisos (1 o 2): ");
            try {
                ColectivoTuristico.validarPisos(pisos);
                return pisos;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static boolean leerAire() {
        while (true) {
            System.out.print("Tiene aire acondicionado? (s/n): ");
            String resp = sc.nextLine().trim().toLowerCase();
            if (resp.equals("s")) {
                return true;
            } else if (resp.equals("n")) {
                return false;
            }
            System.out.println("Responde s o n");
        }
    }

    private static String leerMotor() {
        while (true) {
            System.out.print("Tipo de motor (Nafta, Diesel, Electrico): ");
            String motor = sc.nextLine();
            try {
                LanchaTuristica.validarMotor(motor);
                return motor;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
