import java.util.ArrayList;

public class Matiere {

    private String labelMatiere;
    private double heureCM;
    private double heureTD;
    private double heureTP;
    private double heureProjet;
    private double poidsMatiere;
    private String typeCC;
    private String typeCT;
    private double poidsCC;
    private double poidsCT;

    public Matiere(String labelMatiere, double heureCM, double heureTD, double heureTP, double heureProjet, double poidsCC, String typeCC, double poidsCT, String typeCT, double poidsMatiere) {
        this.labelMatiere = labelMatiere;
        this.heureCM = heureCM;
        this.heureTD = heureTD;
        this.heureTP = heureTP;
        this.heureProjet = heureProjet;
        this.poidsMatiere = poidsMatiere;
        this.typeCC = typeCC;
        this.typeCT = typeCT;
        this.poidsCC = poidsCC;
        this.poidsCT = poidsCT;
    }

    public Matiere(String labelMatiere){
        this.labelMatiere = labelMatiere;
    }

    public String getLabelMatiere() {
        return labelMatiere;
    }

    public void setLabelMatiere(String labelMatiere) {
        this.labelMatiere = labelMatiere;
    }

    public double getHeureCM() {
        return heureCM;
    }

    public void setHeureCM(int heureCM) {
        this.heureCM = heureCM;
    }

    public double getHeureTD() {
        return heureTD;
    }

    public void setHeureTD(int heureTD) {
        this.heureTD = heureTD;
    }

    public double getHeureTP() {
        return heureTP;
    }

    public void setHeureTP(int heureTP) {
        this.heureTP = heureTP;
    }

    public double getHeureProjet() { return heureProjet;}

    public void setHeureProjet(int heureProjet) { this.heureProjet = heureProjet;}
    public double getPoidsMatiere() {
        return poidsMatiere;
    }

    public void setPoidsMatiere(double poidsMatiere) {
        this.poidsMatiere = poidsMatiere;
    }

    public void setTypeCC(String typeCC) {
        this.typeCC = typeCC;
    }

    public String getTypeCT() {
        return typeCT;
    }

    public void setTypeCT(String typeCT) {
        this.typeCT = typeCT;
    }
    public String getTypeCC() {
        return typeCC;
    }
    public double getPoidsCC() {
        return poidsCC;
    }

    public void setPoidsCC(double poidsCC) {
        this.poidsCC = poidsCC;
    }

    public double getPoidsCT() {
        return poidsCT;
    }

    public void setPoidsCT(double poidsCT) {
        this.poidsCT = poidsCT;
    }


}
