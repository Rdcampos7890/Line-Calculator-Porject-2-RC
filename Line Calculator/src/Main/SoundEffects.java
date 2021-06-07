package Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.net.URL;
import javax.sound.sampled.Clip;
import javax.swing.JFrame;

/*
 * SoundEffects class, responsible for the sound effects that can be run behind he program.
 */
public class SoundEffects{

	public static Clip clip2;
	public static Clip clip1;
	
	/*
	 * Runs clip1 with the music located on the file stated as a string in the parameter.
	 */
	public void Run(String filePath) throws InterruptedException {
		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Test Sound");
		f.setSize(300, 200);
		f.setVisible(false);
	
		try {
			URL resource = getClass().getResource(filePath); 
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(resource);              
			clip1 = AudioSystem.getClip();
			clip1 = AudioSystem.getClip();
			clip1.open(audioIn);
			clip1.start();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/*
	 * Runs clip2 with the music located on the file stated as a string in the parameter.
	 */
	public void Run2(String filePath) throws InterruptedException {
		
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setTitle("Test Sound");
		f.setSize(300, 200);
		f.setVisible(false);
	
		try {
			URL resource = getClass().getResource(filePath); 
			AudioInputStream audioIn = AudioSystem.getAudioInputStream(resource);              
			clip2 = AudioSystem.getClip();
			clip2 = AudioSystem.getClip();
			clip2.open(audioIn);
			clip2.start();
		} 
		catch (Exception e) {
			e.printStackTrace();
		} 
	}
}

