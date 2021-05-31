import java.io.*;
public class PhotoMagicDeluxe {
   
   public static void main(String[] args) {
     newTransform(new Picture(new File("mystery.png")), 58, "OPENSESAME").save(new File("logo.png"));
   }
   
   public static Picture newTransform(Picture pic, int tap, String result) {
      return PhotoMagic.transform(pic, new LFSR(getBinary(result), tap));
   }
   
   public static String getBinary(String bin) {
     String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
     String result = "";
     int[] arr = new int[bin.length()];
     for(int i = 0; i < arr.length; i++) {
         arr[i] = base64.indexOf(bin.substring(i, i + 1));
         String newResult = "000000";
         int j = newResult.length() - 1;
         while(arr[i] != 0) {
            char[] arrChar = newResult.toCharArray();
            arrChar[j--] = String.valueOf(arr[i]%2).charAt(0);
            newResult = new String(arrChar);
            arr[i] = arr[i]/2;
         }
         
         result += newResult;
     }
     return result;
   }
}

