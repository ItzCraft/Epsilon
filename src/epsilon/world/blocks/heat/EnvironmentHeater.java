package epsilon.world.blocks.heat;

import arc.graphics.Color;
import arc.math.Mathf;
import arc.struct.EnumSet;
import arc.util.Tmp;
import arc.util.io.*;
import epsilon.content.Kallistea.blocks.KallisteaEnv;
import mindustry.world.Tile;
import mindustry.graphics.Drawf;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.draw.*;
import mindustry.world.meta.*;
import mindustry.world.blocks.heat.HeatBlock;

import static mindustry.Vars.*;
import static mindustry.Vars.player;

public class EnvironmentHeater extends GenericCrafter{
    public float heatOutput = 10f;
    public float warmupRate = 0.15f;
    public int range = 5;
    protected float count;

    public EnvironmentHeater(String name){
        super(name);
        drawer = new DrawMulti(new DrawDefault(), new DrawHeatOutput());
        rotateDraw = false;
        rotate = true;
        canOverdrive = false;
        drawArrow = true;
        flags = EnumSet.of();
    }
    @Override
    public void drawOverlay(float x, float y, int rotation) {
        if (range < 1) return;
        Drawf.dashSquare(Color.acid, x, y, (size + range * 2) * tilesize);
    }
    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid){
        super.drawPlace(x, y, rotation, valid);

        Drawf.dashCircle(x * tilesize + offset, y * tilesize + offset, range, Color.crimson);

        indexer.eachBlock(player.team(), x * tilesize + offset, y * tilesize + offset, range, other -> true, other -> Drawf.selected(other, Tmp.c1.set(Color.crimson).a(Mathf.absin(4f, 1f))));
    }
    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.output, heatOutput, StatUnit.heatUnits);
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("heat", (EnvironmentHeaterBuild entity) -> new Bar("bar.heat", Pal.lightOrange, () -> entity.heat / heatOutput));
    }

    public float countVulcans(int tx, int ty){
        for(int ax = tx - range; ax < tx + range + size; ax++){
            for(int ay = ty - range; ay < ty + range + size; ay++){
                Tile t = world.tile(ax, ay);
                if(t == null){return 0;}
                if (t.block() == KallisteaEnv.miniVulcan) {
                    count++;
                }
            }
        }
        return count;
    }

    public class EnvironmentHeaterBuild extends GenericCrafterBuild implements HeatBlock{
        public float heat;

        @Override
        public void updateTile(){
            super.updateTile();
            if(countVulcans(tile.x, tile.y)>0) heat = Mathf.approachDelta(heat, heatOutput * efficiency, warmupRate * delta());
        }

        @Override
        public float heatFrac(){
            return heat / heatOutput;
        }

        @Override
        public float heat(){
            return heat;
        }

        @Override
        public void write(Writes write){
            super.write(write);
            write.f(heat);
        }

        @Override
        public void read(Reads read, byte revision){
            super.read(read, revision);
            heat = read.f();
        }
    }
}