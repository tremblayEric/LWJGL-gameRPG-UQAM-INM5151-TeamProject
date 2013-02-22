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
package Entite;


public class Individu {
    

    
    String nom;
    
    int force;          //Bonus aux domages
    int agilite;        //Bonus pour toucher et éviter les coups
    int resistance;     //Bonus pour absorber les coups
    int pointsDeVieTotaux;  //Lorsque 100% pv
    int pointsDeVieActuels; //Quand les pv baissent
    
    Inventaire inventaire;   

    
    public Individu(String unNom, int uneForce, int uneAgilite, int uneResistance, int lesPointsDeVie){
        
        nom = unNom;
        force = uneForce;
        agilite = uneAgilite;
        resistance = uneResistance;
        pointsDeVieTotaux = lesPointsDeVie;
        pointsDeVieActuels = lesPointsDeVie;
        inventaire = new Inventaire();
        
    }
    
    public String getNom(){
        return nom;
    }
    
    public int getForce(){
        return force;
    }
    
    public int getAgilite(){
        return agilite;
    }
    
    public int getResistance(){
        return resistance;
    }
    
    public int getPointsDeVieActuels(){
        return pointsDeVieActuels;
    }
    
    public int getPointsDeVieTotaux(){
        return pointsDeVieTotaux;
    }   
    
    public void setPointsDeVieTotaux(int pointsDeVie){
        this.pointsDeVieTotaux = pointsDeVie;
    } 
    
    public void setPointsDeVieActuels(int pointsDeVie){
        this.pointsDeVieActuels = pointsDeVie;
    }
    
    public Inventaire getInventaire(){
        return inventaire;
    }
    
}
