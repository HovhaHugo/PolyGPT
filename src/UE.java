import java.util.ArrayList;
import java.util.List;

public class UE {

    private String label;
    private int nombreMatiere;
    private double ECTS;
    private List<Matiere> listMatiere;


    public UE(String label, int nombreMatiere, double ECTS, List<Matiere> listMatiere) {
        this.label = label;
        this.nombreMatiere = nombreMatiere;
        this.ECTS = ECTS;
        this.listMatiere = listMatiere;
    }

    public UE(String label) {
        this.label = label;
    }

    /**
     * Provide the label of the UE.
     * @return The label of the UE
     */
    public String getLabel() {
        return label;
    }

    /**
     * Modify the label of the UE
     * @param label The new label of the UE
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Provide the number of courses in a UE.
     * @return The number of courses in a UE.
     */
    public int getNombreMatiere() {
        return nombreMatiere;
    }

    /**
     * Modify the number of courses in an UE
     * @param nombreMatiere The new number of course in the UE
     */
    public void setNombreMatiere(int nombreMatiere) {
        this.nombreMatiere = nombreMatiere;
    }

    /**
     * Provide the amount of ECTS in the UE (european norm)
     * @return The amount of ECTS in the UE
     */
    public double getECTS() {
        return ECTS;
    }

    /**
     * Modify the amount of ECTS in the UE
     * @param ECTS The new amount of ECTS
     */
    public void setECTS(double ECTS) {
        this.ECTS = ECTS;
    }

    public List<Matiere> getListMatiere() {
        return listMatiere;
    }

    public void setListMatiere(List<Matiere> listMatiere) {
        this.listMatiere = listMatiere;
    }

    public void addMatiere(String labelMatiere){
        Matiere nouvelleMatiere = new Matiere(labelMatiere);
        listMatiere.add(nouvelleMatiere);
    }


}
