package controller;

public class Controller {
    public static double calcLength(int... pos) {
        return  Math.sqrt(Math.pow(pos[0] - pos[2], 2) + Math.pow(pos[1] - pos[3], 2));
    }
}
