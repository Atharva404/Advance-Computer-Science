import java.awt.Color;
public class PhotoMagic {
   public static void main(String[] args) throws InterruptedException {
      Picture pic = new Picture("pipe.png");
      //pic.show();
      System.out.println(pic.height());
      System.out.println(pic.width());
      LFSR lfsr = new LFSR("01101000010100010000", 16);
      Picture encrypted  = transform(pic, lfsr);
      encrypted.save("encrypted.png");
      encrypted.show();
      Thread.sleep(400);
      Picture decrypt = transform(new Picture("encrypted.png"), lfsr);
      decrypt.show();

   }
   
   public static Picture transform(Picture pic, LFSR lfsr) {
      for(int i = 0; i < pic.width(); i++) {
         for(int j = 0; j < pic.height(); j++) {
            Color color = pic.get(i, j);
            int red = color.getRed() ^ lfsr.generate(8);
            int green = color.getGreen() ^ lfsr.generate(8);
            int blue = color.getBlue() ^ lfsr.generate(8);
            Color newColor = new Color(red, green, blue);
            pic.set(i, j, newColor);
         }
      }
      return pic;
   }
}