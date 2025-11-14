package jp.makizakao.hardustryex.content.recipe;

import arc.struct.Seq;
import jp.makizakao.hardustryex.content.HardItems;
import jp.makizakao.hardustryex.type.recipe.HardRecipe;
import multicraft.Recipe;

public class HardFactoryRecipe {
    public static final Seq<Recipe> BRONZE_FACTORY_TIER_1 = Seq.with(
            HardRecipe.of()
                    .input().items(HardItems.portableBattery, 1).power(0.4f).complete()
                    .output().items(HardItems.chargedPortableBattery, 1).complete()
                    .craftTime(1200f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.bronzePlate, 40, HardItems.brassPlate, 20).power(0.4f).complete()
                    .output().items(HardItems.bronzeHull, 1).complete()
                    .craftTime(1000f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.bronzeIngot, 10, HardItems.bronzePlate, 5).power(0.4f).complete()
                    .output().items(HardItems.bronzePiston, 1).complete()
                    .craftTime(600f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.bronzePlate, 15).power(0.4f).complete()
                    .output().items(HardItems.smallBronzePipe, 1).complete()
                    .craftTime(400f)
                    .build()
    );
    public static final Seq<Recipe> BRONZE_FACTORY_TIER_2 = Seq.with(
            HardRecipe.of()
                    .input().items(HardItems.portableBattery, 1).power(0.6f).complete()
                    .output().items(HardItems.chargedPortableBattery, 1).complete()
                    .craftTime(900f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.bronzePlate, 40, HardItems.brassPlate, 20).power(0.6f).complete()
                    .output().items(HardItems.bronzeHull, 1).complete()
                    .craftTime(800f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.bronzeIngot, 10, HardItems.bronzePlate, 5).power(0.6f).complete()
                    .output().items(HardItems.bronzePiston, 1).complete()
                    .craftTime(400f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.bronzePlate, 15).power(0.6f).complete()
                    .output().items(HardItems.smallBronzePipe, 1).complete()
                    .craftTime(300f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.tinIngot, 5, HardItems.leadIngot, 10, HardItems.copperWire, 2)
                    .power(0.6f).complete()
                    .output().items(HardItems.portableBattery, 1).complete()
                    .craftTime(300f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.bronzeIngot, 100).power(0.6f).complete()
                    .output().items(HardItems.bronzePiston, 1).complete()
                    .craftTime(1200f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.cokeOvenBrick, 20).power(0.6f).complete()
                    .output().items(HardItems.cokeOvenBlock, 1).complete()
                    .craftTime(300f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.ironPlate, 40, HardItems.copperWire, 10).power(0.6f).complete()
                    .output().items(HardItems.cokeOvenController, 1).complete()
                    .craftTime(800f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.fireBrick, 20).power(0.6f).complete()
                    .output().items(HardItems.primitiveBlastFurnaceBlock, 1).complete()
                    .craftTime(300f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.ironPlate, 10, HardItems.ironScrew, 20, HardItems.ironRod, 30)
                    .power(0.6f).complete()
                    .output().items(HardItems.primitiveBlastFurnaceController, 1).complete()
                    .craftTime(1200f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.bronzeIngot, 10, HardItems.bronzePlate, 5).power(0.6f).complete()
                    .output().items(HardItems.bronzePiston, 1).complete()
                    .craftTime(400f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.ironPlate, 10, HardItems.ironScrew, 20, HardItems.ironRod, 30)
                    .power(0.6f).complete()
                    .output().items(HardItems.bronzePiston, 1).complete()
                    .craftTime(800f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.ironPlate, 10, HardItems.ironScrew, 20, HardItems.ironRod, 30)
                    .power(0.6f).complete()
                    .output().items(HardItems.bronzePiston, 1).complete()
                    .craftTime(800f)
                    .build()
    );
}
