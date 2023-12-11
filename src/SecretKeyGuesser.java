import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SecretKeyGuesser {
    private static final char[] CHARACTERS = {'M', 'O', 'C', 'H', 'A'};
    private static final int KEY_LENGTH = 12;
    private char[] guess = new char[KEY_LENGTH];
    private int[] matchCounts = new int[KEY_LENGTH];

    public void start() {
        SecretKey secretKey = new SecretKey();
        int totalMatchCount = 0;
        int guessCount = 0;
        int currentMatchCount = 0;

        Set<String> previousGuesses = new HashSet<>();
    
        // Initialize the guess array with 'M'
        Arrays.fill(guess, 'M');
        
        totalMatchCount = secretKey.guess(new String(guess));
        previousGuesses.add(new String(guess));
    
    
        while (totalMatchCount < KEY_LENGTH) {
            for (int i = 0; i < KEY_LENGTH; i++) {
                if (matchCounts[i] > 0) {
                    // Skip this position if we've already found a match here
                    continue;
                }
                int prevMatchCount = totalMatchCount;
                for (char c : CHARACTERS) {
                    char originalChar = guess[i];
                    guess[i] = c;
                    
                    String currentGuess = new String(guess);

                    if (previousGuesses.contains(currentGuess)) {
                        continue;
                    }
                    int matchCount = secretKey.guess(new String(guess));
                    guessCount++;
                    System.out.println("Guess " + guessCount +  ": " +new String(guess) + ", Matching positions: " + matchCount);
                    if (matchCount > prevMatchCount) {
                        totalMatchCount = matchCount;
                        matchCounts[i]++;
                        previousGuesses.add(currentGuess);          
                        break;
                    } else if(matchCount < prevMatchCount) {
                        // Revert the guess if it didn't improve the match count
                        guess[i] = originalChar;
                       
                        break;
                    }
                }
            }
        }
    

        System.out.println("Secret key: " + new String(guess));
        System.out.println("Number of guesses: " + guessCount);
    }
}


    

