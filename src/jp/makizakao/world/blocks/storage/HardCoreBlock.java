package jp.makizakao.world.blocks.storage;

import arc.graphics.g2d.TextureRegion;
import arc.struct.Seq;
import jp.makizakao.type.SmeltStack;
import jp.makizakao.world.builder.BaseBlockBuilder.*;
import mindustry.game.Team;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.UnitType;
import mindustry.world.Tile;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.meta.BuildVisibility;

import java.util.Arrays;

import static mindustry.Vars.*;

public class HardCoreBlock extends CoreBlock {
    private Seq<SmeltStack> smeltList;

    // コンストラクタは非推奨
    private HardCoreBlock(String name) {
        super(name);
    }

    // Builder用のコンストラクタ
    private HardCoreBlock(Builder builder) {
        super(builder.name);
        if(builder.buildVisibility) {
            requirements(Category.effect, BuildVisibility.editorOnly, builder.requirements);
        } else {
            requirements(Category.effect, builder.requirements);
        }

        alwaysUnlocked = builder.alwaysUnlocked;
        isFirstTier = builder.isFirstTier;
        unitType = builder.unitType;
        health = builder.health;
        itemCapacity = builder.itemCapacity;
        size = builder.size;
        unitCapModifier = builder.unitCapModifier;
        smeltList = builder.smeltList;
    }

    // Builderを作成
    public static IRequirementsBuilder<IUnitTypeBuilder<IUnitCapModifierBuilder<ISmeltListBuilder<Builder>>>> create(
            String name, int health, int itemCapacity, int size) {
        return new Builder(name, health, itemCapacity, size);
    }

    @Override
    public boolean canPlaceOn(Tile tile, Team team, int rotation) {
        if(tile == null) return false;
        if(state.isEditor()) return true;
        CoreBuild core = team.core();
        tile.getLinkedTilesAs(this, tempTiles);
        if(!tempTiles.contains(o -> !o.floor().allowCorePlacement || o.block() instanceof CoreBlock)){
            return true;
        }

        // 同じ大きさのコアブロックを置けるように変更
        if(core == null || (!state.rules.infiniteResources && !core.items.has(requirements, state.rules.buildCostMultiplier))) return false;
        return tile.block() instanceof CoreBlock && size >= tile.block().size && (!requiresCoreZone || tempTiles.allMatch(o -> o.floor().allowCorePlacement));
    }

    @Override
    protected TextureRegion[] icons() {
        return new TextureRegion[]{region, teamRegions[Team.sharded.id]};
    }

    public class HardCoreBuild extends CoreBuild {
        private final Seq<SmeltStack> smeltList = HardCoreBlock.this.smeltList.copy();
        @Override
        public void updateTile() {
            super.updateTile();
            // copperDustを所持している場合、copperIngotを生成
            if(!smeltList.isEmpty()) {
                smelt();
            }
        }

        private void smelt() {
            for(var stack : smeltList) {
                if(items.has(stack.material)) {
                    if(stack.smelted(delta())) {
                        items.remove(stack.material);
                        Arrays.stream(stack.product)
                                .forEach(s -> items.add(s.item, s.amount));
                    }
                }
            }
        }
    }

    public static class Builder implements IRequirementsBuilder<
            IUnitTypeBuilder<IUnitCapModifierBuilder<ISmeltListBuilder<Builder>>>>,
            IUnitTypeBuilder<IUnitCapModifierBuilder<ISmeltListBuilder<Builder>>>,
            IUnitCapModifierBuilder<ISmeltListBuilder<Builder>>, ISmeltListBuilder<Builder> {
        private final String name;
        private final int health;
        private final int itemCapacity;
        private final int size;
        private boolean buildVisibility = false;
        private ItemStack[] requirements;
        private boolean alwaysUnlocked = false;
        private boolean isFirstTier = false;
        private UnitType unitType;
        private int unitCapModifier = 1;
        private Seq<SmeltStack> smeltList;

        private Builder(String name, int health, int itemCapacity, int size) {
            this.name = name;
            this.health = health;
            this.itemCapacity = itemCapacity;
            this.size = size;
        }

        @Override
        public IUnitTypeBuilder<IUnitCapModifierBuilder<ISmeltListBuilder<Builder>>> requirements(Object... stacks) {
            this.requirements = ItemStack.with(stacks);
            return this;
        }

        @Override
        public IUnitCapModifierBuilder<ISmeltListBuilder<Builder>> unitType(UnitType unitType) {
            this.unitType = unitType;
            return this;
        }

        @Override
        public ISmeltListBuilder<Builder> unitCapModifier(int unitCapModifier) {
            this.unitCapModifier = unitCapModifier;
            return this;
        }

        @Override
        public Builder smeltList(Seq<SmeltStack> smeltList) {
            this.smeltList = smeltList;
            return this;
        }

        public Builder editorOnlyVisible() {
            this.buildVisibility = true;
            return this;
        }

        public Builder alwaysUnlocked() {
            this.alwaysUnlocked = true;
            return this;
        }

        public Builder firstTier() {
            this.isFirstTier = true;
            this.editorOnlyVisible();
            this.alwaysUnlocked();
            return this;
        }

        public HardCoreBlock build() {
            return new HardCoreBlock(this);
        }
    }
}
