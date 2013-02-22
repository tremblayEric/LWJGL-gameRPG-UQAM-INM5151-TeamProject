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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



//implementation de serializable pour ecrire des object dans fichiers
//zero effet sure le code 
public class Carte implements Serializable {
    
    /**
     * La carte est 10x10 par defaut pour commencer.
     */
    private Case[][] carte = new Case[10][10];
    private String nomCarte = "Carte Test";
    private String auteur = "RPG games";
    private String version ="0.1";
    private String modeJeu = "defaut";
    private String texteCarte = "texte ici..";
    private int nombreCasesTotal = 2;
    private Map<String,Integer> nombreCasesXZ = new HashMap<String,Integer>()
    {
        {
            put("x", 2);
            put("z", 1);
            
        }
    };
    private List<Case> listesCases = new ArrayList<Case>(){
        
        {
            add(new Case(true,false,true,false,true,false,1,0));
            add(new Case(true,false,true,false,true,false,0,0));
        }   
    };

   

    public Carte() {
        
        for(int i = 0; i < listesCases.size() ; ++i){
            
            initCarte(listesCases.get(i).getPositionX(),
                    listesCases.get(i).getPositionZ(), listesCases.get(i));
        }
        
    }
    
    
    private void initCarte(int x, int z, Case _case){
    
       this.carte[x][z] = _case;
    }
  
    
    
    
    public String getNom(){
        return nomCarte;
    }
    
    public String getVersion(){
        return version;
    }
    
    public String getModeJeu(){
        return modeJeu;
    }
    
    public String getDescription(){
        return texteCarte;
    }
    
    public int getNombreCasesTotal(){
        return nombreCasesTotal;
    }
    
    public int getLargeur(){
        return this.carte.length;
    }
    
    public int getHauteur(){
        return this.carte[0].length;
    }
    
    public void setCarte( final int x, final int z, final Case laCase){
    
    this.carte[x][z] = laCase;
}
    
    /**
     * Traitement d'erreur a ajouter pour ne pas deborder du tableau.
     *
     */
    public Case getCase(final int x, final int z){
    
    return carte[x][z];
    
}
    
    @Override
    public String toString(){
        
        return "Carte [nomCarte=" + nomCarte+ ",auteur=" + auteur +",version=" 
                +version+",modeDeJeu="+ modeJeu + ",texteCarte=" + texteCarte+ 
                ",nombreCaseTotal="+ nombreCasesTotal + ",nombreCasesXZ:"
                + nombreCasesXZ +", listesCases="+listesCases+"]";
    }
    
}
