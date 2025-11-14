package jp.makizakao.hardustryex.content.recipe;

import arc.struct.Seq;
import jp.makizakao.hardustryex.content.HardItems;
import jp.makizakao.hardustryex.type.recipe.HardRecipe;
import multicraft.Recipe;

public class BendingMachineRecipes {
    public static final Seq<Recipe> BENDING_MACHINE_TIER_1 = Seq.with(
            HardRecipe.of()
                    .input().items(HardItems.brassIngot, 1).power(0.8f).complete()
                    .output().items(HardItems.brassPlate, 1).complete()
                    .craftTime(180f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.brassIngot, 1).power(0.8f).complete()
                    .output().items(HardItems.brassPlate, 1).complete()
                    .craftTime(180f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.bronzeIngot, 1).power(0.8f).complete()
                    .output().items(HardItems.bronzePlate, 1).complete()
                    .craftTime(180f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.bronzePlate, 3).power(1.0f).complete()
                    .output().items(HardItems.compressedBronzePlate, 1).complete()
                    .craftTime(180f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.steelPlate, 3).power(1.0f).complete()
                    .output().items(HardItems.compressedSteelPlate, 1).complete()
                    .craftTime(180f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.goldIngot, 1).power(0.8f).complete()
                    .output().items(HardItems.goldPlate, 1).complete()
                    .craftTime(180f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.ironIngot, 1).power(0.8f).complete()
                    .output().items(HardItems.ironPlate, 1).complete()
                    .craftTime(180f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.steelIngot, 1).power(0.8f).complete()
                    .output().items(HardItems.steelPlate, 1).complete()
                    .craftTime(180f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.tinIngot, 1).power(0.8f).complete()
                    .output().items(HardItems.tinPlate, 1).complete()
                    .craftTime(180f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.tinRod, 1).power(0.8f).complete()
                    .output().items(HardItems.tinRing, 1).complete()
                    .craftTime(180f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.copperIngot, 1).power(0.8f).complete()
                    .output().items(HardItems.copperWire, 2).complete()
                    .craftTime(60f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.copperWire, 1).power(0.8f).complete()
                    .output().items(HardItems.fineCopperWire, 4).complete()
                    .craftTime(60f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.tinIngot, 1).power(0.8f).complete()
                    .output().items(HardItems.tinWire, 2).complete()
                    .craftTime(60f)
                    .build()
    );
}
