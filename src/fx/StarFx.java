package fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.Scanner;

public class StarFx extends Application {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private int n = 5;
    private int centerStarX;
    private int centerStarY;
    private double radius;

    public void dataInput(Scanner in){   //Ввод данных с консоли
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
        Pane pane = new Pane();
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawStarShape(gc);
        pane.getChildren().add(canvas);

        Scene scene = new Scene(pane, WIDTH, HEIGHT);
        stage.setTitle("Star");
        stage.setScene(scene);
        stage.show();
    }


    private void drawStarShape(GraphicsContext gc) {
        double innerRadius = radius / 2.5;      // Внутрений радиус
        double startingAngle = Math.PI / 2;     // Начальный угол, верхняя точка звезды
        double angleOfRotation = Math.PI / n;   // Угол поворота

        double[] xPoints = new double[2*n];
        double[] yPoints = new double[2*n];
        // Цикл задает координаты вершинам
        for (int i = 0; i < 2 * n; i++) {
            if (i % 2 == 1){
                xPoints[i] = centerStarX + innerRadius * Math.cos(startingAngle);
                yPoints[i] = centerStarY - innerRadius * Math.sin(startingAngle);
            } else {
                xPoints[i] = centerStarX + radius * Math.cos(startingAngle);
                yPoints[i] = centerStarY - radius * Math.sin(startingAngle);
            }
            startingAngle += angleOfRotation;   // Увеличиваем угол
        }
        gc.strokePolygon(xPoints, yPoints, xPoints.length); // Рисуем звезду
    }

    public static void main(String[] args) {
        launch(args);
    }
}
