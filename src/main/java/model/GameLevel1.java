package model;

public class GameLevel1 extends Game {
    int value = 500;

    public GameLevel1(int width, int height) {
        items = new Item[2];
        items[0] = new Container(width - 100, height / 4, 0);
        items[1] = new Truck(width - 100, height / 2, 1);
        enemySoldiersCount = 180;
        allEnemySoldiers = new EnemySoldier[enemySoldiersCount];
        for (int i = 0; i < enemySoldiersCount - 1; i++) {
            if (i % 2 == 0) {
                allEnemySoldiers[i] = new RedSoldier(i);
            } else {
                allEnemySoldiers[i] = new GraySoldier(i);
            }
        }
        allEnemySoldiers[enemySoldiersCount - 1] = new BossLevel1();
        waveCount = 6;
        selfSoldiers = new SelfSoldier[10];
        selfSoldiers[0] = new GoroMajima();
        selfSoldiers[1] = new DaigoMajima();
        selfSoldiers[2] = new KaoriSayama();
        selfSoldiers[3] = new TaigaSaejima();
        selfSoldiers[4] = new SoheiDojima();
        selfSoldiers[5] = new KojiShindo();
        selfSoldiers[6] = new SotaroKomaki();
        selfSoldiers[7] = new ShintaroKomaki();
        selfSoldiers[8] = new RyoTakashima();
        selfSoldiers[9] = new OsamuKashiwagi();
    }
}
