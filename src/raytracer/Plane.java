package raytracer;

import javafx.scene.paint.Color;

public class Plane implements RenderObject {
  private Vec3 normal;
  private Vec3 pointOnPlane;
  private Color color;

  /**
   * Construct a Plane from a number of properties.
   * @param pointOnPlane A point on the plane.
   * @param normal The normal of the plane.
   * @param color The color of the plane.
   */
  public Plane(Vec3 normal, Vec3 pointOnPlane, Color color) {
    this.normal = normal;
    this.pointOnPlane = pointOnPlane;
    this.color = color;
  }

  public Vec3 getPointOnPlane() {
    return pointOnPlane;
  }

  public Vec3 getNormal() {
    return normal;
  }

  public Vec3 getNormal(Vec3 normalPos) {
    return normal;
  }

  public Color getColor() {
    return color;
  }

  public void setPointOnPlane(Vec3 pointOnPlane) {
    this.pointOnPlane = pointOnPlane;
  }

  public void setNormal(Vec3 normal) {
    this.normal = normal;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  /**
   * Get the intersection distance between this plane and a line.
   * @param lineDir The direction of the line (a unit vector).
   * @param lineOrig The point on the line to calculate the distance from.
   * @return -1 if the line and the plane are parallel, otherwise the distance.
   */
  public double getIntersectionDistance(Vec3 lineDir, Vec3 lineOrig) {
    double v1 = pointOnPlane.subtract(lineOrig).getDotProduct(normal);
    double v2 = lineDir.getDotProduct(normal);

    //System.out.println(v1 / v2);

    if (v2 != 0) {
      return v1 / v2;
    } else {
      return -1; // The line and the plane are parallel.
    }
  }
}
