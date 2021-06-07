package Main;

import javax.sound.sampled.Clip;

/*
 * Main class, runs the main method.
 */
public class Main {

	/*
	 * Main method, creates a local "SoundEffects" object for the sounds of the program
	 * and runs the program by calling o nthe constructor of the "Text" class.
	 */
	public static void main(String[] args) throws InterruptedException {
		
		SoundEffects clip = new SoundEffects();
		clip.Run("(Free) Aggressive Trap Beat - Hacker Rap Instrumental Music 2017 DiKadia #Instrumentals.wav");
        SoundEffects.clip1.loop(Clip.LOOP_CONTINUOUSLY);
		new Text();
		
	}

}
