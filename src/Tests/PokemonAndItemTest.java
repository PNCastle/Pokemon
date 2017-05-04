/* 
 * Authors: Angel Burr, Paul Castleberry, Issac Kim, Sohyun Kim
 * File: PokemonAndItemTest.java
 * Purpose: A series of tests to completely test the Pokemon and Items inheritance
 * hierarchies
 */

package Tests;

import static org.junit.Assert.*;

import org.junit.Test;

import Model.Item;
import Model.Pokemon;
import items.*;
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

public class PokemonAndItemTest {

	Item rock = new Rock();
	Item potion = new Potion();
	Item safariBall = new SafariBall();
	Item bait = new Bait();

	@Test
	public void testAllItemHierarchies() {
		assertEquals("Bait", bait.toString());
		assertEquals("Safari Ball", safariBall.toString());
		assertEquals("Potion", potion.toString());
		assertEquals("Rock", rock.toString());

		assertEquals("Rock", rock.getName());
		
		assertFalse(potion.isThrowable());
		assertTrue(rock.isThrowable());
		assertTrue(bait.isThrowable());
		assertTrue(safariBall.isThrowable());

		assertEquals(30, safariBall.amount());

		for (int i = 1; i <= 30; i++) {
			safariBall.useOne();
		}

		assertEquals(0, safariBall.amount());
		
		safariBall.addOne();
		assertEquals(1, safariBall.amount());
		safariBall.useOne();

		assertFalse(safariBall.useOne());
		assertTrue(rock.useOne());

		assertEquals(3, bait.hpModifier());
		assertEquals(-4, bait.getCatchModifier());
		assertEquals(0, safariBall.hpModifier());
		assertEquals(0, safariBall.getCatchModifier());
		assertEquals(15, potion.hpModifier());
		assertEquals(0, potion.getCatchModifier());
		assertEquals(-2, rock.hpModifier());
		assertEquals(5, rock.getCatchModifier());
	}

	@Test
	public void testAbra() {
		Pokemon testAbra = new Abra(63);

		assertEquals(testAbra.getHP(), 25);
		assertEquals(testAbra.getName(), "Abra");
		assertEquals(testAbra.getCatchRate(), 200);

		testAbra.useItem(rock);
		assertEquals(23, testAbra.getHP());

		testAbra.useItem(rock);
		testAbra.useItem(rock);
		testAbra.useItem(rock);

		System.out.println("Will Abra run 1? " + testAbra.pokemonRun());

		testAbra.useItem(rock);
		testAbra.useItem(rock);
		testAbra.useItem(rock);
		testAbra.useItem(rock);
		testAbra.useItem(rock);
		testAbra.useItem(rock);
		testAbra.useItem(rock);
		testAbra.useItem(rock);
		testAbra.useItem(rock);
		testAbra.useItem(rock);
		testAbra.useItem(rock);
		testAbra.useItem(rock);
		testAbra.useItem(rock);
		testAbra.useItem(rock);
		assertEquals(1, testAbra.getHP());

		assertEquals("pokePic/Abra.gif", testAbra.getPicFileName());
		assertEquals(
				"<html>If it decides to TELEPORT randomly, <br> it creates the illusion that it has created copies <br> of itself.</html>",
				testAbra.getInfo());
		
		assertEquals("Psychic", testAbra.getType());
		testAbra.willRun();

		testAbra.useItem(bait);
		System.out.println("Will Abra run 2? " + testAbra.pokemonRun());

		System.out.println(testAbra.toString());
		System.out.println(
				"Catch Probability: " + testAbra.getCatchProbability());
		System.out.println("Encounter Rate: " + testAbra.getEncounterRate());

		assertEquals(63, testAbra.getPokemonID());
	}

	@Test
	public void testDragonair() {
		Pokemon testDragonair = new Dragonair(148);

		assertEquals(testDragonair.getHP(), 61);
		assertEquals(testDragonair.getName(), "Dragonair");
		assertEquals(testDragonair.getCatchRate(), 45);
		assertEquals(148, testDragonair.getPokemonID());

		testDragonair.useItem(rock);
		testDragonair.useItem(rock);
		testDragonair.useItem(rock);
		testDragonair.useItem(rock);
		testDragonair.useItem(rock);
		testDragonair.useItem(rock);
		testDragonair.useItem(rock);
		testDragonair.useItem(rock);
		testDragonair.useItem(rock);
		testDragonair.useItem(rock);
		assertEquals(41, testDragonair.getHP());

		testDragonair.useItem(bait);
		assertEquals(44, testDragonair.getHP());

		testDragonair.useItem(potion);
		testDragonair.useItem(potion);
		testDragonair.useItem(potion);
		testDragonair.useItem(potion);
		assertEquals(61, testDragonair.getHP());

		System.out.println(testDragonair.toString());
		System.out.println(
				"Catch Probability: " + testDragonair.getCatchProbability());
		System.out
				.println("Encounter Rate: " + testDragonair.getEncounterRate());
	}

	@Test
	public void testStaryu() {
		Pokemon testStaryu = new Staryu(120);

		assertEquals(testStaryu.getHP(), 30);
		assertEquals(testStaryu.getName(), "Staryu");
		assertEquals(testStaryu.getCatchRate(), 225);
		assertEquals(120, testStaryu.getPokemonID());

		testStaryu.useItem(rock);
		testStaryu.useItem(rock);
		testStaryu.useItem(rock);
		testStaryu.useItem(rock);
		testStaryu.useItem(rock);
		testStaryu.useItem(rock);
		testStaryu.useItem(rock);
		testStaryu.useItem(rock);
		testStaryu.useItem(rock);
		assertEquals(12, testStaryu.getHP());

		testStaryu.useItem(safariBall);
		assertEquals(12, testStaryu.getHP());

		System.out.println(testStaryu.toString());
		System.out.println(
				"Catch Probability: " + testStaryu.getCatchProbability());
		System.out.println("Encounter Rate: " + testStaryu.getEncounterRate());
	}

	@Test
	public void testGrimer() {
		Pokemon testGrimer = new Grimer(88);

		assertEquals(testGrimer.getHP(), 80);
		assertEquals(testGrimer.getName(), "Grimer");
		assertEquals(testGrimer.getCatchRate(), 190);
		assertEquals(88, testGrimer.getPokemonID());

		testGrimer.useItem(rock);
		testGrimer.useItem(rock);
		testGrimer.useItem(rock);
		testGrimer.useItem(rock);
		testGrimer.useItem(rock);
		testGrimer.useItem(rock);
		testGrimer.useItem(rock);
		testGrimer.useItem(rock);
		testGrimer.useItem(rock);
		testGrimer.useItem(rock);
		assertEquals(60, testGrimer.getHP());

		testGrimer.useItem(potion);
		assertEquals(75, testGrimer.getHP());

		System.out.println(testGrimer.toString());
		System.out.println(
				"Catch Probability: " + testGrimer.getCatchProbability());
		System.out.println("Encounter Rate: " + testGrimer.getEncounterRate());
	}

	@Test
	public void testRapidash() {
		Pokemon testRapidash = new Rapidash(78);

		assertEquals(testRapidash.getHP(), 65);
		assertEquals(testRapidash.getName(), "Rapidash");
		assertEquals(testRapidash.getCatchRate(), 60);
		assertEquals(78, testRapidash.getPokemonID());

		testRapidash.useItem(rock);
		testRapidash.useItem(rock);
		testRapidash.useItem(rock);
		testRapidash.useItem(rock);
		testRapidash.useItem(rock);
		testRapidash.useItem(rock);
		testRapidash.useItem(rock);
		testRapidash.useItem(rock);
		testRapidash.useItem(rock);
		testRapidash.useItem(rock);
		assertEquals(45, testRapidash.getHP());

		testRapidash.useItem(potion);
		assertEquals(60, testRapidash.getHP());

		System.out.println(testRapidash.toString());
		System.out.println(
				"Catch Probability: " + testRapidash.getCatchProbability());
		System.out
				.println("Encounter Rate: " + testRapidash.getEncounterRate());
	}

	@Test
	public void testPikachu() {
		Pokemon testPikachu = new Pikachu(25);

		assertEquals(testPikachu.getHP(), 35);
		assertEquals(testPikachu.getName(), "Pikachu");
		assertEquals(testPikachu.getCatchRate(), 190);
		assertEquals(25, testPikachu.getPokemonID());

		testPikachu.useItem(rock);
		testPikachu.useItem(rock);
		testPikachu.useItem(rock);
		testPikachu.useItem(rock);
		testPikachu.useItem(rock);
		testPikachu.useItem(rock);
		testPikachu.useItem(rock);
		testPikachu.useItem(rock);
		testPikachu.useItem(rock);
		testPikachu.useItem(rock);
		assertEquals(15, testPikachu.getHP());

		testPikachu.useItem(safariBall);
		assertEquals(15, testPikachu.getHP());

		System.out.println(testPikachu.toString());
		System.out.println(
				"Catch Probability: " + testPikachu.getCatchProbability());
		System.out.println("Encounter Rate: " + testPikachu.getEncounterRate());
	}

	@Test
	public void testPidgey() {
		Pokemon testPidgey = new Pidgey(16);

		assertEquals(testPidgey.getHP(), 40);
		assertEquals(testPidgey.getName(), "Pidgey");
		assertEquals(testPidgey.getCatchRate(), 255);
		assertEquals(16, testPidgey.getPokemonID());

		testPidgey.useItem(rock);
		testPidgey.useItem(rock);
		testPidgey.useItem(rock);
		testPidgey.useItem(rock);
		testPidgey.useItem(rock);
		assertEquals(30, testPidgey.getHP());

		testPidgey.useItem(potion);
		assertEquals(40, testPidgey.getHP());

		System.out.println(testPidgey.toString());
		System.out.println(
				"Catch Probability: " + testPidgey.getCatchProbability());
		System.out.println("Encounter Rate: " + testPidgey.getEncounterRate());
	}

	@Test
	public void testHaunter() {
		Pokemon testHaunter = new Haunter(93);

		assertEquals(testHaunter.getHP(), 45);
		assertEquals(testHaunter.getName(), "Haunter");
		assertEquals(testHaunter.getCatchRate(), 90);
		assertEquals(93, testHaunter.getPokemonID());

		testHaunter.useItem(rock);
		testHaunter.useItem(rock);
		testHaunter.useItem(rock);
		testHaunter.useItem(rock);
		testHaunter.useItem(rock);
		testHaunter.useItem(rock);
		testHaunter.useItem(rock);
		testHaunter.useItem(rock);
		testHaunter.useItem(rock);
		testHaunter.useItem(rock);
		testHaunter.useItem(rock);
		testHaunter.useItem(rock);
		testHaunter.useItem(rock);
		testHaunter.useItem(rock);
		testHaunter.useItem(rock);
		assertEquals(15, testHaunter.getHP());

		testHaunter.useItem(potion);
		assertEquals(30, testHaunter.getHP());

		System.out.println(testHaunter.toString());
		System.out.println(
				"Catch Probability: " + testHaunter.getCatchProbability());
		System.out.println("Encounter Rate: " + testHaunter.getEncounterRate());
	}

	@Test
	public void testGraveler() {
		Pokemon testGraveler = new Graveler(75);

		assertEquals(testGraveler.getHP(), 55);
		assertEquals(testGraveler.getName(), "Graveler");
		assertEquals(testGraveler.getCatchRate(), 120);
		assertEquals(75, testGraveler.getPokemonID());

		testGraveler.useItem(rock);
		testGraveler.useItem(rock);
		testGraveler.useItem(rock);
		testGraveler.useItem(rock);
		testGraveler.useItem(rock);
		testGraveler.useItem(rock);
		testGraveler.useItem(rock);
		testGraveler.useItem(rock);
		testGraveler.useItem(rock);
		testGraveler.useItem(rock);
		testGraveler.useItem(rock);
		testGraveler.useItem(rock);
		testGraveler.useItem(rock);
		testGraveler.useItem(rock);
		testGraveler.useItem(rock);
		assertEquals(25, testGraveler.getHP());

		testGraveler.useItem(potion);
		testGraveler.useItem(potion);
		assertEquals(55, testGraveler.getHP());

		System.out.println(testGraveler.toString());
		System.out.println(
				"Catch Probability: " + testGraveler.getCatchProbability());
		System.out
				.println("Encounter Rate: " + testGraveler.getEncounterRate());
	}

	@Test
	public void testDrowzee() {
		Pokemon testDrowzee = new Drowzee(96);

		assertEquals(testDrowzee.getHP(), 60);
		assertEquals(testDrowzee.getName(), "Drowzee");
		assertEquals(testDrowzee.getCatchRate(), 190);
		assertEquals(96, testDrowzee.getPokemonID());

		testDrowzee.useItem(rock);
		testDrowzee.useItem(rock);
		testDrowzee.useItem(rock);
		testDrowzee.useItem(rock);
		testDrowzee.useItem(rock);
		assertEquals(50, testDrowzee.getHP());

		testDrowzee.useItem(potion);
		assertEquals(60, testDrowzee.getHP());

		System.out.println(testDrowzee.toString());
		System.out.println(
				"Catch Probability: " + testDrowzee.getCatchProbability());
		System.out.println("Encounter Rate: " + testDrowzee.getEncounterRate());
	}

}
