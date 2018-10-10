package raytracer;

import javafx.scene.paint.Color;

public class Sphere implements RenderObject {
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

  public Vec3 getNormal(Vec3 normalPos) {
    return normalPos.subtract(pos).normalize();
  }

  /**
   * Get the intersection distance between this sphere and a line.
   * @param lineDir The direction of the line (a unit vector).
   * @param lineOrig The point on the line to calculate the distance from.
   * @return -1 if the sphere and the line never intersects, otherwise the distance.
   */
  public double getIntersectionDistance(Vec3 lineDir, Vec3 lineOrig) {
    double t1 = lineOrig.subtract(pos).getDotProduct(lineDir);
    double t2 = Math.pow(t1, 2);
    double t3 = Math.pow(lineOrig.subtract(pos).getNorm(), 2);
    double t4 = Math.pow(radius, 2);

    if (t2 - t3 + t4 >= 0) {
      return -t1 - Math.sqrt(t2 - t3 + t4);
    } else {
      return -1; // There is no solution.
    }
  }
}
