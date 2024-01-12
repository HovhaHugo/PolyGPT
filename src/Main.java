import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;

public class Main {
    public static void main(String[] args) throws IOException {
        //We create the variable for the all code that will help us to identify if everything is right
        ArrayList<String> anneeValide = new ArrayList<>();
        anneeValide.add("3A");
        anneeValide.add("4A");
        anneeValide.add("5A");

        //Here it's the part were we create the scanner (for later) and create the file we will write on.
        Scanner sc = new Scanner(System.in);
        PrintWriter writer = new PrintWriter("mon-fichier.txt", StandardCharsets.UTF_8);
        writer.println("Peut tu me faire un fichier json de question/réponse pour les données suivante ? \n \n");

        //We try to see if the file exist and we bring it.
        System.out.println("\n --------------------------------------------------");
        System.out.println("Bienvenue dans Descpryt'Excel, veuillez saisir le chemin d'accès à la maquette : ");
        String path = sc.nextLine();
        System.out.println("Veuillez aussi saisir le nom du fichier : ");
        String name = sc.nextLine();
        //If the file doesn't exist we send an error message
        File file = new File(path+"/"+name+".xlsx");
        while(!file.isFile()){
            System.out.println("Le fichier n'existe pas, vous devez vous être tromper de fichier ou de dossier, veuillez recommencer.");
            System.out.println("Veuillez saisir chemin d'accès à la maquette : ");
            path = sc.nextLine();
            System.out.println("Veuillez aussi saisir le nom du fichier : ");
            name = sc.nextLine();
            file = new File(path+"/"+name);
        }

        //Now, we will focus on the years and the numbers of sheets in the file.
        System.out.println("\n --------------------------------------------------");
        System.out.println("Le fichier concernes combien d'année ? [1,2,3]: ");
        int years = sc.nextInt();
        boolean between1and3 = false;

        //While the numbers of years inform is out of 3, we let the user try again.
        while(!between1and3) {
            Scanner scan = new Scanner(System.in);
            if (years > 3){
                System.out.println("Chiffre en dehors de la demande, merci de ressaisir un chiffre entre 1 et 3");
                System.out.println("Le fichier concernes combien d'année ? [1,2,3]: ");
                years = scan.nextInt();
            }else{
                between1and3 = true;
            }
        }

        String schollyear;
        int toeic;
        //We create another scanner because the old one might bug sometime.
        Scanner scan = new Scanner(System.in);
        //Now that the number of years is between 1 and 3, we try to parse the year.
        switch (years) {
            case 1:
                System.out.println("Quelle est l'année concerncer ? [3A, 4A, 5A]");
                schollyear = scan.nextLine();
                System.out.println("Pour valider cette année, il est nécessaire d'avoir un score toeic de : ");
                toeic = scan.nextInt();
                while(!anneeValide.contains(schollyear)){
                    System.out.println("L'année renseigner n'est pas dans les années pris en charge, merci de ressaisir l'année concerner et réessayer");
                    System.out.println("Le fichier concernes l'année ? [3A, 4A, 5A]: ");
                    schollyear = scan.nextLine();
                    System.out.println("Pour valider cette année, il est nécessaire d'avoir un score toeic de : ");
                    toeic = scan.nextInt();
                }
                parsingAnnee(writer, file, schollyear,toeic);

                break;
            case 2:
                int i = 1;
                while(i <= 2){
                    switch (i){
                        case 1 :
                            System.out.println("Quelle est la 1er année concerncer ? [3A, 4A, 5A]");
                            break;
                        case 2:
                            System.out.println("Quelle est la 2eme année concerncer ? [3A, 4A, 5A]");
                            break;
                    }
                    schollyear = scan.nextLine();
                    while(!anneeValide.contains(schollyear)){
                        System.out.println("L'année renseigner n'est pas dans les années pris en charge, merci de ressaisir l'année concerner et réessayer");
                        System.out.println("Le fichier concernes l'année ? [3A,4A,5A]: ");
                        schollyear = scan.nextLine();
                    }
                    parsingAnnee(writer, file, schollyear,0);
                    i++;
                }
                System.out.println("Merci bien, nous allon maintenant traduire votre fichier.");
                break;
            case 3:
                for(int j = 1; j < 4; j++){
                    if(j == 1){
                            System.out.println("Pour valider la 1ere année, il est nécessaire d'avoir un score toeic de : ");
                            toeic = scan.nextInt();
                            parsingAnnee(writer, file, "3A", toeic);
                    }else{
                        if(j == 2){
                            System.out.println("Pour valider la " + j + "eme année, il est nécessaire d'avoir un score toeic de : ");
                            toeic = scan.nextInt();
                            parsingAnnee(writer, file, "4A", toeic);
                        }else{
                            System.out.println("Pour valider la " + j + "eme année, il est nécessaire d'avoir un score toeic de : ");
                            toeic = scan.nextInt();
                            parsingAnnee(writer, file, "5A", toeic);
                        }
                    }
                }
                System.out.println("Merci bien, nous allon maintenant traduire votre fichier.");
                break;
        }
        System.out.println("Votre maquette à bien été transformer, veuillez la trouvez dans le dossier : ");
        writer.close();
    }

    public static void parsingAnnee(PrintWriter writer, File file, String strAnnee, int toeic) throws IOException {
        long now = System.currentTimeMillis();

        //Creation des variables
        String str1 = null;
        String str2 = null;

        switch(strAnnee){
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
        Annee annee = new Annee();
        annee.setSemestres(str1, str2, file);
        annee.setLabel(strAnnee);
        annee.setToiec(toeic);

        annee.afficherAnnee(writer);

        long then = System.currentTimeMillis();

        float time = then - now;
        System.out.println("Ont mets "+time+"milli sec à faire le paring de la maquette");
    }
}