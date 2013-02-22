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
package Objets;


public class Armure {
    
    private String nom;
    private int absorbtion;
    private double modificateurChanceDAbsorbtion;
    
    public Armure(String unNom, int sonAbsorbtion, double saChanceDAbsorbtion){
        nom = unNom;
        absorbtion = sonAbsorbtion;
        modificateurChanceDAbsorbtion = saChanceDAbsorbtion;
    }
    
      public String getNom(){
        return nom;
    }
    
    public int getAbsorbtion(){
        return absorbtion;
    }
    
    public double getChanceDAbsorbtion(){
        return modificateurChanceDAbsorbtion;
    }  
    
     @Override
    public String toString() {
        return getNom();
    }
    
}