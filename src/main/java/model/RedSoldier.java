package model;

public class RedSoldier extends EnemySoldier {
    public RedSoldier(int id) {
        this.health = 1000;
        this.attack = 500;
        this.velocity = 3 * SoldierVelocity;
        this.id = String.valueOf(id);
    }
}
