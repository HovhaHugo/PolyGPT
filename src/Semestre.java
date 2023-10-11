/**
 * Classe Semestre
 */
public class Semestre {
    private String label; //nom : S5,S6,...

    private List<UE> uniteEnseignement; //Un semestre est compose d'unite d'enseignement called 'UE'

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<UE> getUniteEnseignement() {
        return uniteEnseignement;
    }

    public void setUniteEnseignement(List<UE> uniteEnseignement) {
        this.uniteEnseignement = uniteEnseignement;
    }
}
