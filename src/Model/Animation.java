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
		if (currentFrame >= frames.length-1) currentFrame = 0;
	}
	
	public void setDelay(long d) {
		delay = d;
	}
	
	public void update() {
		
		long elapsed = (System.nanoTime() - startTime) / 1000000;
		if(elapsed > delay) {
			currentFrame++;
			startTime = System.nanoTime();
		}
		if(currentFrame == frames.length-1) {
			currentFrame = 0;
		}
	}
	
	public BufferedImage getImage() {
		return frames[currentFrame];
	}
	
}