import java.util.Random;
public class SecretKey {
    private final String key;
    public char[] getKey;

    public SecretKey() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        String characters = "MOCHA";
        for (int i = 0; i < 12; i++) {
            builder.append(characters.charAt(random.nextInt(characters.length())));
        }
        this.key = builder.toString();
    }

    public int guess(String guess) {
        if (guess.length() != 12) {
            return -1;
        }
        int count = 0;
        for (int i = 0; i < 12; i++) {
            if (key.charAt(i) == guess.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    public String getKey() {
        return key;
    }
}
