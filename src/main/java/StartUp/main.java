package StartUp;

import Login.Login;

import java.io.*;
import java.util.Scanner;
/*
    this build is alpha-build#002. Developed by Mamizu0312.
 */
public class main {
    public static void main(String[] args) {
        System.out.println("General-Infomation-Management System");
        File config = new File("config.yml");
        if(!config.exists()) {
            try {
                config.createNewFile();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("configのロードが終了しました。");
        Scanner scan = new Scanner(System.in);
        System.out.println("userIDを入力してください。");
        System.out.print("ID: ");
        String userID = scan.next();
        System.out.println("");
        System.out.println("passwordを入力してください。");
        System.out.print("password: ");
        String userPassword = scan.next();
        System.out.println("");
        if(!new Login(userID, userPassword).login()) {
            System.out.println("ログインに成功しました。");
            System.exit(0);
        } else {
            System.out.println("ログインに失敗しました。終了します。");
            System.exit(-1);
        }
    }
}
