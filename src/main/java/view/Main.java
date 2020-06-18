package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainPage(primaryStage);
    }

    public void mainPage(final Stage primaryStage) {
        Button startBtn = new Button("ماموریت ها");
        startBtn.setLayoutX(500);
        startBtn.setLayoutY(200);
        startBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                missons(primaryStage);
            }
        });
        Button exitBtn = new Button("خروج");
        exitBtn.setLayoutX(500);
        exitBtn.setLayoutY(300);
        exitBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                primaryStage.close();
            }
        });
        Group root = new Group(startBtn, exitBtn);
        Scene scene = new Scene(root, 1200, 600);
        primaryStage.setTitle("Camorocho");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void missons(final Stage primaryStage) {
        Button startBtn = new Button("مرحله اول");
        startBtn.setLayoutX(500);
        startBtn.setLayoutY(200);
        startBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                startGame(primaryStage, 1);
            }
        });
        Button exitBtn = new Button("بازگشت");
        exitBtn.setLayoutX(500);
        exitBtn.setLayoutY(300);
        exitBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                mainPage(primaryStage);
            }
        });
        Group root = new Group(startBtn, exitBtn);
        Scene scene = new Scene(root, 1200, 600);
        primaryStage.setTitle("Camorocho");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void startGame(final Stage primaryStage, int step) {
        int width = 1200, height = 600, blockCount = 6;
        final Group root = new Group();
        for (int i = 0; i < blockCount; i++) {
            Block block = new Block(width * 7 / 12, (height / blockCount + 10) * i);
            Node blockView = block.getView();
            blockView.setId("block" + i);
            root.getChildren().add(blockView);
        }
        final Game game;
        if (step == 1) {
            game = new GameLevel1(width, height);
        } else {
            game = new GameLevel2(width, height);
        }
        Item[] items = game.getItems();
        for (Item item : items) {
            root.getChildren().add(item.getView());
        }
        int x = 1;
        for (SelfSoldier selfSoldier : game.getSelfSoldiers()) {
            root.getChildren().add(selfSoldier.getView(width * 2 / 3, height / 11 * x));
            x++;
        }
        x = 1;
        for (EnemySoldier enemySoldier : game.setActiveEnemySoldiers(1)) {
            Node enemy = enemySoldier.getView(width / 12, height / 40 * x);
            enemy.setId(enemySoldier.getId());
            root.getChildren().add(enemy);
            x++;
        }
        Text text = new Text(width / 2.0, height / 2.0, "press p to start");
        root.getChildren().add(text);
        Scene scene = new Scene(root, width, height);
        primaryStage.setTitle("Camorocho");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.addEventHandler(KeyEvent.KEY_RELEASED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.P) {
                    root.getChildren().remove(root.getChildren().size() - 1);
                    System.out.println("start");
                    game.run(root);
                }
            }
        });
    }
}
