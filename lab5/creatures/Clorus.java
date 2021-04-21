package creatures;

import huglife.*;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     * creates a plip with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    public Color color() {
        return color(r, g, b);
    }

    public void attack(Creature c) {
        energy += c.energy();
    }

    public void move() {
        energy -= 0.03;
        if (energy < 0) {
            energy = 0;
        }
    }


    /**
     * Plips gain 0.2 energy when staying due to photosynthesis.
     */
    public void stay() {
        energy -= 0.01;
        if (energy < 0) {
            energy = 0;
        }
    }

    public Clorus replicate() {
        Clorus offspring = new Clorus(energy / 2);
        energy = energy / 2;
        return offspring;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            if (entry.getValue().name().equals("empty")) {
                emptyNeighbors.add(entry.getKey());
            }
            if (entry.getValue().name().equals("plip")) {
                plipNeighbors.add(entry.getKey());
            }
        }

        if (emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else if (plipNeighbors.size() > 0) {
            Direction attackDirection = HugLifeUtils.randomEntry(plipNeighbors);
            return new Action(Action.ActionType.ATTACK, attackDirection);
        } else if (energy >= 1) {
            Direction replicateDirection = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.REPLICATE, replicateDirection);
        } else {
            Direction moveDirection = HugLifeUtils.randomEntry(emptyNeighbors);
            return new Action(Action.ActionType.MOVE, moveDirection);
        }

//        return new Action(Action.ActionType.STAY);

    }
}