package sample;

import java.io.Serializable;
import java.util.Random;
import java.beans.*;

public class NumberBean implements PropertyChangeListener {
    private Cordinates cords = new Cordinates();

    private VetoableChangeSupport veto = new VetoableChangeSupport(this);

    private boolean prime;
    private int value;

    public NumberBean(){}

    public NumberBean(Cordinates cords, int value){
        this.setValue(value);
        this.cords = cords;

    }

    public synchronized Cordinates getCords() {
        return cords;
    }

    public synchronized void setCords(Cordinates newCords) throws PropertyVetoException {
        Cordinates oldCords = new Cordinates(this.cords.getX(), this.cords.getY());
        veto.fireVetoableChange("cords",oldCords, newCords);
        this.cords = newCords;
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
        if(primeNumber(value))
            this.setPrime(true);
        else
            this.setPrime(false);
        this.value = value;
    }


    public synchronized void addVetoableChangeListener(VetoableChangeListener l) {
        veto.addVetoableChangeListener(l);
    }
    public synchronized void removeVetoableChangeListener(VetoableChangeListener l) {
        veto.removeVetoableChangeListener(l);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    private static boolean primeNumber(int number){
        if(number == 2) return true;
        if (number%2==0) return false;
        for(int i=3;i*i<=number;i+=2) {
            if(number%i==0)
                return false;
        }
        return true;
    }
}
