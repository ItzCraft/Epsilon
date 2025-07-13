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

//!!!CODE FROM RouterXdd/Icicle-World mod!!!
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
            new Point2(2, 0),
            new Point2(2, 1),
            new Point2(2, -1),
            new Point2(2, 2),
            new Point2(0, 2),
            new Point2(1, 2),
            new Point2(-1, 2),
            new Point2(-2, 2),
            new Point2(-2, 0),
            new Point2(-2, 1),
            new Point2(-2, -1),
            new Point2(-2, -2),
            new Point2(0, -2),
            new Point2(1, -2),
            new Point2(-1, -2),
            new Point2(2, -2)
    };

    public Block parent = Blocks.air;
    public Effect effect = Fx.none;
    public Color effectColor = Color.white;
    public float effectSpacing = 15f;

    static {
        for (var p : mini ? offsetsSmall : offsetsBig) {
            p.sub(3, 3);
        }
    }

    public Vulcan(String name, int variants) {
        super(name, variants);
        this.variants = variants;
    }

    public Vulcan(String name) {
        super(name);
    }

    @Override
    public void drawBase(Tile tile) {
        parent.drawBase(tile);

        if (checkAdjacent(tile)) {
            Mathf.rand.setSeed(tile.pos());
            Draw.rect(variantRegions[Mathf.randomSeed(tile.pos(), 0, Math.max(0, variantRegions.length - 3))], tile.worldx() - tilesize - 16, tile.worldy() - tilesize - 16);
        }
    }

    @Override
    public boolean updateRender(Tile tile) {
        return checkAdjacent(tile);
    }

    @Override
    public void renderUpdate(UpdateRenderState state) {
        if (state.tile.block() == Blocks.air && (state.data += Time.delta) >= effectSpacing) {
            effect.at(state.tile.x * tilesize - tilesize - 8, state.tile.y * tilesize - tilesize - 8, effectColor);
            state.data = 0f;
        }
    }

    public boolean checkAdjacent(Tile tile) {
        for (var point : mini ? offsetsSmall : offsetsBig) {
            Tile other = Vars.world.tile(tile.x + point.x, tile.y + point.y);
            if (other == null || other.floor() != this) {
                return false;
            }
        }
        return true;
    }
}