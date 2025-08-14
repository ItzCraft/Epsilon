package epsilon.world.blocks.defense;

import arc.scene.ui.layout.Table;
import arc.util.io.Reads;
import arc.util.io.Writes;
import epsilon.ui.EpsStyles;
import mindustry.gen.Icon;
import mindustry.world.blocks.defense.ShieldWall;

public class EpsShieldWall extends ShieldWall{
    public EpsShieldWall(String name){
        super(name);
        configurable = true;
        saveConfig = true;
        canOverdrive = false;
    }

    public class EpsShieldWallBuild extends ShieldWallBuild{
        public boolean enabled = true;
        @Override
        public void draw(){
            if(enabled) super.draw();
        }
        @Override
        public void updateTile(){
            if(enabled) super.updateTile();
        }
        @Override
        public void buildConfiguration(Table table){
            table.button(Icon.defense, EpsStyles.epsButtonImage, ()->{
               enabled = true;
               deselect();
            }).pad(-50f).size(40f);
            table.button(Icon.redditAlienSmall, EpsStyles.epsButtonImage, ()->{
                enabled = false;
                deselect();
            }).pad(50f).size(40f);
        }
        @Override
        public void write(Writes write){
            super.write(write);
            write.bool(enabled);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            this.enabled = read.bool();
        }
    }
}
