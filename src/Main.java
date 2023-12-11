public class Main {
    public static void main(String[] args) {
        SecretKey sKey = new SecretKey();
        System.out.println(sKey.getKey());
        SecretKeyGuesser guesser = new SecretKeyGuesser();
        guesser.start();


    }
}