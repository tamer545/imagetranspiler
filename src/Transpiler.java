import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Transpiler {
    private BufferedImage image = null;

    public String checkImage() {
        StringBuilder brainfuckCode = new StringBuilder();
        try {
            image = ImageIO.read(new File("C:\\Users\\timoa\\OneDrive - bbw.ch\\Module\\4. Semester\\133 Cavuoti\\imageToBrainfuckTranspiler\\src\\testImage7.png"));
        } catch (IOException e) {
            System.out.println(e);
        }

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                int imageColor = image.getRGB(x, y);
                Color color = new Color(imageColor, true);
                String finalColor = color.getRed() + "" + color.getGreen() + "" + color.getBlue();
                switch (finalColor) {
                    case "255255255" -> brainfuckCode.append('-');
                    case "000" -> brainfuckCode.append('+');
                    case "02550" -> brainfuckCode.append('.');
                    case "25500" -> brainfuckCode.append('[');
                    case "00255" -> brainfuckCode.append(']');
                    case "128128128" -> brainfuckCode.append(',');
                    case "2552550" -> brainfuckCode.append('<');
                    case "0255255" -> brainfuckCode.append('>');
                }
            }
        }
        System.out.println("Processing Image..");
        System.out.println(brainfuckCode);
        return String.valueOf(brainfuckCode);
    }
}
