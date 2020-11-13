/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practical.pkg5;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author salaam
 */

public class Practical5 {

    /**
     * @param args the command line arguments
     */
    
    public static boolean done;
    public static long timer;
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Enter the hash code to decode: ");
        String input = scanner.next();
        int pos = 0;
        String str = "abcdefghijklmnopqrstuvwxyz0123456789";
        System.out.println("/nDecoding.... /nthis could take some time!");
        done = false;
        timeStart();
        for(int i = 1; i < 7; i++) {
            if (!done) {
                try {
                    char index[] = new char[i];
                    permutation(index, pos, str, input);
                } catch (Exception e) {
                    //System.err.println(e);
                }
            }
        }
        timeStop();
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
    
    
    private static void timeStart(){
        timer = System.currentTimeMillis();
    }

    private static void timeStop(){
        timer = System.currentTimeMillis() - timer;
        System.out.println(String.format("\n%d minutes, \n%d seconds, \n%d milliseconds\n",
                TimeUnit.MILLISECONDS.toMinutes(timer),
                TimeUnit.MILLISECONDS.toSeconds(timer) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timer)),
                timer - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(timer))
                ));
    }
    
    private static void permutation(char[] index, int pos, String str, String input) throws NoSuchAlgorithmException,
            UnsupportedEncodingException{
        String passTest, passTestHash;

            if (pos == index.length && !done) {
                passTest = new String(index);
                passTestHash = SHA1(passTest);

                if (passTestHash.equals(input)) {
                    System.out.println("The encoded word was: " + passTest);
                    done = true;
                }
                // to print out the generated letters(word). printing slows down the process
                // System.out.println(passTest);
            } else {
                //if not found continue cycling string positions
                for (int i = 0; i < str.length(); i++) {
                    index[pos] = str.charAt(i);
                    permutation(index, pos + 1, str, input);
                }
            }
    }
    
}
