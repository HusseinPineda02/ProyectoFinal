import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Listas principales
        ArrayList<Ambiente> ambientes = new ArrayList<>();
        ArrayList<Cita> citas = new ArrayList<>();
        ArrayList<HistorialMedico> historiales = new ArrayList<>();

        // === Ambientes de prueba ===
        ambientes.add(new Ambiente(1, 1, 101));
        ambientes.add(new Ambiente(2, 2, 205));
        ambientes.add(new Ambiente(3, 3, 310));

        boolean continuar = true;

        while (continuar) {

            System.out.println("\n========= SISTEMA HOSPITALARIO =========");
            System.out.println("1. Registrar Cita");
            System.out.println("2. Ver todas las Citas");
            System.out.println("3. Registrar historial médico");
            System.out.println("4. Ver historiales médicos");
            System.out.println("5. Ver ambientes");
            System.out.println("0. Salir");
            System.out.print("Seleccione: ");

            int op = sc.nextInt();
            sc.nextLine(); // limpiar buffer

            switch (op) {

              
                // 1. REGISTRAR CITA
               
                case 1:
                    System.out.println("\n--- Registrar Cita ---");
                    System.out.print("ID de cita: ");
                    int idCita = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Hora: ");
                    String hora = sc.nextLine();

                    System.out.print("Especialidad: ");
                    String especialidad = sc.nextLine();

                    System.out.print("Nombre del paciente: ");
                    String paciente = sc.nextLine();

                    // Elegir ambiente
                    System.out.println("\nSeleccione un ambiente:");
                    for (int i = 0; i < ambientes.size(); i++) {
                        System.out.println((i + 1) + ". Ambiente #" + ambientes.get(i).getNumero());
                    }
                    System.out.print("Opción: ");
                    int ambOP = sc.nextInt();
                    sc.nextLine();

                    Ambiente ambienteSeleccionado = ambientes.get(ambOP - 1);

                    // Crear la cita
                    Cita nueva = new Cita(idCita, new Date(), hora, especialidad, null, ambienteSeleccionado);
                    citas.add(nueva);

                    System.out.println("✔ Cita registrada correctamente.");
                    break;


              
                // 2. VER CITAS
             
                case 2:
                    System.out.println("\n--- Lista de Citas ---");
                    if (citas.isEmpty()) {
                        System.out.println("No hay citas registradas.");
                    } else {
                        for (Cita c : citas) {
                            c.mostrarDatosCita();
                            System.out.println("--------------------");
                        }
                    }
                    break;


             
                // 3. REGISTRAR HISTORIAL
         
                case 3:
                    System.out.println("\n--- Registrar Historial Médico ---");
                    System.out.print("DNI del paciente: ");
                    int dni = sc.nextInt();
                    sc.nextLine();

                    HistorialMedico hm = new HistorialMedico(dni);
                    historiales.add(hm);

                    System.out.println("✔ Historial creado correctamente.");
                    break;


         
                // 4. VER HISTORIALES
            
                case 4:
                    System.out.println("\n--- HISTORIALES MÉDICOS ---");
                    if (historiales.isEmpty()) {
                        System.out.println("No existen historiales.");
                    } else {
                        for (HistorialMedico h : historiales) {
                            h.mostrarConNumeros();
                        }
                    }
                    break;


           
                // 5. VER AMBIENTES
           
                case 5:
                    System.out.println("\n--- AMBIENTES ---");
                    for (Ambiente a : ambientes) {
                        a.mostrarInformacion();
                        System.out.println("---------------------");
                    }
                    break;


            
                // 0. SALIR
           
                case 0:
                    continuar = false;
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("❌ Opción no válida.");
            }
        }

        sc.close();
    }
}
