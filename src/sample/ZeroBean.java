package sample;

import java.beans.*;
import java.io.Serializable;
import java.util.Random;

public class ZeroBean implements VetoableChangeListener, Serializable, Runnable {


    private Cordinates zeroCords = new Cordinates();
    private PropertyChangeSupport chg = new PropertyChangeSupport(this);

    public ZeroBean() { }

    public synchronized Cordinates getZeroCords() {
        return zeroCords;
    }

    public synchronized void setZeroCords(Cordinates zeroCords) {
        Cordinates oldCords = new Cordinates(this.zeroCords.getX(), this.zeroCords.getY());
        chg.firePropertyChange("zeroCords", oldCords, zeroCords);
        if(NumberBean.primeNumber(Strip.strip[zeroCords.getX()][zeroCords.getY()]))
            Strip.strip[oldCords.getX()][oldCords.getY()] = Strip.strip[zeroCords.getX()][zeroCords.getY()];
        else
            Strip.strip[oldCords.getX()][oldCords.getY()] = -1;
        Strip.strip[zeroCords.getX()][zeroCords.getY()] = 0;
        this.zeroCords.setX(zeroCords.getX());
        this.zeroCords.setY(zeroCords.getY());
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener l){
        chg.addPropertyChangeListener(l);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener l){
        chg.removePropertyChangeListener(l);
    }

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        if (evt.getPropertyName().equals("cords")) {
            if (evt.getNewValue() == zeroCords)
                throw new PropertyVetoException("Can't choose this pos", evt);
        }
    }


    @Override
    public void run() {
        Random r = new Random();
        while(Strip.numbersLeft > 0) {
            for (int i = 0; i < 2; i++) {
                if (r.nextBoolean()) { //OX
                    if (r.nextBoolean()) { //PRAWO
                        if (zeroCords.getX() + 1 < Strip.rows) {
                            setZeroCords(new Cordinates(zeroCords.getX() + 1, zeroCords.getY()));
                            break;
                        }
                    } else { //LEWO
                        if (zeroCords.getX() - 1 >= 0) {
                            setZeroCords(new Cordinates(zeroCords.getX() - 1, zeroCords.getY()));
                            break;
                        }
                    }
                } else { //OY
                    if (r.nextBoolean()) {//gora
                        if (zeroCords.getY() - 1 >= 0) {
                            setZeroCords(new Cordinates(zeroCords.getX(), zeroCords.getY() - 1));
                            break;
                        }
                    } else {
                        if (zeroCords.getY() + 1 < Strip.cols) {
                            setZeroCords(new Cordinates(zeroCords.getX(), zeroCords.getY() + 1));
                            break;
                        }
                    }
                }
            }
            try {
                int delay = r.nextInt(500) + 300;

                Thread.sleep(delay);
            } catch (InterruptedException e) {

            }
        }
    }

}
