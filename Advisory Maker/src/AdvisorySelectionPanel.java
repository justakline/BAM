import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class AdvisorySelectionPanel extends JPanel
{
    private Vector<AdvisorButton> advisors;

    public AdvisorySelectionPanel(GUI host)
    {
        super(new GridLayout(0, 1, 0, 0));
        advisors = new Vector<>();
        this.add(new JLabel("Advisories:", SwingConstants.CENTER));

        //Create AdvisorButtons
        for (int i = 0; i < TestCases.getAdvisorStringList().size(); i++) {
            AdvisorButton adv = new AdvisorButton(new Advisory(TestCases.getStudentGroupTest(),
                    TestCases.getAdvisorStringList().get(i)));
            adv.addActionListener(host.getRightPanel());
            advisors.add(adv);
            this.add(adv);
        }
    }
}