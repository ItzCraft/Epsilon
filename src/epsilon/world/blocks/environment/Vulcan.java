package epsilon.world.blocks.environment;

import arc.graphics.*;
import arc.graphics.g2d.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.*;
import mindustry.*;
import mindustry.content.*;
import mindustry.entities.*;
import mindustry.world.*;
import mindustry.world.blocks.environment.*;
import static mindustry.Vars.*;

public class Vulcan extends Floor {
    public static boolean mini = false;
    public static final Point2[] offsetsSmall = {
            new Point2(0, 0),
            new Point2(1, 0),
            new Point2(1, 1),
            new Point2(0, 1)
    };
    public static final Point2[] offsetsBig = {
            new Point2(0, 0),
            new Point2(1, 0),
            new Point2(1, 1),
            new Point2(0, 1),
            new Point2(-1, 1),
            new Point2(-1, 0),
            new Point2(-1, -1),
            new Point2(0, -1),
            new Point2(1, -1),
    };

    public Block parent = Blocks.air;
    public Effect effect = Fx.none;
    public Color effectColor = Color.white;
    public float effectSpacing = 15f;

    static {
        for (var p : mini ? offsetsBig : offsetsSmall) {
            p.sub(1, 1);
        }
    }

    public Vulcan(String name, int variants) {
        super(name, variants);
        this.variants = variants;
    }

    public Vulcan(String name) {
        super(name);
    }

    public Point2[] getOffsets() {
        Point2[] base = mini ? offsetsSmall : offsetsBig;
        Point2[] shifted = new Point2[base.length];

        for (int i = 0; i < base.length; i++) {
            int cx = mini ? 0 : 1;
            int cy = mini ? 0 : 1;
            shifted[i] = new Point2(base[i]).sub(cx, cy);
        }

        return shifted;
    }


    @Override
    public void drawBase(Tile tile) {
        parent.drawBase(tile);

        if (checkAdjacent(tile)) {
            Mathf.rand.setSeed(tile.pos());
            TextureRegion region = variantRegions[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variantRegions.length - 1))];
            Draw.rect(region, tile.worldx() - tilesize, tile.worldy() - tilesize);
        }
    }


    @Override
    public boolean updateRender(Tile tile) {
        return checkAdjacent(tile);
    }

    @Override
    public void renderUpdate(UpdateRenderState state) {
        if (state.tile.block() == Blocks.air && (state.data += Time.delta) >= effectSpacing) {
            effect.at(state.tile.x * tilesize - tilesize, state.tile.y * tilesize - tilesize, effectColor);
            state.data = 0f;
        }
    }

    public boolean checkAdjacent(Tile tile) {
        Point2[] offsets = mini ? offsetsBig : offsetsSmall;
        for (var point : offsets) {
            int dx = tile.x + point.x - 1;
            int dy = tile.y + point.y - 1;
            Tile other = Vars.world.tile(dx, dy);
            if (other == null || other.floor() != this) {
                return false;
            }
        }
        return true;
    }
}