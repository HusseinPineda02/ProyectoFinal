import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Paciente {

    private int dni;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private Date fechaNacimiento;
    private int edad;
    private String genero;
    private List<String> historialMedico;
    private String numeroHistoriaClinica;
    private Double peso;
    private Double altura;

    //Consultorio-Clase 
    private Consultorio consultorioActual;

    //constructor
    public Paciente(int dni, String nombre, String apellidoPaterno, String apellidoMaterno,
                    Date fechaNacimiento, String numeroHistoriaClinica) {

        this.dni = dni;
        this.nombre = (nombre == null || nombre.equals("")) ? "Sin nombre" : nombre;
        this.apellidoPaterno = (apellidoPaterno == null || apellidoPaterno.equals("")) ? "Sin apellido paterno" : apellidoPaterno;
        this.apellidoMaterno = (apellidoMaterno == null || apellidoMaterno.equals("")) ? "Sin apellido materno" : apellidoMaterno;
        this.fechaNacimiento = (fechaNacimiento == null) ? new Date() : fechaNacimiento;
        this.numeroHistoriaClinica = (numeroHistoriaClinica == null || numeroHistoriaClinica.equals("")) ? "Sin numero de historia clinica" : numeroHistoriaClinica;

        this.genero = "por asignar";
        this.historialMedico = new ArrayList<>();
        this.peso = null;
        this.altura = null;
        this.consultorioActual = null;
        this.edad = calcularEdad();
    }

    //getters
    public int getDni() { 
    	return dni; 
    	}
    public String getNombre() { 
    	return nombre; 
    	}
    public String getApellidoPaterno() { 
    	return apellidoPaterno; 
    	}
    public String getApellidoMaterno() { 
    	return apellidoMaterno; 
    	}
    public Date getFechaNacimiento() { 
    	return fechaNacimiento; 
    }
    public int getEdad() { 
    	return edad; 
    	}
    public String getGenero() { 
    	return genero; 
    	}
    public List<String> getHistorialMedico() { 
    	return new ArrayList<>(historialMedico); 
    	}
    public String getNumeroHistoriaClinica() { 
    	return numeroHistoriaClinica; 
    	}
    public Double getPeso() { 
    	return peso; 
    	}
    public Double getAltura() { 
    	return altura; 
    	}
    public Consultorio getConsultorioActual() { 
    	return consultorioActual; 
    	}

    //setters
    public void setGenero(String genero) {
        if (genero == null || genero.equals("")) return;
        this.genero = genero;
    }

    public void setPeso(Double peso) {
        if (peso != null && peso <= 0) return;
        this.peso = peso;
    }

    public void setAltura(Double altura) {
        if (altura != null && altura <= 0) return;
        this.altura = altura;
    }

    //metodos
    public void agregarHistorial(String evento) {
        if (evento == null || evento.equals("")) return;
        historialMedico.add(evento);
    }

    public void asignarAConsultorio(Consultorio consultorio) {
        if (consultorio == null) return;
        if (!consultorio.estaDisponible()) return;
        consultorio.ocupar();
        this.consultorioActual = consultorio;
    }

    public void liberarDeConsultorio() {
        if (consultorioActual != null) {
            consultorioActual.liberar();
            consultorioActual = null;
        }
    }
    //calcula el indice de masa corporal_extra
    public Double calcularIMC() {
        if (peso == null || altura == null) return null;
        return Math.round((peso / (altura * altura)) * 100.0) / 100.0;
    }

    @Override
    public String toString() {
        String nomConsultorio = (consultorioActual != null) ? consultorioActual.getCodigo() : "ninguno";
        return nombre + " " + apellidoPaterno + " Edad: " + edad + " Consultorio: " + nomConsultorio;
    }

    //Verificar duplicados_extra
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Paciente)) return false;
        Paciente otro = (Paciente) obj;
        return dni == otro.dni;
    }

    @Override
    public int hashCode() {
        return dni;
    }
}
