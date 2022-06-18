package server;

public class TestAutorization {
    public static void main(String[] args) {
        Autorization a = new Autorization();
        a.setLogin("login1");
        a.setPassword("12ab");
        System.out.println(a.checkAutorization());
    }
}
