package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Block implements Entity {
    int x, y;
    private Rectangle view;

    public Block(int x, int y) {
        this.x = x;
        this.y = y;
        this.view = new Rectangle(x, y, 10, 50);
        view.setFill(Color.RED);
    }

    public Rectangle getView() {
        return view;
    }
}
