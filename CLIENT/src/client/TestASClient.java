package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestASClient {

    /**
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {


        try(Socket socket = new Socket("127.0.0.2", 8080);
            BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
            DataOutputStream oos = new DataOutputStream(socket.getOutputStream());
            DataInputStream ois = new DataInputStream(socket.getInputStream());	)
        {

            System.out.println("Client connected to socket.");
            System.out.println();
            System.out.println("Input command (getQuote or quit) and press ENTER");


            while(!socket.isOutputShutdown()){
                if(br.ready()){

                    System.out.println("Client start writing in channel...");

                    String clientCommand = br.readLine();

                    oos.writeUTF(clientCommand);
                    oos.flush();

                    System.out.println("Client wrote & start waiting for data from server...");
                    Thread.sleep(2000);

                    if(ois.available()!=0)		{
                        System.out.println("reading...");
                        String in = ois.readUTF();
                        if(in.equalsIgnoreCase("quit")){

                            System.out.println("Client kill connections");
                            Thread.sleep(2000);

                            if(ois.available()!=0)		{
                                System.out.println("reading...");
                                in = ois.readUTF();
                                System.out.println(in);
                            }

                            break;
                        }
                        System.out.println(in);
                    }
                }
            }

            System.out.println("Closing connections & channels on clentSide - DONE.");

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
