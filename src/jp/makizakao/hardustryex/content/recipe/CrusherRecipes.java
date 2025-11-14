package jp.makizakao.hardustryex.content.recipe;

import arc.struct.Seq;
import jp.makizakao.hardustryex.content.HardItems;
import jp.makizakao.hardustryex.type.recipe.ResultRecipe;
import mindustry.content.Items;
import multicraft.Recipe;

public class CrusherRecipes {
    public static final Seq<Recipe> CRUSHER_TIER_1 = Seq.with(
            ResultRecipe.of()
                    .input().items(HardItems.nativeCopper, 1).power(0.3f).complete()
                    .output().items(HardItems.copperDust, 2).complete().dropChances(1.0f)
                    .craftTime(60f)
                    .build(),
            ResultRecipe.of()
                    .input().items(HardItems.galena, 1).power(0.2f).complete()
                    .output().items(HardItems.galenaDust, 2).complete().dropChances(1.0f)
                    .craftTime(60f)
                    .build(),
            ResultRecipe.of()
                    .input().items(HardItems.teallite, 1).power(0.3f).complete()
                    .output().items(HardItems.tealliteDust, 2).complete().dropChances(1.0f)
                    .craftTime(60f)
                    .build(),
            ResultRecipe.of()
                    .input().items(HardItems.tinIngot, 1).power(0.2f).complete()
                    .output().items(HardItems.tinDust, 1).complete().dropChances(1.0f)
                    .craftTime(60f)
                    .build(),
            ResultRecipe.of()
                    .input().items(HardItems.tetrahedrite, 1).power(0.3f).complete()
                    .output().items(HardItems.copperDust, 2, HardItems.zincDust, 1).complete().dropChances(1.0f, 0.2f)
                    .craftTime(60f)
                    .build()
    );
    public static final Seq<Recipe> CRUSHER_TIER_2 = Seq.with(
            ResultRecipe.of()
                    .input().items(HardItems.nativeCopper, 1).power(0.6f).complete()
                    .output().items(HardItems.copperDust, 2).complete().dropChances(1.0f)
                    .craftTime(30f)
                    .build(),
            ResultRecipe.of()
                    .input().items(HardItems.galena, 1).power(0.6f).complete()
                    .output().items(HardItems.galenaDust, 2).complete().dropChances(1.0f)
                    .craftTime(30f)
                    .build(),
            ResultRecipe.of()
                    .input().items(HardItems.teallite, 1).power(0.6f).complete()
                    .output().items(HardItems.tealliteDust, 2).complete().dropChances(1.0f)
                    .craftTime(30f)
                    .build(),
            ResultRecipe.of()
                    .input().items(HardItems.tinIngot, 1).power(0.6f).complete()
                    .output().items(HardItems.tinDust, 1).complete().dropChances(1.0f)
                    .craftTime(30f)
                    .build(),
            ResultRecipe.of()
                    .input().items(HardItems.tetrahedrite, 1).power(0.6f).complete()
                    .output().items(HardItems.copperDust, 2, HardItems.zincDust, 1).complete().dropChances(1.0f, 0.2f)
                    .craftTime(30f)
                    .build(),
            ResultRecipe.of()
                    .input().items(Items.coal, 1).power(0.6f).complete()
                    .output().items(HardItems.coalDust, 2).complete().dropChances(1.0f)
                    .craftTime(30f)
                    .build()
    );
}
