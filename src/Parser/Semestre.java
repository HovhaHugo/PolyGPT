package Parser;

import java.io.PrintWriter;
import java.util.*;

/**
 * Class Semestre
 * contain all the information of a semester (the name and UE's)
 */
public class Semestre {
    private String label;
    private List<UE> uniteEnseignement;

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

    /**
     * Show the list of UE and their following school subject.
     * @param writer The PrintWriter variable that will print everything in the new file.
     */
    public void afficherSemestre(PrintWriter writer) {
        for(int u =0; u < this.uniteEnseignement.size(); u++) {
            //Si un EU est un UE, on affiche les ECTS, sinon non
            if(Objects.equals(this.uniteEnseignement.get(u).getLabel().substring(0,2), "UE")){
                writer.println("\nL'UE n°" + u + " est l'UE de : " + this.uniteEnseignement.get(u).getLabel()+" | ECTS : "
                        +this.uniteEnseignement.get(u).getECTS());
            }else{
                if(Objects.equals(this.uniteEnseignement.get(u).getLabel().substring(0,2), "SO")) {
                    writer.println("\nUne possibilité est le : " + this.uniteEnseignement.get(u).getLabel());
                } else if (Objects.equals(this.uniteEnseignement.get(u).getLabel().substring(0,2), "ST")) {
                    writer.println("\nIl est aussi possible de faire un : " + this.uniteEnseignement.get(u).getLabel());
                }
            }

            if(this.uniteEnseignement.get(u).getDescriptionsEU() != null){
                writer.println("Il faut choisir une matière parmis les 3 suivantes");
            }
            ArrayList<Matiere> listeMatieresUE = new ArrayList<Matiere>(this.uniteEnseignement.get(u).getListMatiere());

            //Pour chaque matière :
            for (Matiere matiere : listeMatieresUE) {
                int nbTab = 67 / 4 - matiere.getLabelMatiere().length() / 4;
                //Si on as rien, on affiche rien
                if (matiere.getPoids_CC() == 0 && matiere.getPoids_CT() == 0) {
                    writer.print("- " + matiere.getLabelMatiere());
                    for (int j = 1; j < nbTab; j++) {
                        writer.print("\t");
                    }
                    writer.println("| CM :" + matiere.getHeure_CM() +
                            " | TD :" + matiere.getHeure_TD() +
                            " | TP :" + matiere.getHeure_TP() +
                            " | Projet :" + matiere.getHeure_projet() +
                            " | Poid :" + matiere.getPoidMatieres());
                } else {
                    //Si on as les 2, on les affiches
                    if (matiere.getPoids_CC() != 0 && matiere.getPoids_CT() != 0) {
                        writer.print("- " + matiere.getLabelMatiere());
                        for (int j = 1; j < nbTab; j++) {
                            writer.print("\t");
                        }
                        writer.println("| CM :" + matiere.getHeure_CM() +
                                " | TD :" + matiere.getHeure_TD() +
                                " | TP :" + matiere.getHeure_TP() +
                                " | Projet :" + matiere.getHeure_projet() +
                                " | CC :" + matiere.getPoids_CC() +
                                " | Type du CC :" + matiere.getType_CC() +
                                " | CT :" + matiere.getPoids_CT() +
                                " | Type du CT :" + matiere.getType_CT() +
                                " | Poid :" + matiere.getPoidMatieres());
                    } else {
                        //Si on as des CC, on les affiches
                        if (matiere.getPoids_CC() != 0) {
                            writer.print("- " + matiere.getLabelMatiere());
                            for (int j = 1; j < nbTab; j++) {
                                writer.print("\t");
                            }
                            writer.println("| CM :" + matiere.getHeure_CM() +
                                    " | TD :" + matiere.getHeure_TD() +
                                    " | TP :" + matiere.getHeure_TP() +
                                    " | Projet :" + matiere.getHeure_projet() +
                                    " | CC :" + matiere.getPoids_CC() +
                                    " | Type du CC :" + matiere.getType_CC() +
                                    " | Poid :" + matiere.getPoidMatieres());
                        }
                        //SI on as des CT, on les affiches
                        if (matiere.getPoids_CT() != 0) {
                            writer.print("- " + matiere.getLabelMatiere());
                            for (int j = 1; j < nbTab; j++) {
                                writer.print("\t");
                            }
                            writer.println("| CM :" + matiere.getHeure_CM() +
                                    " | TD :" + matiere.getHeure_TD() +
                                    " | TP :" + matiere.getHeure_TP() +
                                    " | Projet :" + matiere.getHeure_projet() +
                                    " | CT :" + matiere.getPoids_CT() +
                                    " | Type du CT :" + matiere.getType_CT() +
                                    " | Poid :" + matiere.getPoidMatieres());
                        }
                    }
                }
            }

        }
    }
}
