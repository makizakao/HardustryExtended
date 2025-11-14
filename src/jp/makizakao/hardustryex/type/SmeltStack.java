package jp.makizakao.hardustryex.type;

import arc.struct.Seq;
import arc.util.Time;
import jp.makizakao.hardustryex.content.HardItems;
import lombok.Builder;
import lombok.experimental.SuperBuilder;
import mindustry.type.Item;
import mindustry.type.ItemStack;

import java.util.Objects;

// SmeltStack class for smelting items.
@SuperBuilder(builderMethodName = "of")
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

    public abstract static class SmeltStackBuilder<C extends SmeltStack, B extends SmeltStackBuilder<C, B>> {
        private ItemStack[] material;
        private ItemStack[] product;

        public B material(Object... items) {
            material = new ItemStack[items.length / 2];
            for(int i = 0; i < items.length; i += 2){
                material[i / 2] = new ItemStack((Item)items[i], ((Number)items[i + 1]).intValue());
            }
            return self();
        }

        public B product(Object... items) {
            product = new ItemStack[items.length / 2];
            for(int i = 0; i < items.length; i += 2){
                product[i / 2] = new ItemStack((Item)items[i], ((Number)items[i + 1]).intValue());
            }
            return self();
        }
    }
}
