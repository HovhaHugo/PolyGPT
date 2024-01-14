
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

import static Parser.Parser.parsingAnnee;

/**
 * Main class that will be used to make the programme works
 */
public class Main {
    static ArrayList<String> anneeValide = new ArrayList<>(){{
        add("3A");
        add("4A");
        add("5A");
    }};
    public static void main(String[] args) throws IOException {

        //Here it's the part were we create the scanner (for later) and create the file we will write on.
        PrintWriter writer = new PrintWriter("nouvelleMaquette.txt", StandardCharsets.UTF_8);
        writer.println("Peut tu me faire un fichier json de question/réponse pour les données suivante ? \n \n");

        System.out.println("\n --------------------------------------------------");
        System.out.println("Bienvenue dans Descpryt'Excel, votre logiciel de traduction de fichier Excel");

        //We try to see if the Excel file exist.
        File file = verificationFileExcel();

        //Now, we will focus on the years and the numbers of sheets in the file.
        Scanner sc = new Scanner(System.in);
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
        //We create another scanner.
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

    /**
     * Function that check if an Excel file exist
     * @return The Excel file
     */
    public static File verificationFileExcel(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Veuillez saisir le chemin d'accès à la maquette ainsi que le nom du fichier : ");
        String pahtFile = sc.nextLine();
        //If the file doesn't exist we send an error message
        File file = new File(pahtFile+".xlsx");
        while(!file.isFile()){
            System.out.println("Le fichier n'existe pas, vous devez vous être tromper de fichier ou de dossier, veuillez recommencer.");
            System.out.println("Veuillez saisir chemin d'accès à la maquette avec le nom du fichier : ");
            pahtFile = sc.nextLine();
            file = new File(pahtFile+".xlsx");
        }
        return file;
    }
}