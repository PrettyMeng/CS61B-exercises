package creatures;
import creatures.Clorus;
import creatures.Plip;
import huglife.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;

public class TestClorus {
    @Test
    public void testReplicate() {
        // TODO
        Clorus c = new Clorus(2 );
        Clorus offspring = c.replicate();
        assertNotEquals(c, offspring);
    }

    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus p = new Clorus(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Impassible());
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.TOP);

        assertEquals(expected, actual);

        p = new Clorus(1.2);
        HashMap<Direction, Occupant> wizPlip = new HashMap<Direction, Occupant>();
        wizPlip.put(Direction.TOP, new Plip());
        wizPlip.put(Direction.BOTTOM, new Empty());
        wizPlip.put(Direction.LEFT, new Empty());
        wizPlip.put(Direction.RIGHT, new Empty());

        actual = p.chooseAction(wizPlip);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);

        assertEquals(expected, actual);


        // Energy >= 1; replicate towards an empty space.
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> allEmpty = new HashMap<Direction, Occupant>();
        allEmpty.put(Direction.TOP, new Empty());
        allEmpty.put(Direction.BOTTOM, new Empty());
        allEmpty.put(Direction.LEFT, new Empty());
        allEmpty.put(Direction.RIGHT, new Empty());

        actual = p.chooseAction(allEmpty);
        Action unexpected = new Action(Action.ActionType.STAY);

        assertNotEquals(unexpected, actual);


        // Energy < 1; stay.
//        p = new Clorus(.99);
//
//        actual = p.chooseAction(allEmpty);
//        expected = new Action(Action.ActionType.STAY);
//
//        assertEquals(expected, actual);


        // Energy < 1; stay.
//        p = new Clorus(.99);
//
//        actual = p.chooseAction(topEmpty);
//        expected = new Action(Action.ActionType.MOVE);
//
//        assertEquals(expected, actual);


    }
}
