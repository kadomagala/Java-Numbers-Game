package sample;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class ZeroBean implements PropertyChangeListener, Serializable, Runnable {


    private Cordinates zeroCords = new Cordinates();
    private PropertyChangeSupport chg = new PropertyChangeSupport(this);

    public ZeroBean() { }

    public synchronized Cordinates getZeroCords() {
        return zeroCords;
    }

    public synchronized void setZeroCords(Cordinates zeroCords) {
        Cordinates oldCords = new Cordinates(this.zeroCords.getX(), this.zeroCords.getY());
        this.zeroCords.setX(zeroCords.getX());
        this.zeroCords.setY(zeroCords.getY());
        chg.firePropertyChange("zeroCords", oldCords, zeroCords);
    }

    public synchronized void addPropertyChangeListener(PropertyChangeListener l){
        chg.addPropertyChangeListener(l);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener l){
        chg.removePropertyChangeListener(l);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public void run() {

    }
}
