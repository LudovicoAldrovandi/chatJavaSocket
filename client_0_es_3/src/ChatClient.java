/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author aldro
 */
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ChatClient extends JFrame {

    public ChatClient(String nickname) {
        super("Client: "+nickname);

        //setto la grandezza della finestra 
        this.setSize(new Dimension(500, 300));
        //setto la posizione della finestra. in questo modo la mette al centro dello schermo
        this.setLocationRelativeTo(null);
        //setto la proprietà enable
        this.setEnabled(true);
        //setto il colore di sfondo
        this.setBackground(Color.blue);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //instanzio il pannello grafico per l'inserimento e la visualizzazione
        //dei messaggi
        PannelloChatClient pan = new PannelloChatClient(nickname);

        this.getContentPane().add(pan);

        this.setVisible(true);
        
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                try {
                    pan.disconnettiDalServer();
                } catch (IOException ex) {
                    Logger.getLogger(ChatClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }
    

}
