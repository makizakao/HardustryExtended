package jp.makizakao.type;

import arc.struct.Seq;
import arc.util.Time;
import jp.makizakao.content.HardItems;
import mindustry.type.Item;
import mindustry.type.ItemStack;

import java.util.Objects;

public class SmeltStack {
    public static final SmeltStack[] empty = {};
    public ItemStack[] material;
    public ItemStack[] product;
    public float smeltTime;
    private float timeSmelted = 0;

    public SmeltStack(ItemStack[] material, ItemStack[] product, float smeltTime) {
        this.material = material;
        this.product = product;
        this.smeltTime = smeltTime;
    }

    public SmeltStack copy() {
        return new SmeltStack(material, product, smeltTime);
    }

    public boolean smelted(float delta) {
        if(timeSmelted >= smeltTime) {
            timeSmelted = 0;
            return true;
        }
        timeSmelted += delta;
        return false;
    }


    public static final Seq<SmeltStack> SMELT_TIER_1 = Seq.with(
            new Builder().material(HardItems.copperDust, 2)
                    .product(HardItems.copperIngot, 1)
                    .smeltTime(Time.toSeconds * 1)
                    .build(),
            new Builder().material(HardItems.leadDust, 2)
                    .product(HardItems.leadIngot, 1)
                    .smeltTime(Time.toSeconds * 1)
                    .build());
    public static final Seq<SmeltStack> SMELT_TIER_2 = Seq.with(
            new Builder().material(HardItems.copperDust, 2)
                    .product(HardItems.copperIngot, 1)
                    .smeltTime(Time.toSeconds / 2)
                    .build(),
            new Builder().material(HardItems.leadDust, 2)
                    .product(HardItems.leadIngot, 1)
                    .smeltTime(Time.toSeconds / 2)
                    .build());

    private static class Builder {
        private ItemStack[] material;
        private ItemStack[] product;
        private float smeltTime = 0;

        public Builder material(Object... items) {
            material = new ItemStack[items.length / 2];
            for(int i = 0; i < items.length; i += 2){
                material[i / 2] = new ItemStack((Item)items[i], ((Number)items[i + 1]).intValue());
            }
            return this;
        }

        public Builder product(Object... items) {
            product = new ItemStack[items.length / 2];
            for(int i = 0; i < items.length; i += 2){
                product[i / 2] = new ItemStack((Item)items[i], ((Number)items[i + 1]).intValue());
            }
            return this;
        }

        public Builder smeltTime(float smeltTime) {
            this.smeltTime = smeltTime;
            return this;
        }

        public SmeltStack build() {
            if(Objects.isNull(material)) throw new IllegalStateException("Material must be set.");
            if(Objects.isNull(product)) throw new IllegalStateException("Product must be set.");
            if(smeltTime <= 0) throw new IllegalStateException("Smelt time must be greater than 0.");
            return new SmeltStack(material, product, smeltTime);
        }
    }
}
