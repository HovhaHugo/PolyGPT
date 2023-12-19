import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        ParserH parser = new ParserH();
        ArrayList<UE> listeUE = parser.parseurMaquette("S6");
        Semestre S5 = new Semestre();
        S5.setUniteEnseignement(listeUE);
        S5.afficherSemestre();
        System.out.println();
        System.out.println("--------------------------------------------------");
        System.out.println("Changement de semestre : ");
        ArrayList<UE> listeUES6 = parser.parseurMaquette("S8");
        Semestre S6 = new Semestre();
        S6.setUniteEnseignement(listeUES6);
        S6.afficherSemestre();
        Annee annee3 = new Annee();
    }
}