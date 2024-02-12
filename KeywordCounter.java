import java.util.*;
import java.io.*;

public class KeywordCounter {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    //have user enter source file
    System.out.print("Enter source file: ");
    String sourceFile = input.nextLine();

    File file = new File(sourceFile);

    // validate file exists
    if (file.exists()) {
      try {
        System.out.println("The number of keywords in " + sourceFile + " is " + countKeywords(file));
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
      }
    } else {
      System.out.println("File does not exist.");
    }
  }

  // count the number of keywords in a file
  public static int countKeywords(File file) throws Exception {
    // array of Java keywords
    String[] keywords = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while", "true", "false", "null"};

    Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
    // initialize counter
    int count = 0;

    // use scanner to read file line by line
    try (Scanner input = new Scanner(file)) {
      while (input.hasNext()) {
        String word = input.next();
        // check if word is a keyword
        if (keywordSet.contains(word)) {
          count++;
        }
      }
    }
    return count;

    }
}