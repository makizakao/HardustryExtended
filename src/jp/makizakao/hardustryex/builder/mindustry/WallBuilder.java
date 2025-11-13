package jp.makizakao.hardustryex.builder.mindustry;

import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.world.blocks.defense.Wall;

public class WallBuilder {
    private final String name;
    private final int health;
    private final int size;
    private final ItemStack[] requirements;

    private WallBuilder(RequiredBuilder builder) {
        this.name = builder.name;
        this.health = builder.health;
        this.size = builder.size;
        this.requirements = builder.requirements;
    }

    public static IRequirementsBuilder<WallBuilder> create(String name, int health, int size) {
        return new RequiredBuilder(name, health, size);
    }

    public static class RequiredBuilder implements IRequirementsBuilder<WallBuilder> {
        private String name;
        private int health;
        private int size;
        private ItemStack[] requirements;

        private RequiredBuilder() {}

        private RequiredBuilder(String name, int health, int size) {
            this.name = name;
            this.health = health;
            this.size = size;
        }

        @Override
        public WallBuilder requirements(Object... stacks) {
            this.requirements = ItemStack.with(stacks);
            return new WallBuilder(this);
        }
    }

    public Wall build() {
        if(name == null) throw new IllegalArgumentException("Name must be set");
        return new Wall(name) {{
            requirements(Category.defense, WallBuilder.this.requirements);
            health = WallBuilder.this.health;
            size = WallBuilder.this.size;
        }};
    }

    public interface IRequirementsBuilder<T> {
        T requirements(Object... stacks);
    }
}
