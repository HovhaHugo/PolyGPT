package Parser;

import java.util.List;

/**
 * Class UE
 * contain all the information of a UE (label, number of subject, ECTS and subject's)
 */
public class UE {

    private String label;
    private int nombreMatiere;
    private String descriptionsEU;
    private int ECTS;
    private List<Matiere> listMatiere;


    /**
     * Constructor that will contain all the information about a UE
     * @param label
     * @param ECTS
     * @param listMatiere
     */
    public UE(String label, int ECTS, List<Matiere> listMatiere) {
        this.label = label;
        this.ECTS = ECTS;
        this.listMatiere = listMatiere;
    }

    /**
     * Constructor by default that permit to create and empty UE
     */
    public UE() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getNombreMatiere() {
        return nombreMatiere;
    }

    public void setNombreMatiere(int nombreMatiere) {
        this.nombreMatiere = nombreMatiere;
    }

    public String getDescriptionsEU() {
        return descriptionsEU;
    }

    public void setDescriptionsEU(String descriptionsEU) {
        this.descriptionsEU = descriptionsEU;
    }

    public int getECTS() {
        return ECTS;
    }

    public void setECTS(int ECTS) {
        this.ECTS = ECTS;
    }

    public List<Matiere> getListMatiere() {
        return listMatiere;
    }

    public void setListMatiere(List<Matiere> listMatiere) {
        this.listMatiere = listMatiere;
    }

}
