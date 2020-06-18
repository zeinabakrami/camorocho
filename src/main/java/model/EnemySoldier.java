package model;

import controller.Controller;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Circle;

public class EnemySoldier extends Soldier {

    public EnemySoldier() {
        velocity = 2 * SoldierVelocity;
        this.view = new Circle(5);
    }

    public boolean seanItems(Game game, Item item) {
        if (item.setDurability(this.attack * GameConst.EnemySoldierAttackItemPower / 100))
            game.gameOver();
        return true;
    }

    public void setHealth(int attack) {
        if (health > attack)
            health -= attack;
        else
            health = 0;
    }

    public boolean attackSoldier(SelfSoldier selfSoldier) {
        if (Controller.calcLength(this.x, this.y, selfSoldier.x, selfSoldier.y) < SoldierAttackRange) {
            return selfSoldier.setHealth(this.attack * EnemySoldierAttackSoldierPower / 100);
        } else {
            this.x = selfSoldier.x;
            this.y = selfSoldier.y;
        }
        return false;
    }

    public void goForward(Game game, Group root, Item item) {
        for (Node node : root.getChildren()) {
            if (node.getId().equals(this.id)) {
                if (Controller.calcLength(item.x, item.y, this.x, this.y) - 50 > this.attackRange) {
                    int a = item.y + 50 - this.y;
                    if (Math.abs(a) > velocity)
                        if (a > 0)
                            a = 1;
                        else
                            a = -1;
                    int b = (int) Math.sqrt(Math.pow(velocity, 2) - Math.pow(a, 2));
                    x += b;
                    y += a;
                    node.setLayoutX(x);
                    node.setLayoutY(y);
                    break;
                } else {
                    seanItems(game, item);
                }
            }
        }
    }
}
