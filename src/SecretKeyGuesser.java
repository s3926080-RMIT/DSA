public class SecretKeyGuesser {
  public void start() {
    // brute force key guessing
    SecretKey key = new SecretKey();
    String str = "MMMMMMMMMMMM";
    int matchedChars = key.guess(str);
    int check = matchedChars;
    int currentCharIndex = 0;
    while (matchedChars != 12) {
      if (check - matchedChars == 0) { 
        str = nextOrder(str, currentCharIndex);
      }

      else if (check - matchedChars < 0) {
        currentCharIndex++;
      }

      else if (check - matchedChars > 0) {
        str = prevOrder(str, currentCharIndex);
        currentCharIndex++;
        matchedChars = key.guess(str);
        continue;
      }

      check = matchedChars;
      System.out.println("Guessing... " + str);
      matchedChars = key.guess(str);

    }
    System.out.println("I found the secret key. It is " + str);
  }

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