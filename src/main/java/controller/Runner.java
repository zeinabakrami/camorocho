package controller;

import model.Game;

import java.util.TimerTask;

public class Runner extends TimerTask {
    private Game game;

    public Runner(Game game) {
        this.game = game;
    }

    public void run() {
        game.start = true;
        game.goForward();
    }
}
