import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Ambiente> ambientes = new ArrayList<>();
        ArrayList<Cita> citas = new ArrayList<>();
        ArrayList<HistorialMedico> historiales = new ArrayList<>();

//datos pra prueba
        ambientes.add(new Ambiente(1, 2, 201, true, 4));
        ambientes.add(new Ambiente(2, 1, 105, true, 6));

        boolean continuar = true;

        while (continuar) {
            System.out.println("\n===== SISTEMA HOSPITALARIO (Versión del grupo Joshiro) =====");
            System.out.println("1. Registrar Cita");
            System.out.println("2. Ver Citas Registradas");
            System.out.println("3. Registrar Historial Médico");
            System.out.println("4. Ver Historiales Médicos");
            System.out.println("5. Ver Ambientes");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {

                case 1:
                    System.out.println("\n--- Registrar Cita ---");
                    System.out.print("Nombre del paciente: ");
                    String paciente = sc.nextLine();

                    System.out.print("Doctor: ");
                    String doctor = sc.nextLine();

                    System.out.print("Fecha (dd/mm/aaaa): ");
                    String fecha = sc.nextLine();

                    System.out.print("Hora (hh:mm): ");
                    String hora = sc.nextLine();

                    Cita nuevaCita = new Cita(paciente, doctor, fecha, hora);
                    citas.add(nuevaCita);

                    System.out.println("✔ Cita registrada correctamente.");
                    break;

                case 2:
                    System.out.println("\n--- Lista de Citas ---");
                    if (citas.isEmpty()) {
                        System.out.println("No hay citas registradas.");
                    } else {
                        for (Cita c : citas) {
                            System.out.println(c);
                        }
                    }
                    break;

                case 3:
                    System.out.println("\n--- Registrar Historial Médico ---");
                    System.out.print("Nombre del paciente: ");
                    String p = sc.nextLine();

                    System.out.print("Diagnóstico: ");
                    String diag = sc.nextLine();

                    System.out.print("Tratamiento: ");
                    String trat = sc.nextLine();

                    HistorialMedico hm = new HistorialMedico(p, diag, trat);
                    historiales.add(hm);

                    System.out.println("✔ Historial registrado correctamente.");
                    break;

                case 4:
                    System.out.println("\n--- Historiales Médicos ---");
                    if (historiales.isEmpty()) {
                        System.out.println("No hay historiales registrados.");
                    } else {
                        for (HistorialMedico h : historiales) {
                            System.out.println(h);
                        }
                    }
                    break;

                case 5:
                    System.out.println("\n--- Ambientes Disponibles ---");
                    for (Ambiente a : ambientes) {
                        a.mostrarInfo();
                        System.out.println("-------------------");
                    }
                    break;

                case 0:
                    continuar = false;
                    System.out.println("Saliendo del sistema...");
                    break;

                default:
                    System.out.println("❌ Opción inválida.");
            }

        }

        sc.close();
    }
}
