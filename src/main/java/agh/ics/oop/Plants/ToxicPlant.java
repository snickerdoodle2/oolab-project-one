package agh.ics.oop.Plants;

import agh.ics.oop.Maps.WorldMap;
import agh.ics.oop.Utility.Vector2D;

import java.util.Map;
import java.util.TreeMap;


public class ToxicPlant extends PlantGenerator{
    public ToxicPlant(WorldMap map) {
        super(map);
    }

    @Override
    protected Vector2D generatePreferred() {
        Map<Vector2D, Integer> deadAnimalsMap = map.getDeadAnimalsMap();
        TreeMap<Integer, Vector2D> deadAnimalsTree = new TreeMap<>((o1, o2) -> {
            int comp = Integer.compare(o1, o2);
            return comp != 0 ? comp : random.nextFloat() > .5 ? 0 : -1;
        });

        for (Map.Entry<Vector2D, Integer> set : deadAnimalsMap.entrySet()) {
            deadAnimalsTree.put(set.getValue(), set.getKey());
        }
        Map.Entry<Integer, Vector2D> potential = deadAnimalsTree.firstEntry();
        return potential == null ? generateDisPreferred() : potential.getValue();
    }

//    TODO: BETTER ALGORITHM
    @Override
    protected Vector2D generateDisPreferred() {
        Vector2D tmp = new Vector2D(random.nextInt(map.getWidth()), random.nextInt(map.getHeight()));
        while (map.plantAt(tmp)) {
            tmp = new Vector2D(random.nextInt(map.getWidth()), random.nextInt(map.getHeight()));
        }
        return tmp;
    }

    @Override
    public String getCellBgColor(Vector2D position) {
        return "#FAD6A5";
    }
}
