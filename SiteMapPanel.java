package campingPack;

import javax.swing.*;
import java.awt.*;

public class SiteMapPanel extends JPanel {

    private String date;

    private static final int SITE_WIDTH = 200;

    private static final int SITE_LENGTH = 400;

    public SiteMapPanel(SitemapDisplay paFrame, String date) {

    }
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        //draw site 1
        if(true){
            g.fillRect(100,100,SITE_LENGTH, SITE_WIDTH);
            g.drawString("Stie 1",250,250);
        }
        else{
            g.drawRect(100,100,SITE_LENGTH, SITE_WIDTH);
            g.drawString("Stie 1",250,250);
        }

        //draw site 2
        if(true){
            g.fillRect(600, 100,SITE_LENGTH, SITE_WIDTH);
            g.drawString("Site 2",650,250);
        }
        else{
            g.drawRect(600, 100,SITE_LENGTH, SITE_WIDTH);
            g.drawString("Site 2",650,250);
        }

        //draw site 5
        if(true){
            g.fillRect(100,400,SITE_LENGTH, SITE_WIDTH);
            g.drawString("Site 5",250,600);
        }
        else{
            g.drawRect(100,400,SITE_LENGTH, SITE_WIDTH);
            g.drawString("Site 5",250,600);
        }

        //draw site 4
        if(true){
            g.fillRect(600,400,SITE_LENGTH, SITE_WIDTH);
            g.drawString("Site 4",250,600);
        }
        else{
            g.drawRect(600,400,SITE_LENGTH, SITE_WIDTH);
            g.drawString("Site 4",250,600);
        }
    }


}
