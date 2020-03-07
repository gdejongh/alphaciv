package alphaciv.domain;

import java.util.*;
import org.junit.*;
import static org.junit.Assert.*;

public class TestGameFactory {
    GameImpl game;

    @Before
    public void setUp() {
        game = new GameImpl(new SemiCivFactory());
    }

    // Test SemiCiv Factory

    @Test
    public void shouldBeBetaAgingStrat() {
        AgingStrategy strat = game.getGameFactory().getAgingStrategy();
        if (!(strat instanceof BetaAgingStrategy)) {
            fail();
        }
    }

    @Test
    public void shouldBeGammaActionStrat() {
        ActionStrategy strat = game.getGameFactory().getActionStrategy();
        if (!(strat instanceof GammaActionStrategy)) {
            fail();
        }
    }

    @Test
    public void shouldBeDeltaLayout() {
        BoardFactory factory = game.getGameFactory().getBoardFactory();
        if (!(factory instanceof DeltaBoardFactory)) {
            fail();
        }
    }

    @Test
    public void shouldBeEpsilonWinningStrat() {
        WinningStrategy strat = game.getGameFactory().getWinningStrategy();
        if (!(strat instanceof EpsilonWinningStrategy)) {
            fail();
        }
    }

    @Test
    public void shouldBeEpsilonAttackStrat() {
        AttackStrategy strat = game.getGameFactory().getAttackStrategy();
        if (!(strat instanceof EpsilonAttackStrategy)) {
            fail();
        }
    }

    @Test
    public void shouldBeEtaProductionStrat() {
        ProductionStrategy strat = game.getGameFactory().getProductionStrategy();
        if (!(strat instanceof EtaProductionStrategy)) {
            fail();
        }
    }

    // @Test
    // public void probabilityTester() {
    //     int[] list = new int[1000];
    //     for (int index = 0; index < 1000; index++) {
    //         Random r = new Random();
    //         int counter = 0;
    //         while ((r.nextInt(310)) != 5) {
    //             counter++;
    //         }
    //         list[index] = counter;
    //     }
    //     int sum = 0;
    //     for (int i = 0; i < list.length; i++) {
    //         sum += list[i];
    //     }
    //     int average = sum/list.length;
    //     System.out.println("-----------------------------------------------------------------------");
    //     System.out.println("-----------------------------------------------------------------------");
    //     System.out.println("-----------------------------------------------------------------------");
    //     System.out.println("-----------------------------------------------------------------------");
    //     System.out.println("-----------------------------------------------------------------------");
    //     System.out.println("-----------------------------------------------------------------------");
    //     System.out.println("-----------------------------------------------------------------------");
    //     System.out.println(average);
    // }
}