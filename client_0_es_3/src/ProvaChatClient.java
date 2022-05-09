
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author aldro
 */
public class ProvaChatClient {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Inserisci il nickname: ");
        ChatClient chat = new ChatClient(sc.next());
    }
}
