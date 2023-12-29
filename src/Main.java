import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        PrintWriter writer = new PrintWriter("mon-fichier.txt", "UTF-8");

        //Informations sur le fichier
        System.out.println("\n --------------------------------------------------");
        System.out.println("Bienvenue dans Descpryt'Excel, veuillez saisir le chemin d'accès à la maquette : ");
        String path = sc.nextLine();
        System.out.println("Veuillez aussi saisir le nom du fichier : ");
        String name = sc.nextLine();
        System.out.println("Merci bien, nous allon maintenant traduire votre fichier.");

        //Informations sur les années
        System.out.println("\n --------------------------------------------------");
        System.out.println("Le fichier concernes combien d'année ? [1,2,3]: ");
        int years = sc.nextInt();
        String schollyear;
        boolean valide = false;
        while(valide == false){
            Scanner scan = new Scanner(System.in);
            switch (years){
                case 1:
                    valide = true;
                    System.out.println("Quelle est l'année concerncer ? [3A, 4A, 5A]");
                    schollyear = scan.nextLine();
                    switch (schollyear){
                        case "3A","4A", "5A":
                            parsingAnnee(writer, path, name, schollyear);
                            break;
                        default:
                            System.out.println("L'année renseigner n'est pas dans les années pris en charge, merci de ressaisir l'année concerner et réessayer");
                            System.out.println("Le fichier concernes l'année ? [3A,4A,5A]: ");
                            years = scan.nextInt();
                            break;
                    }
                    break;
                case 2:
                    valide = true;
                    System.out.println("Quelle est la 1er année concerncer ? [3A, 4A, 5A]");
                    schollyear = scan.nextLine();
                    parsingAnnee(writer,path, name, schollyear);
                    System.out.println("Quelle est la 2eme année concerncer ? [3A, 4A, 5A]");
                    schollyear = scan.nextLine();
                    parsingAnnee(writer, path, name, schollyear);
                    System.out.println("Merci bien, nous allon maintenant traduire votre fichier.");
                    break;
                case 3:
                    valide = true;
                    System.out.println("Merci bien, nous allon maintenant traduire votre fichier.");
                    parsingAnnee(writer, path, name, "3A");
                    parsingAnnee(writer, path, name, "4A");
                    parsingAnnee(writer, path, name, "5A");
                    break;
                default:
                    System.out.println("Chiffre en dehors de la demande, merci de ressaisir un chiffre entre 1 et 3");
                    System.out.println("Le fichier concernes combien d'année ? [1,2,3]: ");
                    years = scan.nextInt();
                    break;
            }
            writer.close();
        }
    }

    public static void parsingAnnee(PrintWriter writer, String path, String name, String annee) throws IOException {
        //Creation des variables
        ParserH parser = new ParserH();
        ArrayList<UE> listeUES1 ;
        ArrayList<UE> listeUES2 ;
        Semestre semestre1;
        Semestre semestre2;
        String str1 = null;
        String str2 = null;

        switch(annee){
            case "3A":
                str1 = "S5";
                str2 = "S6";
                break;
            case "4A":
                str1 = "S7";
                str2 = "S8";
                break;
            case "5A":
                str1 = "S9";
                str2 = "S10";
                break;
        }

        System.out.println("\n --------------------------------------------------");
        writer.println("L'annee "+annee+" est contistuer des semestre suivant : ");
        writer.println("1er semestre : ");
        System.out.println("Année : "+annee);
        listeUES1 = parser.parseurMaquette(path, name,str1);
        semestre1 = new Semestre();
        semestre1.setUniteEnseignement(listeUES1);
        semestre1.afficherSemestre(writer);

        System.out.println("\n --------------------------------------------------");
        writer.println("\n --------------------------------------------------\n");
        writer.println("2ème semestre : ");
        System.out.println("2ème semestre : ");
        listeUES2 = parser.parseurMaquette(path, name,str2);
        semestre2 = new Semestre();
        semestre2.setUniteEnseignement(listeUES2);
        semestre2.afficherSemestre(writer);
        writer.println("\n --------------------------------------------------\n");
        System.out.println("\n --------------------------------------------------\n");
        writer.println("");
    }
}