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
import java.awt.List;
import java.net.*;
import javax.swing.JOptionPane;

public class ThreadGestioneServizioChat implements Runnable {

    private int nrMaxConnessioni = 10;
    private List lista;
    private ThreadChatConnessioni[] listaConnessioni;
    Thread me;
    private ServerSocket serverChat;

    public ThreadGestioneServizioChat(int numeroMaxConnessioni, List lista) {
        this.nrMaxConnessioni = nrMaxConnessioni - 1;
        this.lista = lista;
        this.listaConnessioni = new ThreadChatConnessioni[this.nrMaxConnessioni];
        me = new Thread(this);
        me.start();
    }

    public void run() {
        boolean continua = true;
        //instanzio la connessione del server per la chat
        try {
            serverChat = new ServerSocket(6789);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Impossibile instanziare il server");
            continua = false;
        }

        if (continua) {
            //accetto le connessioni chat
            try {
                for (int xx = 0; xx < nrMaxConnessioni; xx++) {
                    Socket tempo = null;
                    tempo = serverChat.accept();
                    listaConnessioni[xx] = new ThreadChatConnessioni(this, tempo);
                }
                serverChat.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Impossibile instanziare server chat");
            }
        }
    }//fine metodo "run"

    public void spedisciMessaggio(String mex, String nickname) {
        //scrivo il mex nella mia lista
        String toSend = nickname+": "+mex;
        lista.add(toSend);
        lista.select(lista.getItemCount() - 1);
        //mando il messaggio agli altri
        for (int xx = 0; xx < this.nrMaxConnessioni; xx++) {
            if (listaConnessioni[xx] != null) {
                listaConnessioni[xx].spedisciMessaggioChat(toSend);
            }
        }
    }
}//fine classe ThreadGestioneServizioChat
