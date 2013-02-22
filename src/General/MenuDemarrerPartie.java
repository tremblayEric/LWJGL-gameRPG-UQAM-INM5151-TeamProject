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

import GestionFichier.LecteurDeCarte;
import java.util.ArrayList;
import java.util.List;
import Tableau.Carte;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;


public class MenuDemarrerPartie extends javax.swing.JFrame {

    private List<Carte> listeCartes = new ArrayList<Carte>();
    private DefaultListModel modelLbCartes = new DefaultListModel();
    private LecteurDeCarte lecteur_json = new LecteurDeCarte();
    private int indexCarteSelectionnee = -1;

    /**
     * Creates new form MenuDemarrerPartie
     */
    public MenuDemarrerPartie() {
       
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        initComponents();
        ambianceVisuelle();
        this.setLocationRelativeTo(null);       //Centre la fenêtre
        
        chargeDossierCartes();
        
        if(listeCartes.size()>0){
           chargeListBoxCartes();
        }
        
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
    
    
    /*
     * Charge toutes les cartes du dossier "app.path/Cartes/" dans un tableau de cartes.
     */
    private void chargeDossierCartes(){
        
        Carte uneCarte;
        String cheminJeu = "";
        
        try{
            cheminJeu = new File(".").getCanonicalPath(); //Trouve le dossier de l'application :
        }catch(Exception e){
            System.out.print(e);
        }
        
        if(cheminJeu != ""){
            
            try{ 
            
                File dossier = new File(cheminJeu + "/Cartes/");
                File[] fichiersDesCartes = dossier.listFiles();

                for (File unFichierCartes : fichiersDesCartes){

                    //Prend seulement les fichiers .json                    
                    if (unFichierCartes.isFile() && unFichierCartes.getName().indexOf("json") > 0){

                        uneCarte = lecteur_json.lireCarteJson(unFichierCartes.getPath());
                        listeCartes.add(uneCarte);

                    }
                }            
            
            } catch (Exception ex) {
                  System.out.print(ex);
            }          

        }   
    }
    
    
    /*
     * Met les noms des cartes du listeCartes dans le listBox
     */
    private void chargeListBoxCartes(){
           
        for (Carte uneCarte : listeCartes) {
               modelLbCartes.addElement(uneCarte.getNom());
        }        
        
        lbCartes.setModel(modelLbCartes);
        
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PanneauPrincipal = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        bQuitter = new javax.swing.JButton();
        bDemarrer = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        iNom = new javax.swing.JLabel();
        iVersion = new javax.swing.JLabel();
        iGrandeur = new javax.swing.JLabel();
        iMode = new javax.swing.JLabel();
        iDescription = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lbCartes = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        PanneauPrincipal.setBackground(new java.awt.Color(0, 0, 0));
        PanneauPrincipal.setMaximumSize(new java.awt.Dimension(800, 600));
        PanneauPrincipal.setMinimumSize(new java.awt.Dimension(800, 600));
        PanneauPrincipal.setPreferredSize(new java.awt.Dimension(800, 600));

        jLabel1.setFont(new java.awt.Font("Script MT Bold", 1, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Projet << RPG >>");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 204, 204));
        jLabel2.setText("Chargement d'un donjon");

        bQuitter.setText("Quitter Le Jeu");
        bQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bQuitterActionPerformed(evt);
            }
        });

        bDemarrer.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        bDemarrer.setText("Démarrer la partie!");
        bDemarrer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bDemarrerActionPerformed(evt);
            }
        });

        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Veuillez choisir une carte :");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 153, 51));
        jLabel4.setText("Nom :");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 51));
        jLabel5.setText("Grandeur :");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 51));
        jLabel6.setText("Description :");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 51));
        jLabel7.setText("Version :");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 51));
        jLabel8.setText("Mode de jeu :");

        iNom.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        iNom.setForeground(new java.awt.Color(153, 153, 0));
        iNom.setText("<text>");
        iNom.setMaximumSize(new java.awt.Dimension(250, 22));
        iNom.setMinimumSize(new java.awt.Dimension(250, 22));
        iNom.setPreferredSize(new java.awt.Dimension(250, 22));

        iVersion.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        iVersion.setForeground(new java.awt.Color(153, 153, 0));
        iVersion.setText("<text>");
        iVersion.setMaximumSize(new java.awt.Dimension(250, 22));
        iVersion.setMinimumSize(new java.awt.Dimension(250, 22));
        iVersion.setPreferredSize(new java.awt.Dimension(250, 22));

        iGrandeur.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        iGrandeur.setForeground(new java.awt.Color(153, 153, 0));
        iGrandeur.setText("<text>");
        iGrandeur.setMaximumSize(new java.awt.Dimension(250, 22));
        iGrandeur.setMinimumSize(new java.awt.Dimension(250, 22));
        iGrandeur.setPreferredSize(new java.awt.Dimension(250, 22));

        iMode.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        iMode.setForeground(new java.awt.Color(153, 153, 0));
        iMode.setText("<text>");
        iMode.setMaximumSize(new java.awt.Dimension(250, 22));
        iMode.setMinimumSize(new java.awt.Dimension(250, 22));
        iMode.setPreferredSize(new java.awt.Dimension(250, 22));

        iDescription.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        iDescription.setForeground(new java.awt.Color(153, 153, 0));
        iDescription.setText("<text>");
        iDescription.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        iDescription.setMaximumSize(new java.awt.Dimension(250, 22));
        iDescription.setMinimumSize(new java.awt.Dimension(250, 22));
        iDescription.setPreferredSize(new java.awt.Dimension(250, 22));

        lbCartes.setBackground(new java.awt.Color(0, 0, 0));
        lbCartes.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lbCartes.setForeground(new java.awt.Color(0, 102, 0));
        lbCartes.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "<nomCarte>", "<nomCarte>", "<nomCarte>" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        lbCartes.setMaximumSize(new java.awt.Dimension(250, 200));
        lbCartes.setMinimumSize(new java.awt.Dimension(250, 200));
        lbCartes.setPreferredSize(new java.awt.Dimension(250, 200));
        lbCartes.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lbCartesValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(lbCartes);

        javax.swing.GroupLayout PanneauPrincipalLayout = new javax.swing.GroupLayout(PanneauPrincipal);
        PanneauPrincipal.setLayout(PanneauPrincipalLayout);
        PanneauPrincipalLayout.setHorizontalGroup(
            PanneauPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PanneauPrincipalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(188, 188, 188))
            .addGroup(PanneauPrincipalLayout.createSequentialGroup()
                .addGroup(PanneauPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PanneauPrincipalLayout.createSequentialGroup()
                        .addGap(276, 276, 276)
                        .addComponent(jLabel2))
                    .addGroup(PanneauPrincipalLayout.createSequentialGroup()
                        .addGap(103, 103, 103)
                        .addGroup(PanneauPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(PanneauPrincipalLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(38, 38, 38)
                                .addGroup(PanneauPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(PanneauPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(iVersion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(iNom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(iGrandeur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(iMode, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(iDescription, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel3)))
                    .addGroup(PanneauPrincipalLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(bQuitter))
                    .addGroup(PanneauPrincipalLayout.createSequentialGroup()
                        .addGap(200, 200, 200)
                        .addComponent(bDemarrer, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        PanneauPrincipalLayout.setVerticalGroup(
            PanneauPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanneauPrincipalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(PanneauPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PanneauPrincipalLayout.createSequentialGroup()
                        .addGroup(PanneauPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(iNom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanneauPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(iVersion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanneauPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(iGrandeur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(PanneauPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(iMode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(PanneauPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(iDescription, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
                .addComponent(bDemarrer, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(bQuitter)
                .addGap(88, 88, 88))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanneauPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 825, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PanneauPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bQuitterActionPerformed
        System.exit(0);
    }//GEN-LAST:event_bQuitterActionPerformed

    private void lbCartesValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lbCartesValueChanged

        int indexCliquee = lbCartes.getSelectedIndex();
        
        if(indexCliquee != -1 && listeCartes.size() > 0){
            iNom.setText(listeCartes.get(indexCliquee).getNom());
            iVersion.setText(listeCartes.get(indexCliquee).getVersion());
            iGrandeur.setText("" + listeCartes.get(indexCliquee).getNombreCasesTotal());
            iMode.setText(listeCartes.get(indexCliquee).getModeJeu());
            iDescription.setText(fixMultilignes(listeCartes.get(indexCliquee).getDescription()));
            indexCarteSelectionnee = indexCliquee;
        }
        
    }//GEN-LAST:event_lbCartesValueChanged

    
    /*
     * Pour que le label affiche du multilignes il faut que le texte soit en html
     * et que l'on mette des breaklines.
     */
    private String fixMultilignes(String texte){

        int max = 30;
        int positionEspace = 0;
        int longueurTexte = texte.length();
        
        for(int i = 1; longueurTexte - positionEspace > 30 ;i++){

            positionEspace = texte.lastIndexOf(" ", max);
            texte = texte.substring(0,positionEspace) + "<br />" + texte.substring(positionEspace+1,longueurTexte) ; 
            max += 30 + (5 * i);
         
        }
        
        texte = "<html>" + texte + "</html>";  
        
        return texte;
    }
    
    
    private void bDemarrerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bDemarrerActionPerformed
        
        if(indexCarteSelectionnee != -1){
            
            this.setVisible(false);   
            MoteurDeJeu leJeu = new MoteurDeJeu(listeCartes.get(indexCarteSelectionnee));
            //FenetreDeJeu fenetre = new FenetreDeJeu(listeCartes.get(indexCarteSelectionnee));
            //fenetre.pack();
            //fenetre.setVisible(true);           
            this.dispose();
        }
        
    }//GEN-LAST:event_bDemarrerActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PanneauPrincipal;
    private javax.swing.JButton bDemarrer;
    private javax.swing.JButton bQuitter;
    private javax.swing.JLabel iDescription;
    private javax.swing.JLabel iGrandeur;
    private javax.swing.JLabel iMode;
    private javax.swing.JLabel iNom;
    private javax.swing.JLabel iVersion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList lbCartes;
    // End of variables declaration//GEN-END:variables
}
