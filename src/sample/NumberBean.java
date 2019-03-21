package sample;

import java.io.Serializable;
import java.util.Random;

public class NumberBean implements Serializable, Runnable {
    private int value;
    private Cordinates cords;
    public boolean isPrime;
    public static int probabilityOfGoingTowardsZero = 75;
    private boolean isEaten = false;


    public NumberBean() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Cordinates getCords() {
        return cords;
    }

    public void setCords(Cordinates cords) {
        this.cords = cords;
    }

    public boolean isEaten() {
        return isEaten;
    }

    public void setEaten(boolean eaten) {
        isEaten = eaten;
    }

    private void move() {

        if (value == 0) {
            Random r = new Random();
            int dec = r.nextInt(4);
            switch (dec) {
                case 0: {
                    int newX = cords.getX() - 1;
                    if (newX >= 0 || newX < Strip.rowSize) {
                        if (Strip.strip.containsValue(new Cordinates(newX, cords.getY()))) {

                            for (NumberBean nb : Strip.beans) {
                                if (nb.cords == cords) {
                                    nb.setEaten(true);
                                }
                            }
                            cords.setX(newX);
                            Strip.strip.replace(0, cords);

                        } else {
                            cords.setX(newX);
                            Strip.strip.replace(0, cords);
                        }
                    }
                    break;
                }
                case 1: {
                    int newX = cords.getX() + 1;
                    if (newX >= 0 || newX < Strip.rowSize) {
                        if (Strip.strip.containsValue(new Cordinates(newX, cords.getY()))) {

                            for (NumberBean nb : Strip.beans) {
                                if (nb.cords == cords) {
                                    nb.setEaten(true);
                                }
                            }
                            cords.setX(newX);
                            Strip.strip.replace(0, cords);

                        } else {
                            cords.setX(newX);
                            Strip.strip.replace(0, cords);
                        }
                    }
                    break;
                }
                case 2: {
                    int newY = cords.getY() - 1;
                    if(newY >= 0 || newY < Strip.rowSize){
                        if(Strip.strip.containsValue(new Cordinates(cords.getY(), newY))){

                            for (NumberBean nb: Strip.beans) {
                                if(nb.cords == cords){
                                    nb.setEaten(true);
                                }
                            }
                            cords.setY(newY);
                            Strip.strip.replace(0,cords);

                        }
                        else{
                            cords.setY(newY);
                            Strip.strip.replace(0,cords);
                        }
                    }
                    break;
                }
                case 3: {
                    int newY = cords.getY() + 1;
                    if(newY >= 0 || newY < Strip.rowSize){
                        if(Strip.strip.containsValue(new Cordinates(cords.getY(), newY))){

                            for (NumberBean nb: Strip.beans) {
                                if(nb.cords == cords){
                                    nb.setEaten(true);
                                }
                            }
                            cords.setY(newY);
                            Strip.strip.replace(0,cords);

                        }
                        else{
                            cords.setY(newY);
                            Strip.strip.replace(0,cords);
                        }
                    }
                    break;
                }
                default: {
                    break;
                }
            }
        } else {
            int distX = cords.getX() - Strip.strip.get(0).getX();
            int distY = cords.getY() - Strip.strip.get(0).getY();

            int directionX = distX > 0 ? -1 : 1;
            int directionY = distY > 0 ? -1 : 1;

            Random r = new Random();
            int probability = r.nextInt(100);
            if (probability > probabilityOfGoingTowardsZero) {
                if (r.nextBoolean()) { // po OX
                    if (isGoodPos(cords.getX() + directionX, cords.getY())) {
                        cords.setX(cords.getX() + directionX);
                        Strip.strip.replace(value, cords);
                        return;
                    }
                    if (isGoodPos(cords.getX(), cords.getY() + directionY)) {
                        cords.setY(cords.getY() + directionY);
                        Strip.strip.replace(value, cords);
                        return;
                    }
                } else {
                    if (isGoodPos(cords.getX(), cords.getY() + directionY)) {
                        cords.setY(cords.getY() + directionY);
                        Strip.strip.replace(value, cords);
                        return;
                    }
                    if (isGoodPos(cords.getX() + directionX, cords.getY())) {
                        cords.setX(cords.getX() + directionX);
                        Strip.strip.replace(value, cords);
                        return;
                    }
                }
            } else {
                if (r.nextBoolean()) { // po OX
                    if (r.nextBoolean()) {
                        if (isGoodPos(cords.getX() - 1, cords.getY())) {
                            cords.setX(cords.getX() - 1);
                            Strip.strip.replace(value, cords);
                            return;
                        }
                        if (isGoodPos(cords.getX() + 1, cords.getY())) {
                            cords.setX(cords.getX() + 1);
                            Strip.strip.replace(value, cords);
                            return;
                        }
                    } else {
                        if (isGoodPos(cords.getX() + 1, cords.getY())) {
                            cords.setX(cords.getX() + 1);
                            Strip.strip.replace(value, cords);
                            return;
                        }
                        if (isGoodPos(cords.getX() - 1, cords.getY())) {
                            cords.setX(cords.getX() - 1);
                            Strip.strip.replace(value, cords);
                            return;
                        }
                    }
                } else { // po OY
                    if (r.nextBoolean()) {
                        if (isGoodPos(cords.getX(), cords.getY() - 1)) {
                            cords.setY(cords.getY() - 1);
                            Strip.strip.replace(value, cords);
                            return;
                        }
                        if (isGoodPos(cords.getX(), cords.getY() + 1)) {
                            cords.setY(cords.getY() + 1);
                            Strip.strip.replace(value, cords);
                            return;
                        }
                    } else {
                        if (isGoodPos(cords.getX(), cords.getY() + 1)) {
                            cords.setY(cords.getY() + 1);
                            Strip.strip.replace(value, cords);
                            return;
                        }
                        if (isGoodPos(cords.getX(), cords.getY() - 1)) {
                            cords.setY(cords.getY() - 1);
                            Strip.strip.replace(value, cords);
                            return;
                        }
                    }
                }
            }
        }
    }


    private boolean isGoodPos(int x, int y) {
        if (x < 0 || x > Strip.rowSize || y < 0 || y > Strip.colSize)
            return false;
        return !Strip.strip.containsValue(new Cordinates(x, y));
    }

    public void isPrime() {
        if (value == 1) {
            isPrime = true;
        } else {
            for (int i = 2; i <= Math.sqrt(value); i++) {
                if (value % i == 0) isPrime = false;
            }
            isPrime = true;
        }
    }

    @Override
    public void run() {
        while (!isEaten) {
            System.out.println("number: " + value + "x: " + cords.getX() + " y: " + cords.getY() + "\n");
            move();
            System.out.println("number: " + value + "x: " + cords.getX() + " y: " + cords.getY() + "\n");
        }

    }
}
