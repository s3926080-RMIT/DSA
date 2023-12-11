// Du Tuan Vu
import java.util.Arrays;

public class SecretKeyGuesser {
    public static void main(String[] args) {
        new SecretKeyGuesser().start();
    }

    public void start() {
        SecretKey key = new SecretKey();
        String str = "MMMMMMMMMMMM";
        int counter = 0;

        do {
            int matched = key.guess(str);
            counter++;

            if (matched == 12) {
                System.out.println("I found the secret key. It is " + str);
                System.out.println("Number of guesses: " + counter);
                break;
            }

            str = nextPermutation(str);
            System.out.println("Guessing... " + str);
        } while (true);
    }

    private String nextPermutation(String current) {
        char[] curr = current.toCharArray();
        int n = curr.length;
    
        int i = n - 1;
        while (i > 0 && order(curr[i - 1]) >= order(curr[i])) {
            i--;
        }
        // Start from the rightmost element (curr[i]) and find the first element that is smaller than its next element (curr[i - 1]).
        ;
    
        if (i <= 0) {
            Arrays.sort(curr); 
            return String.valueOf(curr);
        }
        // If no such element is found, it means the current permutation is the last one. In this case, sort the entire array in ascending order and return.
    
        int j = n - 1;
        while (order(curr[j]) <= order(curr[i - 1])) {
            j--;
        }
        // Start from the rightmost element again and find the smallest element to the right of curr[i-1] but larger than curr[i-1].
    
        char temp = curr[i - 1];
        curr[i - 1] = curr[j];
        curr[j] = temp;
        // Swap curr[i-1] with the found element.
    
        j = n - 1;
        while (i < j) {
            temp = curr[i];
            curr[i] = curr[j];
            curr[j] = temp;
            i++;
            j--;
        }
        // Reverse sort the remaining elements to get the next lexicographically greater permutation.
    
        reverseSort(curr, i, n);
    
        return String.valueOf(curr);
    }
    
    private void reverseSort(char[] arr, int start, int end) {
  
        while (start < end) {
            char temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
    
    private static int order(char c) {
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
}