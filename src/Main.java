public class Main {
    public static void main(String[] args) {
        Matiere langageC = new Matiere("LangageC", 10, 0, 14, 7, 1, "E",0,null, 1);
        langageC.setLabelMatiere("Math");
        //System.out.println("L'UE de RO est composé de " + langageC.getLabelMatiere());

        ParseurC fichier = new ParseurC();
        fichier.parser();

    }
}