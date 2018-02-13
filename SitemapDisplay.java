package campingPack;

import javax.swing.*;
import java.awt.event.ActionListener;

public class SitemapDisplay extends JFrame /**implements ActionListener*/{

    private JComboBox year;

    private JComboBox month;

    private JComboBox day;

    private JButton checkCampground;

    private JPanel dayChooser;

    private JLabel[] siteLabels;

    private SiteMapPanel siteMap;



    public SitemapDisplay(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        siteMap = new SiteMapPanel(this, "StringShite");

        add(siteMap);
    }

    public static void main(String[] args){
        SitemapDisplay frame = new SitemapDisplay();
        frame.setSize(1000,1000);
        frame.setVisible(true);
    }
}
