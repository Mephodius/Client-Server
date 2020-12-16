import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Server {
    int port = 6666;// число как номер порта (что угодно в диапазоне 1025 - 65535)
    DataInputStream dis;
    DataOutputStream dos;
    Server(){
        try {
            ServerSocket ss = new ServerSocket(port); // создаем сокет сервера и привязываем его к выбранному порту
            System.out.println("Ожидание подключение клиента... ");

            Socket socket = ss.accept(); // метод для принятия подключений от клиента
            System.out.println("Клиент подключился"); //


            // Входной и выходной потоки (для СЕРВЕРА) для получения данных от КЛИЕНТА и посылка данных КЛИЕНТУ
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            // Потоки не знают, что за тип данных в них передаётся. Если строки или числа, то...
            //... то лучше переделать в другие потоки, чтобы указать, что мы текст передаём
            //даже если число, всё равно оно легко из строки выколупывается
            dis = new DataInputStream(is);
            dos = new DataOutputStream(os);



        } catch(Exception x) { x.printStackTrace(); }
    }
    public void Func() throws IOException {

        String A;
        Double B;//=inn.nextInt();
        A = dis.readUTF();
        B = dis.readDouble();
        //.readUTF(); // ожидаем пока клиент пришлет строку текста.
        System.out.println("Клиент прислал мне строку : " + A);
        System.out.println("Клиент прислал мне число : " + B);
        Double C;
        Double D;
        for (int i = 0; i < 7; i++) {
            C = dis.readDouble();
            // конец передачи данных.
            D = C * Double.parseDouble(A) / B;
            dos.writeUTF(String.valueOf(D));
        }
        //dos.flush();

    }
        public static void main(String[] args) throws IOException {
            Scanner inn=new Scanner(System.in);
            Server myserver = new Server();
            while(true) {
                myserver.Func();

            }
    }
        }


