import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParseurC {

    public void parser() {

        List<UE> unitesEnseignements = new ArrayList<>(); //Liste pour stocker les objets UE
        Map<String, List<Matiere>> matieresParUE = new HashMap<>();

        try {
            FileInputStream file = new FileInputStream(new File("C:/Cours/4A S7/Projet PolyGPT/Maquettes/S5.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            // Parcourir toutes les feuilles du classeur
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                //##########################################################################################################
                // Boucle pour parcourir toutes les cellules de la colonne
                List<Matiere> listMatiereOld = new ArrayList<>();

                for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {

                    XSSFRow row = sheet.getRow(i); //Recuperer le numero de la ligne
                    String nomUE = null;

                    double ECTS = 0;

                    //Nom UE
                    if (row != null && i > 4) {
                        XSSFCell cellUE = row.getCell(1); // Récupérer la cellule à la colonne 1

                        if (cellUE != null && cellUE.getCellType() == CellType.STRING) {

                            if (cellUE.getStringCellValue().equals("1 au choix")) {
                                //System.out.print(nomUE);
                            } else {
                                nomUE = cellUE.getStringCellValue();
                            }
                        }
                    }

                    //ECTS
                    if (row != null && i > 4) {
                        XSSFCell cell2 = row.getCell(12); // Récupérer la cellule à la colonne 12

                        if (cell2 != null && cell2.getCellType() == CellType.NUMERIC) {
                            ECTS = cell2.getNumericCellValue(); // Récupérer le nom de l'unité d'enseignement
                        }
                    }

                    //Matieres
                    if (row != null && i > 5) {
                        XSSFCell cell = row.getCell(2); // Récupérer la cellule à la colonne 2
                        String nomMatiere = null;
                        double heureCM = 0;
                        double heureTD = 0;
                        double heureTP = 0;
                        double heureProjet = 0;
                        double poidsMatiere = 0;
                        double poidsCC = 0;
                        String typeCC = null;
                        double poidsCT = 0;
                        String typeCT = null;
                        double poidsMatierePourcent = 0;

                        String idUE = unitesEnseignements.getLabel();

                        if (cell != null && cell.getCellType() == CellType.STRING) {
                            nomMatiere = cell.getStringCellValue(); // Récupérer le nom de la matière

                            //Switch case pour l'indice colonne

                            // Ajouter la matiere à la liste
                            XSSFCell cellCM = row.getCell(3);
                            heureCM = cellCM.getNumericCellValue();
                            XSSFCell cellTD = row.getCell(4);
                            heureTD = cellTD.getNumericCellValue();
                            XSSFCell cellTP = row.getCell(5);
                            heureTP = cellTP.getNumericCellValue();
                            XSSFCell cellProjet = row.getCell(6);
                            heureProjet = cellProjet.getNumericCellValue();
                            XSSFCell cellPoids = row.getCell(11);
                            poidsMatiere = cellPoids.getNumericCellValue();
                            poidsMatierePourcent = poidsMatiere * 100;


                            XSSFCell cellPoidsCC = row.getCell(7);
                            if (cellPoidsCC != null) {
                                poidsCC = cellPoidsCC.getNumericCellValue();
                            }

                            XSSFCell cellTypeCC = row.getCell(8);
                            typeCC = cellTypeCC.getStringCellValue();
                            XSSFCell cellPoidsCT = row.getCell(9);
                            poidsCT = cellPoidsCT.getNumericCellValue();
                            XSSFCell cellTypeCT = row.getCell(10);
                            typeCT = cellTypeCT.getStringCellValue();

                            Matiere matiere = new Matiere(nomMatiere, heureCM, heureTD, heureTP, heureProjet, poidsCC, typeCC, poidsCT, typeCT, poidsMatierePourcent); // Supposons que les valeurs de note et de crédit sont 0 pour l'instant
                            Matiere MATIERE = new Matiere(nomMatiere, heureCM, heureTD, heureTP, heureProjet, poidsCC, typeCC, poidsCT, typeCT, poidsMatierePourcent); // Supposons que les valeurs de note et de crédit sont 0 pour l'instant

                            listMatiereOld.add(matiere);

                            //System.out.println("Liste des Matières : " + listMatiereOld);

                            if (matieresParUE.containsKey(idUE)) {
                                // Si oui, ajouter la matière à la liste existante
                                matieresParUE.get(idUE).add(MATIERE);
                            } else {
                                // Sinon, créer une nouvelle liste de matières pour cette UE
                                List<Matiere> nouvellesMatieres = new ArrayList<>();
                                nouvellesMatieres.add(MATIERE);
                                matieresParUE.put(idUE, nouvellesMatieres);
                            }


                        }
                    }


                    /*
                    SWITCH CASE MARCHE PAS

                    // Matieres
                    if (row != null && i > 5) {
                        XSSFCell cell = row.getCell(2); // Récupérer la cellule à la colonne 2
                        String nomMatiere = null;
                        double heureCM = 0;
                        double heureTD = 0;
                        double heureTP = 0;
                        double heureProjet = 0;
                        double poidsMatiere = 0;
                        double poidsCC = 0;
                        String typeCC = null;
                        double poidsCT = 0;
                        String typeCT = null;
                        double poidsMatierePourcent = 0;

                        if (cell != null && cell.getCellType() == CellType.STRING) {
                            nomMatiere = cell.getStringCellValue(); // Récupérer le nom de la matière

                            int columnIndex = cell.getColumnIndex();
                            switch (columnIndex) {
                                case 3:
                                    if (cell.getCellType() == CellType.NUMERIC){
                                        heureCM = getCellValue(row.getCell(columnIndex));
                                        break;
                                    }
                                    break;

                                case 4:
                                    heureTD = getCellValue(row.getCell(columnIndex));
                                    break;
                                case 5:
                                    heureTP = getCellValue(row.getCell(columnIndex));
                                    break;
                                case 6:
                                    heureProjet = getCellValue(row.getCell(columnIndex));
                                    break;
                                case 11:
                                    poidsMatiere = getCellValue(row.getCell(columnIndex));
                                    poidsMatierePourcent = poidsMatiere * 100;
                                    break;
                                case 7:
                                    poidsCC = getCellValue(row.getCell(columnIndex));
                                    break;
                                case 8:
                                    typeCC = getCellStringValue(row.getCell(columnIndex));
                                    break;
                                case 9:
                                    poidsCT = getCellValue(row.getCell(columnIndex));
                                    break;
                                case 10:
                                    typeCT = getCellStringValue(row.getCell(columnIndex));
                                    break;
                                default:
                                    // Gérer d'autres cas si nécessaire
                                    break;
                            }

                            Matiere matiere = new Matiere(nomMatiere, heureCM, heureTD, heureTP, heureProjet, poidsCC, typeCC, poidsCT, typeCT, poidsMatierePourcent);
                            // Supposons que les valeurs de note et de crédit sont 0 pour l'instant
                            listMatiereOld.add(matiere);
                        }
                    }
                    */



                    //Creation des objets UE
                    if (nomUE != null) {
                        List<Matiere> listMatiereNew = new ArrayList<Matiere>(listMatiereOld);

                        if (nomUE != nomUE) {
                            UE uniteEnseignement = new UE(nomUE, 0, ECTS, listMatiereNew); // Supposons que les autres valeurs soient vides pour l'instant
                            listMatiereOld.clear();

                            unitesEnseignements.add(uniteEnseignement);
                        }/*
                        if (nomUENouveau.equals("SOUTIEN")) {
                            UE uniteEnseignement = new UE(nomUENouveau, 0, ECTS, listMatiereOld); // Supposons que les autres valeurs soient vides pour l'instant
                            listMatiereOld.clear();

                            unitesEnseignements.add(uniteEnseignement);
                        } else {
                            UE uniteEnseignement = new UE(nomUENouveau, 0, ECTS, listMatiereNew); // Supposons que les autres valeurs soient vides pour l'instant
                            listMatiereOld.clear();

                            unitesEnseignements.add(uniteEnseignement);

                        }*/
                    }
                }
                //##########################################################################################################

                // Fermer le fichier
                file.close();
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Faire ce que vous voulez avec la liste de matières
        // Par exemple, imprimer les matières et leurs notes
        for (UE uniteEnseignement: unitesEnseignements) {
            System.out.print("UE : " + uniteEnseignement.getLabel() +
                        " | ECTS :" + uniteEnseignement.getECTS() + '\n');
            for (Matiere matiere : uniteEnseignement.getListMatiere()) {
                /*System.out.println( "   " + matiere.getLabelMatiere() +
                                " | CM :" + matiere.getHeureCM() +
                                " | TD :" + matiere.getHeureTD() +
                                " | TP :" + matiere.getHeureTP() +
                                " | Projet :" + matiere.getHeureProjet() +
                                " | CC :" + matiere.getPoidsCC() +
                                " | typeCC :" + matiere.getTypeCC() +
                                " | CT :" + matiere.getPoidsCT() +
                                " | typeCT :" + matiere.getTypeCT() +
                                " | Poids :" + matiere.getPoidsMatiere() + "%");*/
                /*System.out.println( "   " + matiere.getLabelMatiere() + "\n" + "           " +
                        " | " + matiere.getHeureCM() +
                        " | " + matiere.getHeureTD() +
                        " | " + matiere.getHeureTP() +
                        " | " + matiere.getHeureProjet() +
                        " | " + matiere.getPoidsCC() +
                        " | " + matiere.getTypeCC() +
                        " | " + matiere.getPoidsCT() +
                        " | " + matiere.getTypeCT() +
                        " | " + matiere.getPoidsMatiere() + "%");*/
               System.out.println( "   " + matiere.getLabelMatiere());
            }
        }
    }
}

//Parser la maquette et ecrire les données parsées dans un fichier txt

// Prochaine etape : ajouter les données dans les variables adaptees de la structure cree
//Pour ajouter par exemple toutes les matieres dans un objet Matiere : il faut ?

//Solution pour associer la bonne liste des matieres au bon UE
// Probleme : la liste Old ajouté au premier UE contient les matieres du dernier UE
// Explication : Les UE sont lu avant les matieres donc la premiere liste des matieres ne peut pas etre enregistrer dans le premier UE
//                  puisqu'il est créé avant la lecture de ses matieres
// Solution lire l'UE, lire les matieres et dès que l'UE change = enregistrer la liste des matieres dans l'ancien UE
// Solution -> faire une Map / faire un algo qui comprend quand on passe d'un UE a un autre