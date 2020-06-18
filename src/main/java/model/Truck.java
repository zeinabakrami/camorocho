package model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Truck extends Item {
    public Truck(int x, int y, int id) {
        this.x = x;
        this.y = y;
        this.durability = 5000;
        this.view = new Rectangle(x, y, 50, 100);
        ((Rectangle) view).setFill(Color.PURPLE);
        this.id = "truck" + id;
        this.view.setId(this.id);
    }
}
