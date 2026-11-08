package epsilon.world.blocks.storage;


import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.math.*;
import arc.scene.ui.layout.Scl;
import arc.util.Time;
import arc.util.Tmp;
import mindustry.content.Fx;
import mindustry.entities.Effect;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.world.blocks.storage.CoreBlock;

import static epsilon.content.Kallistea.EpsFx.*;
import static mindustry.Vars.*;

public class UnstableCoreBlock extends CoreBlock {
    public int fireCount = 24, smokeCount = 18;

    public UnstableCoreBlock(String name){
        super(name);
        launchEffect = Fx.none;
    }

    public void drawCrash(float x, float y, float fout){
        float apparent = 1f + Interp.pow2Out.apply(fout) * 3f;
        float radius = size * tilesize / 2f * apparent;

        float prevZ = Draw.z();
        Draw.z(Layer.max + 1f);


        for(int i = 0; i < smokeCount; i++){
            float life = (Time.time * 0.012f + Mathf.randomSeed(i + 200)) % 1f;
            float a = Mathf.randomSeed(i) * 360f;
            float d = Mathf.randomSeed(i + 100) * radius;
            Draw.color(Color.gray, Color.darkGray, life);
            Draw.alpha((1f - life) * 0.6f);
            Fill.circle(
                    x + Angles.trnsx(a, d),
                    y + Angles.trnsy(a, d) + life * 25f * apparent,
                    (2f + life * 5f) * apparent);
        }


        for(int i = 0; i < fireCount; i++){
            float life = (Time.time * 0.03f + Mathf.randomSeed(i + 400)) % 1f;
            float a = Mathf.randomSeed(i + 300) * 360f;
            float d = Mathf.randomSeed(i + 500) * radius * 0.8f;
            Draw.color(Pal.lightFlame, Pal.darkFlame, life);
            Fill.circle(
                    x + Angles.trnsx(a, d),
                    y + Angles.trnsy(a, d) + life * 10f * apparent,
                    (1f - life) * 3.5f * apparent);
        }

        Draw.color();
        Draw.z(prevZ);
    }

    public void crashImpact(float x, float y){
        Fx.dynamicExplosion.at(x, y, size * 0.8f);
        for(int i = 0; i < 12; i++){
            Tmp.v1.trns(Mathf.random(360f), Mathf.random(size * tilesize));
            crashFire.at(x + Tmp.v1.x, y + Tmp.v1.y);
            crashSmoke.at(x + Tmp.v1.x, y + Tmp.v1.y);
        }
        Effect.shake(6f, 20f, x, y);
    }



    public class UnstableCoreBlockBuild extends CoreBuild{
        @Override
        public void drawLanding(float x, float y){
            float fout = renderer.getLandTime() / landDuration;
            if(renderer.isLaunching()) fout = 1f - fout;
            fout = Mathf.clamp(fout);

            float scl = Scl.scl(4f) / renderer.getDisplayScale();
            float s = region.width * region.scl() * scl * 3.6f * Interp.pow2Out.apply(fout);
            float rotation = Interp.pow2In.apply(fout) * 135f;
            float fin = renderer.getLandTimeIn();
            float thrustOpen = 0.25f;
            float thrusterFrame = fin >= thrustOpen ? 1f : fin / thrustOpen;

            Draw.color(Pal.lightTrail);
            Draw.rect("circle-shadow", x, y, s, s);

            Draw.scl(scl);

            Draw.color();
            drawLandingThrusters(x, y, rotation, thrusterFrame);

            Drawf.spinSprite(region, x, y, rotation);

            Draw.color();
            Draw.alpha(Interp.pow4In.apply(thrusterFrame));
            drawLandingThrusters(x, y, rotation, thrusterFrame);
            Draw.alpha(1f);

            if(teamRegions[team.id] == teamRegion) Draw.color(team.color);
            Drawf.spinSprite(teamRegions[team.id], x, y, rotation);

            Draw.color();
            Draw.scl();
            Draw.reset();
            drawCrash(x, y, fout);
        }
    }
}
