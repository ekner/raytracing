package raytracer;

import javafx.scene.paint.Color;

public class Sphere {
  private Vec3 pos;
  private double radius;
  private Color color;

  /**
   * Construct a Sphere from a number of properties.
   * @param pos The coordinate of the center of the sphere.
   * @param radius The radius of the sphere, in pixels.
   * @param color The color of the sphere.
   */
  public Sphere(Vec3 pos, double radius, Color color) {
    this.pos = pos;
    this.radius = radius;
    this.color = color;
  }

  public Vec3 getPos() {
    return pos;
  }

  public double getRadius() {
    return radius;
  }

  public Color getColor() {
    return color;
  }

  public void setPos(Vec3 pos) {
    this.pos = pos;
  }

  public void setRadius(double radius) {
    this.radius = radius;
  }

  public void setColor(Color color) {
    this.color = color;
  }
}
