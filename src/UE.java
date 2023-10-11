import java.util.ArrayList;
import java.util.List;

public class UE {

    private String label;
    private int nombreMatiere;
    private int ECTS;
    private List<Matiere> listMatiere;

    private List<String> listEvaluation;

    private List<String> listTypeEval;

    private List<Float> listCoeffEval;

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
    public int getECTS() {
        return ECTS;
    }

    /**
     * Modify the amount of ECTS in the UE
     * @param ECTS The new amount of ECTS
     */
    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }

    public List<Matiere> getListMatiere() {
        return listMatiere;
    }

    public void setListMatiere(List<Matiere> listMatiere) {
        this.listMatiere = listMatiere;
    }

    public List<String> getListEvaluation() {
        return listEvaluation;
    }

    public void setListEvaluation(List<String> listEvaluation) {
        this.listEvaluation = listEvaluation;
    }

    public List<String> getListTypeEval() {
        return listTypeEval;
    }

    public void setListTypeEval(List<String> listTypeEval) {
        this.listTypeEval = listTypeEval;
    }

    public List<Float> getListCoeffEval() {
        return listCoeffEval;
    }

    public void setListCoeffEval(List<Float> listCoeffEval) {
        this.listCoeffEval = listCoeffEval;
    }
}
