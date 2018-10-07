package raytracer;

import java.util.ArrayList;

import javafx.scene.paint.Color;

public class RenderScene {
  private int width;
  private int height;
  private ArrayList<Sphere> spheres;
  private Vec3 cameraPos;

  /**
   * Create the RenderScene.
   * @param width The width in pixels.
   * @param height The height in pixels.
   */
  public RenderScene(int width, int height) {
    this.width = width;
    this.height = height;
    spheres = new ArrayList<>();
  }

  public void addSphere(Sphere sphere) {
    spheres.add(sphere);
  }

  public void setCameraPos(Vec3 pos) {
    cameraPos = pos;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  /**
   * Render the scene and get all the pixels.
   * @return List of pixels, which can be used to draw to the screen or generate an image file.
   */
  public ArrayList<Color> renderAndGetImage() {
    ArrayList<Color> pixels = new ArrayList<>(width * height);

    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; ++x) {
        Color color = getPixel((double) x / width, (double) y / height);
        pixels.add(width * y + x, color);
      }
    }

    return pixels;
  }

  private double getIntersectionDistance(Vec3 lineDir, Vec3 lineOrig, Sphere sphere) {
    double t1 = lineOrig.subtract(sphere.getPos()).getDotProduct(lineDir);
    double t2 = Math.pow(t1, 2);
    double t3 = Math.pow(lineOrig.subtract(sphere.getPos()).getNorm(), 2);
    double t4 = Math.pow(sphere.getRadius(), 2);

    if (t2 - t3 + t4 >= 0) {
      return -t1 - Math.sqrt(t2 - t3 + t4);
    } else {
      return -1; // There is no solution.
    }
  }

  private Color getPixel(double x, double y) {
    Vec3 screenPixelPos = new Vec3(x, y, 0);
    Vec3 screenDir = screenPixelPos.subtract(cameraPos).normalize();
    double intersectionDistance = getIntersectionDistance(screenDir, cameraPos, spheres.get(0));

    double green = (intersectionDistance - 0.5) / (0.8867334 - 0.5198039);
    if (green > 1) {
      green = 1;
    } else if (green < 0) {
      green = 0;
    }

    if (intersectionDistance >= 0) {
      return new Color(1, green, 0, 1);
    } else {
      return new Color(1, 1, 1, 1);
    }
  }
}
