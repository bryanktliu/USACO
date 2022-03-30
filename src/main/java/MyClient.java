import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyClient {

    public static void main(String[] args) throws IOException {
        String userHome = System.getProperty("user.home");
        Socket socket = new Socket("localhost", 9001);
        try (BufferedReader in =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out =
                        new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            String command;
            while ((command = input.readLine()) != null) {
                if (command.equals("bye")) {
                    break;
                } else if (command.startsWith("download ")) {
                    out.write(command);
                    out.newLine();
                    out.flush();
                    String i = in.readLine();
                    if (!i.equals("File does not exist.") && !i.equals("Cannot download folder.")) {
                        Path outputFile =
                                Paths.get(userHome, "Downloads/output/", command.split(" ", 2)[1]);
                        try (BufferedInputStream inFile =
                                        new BufferedInputStream(socket.getInputStream());
                                BufferedOutputStream outFile =
                                        new BufferedOutputStream(
                                                Files.newOutputStream(outputFile))) {
                            int size = Integer.parseInt(i);
                            for (int j = 0; j < size; ++j) {
                                outFile.write(in.read());
                            }
                        }
                    } else {
                        System.out.println(i);
                        in.readLine();
                    }
                } else {
                    out.write(command);
                    out.newLine();
                    out.flush();
                    String i = in.readLine();
                    while (i != null && !i.equals("")) {
                        System.out.println(i);
                        i = in.readLine();
                    }
                }
            }
        }
    }
}
