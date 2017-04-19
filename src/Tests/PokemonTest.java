package Tests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import Model.Pokemon;

import pokemon.Abra;
import pokemon.Dragonair;
import pokemon.Drowzee;
import pokemon.Graveler;
import pokemon.Grimer;
import pokemon.Haunter;
import pokemon.Pidgey;
import pokemon.Pikachu;
import pokemon.Rapidash;
import pokemon.Staryu;

public class PokemonTest {
	
	@Test
	public void testAbra() {
		Pokemon testAbra = new Abra(63);
		
		assertEquals(testAbra.getHP(), 25);
		assertEquals(testAbra.getName(), "Abra");
		assertEquals(testAbra.getCatchRate(), 200);
		
		System.out.println(testAbra.toString());
	}
	
	@Test
	public void testDragonair() {
		Pokemon testDragonair = new Dragonair(148);
		
		assertEquals(testDragonair.getHP(), 61);
		assertEquals(testDragonair.getName(), "Dragonair");
		assertEquals(testDragonair.getCatchRate(), 45);
		
		System.out.println(testDragonair.toString());
	}

	@Test
	public void testStaryu() {
		Pokemon testStaryu = new Staryu(120);
		
		assertEquals(testStaryu.getHP(), 30);
		assertEquals(testStaryu.getName(), "Staryu");
		assertEquals(testStaryu.getCatchRate(), 225);
		
		System.out.println(testStaryu.toString());
	}

	@Test
	public void testGrimer() {
		Pokemon testGrimer = new Grimer(88);
		
		assertEquals(testGrimer.getHP(), 80);
		assertEquals(testGrimer.getName(), "Grimer");
		assertEquals(testGrimer.getCatchRate(), 190);
		
		System.out.println(testGrimer.toString());
	}

	@Test
	public void testRapidash() {
		Pokemon testRapidash = new Rapidash(78);
		
		assertEquals(testRapidash.getHP(), 65);
		assertEquals(testRapidash.getName(), "Rapidash");
		assertEquals(testRapidash.getCatchRate(), 60);
		
		System.out.println(testRapidash.toString());
	}

	@Test
	public void testPikachu() {
		Pokemon testPikachu = new Pikachu(25);
		
		assertEquals(testPikachu.getHP(), 35);
		assertEquals(testPikachu.getName(), "Pikachu");
		assertEquals(testPikachu.getCatchRate(), 190);
		
		System.out.println(testPikachu.toString());
	}

	@Test
	public void testPidgey() {
		Pokemon testPidgey = new Pidgey(16);
		
		assertEquals(testPidgey.getHP(), 40);
		assertEquals(testPidgey.getName(), "Pidgey");
		assertEquals(testPidgey.getCatchRate(), 225);
		
		System.out.println(testPidgey.toString());
	}

	@Test
	public void testHaunter() {
		Pokemon testHaunter = new Haunter(93);
		
		assertEquals(testHaunter.getHP(), 45);
		assertEquals(testHaunter.getName(), "Haunter");
		assertEquals(testHaunter.getCatchRate(), 90);
		
		System.out.println(testHaunter.toString());
	}

	@Test
	public void testGraveler() {
		Pokemon testGraveler = new Graveler(75);
		
		assertEquals(testGraveler.getHP(), 55);
		assertEquals(testGraveler.getName(), "Graveler");
		assertEquals(testGraveler.getCatchRate(), 120);
		
		System.out.println(testGraveler.toString());
	}

	@Test
	public void testDrowzee() {
		Pokemon testDrowzee = new Drowzee(96);
		
		assertEquals(testDrowzee.getHP(), 60);
		assertEquals(testDrowzee.getName(), "Drowzee");
		assertEquals(testDrowzee.getCatchRate(), 190);
		
		System.out.println(testDrowzee.toString());
	}


}
