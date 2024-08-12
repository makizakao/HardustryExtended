package jp.makizakao.hardustryex.content.recipe;

import arc.struct.Seq;
import jp.makizakao.hardustryex.content.HardItems;
import jp.makizakao.hardustryex.builder.RecipeBuilder;
import multicraft.Recipe;

public class HardFactoryRecipe {
    public static final Seq<Recipe> BRONZE_FACTORY_TIER_1 = Seq.with(
            RecipeBuilder
                    .input().items(HardItems.portableBattery, 1).power(0.4f)
                    .output().items(HardItems.chargedPortableBattery, 1)
                    .craftTime(1200f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.bronzePlate, 40, HardItems.brassPlate, 20).power(0.4f)
                    .output().items(HardItems.bronzeHull, 1)
                    .craftTime(1000f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.bronzeIngot, 10, HardItems.bronzePlate, 5).power(0.4f)
                    .output().items(HardItems.bronzePiston, 1)
                    .craftTime(600f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.bronzePlate, 15).power(0.4f)
                    .output().items(HardItems.smallBronzePipe, 1)
                    .craftTime(400f)
                    .build()
    );
    public static final Seq<Recipe> BRONZE_FACTORY_TIER_2 = Seq.with(
            RecipeBuilder
                    .input().items(HardItems.portableBattery, 1).power(0.6f)
                    .output().items(HardItems.chargedPortableBattery, 1)
                    .craftTime(900f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.bronzePlate, 40, HardItems.brassPlate, 20).power(0.6f)
                    .output().items(HardItems.bronzeHull, 1)
                    .craftTime(800f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.bronzeIngot, 10, HardItems.bronzePlate, 5).power(0.6f)
                    .output().items(HardItems.bronzePiston, 1)
                    .craftTime(400f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.bronzePlate, 15).power(0.6f)
                    .output().items(HardItems.smallBronzePipe, 1)
                    .craftTime(300f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.tinIngot, 5, HardItems.leadIngot, 10, HardItems.copperWire, 2)
                    .power(0.6f)
                    .output().items(HardItems.portableBattery, 1)
                    .craftTime(300f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.bronzeIngot, 100).power(0.6f)
                    .output().items(HardItems.bronzePiston, 1)
                    .craftTime(1200f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.cokeOvenBrick, 20).power(0.6f)
                    .output().items(HardItems.cokeOvenBlock, 1)
                    .craftTime(300f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.ironPlate, 40, HardItems.copperWire, 10).power(0.6f)
                    .output().items(HardItems.cokeOvenController, 1)
                    .craftTime(800f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.fireBrick, 20).power(0.6f)
                    .output().items(HardItems.primitiveBlastFurnaceBlock, 1)
                    .craftTime(300f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.ironPlate, 10, HardItems.ironScrew, 20, HardItems.ironRod, 30)
                    .power(0.6f)
                    .output().items(HardItems.primitiveBlastFurnaceController, 1)
                    .craftTime(1200f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.bronzeIngot, 10, HardItems.bronzePlate, 5).power(0.6f)
                    .output().items(HardItems.bronzePiston, 1)
                    .craftTime(400f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.ironPlate, 10, HardItems.ironScrew, 20, HardItems.ironRod, 30)
                    .power(0.6f)
                    .output().items(HardItems.bronzePiston, 1)
                    .craftTime(800f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.ironPlate, 10, HardItems.ironScrew, 20, HardItems.ironRod, 30)
                    .power(0.6f)
                    .output().items(HardItems.bronzePiston, 1)
                    .craftTime(800f)
                    .build()
    );
}
