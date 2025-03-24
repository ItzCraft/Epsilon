package epsilon.world.blocks.storage;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.TextureRegion;
import arc.util.Time;
import arc.math.Mathf;
import epsilon.graphics.EpsPal;
import mindustry.Vars;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.world.blocks.storage.CoreBlock;

import static epsilon.content.Kallistea.EpsFx.launchCore;
import static mindustry.Vars.*;

public class EpsilonCoreBlock extends CoreBlock{
    public Color[] glowColors = {Color.valueOf("00000000"), Color.coral, Color.valueOf("ff6161"), Color.pink, Color.acid, Color.sky, Color.red, EpsPal.ganierisTeam, EpsPal.incersTeam, EpsPal.fenspor};
    public TextureRegion glow;
    public float glowMag = 0.5f;
    public float glowScl = 10f;
    public float landDuration = 160;
    public Effect launchEffect = launchCore;

    public EpsilonCoreBlock(String name){
        super(name);
    }

    @Override
    public void load(){
        super.load();
        glow = Core.atlas.find(name + "-glow");
        uiIcon = fullIcon = editorIcon = Core.atlas.find(name + "-full");
    }
    public class EpsilonCoreBuild extends CoreBuild implements LaunchAnimator{
        @Override
        public void draw(){
            super.draw();
            Drawf.additive(glow, team.id < 9 ? glowColors[team.id] : glowColors[1], 0.8f - glowMag + Mathf.absin(Time.time, glowScl, glowMag), x, y, 0f, Layer.blockAdditive);
        }
        @Override
        public float launchDuration(){
            return landDuration;
        }

        @Override
        public float zoomLaunch() {
            return 0;
        }

        @Override
        public void drawLaunch() {

        }

        @Override
        public void beginLaunch(boolean launching){
            if(!headless){
                if(renderer.isLaunching()){
                    return;
                }else{
                    Time.run(launchDuration(), () -> {
                        launchEffect.at(this);
                        Effect.shake(5f, 5f, this);
                        thrusterTime = 1f;
                    });
                }
            }
        }

        @Override
        public void endLaunch() {

        }

        @Override
        public void updateLaunch() {

        }
    } 
}
