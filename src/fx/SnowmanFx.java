package fx;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.util.Random;
import java.util.Scanner;

public class SnowmanOneFx {
    public static final int WIDTH = 600;
    public static final int HEIGHT = 700;
    private int countCircles;
    private double minRadius;
    private double maxRadius;

    public void countCircles(Scanner in){   // Ввод количества кругов
        System.out.print("Введите количество кругов: ");
        this.countCircles = in.nextInt();
    }

    public void inputRadius(Scanner in){   //Ввод минимального и максимального радиуса круга
        System.out.print("Введите минимальный радиус круга: ");
        this.minRadius = in.nextDouble();
        System.out.print("Введите максимальный радиус круга: ");
        this.maxRadius = in.nextDouble();
    }
    public static Color RandomColor() {  // Генерация цвета круга
        Random random = new Random();
        return Color.color(random.nextDouble(), random.nextDouble(), random.nextDouble());
    }

    public static double RandomRadius(double minRadius, double maxRadius) {  // Генерация радиуса круга
        return minRadius + Math.random() * (maxRadius - minRadius);
    }

    public Circle drawCircle(){    // Рисуем круг
        Circle circle = new Circle(RandomRadius(minRadius, maxRadius));
        circle.setFill(Color.WHITE);
        circle.setStroke(RandomColor());
        circle.setStrokeWidth(2);
        return circle;
    }

    public Circle[] drawSnowman(){
        Circle[] circles = new Circle[countCircles];   // Создаем массив с кругами
        for (int i = 0; i < circles.length; i++){
            circles[i] = drawCircle();
        }

        // Задаем координаты кругам
        double centerX = WIDTH / 2;
        double centerY = HEIGHT - circles[0].getRadius() - circles[0].getStrokeWidth();
        for (int i = 0; i < circles.length; i++){
            if (i == 0) {
                circles[i].setCenterX(centerX);
                circles[i].setCenterY(centerY);
            }else {
                circles[i].setCenterX(centerX);
                centerY = centerY - circles[i-1].getRadius() - circles[i].getRadius() - circles[i].getStrokeWidth();
                circles[i].setCenterY(centerY);
            }
        }
        return circles;
    }

    public Circle[] drawSnowmanFace(Circle[] snowman){
        Circle[] face = new Circle[3];
        for (int i = 0; i < face.length; i++){  // Заполняем массив кругов на лице
            face[i] = drawCircle();
        }
        double maxRadius = (snowman[snowman.length-1].getRadius()/3)/2;    // Определяем диапазон радиуса для глаз
        double minRadius = maxRadius / 3;

        // Задаем координаты кругам
        for (int i = 0; i < face.length; i++){
            double radius = minRadius + Math.random() * (maxRadius - minRadius); // Генерируем радиус в диапазоне
            if (i == 0){
                // Координаты правого глаза
                face[i].setCenterX(snowman[snowman.length-1].getCenterX() - snowman[snowman.length-1].getRadius()/2);
                face[i].setCenterY(snowman[snowman.length-1].getCenterY() - snowman[snowman.length-1].getRadius()/3);
            }else if (i == 1){
                // Координаты левого глаза
                face[i].setCenterX(snowman[snowman.length-1].getCenterX() + snowman[snowman.length-1].getRadius()/2);
                face[i].setCenterY(snowman[snowman.length-1].getCenterY() - snowman[snowman.length-1].getRadius()/3);
            }else {
                // Координаты носа
                face[i].setCenterX(snowman[snowman.length-1].getCenterX());
                face[i].setCenterY(snowman[snowman.length-1].getCenterY());
            }
            face[i].setRadius(radius);
        }
        return face;
    }
}
