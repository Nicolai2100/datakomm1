import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author s185020
 */
public class Client2 {

    /**
     * Open a terminal and type /sbin/ifconfig Find the ip-address and run this
     * code
     */
    public static void main(String argv[]) throws IOException {
        Scanner scan = new Scanner(System.in);


//thinlinc - Try /sbin/ifconfig

        System.out.println("Press: \n1 for connection to localhost\n2 for connection to the xterm");

        int choice = scan.nextInt();

        if (choice == 2) {

            while (true) {

                //thinlinc - Try /sbin/ifconfig
                try {
                    String host = "127.0.0.1";
                    System.out.println("\nClient: Attempting connection... \n");
                    Socket socketClient = new Socket(host, 25);
                    Scanner sc1 = new Scanner(socketClient.getInputStream());
                    System.out.println("Client: " + "Mail Service Connection Established");

                    BufferedReader reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

                    //localhost = ip adress
                    String serverMsg;
                    if ((serverMsg = reader.readLine()) != null) {
                        System.out.println("Server: " + serverMsg);
                        writer.write("helo telnet\r\n");
                        writer.flush();

                        serverMsg = reader.readLine();
                        System.out.println("Server: " + serverMsg);
                        System.out.println("Server: enter sender's email:");

                        //String emailFrom = scan.next();
                        writer.write("MAIL FROM: nicolailarsen2100@gmail.com\r\n");
                        //writer.write("MAIL FROM: " + emailFrom + "\r\n");
                        writer.flush();
                        serverMsg = reader.readLine();
                        System.out.println("Server: " + serverMsg);
                        System.out.println("Server: enter receiver's mail:");

//System.out.println("Server: enter receiver's mail:");

                        //String emailTo = scan.next();
                        writer.write("RCPT TO: nicolailarsen2100@gmail.com\r\n");

                        //writer.write("RCPT TO: " + emailTo + "\r\n");
                        writer.flush();
                        serverMsg = reader.readLine();
                        System.out.println("Server: " + serverMsg);


                        writer.write("DATA\r\n");
                        writer.flush();
                        serverMsg = reader.readLine();
                        System.out.println("Client: " + serverMsg);
                        System.out.println("Server: Enter content: ");
                        String content = scan.next();
                        writer.write("CONTENT: " + content + "\r\n");
                        writer.flush();

                        String input;
                        boolean endMessage = true;

                        do {
                            input = scan.next();
                            writer.write(input + "\r\n");

                            if (reader.ready()) {
                                serverMsg = reader.readLine();
                                if (serverMsg != null) {
                                    System.out.println("Client: " + serverMsg);
                                }
                            }
                            if (input.charAt(0) == '.') {
                                System.out.println(true);
                                endMessage = false;

                            }
                        } while (endMessage);
                        writer.flush();

                        serverMsg = reader.readLine();
                        System.out.println("Client: " + serverMsg);

                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }
    }
}


