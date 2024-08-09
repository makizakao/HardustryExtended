package jp.makizakao.world.block.production;

public class SteamBoiler extends ExplodableCrafter {
    protected SteamBoiler(String name) {
        super(name);
    }

    protected SteamBoiler(Builder builder) {
        super(builder);
    }

    public class SteamBoilerBuild extends ExplodableCrafterBuild {
        @Override
        public void updateTile() {
            super.updateTile();

        }
    }

}
