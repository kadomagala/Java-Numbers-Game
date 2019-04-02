package sample;

import java.io.Serializable;
import java.util.Random;
import java.beans.*;

import static java.lang.Math.abs;
import static java.lang.Math.sqrt;

public class NumberBean implements PropertyChangeListener, VetoableChangeListener, Serializable, Runnable {
    private Cordinates cords = new Cordinates();

    private VetoableChangeSupport veto = new VetoableChangeSupport(this);

    private boolean prime;
    private boolean isAlive = true;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    private int value;

    public NumberBean() { }

    public NumberBean(Cordinates cords, int value) {
        this.setValue(value);
        this.cords = cords;

    }

    public synchronized Cordinates getCords() {
        return cords;
    }

    public synchronized void setCords(Cordinates newCords) throws PropertyVetoException {
        if(isAlive) {
            Cordinates oldCords = new Cordinates(this.cords.getX(), this.cords.getY());
            veto.fireVetoableChange("cords", oldCords, newCords);
            Strip.strip[oldCords.getX()][oldCords.getY()] = -1;
            Strip.strip[newCords.getX()][newCords.getY()] = value;
            this.cords = newCords;
        }
    }

    public synchronized boolean isPrime() {
        return prime;
    }

    public synchronized void setPrime(boolean prime) {
        this.prime = prime;
    }

    public synchronized int getValue() {
        return value;
    }

    public synchronized void setValue(int value) {
        if (primeNumber(value))
            this.setPrime(true);
        else
            this.setPrime(false);
        if(value == -1)
            isAlive = false;
        this.value = value;
    }


    public synchronized void addVetoableChangeListener(VetoableChangeListener l) {
        veto.addVetoableChangeListener(l);
    }

    public synchronized void removeVetoableChangeListener(VetoableChangeListener l) {
        veto.removeVetoableChangeListener(l);
    }



    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        if (evt.getPropertyName().equals("cords")) {
            if (evt.getNewValue() == cords)
                throw new PropertyVetoException("Can't choose this pos", evt);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() == "zeroCords") {
            System.out.println("zero oops");
            if (evt.getNewValue() == cords && !isPrime()) {
                setAlive(false);
                setValue(-1);
                Strip.strip[cords.getX()][cords.getY()] = 0;
            } else if (evt.getNewValue() == cords && prime) {
                cords = (Cordinates) evt.getOldValue();
            }
        }
    }

    public static boolean primeNumber(int number) {
        if (number == 2) return true;
        if (number % 2 == 0) return false;
        for (int i = 3; i * i <= number; i += 2) {
            if (number % i == 0)
                return false;
        }
        return true;
    }

    private int distanceToZero() {
        return abs(Strip.zeroBean.getZeroCords().getX() - getCords().getX()) + abs(Strip.zeroBean.getZeroCords().getY() - getCords().getY());
    }

    @Override
    public void run() {
        while (isAlive) {
            Random r = new Random();
            for (int i = 0; i < 2; i++) {
                if (distanceToZero() > r.nextInt((int) sqrt(Strip.rows * Strip.cols))) {
                    if (r.nextBoolean()) { // OX
                        int dest = (Strip.zeroBean.getZeroCords().getX() > getCords().getX()) ? 1 : -1;
                        try {
                            if (getCords().getX() + dest >= 0 && getCords().getX() + dest < Strip.rows) {
                                if (Strip.zeroBean.getZeroCords() == new Cordinates(getCords().getX() + dest, getCords().getY()))
                                    continue;
                                setCords(new Cordinates(getCords().getX() + dest, getCords().getY()));
                            }
                            else
                                continue;
                        } catch (PropertyVetoException e) {
                            continue;
                        }
                        break;
                    } else { //OY
                        int dest = (Strip.zeroBean.getZeroCords().getY() > getCords().getY()) ? 1 : -1;
                        try {
                            if (getCords().getY() + dest >= 0 && getCords().getY() + dest < Strip.cols) {
                                if (Strip.zeroBean.getZeroCords() == new Cordinates(getCords().getX(), getCords().getY()+ dest))
                                    continue;
                                setCords(new Cordinates(getCords().getX(), getCords().getY() + dest));
                            }
                            else
                                continue;
                        } catch (PropertyVetoException e) {
                            continue;
                        }
                        break;
                    }
                } else {
                    if (r.nextBoolean()) {//OX
                        if (r.nextBoolean()) {//PRAWY
                            int newX = cords.getX() + 1;
                            if (newX < Strip.rows) {
                                try {
                                    if (Strip.zeroBean.getZeroCords() == new Cordinates(newX, getCords().getY()))
                                        continue;
                                    setCords(new Cordinates(newX, getCords().getY()));
                                } catch (PropertyVetoException e) {
                                    continue;
                                }
                                break;
                            }
                        } else {//LEWY
                            int newX = cords.getX() - 1;
                            if (newX >= 0) {
                                try {
                                    if (Strip.zeroBean.getZeroCords() == new Cordinates(newX, getCords().getY()))
                                        continue;
                                    setCords(new Cordinates(newX, getCords().getY()));
                                } catch (PropertyVetoException e) {
                                    continue;
                                }
                                break;

                            }
                        }
                    } else {//OY
                        if (r.nextBoolean()) {//GORA
                            int newY = cords.getY() - 1;
                            if (newY >= 0) {
                                try {
                                    if (Strip.zeroBean.getZeroCords() == new Cordinates(getCords().getX(), newY))
                                        continue;
                                    setCords(new Cordinates(getCords().getX(), newY));
                                } catch (PropertyVetoException e) {
                                    continue;
                                }
                                break;
                            }
                        } else {//DOL
                            int newY = cords.getY() + 1;
                            if (newY < Strip.cols) {
                                try {
                                    if (Strip.zeroBean.getZeroCords() == new Cordinates(getCords().getX(), newY))
                                        continue;
                                    setCords(new Cordinates(getCords().getX(), newY));
                                } catch (PropertyVetoException e) {
                                    continue;
                                }
                                break;
                            }
                        }
                    }
                }
            }
            try {
                int delay = r.nextInt(500) + 600;

                Thread.sleep(delay);
            } catch (InterruptedException e) {

            }
        }
    }

}
