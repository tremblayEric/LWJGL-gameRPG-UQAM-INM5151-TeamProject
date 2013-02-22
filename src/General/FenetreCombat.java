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

import Entite.Monstre;
import Entite.Personnage;
import Objets.Potion;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Jean-Francois
 */
public class FenetreCombat extends javax.swing.JFrame {

    private static List<String> listeConsole = new ArrayList<String>();     //10 dernières lignes de la console
    private static MoteurDeJeu leMoteurDuJeu;
    private static Personnage lePerso;
        String cheminJeu = new File(".").getCanonicalPath(); //Trouve le dossier de l'application :    
    
    public FenetreCombat(Personnage joueur, Monstre monstre, MoteurDeJeu moteur) throws IOException {
             
        leMoteurDuJeu = moteur;

        initComponents();
        ambianceVisuelle();
        this.setLocationRelativeTo(null);       //Centre la fenêtre
        listeConsole.removeAll(listeConsole);
        lePerso = joueur;
        
        try{

            jImage.setIcon(new javax.swing.ImageIcon(cheminJeu + "/textures/demon.jpg"));
            jImage.setOpaque(true);
            
            lPhotoPerso.setIcon(new javax.swing.ImageIcon(cheminJeu + "/textures/guerrier.jpg"));
            lPhotoPerso.setOpaque(true);  
            
            chargerCaracteristiques();
            
            pv.setPreferredSize(new java.awt.Dimension(calculeGrandeurBarrePointsVie(), 25));
            pv.setSize(pv.getPreferredSize());

            lpointsVie.setText("" + leMoteurDuJeu.getPointsVieActuelsJoueur() + "/" + leMoteurDuJeu.getPointsVieTotalJoueur());
            lnbPotions.setText("0");
            
            lnbPotions.setText("" + leMoteurDuJeu.getNbPotions());            
            
            
        }catch(Exception ex){
            System.out.print(ex);
        }

        this.pack();
        this.setVisible(true);   
        
        Combat leCombat = new Combat(joueur,monstre);
        leCombat.start();    
        
    }
    

    private void chargerCaracteristiques(){
        
        lForce.setText("" + lePerso.getForce());
        lAgilite.setText("" + lePerso.getAgilite());
        lResistance.setText("" + lePerso.getResistance());
        lNiveau.setText("" + lePerso.getNiveau());
        lXp.setText("" + lePerso.getXpRestant());
        lArme.setText(lePerso.getInventaire().getArmeEquipee().getNom());
        lArmure.setText(lePerso.getInventaire().getArmureEquipee().getNom());

    }
    
    
    
    private void ambianceVisuelle(){
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */        
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuDemarrerPartie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuDemarrerPartie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuDemarrerPartie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuDemarrerPartie.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

    }
    
    
    //Calcule la grandeur de la barre de points de vie
    private static int calculeGrandeurBarrePointsVie(){
        double temp = 250.0 * ((double)leMoteurDuJeu.getPointsVieActuelsJoueur() / (double)leMoteurDuJeu.getPointsVieTotalJoueur());
        return (int)temp;
    }
    
    public static void finCombat(int experience){
        leMoteurDuJeu.finDuCombat(experience);
    }
    
    
    public static void ajoutTexteConsole(String texte){
       
        String phrase = ""; 

           if(listeConsole.size() > 10){
               listeConsole.remove(0);
           }   
            
           listeConsole.add(texte);

           for(int i = 0; i < listeConsole.size(); i++){
               phrase += listeConsole.get(i) + "<br />";
           }            
            
           phrase = "<html>" + phrase + "</html>";  

           lpointsVie.setText("" + leMoteurDuJeu.getPointsVieActuelsJoueur() + "/" + leMoteurDuJeu.getPointsVieTotalJoueur());

           pv.setPreferredSize(new java.awt.Dimension(calculeGrandeurBarrePointsVie(), 25));
           pv.setSize(pv.getPreferredSize());            
            
           console.setText(phrase);            
                
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pImage = new javax.swing.JPanel();
        jImage = new javax.swing.JLabel();
        pDroit = new javax.swing.JPanel();
        pPv = new General.PaneauAvecImage(new javax.swing.ImageIcon(cheminJeu + "/textures/sous_guerrier.jpg").getImage());
        jLabel2 = new javax.swing.JLabel();
        pArriereBarrePV = new javax.swing.JPanel();
        pv = new javax.swing.JLabel();
        lpointsVie = new javax.swing.JLabel();
        pPv1 = new General.PaneauAvecImage(new javax.swing.ImageIcon(cheminJeu + "/textures/sous_guerrier2.jpg").getImage());
        lnbPotions = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        bUtiliserPotion = new javax.swing.JButton();
        pPv2 = new General.PaneauAvecImage(new javax.swing.ImageIcon(cheminJeu + "/textures/sous_guerrier.jpg").getImage());
        lpointsVie7 = new javax.swing.JLabel();
        lArme = new javax.swing.JLabel();
        lpointsVie6 = new javax.swing.JLabel();
        lArmure = new javax.swing.JLabel();
        pCaracteristiques = new General.PaneauAvecImage(new javax.swing.ImageIcon(cheminJeu + "/textures/guerrier2.jpg").getImage());
        lpointsVie1 = new javax.swing.JLabel();
        lpointsVie2 = new javax.swing.JLabel();
        lpointsVie3 = new javax.swing.JLabel();
        lpointsVie4 = new javax.swing.JLabel();
        lpointsVie5 = new javax.swing.JLabel();
        lForce = new javax.swing.JLabel();
        lAgilite = new javax.swing.JLabel();
        lResistance = new javax.swing.JLabel();
        lNiveau = new javax.swing.JLabel();
        lXp = new javax.swing.JLabel();
        pPv4 = new javax.swing.JPanel();
        lPhotoPerso = new javax.swing.JLabel();
        pConsole = new javax.swing.JPanel();
        console = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(1024, 768));
        setMinimumSize(new java.awt.Dimension(1024, 768));

        jPanel1.setBackground(new java.awt.Color(65, 52, 43));
        jPanel1.setMaximumSize(new java.awt.Dimension(1024, 768));
        jPanel1.setMinimumSize(new java.awt.Dimension(1024, 768));
        jPanel1.setPreferredSize(new java.awt.Dimension(1024, 768));

        pImage.setBackground(new java.awt.Color(0, 0, 0));

        jImage.setBackground(new java.awt.Color(0, 0, 0));
        jImage.setOpaque(true);

        javax.swing.GroupLayout pImageLayout = new javax.swing.GroupLayout(pImage);
        pImage.setLayout(pImageLayout);
        pImageLayout.setHorizontalGroup(
            pImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pImageLayout.createSequentialGroup()
                .addComponent(jImage, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pImageLayout.setVerticalGroup(
            pImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pImageLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pDroit.setBackground(new java.awt.Color(0, 0, 0));

        pPv.setBackground(new java.awt.Color(102, 102, 102));

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Points de vie :");

        pArriereBarrePV.setBackground(new java.awt.Color(204, 204, 204));
        pArriereBarrePV.setMaximumSize(new java.awt.Dimension(250, 25));
        pArriereBarrePV.setMinimumSize(new java.awt.Dimension(250, 25));
        pArriereBarrePV.setPreferredSize(new java.awt.Dimension(250, 25));

        pv.setBackground(new java.awt.Color(255, 0, 0));
        pv.setDoubleBuffered(true);
        pv.setMaximumSize(new java.awt.Dimension(250, 25));
        pv.setMinimumSize(new java.awt.Dimension(0, 25));
        pv.setOpaque(true);
        pv.setPreferredSize(new java.awt.Dimension(250, 25));

        javax.swing.GroupLayout pArriereBarrePVLayout = new javax.swing.GroupLayout(pArriereBarrePV);
        pArriereBarrePV.setLayout(pArriereBarrePVLayout);
        pArriereBarrePVLayout.setHorizontalGroup(
            pArriereBarrePVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pArriereBarrePVLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(pv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pArriereBarrePVLayout.setVerticalGroup(
            pArriereBarrePVLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pArriereBarrePVLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(pv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        lpointsVie.setBackground(new java.awt.Color(255, 255, 255));
        lpointsVie.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lpointsVie.setForeground(new java.awt.Color(255, 255, 255));
        lpointsVie.setText("999 / 999");

        javax.swing.GroupLayout pPvLayout = new javax.swing.GroupLayout(pPv);
        pPv.setLayout(pPvLayout);
        pPvLayout.setHorizontalGroup(
            pPvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPvLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pPvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pPvLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(lpointsVie))
                    .addComponent(pArriereBarrePV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pPvLayout.setVerticalGroup(
            pPvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPvLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pPvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lpointsVie, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pArriereBarrePV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        pPv1.setBackground(new java.awt.Color(102, 102, 102));

        lnbPotions.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lnbPotions.setForeground(new java.awt.Color(255, 255, 255));
        lnbPotions.setText("0");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Potions :");

        bUtiliserPotion.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        bUtiliserPotion.setText("Utiliser");
        bUtiliserPotion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bUtiliserPotionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pPv1Layout = new javax.swing.GroupLayout(pPv1);
        pPv1.setLayout(pPv1Layout);
        pPv1Layout.setHorizontalGroup(
            pPv1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPv1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lnbPotions, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(bUtiliserPotion)
                .addGap(33, 33, 33))
        );
        pPv1Layout.setVerticalGroup(
            pPv1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pPv1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pPv1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lnbPotions, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bUtiliserPotion))
                .addGap(5, 5, 5))
        );

        jLabel3.getAccessibleContext().setAccessibleName("");

        pPv2.setBackground(new java.awt.Color(102, 102, 102));

        lpointsVie7.setBackground(new java.awt.Color(255, 255, 255));
        lpointsVie7.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lpointsVie7.setForeground(new java.awt.Color(255, 255, 255));
        lpointsVie7.setText("Arme :");

        lArme.setBackground(new java.awt.Color(255, 255, 255));
        lArme.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lArme.setForeground(new java.awt.Color(255, 255, 255));
        lArme.setText("Arme équipée :");

        lpointsVie6.setBackground(new java.awt.Color(255, 255, 255));
        lpointsVie6.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lpointsVie6.setForeground(new java.awt.Color(255, 255, 255));
        lpointsVie6.setText("Armure :");

        lArmure.setBackground(new java.awt.Color(255, 255, 255));
        lArmure.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lArmure.setForeground(new java.awt.Color(255, 255, 255));
        lArmure.setText("Armure équipée :");

        javax.swing.GroupLayout pPv2Layout = new javax.swing.GroupLayout(pPv2);
        pPv2.setLayout(pPv2Layout);
        pPv2Layout.setHorizontalGroup(
            pPv2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPv2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pPv2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lpointsVie7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lpointsVie6, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pPv2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lArmure, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lArme, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pPv2Layout.setVerticalGroup(
            pPv2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPv2Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pPv2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lpointsVie7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lArme, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pPv2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lpointsVie6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lArmure, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        pCaracteristiques.setBackground(new java.awt.Color(102, 102, 102));

        lpointsVie1.setBackground(new java.awt.Color(255, 255, 255));
        lpointsVie1.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lpointsVie1.setForeground(new java.awt.Color(255, 255, 255));
        lpointsVie1.setText("Niveau actuel :");

        lpointsVie2.setBackground(new java.awt.Color(255, 255, 255));
        lpointsVie2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lpointsVie2.setForeground(new java.awt.Color(255, 255, 255));
        lpointsVie2.setText("XP Prochain niveau :");

        lpointsVie3.setBackground(new java.awt.Color(255, 255, 255));
        lpointsVie3.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lpointsVie3.setForeground(new java.awt.Color(255, 255, 255));
        lpointsVie3.setText("Force :");

        lpointsVie4.setBackground(new java.awt.Color(255, 255, 255));
        lpointsVie4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lpointsVie4.setForeground(new java.awt.Color(255, 255, 255));
        lpointsVie4.setText("Agilité :");

        lpointsVie5.setBackground(new java.awt.Color(255, 255, 255));
        lpointsVie5.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lpointsVie5.setForeground(new java.awt.Color(255, 255, 255));
        lpointsVie5.setText("Résistance :");

        lForce.setBackground(new java.awt.Color(255, 255, 255));
        lForce.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lForce.setForeground(new java.awt.Color(255, 255, 255));
        lForce.setText("vide");

        lAgilite.setBackground(new java.awt.Color(255, 255, 255));
        lAgilite.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lAgilite.setForeground(new java.awt.Color(255, 255, 255));
        lAgilite.setText("vide");

        lResistance.setBackground(new java.awt.Color(255, 255, 255));
        lResistance.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lResistance.setForeground(new java.awt.Color(255, 255, 255));
        lResistance.setText("vide");

        lNiveau.setBackground(new java.awt.Color(255, 255, 255));
        lNiveau.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lNiveau.setForeground(new java.awt.Color(255, 255, 255));
        lNiveau.setText("vide");

        lXp.setBackground(new java.awt.Color(255, 255, 255));
        lXp.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lXp.setForeground(new java.awt.Color(255, 255, 255));
        lXp.setText("vide");

        javax.swing.GroupLayout pCaracteristiquesLayout = new javax.swing.GroupLayout(pCaracteristiques);
        pCaracteristiques.setLayout(pCaracteristiquesLayout);
        pCaracteristiquesLayout.setHorizontalGroup(
            pCaracteristiquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCaracteristiquesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCaracteristiquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lpointsVie3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lpointsVie4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lpointsVie5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lpointsVie1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lpointsVie2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pCaracteristiquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(lForce, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lAgilite, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lResistance, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lNiveau, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lXp, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pCaracteristiquesLayout.setVerticalGroup(
            pCaracteristiquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pCaracteristiquesLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addGroup(pCaracteristiquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCaracteristiquesLayout.createSequentialGroup()
                        .addComponent(lForce, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lAgilite, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lResistance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pCaracteristiquesLayout.createSequentialGroup()
                        .addComponent(lpointsVie3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lpointsVie4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lpointsVie5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pCaracteristiquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lpointsVie1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lNiveau, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pCaracteristiquesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lpointsVie2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lXp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pPv4.setBackground(new java.awt.Color(102, 102, 102));
        pPv4.setPreferredSize(new java.awt.Dimension(0, 160));

        lPhotoPerso.setBackground(new java.awt.Color(204, 255, 102));
        lPhotoPerso.setOpaque(true);

        javax.swing.GroupLayout pPv4Layout = new javax.swing.GroupLayout(pPv4);
        pPv4.setLayout(pPv4Layout);
        pPv4Layout.setHorizontalGroup(
            pPv4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lPhotoPerso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pPv4Layout.setVerticalGroup(
            pPv4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pPv4Layout.createSequentialGroup()
                .addComponent(lPhotoPerso, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout pDroitLayout = new javax.swing.GroupLayout(pDroit);
        pDroit.setLayout(pDroitLayout);
        pDroitLayout.setHorizontalGroup(
            pDroitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pDroitLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pDroitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pPv2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pCaracteristiques, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pPv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pPv1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pPv4, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE))
                .addContainerGap())
        );
        pDroitLayout.setVerticalGroup(
            pDroitLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pDroitLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pPv4, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pCaracteristiques, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pPv2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(pPv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pPv1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        pConsole.setBackground(new java.awt.Color(0, 0, 0));

        console.setBackground(new java.awt.Color(0, 0, 0));
        console.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        console.setForeground(new java.awt.Color(204, 0, 51));
        console.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        console.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        console.setOpaque(true);
        console.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout pConsoleLayout = new javax.swing.GroupLayout(pConsole);
        pConsole.setLayout(pConsoleLayout);
        pConsoleLayout.setHorizontalGroup(
            pConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pConsoleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(console, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pConsoleLayout.setVerticalGroup(
            pConsoleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pConsoleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(console, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pDroit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(pConsole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pImage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pDroit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pConsole, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bUtiliserPotionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bUtiliserPotionActionPerformed
        
       if(leMoteurDuJeu != null && leMoteurDuJeu.utiliserPotion()){
           lnbPotions.setText("" + leMoteurDuJeu.getNbPotions());
           
           //Fix refresh
           lpointsVie.setText("" + leMoteurDuJeu.getPointsVieActuelsJoueur() + "/" + leMoteurDuJeu.getPointsVieTotalJoueur());

           pv.setPreferredSize(new java.awt.Dimension(calculeGrandeurBarrePointsVie(), 25));
           pv.setSize(pv.getPreferredSize());    
           //Fin Fix refresh
       } 
 
    }//GEN-LAST:event_bUtiliserPotionActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bUtiliserPotion;
    private static javax.swing.JLabel console;
    private javax.swing.JLabel jImage;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private static javax.swing.JLabel lAgilite;
    private static javax.swing.JLabel lArme;
    private static javax.swing.JLabel lArmure;
    private static javax.swing.JLabel lForce;
    private static javax.swing.JLabel lNiveau;
    private javax.swing.JLabel lPhotoPerso;
    private static javax.swing.JLabel lResistance;
    private static javax.swing.JLabel lXp;
    private static javax.swing.JLabel lnbPotions;
    private static javax.swing.JLabel lpointsVie;
    private static javax.swing.JLabel lpointsVie1;
    private static javax.swing.JLabel lpointsVie2;
    private static javax.swing.JLabel lpointsVie3;
    private static javax.swing.JLabel lpointsVie4;
    private static javax.swing.JLabel lpointsVie5;
    private static javax.swing.JLabel lpointsVie6;
    private static javax.swing.JLabel lpointsVie7;
    private javax.swing.JPanel pArriereBarrePV;
    private javax.swing.JPanel pCaracteristiques;
    private static javax.swing.JPanel pConsole;
    private javax.swing.JPanel pDroit;
    private javax.swing.JPanel pImage;
    public static javax.swing.JPanel pPv;
    private javax.swing.JPanel pPv1;
    private javax.swing.JPanel pPv2;
    private javax.swing.JPanel pPv4;
    public static javax.swing.JLabel pv;
    // End of variables declaration//GEN-END:variables
}
