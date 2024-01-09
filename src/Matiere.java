import java.util.ArrayList;

public class Matiere {

    private String labelMatiere;
    private int heure_TD;
    private int heure_TP;
    private int heure_CM;
    private float Poids_CC;
    private String Type_CC;
    private float Poids_CT;
    private String Type_CT;
    private float poidMatiere;

    public Matiere(String labelMatiere, int heure_TD, int heure_TP, int heure_CM,float Poid_CC, String Type_CC, float Poid_CT, String Type_CT, float poidMatiere) {
        this.labelMatiere = labelMatiere;
        this.heure_TD = heure_TD;
        this.heure_TP = heure_TP;
        this.heure_CM = heure_CM;
        this.Poids_CC = Poid_CC;
        this.Type_CC = Type_CC;
        this.Poids_CT = Poid_CT;
        this.Type_CT = Type_CT;
        this.poidMatiere = poidMatiere;
    }

    public Matiere() {
    }

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

    public float getPoids_CC() {
        return Poids_CC;
    }

    public void setPoids_CC(float poids_CC) {
        Poids_CC = poids_CC;
    }

    public String getType_CC() {
        return Type_CC;
    }

    public void setType_CC(String type_CC) {
        Type_CC = type_CC;
    }

    public float getPoids_CT() {
        return Poids_CT;
    }

    public void setPoids_CT(float poids_CT) {
        Poids_CT = poids_CT;
    }

    public String getType_CT() {
        return Type_CT;
    }

    public void setType_CT(String type_CT) {
        Type_CT = type_CT;
    }

    public float getPoidMatiere() {
        return poidMatiere;
    }

    public void setPoidMatiere(float poidMatiere) {
        this.poidMatiere = poidMatiere;
    }
}
