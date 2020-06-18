package model;

import javafx.scene.Node;

public class Soldier implements Entity, GameConst {
    int x = 0, y = 0, health, attack, attackRange = SoldierAttackRange, velocity = SoldierVelocity;
    String id;
    Node view;

    public Node getView(int x, int y) {
        view.setLayoutX(this.x = x);
        view.setLayoutY(this.y = y);
        return view;
    }

    public String getId() {
        return id;
    }

    public boolean isAlive() {
        return health > 0;
    }
}
