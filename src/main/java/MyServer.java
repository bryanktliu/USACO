import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MyServer extends Thread {

    Socket socket;

    public MyServer(Socket socket) {
        this.socket = socket;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(9001);
        while (true) {
            Socket socket1 = socket.accept();
            Thread t = new MyServer(socket1);
            t.start();
        }
    }

    public void run() {
        try (BufferedReader in =
                        new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out =
                        new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
            String input = in.readLine();
            while (input != null) {
                if (input.equals("ls")) {
                    for (Path p :
                            Files.list(Paths.get("/Users/bryan/Downloads")).toArray(Path[]::new)) {
                        if (Files.isDirectory(p)) {
                            out.write(p.getFileName() + "/");
                        } else {
                            out.write(String.valueOf(p.getFileName()));
                        }
                        out.newLine();
                    }
                } else if (input.startsWith("download ")) {
                    Path d = Paths.get("/Users/bryan/Downloads/", input.split(" ", 2)[1]);
                    if (Files.exists(d)) {
                        if (Files.isDirectory(d)) {
                            out.write("Cannot download folder.");
                            out.newLine();
                        } else {
                            out.write(String.valueOf(Files.size(d)));
                            out.newLine();
                            out.flush();
                            Files.copy(d, socket.getOutputStream());
                            out.newLine();
                            out.flush();
                        }
                    } else {
                        out.write("File does not exist.");
                        out.newLine();
                    }
                } else if (input.toLowerCase().equals("bye")) {
                    socket.close();
                    System.exit(1);
                } else {
                    out.write(input);
                    out.newLine();
                    out.newLine();
                }
                out.flush();
                input = in.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Client closed.");
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
