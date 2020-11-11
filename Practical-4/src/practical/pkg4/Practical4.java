/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practical.pkg4;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author salaam
 */
public class Practical4 {

    public static Scanner scanner = new Scanner(System.in);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        hashWord();
    }
    
    private static void hashWord(){
        System.out.println("Enter a word to encode: ");
        String input = scanner.next();

        try{
            input = SHA1(input);
        } catch(NoSuchAlgorithmException | UnsupportedEncodingException e){
            System.err.println(e);
        }
        System.out.println("\nYour word encoded: \n" + input);
    }
    
    
    private static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-1");
        byte[] hash;
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        hash = md.digest();
        return(convertToHex(hash));
    }
    
    
    private static String convertToHex(byte[] data){
        StringBuffer buf = new StringBuffer();
        for (byte aData : data) {
            int halfbyte = (aData >>> 4) & 0x0F;
            int two_halves = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9)) {
                    buf.append((char) ('0' + halfbyte));
                } else {
                    buf.append((char) ('a' + (halfbyte - 10)));
                }
                halfbyte = aData & 0x0F;
            } while (two_halves++ < 1);
        }
        return buf.toString();
    }
}
