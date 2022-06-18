package server;

import server.log.WriteLog;
import server.quotegen.QuoteGenerator;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Date;


public class Server implements Runnable {

    private static Socket clientServer;
    final int maxQuotes = 2;
    private int countQuotes = 0;
    //static final String adress = "localhost";
    //static final int port = 8080;

    public Server(Socket socket) {
        Server.clientServer = socket;
    }


    @Override
    public void run() {
        try {
            System.out.println("Connection acceped. Port client: " + clientServer.getPort());

            //Канал записи в сокет
            DataOutputStream out = new DataOutputStream(clientServer.getOutputStream());
            DataInputStream in = new DataInputStream(clientServer.getInputStream());

            WriteLog log = new WriteLog(clientServer, new Date());
            //Autorization aut = new Autorization();

            while (!clientServer.isClosed()) {
                System.out.println("Server reading from channel");
//                out.writeUTF("Input login");
//                aut.setLogin(in.readUTF());
//                System.out.println(aut.getLogin());
//                out.writeUTF("Input password");
//                aut.setPassword(in.readUTF());

//                if (aut.checkAutorization()) {
                    String entry = in.readUTF();

                    System.out.println("READ from client - " + entry);

                    if (entry.equalsIgnoreCase("quit")) {
                        System.out.println("Client initialize connection suicide");
                        out.writeUTF("quit");
                        //out.writeUTF("Server is stop.");
                        out.flush();
                        break;
                    } else if (countQuotes == maxQuotes) {         //если достигнуто максимальное число цитат
                        out.writeUTF("quit");
                        out.writeUTF("The maximum number of citations has been reached!");
                        out.flush();
                        break;
                    } else if (entry.equalsIgnoreCase("getQuote")) {
                        String quote = QuoteGenerator.returnQuote();
                        log.addQuotes(quote);
                        Thread.sleep(2000);
                        out.writeUTF(quote);
                        System.out.println("Quote is sended");
                        out.flush();
                        countQuotes++;
                    } else {
                        out.writeUTF("Incorrect command! Try again.");
                        out.flush();
                    }
                }

                log.setTimeDisconnection(new Date().toString());
                log.WriteInFile();


//            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}