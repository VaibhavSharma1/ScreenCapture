package screenshot;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class Autocapture3 {

public static void main(String[] args) 
{
	System.out.println("Enter interval between 2 screenshots (in minutes)");
	Scanner obj = new Scanner(System.in);
	int min = obj.nextInt();
int interval = 60000*min; //minutes are converted into milliseconds
int Temp = interval/1000; //milliseconds are converted into seconds
int Max = 28800/Temp; //Max possible screenshots in 8 hours
Timer timer = new Timer();
timer.schedule( new TimerTask()
{
 private int i = 0;
 
 public void run()
 {
	 
     System.out.println("System will take next screenshot " + min + " minutes Later");
     try {
         Robot robot = new Robot();
         String format = "jpg";
         String fileName = "FullScreenshot." + format;
          
         Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
         BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
         ImageIO.write(screenFullImage, format, new File(fileName));
         File SrcFile= FileUtils.getFile(("C:\\Users\\vaibhavsharma\\workspace\\screenshot\\FullScreenshot.jpg"));
         FileUtils.copyFile(SrcFile,new File("C:\\Users\\vaibhavsharma\\Desktop\\ScreenCapture\\FullScreenshot" + i + "." + format));
         //FileUtils.moveFile(SrcFile,new File("https://drive.google.com/drive/folders/0B8-LyYp89DwadEVDUFlCcHpxOTA/file.jpg"));
         System.out.println("A full screenshot saved!");
     } catch (AWTException | IOException ex) {
         System.err.println(ex);
     }
     if (++i == Max) timer.cancel(); // Count up to Max times, then cancel
 }
}, interval, interval //Note the second argument for repetition
);

}
	
}