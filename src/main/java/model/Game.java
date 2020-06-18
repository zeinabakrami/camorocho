package model;

import controller.Controller;
import controller.Runner;
import javafx.scene.Group;
import view.Main;

import java.util.Timer;
import java.util.TimerTask;

public class Game {
    int price = 100, waveCount, wave, enemySoldiersCount;
    Timer timer;
    Item[] items;
    SelfSoldier[] selfSoldiers;
    EnemySoldier[] allEnemySoldiers, activeEnemySoldiers;
    private Group root;
    public boolean start = false;

    public void run(Group root) {
        this.root = root;
        Runner runner = new Runner(this);
        timer = new Timer();
        timer.scheduleAtFixedRate(runner, 0, 100);
    }

    public void checkGame() {
        for (Item item : items) {
            if (item.durability == 0) {
                gameOver();
                return;
            }
        }
        boolean live = false;
        for (SelfSoldier selfSoldier : selfSoldiers) {
            if (selfSoldier.health > 0) {
                live = true;
                break;
            }
        }
        if (!live) {
            gameOver();
            return;
        }
        live = false;
        for (EnemySoldier enemySoldier : allEnemySoldiers) {
            if (enemySoldier.health > 0) {
                live = true;
                break;
            }
        }
        if (!live)
            gameWon();
    }

    private void gameWon() {
        timer.cancel();
        start = false;
        System.out.println("Game Won!");
    }

    public void gameOver() {
        timer.cancel();
        start = false;
        System.out.println("Game Over!!");
    }

    public Item[] getItems() {
        return items;
    }

    int getLastLiveEnemy() {
        for (int i = 0; i < enemySoldiersCount; i++) {
            if (allEnemySoldiers[i].health > 0) {
                return i;
            }
        }
        return -1;
    }

    public EnemySoldier[] setActiveEnemySoldiers(int wave) {
        this.wave = wave;
        if (wave == waveCount) {
            activeEnemySoldiers = new EnemySoldier[1];
            activeEnemySoldiers[0] = allEnemySoldiers[enemySoldiersCount - 1];
        } else {
            int x = 0;
            activeEnemySoldiers = new EnemySoldier[enemySoldiersCount / (waveCount - 1)];
            for (int i = (wave - 1) * enemySoldiersCount / (waveCount - 1); i < wave * enemySoldiersCount / (waveCount - 1); i++) {
                activeEnemySoldiers[x] = allEnemySoldiers[i];
                x++;
            }
        }
        return activeEnemySoldiers;
    }

    public EnemySoldier[] getActiveEnemySoldiers() {
        return activeEnemySoldiers;
    }

    public SelfSoldier[] getSelfSoldiers() {
        return selfSoldiers;
    }

    public void goForward() {
        for (EnemySoldier enemySoldier : activeEnemySoldiers) {
            if (start && enemySoldier.isAlive()) {
                int i = 0, min = Integer.MAX_VALUE;
                for (int j = 0; j < items.length; j++) {
                    Item item = items[j];
                    int m = (int) Controller.calcLength(item.x, item.y, enemySoldier.x, enemySoldier.y);
                    if (m < min) {
                        i = j;
                        min = m;
                    }
                }
                enemySoldier.goForward(this, root, items[i]);
            }
        }
        for (SelfSoldier selfSoldier : selfSoldiers) {
            if (start && selfSoldier.isAlive()) {
                EnemySoldier e = null;
                double min = Double.MAX_VALUE;
                for (EnemySoldier enemySoldier : activeEnemySoldiers) {
                    double len = Controller.calcLength(selfSoldier.x, selfSoldier.y, enemySoldier.x, enemySoldier.y);
                    if (len < selfSoldier.fieldOfView && len < min) {
                        e = enemySoldier;
                        min = len;
                    }
                }
                if (e != null)
                    selfSoldier.goForward(this, root, e);
            }
        }
        if (allEnemySoldiers[enemySoldiersCount - 1].health == 0) {
            gameWon();
        }
    }
}
