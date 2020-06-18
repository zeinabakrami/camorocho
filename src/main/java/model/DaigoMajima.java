package model;

public class DaigoMajima extends SelfSoldier {
    public DaigoMajima() {
        this.health = 4000;
        this.attack = 4000;
        this.attackRange *= 2;
        this.id = "daigo-majima";
        this.view.setId(this.id);
    }
}
