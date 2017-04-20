package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Map;
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
		
		//theMap.toString(); 
		theMap.getColTileIndex(5);
		theMap.getRowTileIndex(5);
		theMap.getTile(5, 5);
		theMap.getTileSize();
		theMap.getX();
		theMap.getY();
		
	}

}
