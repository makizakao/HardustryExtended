package jp.makizakao.hardustryex.content.builder;

public final class Vein {
    final boolean generateDefault;
    final float threshold;
    final float generationScale;

    /**
     * 鉱脈に関する情報を保持するクラス
     * @param generateDefault 鉱脈がデフォルトでワールドに生成されるか
     * @param threshold 鉱脈生成のしきい値
     * @param generationScale 鉱脈生成した時の鉱脈の大きさ
     */
    public Vein(final boolean generateDefault, final float threshold, final float generationScale) {
        this.generateDefault = generateDefault;
        this.threshold = threshold;
        this.generationScale = generationScale;
    }
}
