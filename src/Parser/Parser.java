package Parser;

import java.io.File;
import java.io.IOException;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

/**
 * Class Parser
 * contain all the function that will parse an Excel file into a text file
 */
public class Parser {
    /**
     * Function that will parse an Excel sheets into a Semestre with all the UE and the school subject.
     * @param file the Excel sheets we will translate
     * @param index the sheets we will translate
     * @return An arraylist of Parser.UE that contains every Parser.UE for a semester
     * @throws IOException Will throw exception if something went wrong
     */
    public ArrayList<UE> parseurSemestre(File file, String index) throws IOException {
        //obtaining input bytes from a file
        FileInputStream fis=new FileInputStream(file);

        //creating workbook instance that refers to .xls file
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        //We select the right sheets in the Excel file
        XSSFSheet ws = workbook.getSheet(index);

        ArrayList<UE> listeUE = new ArrayList<UE>();
        ArrayList<Matiere> listeMatieres = new ArrayList<Matiere>();
        int rowNum = 0;

        //Creation of the variable for a UE
        UE oldUE = new UE();
        oldUE.setLabel("");
        UE newUE = new UE();
        int ECTS = 0;
        int NewECTS = 0;
        boolean nouveau = false;
        boolean premier = true;

        //While we are not at the last line of the Excel sheet we try to translate everything
        while (rowNum <= ws.getLastRowNum())
        {
            XSSFRow row = ws.getRow(rowNum);

            //Creation of the variable for a school subject
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

            int cellNum = 0;
            rowNum++;

            //Will we are not at the last cell of the line, we try to get all the information
            while (cellNum <= row.getLastCellNum()){
                XSSFCell cell = row.getCell(cellNum);

                //After the explanation of how the file is set up for human being, we translate the file
                if(rowNum>5 && cell != null){
                    //We save information about UE if the B column have value
                    if(cellNum == 1 && cell.getCellType() == Cell.CELL_TYPE_STRING){
                        String res = cell.getStringCellValue().substring(0, 2);

                        //If it's a UE, a Soutien or a Stage, we save it,
                        if((Objects.equals(res, "UE") || Objects.equals(res, "SO") || Objects.equals(res, "ST")) && !premier){
                            nouveau = true;
                            newUE.setLabel(cell.getStringCellValue());
                        }else{
                            if((Objects.equals(res, "UE") || Objects.equals(res, "SO") || Objects.equals(res, "ST")) && premier){
                                premier = false;
                                oldUE.setLabel(cell.getStringCellValue());
                            }
                        }
                        //If we have a text that talk about option, we set up this information in the description of the UE
                        if(Objects.equals(res, "Op")){
                            oldUE.setDescriptionsEU(cell.getStringCellValue());
                        }
                    }

                    // We translate the information from the C column to the last column of the board into their correct
                    // place
                    switch(cellNum){
                        //Column C : Name of the subject
                        case 2:
                            if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                                if(!cell.getStringCellValue().contains("validation")){
                                    nomMatiere = cell.getStringCellValue();
                                }
                            }
                            break;
                        //Column D : Amount of CM time in a subject
                        case 3:
                            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                heureCours = (int)cell.getNumericCellValue();
                            }
                            break;
                        //Column E : Amount of TD time in a subject
                        case 4:
                            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                heureTD = (int)cell.getNumericCellValue();
                            }
                            break;
                        //Column F : Amount of TP time in a subject
                        case 5:
                            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                heureTP = (int)cell.getNumericCellValue();
                            }
                            break;
                        //Column G : Amount of project time in a subject
                        case 6:
                            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                heureProjet = (int)cell.getNumericCellValue();
                            }
                            break;
                        //Column H : The weight of the CC in the subject
                        case 7:
                            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                CC = (float)cell.getNumericCellValue();
                            }
                            break;
                        //Column I : The type of CC (Oral or Ecrit or both) in the subject
                        case 8:
                            if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                                TypeCC = cell.getStringCellValue();
                            }
                            break;
                        //Column J : The weight of the CT in the subject
                        case 9:
                            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                CT = (float)cell.getNumericCellValue();
                            }
                            break;
                        //Column K : The type of CC (Oral or Ecrit or both) in the subject
                        case 10:
                            if(cell.getCellType() == Cell.CELL_TYPE_STRING){
                                TypeCT = cell.getStringCellValue();
                            }
                            break;
                        //Column L : The weight of the subject in the UE
                        case 11:
                            if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
                                poid = (float)cell.getNumericCellValue()*100;
                            }
                            break;
                        //Column M : The ECTS (european norm) for the subject
                        case 12:
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
                cellNum++;
            }
            // If we don't have subject name, we save everything that we did until now (it's the little gap between 2 UE
            // in the Excel file)
            if(nomMatiere != null){
                Matiere newMatiere = new Matiere(nomMatiere,heureTD,heureTP,heureProjet,heureCours,CC, TypeCC, CT, TypeCT, poid);
                listeMatieres.add(newMatiere);
            }

            // If we find a new UE (we have some text in the B column), we save the old UE and prepare the new one
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

            //If we are at the end of the sheet, we save everything we did so far
            if(rowNum==ws.getLastRowNum()){
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

    /**
     * Function that will translate the Excel sheets into Semester, then save it into a year to be able to print a year
     * into our text file
     * @param writer The PrintWriter variable that will help us print everything into our texte file.
     * @param file The Excel file
     * @param strAnnee The String value of the current year we try to translate.
     * @param toeic The amount of TOEIC point we need to pass this year
     * @throws IOException Will trhow exception if the file doesn't exist.
     */
    public static void parsingAnnee(PrintWriter writer, File file, String strAnnee, int toeic) throws IOException {
        //Creation des variables
        String str1 = null;
        String str2 = null;

        //We select the right name for the index depending on the school year
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
        //We create a new year with all the information needed, and we print it into the text file.
        Annee annee = new Annee();
        annee.setSemestres(str1, str2, file);
        annee.setLabel(strAnnee);
        annee.setToiec(toeic);

        annee.afficherAnnee(writer);
    }
}
