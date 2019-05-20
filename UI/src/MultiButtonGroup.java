import javax.swing.*;
import java.util.Vector;

public class MultiButtonGroup extends ButtonGroup {

    int maxSelected; // maximum number of buttons allowed to be selected

    Vector<ButtonModel> selected = new Vector<>(); //currently selected Buttons

    public MultiButtonGroup() {
        super();
        maxSelected = 1;
    }

    public MultiButtonGroup(int maxSelected) {
        super();
        this.maxSelected = maxSelected;
    }

    public MultiButtonGroup(Vector<AbstractButton> buttons, int maxSelected) {
        super();
        this.buttons = buttons;
        this.maxSelected = maxSelected;
    }

    @Override
    public void add(AbstractButton b) {
        if (b == null)
            return;
        buttons.addElement(b);

        if (b.isSelected()) {
            if (selected.size() < maxSelected) {
                selected.addElement(b.getModel());
            } else {
                b.setSelected(false);
            }
        }
        b.getModel().setGroup(this);
    }

    @Override
    public void remove(AbstractButton b) {
        if (b == null)
            return;
        buttons.removeElement(b);
        if (selected.contains(b))
            selected.removeElement(b);
        b.getModel().setGroup(null);
    }

    @Override
    public void clearSelection() {
        if (!selected.isEmpty()) {
            selected.forEach(abstractButton -> abstractButton.setSelected(false));
            selected.clear();
        }
    }

    public Vector<ButtonModel> getSelected() {
        return selected;
    }

    @Override
    public void setSelected(ButtonModel m, boolean b) {
        if (b && m != null && !selected.contains(m)) {
            selected.addElement(m);
            m.setSelected(true);
        }
    }

    @Override
    public boolean isSelected(ButtonModel m) {
        return selected.contains(m);
    }

    @Override
    public int getButtonCount() {
        if (buttons == null) {
            return 0;
        } else {
            return buttons.size();
        }
    }
}
