package epsilon.content.Kallistea;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.Vec2;
import arc.util.Time;
import mindustry.entities.Effect;
import mindustry.gen.Bullet;
import mindustry.graphics.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.randLenVectors;

public class EpsFx{
    public static final TextureRegion[] overloadSprites = new TextureRegion[]{
            Core.atlas.find("epsilon-energy-1"),
            Core.atlas.find("epsilon-energy-2"),
            Core.atlas.find("epsilon-energy-3")
};
    public static final Effect
    launchCore = new Effect(50, e -> {
        color(Pal.engine);

        e.scaled(25f, f -> {
            stroke(f.fout() * 2f);
            Lines.circle(e.x, e.y, 4f + f.finpow() * 30f);
        });

        stroke(e.fout() * 2f);

        randLenVectors(e.id, 24, e.finpow() * 50f, (x, y) -> {
            float ang = Mathf.angle(x, y);
            lineAngle(e.x + x, e.y + y, ang, e.fout() * 4 + 1f);
        });
    }),
    plasmaChargeRed = new Effect(140f, e -> {
        color(Color.valueOf("ffb3bc"));

        randLenVectors(e.id, 17, 5f + 20f * e.fout(), e.rotation, 170f, (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 5.4f + 1f);
        });
    }),
    purpleFire = new Effect(60f, e -> {
        float alpha = e.fin();
        float radius = 2f + Mathf.sin(e.time * 0.1f) * 0.5f;
        Draw.color(Color.valueOf("d400ff"), Color.valueOf("8a2be2"), alpha);
        Fill.circle(e.x, e.y, radius);
        Drawf.light(e.x, e.y, radius * 6f, Color.valueOf("d400ff"), 0.3f);
    }),
    purpleSmoke = new Effect(30f, e -> {
        Draw.color(Color.valueOf("ab72d6"), 0.3f);
        Fill.rect(e.x + Mathf.range(1f), e.y + e.finpow() * 4f, 1f, 4f);
    }),
    trailParticleEffect = new Effect(8f, e -> {
        float life = Interp.pow2Out.apply(1f - e.fin());
        Color dropColor = Color.valueOf("db96eb").cpy().a(0.7f * life);

        Draw.color(dropColor);
        Fill.circle(e.x, e.y, 1f * (1f - e.fin()));
        Draw.reset();
    }),
    purpleOrbital = new Effect(60f, e -> {
        float t = e.time / 60f;
        float alpha = Interp.pow2Out.apply(1f - t) * (0.8f + Mathf.absin(e.id, 6f, 0.1f));
        int particles = 5;
        float baseRadius = 15f;
        float speed = -900f;
        int times = 13;
        for (int i = 0; i < particles; i++) {
            float phaseOffset = Mathf.randomSeed(e.id * 17 + i * 43) * 360f;
            float angle = t * speed + phaseOffset;
            float rad = angle * Mathf.degreesToRadians;
            float interpRadius = baseRadius * (1f + Mathf.absin(t * 5f, 0.1f));
            float x = Mathf.cos(rad) * interpRadius;
            float y = Mathf.sin(rad) * interpRadius;
            float speedAngle = angle + 90f;
            float moveRad = speedAngle * Mathf.degreesToRadians;
            float pulse = 1f + Mathf.sin(t * 12f + i * 1.5f) * 0.1f;
            float orbSize = Interp.pow2Out.apply(0.8f * pulse);
            float trailWidth = orbSize * 1.5f;
            float trailLength = Interp.sineOut.apply(19f * pulse);
            Color c = Color.valueOf("b697c2").cpy().a(alpha);
            Draw.color(c);
            Fill.circle(e.x + x, e.y + y, orbSize);
            Drawf.tri(
                    e.x + x,
                    e.y + y,
                    trailWidth,
                    trailLength,
                    speedAngle
            );
            float px = e.x + x + Mathf.cos(moveRad) * trailLength * 0.6f;
            float py = e.y + y + Mathf.sin(moveRad) * trailLength * 0.6f;
            for(int s=0; s<times; s++){trailParticleEffect.at(px, py);}
        }

        Draw.reset();
    }),
    plasmaChargePurple = new Effect(140f, e -> {
        color(Color.valueOf("c075d1"));

        randLenVectors(e.id, 17, 5f + 20f * e.fout(), e.rotation, 170f, (x, y) -> {
            lineAngle(e.x + x, e.y + y, Mathf.angle(x, y), e.fslope() * 5.4f + 1f);
        });
    }),
    prismBurst = new Effect(25f, e -> {
        Draw.color(Color.purple, Color.white, e.fin());
        for (int i = 0; i < 6; i++) {
            float angle = i * 60f + e.time * 6f;
            float len = 5f + Mathf.absin(e.time, 2f, 1f);
            Lines.lineAngle(e.x, e.y, angle, len);
        }
        Drawf.light(e.x, e.y, 40f, Color.purple, 0.5f);
    }),
    singleOverloadFlashPurple = new Effect(30f, e -> {
        float alpha = 1f - e.finpow();
        float size = Mathf.lerp(3f, 6f, e.fin());
        Draw.color(Color.valueOf("a78bad").a(alpha));
        TextureRegion region = Core.atlas.find("epsilon-energy-1");
        Draw.rect(region, e.x, e.y, size, size);
        Draw.reset();
    }),
    energyOverloadPulse = new Effect(30f, e -> {
        if (e.time % 10f < Time.delta){
            for (int i = 0; i < 3; i++){
                float angle = Mathf.random(360f);
                float dist = Mathf.random(4f, 12f);
                float x = e.x + Angles.trnsx(angle, dist);
                float y = e.y + Angles.trnsy(angle, dist);
                singleOverloadFlashPurple.at(x, y);
            }
        }
    });
}