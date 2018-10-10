package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import raytracer.Light;
import raytracer.Plane;
import raytracer.RenderScene;
import raytracer.Sphere;
import raytracer.Util;
import raytracer.Vec3;

public class Main extends Application {

  private static final int winWidth = 400;
  private static final int winHeight = 400;
  private static final int renderDetail = 20;
  private RenderScene scene;
  private Canvas canvas;

  @Override
  public void start(Stage primaryStage) throws Exception {
    setupWindow(primaryStage);
    setupRenderScene();
    drawRenderScene();
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private void setupWindow(Stage primaryStage) {
    Group root = new Group();
    canvas = new Canvas(winWidth, winHeight);
    root.getChildren().add(canvas);
    primaryStage.setTitle("Raytracing");
    primaryStage.setScene(new Scene(root, winWidth, winHeight));
  }

  private void setupRenderScene() {
    scene = new RenderScene(400, 400);
    scene.setCameraPos(new Vec3(0, 0, 0.5));
    scene.addLight(new Light(new Vec3(0.3, 0.3, 0), 0.8));
    scene.addLight(new Light(new Vec3(-0.3, 0.25, 0), 0.7));
    scene.addLight(new Light(new Vec3(0.33, -0.34, -0.3), 0.3));

    scene.addObject(new Sphere(new Vec3(0, 0, -0.5), 0.3, new Color(1, 0, 0, 1)));
    scene.addObject(new Sphere(new Vec3(0.3, 0.3, -0.5), 0.25, new Color(1, 0, 0, 1)));
    scene.addObject(new Plane(new Vec3(1, 0, 0), new Vec3(-1, 0, 0), new Color(0, 0, 1, 1)));
    scene.addObject(new Plane(new Vec3(-1, 0, 0), new Vec3(1, 0, 0), new Color(0.5, 0.3, 1, 1)));
    scene.addObject(new Plane(new Vec3(0, 1, 0), new Vec3(0, -1, 0), new Color(0.5, 0.8, 1, 1)));
    scene.addObject(new Plane(new Vec3(0, -1, 0), new Vec3(0, 1, 0), new Color(0.9, 0.3, 1, 1)));
    scene.addObject(new Plane(new Vec3(0, 0, 1), new Vec3(0, 0, -1.5), new Color(0, 1, 0, 1)));
    scene.addObject(new Plane(new Vec3(0, 0, -1), new Vec3(0, 0, 1.5), new Color(1, 1, 0, 1)));
  }

  private void drawRenderScene() {
    ArrayList<ArrayList<Color>> colorList = new ArrayList<>();

    for (int i = 0; i < renderDetail; i++) {
      double percentage = ((double) i) / ((double) renderDetail) * 100.0;
      System.out.println(percentage + "%");
      colorList.add(scene.renderAndGetImage());
    }
    System.out.println("100%");

    PixelWriter pw = canvas.getGraphicsContext2D().getPixelWriter();

    for (int y = 0; y < winHeight; y++) {
      for (int x = 0; x < winWidth; ++x) {
        ArrayList<Color> pixelColors = new ArrayList<>();

        for (ArrayList<Color> colors : colorList) {
          pixelColors.add(colors.get(y * winWidth + x));
        }

        Color color = Util.getMeanColor(pixelColors);
        pw.setColor(x, winHeight - y - 1, color);
      }
    }
  }
}
