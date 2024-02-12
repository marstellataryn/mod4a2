import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class KeywordCounter {

  public static void main(String[] args) {
    if (args.length != 1) {
      System.out.println("Usage: java KeywordCounter <input file> ");
      System.exit(1);
    }

    // read file name
    String fileName = args[0];

    // create File object to represent soource code file
    File file = new File(fileName);

    // check if file exists 
    if (!file.exists()) {
      System.out.println("File " + fileName + " does not exist");
      System.exit(2);
    }

    // create a set to store keywords
    Set<String> keywords = new HashSet<>();
    initializeKeywords(keywords);

    // create counter
    int countKeywords = 0;

    try (Scanner input = new Scanner(file)) {
      boolean insideString = false;
      boolean insideComment = false; 

      while (input.hasNext()) {
        String line = input.nextLine().trim();

        //Check for strings
        if (!insideComment && line.contains("\"")) {
          insideString = !insideString;
          continue;
        }
        // check for single-line comments
        if (!insideString && line.contains("//")) {
          continue; 
        }
        // check for block comments
        if (!insideString) {
          if (line.startsWith("/*")) {
            insideComment = true;
          }
          
          if (line.endsWith("*/")) {
            insideComment = false;
            continue; 
          }
        }

        // process line if not inside a string or comment
        if (!insideString && !insideComment) {
          String[] words = line.split("[\\s+\\(\\);,\\.]");
          
          for (String word : words) {
            if (keywords.contains(word)) {
              countKeywords++;
            }
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  
    System.out.println("Number of keywords: " + countKeywords);
  }

 // helper method to initialize keywords
  private static void initializeKeywords(Set<String> set) {
    String[] keywordString = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "for", "final", "finally", "float", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return","short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while"};

    for (String keyword : keywordString) {
      set.add(keyword);
    }
  }
}



