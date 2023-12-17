public class SecretKeyGuesser {
  public void start() {
    SecretKey key = new SecretKey();
    String str = "MMMMMMMMMMMM";

    // Assigning initial guess to a variable to then be used to compare number of matched characters
    // between each guess.
    int matchedChars = key.guess(str);

    System.out.println("Guessing... " + str);
    
    // Acts as a dummy variable to store the number of guessed characters of previous iteration
    // For example, guess #1 returns 2 then check will be assigned 2 when running guess #2,
    // matchedChars and check will be compared to see any change occured.
    int check = matchedChars;

    // Index of current character being shifted
    int currentCharIndex = 0;
    
    while (matchedChars != 12) {
      
      // Case 1: If GuessedKey and CorrectKey have 0 matches, shift every character in GuessedKey forward once.
      while (matchedChars == 0) {
        int currentCharIndexShiftAll = 0;
        for (int i = 0; i < str.length(); i++) {
            str = nextOrder(str, currentCharIndexShiftAll);
            currentCharIndexShiftAll++;
            
        }
        matchedChars = key.guess(str);
        System.out.println("Guessing... " + str);
      }

      // Break condition for outer while loop (primarily for Case 1 where after shifting each character and just so
      // happens to reach CorrectKey)
      if (matchedChars == 12) {
        break;
      }
      
      // Case 2: If number of matched characters between current guess and last guess does not change, continue shifting
      if (check - matchedChars == 0) { 
        str = nextOrder(str, currentCharIndex);
      }

      // Case 3: If the number of mached characters in the current guess exceeds last guess', proceed to next character
      // and shift it once.
      else if (check - matchedChars < 0) {
        currentCharIndex++;
        str = nextOrder(str, currentCharIndex);
      }

      // Case 4: If number of matched characters in current guess is lower than last guess', shift the current character
      // backwards once. Then proceed to the next character. 
      else if (check - matchedChars > 0) {
        str = prevOrder(str, currentCharIndex);
        currentCharIndex++;
        matchedChars = key.guess(str);
        continue;
      }

      // Assign check to the current number of matched characters before moving forward to next guess
      check = matchedChars;
      System.out.println("Guessing... " + str);
      matchedChars = key.guess(str);
    }
    System.out.println("I found the secret key. It is " + str);
  }
  
  // Return order in character form based on number in order
  static int order(char c) {
    if (c == 'M') {
      return 0;
    } else if (c == 'O') {
      return 1;
    } else if (c == 'C') {
      return 2;
    } else if (c == 'H') {
      return 3;
    } 
    return 4;
  }

  // Return order in int form based on character
  static char charOf(int order) {
    if (order == 0) {
      return 'M';
    } else if (order == 1) {
      return 'O';
    } else if (order == 2) {
      return 'C';
    } else if (order == 3) {
      return 'H';
    } 
    return 'A';
  }

  // Shift current character forward by 1 according to established order
  public String nextOrder(String current, int currentCharIndex) {
      char[] characters = current.toCharArray();
      if (order(characters[currentCharIndex]) == 4) {
          characters[currentCharIndex] = 'M';
      }
      else {
          characters[currentCharIndex] = charOf(order(characters[currentCharIndex]) + 1);
      }
      return String.valueOf(characters);
  }

  // Shift the current character backwards by 1 according to established order
  public String prevOrder(String current, int currentCharIndex) {
      char[] characters = current.toCharArray();
      if (order(characters[currentCharIndex]) == 0) {
          characters[currentCharIndex] = 'A';
      }
      else {
          characters[currentCharIndex] = charOf(order(characters[currentCharIndex]) - 1);
      }
      return String.valueOf(characters);
  }
}