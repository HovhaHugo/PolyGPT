public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Matiere math = new Matiere();
        math.setLabelMatiere("Math");
        System.out.println("L'UE de RO est composé de "+math.getLabelMatiere());
    }
}