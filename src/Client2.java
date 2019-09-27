import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author s185020 - Nicolai J. Larsen
 */
public class Client2 {
    /**
     * This code sould be run through a Thinlinc client on the gbar-server.
     */
    public static void main(String argv[]) throws IOException {
        Scanner scan = new Scanner(System.in);
        BufferedReader reader = null;
        BufferedWriter writer = null;

        try {
            String host = "127.0.0.1";
            System.out.println("\nClient: Attempting connection... \n");

            while (true) {
                Socket socketClient = new Socket(host, 25);
                Scanner sc1 = new Scanner(socketClient.getInputStream());
                System.out.println("Client: " + "Mail Service Connection Established");

                reader = new BufferedReader(new InputStreamReader(socketClient.getInputStream()));
                writer = new BufferedWriter(new OutputStreamWriter(socketClient.getOutputStream()));

                String serverMsg;
                if ((serverMsg = reader.readLine()) != null) {
                    System.out.println("Server: " + serverMsg);
                    writer.write("helo telnet\r\n");
                    writer.flush();

                    serverMsg = reader.readLine();
                    System.out.println("Server: " + serverMsg);
                    System.out.println("Server: enter sender's email:");

                    //String emailFrom = scan.next();
                    writer.write("MAIL FROM: HeksiaDeTrix@Andeby.com\r\n");
                    //writer.write("MAIL FROM: " + emailFrom + "\r\n");
                    writer.flush();
                    serverMsg = reader.readLine();
                    System.out.println("Server: " + serverMsg);
                    System.out.println("Server: enter receiver's email address:");

                    String emailTo = "nicolailarsen2100@gmail.com";
                    //String emailTo = scan.next();
                    writer.write("RCPT TO: emailTo\r\n");

                    //writer.write("RCPT TO: " + emailTo + "\r\n");
                    writer.flush();
                    serverMsg = reader.readLine();
                    System.out.println("Server: " + serverMsg);
                    writer.write("DATA\r\n");
                    writer.flush();
                    serverMsg = reader.readLine();
                    System.out.println("Client: " + serverMsg);
                    System.out.println("Server: Enter content: ");
                    String subjectString = scan.next();
                    writer.write("SUBJECT: " + subjectString + "\r\n");
                    writer.flush();

                    String input;
                    boolean endMessage = true;

                    do {
                        input = scan.next();
                        writer.write(input + "\r\n");

                        if (input.charAt(0) == '.') {
                            endMessage = false;
                        }
                    } while (endMessage);
                    writer.flush();

                    serverMsg = reader.readLine();
                    System.out.println("Client: " + serverMsg);
                }
            }
        } catch (
                Exception e) {
            System.out.println("Client: Failed connecting to server!");
            System.out.println(e.getMessage());
        }
    }
}

