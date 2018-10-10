package raytracer;

public class Light {
  private Vec3 pos;
  private double value;

  /**
   * Construct a Light from a number of properties.
   * @param pos The coordinate of the center of the light source.
   * @param value The value (intensity) of the light source.
   */
  public Light(Vec3 pos, double value) {
    this.pos = pos;
    this.value = value;
  }

  public Vec3 getPos() {
    return pos;
  }

  public double getValue() {
    return value;
  }

  public void setPos(Vec3 pos) {
    this.pos = pos;
  }

  public void setValue(double value) {
    this.value = value;
  }

  public Vec3 getNormal(Vec3 normalPos) {
    return normalPos.subtract(pos).normalize();
  }
}
