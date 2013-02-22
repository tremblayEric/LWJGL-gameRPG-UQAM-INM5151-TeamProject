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
package Tableau;

import General.FenetreCombat;
import General.MoteurDeJeu;
import GestionFichier.LecteurDeCarte;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import static org.lwjgl.opengl.GL11.*;

public class FenetreDonjon3d extends JFrame {

    /*
     private final String cheminTexturePlancher   = "C:/Users/eric et bendjina/Documents/NetBeansProjects/inm5151rpg~subversion/RPG/textures/beton.jpg";
     private final String cheminTextureMur        = "C:/Users/eric et bendjina/Documents/NetBeansProjects/inm5151rpg~subversion/RPG/textures/murPierre.jpg";
     private final String textureTexturePlafond   = "C:/Users/eric et bendjina/Documents/NetBeansProjects/inm5151rpg~subversion/RPG/textures/plafond.jpg";
     */
    private String cheminTexturePlancher;
    private String cheminTextureMur;
    private String textureTexturePlafond;
    private String textureTextureMonstre;
    ArrayList<Texture> listeDeTexture;
    private Texture texturePlancher = null;
    private Texture textureMur = null;
    private Texture texturePlafond = null;
    private Texture textureMonstre = null;
    boolean flagCombat = false;
    boolean monstre = false;
    MoteurDeJeu leJeu;
    Carte laCarte;
    Case caseActuelle = null;
    //vecteur 3d  contenant la position de la camera
    Vector3f position = null;
    //rotation de la camera autour de l axe des y
    private float yaw = 0.0f;
    //rotation de la camera autour de l axe des x
    private float pitch = 0.0f;
    //constructeur prenant en parametre les coordonne de la camera
    private boolean fermePasProgramme = true;
    private float orientation;
    float positionDansCarte[];

    public Case trouverCaseActuelle(Carte laCarte, float positionX, float positionZ) {

        Case laCase = null;

        if (laCarte != null) {

            for (int i = 0; i < laCarte.getLargeur(); ++i) {


                for (int j = 0; j < laCarte.getHauteur(); ++j) {


                    if (laCarte.getCase(i, j) != null) {


                        if (Math.abs(Math.ceil(positionX)) == laCarte.getCase(i, j).getPositionX() && Math.abs(Math.floor(positionZ)) == laCarte.getCase(i, j).getPositionZ()) {

                            laCase = laCarte.getCase(i, j);
                            // System.out.println("caseX " + laCase.getPositionX() + " persoX  " + ( Math.abs(Math.floor(positionX)) -1 ) + " caseZ " + laCase.getPositionZ() + " persoZ " + Math.abs( Math.floor(positionZ) ));

                        }

                    }

                }

            }


        }


        return laCase;

    }

    public FenetreDonjon3d(MoteurDeJeu leMoteurDeJeu) {
        orientation = leMoteurDeJeu.getOrientation();
        positionDansCarte = leMoteurDeJeu.getPositionDansCarte();
        position = new Vector3f(positionDansCarte[0], positionDansCarte[1], positionDansCarte[2]);
        leJeu = leMoteurDeJeu;
    }

    public FenetreDonjon3d(Carte uneCarte, MoteurDeJeu leMoteurDeJeu) {

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //Si la fenetre 3D ferme, le jeu meurt pas

        laCarte = uneCarte;
        leJeu = leMoteurDeJeu;
        orientation = leMoteurDeJeu.getOrientation();
        fermePasProgramme = true;
        initDisplay(false);
        initGL();

    }

    public void demmarageOpenGl() {

        try {
            init();
            gameRunning = true;
            run();
        } catch (Exception ex) {
        }
    }

    public void yaw(float amount) {
        yaw += amount;
    }

    //increment la rotation de la camera
    public void pitch(float amount) {
        //incremente pitch
        pitch += amount;
    }
    //bouge la camera vers l avant

    public void walkForward(Carte laCarte, float distance) {

        caseActuelle = trouverCaseActuelle(laCarte, position.x, position.z);

        if (caseActuelle != null) {

            if (!flagCombat && !caseActuelle.getMonstre()) {//pas de monstre bloquant le chemin

                if (position.x - distance * (float) Math.sin(Math.toRadians(yaw)) > position.x) {//on avance vers l'ouest
                    //si pas de mur bloquant ou qu'on a pas atteint la limite de la case
                    //System.out.println("ouest");
                    if (!caseActuelle.getMurOuest() || caseActuelle.getPositionX() > Math.abs(position.x - distance * (float) Math.sin(Math.toRadians(yaw)))) {

                        position.x -= distance * (float) Math.sin(Math.toRadians(yaw));

                    }

                } else {//on avance vers l'est
                    // System.out.println("est");
                    if (!caseActuelle.getMurEst() || caseActuelle.getPositionX() > Math.abs(position.x - distance * (float) Math.sin(Math.toRadians(yaw)))) {
                        position.x -= distance * (float) Math.sin(Math.toRadians(yaw));

                    }

                }

                if (position.z - distance * (float) Math.sin(Math.toRadians(yaw)) > position.z) {
                    //System.out.println("nord");
                    if (!caseActuelle.getMurNord() || caseActuelle.getPositionZ() > Math.abs(position.z + distance * (float) Math.sin(Math.toRadians(yaw)))) {
                        //System.out.println( " caseZ " + caseActuelle.getPositionZ() + " persoZ " + Math.abs( Math.floor(position.z) ));

                        position.z += distance * (float) Math.cos(Math.toRadians(yaw));
                    }

                } else {
                    //System.out.println("sud");

                    if (!caseActuelle.getMurSud() || caseActuelle.getPositionZ() < Math.abs(position.z + distance * (float) Math.sin(Math.toRadians(yaw))) + 0.5) {
                        position.z += distance * (float) Math.cos(Math.toRadians(yaw));

                    }
                }

            } else if (caseActuelle.getMonstre() && !flagCombat) {//signal de monstre

                // System.out.println("rencontre avec un monstre le combat debute");
                flagCombat = true;
                monstre = true;

                Display.destroy();
                this.setVisible(false);

                try {
                    leJeu.setOrientation(this.yaw);
                    positionDansCarte[0] = position.x;
                    positionDansCarte[1] = position.y;
                    positionDansCarte[2] = position.z;
                    leJeu.setPositionDansCarte(positionDansCarte);
                    leJeu.combat(caseActuelle);

                    gameRunning = false;
                    super.dispose();
                    Display.destroy();
                    this.dispose();

                } catch (IOException ex) {
                    Logger.getLogger(FenetreDonjon3d.class.getName()).log(Level.SEVERE, null, ex);
                }
                //         this.dispose();                
                gameRunning = false;
                //            Display.destroy();

            }
        }

    }

    //recule la camera
    public void walkBackwards(Carte laCarte, float distance) {
   
        caseActuelle = trouverCaseActuelle(laCarte, position.x, position.z);

        if (caseActuelle != null) {

/*
            if (position.x + distance * (float) Math.sin(Math.toRadians(yaw)) < position.x) {//recule vers l'est
                //s'il n'y a pas de mur est bloquant ou qu'on est pas encore colle au mur
                if (!caseActuelle.getMurEst() || caseActuelle.getPositionX() > Math.abs(position.x + distance * (float) Math.sin(Math.toRadians(yaw))) - 0.5) {

                    position.x += distance * (float) Math.sin(Math.toRadians(yaw));

                }

            } else {//on recule vers l'ouest


                if (!caseActuelle.getMurOuest() || caseActuelle.getPositionX() > Math.abs(position.x + distance * (float) Math.sin(Math.toRadians(yaw)))) {

                    position.x += distance * (float) Math.sin(Math.toRadians(yaw));
                    // System.out.println("caseX " + caseActuelle.getPositionX() + " persoX  " + (Math.abs(Math.floor(position.x)) - 1) + " caseZ " + caseActuelle.getPositionZ() + " persoZ " + Math.abs(Math.floor(position.z)));

                }

            }*/
            /*
             if (position.z - distance * (float) Math.sin(Math.toRadians(yaw)) > position.z) {

             if (!caseActuelle.getMurNord() || caseActuelle.getPositionZ() > Math.abs(position.z - distance * (float) Math.sin(Math.toRadians(yaw)))) {

             position.z -= distance * (float) Math.cos(Math.toRadians(yaw));
             }

             } else {

             if (!caseActuelle.getMurSud() || caseActuelle.getPositionZ() < Math.abs(position.z - distance * (float) Math.sin(Math.toRadians(yaw)))) {

             position.z -= distance * (float) Math.cos(Math.toRadians(yaw));

             }
             }*/
        }
        
        position.x += distance * (float) Math.sin(Math.toRadians(yaw));
        position.z -= distance * (float) Math.cos(Math.toRadians(yaw));

    }

    //deplacement du regard
    public void lookThrough() {
        glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        glTranslatef(position.x, position.y, position.z);
    }
    private static boolean gameRunning = true;
    private static int targetWidth = 1024;
    private static int targetHeight = 768;

    private void init() throws IOException {

        String cheminJeu = new File(".").getCanonicalPath(); //Trouve le dossier de l'application :

        cheminTexturePlancher = cheminJeu + "/textures/beton.jpg";
        cheminTextureMur = cheminJeu + "/textures/murPierre.jpg";
        textureTexturePlafond = cheminJeu + "/textures/plafond.jpg";
        textureTextureMonstre = cheminJeu + "/textures/monstre.jpg";

        texturePlancher = TextureLoader.getTexture("JPG", new FileInputStream(cheminTexturePlancher));
        textureMur = TextureLoader.getTexture("JPG", new FileInputStream(cheminTextureMur));
        texturePlafond = TextureLoader.getTexture("JPG", new FileInputStream(textureTexturePlafond));
        textureMonstre = TextureLoader.getTexture("JPG", new FileInputStream(textureTextureMonstre));

    }
    private float xrot = 0.1f;
    private float yrot = 0.1f;
    private float zrot = 0.1f;

    private static void initDisplay(boolean fullscreen) {

        DisplayMode chosenMode = null;

        try {
            DisplayMode[] modes = Display.getAvailableDisplayModes();

            for (int i = 0; i < modes.length; i++) {
                if ((modes[i].getWidth() == targetWidth) && (modes[i].getHeight() == targetHeight)) {
                    chosenMode = modes[i];
                    break;
                }
            }
        } catch (LWJGLException e) {
            Sys.alert("Erreur", "incapable de determiner le mode d affichage");
            System.exit(0);
        }

        // at this point if we have no mode there was no appropriate, let the user know
        // and give up
        if (chosenMode == null) {
            Sys.alert("Erreur", "pas de mode d affichage trouve");
            System.exit(0);
        }

        try {
            Display.setDisplayMode(chosenMode);
            Display.setFullscreen(fullscreen);
            Display.setTitle("RPG");
            Display.create();

        } catch (LWJGLException e) {
            Sys.alert("Erreur", "Incapable d ouvrir la fenetre.");
            System.exit(0);
        }

    }

    private static boolean initGL() {
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();

        GLU.gluPerspective(45.0f, ((float) targetWidth) / ((float) targetHeight), 0.1f, 100.0f);
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();

        glEnable(GL_TEXTURE_2D);
        glShadeModel(GL_SMOOTH);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glClearDepth(1.0f);
        glEnable(GL_DEPTH_TEST);
        glDepthFunc(GL_LEQUAL);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        return true;
    }

    public boolean isKeyPressed(int keyCode) {


        switch (keyCode) {
            case KeyEvent.VK_SPACE:
                keyCode = Keyboard.KEY_SPACE;
                break;
            case KeyEvent.VK_ESCAPE:
                keyCode = Keyboard.KEY_ESCAPE;
                break;
            case KeyEvent.VK_W:
                keyCode = Keyboard.KEY_W;
                break;
            case KeyEvent.VK_A:
                keyCode = Keyboard.KEY_A;
                break;
            case KeyEvent.VK_S:
                keyCode = Keyboard.KEY_S;
                break;
            case KeyEvent.VK_D:
                keyCode = Keyboard.KEY_D;
                break;
            case KeyEvent.VK_I:
                keyCode = Keyboard.KEY_I;
                break;
        }

        return org.lwjgl.input.Keyboard.isKeyDown(keyCode);
    }

    private void run() {
        FenetreDonjon3d camera = new FenetreDonjon3d(leJeu);
        //camera.yaw(90.0f);      //Place la camera dans le bon sens
        camera.yaw(orientation);
        float dx = 0.0f;
        float dy = 0.0f;
        float dt = 0.0f; //longueur des frame
        float lastTime = 0.0f;
        float time = 0.0f;

        float mouseSensitivity = 0.15f;
        float movementSpeed = 10.0f; //10 unite par seconde

        //hide the mouse
        Mouse.setGrabbed(false);
        while (gameRunning) {

            update();
            render();
            Display.update();
            time = Sys.getTime();

            //vitesse de deplacement
            dt = 0.00009f;

            lastTime = time;
            if (monstre || flagCombat) {
                // System.out.println("monstre = " + monstre + " flag = " + flagCombat);
            }

            if (!flagCombat && !monstre) {


                if (gameRunning && Keyboard.isKeyDown(Keyboard.KEY_W))//avancer
                {
                    // System.out.println("x: " + position.x + " z: " + position.z);
                    camera.walkForward(laCarte, movementSpeed * dt);
                }
                if (gameRunning && Keyboard.isKeyDown(Keyboard.KEY_S))//reculer
                {
                    camera.walkBackwards(laCarte, movementSpeed * dt);
                }
                if (gameRunning && Keyboard.isKeyDown(Keyboard.KEY_A))//deplacement vers la gauche
                {
                    //tourne sur l'axe des X vers la droite
                    for (int i = 0; i < 10; ++i) {
                        camera.yaw(-0.01f);
                    }

                }
                if (gameRunning && Keyboard.isKeyDown(Keyboard.KEY_D))//deplacement vers la droite
                {
                    //tourne sur l'axe des X vers la gauche
                    for (int i = 0; i < 10; ++i) {
                        camera.yaw(0.01f);
                    }
                }
                if(gameRunning && Keyboard.isKeyDown(Keyboard.KEY_I))
                {
                     Display.destroy();
                     this.setVisible(false);

                     leJeu.setOrientation(camera.yaw);
                     leJeu.setPositionDansCarte(new float[]{camera.position.x,camera.position.y,camera.position.z});
                     leJeu.gestionInventaire();

                    gameRunning = false;
                    super.dispose();
                    Display.destroy();
                    this.dispose();
              
                    gameRunning = false;

                }

            }
            //chargement de la matrice identitee
            glLoadIdentity();

            camera.lookThrough();

            if (Display.isCloseRequested()) {
                gameRunning = false;
                Display.destroy();
                System.exit(0);
            }
            if (gameRunning && Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
                Sys.alert("Close", "To continue, press ESCAPE on your keyboard or OK on the screen.");

                if (fermePasProgramme) {
                    gameRunning = false;
                    Display.destroy();
                    this.dispose();
                } else {
                    System.exit(0);
                }

            }

        }
    }

    private void update() {
        xrot += 0.1f;
        yrot += 0.1f;
        zrot += 0.1f;
    }

    private void colorer(float r, float g, float b) {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glColor3f(r, g, g);
    }

    private void plancher() {

        glBegin(GL_QUADS);
        colorer(0.6f, 0.6f, 0.3f);
        glVertex3f(0.0f, -0.01f, -1.0f);
        glVertex3f(21.0f, -0.01f, -1.0f);
        glVertex3f(21.0f, -0.01f, 20.0f);
        glVertex3f(0.0f, 0.0f, 20.0f);
        glEnd();
    }

    private void dessinerCase(Case uneCase) {

        textureMur.bind();
        if (uneCase.getMurSud()) {

            glBegin(GL_QUADS);
            glTexCoord2f(0.0f, 0.0f);
            glVertex3f(0.0f, 0.0f, 0.0f);
            glTexCoord2f(textureMur.getWidth(), 0.0f);
            glVertex3f(1.0f, 0.0f, 0.0f);
            glTexCoord2f(textureMur.getWidth(), textureMur.getHeight());
            glVertex3f(1.0f, 1.0f, 0.0f);
            glTexCoord2f(0.0f, textureMur.getHeight());
            glVertex3f(0.0f, 1.0f, 0.0f);
            glEnd();
        }
        // mur nord
        if (uneCase.getMurNord()) {

            glBegin(GL_QUADS);
            glTexCoord2f(0.0f, 0.0f);
            glVertex3f(0.0f, 0.0f, -1.0f);
            glTexCoord2f(textureMur.getWidth(), 0.0f);
            glVertex3f(1.0f, 0.0f, -1.0f);
            glTexCoord2f(textureMur.getWidth(), textureMur.getHeight());
            glVertex3f(1.0f, 1.0f, -1.0f);
            glTexCoord2f(0.0f, textureMur.getHeight());
            glVertex3f(0.0f, 1.0f, -1.0f);
            glEnd();
        }

        // plafond

        texturePlafond.bind();
        glBegin(GL_QUADS);
        glTexCoord2f(0.0f, texturePlafond.getHeight());
        glVertex3f(0.0f, 1.0f, 0.0f);
        glTexCoord2f(0.0f, 0.0f);
        glVertex3f(1.0f, 1.0f, 0.0f);
        glTexCoord2f(texturePlafond.getWidth(), 0.0f);
        glVertex3f(1.0f, 1.0f, -1.0f);
        glTexCoord2f(texturePlafond.getWidth(), texturePlafond.getHeight());
        glVertex3f(0.0f, 1.0f, -1.0f);

        glEnd();

        // plancher

        texturePlancher.bind();
        glBegin(GL_QUADS);

        glTexCoord2f(texturePlancher.getWidth(), texturePlancher.getHeight());
        glVertex3f(0.0f, 0.0f, 0.0f);
        glTexCoord2f(0.0f, texturePlancher.getHeight());
        glVertex3f(1.0f, 0.0f, 0.0f);
        glTexCoord2f(0.0f, 0.0f);
        glVertex3f(1.0f, 0.0f, -1.0f);
        glTexCoord2f(texturePlancher.getWidth(), 0.0f);
        glVertex3f(0.0f, 0.0f, -1.0f);

        glEnd();

        // mur est
        textureMur.bind();
        glBegin(GL_QUADS);
        if (uneCase.getMurEst()) {

            glTexCoord2f(textureMur.getWidth(), 0.0f);glVertex3f(1.0f, 0.0f, 0.0f);
            glTexCoord2f(textureMur.getWidth(), textureMur.getHeight());glVertex3f(1.0f, 1.0f, 0.0f);
            glTexCoord2f(0.0f, textureMur.getHeight());glVertex3f(1.0f, 1.0f, -1.0f);
            glTexCoord2f(0.0f, 0.0f);glVertex3f(1.0f, 0.0f, -1.0f);
        }
        // mur ouest

        if (uneCase.getMurOuest()) {

            glTexCoord2f(textureMur.getWidth(), 0.0f);glVertex3f(0.0f, 0.0f, 0.0f);
            glTexCoord2f(textureMur.getWidth(), textureMur.getHeight());glVertex3f(0.0f, 1.0f, 0.0f);
            glTexCoord2f(0.0f, textureMur.getHeight());glVertex3f(0.0f, 1.0f, -1.0f);
            glTexCoord2f(0.0f, 0.0f);glVertex3f(0.0f, 0.0f, -1.0f);
        }
        glEnd();
        
        textureMonstre.bind();
        glBegin(GL_QUADS);
        if (uneCase.getMonstre()) {

            glTexCoord2f(0.0f, textureMonstre.getHeight());glVertex3f(0.25f, 0.0f, -0.25f);
            glTexCoord2f(0.0f, 0.0f);glVertex3f(0.25f, 0.7f, -0.25f);
            glTexCoord2f(textureMonstre.getWidth(), 0.0f );glVertex3f(0.25f, 0.7f, -0.75f);
            glTexCoord2f(textureMonstre.getWidth(), textureMonstre.getHeight() );glVertex3f(0.25f, 0.0f, -0.75f);
            
            glTexCoord2f(0.0f, textureMonstre.getHeight());glVertex3f(0.25f, 0.0f, -0.25f);
            glTexCoord2f(0.0f, 0.0f);glVertex3f(0.25f, 0.7f, -0.25f);
            glTexCoord2f(textureMonstre.getWidth(), 0.0f );glVertex3f(0.75f, 0.7f, -0.5f);
            glTexCoord2f(textureMonstre.getWidth(), textureMonstre.getHeight() );glVertex3f(0.75f, 0.0f, -0.5f);
            
            glTexCoord2f(0.0f, textureMonstre.getHeight());glVertex3f(0.75f, 0.0f, -0.5f);
            glTexCoord2f(0.0f, 0.0f);glVertex3f(0.75f, 0.7f, -0.5f);
            glTexCoord2f(textureMonstre.getWidth(), 0.0f );glVertex3f(0.25f, 0.7f, -0.75f);
            glTexCoord2f(textureMonstre.getWidth(), textureMonstre.getHeight() );glVertex3f(0.25f, 0.0f, -0.75f);
            
            
          
        }
        glEnd();

    }

    private void positionnerCase(Case UneCase) {

        glPushMatrix();
        glTranslatef(UneCase.getPositionX(), 0, UneCase.getPositionZ());
        dessinerCase(UneCase);
        glPopMatrix();

    }

    private void render() {

        glClear(GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        //GL11.glLoadIdentity();
        // plancher();


        for (int i = 0; i < laCarte.getLargeur(); ++i) {

            for (int j = 0; j < laCarte.getHauteur(); ++j) {

                if (laCarte.getCase(i, j) != null) {

                    positionnerCase(laCarte.getCase(i, j));

                }

            }

        }

    }
}
