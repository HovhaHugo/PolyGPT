import java.io.PrintWriter;
import java.util.List;

/**
 * Classe Annee
 */
public class Annee {

    private String label; // 3A, 4A, 5A
    private List<Semestre> semestre; //Une annee est une liste de deux semestres

    private int toiec;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getToiec() {
        return toiec;
    }

    public void setToiec(int toiec) {
        this.toiec = toiec;
    }

    public List<Semestre> getSemestre() {
        return semestre;
    }

    public void setSemestre(List<Semestre> semestre) {
        this.semestre = semestre;
    }

    public void afficherAnnee(PrintWriter writer) {
        writer.println("L'annee "+this.getLabel()+" est contistuer des semestre suivant : ");
        List<Semestre> semestreAnnee = this.getSemestre();
        for(int i = 0; i<semestreAnnee.size(); i++){
            semestreAnnee.get(i).afficherSemestre(writer);
        }
        writer.println("Enfin, pour valider l'année, il est nécessaire d'avoir le niveau de TOEIC de "+this.getToiec());
    }
}




