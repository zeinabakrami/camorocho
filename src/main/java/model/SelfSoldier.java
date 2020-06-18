package model;

import controller.Controller;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SelfSoldier extends Soldier implements GameConst {
    int initialX, initialY, fieldOfView = SelfSoldierFieldOfView, grade = 1;

    public SelfSoldier() {
        this.velocity = 5 * SoldierVelocity;
        this.view = new Circle(5, Color.GREEN);
        this.id = "goro-majima";
        this.view.setId(this.id);
    }

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

    public boolean seenEnemy(Game game) {
        for (EnemySoldier enemySoldier : game.activeEnemySoldiers) {
            if (Controller.calcLength(this.x, this.y, enemySoldier.x, enemySoldier.y) < SelfSoldierFieldOfView) {
                this.goToEnemy(enemySoldier);
                return true;
            }
        }
        return false;
    }

    private void goToEnemy(EnemySoldier enemySoldier) {
        this.x = enemySoldier.x;
        this.y = enemySoldier.y;
    }

    public boolean attackEnemy(Game game) {
        boolean view = false;
        for (EnemySoldier enemySoldier : game.activeEnemySoldiers) {
            if (Controller.calcLength(this.x, this.y, enemySoldier.x, enemySoldier.y) < SoldierAttackRange) {
                enemySoldier.setHealth(this.attack * SelfSoldierAttackPower / 100);
                view = true;
            }
        }
        if (view)
            for (EnemySoldier enemySoldier : game.activeEnemySoldiers) {
                if (Controller.calcLength(this.x, this.y, enemySoldier.x, enemySoldier.y) < EnemySoldierAttackFriendRange)
                    if (enemySoldier.attackSoldier(this))
                        break;
            }
        else
            this.setPosition();
        return false;
    }

    private void setPosition() {
        this.x = this.initialX;
        this.y = this.initialY;
    }

    public boolean setHealth(int attack) {
        if (health > attack)
            health -= attack;
        else {
            health = 0;
            return true;
        }
        return false;
    }

    public void goForward(Game game, Group root, EnemySoldier enemySoldier) {
        for (Node node : root.getChildren()) {
            if (node.getId().equals(this.id)) {
                if (Controller.calcLength(enemySoldier.x, enemySoldier.y, this.x, this.y) > this.attackRange) {
                    int a = this.y - enemySoldier.y;
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
                    attackEnemy(game);
                }
            }
        }
    }
}
