package Tests;

import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.Test;

import Model.Animation;
import Model.Map;
import Model.Tile;
import Model.Trainer;

public class AnimationTest {
	
	private String mapFile = "mapTwo.txt";
	private String imageFile = "resizedTiles.png";
	private Object[] toLoad = new Object[9];

	@Test
	public void test() {
		Map theMap = new Map(mapFile, 50);
		theMap.loadTiles(imageFile);
		Trainer ashe = new Trainer(theMap);
		
		toLoad[0] = 300.0;
		toLoad[1] = 300.0;
		toLoad[2] = 0.0;
		toLoad[3] = 0.0;
		toLoad[4] = 100;
		toLoad[5] = false;
		toLoad[6] = false;
		toLoad[7] = false;
		toLoad[8] = true;
	
		
		ashe.setUp(true);
		ashe.update();
		ashe.update();
		ashe.update();
		ashe.setUp(false);
		ashe.setDown(true);
		ashe.update();
		ashe.setDown(false);
		ashe.setLeft(true);
		ashe.update();
		ashe.setLeft(false);
		ashe.setRight(true);
		ashe.update();
		ashe.setRight(false);		
		
		Trainer ketchum = new Trainer(theMap, toLoad);
		ketchum.setDx(-1);
		ketchum.update();
		ketchum.setDx(1);
		ketchum.update();
		ketchum.setDy(-1);
		ketchum.update();
		ketchum.setDy(1);
		ketchum.update();
		
		ashe.toSerialize();
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("trainerOneTrans.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Graphics2D g = (Graphics2D) image.getGraphics();
		theMap.draw(g);
		Tile testTile = new Tile(image, false, false, false, false);
		testTile.getImage();
		
		//theMap.toString(); 
		theMap.getColTileIndex(5);
		theMap.getRowTileIndex(5);
		theMap.getTile(5, 5);
		theMap.getTileSize();
		theMap.getX();
		theMap.getY();
		
		BufferedImage[] standingDown = new BufferedImage[1];
		
		Animation animation = new Animation();
		animation.setFrames(standingDown);
		animation.getImage();
	}

}
