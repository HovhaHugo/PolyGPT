import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.*;

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

    public void afficherSemestre(PrintWriter writer) {
        for(int u =0; u < this.uniteEnseignement.size(); u++) {
            //SI un EU est un UE, on affiche les ECTS, sinon non
            if(Objects.equals(this.uniteEnseignement.get(u).getLabel().substring(0,2), "UE")){
                writer.println("L'UE n°" + u + " est l'UE de : " + this.uniteEnseignement.get(u).getLabel()+" | ECTS : "
                        +this.uniteEnseignement.get(u).getECTS());
                System.out.println("L'UE n°" + u + " est l'UE de : " + this.uniteEnseignement.get(u).getLabel()+" | ECTS : "
                        +this.uniteEnseignement.get(u).getECTS());
            }else{
                if(Objects.equals(this.uniteEnseignement.get(u).getLabel().substring(0,2), "SO")) {
                    writer.println("Une possibilité est le : " + this.uniteEnseignement.get(u).getLabel());
                    System.out.println("Une possibilité est le : " + this.uniteEnseignement.get(u).getLabel());
                } else if (Objects.equals(this.uniteEnseignement.get(u).getLabel().substring(0,2), "ST")) {
                    writer.println("Il est aussi possible de faire un : " + this.uniteEnseignement.get(u).getLabel());
                    System.out.println("Il est aussi possible de faire un : " + this.uniteEnseignement.get(u).getLabel());
                }
            }

            if(this.uniteEnseignement.get(u).getDescriptionsEU() != null){
                writer.println("Il faut choisir une matière parmis les 3 suivantes");
                System.out.println("Il faut choisir une matière parmis les 3 suivantes");
            }
            ArrayList<Matiere> listeMatieresUE = new ArrayList<Matiere>(this.uniteEnseignement.get(u).getListMatiere());

            //Pour chaque matière :
            for (Matiere matiere : listeMatieresUE) {
                int nbTab = 67 / 4 - matiere.getLabelMatiere().length() / 4;
                //Si on as rien, on affiche rien
                if (matiere.getPoids_CC() == 0 && matiere.getPoids_CT() == 0) {
                    writer.print("- " + matiere.getLabelMatiere());
                    System.out.print("- " + matiere.getLabelMatiere());
                    for (int j = 1; j < nbTab; j++) {
                        writer.print("\t");
                        System.out.print("\t");
                    }
                    writer.println("| CM :" + matiere.getHeure_CM() +
                            " | TD :" + matiere.getHeure_TD() +
                            " | TP :" + matiere.getHeure_TP() +
                            " | Poid :" + matiere.getPoidMatiere());
                    System.out.println("| CM :" + matiere.getHeure_CM() +
                            " | TD :" + matiere.getHeure_TD() +
                            " | TP :" + matiere.getHeure_TP() +
                            " | Poid :" + matiere.getPoidMatiere());
                } else {
                    //Si on as les 2, on les affiches
                    if (matiere.getPoids_CC() != 0 && matiere.getPoids_CT() != 0) {
                        writer.print("- " + matiere.getLabelMatiere());
                        System.out.print("- " + matiere.getLabelMatiere());
                        for (int j = 1; j < nbTab; j++) {
                            writer.print("\t");
                            System.out.print("\t");
                        }
                        writer.println("| CM :" + matiere.getHeure_CM() +
                                " | TD :" + matiere.getHeure_TD() +
                                " | TP :" + matiere.getHeure_TP() +
                                " | CC :" + matiere.getPoids_CC() +
                                " | Type du CC :" + matiere.getType_CC() +
                                " | CT :" + matiere.getPoids_CT() +
                                " | Type du CT :" + matiere.getType_CT() +
                                " | Poid :" + matiere.getPoidMatiere());
                        System.out.println("| CM :" + matiere.getHeure_CM() +
                                " | TD :" + matiere.getHeure_TD() +
                                " | TP :" + matiere.getHeure_TP() +
                                " | CC :" + matiere.getPoids_CC() +
                                " | Type du CC :" + matiere.getType_CC() +
                                " | CT :" + matiere.getPoids_CT() +
                                " | Type du CT :" + matiere.getType_CT() +
                                " | Poid :" + matiere.getPoidMatiere());
                    } else {
                        //Si on as des CC, on les affiches
                        if (matiere.getPoids_CC() != 0) {
                            writer.print("- " + matiere.getLabelMatiere());
                            System.out.print("- " + matiere.getLabelMatiere());
                            for (int j = 1; j < nbTab; j++) {
                                writer.print("\t");
                                System.out.print("\t");
                            }
                            writer.println("| CM :" + matiere.getHeure_CM() +
                                    " | TD :" + matiere.getHeure_TD() +
                                    " | TP :" + matiere.getHeure_TP() +
                                    " | CC :" + matiere.getPoids_CC() +
                                    " | Type du CC :" + matiere.getType_CC() +
                                    " | Poid :" + matiere.getPoidMatiere());
                            System.out.println("| CM :" + matiere.getHeure_CM() +
                                    " | TD :" + matiere.getHeure_TD() +
                                    " | TP :" + matiere.getHeure_TP() +
                                    " | CC :" + matiere.getPoids_CC() +
                                    " | Type du CC :" + matiere.getType_CC() +
                                    " | Poid :" + matiere.getPoidMatiere());
                        }
                        //SI on as des CT, on les affiches
                        if (matiere.getPoids_CT() != 0) {
                            writer.print("- " + matiere.getLabelMatiere());
                            System.out.print("- " + matiere.getLabelMatiere());
                            for (int j = 1; j < nbTab; j++) {
                                writer.print("\t");
                                System.out.print("\t");
                            }
                            writer.println("| CM :" + matiere.getHeure_CM() +
                                    " | TD :" + matiere.getHeure_TD() +
                                    " | TP :" + matiere.getHeure_TP() +
                                    " | CT :" + matiere.getPoids_CT() +
                                    " | Type du CT :" + matiere.getType_CT() +
                                    " | Poid :" + matiere.getPoidMatiere());
                            System.out.println("| CM :" + matiere.getHeure_CM() +
                                    " | TD :" + matiere.getHeure_TD() +
                                    " | TP :" + matiere.getHeure_TP() +
                                    " | CT :" + matiere.getPoids_CT() +
                                    " | Type du CT :" + matiere.getType_CT() +
                                    " | Poid :" + matiere.getPoidMatiere());
                        }
                    }
                }
            }

        }
    }
}
