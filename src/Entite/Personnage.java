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
package Entite;

import Objets.Arme;
import Objets.Armure;


public class Personnage extends Individu {
    
    int niveau = 1;
    int experience = 0;
    int xpProchainNiveau = 100;
    
    
    public Personnage(String unNom, int uneForce, int uneAgilite, int uneResistance, int lesPointsDeVie){
        
        super(unNom, uneForce, uneAgilite, uneResistance, lesPointsDeVie); 
    
    }
    
    
    public void ajouterExperience(int xp){
        
        experience += xp;
        
        if(experience >= xpProchainNiveau){
            
            experience = 0;
            xpProchainNiveau *= 1.5;
            niveau++;
            force++;
            agilite++;
            resistance++;
            pointsDeVieTotaux *= 1.5;
            pointsDeVieActuels = pointsDeVieTotaux;     //Remet le joueur a 100% points de vie.
        }
        
        
    }
    
    public int getXpRestant(){
        return xpProchainNiveau - experience;
    }
    
    public int getNiveau(){
        return niveau;
    }
    
    
}
