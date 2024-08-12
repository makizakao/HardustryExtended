package jp.makizakao.hardustryex.world.blocks.power;

import arc.graphics.g2d.Draw;
import arc.math.Mathf;
import arc.util.Time;
import jp.makizakao.hardustryex.world.stat.WindGeneratorStatsManager;
import mindustry.type.Category;
import mindustry.type.ItemStack;

/**
 * 風力発電機のクラスの定義
 */
public class WindGenerator extends RotateGenerator {
    /** ランダムに発電量を決めるときの最小値、最大値 */
    private float minEfficiency = 1f;
    private float maxEfficiency = 1f;
    /** 現在の発電量が維持される時間の最小値、最大値 */
    private float minPowerDuration = Time.toMinutes;
    private float maxPowerDuration = Time.toMinutes * 5;
    protected WindGeneratorStatsManager statsManager = new WindGeneratorStatsManager();

    /**
     * Builder用のコンストラクタ
     */
    private WindGenerator(Builder builder) {
        super(builder.name);
        requirements(Category.power, builder.requirements);
        health = builder.health;
        size = builder.size;
        powerProduction = builder.powerProduction;
        minEfficiency = builder.minEfficiency;
        maxEfficiency = builder.maxEfficiency;
        minPowerDuration = builder.minPowerDuration;
        maxPowerDuration = builder.maxPowerDuration;
        rotateSpeed = builder.rotateSpeed;
    }

    @Override
    public void setStats() {
        super.setStats();
        statsManager.setStats(this);
    }

    public float getMinGeneration() {
        return this.powerProduction * 60 * minEfficiency;
    }

    public float getMaxGeneration() {
        return this.powerProduction * 60 * maxEfficiency;
    }

    @Override
    public void load() {
        super.load();
    }

    /** ゲーム内で生成されたブロックのクラス */
    public class WindGeneratorBuild extends RotateGeneratorBuild {
        /** 現在の発電量の継続時間 */
        private float powerDuration = -1f;

        /** 毎Tick実行される処理 */
        @Override
        public void updateTile() {
            // 発電量の継続時間が終了した場合、新たな発電量を決定
            if(powerDuration < 0) {
                powerDuration = Mathf.lerp(minPowerDuration, maxPowerDuration, Mathf.random());
                productionEfficiency = Mathf.lerp(minEfficiency, maxEfficiency, Mathf.random());
            }
            // 発電量の継続時間を更新
            powerDuration -= delta();
            // 描画時の回転角度を更新
            progress = (progress + rotateSpeed * productionEfficiency * delta()) % 360;
        }

        /** ブロックの描画処理 */
        @Override
        public void draw() {
            super.draw();
            Draw.rect(rotatorRegion, x, y, progress);
        }
    }

    /** WinGeneratorを継承するブロックの定義時を行うBuilderクラス */
    public static class Builder {
        /** WindGeneratorの属性 */
        private String name;
        private int health;
        private int size;
        private float powerProduction = 1f;
        private float minEfficiency = 1f;
        private float maxEfficiency = 1f;
        private float minPowerDuration = Time.toMinutes;
        private float maxPowerDuration = Time.toMinutes * 5;
        private float rotateSpeed = 1f;
        private ItemStack[] requirements;

        private Builder() {}

        /**
         * RequiredBuilderからBuilderを生成
         */
        private Builder(RequiredBuilder builder) {
            this.name = builder.name;
            this.health = builder.health;
            this.size = builder.size;
            this.requirements = builder.requirements;
            this.powerProduction = builder.powerProduction;
            this.minEfficiency = builder.minEfficiency;
            this.maxEfficiency = builder.maxEfficiency;
            this.minPowerDuration = builder.minPowerDuration;
            this.maxPowerDuration = builder.maxPowerDuration;
        }

        /**
         * WindGeneratorのBuilderを生成
         * @param name WindGeneratorの名前
         * @param health WindGeneratorの耐久値
         * @param size WindGeneratorのサイズ
         * @return WindGeneratorのBuilder
         */
        public static IRequirementsBuilder<IPowerProductionBuilder<IEfficiencyBuilder<
                IPowerDurationBuilder<Builder>>>> create(String name, int health, int size) {
            return new RequiredBuilder(name, health, size);
        }

        /**
         * WindGenerator生成時の必須項目を設定するBuilderクラス
         */
        public static class RequiredBuilder implements
                IRequirementsBuilder<IPowerProductionBuilder<IEfficiencyBuilder<IPowerDurationBuilder<Builder>>>>,
                IPowerProductionBuilder<IEfficiencyBuilder<IPowerDurationBuilder<Builder>>>,
                IEfficiencyBuilder<IPowerDurationBuilder<Builder>>,
                IPowerDurationBuilder<Builder> {
            private String name;
            private int health;
            private int size;
            private ItemStack[] requirements;
            private float powerProduction;
            private float minEfficiency;
            private float maxEfficiency;
            private float minPowerDuration;
            private float maxPowerDuration;

            private RequiredBuilder() {}

            private RequiredBuilder(String name, int health, int size) {
                this.name = name;
                this.health = health;
                this.size = size;
            }

            @Override
            public IPowerProductionBuilder<IEfficiencyBuilder<IPowerDurationBuilder<Builder>>> requirements(
                    Object... stacks) {
                this.requirements = ItemStack.with(stacks);
                return this;
            }

            @Override
            public IEfficiencyBuilder<IPowerDurationBuilder<Builder>> powerProduction(float powerProduction) {
                this.powerProduction = powerProduction;
                return this;
            }

            @Override
            public IPowerDurationBuilder<Builder> efficiency(float minEfficiency, float maxEfficiency) {
                this.minEfficiency = minEfficiency;
                this.maxEfficiency = maxEfficiency;
                return this;
            }

            @Override
            public Builder powerDuration(float minPowerDuration, float maxPowerDuration) {
                this.minPowerDuration = minPowerDuration;
                this.maxPowerDuration = maxPowerDuration;
                return new Builder(this);
            }
        }

        public Builder rotateSpeed(float rotateSpeed) {
            this.rotateSpeed = rotateSpeed;
            return this;
        }

        public WindGenerator build() {
            return new WindGenerator(this);
        }

        public interface IRequirementsBuilder<T> {
            T requirements(Object... stacks);
        }

        public interface IPowerProductionBuilder<T> {
            T powerProduction(float powerProduction);
        }

        public interface IEfficiencyBuilder<T> {
            T efficiency(float minEfficiency, float maxEfficiency);
        }

        public interface IPowerDurationBuilder<T> {
            T powerDuration(float minPowerDuration, float maxPowerDuration);
        }
    }
}
