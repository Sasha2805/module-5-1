package fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Scanner;

public class dddd extends Application {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private int n = 5;
    private int centerStarX;
    private int centerStarY;
    private double radius;

    public void dataInput(Scanner in){   //Ввод данных
        System.out.print("Введите X центра звезды: ");
        this.centerStarX = in.nextInt();
        System.out.print("Введите Y центра звезды: ");
        this.centerStarY = in.nextInt();
        System.out.print("Введите радиус звезды: ");
        this.radius = in.nextDouble();
    }

    @Override
    public void start(Stage stage) {
        userInterface(stage);
    }

    private void userInterface(Stage stage) {
        Scanner in = new Scanner(System.in);
        dataInput(in);
        Pane root = new Pane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawStarShape(gc);
        root.getChildren().add(canvas);

        Scene scene = new Scene(root, WIDTH, HEIGHT, Color.WHITESMOKE);
        stage.setTitle("Star");
        stage.setScene(scene);
        stage.show();
    }


    private void drawStarShape(GraphicsContext gc) {
        double innerRadius = radius/3;
        double startingAngle = Math.PI / 2;
        double angleOfRotation = Math.PI / n;

        double[] xPoints = new double[2*n];
        double[] yPoints = new double[2*n];
        for (int i = 0; i < 2 * n; i++) {
            if (i % 2 == 1){
                xPoints[i] = centerStarX + innerRadius * Math.cos(startingAngle);
                yPoints[i] = centerStarY - innerRadius * Math.sin(startingAngle);
            } else {
                xPoints[i] = centerStarX + radius * Math.cos(startingAngle);
                yPoints[i] = centerStarY - radius * Math.sin(startingAngle);
            }
            startingAngle += angleOfRotation;
        }
        gc.strokePolygon(xPoints, yPoints, xPoints.length);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
