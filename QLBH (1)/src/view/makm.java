/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;
import java.nio.charset.Charset;
import java.util.Random;
/**
 *
 * @author ADMIN
 */
public class makm {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int len = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();
        System.out.println("Random String: " + generatedString);
    }
    
}


