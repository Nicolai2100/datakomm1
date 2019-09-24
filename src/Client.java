import java.io.*;
import java.net.Socket;
import java.util.Scanner;


// /sbin/ifconfig

public class Client {
    public static void main(String argv[]) throws IOException {
        Scanner scan = new Scanner(System.in);

        //thinlinc - Try /sbin/ifconfig

        try {
         //   System.out.println("Press: \n1 for connection to localhost\n2 for connection to the xterm");
/*
            int choice = scan.nextInt();

            if (choice == 1) {
                //localhost = ip adress
                Socket socketClient = new Socket("localhost", 5555);
                System.out.println("Client: " + "Connection Established");
                BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));
                String serverMsg;
                writer.write("8\r\n");
                writer.write("10\r\n");
                writer.flush();
                while ((serverMsg = reader.readLine()) != null) {
                    System.out.println("Client: " + serverMsg);
                }
            }
            // if (choice == 2)
*/

//            String host3 = "10.66.27.21";
            //6789




//            String host3 = "127.0.0.1";
            String host3 = "127.0.0.1";
            System.out.println("Attempting connection... \n");
            Socket socketClient = new Socket(host3, 25);
            Scanner sc1 = new Scanner(socketClient.getInputStream());
            System.out.println("Client: " + "Connection Established");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

            //localhost = ip adress
            String serverMsg;




            while ((serverMsg = reader.readLine()) != null) {
                System.out.println("Client: " + serverMsg);
            }
            writer.write("helo telnet\r\n");
            writer.flush();
            while ((serverMsg = reader.readLine()) != null) {
                System.out.println("Client: " + serverMsg);
            }
            writer.write("MAIL FROM: daneskjold@daneskjold.dk\r\n");
            writer.flush();
            while ((serverMsg = reader.readLine()) != null) {
                System.out.println("Client: " + serverMsg);
            }
            writer.write("RCPT TO: nicolailarsen2100@gmail.com\r\n");
            writer.flush();
            while ((serverMsg = reader.readLine()) != null) {
                System.out.println("Client: " + serverMsg);
            }
            writer.write("DATA\r\n");
            writer.flush();
            while ((serverMsg = reader.readLine()) != null) {
                System.out.println("Client: " + serverMsg);
            }
            writer.write("CONTENT: \r\n");
            writer.flush();
            while ((serverMsg = reader.readLine()) != null) {
                System.out.println("Client: " + serverMsg);
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }
}
