package gui.image;

import java.awt.*;
import javax.swing.*;

public class ProcessImagesPanel extends JPanel {

	private Image image;
	private MediaTracker mTracker;
	private int imageID;
	private int imageSize;
        private int currentSize;

	public ProcessImagesPanel(int size)
        {
            currentSize=size;
        }
        
        
        public void setImage(String filename) {
		this.setLayout(new BorderLayout());
		mTracker = new MediaTracker(this);
		imageID = 0;
		image = Toolkit.getDefaultToolkit().getImage(filename);

		mTracker.addImage(image,imageID);
		try {
			mTracker.waitForID(imageID);
		}
		catch(InterruptedException e) {
			//System.out.print("Error loading "+filename+".");
		}
		imageSize = image.getWidth(this);
		this.setPreferredSize(new Dimension(882,715));
		repaint();
	}

    public void setCurrentSize(int s) {
        currentSize=s;
        repaint();
    }

    @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
        if (currentSize==1)
            g.drawImage(image,10,10,this);
        else if (currentSize==0)
            g.drawImage(image,10,20 + imageSize,imageSize,imageSize/2,this);
        else
	    g.drawImage(image,20 + imageSize,10,20+2*imageSize,10+imageSize,0,0,imageSize/2,imageSize/2,this);
	}

}