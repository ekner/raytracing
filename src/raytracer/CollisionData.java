package raytracer;

class CollisionData {
  RenderObject renderObject;
  double distance;
  double lightness;

  /**
   * CollisionData is used by the reacer, to return the object the ray has collided with,
   * the distance to the collided object, and the lightness in the collision spot.
   */
  CollisionData() {
    this.renderObject = null;
    this.distance = -1;
    this.lightness = 0;
  }
}
