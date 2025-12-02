import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Hospital hospital = new Hospital("Hospital Equipo3", "Arequipa", "Av. UTP", 200);

        boolean activo = true;

        while (activo) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Pacientes");
            System.out.println("2. Trabajadores");
            System.out.println("3. Salas");
            System.out.println("4. Consultorios");
            System.out.println("5. Salir");
            System.out.print("Opción: ");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    hospital.mostrarPacientes();
                    break;
                case 2:
                    hospital.consultaTrabajadores();
                    break;
                case 3:
                    hospital.mostrarSalas();
                    break;
                case 4:
                    hospital.mostrarConsultorios();
                    break;
                case 5:
                    activo = false;
                    System.out.println("Bye.");
                    break;
                default:
                    System.out.println("Inválido.");
            }
        }
    }
}
