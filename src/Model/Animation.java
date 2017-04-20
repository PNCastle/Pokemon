/*
 * Authors:  Paul Castleberry, Angel Burr, Sohyun Kim, Isaac Kim
 * Filename: Animation.java
 */

package Model;
import java.awt.image.BufferedImage;

public class Animation {

	
	public Animation() {}
	
	private BufferedImage[] frames;
	private int currentFrame;
	
	private long startTime;
	private long delay;
	
	public void setFrames(BufferedImage[] images) {
		frames = images;
		if (currentFrame >= frames.length) 
			currentFrame = 0;
	}
	
	public void setDelay(long d) {
		delay = d;
	}
	
	public void update() {
		
		if (delay == -1)
			return;
		
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if(elapsed > delay) {
			currentFrame++;
			startTime = System.nanoTime();
		}
		if(frames.length == 1) {
			currentFrame = 0;
			return;
		}
		if(currentFrame == frames.length) {
			currentFrame = 0;
		}
	}
	
	public BufferedImage getImage() {
		return frames[currentFrame];
	}

}
