import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ParserH {
    /**
     * Function that will parse an Excel sheets into information in natural language
     * @param file the Excel sheets we will translate
     * @param index the sheets we will translate
     * @return An arraylist of UE that contains every UE for a semester
     * @throws IOException
     */
    public ArrayList<UE> parseurMaquette(File file, String index) throws IOException {
        //obtaining input bytes from a file
        FileInputStream fis=new FileInputStream(file);

        //creating workbook instance that refers to .xls file
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        //We select the right sheets in the Excel file
        XSSFSheet ws = workbook.getSheet(index);

        ArrayList<UE> listeUE = new ArrayList<UE>();
        ArrayList<Matiere> listeMatieres = new ArrayList<Matiere>();
        int i = 1;

        UE oldUE = new UE();
        oldUE.setLabel("");
        UE newUE = new UE();
        int ECTS = 0;
        int NewECTS = 0;
        boolean nouveau = false;
        boolean premier = true;

        while (i <= ws.getLastRowNum())
        {
            XSSFRow row = ws.getRow(i);
            //Row row = rowIterator.next();

            String nomMatiere = null;
            int heureCours = 0;
            int heureTD = 0;
            int heureTP = 0;
            int heureProjet = 0;
            float CC = 0;
            String TypeCC = null;
            float CT = 0;
            String TypeCT = null;
            float poid = 0;

            //For each row, iterate through all the columns
            Iterator<Cell> cellIterator = row.cellIterator();

            int j = 1;
            i++;

            while (cellIterator.hasNext())
            {
                Cell cell = cellIterator.next();
                if(i>4){
                    //Gestion des UE :
                    if(j == 2 && cell.getCellType() == Cell.CELL_TYPE_STRING){
                        String res = cell.getStringCellValue().substring(0, 2);

                        //Si le texte contient UE, on creer notre UE
                        if((Objects.equals(res, "UE") || Objects.equals(res, "SO") || Objects.equals(res, "ST")) && !premier){
                            nouveau = true;
                            newUE.setLabel(cell.getStringCellValue());
                        }else{
                            if((Objects.equals(res, "UE") || Objects.equals(res, "SO") || Objects.equals(res, "ST")) && premier){
                                premier = false;
                                oldUE.setLabel(cell.getStringCellValue());
                            }
                        }
                        if(Objects.equals(res, "Op")){
                            oldUE.setDescriptionsEU(cell.getStringCellValue());
                        }
                    }

                    //Gestion des matières
                    switch(j){
                        case 3:
                            if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                                if(!cell.getStringCellValue().contains("validation")){
                                    nomMatiere = cell.getStringCellValue();
                                }
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
                                CC = (float)cell.getNumericCellValue();
                            }
                            break;
                        case 9:
                            if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                                TypeCC = cell.getStringCellValue();
                            }
                            break;
                        case 10:
                            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                CT = (float)cell.getNumericCellValue();
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
                        case 13:
                            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC && nouveau){
                                NewECTS = (int)cell.getNumericCellValue();
                            }else{
                                if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                    ECTS = (int)cell.getNumericCellValue();
                                }
                            }
                            break;
                    }

                }
                j++;
            }
            //Si on as pas de nom de matière, on l'ajoute à la liste des matières.
            if(nomMatiere != null){
                Matiere newMatiere = new Matiere(nomMatiere,heureTD,heureTP,heureCours,CC, TypeCC, CT, TypeCT, poid);
                listeMatieres.add(newMatiere);
            }

            if(nouveau){
                ArrayList<Matiere> listeMatieresUE = new ArrayList<Matiere>(listeMatieres);
                listeMatieres.clear();
                oldUE.setListMatiere(listeMatieresUE);
                oldUE.setECTS(ECTS);

                UE nouvelleUE = new UE(oldUE.getLabel(), oldUE.getECTS(), oldUE.getListMatiere());
                if(oldUE.getDescriptionsEU() != null){
                    nouvelleUE.setDescriptionsEU(oldUE.getDescriptionsEU());
                    oldUE.setDescriptionsEU(null);
                }

                ECTS = NewECTS;
                listeUE.add(nouvelleUE);
                String label = newUE.getLabel();
                oldUE.setLabel(label);
                nouveau = false;
            }

            if(i==ws.getLastRowNum()){
                ArrayList<Matiere> listeMatieresUE = new ArrayList<Matiere>(listeMatieres);
                listeMatieres.clear();
                oldUE.setListMatiere(listeMatieresUE);
                oldUE.setECTS(ECTS);

                UE nouvelleUE = new UE(oldUE.getLabel(), oldUE.getECTS(), oldUE.getListMatiere());
                listeUE.add(nouvelleUE);
            }
        }
        fis.close();
        return listeUE;
    }
}
