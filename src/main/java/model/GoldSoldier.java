package model;

public class GoldSoldier extends EnemySoldier {
    public GoldSoldier() {
        this.health = 800;
        this.attack = 2000;
        this.attackRange *= 5;
        this.velocity = SoldierVelocity;
    }
}
