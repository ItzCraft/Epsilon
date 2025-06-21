package epsilon.content.Kallistea;

import arc.graphics.Color;
import arc.graphics.g2d.*;
import arc.math.*;
import mindustry.entities.Effect;
import mindustry.gen.Bullet;
import mindustry.graphics.*;

import static arc.graphics.g2d.Draw.*;
import static arc.graphics.g2d.Lines.*;
import static arc.math.Angles.randLenVectors;

public class EpsFx{
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
    plasmaCharge = new Effect(140f, e -> {
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
    prismBurst = new Effect(25f, e -> {
        Draw.color(Color.purple, Color.white, e.fin());
        for (int i = 0; i < 6; i++) {
            float angle = i * 60f + e.time * 6f;
            float len = 5f + Mathf.absin(e.time, 2f, 1f);
            Lines.lineAngle(e.x, e.y, angle, len);
        }
        Drawf.light(e.x, e.y, 40f, Color.purple, 0.5f);
    });
}