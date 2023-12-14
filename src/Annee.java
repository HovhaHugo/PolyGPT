import java.util.List;

/**
 * Classe Annee
 */
public class Annee {

    private String label; // 3A, 4A, 5A
    private List<Semestre> semestre; //Une annee est une liste de deux semestres

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Semestre> getSemestre() {
        return semestre;
    }

    public void setSemestre(List<Semestre> semestre) {
        this.semestre = semestre;
    }
}

///Une annee est definit par 3A/4A/5A ou par l'annee civique 2023-2024 ?



