import java.util.ArrayList;

public class Matiere {

    private String labelMatiere;
    private int heure_TD;
    private int heure_TP;
    private int heure_CM;
    private float poidMatiere;

    public String getLabelMatiere() {
        return labelMatiere;
    }

    public void setLabelMatiere(String labelMatiere) {
        this.labelMatiere = labelMatiere;
    }

    public int getHeure_TD() {
        return heure_TD;
    }

    public void setHeure_TD(int heure_TD) {
        this.heure_TD = heure_TD;
    }

    public int getHeure_TP() {
        return heure_TP;
    }

    public void setHeure_TP(int heure_TP) {
        this.heure_TP = heure_TP;
    }

    public int getHeure_CM() {
        return heure_CM;
    }

    public void setHeure_CM(int heure_CM) {
        this.heure_CM = heure_CM;
    }

    public float getPoidMatiere() {
        return poidMatiere;
    }

    public void setPoidMatiere(float poidMatiere) {
        this.poidMatiere = poidMatiere;
    }
}
