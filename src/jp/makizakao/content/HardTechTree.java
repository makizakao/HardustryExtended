package jp.makizakao.content;

import mindustry.content.Planets;
import mindustry.content.TechTree;

import static mindustry.content.TechTree.*;

public class HardTechTree {
    public static TechTree.TechNode content = null;
    public static void load() {
        TechNode root = nodeRoot("hardustry-ex", Planets.serpulo, () -> {
            node(HardItems.brassDust, () -> {
                node(HardItems.bronzeDust);
            });
        });
        root.planet = Planets.serpulo;
    }
}
