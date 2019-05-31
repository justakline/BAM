import javax.swing.*;

public class AdvisorButton extends JToggleButton {

    private Advisory advisory;

    public AdvisorButton(Advisory advisory) {
        super(advisory.getAdvisor());
        this.advisory = advisory;
    }

    @Override
    public String getName() {
		return advisory.getAdvisor();
		}

	public void setName(String name) {
		advisory.setName(name);
		this.setText(advisory.getAdvisor());
	}

	public Advisory getAdvisory() {return advisory;}

	public void setAdvisory(Advisory advisory) {
		this.advisory = advisory;
	}
}
