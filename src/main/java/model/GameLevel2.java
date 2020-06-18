package model;

public class GameLevel2 extends Game {
    int value = 1000;

    public GameLevel2(int width, int height) {
        items = new Item[2];
        items[0] = new Van();
        items[1] = new Truck(width, height, 1);
        allEnemySoldiers = new EnemySoldier[280];
        for (int i = 0; i < 279; i++) {
            if (i % 2 == 0) {
                allEnemySoldiers[i] = new RedSoldier(i);
            } else {
                allEnemySoldiers[i] = new GraySoldier(i);
            }
        }
        allEnemySoldiers[279] = new BossLevel2();
        waveCount = 8;
    }
}
