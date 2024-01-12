import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

    public void setSemestres(String index1, String index2, File file) throws IOException {
        this.semestre = new ArrayList<>();

        ParserH parser = new ParserH();
        Semestre semestre1 = new Semestre();
        semestre1.setLabel(index1);
        long now = System.currentTimeMillis();
        semestre1.setUniteEnseignement(parser.parseurMaquette(file, index1));
        long then = System.currentTimeMillis();
        float time = then - now;
        System.out.println("Ont mets "+time+"milli sec à parser un semestre.");
        Semestre semestre2 = new Semestre();
        semestre2.setLabel(index2);
        semestre2.setUniteEnseignement(parser.parseurMaquette(file, index2));

        this.semestre.add(semestre1);
        this.semestre.add(semestre2);
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




