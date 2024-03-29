package Tests;

import static org.junit.Assert.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.junit.Test;

import Model.Animation;
import Model.Item;
import Model.Map;
import Model.Pokemon;
import Model.Tile;
import Model.Trainer;

public class AnimationAndTrainerTest {
	
	private String mapFile = "mapTwo.txt";
	private String imageFile = "resizedTiles2.png";
	private Object[] toLoad = new Object[12];

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
		toLoad[9] = new ArrayList<Item>();
		toLoad[10] = new ArrayList<Pokemon>();
		toLoad[11] = theMap.getMap();
	
		ashe.getCurrentPokemon();
		ashe.getItemsList();
		ashe.getPokedex();
		ashe.ran();
		assertEquals(0, ashe.getStepCount());
		assertEquals(30, ashe.safariBallCount());
		
		ashe.throwSafariBall();
		assertEquals(29, ashe.safariBallCount());
		
		assertFalse(ashe.isOnBike());
		ashe.dismountBike();
		ashe.mountBike();
		
		ashe.getItemsList().get(3).addOne();
		ashe.mountBike();
		ashe.dismountBike();
		
		ashe.getCurrentPokemonID();
		
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
		ketchum.setHasBikeNItem(true);
		ketchum.update();
		
		ketchum.setUp(true);
		ketchum.setDy(1);
		ketchum.update();
		
		ashe.toSerialize();
		
		ashe.setCurrentPokemon(.93);
		ashe.setCurrentPokemon(.99);
		ashe.setCurrentPokemon(.9);
		ashe.setHasBikeNItem(true);
		ashe.update();
		
		
		BufferedImage image = null;
		try {
			image = ImageIO.read(new File("trainerOneTrans.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Graphics2D g = (Graphics2D) image.getGraphics();
		theMap.draw(g);
		
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
	
	@Test
	public void test2(){
		Map theMap = new Map(mapFile, 50);
		theMap.loadTiles(imageFile);
		Trainer ashe = new Trainer(theMap);
		ashe.getMap().removeItem(48, 23);
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = (Graphics2D) image.getGraphics();
		ashe.draw(g);
	}
	
	@Test
	public void test3(){
		Map theMap = new Map(mapFile, 50);
		theMap.loadTiles(imageFile);
		Trainer ashe = new Trainer(theMap);
		ashe.getPokedex().addAll(ashe.getRare());
		ashe.getPokedex().addAll(ashe.getCommon());
		ashe.getPokedex().addAll(ashe.getUncommon());
		
	}

}
