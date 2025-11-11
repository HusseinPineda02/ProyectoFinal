public class Doctor extends Trabajador {

    ArrayList<Pacientes> p;
    String especialidad;
    Horario horario;

    public Doctor() {

    }

    public void setEspecialidad(String especialidad){
        this.especialidad = especialidad;
    }

    public void asignarCita() {

    }

    public String verHistorialPaciente(String dni){
        int i = 0;
        for(Paciente p : pacientes){
            if(p.getDni == dni){
                System.out.print(p.getHistorial());
            }
            i++;
        }
        return "PACIENTE NO ENCONTRADO\n";
    }

}