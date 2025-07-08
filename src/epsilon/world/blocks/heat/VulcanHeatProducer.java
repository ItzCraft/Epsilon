package epsilon.world.blocks.heat;

import arc.Core;
import arc.math.Mathf;
import epsilon.world.blocks.environment.Vulcan;
import mindustry.content.Blocks;
import mindustry.graphics.*;
import mindustry.world.Tile;
import mindustry.world.blocks.heat.HeatProducer;

import static mindustry.Vars.*;

public class VulcanHeatProducer extends HeatProducer{
    public int range = 10;
    public int limitVulcans = 5;
    public boolean displayEfficiency = true;
    public VulcanHeatProducer(String name){
        super(name);
    }

    @Override
    public void drawPlace(int x, int y, int rotation, boolean valid) {
        super.drawPlace(x, y, rotation, valid);
        int ox = x;
        int oy = y;

        x *= tilesize;
        y *= tilesize;
        x += offset;
        y += offset;

        Drawf.dashSquare(Pal.accent, x, y, range * tilesize);
        int bcount = 0;
        int frange = (int) Math.floor(range / 2);
        for (int xm = -frange + 1; xm <= frange; xm++) {
            for (int ym = -frange + 1; ym <= frange; ym++) {
                Tile other = world.tile(ox + xm, oy + ym);
                if (other.floor()
                        instanceof Vulcan &&
                        !((xm > -1 && ym > -1) && (xm < size && ym < size))
                ) {
                    Drawf.selected(other.x, other.y, Blocks.router, Pal.lightOrange);
                    bcount++;
                }
            }
        }
        if(displayEfficiency){
            drawPlaceText(Core.bundle.formatFloat("bar.efficiency", 1 * 100, 1), x, y, valid);
        }
    }

    public class VulcanHeatProducerBuild extends HeatProducerBuild{
        // thanks thrster for code
        public int eachTile(int range){
            int rcount = 0;
            int frange = (int) Math.floor(range/2);
            for(int xm = -frange+1;xm<=frange;xm++){
                for(int ym = -frange+1;ym<=frange;ym++){
                    Tile other = tile.nearby(xm,ym);
                    if(other.floor() instanceof Vulcan) {
                        rcount++;
                    }
                }
            }
            return rcount-size*size;
        }

        public int calculateCount(int count){
            int value = Mathf.floor( 1 + Mathf.sin((float) ((count/-9)*Mathf.pi+(1.3*(count/Mathf.pi))-2)));
            return Math.max(value, 1);
        }

        @Override
        public void updateTile(){
            super.updateTile();
            int count = Math.min(calculateCount(eachTile(range)), limitVulcans);
            this.heat = Mathf.approachDelta(this.heat, VulcanHeatProducer.this.heatOutput * this.efficiency * count, VulcanHeatProducer.this.warmupRate * this.delta());
        }

        @Override
        public void drawSelect(){
            super.drawSelect();

            Drawf.dashSquare(Pal.accent, x, y, range * tilesize);
            int frange = (int) Math.floor(range/2);
            for(int xm = -frange+1;xm<=frange;xm++){
                for(int ym = -frange+1;ym<=frange;ym++){
                    Tile other = tile.nearby(xm,ym);
                    if(other.floor() instanceof Vulcan) {
                        Drawf.selected(other.x, other.y, Blocks.router, Pal.lightOrange);
                    }
                }
            }
        }
    }
}
