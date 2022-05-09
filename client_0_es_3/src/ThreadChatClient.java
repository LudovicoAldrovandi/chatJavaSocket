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
import java.net.*;
import java.io.*;
import javax.swing.*;

public class ThreadChatClient implements Runnable {

    private List lista;
    Thread me;
    private Socket client;
    private BufferedReader input = null;
    private PrintWriter output = null;
    private String nickname = null;

    public ThreadChatClient(List lista, String ipServer, int porta, String nickname) {
        this.lista = lista;
        try {
            client = new Socket(ipServer, porta);
            this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.output = new PrintWriter(client.getOutputStream(), true);
            this.nickname = nickname;
            output.println(nickname);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Impossibile connettersi al server");
        }
        me = new Thread(this);
        me.start();
    }

    public void run() {
        //aspetto le ricezioni di messaggi e aggiungo i messaggi alla lista
        while (true) {
            try {
                String mex = null;
                while ((mex = input.readLine()) == null) {
                }
                lista.add(mex);
                lista.select(lista.getItemCount() - 1);
            } catch (Exception e) {

            }
           
        }
    }

    public void spedisciMessaggioChat(String messaggio) {
        try {
            output.println(messaggio);
        } catch (Exception e) {

        }
    }
    
    public void chiudiConnessione() throws IOException{
        client.close();
    }

}
