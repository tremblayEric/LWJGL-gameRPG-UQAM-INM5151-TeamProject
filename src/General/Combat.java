/*
 * Copyright 2011 Eric Tremblay, Jean-Francois Elie, Ricardo Solon.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package General;

import java.math.*;
import Entite.Individu;
import Entite.Monstre;
import Entite.Personnage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Combat extends Thread {

    Personnage joueur;
    Monstre monstre;
    String attaqueJoueurDebut = "Vous attaquez "; //Vous attaquez Godzilla avec votre mace, vous ratez votre cible!
    String attaqueJoueurDebut2 = " avec votre ";
    String attaqueJoueurRate = " et vous RATEZ votre cible!";
    String attaqueJoueurSucces1 = ". Vous infligez "; //Vous infligez 10 points de dégats!
    String attaqueJoueurSucces2 = " de dégâts!";
    String attaqueJoueurDoubleDmg = ". Votre attaque ébranle votre adversaire! Vous faites le double de dégâts!";
    String attaqueMonstreDebut = " vous attaque"; //Godzilla vous attaque et rate sa cible!
    String attaqueMonstreDebut2 = " avec son ";
    String attaqueMonstreRate = " et rate sa cible!";
    String attaqueMonstreSucces1 = ". Vous perdez "; //Vous infligez 10 points de dégats!
    String attaqueMonstreSucces2 = " points de vie!";
    String attaqueMonstreDoubleDmg = ". Vous recevez un coup violent! Vous perdez deux fois plus de points de vie!";
    String joueurAbsorbeDebut = "Votre armure a absorbée ";
    String joueurAbsorbeFin = " points de dégâts!";
    String potionDebut = "Vous utilisez une potion et regargnez ";
    String potionFin = " points de vie!";
    Individu individuAAfficher;
    String texteAAfficher;
    String temp;
    boolean alternatif;
    boolean fixLag;

    Combat(Personnage joueur, Monstre monstre) {

        this.joueur = joueur;
        this.monstre = monstre;
        alternatif = true;

    }

    @Override
    public void run() {

        while (joueur.getPointsDeVieActuels() > 0 && monstre.getPointsDeVieActuels() > 0) {

            try {


                if (alternatif) {
                    alternatif = false;
                    monstre.setPointsDeVieActuels(monstre.getPointsDeVieActuels() - attaquer(joueur, monstre));

                } else {
                    alternatif = true;
                    joueur.setPointsDeVieActuels(joueur.getPointsDeVieActuels() - attaquer(monstre, joueur));

                }
                afficher(texteAAfficher, individuAAfficher);


                Thread.sleep(1800);

            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }


        if (joueur.getPointsDeVieActuels() <= 0) {
            afficher("Vous êtes mort... fin de la partie.", null);
        } else {
            afficher("Vous avez remporté le combat!", null);
        }


        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Combat.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (joueur.getPointsDeVieActuels() > 0) {
            FenetreCombat.finCombat(monstre.getExperience());
        } else {
            System.exit(0);
        }

        this.stop();

    }

    private double calculeDomagesNet(int domageBrut, int domageAbsorbe, double chanceAbsorbtion, int resistance) {

        double resultat = 0.0;
        double temp = Math.random();

        if (chanceAbsorbtion >= temp) {
            resultat = domageBrut - domageAbsorbe - resistance;
        } else {
            resultat = domageBrut - resistance;
        }

        return resultat;

    }

    //Simule un facteur aléatoire dans les résultats
    //Peut ameliorer ou diminuer le resultat par 10% (20% de variance total)
    private double modificationFacteurHasard(double unEvenement) {

        double hasard = 0.9 + (Math.random() * (1.1 - 0.9));
        return unEvenement * hasard;

    }

    //20% de chance de faire des doubles domages
    private boolean verifieDoubleDomage() {

        double temp = Math.random();

        if (temp > 0.8) {
            return true;
        } else {
            return false;
        }

    }

    private int attaquer(Individu attaquant, Individu defenseur) {

        int pointsViePerdues = 0;
        double chanceTouche = (1.0 + attaquant.getInventaire().getArmeEquipee().getAgilite()) * attaquant.getAgilite();
        double chanceEsquive = defenseur.getAgilite();
        int domageBrut = attaquant.getForce() + attaquant.getInventaire().getArmeEquipee().getDomage();
        boolean doubleDomage = verifieDoubleDomage();
        double domageNet;


        chanceTouche = modificationFacteurHasard(chanceTouche);
        chanceEsquive = modificationFacteurHasard(chanceEsquive);

        if (chanceTouche >= chanceEsquive) {

            domageNet = calculeDomagesNet(domageBrut, defenseur.getInventaire().getArmureEquipee().getAbsorbtion(), defenseur.getInventaire().getArmureEquipee().getChanceDAbsorbtion(), defenseur.getResistance());

            if (domageNet > 0) {

                if (doubleDomage) {
                    domageNet *= 2;
                }

                pointsViePerdues = (int) domageNet;

            } else {
                domageNet = 0;
            }

            if (attaquant == joueur) {

                individuAAfficher = joueur;
                temp = attaqueJoueurDebut + defenseur.getNom() + attaqueJoueurDebut2 + attaquant.getInventaire().getArmeEquipee().getNom();
                if (doubleDomage) {
                    texteAAfficher = temp + attaqueJoueurDoubleDmg + attaqueJoueurSucces1 + (int) domageNet + attaqueJoueurSucces2;
                } else {
                    texteAAfficher = temp + attaqueJoueurSucces1 + (int) domageNet + attaqueJoueurSucces2;
                }

            } else {

                individuAAfficher = monstre;
                temp = monstre.getNom() + attaqueMonstreDebut + attaqueMonstreDebut2 + monstre.getInventaire().getArmeEquipee().getNom();
                if (doubleDomage) {
                    texteAAfficher = temp + ". " + attaqueMonstreDoubleDmg + attaqueMonstreSucces1 + (int) domageNet + attaqueMonstreSucces2;

                } else {
                    texteAAfficher = temp + attaqueMonstreSucces1 + (int) domageNet + attaqueMonstreSucces2;
                }

            }

        } else {

            if (attaquant == joueur) {
                individuAAfficher = joueur;
                texteAAfficher = attaqueJoueurDebut + monstre.getNom() + attaqueJoueurDebut2 + joueur.getInventaire().getArmeEquipee().getNom() + attaqueJoueurRate;

            } else {
                individuAAfficher = monstre;
                texteAAfficher = monstre.getNom() + attaqueMonstreDebut + attaqueMonstreDebut2 + monstre.getInventaire().getArmeEquipee().getNom() + attaqueMonstreRate;
            }
        }

        return pointsViePerdues;


    }

    private void afficher(String texte, Individu attaquant) {

        if (texte != null && texte != "") {

            if (attaquant == null) {
                texte = "<font color = #FFFFFF  >" + texte + "</font>";
            } else if (attaquant == joueur) {
                texte = "<font color = #336633 >" + texte + "</font>";
            } else {
                texte = "<font color = #FF0000 >" + texte + "</font>";
            }
            FenetreCombat.ajoutTexteConsole(texte);
        }
    }
}
