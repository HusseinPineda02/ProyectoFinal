public abstract class Trabajador {

    String nombre, apePaterno, apeMaterno, id, dni;
    double salario;

    public Trabajador(){

    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setApePaterno(String apePaterno){
        this.apePaterno= apePaterno;
    }
    public void setApeMaterno(String apeMaterno){
        this.apeMaterno= apeMaterno;
    }
    public void setId(String id){
        this.id = id;
    }
    public void setDni(String dni){
        this.dni = dni;
    }
    public void setSalario(double salario){
        this.salario = salario;
    }
    public String getNombre(){
        return nombre;
    }
    public String getApePaterno(){
        return apePaterno;
    }
    public String getApeMaterno(){
        return apeMaterno;
    }
    public String getId(){
        return id;
    }
    public String getDni(){
        return dni;
    }
    public double getSalario(){
        return salario;
    }
    



}