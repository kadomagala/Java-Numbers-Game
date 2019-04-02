package sample;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;

public class StripController implements PropertyChangeListener, VetoableChangeListener {
    private StripView view;
    private StripModel model;

    public StripController(StripView view, StripModel model) {
        this.view = view;
        this.model = model;
        view.updateView(model.getStrip());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {

    }
}
