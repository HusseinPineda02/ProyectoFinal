public class Sala {
    private int aforo;
    private int camillas;
    private String tipoSala;

    public Sala(int aforo, int camillas, String tipoSala) {
        this.aforo = aforo;
        this.camillas = camillas;
        this.tipoSala = tipoSala;
    }

    public int getAforo() { return aforo; }
    public int getCamillas() { return camillas; }
    public String getTipoSala() { return tipoSala; }

    public void setAforo(int aforo) { this.aforo = aforo; }
    public void setCamillas(int camillas) { this.camillas = camillas; }
    public void setTipoSala(String tipoSala) { this.tipoSala = tipoSala; }

    public void aumentarAforo() { aforo++; }
    public void reducirAforo() { aforo--; }
    public void aumentarCamillas() { camillas++; }
    public void cambiarTipoDeSala(String nuevo) { this.tipoSala = nuevo; }
}
