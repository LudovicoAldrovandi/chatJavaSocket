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
import java.net.*;
import java.io.*;

public class ThreadChatConnessioni implements Runnable {

    private ThreadGestioneServizioChat gestoreChat;
    private Socket client = null;
    private BufferedReader input = null;
    private PrintWriter output = null;
    private String nickname = null;
    private int interazioni = 0;
    Thread me;

    public ThreadChatConnessioni(ThreadGestioneServizioChat gestoreChat, Socket client) {
        this.gestoreChat = gestoreChat;
        this.client = client;
        try {
            this.input = new BufferedReader(new InputStreamReader(client.getInputStream()));
            this.output = new PrintWriter(this.client.getOutputStream(), true);
            this.nickname = input.readLine();
        } catch (Exception e) {
            output.println("Errore nella lettura.");
        }
        me = new Thread(this);
        me.start();
    }

    public void run() {
        gestoreChat.spedisciMessaggio("si è unito alla conversazione.", nickname);
        while (true) {
            try {
                String mex = null;
                //rimango in attesa dei messaggi mandati dal client 
                while ((mex = input.readLine()) == null) {
                }
                if (mex.equals("disconnetto#")) {
                    
                    gestoreChat.spedisciMessaggio("DISCONNESSO, " + interazioni + " interazioni.", nickname);
                    break;
                } else {
                    gestoreChat.spedisciMessaggio(mex, nickname);
                    interazioni++;
                }
                //invoco il metodo del gestoreChat per ripetere a tutti il messaggio ricevuto

            } catch (Exception e) {
                output.println("Errore nella spedizione del messaggio a tutti.");
            }

        }
    }

    public void spedisciMessaggioChat(String messaggio) {
        try {
            output.println(messaggio);
        } catch (Exception e) {
            output.println("Errore nella spedizione del singolo messaggio.");
        }
    }
}
