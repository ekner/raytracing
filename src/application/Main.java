package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import raytracer.RenderScene;
import raytracer.Sphere;
import raytracer.Vec3;

public class Main extends Application {

  private static final int winWidth = 400;
  private static final int winHeight = 400;
  private RenderScene renderScene;
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

  private void setupWindow(Stage primaryStage) throws Exception {
    Group root = new Group();
    canvas = new Canvas(winWidth, winHeight);
    root.getChildren().add(canvas);
    primaryStage.setTitle("Raytracing");
    primaryStage.setScene(new Scene(root, winWidth, winHeight));
  }

  private void setupRenderScene() {
    renderScene = new RenderScene(400, 400);
    renderScene.setCameraPos(new Vec3(0.5, 0.5, 0.5));
    renderScene.addSphere(new Sphere(new Vec3(0.3, 0.5, -0.5), 0.5, new Color(1, 0, 0, 1)));
  }

  private void drawRenderScene() {
    ArrayList<Color> pixels = renderScene.renderAndGetImage();
    PixelWriter pw = canvas.getGraphicsContext2D().getPixelWriter();

    //Color pixel = renderScene.getPixel(0.5, 0.5);

    for (int y = 0; y < 400; ++y) {
      for (int x = 0; x < 400; ++x) {
        pw.setColor(x, y, pixels.get(y * 400 + x));
        //pw.setColor(x, y, pixel);
      }
    }
  }
}
