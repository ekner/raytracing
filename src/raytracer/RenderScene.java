package raytracer;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class RenderScene {
  private int width;
  private int height;
  private ArrayList<Light> lights;
  private ArrayList<RenderObject> renderObjects;
  private Vec3 cameraPos;
  private static final int NUM_REFLECTIONS = 5;

  /**
   * Create the RenderScene.
   * @param width The width in pixels.
   * @param height The height in pixels.
   */
  public RenderScene(int width, int height) {
    this.width = width;
    this.height = height;
    lights = new ArrayList<>();
    renderObjects = new ArrayList<>();
  }

  public void addLight(Light light) {
    lights.add(light);
  }

  public void addObject(RenderObject ro) {
    renderObjects.add(ro);
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
        Color color = getPixel((double) x / width - 0.5, (double) y / height - 0.5);
        pixels.add(width * y + x, color);
      }
    }

    return pixels;
  }

  private double getLightness(Vec3 pos, Vec3 normal, RenderObject ro) {
    double totalVal = 0;

    for (Light light : lights) {
      Vec3 lightNormal = pos.subtract(light.getPos()).normalize();
      CollisionData collisionData = getCollisionData(lightNormal, light.getPos());

      if (collisionData.renderObject == ro) {
        double cosin = normal.getDotProduct(lightNormal);
        totalVal += (1 - ((cosin + 1) / 2)) * light.getValue();
      }
    }

    if (totalVal > 1) {
      totalVal = 1;
    }

    return totalVal;
  }

  private CollisionData getCollisionData(Vec3 lineDir, Vec3 lineOrig) {
    CollisionData collisionData = new CollisionData();

    for (RenderObject ro : renderObjects) {
      double thisDistance = ro.getIntersectionDistance(lineDir, lineOrig);

      if (thisDistance >= 0 && (thisDistance < collisionData.distance
          || collisionData.distance == -1)) {
        collisionData.distance = thisDistance;
        collisionData.renderObject = ro;
      }
    }

    return collisionData;
  }

  private ArrayList<CollisionData> trace(Vec3 direction, Vec3 origin) {
    ArrayList<CollisionData> collidedObjects = new ArrayList<>();

    for (int i = 0; i < NUM_REFLECTIONS; i++) {
      CollisionData collisionData = getCollisionData(direction, origin);

      if (collisionData.distance >= 0) {
        origin = origin.add(direction.multiply(collisionData.distance));
        Vec3 normal = collisionData.renderObject.getNormal(origin);
        direction = direction.getReflectionVector(normal);
        collidedObjects.add(collisionData);
        collidedObjects.get(i).lightness = getLightness(origin, direction,
                                                        collisionData.renderObject);
      } else {
        break;
      }
    }

    return collidedObjects;
  }

  private Color getColor(ArrayList<CollisionData> collidedObjects) {
    if (collidedObjects.size() > 0) {
      Color color;

      if (collidedObjects.get(0).renderObject instanceof Sphere) {
        color = collidedObjects.get(collidedObjects.size() - 1).renderObject.getColor();
      } else {
        color = collidedObjects.get(0).renderObject.getColor();
      }

      double lightness = collidedObjects.get(0).lightness;
      return new Color(color.getRed() * lightness, color.getGreen() * lightness,
                       color.getBlue() * lightness, 1);
    } else {
      return new Color(0, 0, 0, 1);
    }
  }

  private Color getPixel(double x, double y) {
    Vec3 screenPixelPos = new Vec3(x, y, 0);
    Vec3 direction = screenPixelPos.subtract(cameraPos).normalize();
    Vec3 origin = new Vec3(cameraPos);
    ArrayList<CollisionData> collidedObjects = trace(direction, origin);
    return getColor(collidedObjects);
  }
}
