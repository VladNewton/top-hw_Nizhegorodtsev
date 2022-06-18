package server;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Autorization {

    private String login;
    private String password;

    private final static Map<String,String> loginBase = new HashMap<>();
    static {
        loginBase.put("login1", "12ab");
        loginBase.put("login2", "22cd");
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Autorization(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Autorization() {}

    public boolean checkAutorization() {
        return  (password.equals(loginBase.get(login)));
    }

    public void inputLoginPassw() {
        System.out.println("Введите логин: ");
        this.setLogin(new Scanner(System.in).nextLine());
        System.out.println("Введите пароль: ");
        this.setPassword(new Scanner(System.in).nextLine());
    }


}
