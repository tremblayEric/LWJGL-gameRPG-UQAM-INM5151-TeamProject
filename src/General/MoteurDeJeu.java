/*
 * Copyright 2012 Eric Tremblay, Jean-Francois Elie, Ricardo Solon.
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

import Entite.Individu;
import General.Combat;
import Entite.Personnage;
import Entite.Monstre;
import Objets.Arme;
import Objets.Armure;
import Objets.Potion;
import Tableau.Carte;
import Tableau.Case;
import Tableau.FenetreDonjon3d;
//import Tableau.FrameOpenGl;
import java.io.IOException;


public class MoteurDeJeu {
           
        FenetreDonjon3d moteur3d;
        Carte laCarte;
        private Personnage joueur; 
        private Monstre monstre;
        FenetreCombat fCombat;
        float orientation = 90.0f; //Orientation du joueur dans la fenetre 3D
        float positionDansCarte[];
        private FenetreInventaire fInventaire;
               
        
    //MoteurDeJeu(Personnage joueur){
    MoteurDeJeu(Carte uneCarte){
        
        laCarte = uneCarte;
        initialiserPartie();
        
        positionDansCarte = new float[3];
        positionDansCarte[0] =-1.5f;
        positionDansCarte[1] =-0.5f;
        positionDansCarte[2] =0.5f;
        
        deroulementDeLaPartie();
        
    }
    
    private void initialiserPartie(){

        joueur = new Personnage("Tom", 12, 12, 8, 50);        
        //Arme uneArme = new Arme("Couteau", 5, 0.25);
        Arme uneArme = new Arme("Épée longue", 10, 0.25);
        Arme uneArme2 = new Arme("Gourdin", 5, 0.55);
        Arme uneArme3 = new Arme("Glaive à deux mains", 14, -0.10);     
        Armure uneArmure = new Armure("Cuirasse d'écailles", 2, 0.20);    
        Armure uneArmure2 = new Armure("Armure de cuir", 1, 0.25);    

        joueur.getInventaire().ajouterArme(uneArme);
        joueur.getInventaire().ajouterArme(uneArme2);
        joueur.getInventaire().ajouterArme(uneArme3);
        joueur.getInventaire().equiperArme(0);
        
        joueur.getInventaire().ajouterArmure(uneArmure);
        joueur.getInventaire().ajouterArmure(uneArmure2);     
        joueur.getInventaire().equiperArmure(0);
        
        joueur.getInventaire().ajouterPotion(new Potion(20));
        joueur.getInventaire().ajouterPotion(new Potion(20));
        joueur.getInventaire().ajouterPotion(new Potion(20)); 

    }

    private void deroulementDeLaPartie(){
        
        if(laCarte != null){

            moteur3d = new FenetreDonjon3d(laCarte, this);
            moteur3d.demmarageOpenGl();           

        }
            
    }
    
    
    
    public void setPositionDansCarte(float[] positionActuelle){
        positionDansCarte[0] = positionActuelle[0];
        positionDansCarte[1] = positionActuelle[1];
        positionDansCarte[2] = positionActuelle[2];
    }
    
    public float[] getPositionDansCarte(){
        return positionDansCarte;
    }
    
    
    public void setOrientation(float orientationActuelle){
        orientation = orientationActuelle;
    }
    
    public float getOrientation(){
        return orientation;
    }
    
    public int getPointsVieActuelsJoueur(){
        return joueur.getPointsDeVieActuels();
    }
    
    public int getPointsVieTotalJoueur(){
        return joueur.getPointsDeVieTotaux();
    }
    
    public int getNbPotions(){
        return joueur.getInventaire().getNbPotions();
    }
    
    //true = potion utilisee avec success
    public boolean utiliserPotion(){
        
        if(joueur.getInventaire().getNbPotions() > 0){
            
            joueur.setPointsDeVieActuels( joueur.getPointsDeVieActuels() + joueur.getInventaire().utiliserPotion() ) ;

            if(joueur.getPointsDeVieActuels() > joueur.getPointsDeVieTotaux()){
                joueur.setPointsDeVieActuels(joueur.getPointsDeVieTotaux());
            }            
            return true;
        
        }else{
            return false;
        }
    }
    
    public void finDuCombat(int resultat){
        
        fCombat.setVisible(false);
        fCombat.dispose();
        joueur.ajouterExperience(50);
        deroulementDeLaPartie();
        
    }
    
    //Demarre un thread de combat
    public void combat(Case uneCase) throws IOException{

         fCombat = new FenetreCombat(joueur, uneCase.getObjetMonstre(),this);
         uneCase.setMonstre(false);
        
        //Combat leCombat = new Combat();
        // leCombat.start();       
         
    }
    
    public void gestionInventaire(){
        
        fInventaire = new FenetreInventaire(joueur, this);
    }
    
    public void finGestionInventaire(){
        
        fInventaire.setVisible(false);
        fInventaire.dispose();
        deroulementDeLaPartie();
    }
}