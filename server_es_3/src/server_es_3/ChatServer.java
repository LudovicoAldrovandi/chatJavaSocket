/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_es_3;

/**
 *
 * @author aldro
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatServer extends JFrame {

    public ChatServer() {
        super("Chat Server");
        this.setSize(new Dimension(500, 300)); // setto la grandezza della finestra 
        this.setLocationRelativeTo(null);     // la metto al centro dello schermo
        this.setEnabled(true);                // setto la proprietà enable
        this.setBackground(Color.blue);	      // setto il colore di sfondo
        //creo il pannello per l'inserimento e la visualizzazione dei messaggi
        PannelloChatServer pan = new PannelloChatServer();
        this.getContentPane().add(pan);
        this.setVisible(true);
    }
}
