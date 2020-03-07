package alphaciv.domain;

import org.junit.*;
import static org.junit.Assert.*;

public class TestTurns {

	private Game game;
	private AlphaAgingStrategy alphaAgingStrategy;
	private BetaAgingStrategy betaAgingStrategy;

	@Before
	public void before() {
		game = new GameImpl();
		alphaAgingStrategy = new AlphaAgingStrategy();
		betaAgingStrategy = new BetaAgingStrategy();
	}

	
	@Test
	public void shouldBeBluePlayersTurn() {
		game.endOfTurn();
		assertEquals(Player.BLUE,game.getPlayerInTurn());
	}
	
	@Test
	public void shouldBeRedPlayersTurnAfterBlue() {
		game.endOfTurn();
		game.endOfTurn();
		assertEquals(Player.RED, game.getPlayerInTurn());
	}
	
	//integration tests for aging
	@Test
	public void shouldStartAtYear4000BC() {
		assertEquals(-4000, game.getAge());
	}
	
	@Test
	public void shouldIncrementBy100Years() {
		for(int i = 0; i < 1000; i+=100) {
			game.endOfTurn();
			game.endOfTurn();
			assertEquals(-3900+i,game.getAge());
		}	
	}
		
	//AlphaCiv aging strategy calculates age properly
	@Test
	public void alphaCivAgingStrategyShouldIncrementBy100Years() {
		assertEquals(-2900, alphaAgingStrategy.ageWorld(-3000));
	}

	//BetaCiv aging strategy calculates age properly
	@Test
	public void betaCivAgingStrategyShouldIncrementBy100YearsBetween4000And100BC() {
		int age = -4000;
		while(age<-100){
			assertEquals(age+100, betaAgingStrategy.ageWorld(age));
			age += 100;
		}
	}
	
	@Test
	public void betaCivAgingStrategyShouldStepAroundBirthOfChrist() {
		assertEquals(-1, betaAgingStrategy.ageWorld(-100));
		assertEquals(1, betaAgingStrategy.ageWorld(-1));
		assertEquals(50, betaAgingStrategy.ageWorld(1));
	}
	
	@Test
	public void betaCivAgingStrategyShouldIncrementBy50YearsBetween50And1750AD() {
		int age = 50;
		while(age<1750){
			assertEquals(age+50, betaAgingStrategy.ageWorld(age));
			age += 50;
		}
	}
	
	@Test
	public void betaCivAgingStrategyShouldIncrementBy25YearsBetween1750And1900AD() {
		int age = 1750;
		while(age<1900){
			assertEquals(age+25, betaAgingStrategy.ageWorld(age));
			age += 25;
		}
	}
		
	@Test
	public void betaCivAgingStrategyShouldIncrementBy5YearsBetween1900And1970AD() {
		int age = 1900;
		while(age<1970){
			assertEquals(age+5, betaAgingStrategy.ageWorld(age));
			age += 5;
		}
	}
	
	@Test
	public void betaCivAgingStrategyShouldIncrementBy1YearAfter1970AD() {
		assertEquals(1971, betaAgingStrategy.ageWorld(1970));
		assertEquals(3001, betaAgingStrategy.ageWorld(3000));
	}	
}
