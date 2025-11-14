package jp.makizakao.hardustryex.content.recipe;

import arc.struct.Seq;
import jp.makizakao.hardustryex.content.HardItems;
import jp.makizakao.hardustryex.type.recipe.HardRecipe;
import mindustry.content.Items;
import multicraft.Recipe;

public class PressRecipes {
    public static final Seq<Recipe> BRONZE_PRESS_TIER_1 = Seq.with(
            HardRecipe.of()
                    .input().items(HardItems.bronzeIngot, 3).power(0.4f).complete()
                    .output().items(HardItems.bronzePlate, 2).complete()
                    .craftTime(600f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.brassIngot, 3).power(0.4f).complete()
                    .output().items(HardItems.brassPlate, 2).complete()
                    .craftTime(600f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.tinIngot, 3).power(0.4f).complete()
                    .output().items(HardItems.tinPlate, 2).complete()
                    .craftTime(600f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.copperIngot, 1).power(0.4f).complete()
                    .output().items(HardItems.copperWire, 2).complete()
                    .craftTime(300f)
                    .build()
    );
    public static final Seq<Recipe> BRONZE_PRESS_TIER_2 = Seq.with(
            HardRecipe.of()
                    .input().items(HardItems.bronzeIngot, 3).power(0.6f).complete()
                    .output().items(HardItems.bronzePlate, 2).complete()
                    .craftTime(400f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.brassIngot, 3).power(0.6f).complete()
                    .output().items(HardItems.brassPlate, 2).complete()
                    .craftTime(400f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.tinIngot, 3).power(0.6f).complete()
                    .output().items(HardItems.tinPlate, 2).complete()
                    .craftTime(400f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.bronzePlate, 3).power(0.6f).complete()
                    .output().items(HardItems.compressedBronzePlate, 1).complete()
                    .craftTime(600f)
                    .build(),
            HardRecipe.of()
                    .input().items(Items.coal, 2).power(0.6f).complete()
                    .output().items(Items.graphite, 1).complete()
                    .craftTime(600f)
                    .build(),
            HardRecipe.of()
                    .input().items(HardItems.copperIngot, 1).power(0.6f).complete()
                    .output().items(HardItems.copperWire, 2).complete()
                    .craftTime(150f)
                    .build()
    );
}
