package model;

public class GoroMajima extends SelfSoldier {
    public GoroMajima() {
        this.health = 4000;
        this.attack = 4500;
        this.fieldOfView *= 3;
        this.attackRange *= 4;
        this.id = "goro-majima";
        this.view.setId(this.id);
    }
}
