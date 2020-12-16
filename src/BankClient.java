import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;

public class BankClient extends JFrame {
    Currencies currency;
    Map<JTextField, Double> Text;
    static int serverPort = 6666; // здесь обязательно нужно указать порт к которому привязывается сервер.
    static String address = "127.0.0.1"; //IP-адрес сервера, он же адрес компьютера клиента.
    DataInputStream dis;
    DataOutputStream dos;
    BankClient() {
        super("bla-bla-bla");
        try {
            InetAddress ipAddress = InetAddress.getByName(address); // создаем объект IP-адреса.
            System.out.println("IP-addres: " + address + " port " + serverPort );
            Socket socket = new Socket(ipAddress, serverPort); // создаем сокет с известным IP и портом.
            System.out.println("Сокет создан");

            // Та же хрень с потоками. Сначала потоки ввода-вывода, а потом явно
            //указываем, что это потоки данных (значения переменных, строки...)
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            dis = new DataInputStream(is);
            dos = new DataOutputStream(os);
        } catch (Exception x) {

        }
        currency = new Currencies();
        setSize(400, 400);
        JLabel usd = new JLabel("USD");
        JLabel yena = new JLabel("JPN");
        JLabel ruble = new JLabel("BYN");
        JLabel zloty = new JLabel("PLN");
        JLabel euro = new JLabel("EUR");
        JLabel manat = new JLabel("TMT");
        JLabel uganda = new JLabel("UGX");
        JTextField USD = new JTextField("", 50);
        USD.setMaximumSize(USD.getPreferredSize());
        JTextField YENA = new JTextField("", 50);
        YENA.setMaximumSize(YENA.getPreferredSize());
        JTextField RUBLE = new JTextField("", 50);
        RUBLE.setMaximumSize(RUBLE.getPreferredSize());
        JTextField ZLOTY = new JTextField("", 50);
        ZLOTY.setMaximumSize(ZLOTY.getPreferredSize());
        JTextField EURO = new JTextField("", 50);
        EURO.setMaximumSize(EURO.getPreferredSize());
        JTextField MANAT = new JTextField("", 50);
        MANAT.setMaximumSize(MANAT.getPreferredSize());
        JTextField UGANDA = new JTextField("", 50);
        UGANDA.setMaximumSize(UGANDA.getPreferredSize());
        JButton convert = new JButton("CONVERT");
        Text = new HashMap<>();
        Text.put(USD, currency.GetCurrency(0, 0));
        Text.put(YENA, currency.GetCurrency(1, 0));
        Text.put(RUBLE, currency.GetCurrency(2, 0));
        Text.put(ZLOTY, currency.GetCurrency(3, 0));
        Text.put(EURO, currency.GetCurrency(4, 0));
        Text.put(MANAT, currency.GetCurrency(5, 0));
        Text.put(UGANDA, currency.GetCurrency(6, 0));
        convert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {


                try {
                    // Создаем поток для чтения с клавиатуры.
                    //BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
                    String A;
                    Double B;

                    A = WhatValue().getKey().getText();
                    B = WhatValue().getValue();// = keyboard.readLine(); // ждем пока пользователь введет что-то и нажмет кнопку Enter.
                    System.out.println("Отправка числа клиенту...");
                    dos.writeUTF(A);
                    dos.writeDouble(B);//.writeUTF(line); // отсылаем введенную строку текста серверу.
                    dos.flush(); // заставляем поток закончить передачу данных.
                    for (Map.Entry<JTextField, Double> item : Text.entrySet()) {
                        dos.writeDouble(item.getValue());
                        item.getKey().setText(dis.readUTF());
                    }
                    // dos.flush();
                } catch (Exception x) {



                    /*
                    x.printStackTrace();
                Map.Entry<JTextField, Double> value;
               value = WhatValue();
                System.out.println(value.getKey().getText());
               try {
                   for (Map.Entry<JTextField, Double> item : Text.entrySet()) {
                       item.getKey().setText(String.valueOf(item.getValue() / value.getValue() * Double.parseDouble(value.getKey().getText())));
                   }
               }catch(NumberFormatException a){
                   System.out.println("XD");
               }

            */
                }
        }});
        JButton clear = new JButton("CLEAR");
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                for (Map.Entry<JTextField, Double> item : Text.entrySet()) {
                    item.getKey().setText("");
                }
            }
        });
        Box first = Box.createHorizontalBox();
        first.add(Box.createHorizontalGlue());
        first.add(usd);
        first.add(Box.createHorizontalStrut(10));
        first.add(USD);
        first.add(Box.createHorizontalGlue());
        Box second = Box.createHorizontalBox();
        second.add(Box.createHorizontalGlue());
        second.add(yena);
        second.add(Box.createHorizontalStrut(10));
        second.add(YENA);
        second.add(Box.createHorizontalGlue());
        Box third = Box.createHorizontalBox();
        third.add(Box.createHorizontalGlue());
        third.add(ruble);
        third.add(Box.createHorizontalStrut(10));
        third.add(RUBLE);
        third.add(Box.createHorizontalGlue());
        Box Final = Box.createHorizontalBox();
        Final.add(Box.createHorizontalGlue());
        Final.add(convert);
        Final.add(Box.createHorizontalStrut(100));
        Final.add(clear);
        Final.add(Box.createHorizontalGlue());
        Box Final2_0 = Box.createVerticalBox();
        Final2_0.add(Box.createVerticalGlue());
        Final2_0.add(first);
        Final2_0.add(second);
        Final2_0.add(third);
        Final2_0.add(Final);
        Final2_0.add(Box.createVerticalGlue());
        getContentPane().add(Final2_0);
    }
    public Map.Entry<JTextField, Double> WhatValue() {
        for (Map.Entry<JTextField, Double> item : Text.entrySet()) {
            if (!item.getKey().getText().equals("")) {

                return item;
            }
        }
        return null;
    }


        public static void main(String[] args)
        {
            BankClient Oleg = new BankClient();
            Oleg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Oleg.setVisible(true);

            }
        }


