package Parser;

/**
 * Class Matiere
 * manage all the scholar subject for a subject.
 */
public class Matiere {

    // Attribut.
    private String labelMatiere;
    private int heure_TD;
    private int heure_TP;
    private int heure_CM;
    private int heure_projet;
    private float Poids_CC;
    private String Type_CC;
    private float Poids_CT;
    private String Type_CT;
    private float poidMatieres;

    /**
     * Permit to create an object Matier that will store all the infos about a subject.
     * @param labelMatiere the name of the subject
     * @param heure_TD the amount of TD hours for the subject.
     * @param heure_TP the amount of TP hours for the subject.
     * @param heure_CM the amount of CM hours for the subject.
     * @param heure_projet the amount of project hours for the subject.
     * @param Poid_CC the weight of the exam.
     * @param Type_CC the type of exam.
     * @param Poid_CT the weigth of the final exam.
     * @param Type_CT the type of the final exam.
     * @param poidMatieres the weight of the subject in the UE.
     */
    public Matiere(String labelMatiere, int heure_TD, int heure_TP, int heure_CM,int heure_projet,float Poid_CC, String Type_CC, float Poid_CT, String Type_CT, float poidMatieres) {
        this.labelMatiere = labelMatiere;
        this.heure_TD = heure_TD;
        this.heure_TP = heure_TP;
        this.heure_CM = heure_CM;
        this.heure_projet = heure_projet;
        this.Poids_CC = Poid_CC;
        this.Type_CC = Type_CC;
        this.Poids_CT = Poid_CT;
        this.Type_CT = Type_CT;
        this.poidMatieres = poidMatieres;
    }

    /**
     * Constructor by default for an empty subject.
     */
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

    public int getHeure_projet() {
        return heure_projet;
    }

    public void setHeure_projet(int heure_projet) {
        this.heure_projet = heure_projet;
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

    public float getPoidMatieres() {
        return poidMatieres;
    }

    public void setPoidMatieres(float poidMatiere) {
        this.poidMatieres = poidMatiere;
    }
}
