package raytracer;

import javafx.scene.paint.Color;

public interface RenderObject {
  Vec3 getNormal(Vec3 normalPos);

  Color getColor();

  double getIntersectionDistance(Vec3 lineDir, Vec3 lineOrig);
}
