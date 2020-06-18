package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Container extends Item {
    public Container(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.durability = 3000;
        this.view = new Rectangle(x, y, 50, 100);
        ((Rectangle) view).setFill(Color.YELLOW);
        this.id = "container" + id;
        this.view.setId(this.id);
    }
}
