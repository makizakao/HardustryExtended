package jp.makizakao.content.recipe;

import arc.struct.Seq;
import jp.makizakao.content.HardItems;
import jp.makizakao.world.builder.RecipeBuilder;
import multicraft.Recipe;

public class BendingMachineRecipes {
    public static final Seq<Recipe> BENDING_MACHINE_TIER_1 = Seq.with(
            RecipeBuilder
                    .input().items(HardItems.brassIngot, 1).power(0.8f)
                    .output().items(HardItems.brassPlate, 1)
                    .craftTime(180f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.bronzeIngot, 1).power(0.8f)
                    .output().items(HardItems.bronzePlate, 1)
                    .craftTime(180f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.bronzePlate, 3).power(1.0f)
                    .output().items(HardItems.compressedBronzePlate, 1)
                    .craftTime(180f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.steelPlate, 3).power(1.0f)
                    .output().items(HardItems.compressedSteelPlate, 1)
                    .craftTime(180f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.goldIngot, 1).power(0.8f)
                    .output().items(HardItems.goldPlate, 1)
                    .craftTime(180f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.ironIngot, 1).power(0.8f)
                    .output().items(HardItems.ironPlate, 1)
                    .craftTime(180f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.steelIngot, 1).power(0.8f)
                    .output().items(HardItems.steelPlate, 1)
                    .craftTime(180f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.tinIngot, 1).power(0.8f)
                    .output().items(HardItems.tinPlate, 1)
                    .craftTime(180f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.tinRod, 1).power(0.8f)
                    .output().items(HardItems.tinRing, 1)
                    .craftTime(180f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.copperIngot, 1).power(0.8f)
                    .output().items(HardItems.copperWire, 2)
                    .craftTime(60f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.copperWire, 1).power(0.8f)
                    .output().items(HardItems.fineCopperWire, 4)
                    .craftTime(60f)
                    .build(),
            RecipeBuilder
                    .input().items(HardItems.tinIngot, 1).power(0.8f)
                    .output().items(HardItems.tinWire, 2)
                    .craftTime(60f)
                    .build()
    );
}
