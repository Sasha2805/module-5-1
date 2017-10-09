package fx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class UserInterface extends Application {
    public static final int WIDTH = 500;
    public static final int HEIGHT = 500;
    private Stage window;
    private Scene startScene, infoScene, snowmanScene;

    public Scene StartScene() { // Стартовая сцена (выбор действия)
        Label label = new Label("Выберите действие:");
        label.setStyle("-fx-font-size: 12pt;");

        // Добавила RadioButton для выбора действия
        ToggleGroup group = new ToggleGroup();
        RadioButton button1 = new RadioButton("Ввести параметры снеговика");
        button1.setToggleGroup(group);
        button1.setSelected(true);
        button1.setStyle("-fx-font-size: 10pt;");

        RadioButton button2 = new RadioButton("Покрасить все круги в красный цвет");
        button2.setToggleGroup(group);
        button2.setStyle("-fx-font-size: 10pt;");

        RadioButton button3 = new RadioButton("Сделать градиент");
        button3.setToggleGroup(group);
        button3.setStyle("-fx-font-size: 10pt;");

        Button confirm = new Button("Подтвердить действие");
        confirm.setBorder(Border.EMPTY);
        confirm.setStyle("-fx-font-size: 10pt;");
        confirm.setOnAction(event -> {
            if (button1.isSelected()) window.setScene(sceneInfo());
            if (button2.isSelected()) window.setScene(sceneSnowman(2));
            if (button3.isSelected()) window.setScene(sceneSnowman(3));
        });

        VBox box = new VBox(10);
        box.setPadding(new Insets(30, 0, 0, 25));
        box.getChildren().addAll(label, button1, button2, button3, confirm);
        startScene = new Scene(box, WIDTH, HEIGHT);
        return startScene;
    }

    public Scene sceneInfo(){   // Сцена для ввода параметров снеговика
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label countCircles = new Label("Количество кругов:");
        countCircles.setStyle("-fx-font-size: 10pt;");
        grid.add(countCircles, 0, 0);

        TextField textCountCircles = new TextField();
        grid.add(textCountCircles, 1, 0);

        Label minR = new Label("Минимальный радиус:");
        minR.setStyle("-fx-font-size: 10pt;");
        grid.add(minR, 0, 1);

        TextField textMinR = new TextField();
        grid.add(textMinR, 1, 1);

        Label maxR = new Label("Максимальный радиус:");
        maxR.setStyle("-fx-font-size: 10pt;");
        grid.add(maxR, 0, 2);

        TextField textMaxR = new TextField();
        grid.add(textMaxR, 1, 2);

        Button confirm = new Button("Задать параметры");
        confirm.setStyle("-fx-font-size: 10pt;");
        confirm.setOnAction(event -> {      // Передача параметров снеговика
            int count = Integer.parseInt(textCountCircles.getText());
            double minRadius = Double.parseDouble(textMinR.getText());
            double maxRadius = Double.parseDouble(textMaxR.getText());
            window.setScene(sceneSnowman(count, minRadius, maxRadius));}); // Возвращаем сцену с снеговиком

        HBox box = new HBox(10);
        box.setAlignment(Pos.BOTTOM_RIGHT);
        box.getChildren().add(confirm);
        grid.add(box, 1, 4);
        infoScene = new Scene(grid,WIDTH,HEIGHT);
        return infoScene;
    }

    // Метод для отрисовки снеговика, после передачи параметров через UI
    public Scene sceneSnowman(int countCircles, double minRadius, double maxRadius){
        Pane pane = new Pane();
        SnowmanFx snowman = new SnowmanFx(countCircles, minRadius, maxRadius);
        Circle[] sCircles = snowman.drawSnowman(WIDTH, HEIGHT);
        Circle[] fSnowman = snowman.drawSnowmanFace(sCircles);
            for (int i = 0; i < sCircles.length; i++){
                pane.getChildren().add(sCircles[i]);
            }
            for (int i = 0; i < fSnowman.length; i++){
                pane.getChildren().add(fSnowman[i]);
            }
            snowmanScene = new Scene(pane, WIDTH, HEIGHT, Color.WHITE);
        return snowmanScene;
    }

    // Перегруженный метод для отрисовки снеговика, без передачи параметров (задания 2 и 3)
    public Scene sceneSnowman(int numberTask){
        Pane pane = new Pane();
        SnowmanFx snowman = new SnowmanFx(4, 40, 60);
        Circle[] circles;
        if (numberTask == 2){
            circles = snowman.paintCircles(Color.RED, WIDTH, HEIGHT);   // Красим круги в красный цвет
            for (int i = 0; i < circles.length; i++){
                pane.getChildren().add(circles[i]);
            }
            snowmanScene = new Scene(pane, WIDTH, HEIGHT);
        }
        if (numberTask == 3){
            circles = snowman.snowmanGradient(WIDTH, HEIGHT);   // Градиент
            for (int i = 0; i < circles.length; i++){
                pane.getChildren().add(circles[i]);
            }
            snowmanScene = new Scene(pane, WIDTH, HEIGHT);
        }
        return snowmanScene;
    }


    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        stage.setTitle("Snowman");
        stage.setScene(StartScene());
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
