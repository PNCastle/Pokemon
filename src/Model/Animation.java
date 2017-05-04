/*
 * Authors:  Paul Castleberry, Angel Burr, Sohyun Kim, Isaac Kim
 * Filename: Animation.java
 * Purpose:  Animates our sprite sheet as the trainer moves around
 * 			 the map, adjust according to direction and speed.
 * 			 Credit goes to ForeignGuyMike for his tutorials on
 *           sprite animation.
 *           https://www.youtube.com/channel/UC_IV37n-uBpRp64hQIwywWQ
 */

package Model;
import java.awt.image.BufferedImage;

public class Animation {

	
	public Animation() {}
	
	private BufferedImage[] frames; //array of sprites
	private int currentFrame;		//index into frames
	
	private long startTime;			//timers and
	private long delay;				//delay time
	
	//setFrames will set the sent images to frames for this animation
	public void setFrames(BufferedImage[] images) {
		frames = images;
		if (currentFrame >= frames.length) 
			currentFrame = 0;
	}
	
	//setDelay will set the delay time for this animation
	public void setDelay(long d) {
		delay = d;
	}
	
	//update the animation. -1 refers to a frame that does not change,
	//or a single image. Otherwise the delay time is used to rotate
	//through the sprite sheet frames as the animation is carried out
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
	
	//retrieve the current image of the image array
	public BufferedImage getImage() {
		return frames[currentFrame];
	}

}
