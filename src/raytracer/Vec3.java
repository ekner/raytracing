package raytracer;

import java.util.ArrayList;

public class Vec3 {
  private double xval;
  private double yval;
  private double zval;

  /**
   * Construct a vector from its x, y and z components.
   * @param xval The x component.
   * @param yval The y component.
   * @param zval The z component.
   */
  public Vec3(double xval, double yval, double zval) {
    this.xval = xval;
    this.yval = yval;
    this.zval = zval;
  }

  /**
   * Copy constructor.
   * @param copyFrom The Vec3 to copy from.
   */
  public Vec3(Vec3 copyFrom) {
    this.xval = copyFrom.getX();
    this.yval = copyFrom.getY();
    this.zval = copyFrom.getZ();
  }

  /**
   * Get the x component.
   */
  public double getX() {
    return xval;
  }

  /**
   * Get the y component.
   */
  public double getY() {
    return yval;
  }

  /**
   * Get the z component.
   */
  public double getZ() {
    return zval;
  }

  public void setX(double xval) {
    this.xval = xval;
  }

  public void setY(double yval) {
    this.yval = yval;
  }

  public void setZ(double zval) {
    this.zval = zval;
  }

  /**
   * Set the first, the two first, or all values of the vector.
   * @param list Contains either the [x], [x, y], or the [x, y, z] values.
   */
  public void set(ArrayList<Double> list) {
    if (list.size() >= 1) {
      this.xval = list.get(0);
    }

    if (list.size() >= 2) {
      this.yval = list.get(1);
    }

    if (list.size() >= 3) {
      this.zval = list.get(2);
    }
  }

  /**
   * Perform addition and return new vector.
   * @param vecToAdd The vector to add.
   */
  public Vec3 add(Vec3 vecToAdd) {
    Vec3 vecCopy = new Vec3(this);
    vecCopy.xval += vecToAdd.xval;
    vecCopy.yval += vecToAdd.yval;
    vecCopy.zval += vecToAdd.zval;
    return vecCopy;
  }

  /**
   * Perform subtraction and return new vector.
   * @param vecToSubtract The vector to subtract.
   */
  public Vec3 subtract(Vec3 vecToSubtract) {
    Vec3 vecCopy = new Vec3(this);
    vecCopy.xval -= vecToSubtract.xval;
    vecCopy.yval -= vecToSubtract.yval;
    vecCopy.zval -= vecToSubtract.zval;
    return vecCopy;
  }

  /**
   * Perform multiplication and return new vector.
   * @param factor The scalar factor.
   */
  public Vec3 multiply(double factor) {
    Vec3 vecCopy = new Vec3(this);
    vecCopy.xval *= factor;
    vecCopy.yval *= factor;
    vecCopy.zval *= factor;
    return vecCopy;
  }

  /**
   * Get the norm (length) of the vector.
   */
  public double getNorm() {
    return Math.sqrt(xval * xval + yval * yval + zval * zval);
  }

  /**
   * Perform normalization and return new vector.
   */
  public Vec3 normalize() {
    Vec3 vecCopy = new Vec3(this);
    double norm = getNorm();
    vecCopy.xval /= norm;
    vecCopy.yval /= norm;
    vecCopy.zval /= norm;
    return vecCopy;
  }

  public double getDotProduct(Vec3 secondFactor) {
    return xval * secondFactor.getX() + yval * secondFactor.getY() + zval * secondFactor.getZ();
  }

  /**
   * Get this vector represented as a string.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("X: ");
    sb.append(xval);
    sb.append(", Y: ");
    sb.append(yval);
    sb.append(", Z: ");
    sb.append(zval);
    return sb.toString();
  }

  /**
   * Computes toString an prints it to std out.
   */
  public void debug() {
    System.out.println(toString());
  }
}
