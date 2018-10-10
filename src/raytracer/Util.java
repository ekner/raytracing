package raytracer;

import java.util.ArrayList;
import javafx.scene.paint.Color;

public class Util {

  /**
   * Calculate the mean color of the colors in the supplied list.
   * @param colors List of Colors.
   * @return The mean Color.
   */
  public static Color getMeanColor(ArrayList<Color> colors) {
    double r = 0;
    double g = 0;
    double b = 0;

    for (Color color : colors) {
      r += Math.pow(color.getRed(), 2);
      g += Math.pow(color.getGreen(), 2);
      b += Math.pow(color.getBlue(), 2);
    }

    r = Math.sqrt(r / colors.size());
    g = Math.sqrt(g / colors.size());
    b = Math.sqrt(b / colors.size());

    return new Color(r, g, b, 1);
  }
}
