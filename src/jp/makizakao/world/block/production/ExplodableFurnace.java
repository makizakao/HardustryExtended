package jp.makizakao.world.block.production;

import arc.audio.Sound;
import jp.makizakao.world.builder.BaseBlockBuilder.*;
import mindustry.content.Fx;
import mindustry.entities.Damage;
import mindustry.entities.Effect;
import mindustry.gen.Sounds;

public class ExplodableFurnace extends HardMultiCrafter {
    private int explosionDamage;
    private int explosionRadius;
    private float explodeHeat = 0;
    private final Effect explodeEffect = Fx.explosion;
    private final Sound explodeSound = Sounds.none;

    protected ExplodableFurnace(String name) {
        super(name);
    }

    protected ExplodableFurnace(Builder builder) {
        super(builder);
        this.explodeHeat = builder.explodeHeat;
        this.explosionDamage = builder.damage;
        this.explosionRadius = builder.radius;
    }

    public static IExplodeHeatBuilder<IRequirementsBuilder<IResolveRecipesBuilder<
            HardMultiCrafter.Builder>>> createExplodable(String name, int health, int size) {
        return new Builder(name, health, size);
    }

    public class ExplodableFurnaceBuild extends HardMultiCrafterBuild {
        @Override
        public void updateTile() {
            super.updateTile();
            if(explodeHeat < this.heat) {
                createExplosion();
                onDestroyed();
            }
        }

        public void createExplosion() {
            if (ExplodableFurnace.this.explosionDamage > 0) {
                Damage.damage(this.x, this.y, (float)(ExplodableFurnace.this.explosionRadius * 8), (float)ExplodableFurnace.this.explosionDamage);
            }

            ExplodableFurnace.this.explodeEffect.at(this);
            ExplodableFurnace.this.explodeSound.at(this);
        }
    }

    public static class Builder extends HardMultiCrafter.Builder implements IExplodeHeatBuilder<
                IRequirementsBuilder<IResolveRecipesBuilder<HardMultiCrafter.Builder>>> {
        private int damage;
        private int radius;
        private float explodeHeat = 0;

        protected Builder(String name, int health, int size) {
            super(name, health, size);
        }

        @Override
        public IRequirementsBuilder<IResolveRecipesBuilder<HardMultiCrafter.Builder>> explodeHeat(
                float explodeHeat, int damage, int radius) {
            this.explodeHeat = explodeHeat;
            this.damage = damage;
            this.radius = radius;
            return this;
        }

        @Override
        public ExplodableFurnace build()
        {
            return new ExplodableFurnace(this);
        }
    }
}
