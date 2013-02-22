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
import Objets.Cle;
import Objets.Potion;
import java.util.ArrayList;

public class Inventaire {
   
    private ArrayList<Potion> listesPotions = new ArrayList<Potion>();
    private ArrayList<Cle> listesCles = new ArrayList<Cle>();
    private ArrayList<Arme> listesArmes = new ArrayList<Arme>(); 
    private ArrayList<Armure> listesArmures = new ArrayList<Armure>();
    
    int indexArmeEquipee = -1;
    int indexArmureEquipee = -1;
    int indexPotionEquipee = -1;
    int indexCleEquipee = -1;
    
    
    public Inventaire(){
        
    }
    
    public ArrayList<Potion> getInventairePotion(){
        
        return listesPotions;
    }
    
    public Potion getPotionEquipee(){
        return(listesPotions.get(indexPotionEquipee));
    }
    
    public void ajouterPotion(Potion unePotion){
        listesPotions.add(unePotion);
    }
    
    public int utiliserPotion(){
       
        int resultat = 0;
        
        if(listesPotions.size() > 0){
            resultat = listesPotions.remove(0).getNbPointsVie();
        }else{
            resultat = 0;
        }

        return resultat;
        
    }
    
    public boolean equiperPotion(int indexPotion){
        
        if(indexPotion < listesPotions.size()){
            indexPotionEquipee = indexPotion;
            return true;
        }else{
            return false;
        }
        
    }
    
    public int getNbPotions(){
        return listesPotions.size();
    }
    
    public ArrayList<Cle> getInventaireCle(){
        
        return listesCles;
    }
    
    public Cle getCleEquipee(){
        return(listesCles.get(indexCleEquipee));
    }
    
    public void ajouterCle(Cle uneCle){
        listesCles.add(uneCle);
    }
    
    public boolean equiperCle(int indexCle){
        
        if(indexCle < listesCles.size()){
            indexCleEquipee = indexCle;
            return true;
        }else{
            return false;
        }
        
    }
    
    public ArrayList<Arme> getInventaireArme(){
        
        return listesArmes;
    }
    
    public Arme getArmeEquipee(){
        return(listesArmes.get(indexArmeEquipee));
    }
    
    public void ajouterArme(Arme uneArme){
        listesArmes.add(uneArme);
    }
    
    //Si true, alors l'interface peut "cocher" l'arme comme utilisee
    public boolean equiperArme(int indexArme){
        
        if(indexArme < listesArmes.size()){
            indexArmeEquipee = indexArme;
            return true;
        }else{
            return false;
        }
        
    }
    
    
    public ArrayList<Armure> getInventaireArmure(){
        
        return listesArmures;
    }
    
    public void ajouterArmure(Armure unArmure){
        listesArmures.add(unArmure);
    }
    
    //Si true, alors l'interface peut "cocher" l'arme comme utilisee
    public boolean equiperArmure(int indexArmure){
        
        if(indexArmure < listesArmures.size()){
            indexArmureEquipee = indexArmure;
            return true;
        }else{
            return false;
        }
        
    }
    
    public Armure getArmureEquipee(){
        return(listesArmures.get(indexArmureEquipee));
    }
    
    public int getIndexPotion(){
        
        return indexPotionEquipee;
    }
    
    public int getIndexArme(){
        
        return indexArmeEquipee;
    }
    
    public int getIndexArmures(){
        
        return indexArmureEquipee;
    }
    
    public int getIndexCle(){
        
        return indexCleEquipee;
    }
    
    
    
}
