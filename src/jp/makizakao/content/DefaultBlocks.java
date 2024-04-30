package jp.makizakao.content;

import mindustry.content.Blocks;
import mindustry.type.Category;

import static mindustry.type.ItemStack.with;

public class DefaultBlocks {
    public static void load() {
        // junction
        Blocks.junction.requirements(Category.distribution, with(HardItems.copperIngot, 2, HardItems.leadIngot, 2));
        Blocks.junction.consumesPower = true;
        Blocks.junction.consumePower(0.04f);
        // router
        Blocks.router.requirements(Category.distribution, with(HardItems.copperIngot, 3, HardItems.leadIngot, 3));
        Blocks.router.consumesPower = true;
        Blocks.router.consumePower(0.06f);
        // sorter
        Blocks.sorter.requirements(Category.distribution, with(HardItems.copperIngot, 2, HardItems.leadIngot, 2));
        Blocks.sorter.consumesPower = true;
        Blocks.sorter.consumePower(0.04f);
        // inverted sorter
        Blocks.invertedSorter.requirements(Category.distribution, with(HardItems.copperIngot, 2, HardItems.leadIngot, 2));
        Blocks.invertedSorter.consumesPower = true;
        Blocks.invertedSorter.consumePower(0.04f);
        // overflow gate
        Blocks.overflowGate.requirements(Category.distribution, with(HardItems.copperIngot, 4, HardItems.leadIngot, 2));
        Blocks.overflowGate.consumesPower = true;
        Blocks.overflowGate.consumePower(0.04f);
        // underflow gate
        Blocks.underflowGate.requirements(Category.distribution, with(HardItems.copperIngot, 4, HardItems.leadIngot, 2));
        Blocks.underflowGate.consumesPower = true;
        Blocks.underflowGate.consumePower(0.04f);
        // unloader
        Blocks.unloader.requirements(Category.effect, with(HardItems.copperIngot, 10, HardItems.leadIngot, 20));
        Blocks.unloader.consumesPower = true;
        Blocks.unloader.consumePower(0.1f);
    }
}
