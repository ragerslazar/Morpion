import java.util.Scanner;
public class Main {
    public static void afficherTableau(String[][] tableau) {
        for (int i = 0; i < tableau.length; i++) {
            for (int j = 0; j < tableau.length; j++) {
                System.out.print(tableau[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean verificationGagnant(String[][] pTableau, String pSymbole) {
        boolean winner = false;
        for (int i = 0; i < pTableau.length; i++) {
            //Vérification lignes
            if (pTableau[i][0].equals(pSymbole) && pTableau[i][1].equals(pSymbole) && pTableau[i][2].equals(pSymbole)) {
                winner = true;
            }
            //Vérification colonnes
            else if (pTableau[0][i].equals(pSymbole) && pTableau[1][i].equals(pSymbole) && pTableau[2][i].equals(pSymbole)) {
                winner = true;
            }
            //Vérification diagonnales
            else if (pTableau[0][0].equals(pSymbole) && pTableau[1][1].equals(pSymbole) && pTableau[2][2].equals(pSymbole)) {
                winner = true;
            } else if (pTableau[2][0].equals(pSymbole) && pTableau[1][1].equals(pSymbole) && pTableau[0][2].equals(pSymbole)) {
                winner = true;
            }
        }
        return (winner);
    }

    public static void main(String[] args) {
        String[][] morpionArray = new String[3][3]; //Ligne - Colonne
        Scanner scanner = new Scanner(System.in);
        int manches = 1;
        for (int i = 0; i != morpionArray.length; i++) {
            for (int j = 0; j != morpionArray[i].length; j++) {
                morpionArray[i][j] = ".";
            }
        }
        System.out.println("Choisissez votre symbole: o / x");
        String symbole = scanner.next();
        if (symbole.equalsIgnoreCase("o")) {
            System.out.println("Le joueur 1 sera le symbole " + symbole + ". Le joueur 2 aura le symbole x.");
        } else {
            System.out.println("Le joueur 1 sera le symbole " + symbole + ". Le joueur 2 aura le symbole o.");
        }

        int numJoueur = 1;
        while (manches <= 9) {
            System.out.println("\nManche " + manches);
            System.out.println("Joueur " + numJoueur + " à toi de jouer. " + symbole + "\n");
            afficherTableau(morpionArray);
            System.out.println("\nCoordonée X:");
            byte coX = scanner.nextByte();
            System.out.println("Coordonée Y:");
            byte coY = scanner.nextByte();

            while (morpionArray[coX-1][coY-1].equalsIgnoreCase("o") || morpionArray[coX-1][coY-1].equalsIgnoreCase("x")) {
                System.out.println("Emplacement déjà occupé !");
                System.out.println("\nCoordonée X:");
                coX = scanner.nextByte();
                System.out.println("Coordonée Y:");
                coY = scanner.nextByte();
            }
            morpionArray[coX-1][coY-1] = symbole;
            afficherTableau(morpionArray);
            if (verificationGagnant(morpionArray, symbole)) {
                System.out.println("Le joueur " + numJoueur + " gagne ! " + symbole);
                break;
            }

            switch (numJoueur) {
                case 1:
                    numJoueur = 2;
                    break;
                case 2:
                    numJoueur = 1;
                    break;
                default:
                    numJoueur = 1;
                    break;
            }

            switch (symbole) {
                case "x":
                    symbole = "o";
                    break;
                case "o":
                    symbole = "x";
                    break;
            }
            if (manches == 9 && !verificationGagnant(morpionArray, symbole)) {
                System.out.println("\nManche 9 terminée. Aucun joueur n'a gagné.");
            }
            manches++;
        }
    }
}