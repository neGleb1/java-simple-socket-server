/*package "YOUR PACKAGE NAME";*/

import java.io.*;
import java.net.*;

//simple socket server, that listens 8080 port and displays some string
public class HttpServer {
    
    public static void main(String[] args) throws IOException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        
        System.out.println("Server started on port " + port);
        
        while (true) {
            Socket clientSocket = serverSocket.accept();
            
            System.out.println("Client connected from " + clientSocket.getInetAddress());
            
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            
            String request = in.readLine();
            System.out.println("Received request: " + request);
            
            if (request.startsWith("GET")) {
                out.writeBytes("HTTP/1.1 200 OK\r\n");
                out.writeBytes("Content-Type: text/html\r\n");
                out.writeBytes("\r\n");
                out.writeBytes("<html><head><title>Java HTTP Server</title></head><body><h1>Hello World!</h1></body></html>");
            } else {
                out.writeBytes("HTTP/1.1 400 Bad Request\r\n");
            }
            
            out.flush();
            out.close();
            in.close();
            clientSocket.close();
            
            System.out.println("Client disconnected");
        }
    }
}
