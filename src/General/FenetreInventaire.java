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

import Entite.Individu;
import Entite.Inventaire;
import Entite.Personnage;
import Objets.Arme;
import Objets.Armure;
import Objets.Cle;
import Objets.Potion;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;


public class FenetreInventaire extends javax.swing.JFrame {

    private ArrayList<Arme> listeArme;
    private ArrayList<Potion> listePotion;
    private ArrayList<Armure> listeArmures;
    private ArrayList<Cle> listeCle;
    private Inventaire inventaire;
    private DefaultListModel listModelArme = new DefaultListModel();
    private DefaultListModel listModelPotion = new DefaultListModel();
    private DefaultListModel listModelArmure = new DefaultListModel();
    private DefaultListModel listModelCle = new DefaultListModel();
    private static MoteurDeJeu monMoteurDeJeu;
    
    private static Individu joueur = new Personnage("R", 5, 5, 5, 5);
    
    
    
    
    public FenetreInventaire(Individu joueur,MoteurDeJeu lemoteurDeJeu){
        
        
        
        
        monMoteurDeJeu = lemoteurDeJeu;
        
        inventaire =  joueur.getInventaire();
        listeArme = inventaire.getInventaireArme();
        listeArmures = inventaire.getInventaireArmure();
        listePotion = inventaire.getInventairePotion();
        listeCle = inventaire.getInventaireCle();
        
        
        
        initComponents();
        
       
        
        
        this.setLocationRelativeTo(null); 
        this.pack();
        this.setVisible(true); 
        
        
        
    }
    
    
     private void setModelListe(DefaultListModel model, ArrayList liste){
         
         if(liste.size() > 0){
            for(int i = 0; i < liste.size(); i++){
                
                model.addElement(liste.get(i).toString());
                
            }
        }
     }
     
     private void finInventaire(){
         
         monMoteurDeJeu.finGestionInventaire();
         
         
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jListPotion = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jListArmes = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jListArmures = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jListCle = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        OkButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setMaximumSize(new java.awt.Dimension(800, 600));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 600));

        jListPotion.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListPotion.removeAll();
        setModelListe(listModelPotion, listePotion);
        jListPotion = new JList(listModelPotion);
        jListPotion.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if(inventaire.getIndexPotion() != -1) jListPotion.setSelectedIndex(inventaire.getIndexPotion());
        jScrollPane1.setViewportView(jListPotion);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Potion");

        jListArmes.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListArmes.removeAll();
        setModelListe(listModelArme, listeArme);
        jListArmes = new JList(listModelArme);
        jListArmes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if(inventaire.getIndexArme() != -1) jListPotion.setSelectedIndex(inventaire.getIndexArme());
        jScrollPane2.setViewportView(jListArmes);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Armes");

        jListArmures.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListArmures.removeAll();
        setModelListe(listModelArmure, listeArmures);
        jListArmures = new JList(listModelArmure);
        jListArmures.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if(inventaire.getIndexArmures() != -1) jListPotion.setSelectedIndex(inventaire.getIndexArmures());
        jScrollPane3.setViewportView(jListArmures);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Armures");

        jListCle.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        jListCle.removeAll(); // cle
        setModelListe(listModelCle, listeCle);
        jListCle = new JList(listModelCle);
        jListCle.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if(inventaire.getIndexCle() != -1) jListPotion.setSelectedIndex(inventaire.getIndexCle());
        jScrollPane4.setViewportView(jListCle);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Cle");

        OkButton.setText("OK");
        OkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OkButtonActionPerformed(evt);
            }
        });

        CancelButton.setText("Annuler");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 0, 51));
        jLabel5.setText("Inventaire");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 146, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(127, 127, 127))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(77, 77, 77)
                        .addComponent(jLabel4)
                        .addGap(110, 110, 110))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(134, 134, 134)
                .addComponent(OkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(184, 184, 184))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(251, 251, 251))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OkButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(162, 162, 162))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 599, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void OkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OkButtonActionPerformed
        
        if(jListArmes.getSelectedIndex() != -1){
            inventaire.equiperArme(jListArmes.getSelectedIndex());
        }else if(jListArmures.getSelectedIndex() != -1){
            
            inventaire.equiperArmure(jListArmures.getSelectedIndex());
        }else if(jListPotion.getSelectedIndex() != -1){
            inventaire.equiperPotion(jListPotion.getSelectedIndex());
        }else if(jListCle.getSelectedIndex() != -1){
            inventaire.equiperCle(jListCle.getSelectedIndex());
        }
        
        finInventaire();
        
        
    }//GEN-LAST:event_OkButtonActionPerformed

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        // TODO add your handling code here:
        
        finInventaire();
    }//GEN-LAST:event_CancelButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(FenetreInventaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FenetreInventaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FenetreInventaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FenetreInventaire.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               new FenetreInventaire(joueur,monMoteurDeJeu).setVisible(true);
            }
       });
   }
     
  

   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton OkButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JList jListArmes;
    private javax.swing.JList jListArmures;
    private javax.swing.JList jListCle;
    private javax.swing.JList jListPotion;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    // End of variables declaration//GEN-END:variables
}