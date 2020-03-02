package tests;

import java.util.Random;
import org.junit.*;
import static org.junit.Assert.*;

public class TestGameFactory {
    GameImpl game;
    @Before
    public void setUp(){
        game = new GameImpl(new SemiCivFactory());
    }

    // Test SemiCiv Factory

    @Test
public void shouldBeBetaAgingStrat(){
    AgingStrategy strat = game.getGameFactory().getAgingStrategy();
    if(!(strat instanceof BetaAgingStrategy)){
        fail();
    }
}
@Test
public void shouldBeGammaActionStrat(){
    ActionStrategy strat = game.getGameFactory().getActionStrategy();
    if(!(strat instanceof GammaActionStrategy)){
        fail();
    }
}
@Test
public void shouldBeDeltaLayout(){
    BoardFactory factory = game.getGameFactory().getBoardFactory();
    if(!(factory instanceof DeltaBoardFactory)){
        fail();
    }
}
@Test
public void shouldBeEpsilonWinningStrat(){
    WinningStrategy strat = game.getGameFactory().getWinningStrategy();
    if(!(strat instanceof EpsilonWinningStrategy)){
        fail();
    }
}
@Test
public void shouldBeEpsilonAttackStrat(){
    AttackStrategy strat = game.getGameFactory().getAttackStrategy();
    if(!(strat instanceof EpsilonAttackStrategy)){
        fail();
    }
}
@Test
public void shouldBeEtaProductionStrat(){
    ProductionStrategy strat = game.getGameFactory().getProductionStrategy();
    if(!(strat instanceof EtaProductionStrategy)){
        fail();
    }
}

    @Test
    public void probabilityTester(){
        Random r = new Random();
        int x = 1000;
        while(x != 5){
            System.out.println("NOPE!");
        }
        System.out.println("-------------------YUP!--------------");
    }
}