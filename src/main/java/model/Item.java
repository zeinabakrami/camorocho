package model;

import javafx.scene.Node;

public class Item implements Entity, GameConst {
    int durability, grade = 1, x, y;
    String id;
    Node view;

    public boolean setTurbo(Game game) {
        int need_price = grade * 10;
        if (game.price > need_price) {
            game.price -= need_price;
            grade++;
            return true;
        } else {
            return false;
        }
    }

    public boolean setDurability(int durability) {
        if (durability < this.durability)
            this.durability -= durability;
        else
            return true;
        return false;
    }

    public Node getView() {
        return view;
    }

    public String getId() {
        return id;
    }
}