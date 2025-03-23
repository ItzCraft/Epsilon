package epsilon.content.Kallistea;

import arc.*;
import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.*;
import arc.math.geom.*;
import mindustry.entities.Effect;
import mindustry.graphics.Pal;

import static arc.graphics.g2d.Draw.rect;

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
    });
}