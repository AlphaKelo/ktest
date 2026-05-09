package BobsCircus;

import java.util.ArrayList;

/**
 * This is a utility class that encrypts and decrypts a phrase using three
 * different approaches. 
 * 
 * The first approach is called the Vigenere Cipher.Vigenere encryption 
 * is a method of encrypting alphabetic text based on the letters of a keyword.
 * 
 * The second approach is Playfair Cipher. It encrypts two letters (a digraph) 
 * at a time instead of just one.
 * 
 * The third approach is Caesar Cipher. It is a simple replacement cypher. 
 * 
 * @author Huseyin Aygun
 * @version 8/3/2025
 */

public class CryptoManager { 

    private static final char LOWER_RANGE = ' ';
    private static final char UPPER_RANGE = '_';
    private static final int RANGE = UPPER_RANGE - LOWER_RANGE + 1;
    // Use 64-character matrix (8X8) for Playfair cipher  
    private static final String ALPHABET64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!\"#$%&'()*+,-./:;<=>?@[\\]^_";

    public static boolean isStringInBounds(String plainText) {
        for (int i = 0; i < plainText.length(); i++) {
            if (!(plainText.charAt(i) >= LOWER_RANGE && plainText.charAt(i) <= UPPER_RANGE)) {
                return false;
            }
        }
        return true;
    }

	/**
	 * Vigenere Cipher is a method of encrypting alphabetic text 
	 * based on the letters of a keyword. It works as below:
	 * 		Choose a keyword (e.g., KEY).
	 * 		Repeat the keyword to match the length of the plaintext.
	 * 		Each letter in the plaintext is shifted by the position of the 
	 * 		corresponding letter in the keyword (A = 0, B = 1, ..., Z = 25).
	 */   

    public static String vigenereEncryption(String plainText, String key) {
         
    	if (!isStringInBounds(plainText)) {
    		return "Error, try again";
    	}
    	
    	//initialize variables
    	String result = "";
    	int keyIndex = 0;
    	
    	//loop through the encryption
    	for (int g = 0; g < plainText.length(); g++) {
    		char pT = plainText.charAt(g);
    		char ky = key.charAt(keyIndex);
    		
    		int vigShift = ky - LOWER_RANGE;
    		int encrypt = pT + vigShift;
    		
    		if(encrypt > UPPER_RANGE) {
    			encrypt = encrypt - (UPPER_RANGE - LOWER_RANGE + 1);
    			
    		}
    		
    		result += (char) encrypt;
    		
    		//loop the key index as well
    		keyIndex++;
    		if (keyIndex == key.length()) {
    			keyIndex = 0;
    		}
    	}
    	
    	return result; 
    }

    // Vigenere Decryption
    public static String vigenereDecryption(String encryptedText, String key) {
         if(!isStringInBounds(encryptedText)) {
        	return "Error, try again";
         }
    	
         String result = "";
         int keyIndex = 0;
         
         for (int h = 0; h < encryptedText.length(); h++) {
     		char eT = encryptedText.charAt(h);
     		char ky = key.charAt(keyIndex);
     		
     		int vigShift = ky - LOWER_RANGE;
     		int decrypt = eT - vigShift;
     		
     		if(decrypt < LOWER_RANGE) {
     			decrypt = decrypt + (UPPER_RANGE - LOWER_RANGE + 1);
     			
     		}
     		
     		result += (char) decrypt;
     		
     		//loop the key index as well
     		keyIndex++;
     		if (keyIndex == key.length()) {
     			keyIndex = 0;
     		}
     	}
     	
     	return result; 
         
         
    }


	/**
	 * Playfair Cipher encrypts two letters at a time instead of just one.
	 * It works as follows:
	 * A matrix (8X8 in our case) is built using a keyword
	 * Plaintext is split into letter pairs (e.g., ME ET YO UR).
	 * Encryption rules depend on the positions of the letters in the matrix:
	 *     Same row: replace each letter with the one to its right.
	 *     Same column: replace each with the one below.
	 *     Rectangle: replace each letter with the one in its own row but in the column of the other letter in the pair.
	 */    

    public static String playfairEncryption(String plainText, String key) {

        if (!isStringInBounds(plainText)) {
            return "Error, try again";
        }

        char[] plainTextArray = plainText.toCharArray();
        char[] keyArray = key.toCharArray();
        char[][] matrix = new char[8][8];
        boolean[] chosen = new boolean[96];

        int charIndex = 0;
        char asciiList = LOWER_RANGE;

        // fill matrix
        for (int r = 0; r < 8; r++) {
            for (int col = 0; col < 8; col++) {

                while (charIndex < keyArray.length) {
                    char c = keyArray[charIndex];
                    charIndex++;

                    if (c >= LOWER_RANGE && c <= UPPER_RANGE && !chosen[c]) {
                        matrix[r][col] = c;
                        chosen[c] = true;
                        break;
                    }
                }

                if (matrix[r][col] == 0) {
                    while (chosen[asciiList]) {
                        asciiList++;
                    }
                    matrix[r][col] = asciiList;
                    chosen[asciiList] = true;
                    asciiList++;
                }
            }
        }

        // encryption
        ArrayList<Character> resultList = new ArrayList<>();
        if (plainTextArray.length % 2 != 0) {
            plainText += " ";
            plainTextArray = plainText.toCharArray();
        }
        for (int q = 0; q < plainTextArray.length; q += 2) {

            char pairA = plainTextArray[q];
            char pairB = plainTextArray[q + 1];

            int rowA = 0, colA = 0;
            int rowB = 0, colB = 0;

            // find positions
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {

                    if (matrix[row][col] == pairA) {
                        rowA = row;
                        colA = col;
                    }

                    if (matrix[row][col] == pairB) {
                        rowB = row;
                        colB = col;
                    }
                }
            }

            // apply playfair rules
            if (rowA == rowB) {
                resultList.add(matrix[rowA][(colA + 1) % 8]);
                resultList.add(matrix[rowB][(colB + 1) % 8]);
            }
            else if (colA == colB) {
                resultList.add(matrix[(rowA + 1) % 8][colA]);
                resultList.add(matrix[(rowB + 1) % 8][colB]);
            }
            else {
                resultList.add(matrix[rowA][colB]);
                resultList.add(matrix[rowB][colA]);
            }
        }

        // convert ArrayList to String
        String result = "";
        for (char c : resultList) {
            result += c;
        }

        return result;
    }

    
    public static String playfairDecryption(String encryptedText, String key) {
         
    	 if(!isStringInBounds(encryptedText)) {
         	return "Error, try again";
          }
    

         char[] plainTextArray = encryptedText.toCharArray();
         char[] keyArray = key.toCharArray();
         char[][] matrix = new char[8][8];
         boolean[] chosen = new boolean[96];

         int charIndex = 0;
         char asciiList = LOWER_RANGE;

         // fill matrix
         for (int r = 0; r < 8; r++) {
             for (int col = 0; col < 8; col++) {

                 while (charIndex < keyArray.length) {
                     char c = keyArray[charIndex];
                     charIndex++;

                     if (c >= LOWER_RANGE && c <= UPPER_RANGE && !chosen[c]) {
                         matrix[r][col] = c;
                         chosen[c] = true;
                         break;
                     }
                 }

                 if (matrix[r][col] == 0) {
                     while (chosen[asciiList]) {
                         asciiList++;
                     }
                     matrix[r][col] = asciiList;
                     chosen[asciiList] = true;
                     asciiList++;
                 }
             }
         }

         // encryption
         ArrayList<Character> resultList = new ArrayList<>();

         for (int q = 0; q < plainTextArray.length; q += 2) {

             char pairA = plainTextArray[q];
             char pairB = plainTextArray[q + 1];

             int rowA = 0, colA = 0;
             int rowB = 0, colB = 0;

             // find positions
             for (int row = 0; row < 8; row++) {
                 for (int col = 0; col < 8; col++) {

                     if (matrix[row][col] == pairA) {
                         rowA = row;
                         colA = col;
                     }

                     if (matrix[row][col] == pairB) {
                         rowB = row;
                         colB = col;
                     }
                 }
             }

             // apply playfair rules
             if (rowA == rowB) {
                 resultList.add(matrix[rowA][(colA + 7) % 8]);
                 resultList.add(matrix[rowB][(colB + 7) % 8]);
             }
             else if (colA == colB) {
                 resultList.add(matrix[(rowA + 7) % 8][colA]);
                 resultList.add(matrix[(rowB + 7) % 8][colB]);
             }
             else {
                 resultList.add(matrix[rowA][colB]);
                 resultList.add(matrix[rowB][colA]);
             }
         }

         // convert ArrayList to String
         String result = "";
         for (char c : resultList) {
             result += c;
         }

         return result;
     }
    	
    

    /**
     * Caesar Cipher is a simple substitution cipher that replaces each letter in a message 
     * with a letter some fixed number of positions down the alphabet. 
     * For example, with a shift of 3, 'A' would become 'D', 'B' would become 'E', and so on.
     */    
 
    public static String caesarEncryption(String plainText, int key) {
    	
    	if (!isStringInBounds(plainText)) {
    		return "Error, try again";
    	}
    	
    	String result = "";
    	
    	for (int i = 0; i < plainText.length(); i++) {
    		
    		char pT = plainText.charAt(i);
    		int encrypt = pT + key;
    		
    		if(encrypt > UPPER_RANGE) {
    			encrypt = encrypt - (UPPER_RANGE - LOWER_RANGE + 1);
    			
    		}
    		
    		result += (char) encrypt;
    		
    	}
    	
    	return result;
    }

    // Caesar Decryption
    public static String caesarDecryption(String encryptedText, int key) {
    	
    	 if(!isStringInBounds(encryptedText)) {
         	return "Error, try again";
          }
    	
    	 String result = "";
    	 
    	 for (int g = 0; g < encryptedText.length(); g++) {
    		 
    		 char eT = encryptedText.charAt(g);
    		 int decrypt = eT - key;
    		 
    		 if(decrypt < LOWER_RANGE) {
      			decrypt = decrypt + (UPPER_RANGE - LOWER_RANGE + 1);
      			
      		}
      		
      		result += (char) decrypt; 
    	 }
    	 
    	 
    	 return result; 
    }    

}