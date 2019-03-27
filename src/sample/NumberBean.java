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

  /*  private void move() {

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
*/

    private void move() {
        Random r = new Random();
        if (value == 0) {
            int dec = r.nextInt(4);
            for (int i = 0; i < 2; i++) {
                int newX, newY;
                switch (dec) {
                    case 0: { //lewo
                        newX = cords.getX() - 1;
                        newY = cords.getY();
                        break;
                    }
                    case 1: { //prawo
                        newX = cords.getX() + 1;
                        newY = cords.getY();
                        break;
                    }
                    case 2: { //gora
                        newX = cords.getX();
                        newY = cords.getY() + 1;
                        break;
                    }
                    case 3: { //dol
                        newX = cords.getX();
                        newY = cords.getY() - 1;
                        break;
                    }
                    default: {
                        newX = cords.getX();
                        newY = cords.getY();
                        break;
                    }
                }
                if (newX < 0 || newX >= Strip.rowSize || newY < 0 || newY >= Strip.colSize)
                    continue;
                if (Strip.strip[newX][newY] == -1) {
                    Strip.strip[cords.getX()][cords.getY()] = -1;
                    setCords(new Cordinates(newX, newY));
                    Strip.strip[cords.getX()][cords.getY()] = value;
                    return;
                }
                if (Strip.strip[newX][newY] != -1) {
                    int tempValue = Strip.strip[newX][newY];
                    Strip.strip[newX][newY] = 0;
                    for (NumberBean nb : Strip.beans) {
                        if (nb.getCords() == new Cordinates(newX, newY)) {
                            if (nb.isPrime) {
                                nb.cords = new Cordinates(cords.getX(), cords.getY());
                                Strip.strip[cords.getX()][cords.getY()] = tempValue;
                            } else {
                                nb.setEaten(true);
                                nb.setCords(new Cordinates(-1, -1));
                                Strip.strip[cords.getX()][cords.getY()] = -1;
                            }
                            cords = new Cordinates(newX, newY);
                            return;
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < 2; i++) {
                if (r.nextInt(100) < probabilityOfGoingTowardsZero) {
                    if (r.nextBoolean()) { //ox
                        int decX = Strip.beans[0].cords.getX() < cords.getX() ? -1 : 1;
                        if (!isGoodPos(cords.getX() + decX, cords.getY()))
                            continue;
                        Strip.strip[cords.getX()][cords.getY()] = -1;
                        cords = new Cordinates(cords.getX() + decX, cords.getY());
                        Strip.strip[cords.getX()][cords.getY()] = value;

                    } else { //oy
                        int decY = Strip.beans[0].cords.getY() < cords.getY() ? -1 : 1;
                        if (!isGoodPos(cords.getX(), cords.getY() + decY))
                            continue;
                        Strip.strip[cords.getX()][cords.getY()] = -1;
                        cords = new Cordinates(cords.getX(), cords.getY() + decY);
                        Strip.strip[cords.getX()][cords.getY()] = value;
                    }
                    return;
                } else {
                    int newX = -1;
                    int newY = -1;
                    int dec = r.nextInt(4);
                    switch (dec) {
                        case 0: { //lewo
                            newX = cords.getX() - 1;
                            newY = cords.getY();
                            break;
                        }
                        case 1: { //prawo
                            newX = cords.getX() + 1;
                            newY = cords.getY();
                            break;
                        }
                        case 2: { //gora
                            newX = cords.getX();
                            newY = cords.getY() + 1;
                            break;
                        }
                        case 3: { //dol
                            newX = cords.getX();
                            newY = cords.getY() - 1;
                            break;
                        }
                        default: {
                            newX = cords.getX();
                            newY = cords.getY();
                            break;
                        }
                    }
                    if (!isGoodPos(newX, newY)) {
                        continue;
                    } else {
                        Strip.strip[cords.getX()][cords.getY()] = -1;
                        Strip.strip[newX][newY] = value;
                        cords = new Cordinates(newX, newY);
                        return;
                    }
                }
            }
        }
    }

    private boolean isGoodPos(int x, int y) {
        if (x < 0 || x >= Strip.rowSize || y < 0 || y >= Strip.colSize)
            return false;
        return Strip.strip[x][y] == -1;
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
            move();
        }
    }
}
