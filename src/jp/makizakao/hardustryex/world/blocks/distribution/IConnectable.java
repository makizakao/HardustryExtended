package jp.makizakao.hardustryex.world.blocks.distribution;

import mindustry.world.Block;

public interface IConnectable {
    default boolean canConnect(Block other) {
        return other instanceof HardConveyor || other instanceof HardJunction || other instanceof HardOverflowGate
                || other instanceof HardRouter || other instanceof HardSorter;
    }
}
