package epsilon.world.blocks.storage;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.TextureRegion;
import arc.util.Time;
import arc.math.Mathf;
import epsilon.graphics.EpsPal;
import mindustry.gen.Building;
import mindustry.graphics.*;
import mindustry.world.blocks.storage.CoreBlock;

public class EpsilonCoreBlock extends CoreBlock{
    public TextureRegion glow;
    public float glowMag = 0.5f;
    public float glowScl = 10f;

    public EpsilonCoreBlock(String name){
        super(name);
    }

    @Override
    public void load(){
        super.load();
        glow = Core.atlas.find(name + "-glow");
        uiIcon = fullIcon = editorIcon = Core.atlas.find(name + "-full");
    }
    public class EpsilonCoreBuild extends CoreBuild{
        @Override
        public void draw(){
            super.draw();
            Drawf.additive(glow, team.color, 0.8f - glowMag + Mathf.absin(Time.time, glowScl, glowMag), x, y, 0f, Layer.blockAdditive);
        }
    } 
}
