public class Sala {
	
    private String codigo;
    private int aforo;
    private int camillas;
    private String tipoSala;
    private int ocupados;

    // constructor
    public Sala(String codigo, int aforo, int camillas, String tipoSala) {

        this.codigo = (codigo == null || codigo.isEmpty()) ? "SIN_CODIGO" : codigo;

        this.aforo = aforo >= 0 ? aforo : 0;
        this.camillas = camillas >= 0 ? camillas : 0;

        this.tipoSala = (tipoSala == null || tipoSala.isEmpty()) ? "General" : tipoSala;

        this.ocupados = 0;
    }

    // getters
    public String getCodigo() {
        return codigo;
    }

    public int getAforo() {
        return aforo;
    }

    public int getCamillas() {
        return camillas;
    }

    public String getTipoSala() {
        return tipoSala;
    }

    public int getOcupados() {
        return ocupados;
    }

    // setters
    public void setAforo(int aforo) {
        if (aforo >= 0) {
            this.aforo = aforo;
            if (ocupados > aforo) ocupados = aforo; // Ajuste lógico
        }
    }

    public void setCamillas(int camillas) {
        if (camillas >= 0) {
            this.camillas = camillas;
        }
    }

    public void setTipoSala(String tipoSala) {
        if (tipoSala != null && !tipoSala.isEmpty()) {
            this.tipoSala = tipoSala;
        }
    }

    public void setCodigo(String codigo) {
        if (codigo != null && !codigo.isEmpty()) {
            this.codigo = codigo;
        }
    }

    // metodos para el sistema
    public boolean ingresarPersona() {
        if (ocupados < aforo) {
            ocupados++;
            return true;
        }
        return false;
    }

    public boolean retirarPersona() {
        if (ocupados > 0) {
            ocupados--;
            return true;
        }
        return false;
    }

    public boolean estaLlena() {
        return ocupados >= aforo;
    }

    public void aumentarAforo() {
        aforo++;
    }

    public void reducirAforo() {
        if (aforo > 0) {
            aforo--;
            if (ocupados > aforo) ocupados = aforo;
        }
    }

    public void aumentarCamillas() {
        camillas++;
    }

    public boolean retirarCamilla() {
        if (camillas > 0) {
            camillas--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Sala " + codigo +
                " Tipo: " + tipoSala +
                " Aforo: " + aforo +
                " Ocupados: " + ocupados +
                " Camillas: " + camillas;
    }
}
