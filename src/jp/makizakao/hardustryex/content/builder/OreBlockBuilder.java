package jp.makizakao.hardustryex.content.builder;

import jp.makizakao.hardustryex.content.HardItems;
import mindustry.type.Item;
import mindustry.world.Block;
import mindustry.world.blocks.environment.OreBlock;

import java.util.Objects;

public class OreBlockBuilder {
    /**
     * 追加鉱石
     * <p>
     *     鉱石ブロックを追加する際は、このクラスに追加し、{@link #load() }メソッド内で初期化する
     * </p>
     */
    public static Block
            galenaOre, sphaleriteOre, tealliteOre, tetrahedriteOre, tinyCopperOre, tinyLeadOre;

    /**
     * 鉱石ブロックを作成するメソッド
     * @param name ブロック名
     * @param dropItem 採掘時にドロップするアイテム
     * @param ore 鉱石
     * @return 鉱石ブロック
     */
    private static OreBlock build(final String name, final Item dropItem, final Vein ore) {
        if (name.isEmpty()) throw new IllegalArgumentException("ブロック名が未定義です");
        if (Objects.isNull(dropItem)) throw new IllegalArgumentException("ドロップアイテムが未定義です");
        if (Objects.isNull(ore)) throw new IllegalArgumentException("鉱石が未定義です");

        return new OreBlock(name, dropItem) {{
            oreDefault = ore.generateDefault;
            oreThreshold = ore.threshold;
            oreScale = ore.generationScale;
        }};
    }

    /**
     * 鉱石ブロックをゲームに登録するメソッド
     * <p>
     *     {@link jp.makizakao.hardustryex.content.HardBlocks#load()} から呼び出す
     * </p>
     */
    public static void load() {
        var hardVein = new Vein(false, 0f, 0f);
        galenaOre = OreBlockBuilder.build("galena-ore", HardItems.galena, hardVein);
        sphaleriteOre = OreBlockBuilder.build("sphalerite-ore", HardItems.sphalerite, hardVein);
        tealliteOre = OreBlockBuilder.build("teallite-ore", HardItems.teallite, hardVein);
        tetrahedriteOre = OreBlockBuilder.build("tetrahedrite-ore", HardItems.tetrahedrite, hardVein);
        tinyCopperOre = OreBlockBuilder.build("tiny-copper-ore", HardItems.copperDust, hardVein);
        tinyLeadOre = OreBlockBuilder.build("tiny-lead-ore", HardItems.leadDust, hardVein);
    }
}
