package jp.makizakao.world.blocks;

import arc.graphics.g2d.TextureRegion;
import jp.makizakao.type.SmeltStack;
import mindustry.game.Team;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.UnitType;
import mindustry.world.Tile;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.meta.BuildVisibility;

import java.util.ArrayList;
import java.util.List;

import static mindustry.Vars.*;
import static mindustry.type.ItemStack.with;

public class HardCoreBlock extends CoreBlock {
    private List<SmeltStack> smeltList;

    // コンストラクタは非推奨
    private HardCoreBlock(String name) {
        super(name);
    }

    private HardCoreBlock(Builder builder) {
        super(builder.name);
        if(builder.buildVisibility) {
            requirements(Category.effect, BuildVisibility.editorOnly, builder.stacks);
        } else {
            requirements(Category.effect, with());
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
    public static Builder create(String name, int health, int itemCapacity, int size) {
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
        private final List<SmeltStack> smeltList = new ArrayList<>(HardCoreBlock.this.smeltList);
        @Override
        public void updateTile() {
            super.updateTile();
            // copperDustを所持している場合、copperIngotを生成
            if(!smeltList.isEmpty()) {
                smelt();
            }
        }

        private void smelt() {
            for(SmeltStack stack : smeltList) {
                if(items.has(stack.material().item, stack.material().amount)) {
                    if(stack.smelted()) {
                        items.remove(stack.material().item, stack.material().amount);
                        items.add(stack.product().item, stack.product().amount);
                    }
                }
            }
        }
    }

    public static class Builder {
        private final String name;
        private final int health;
        private final int itemCapacity;
        private final int size;
        private boolean buildVisibility = false;
        private ItemStack[] stacks;
        private boolean alwaysUnlocked = false;
        private boolean isFirstTier = false;
        private UnitType unitType;
        private int unitCapModifier = 1;
        private List<SmeltStack> smeltList;

        private Builder(String name, int health, int itemCapacity, int size) {
            this.name = name;
            this.health = health;
            this.itemCapacity = itemCapacity;
            this.size = size;
        }

        public Builder isEditorOnlyVisible(boolean editorOnlyVisible) {
            this.buildVisibility = editorOnlyVisible;
            return this;
        }

        public Builder with(Object... stacks) {
            this.stacks = ItemStack.with(stacks);
            return this;
        }

        public Builder alwaysUnlocked(boolean alwaysUnlocked) {
            this.alwaysUnlocked = alwaysUnlocked;
            return this;
        }

        public Builder isFirstTier(boolean isFirstTier) {
            this.isFirstTier = isFirstTier;
            return this;
        }

        public Builder unitType(UnitType unitType) {
            this.unitType = unitType;
            return this;
        }

        public Builder unitCapModifier(int unitCapModifier) {
            this.unitCapModifier = unitCapModifier;
            return this;
        }

        public Builder smeltList(List<SmeltStack> smeltList) {
            this.smeltList = smeltList;
            return this;
        }

        public HardCoreBlock build() {
            if(name == null) throw new IllegalStateException("Name is required");
            if(stacks == null) throw new IllegalStateException("ItemStack is required");
            if(unitType == null) throw new IllegalStateException("UnitType is required");
            return new HardCoreBlock(this);
        }
    }
}
