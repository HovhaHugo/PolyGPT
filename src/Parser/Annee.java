package Parser;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Class Annee
 * contain all the information of a schoole year
 */
public class Annee {

    private String label;
    private List<Semestre> semestre;
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

    /**
     * Permit to parse the semester into the school years
     * @param index1 The index of the first semester in the Excel file
     * @param index2 The index of the second semester in the Excel file
     * @param file The Excel file
     * @throws IOException Some exception if the Excel file doesn't exist
     */
    public void setSemestres(String index1, String index2, File file) throws IOException {
        this.semestre = new ArrayList<>();

        Parser parser = new Parser();
        Semestre semestre1 = new Semestre();
        semestre1.setLabel(index1);
        semestre1.setUniteEnseignement(parser.parseurSemestre(file, index1));
        Semestre semestre2 = new Semestre();
        semestre2.setLabel(index2);
        semestre2.setUniteEnseignement(parser.parseurSemestre(file, index2));

        this.semestre.add(semestre1);
        this.semestre.add(semestre2);
    }

    /**
     * This function permit to print everything about a school year into our file.
     * @param writer The PrintWriter variable that will print everything into the new file
     */
    public void afficherAnnee(PrintWriter writer) {
        writer.println("\nL'année "+this.getLabel()+" est constituer des semestre suivant : ");
        List<Semestre> semestreAnnee = this.getSemestre();
        for(int i = 0; i<semestreAnnee.size(); i++){
            writer.println("Lors du semestre "+semestreAnnee.get(i).getLabel()+" il y a les cours suivant : ");
            semestreAnnee.get(i).afficherSemestre(writer);
            writer.println("");
        }
        writer.println("Enfin, pour valider l'année, il est nécessaire d'avoir le niveau de TOEIC de "+this.getToiec()+"\n");
    }

}




