import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Objects;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ParserH {
    public void afficherExcel() throws IOException   {
        //obtaining input bytes from a file
        FileInputStream fis=new FileInputStream(new File("/Users/hugohovhannessian/Hugo/Etude_Sup/Polytech/DI4/S7/Projet/S5.xlsx"));
        //creating workbook instance that refers to .xls file
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        XSSFSheet ws = workbook.getSheetAt(0);

        ArrayList<UE> listeUE = new ArrayList<UE>();
        ArrayList<Matiere> listeMatieres = new ArrayList<Matiere>();


        Iterator<Row> rowIterator = ws.iterator();
        int i = 1;

        UE oldUE = new UE();
        oldUE.setLabel("");
        UE newUE = new UE();

        boolean nouveau = false;
        boolean premier = true;

        while (rowIterator.hasNext() && i<=47)
        {
            //System.out.print(i + "|");
            Row row = rowIterator.next();

            String nomMatiere = null;
            int heureCours = 0;
            int heureTD = 0;
            int heureTP = 0;
            int heureProjet = 0;
            int CC = 0;
            String TypeCC = null;
            int CT = 0;
            String TypeCT = null;
            float poid = 0;

            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            int j = 1;

            i++;

            while (cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();

                if(i>=13){

                    //Gestion des UE :
                    //Pour chaque ligne avec une valeur textuel en colonnes 2, on creer un UE
                    if(j == 2 && cell.getCellType() == Cell.CELL_TYPE_STRING){
                        String res = cell.getStringCellValue().substring(0, 2);

                        //Si le texte contient UE, on creer notre UE
                        if(Objects.equals(res, "UE") && !premier){
                            nouveau = true;
                            newUE.setLabel(cell.getStringCellValue());
                        }else{
                            if(premier){
                                premier = false;
                                oldUE.setLabel(cell.getStringCellValue());
                            }
                        }
                    }

                    //Gestion des matières
                    switch(j){
                        case 3:
                            if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                                nomMatiere = cell.getStringCellValue();
                            }
                            break;
                        case 4:
                            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                heureCours = (int)cell.getNumericCellValue();
                            }
                            break;
                        case 5:
                            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                heureTD = (int)cell.getNumericCellValue();
                            }
                            break;
                        case 6:
                            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                heureTP = (int)cell.getNumericCellValue();
                            }
                            break;
                        case 7:
                            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                heureProjet = (int)cell.getNumericCellValue();
                            }
                            break;
                        case 8:
                            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                CC = (int)cell.getNumericCellValue();
                            }
                            break;
                        case 9:
                            if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                                TypeCC = cell.getStringCellValue();
                            }
                            break;
                        case 10:
                            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                CT = (int)cell.getNumericCellValue();
                            }
                            break;
                        case 11:
                            if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                                TypeCT = cell.getStringCellValue();
                            }
                            break;
                        case 12:
                            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                poid = (float)cell.getNumericCellValue()*100;
                            }
                            break;
                    }

                }

                j++;

            }

            //Si on as pas de nom de matière, on l'ajoute à la liste des matières.
            if(nomMatiere != null){
                Matiere newMatiere = new Matiere(nomMatiere,heureTD,heureTP,heureCours,poid);
                listeMatieres.add(newMatiere);
            }

            if(nouveau){
                ArrayList<Matiere> listeMatieresUE = new ArrayList<Matiere>(listeMatieres);
                listeMatieres.clear();
                oldUE.setListMatiere(listeMatieresUE);

                UE nouvelleUE = new UE(oldUE.getLabel(), oldUE.getListMatiere());

                listeUE.add(nouvelleUE);
                String label = newUE.getLabel();
                oldUE.setLabel(label);
                nouveau = false;
            }

            if(i==47){
                ArrayList<Matiere> listeMatieresUE = new ArrayList<Matiere>(listeMatieres);
                listeMatieres.clear();
                oldUE.setListMatiere(listeMatieresUE);
                UE nouvelleUE = new UE(oldUE.getLabel(), oldUE.getListMatiere());
                listeUE.add(nouvelleUE);
            }

        }
        fis.close();


        //Afficher les UE et matière.
        for(int u =0; u < listeUE.size(); u++) {
            System.out.println("L'UE n°" + u + " est l'UE de :" + listeUE.get(u).getLabel());
            ArrayList<Matiere> listeMatieresUE = new ArrayList<Matiere>(listeUE.get(u).getListMatiere());
            for (int k = 0; k < listeMatieresUE.size(); k++) {
                System.out.println("- " + listeMatieresUE.get(k).getLabelMatiere() +
                        " | CM :" + listeMatieresUE.get(k).getHeure_CM() +
                        " | TD :" + listeMatieresUE.get(k).getHeure_TD() +
                        " | TP :" + listeMatieresUE.get(k).getHeure_TP() +
                        " | Poid :" + listeMatieresUE.get(k).getPoidMatiere());
            }

        }
    }
}
