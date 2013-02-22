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
package Tableau;
import Entite.Monstre;
import Objets.Arme;
import Objets.Armure;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;



// implementation de serializable carte et case sont intimement lier
// zero effet sur le code 
public class Case implements Serializable {
    
    /*
     * coordonnees en x , y , z de la case dans la carte.
     */
    private int x;
    private int z;
    
    private boolean murNord  = false;
    private boolean murEst   = false;
    private boolean murOuest = false;
    private boolean murSud   = false;
    private boolean monstre  = false;
    private boolean coffre   = false;
    
    //ajout pour lire/ecrire carte 
    private Map<String,Integer> coordonee = new HashMap<String,Integer>();
    private Map<String,Boolean> murNordCarte = new HashMap<String,Boolean>();
    private Map<String,Boolean> murSudCarte = new HashMap<String,Boolean>();
    private Map<String,Boolean> murEstCarte = new HashMap<String,Boolean>();
    private Map<String,Boolean> murOuestCarte = new HashMap<String,Boolean>();
    private Map<String,Boolean> milieuCarte = new HashMap<String,Boolean>();
    
    Case(boolean murNord, boolean murEst, boolean murOuest, boolean murSud, boolean monstre, boolean coffre, int x, int z){
        
        this.murNord  = murNord;
        this.murEst   = murEst;
        this.murOuest = murOuest;
        this.murSud   = murSud;
        this.monstre  = monstre;
        this.coffre   = coffre; 
        
        this.x = x;
        this.z = z;
       
        this.coordonee.put("x", this.x);
        this.coordonee.put("z",this.z);
        
        this.murNordCarte.put("murNord", this.murNord);
        this.murSudCarte.put("murSud", this.murSud);
        this.murEstCarte.put("murEst",this.murEst);
        this.murOuestCarte.put("murOuest",this.murOuest);
        this.milieuCarte.put("milieu",false);
        
    }

    public Case() {
    }
    
   /**
    * plusieur accesseur (setter) devront etre rajouter afin de pouvoir acceder au element 
    * plus simplement lorsque la complexite du logiciel croitrera 
    * ( ex: if(laCase12.getMurNord()){"on ne peut avanecer vers le nord"};
    */
    
    public int getPositionX(){
        return x;
    }
    

      public int getPositionZ(){
        return z;
    }
    
    public void setPositon(final int x, final int z){
        this.x = x;
        this.z = z;
    }
    public Boolean getMurNord(){
        return murNord;  
    }
    public Boolean getMurSud(){
        return murSud;  
    }
    public Boolean getMurEst(){
        return murEst;  
    }
    public Boolean getMurOuest(){
        return murOuest;  
    }
     public Boolean getMonstre(){
        return monstre;  
    }
     
    //Dans la version complete, chaque case ayant un monstre, devrait avoir la description du monstre. 
    public Monstre getObjetMonstre(){
        
        Monstre demon = new Monstre("Murtar", 10, 10, 10, 50, 50);  
        Arme uneArme = new Arme("Épée obscure", 5, 0.30);
        Armure uneArmure = new Armure("Peau Dragon", 2, 0.20);   

        demon.getInventaire().ajouterArme(uneArme);
        demon.getInventaire().equiperArme(0);
        
        demon.getInventaire().ajouterArmure(uneArmure);
        demon.getInventaire().equiperArmure(0);
        
        return demon;
    }
          
     
     public void setMonstre(boolean valeur){
         monstre = valeur;  
    }
    public Case getCaseAdjacentNord(Carte laCarte){
        return laCarte.getCase(this.x, this.z + 1);
    }
   
    public String toString() {
        
        return "Case [coordonnee:"+ coordonee + ",murNord:"+ murNordCarte +","
                + "murSudCarte:"+ murSudCarte +",murEst:"+ murEstCarte +
                ",murOuest:"+ murOuestCarte + ",milieu:"+ milieuCarte +" ]";
    }
}
