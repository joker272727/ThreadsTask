package com.sha.emi;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

public class App
{
    public static void main( String[] args )
    {
        Runnable thread1 = () -> {
            Scanner in = new Scanner(System.in);
            for(int i = 0; ; i++) {
                try (DataOutputStream is = new DataOutputStream(new FileOutputStream("file.txt", true))) {

                    String s = in.nextLine();
                    int o = stringToNumber(s);
                    is.writeInt(o);
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }
            }
        };

        Thread thread11 = new Thread(thread1);
        thread11.start();

        Runnable thread2 = () -> {

            int[] mas = new int[100];
            Scanner s = null;
            int min = 9999, numb = 0;
            for(int j = 0; ; j++) {
                int i = 0;
                try(DataInputStream os = new DataInputStream(new FileInputStream("file.txt"))) {
                    int value;
                    sleep(5000);

                    while (os.available() > 0) {
                        value = os.readInt();
                        mas[i] = value;
                        System.out.println(numberToString(value));
                        if(mas[i] < min) {
                            min = mas[i];
                            numb = i;
                        }
                        i++;
                    }

                } catch (IOException ioex) {
                    ioex.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try (DataOutputStream is = new DataOutputStream(new FileOutputStream("file.txt"))) {
                    for(int m = 0; m < i; m++) {
                        if(m != numb)
                            is.writeInt(mas[m]);
                    }
                } catch (IOException ioex) {
                    ioex.printStackTrace();
                }
            }


        };

        Thread threadd = new Thread(thread2);
        threadd.start();

        /*Scanner in = new Scanner(System.in);
        int i = in.nextInt();
        System.out.println(numberToString(i));*/


    }

    public static int stringToNumber(String input) {
        String[] s = input.split(" ");
        int out = 0, size;
        if((s.length % 2) == 0)
            size = s.length;
        else {
            size = s.length - 1;
            out += Data.stringToNumber(s[s.length - 1]);
        }
        for(int i = 0; i < size; )
        {
            int first = Data.stringToNumber(s[i + 1]), second = Data.stringToNumber(s[i]);
            if((first > 90) && (second != 0)) {
                out += (first * second);
                i += 2;
            }
            else if(first > 0 && (second != 0)){
                out += (first + second);
                i += 2;
                break;
            }
            else {
                System.out.println("Input number with error!");
                return 0;
            }

        }
        return out;
    }

    public static String numberToString(int input) {
        String out = "";
        if((input % 10) > 0)
            out = Data.numberToString(input % 10);
        if(input < 10)
            return out;
        if((input % 100) > 19)
            out = Data.numberToString((input % 100) / 10 * 10) + ' ' + out;
        else if((input % 100) > 9)
            out = Data.numberToString(input % 100);
        if(input < 100)
            return out;
        if((input % 1000) >= 100)
            out = Data.numberToString((input % 1000) / 100) + " hundred " + out;
        if(input < 1000)
            return out;
        if((input % 10000) >= 1000)
            out = Data.numberToString((input % 10000) / 1000) + " thousand " + out;
        return out;
    }

}

class Data {

    public static final List<Data> datas = Arrays.asList(
            new Data("one", 1),
            new Data("two", 2),
            new Data("three", 3),
            new Data("four", 4),
            new Data("five", 5),
            new Data("six", 6),
            new Data("seven", 7),
            new Data("eight", 8),
            new Data("nine", 9),
            new Data("ten", 10),
            new Data("eleven", 11),
            new Data("twelve", 12),
            new Data("thirteen", 13),
            new Data("fourteen", 14),
            new Data("fifteen", 15),
            new Data("sixteen", 16),
            new Data("seventeen", 17),
            new Data("eighteen", 18),
            new Data("nineteen", 19),
            new Data("twenty", 20),
            new Data("thirty", 30),
            new Data("fourty", 40),
            new Data("fifty", 50),
            new Data("sixty", 60),
            new Data("seventy", 70),
            new Data("eighty", 80),
            new Data("ninety", 90),
            new Data("hundred", 100),
            new Data("thousand", 1000)
    );

    String s;
    int ch;

    public Data(String strValue,int intValue)
    {
        ch = intValue;
        s = strValue;
    }
    public String getString() { return s; };
    public int getNumber() {return ch; };

    public static int stringToNumber(String string) {
        for(int i = 0; i < 29; i++)
            if (string.equals(datas.get(i).getString())) return datas.get(i).getNumber();
        return 0;
    }

    public static String numberToString(int number) {
        for(int i = 0; i < 29; i++)
            if(number == datas.get(i).getNumber()) return datas.get(i).getString();
        return "0";
    }
}