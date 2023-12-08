import java.io.*;
import java.util.*;

public class Perceptron {

    public static double[] appPerceptron(double[] perceptron, double [] exemple,
                                         double sortie, double pas) {


        /* poids */
        for (int i = 0; i <2; i++) {
            perceptron[i] = perceptron[i] + pas * exemple[i] * (sortie - exemple[2]);
        }
        /* seuil */
        perceptron[2] = perceptron[2] - pas * (sortie - exemple[2]);

        return perceptron;
    }

    /**
     *
     * @param perceptron : poids et le seuil
     * @param exemple : entrees + désirée
     * @param pas : pas d'apprentissage (u)
     * @return
     */
    public static  double[] hebb(double[] perceptron, double [] exemple,
                                 double pas) {
	/*
	  mise a jour des poids du perceptron
	  suivant la loi de hebb
	*/
        /* poids */
        for (int i = 0; i <2; i++) {
            perceptron[i] = perceptron[i] + pas * exemple[i] * exemple[2];
        }
        /* seuil */
        perceptron[2] = perceptron[2] - pas * exemple[2];

        return perceptron;
    }

    public static void affichePoids(double[] perceptron) {
        int i;

        for (i=0; i< perceptron.length -1 ; i++)
            System.out.printf("\t w%d =  %2.2f", i, perceptron[i]);
        System.out.printf("\t seuil =  %2.2f\n", perceptron[perceptron.length -1]);

    }


    public static void learn(double[][] exemples, String loi, double pas) {

        /* Deux poids sur les entrees [0][1], un seuil [2] */
        double[] perceptron = {0, 0, 0};

        boolean[] appris = {false, false, false,false};
        /* vrai si tous les exemples passent sans erreur */
        boolean apprentissage = false;


        affichePoids(perceptron);

        while(!apprentissage ) {
            if (loi.equals("hebb")) {
            for (int i = 0; i < 4; i++) {

                    if (appris[i] == false) {
                        double signe = Math.signum(perceptron[0] * exemples[i][0] + perceptron[1] * exemples[i][1] - perceptron[2]);
                        //System.out.println("Signe = " + signe);
                        //System.out.println("Désirée = " + exemples[i][2]);
                        if (signe != exemples[i][2]) {
                            perceptron = hebb(perceptron, exemples[i], pas);
                            //System.out.println("On apprends");
                        } else {
                            appris[i] = true;
                            //System.out.println("on a appruis ");
                        }
                    }
                }
                System.out.println("res = " + appris[0] + " " + appris[1] + " " + appris[2] + " " + appris[3]);
                apprentissage = appris[0] && appris[1] && appris[2] && appris[3];

            }
            else if(loi.equals("perceptron")){
                for (int i = 0; i < 4; i++) {

                    if (appris[i] == false) {
                        double signe = Math.signum(perceptron[0] * exemples[i][0] + perceptron[1] * exemples[i][1] - perceptron[2]);
                        //System.out.println("Signe = " + signe);
                        //System.out.println("Désirée = " + exemples[i][2]);
                        if (signe != exemples[i][2]) {
                            perceptron = appPerceptron(perceptron, exemples[i],signe, pas);
                            //System.out.println("On apprends");
                        } else {
                            appris[i] = true;
                            //System.out.println("on a appruis ");
                        }
                    }
                }
                System.out.println("res = " + appris[0] + " " + appris[1] + " " + appris[2] + " " + appris[3]);
                apprentissage = appris[0] && appris[1] && appris[2] && appris[3];
            }
        }

        System.out.println("Terminé");
        affichePoids(perceptron);

    }

    public static void main(String [] args) throws IOException {
        double[][] tableET = {
                {-1,-1, -1},
                {-1,1,-1},
                {1,-1,-1},
                {1,1,1}
        };
        double[][] tableOU = {
                {-1,-1,-1},
                {-1,1,1},
                {1,-1,1},
                {1,1,1}
        };
        double pas = .005;

        System.out.println("... tableET ...");
        learn(tableET,"hebb", pas);

    }
}
