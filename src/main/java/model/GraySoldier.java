package model;

public class GraySoldier extends EnemySoldier {
    public GraySoldier(int id) {
        this.health = 1000;
        this.attack = 800;
        this.id = String.valueOf(id);
    }
}
