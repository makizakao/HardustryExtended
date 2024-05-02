package jp.makizakao.content;

import mindustry.type.Item;
import mindustry.world.blocks.environment.OreBlock;

import java.util.Objects;

public class DefaultBlockBuilders {
    public static class OreBlockBuilder {
        private final String name;
        private final Item dropItem;
        private boolean oreDefault = false;
        private float oreThreshold = 0f;
        private float oreScale = 0f;

        private OreBlockBuilder(String name, Item dropItem) {
            this.name = name;
            this.dropItem = dropItem;
        }

        public static OreBlockBuilder create(String name, Item dropItem) {
            return new OreBlockBuilder(name, dropItem);
        }

        public OreBlockBuilder oreDefault() {
            this.oreDefault = true;
            return this;
        }

        public OreBlockBuilder oreThreshold(float oreThreshold) {
            this.oreThreshold = oreThreshold;
            return this;
        }

        public OreBlockBuilder oreScale(float oreScale) {
            this.oreScale = oreScale;
            return this;
        }

        public OreBlock build() {
            if(Objects.isNull(name)) throw new IllegalArgumentException("Name must be set");
            return new OreBlock(name, dropItem) {{
                oreDefault = OreBlockBuilder.this.oreDefault;
                oreThreshold = OreBlockBuilder.this.oreThreshold;
                oreScale = OreBlockBuilder.this.oreScale;
            }};
        }
    }
}
