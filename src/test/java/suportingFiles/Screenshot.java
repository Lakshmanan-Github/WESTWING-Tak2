package suportingFiles;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Screenshot {

	//To take the full size screenshot
	public void Take_ScreenShot(String fileName) throws InterruptedException
	{
		if((fileName !=""))
		{
			Thread.sleep(1000);
			try {
				Toolkit tool = Toolkit.getDefaultToolkit();
				Dimension d = tool.getScreenSize();
				Rectangle rect = new Rectangle(d);
				Robot robot = new Robot();
				File f = new File(System.getProperty("user.dir")+"/Output/Screenshots/"+fileName+".jpg");
				BufferedImage img = robot.createScreenCapture(rect);
				ImageIO.write(img,"jpeg",f);
				Thread.sleep(1000);
			} catch(Exception e){
				e.printStackTrace();
				System.out.println(e);
			}
		}
	}
}
