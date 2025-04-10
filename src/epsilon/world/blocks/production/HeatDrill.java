package epsilon.world.blocks.production;


import arc.Core;
import arc.math.Mathf;
import arc.util.Strings;
import mindustry.graphics.Pal;
import mindustry.ui.Bar;
import mindustry.world.blocks.production.*;
import mindustry.world.meta.*;

public class HeatDrill extends BurstDrill{
    /** Base heat requirement for 100% efficiency. */
    public float heatRequirement = 13f;
    /** After heat meets this requirement, excess heat will be scaled by this number.*/
    public float overheatScale = 1f;
    /** Maximum possible efficiency after overheat. */
    public float maxEfficiency = 2f;

    public HeatDrill(String name){
        super(name);
    }

    @Override
    public void setBars(){
        super.setBars();
        removeBar("drillspeed");
        addBar("drillspeed", (HeatDrillBuild e) ->
                new Bar(() -> Core.bundle.format("bar.drillspeed", Strings.fixed(e.lastDrillSpeed * 60 * e.timeScale() * Math.min(1, e.heat / heatRequirement), 2)), () -> Pal.ammo, () -> e.warmup));
        addBar("heat", (HeatDrillBuild e) ->
                new Bar(() -> Core.bundle.format("bar.heatpercent", (int)(e.heat + 0.01f), (int)(e.getHeatAmount() * 100)), () -> Pal.lightOrange, () -> e.heat / heatRequirement));
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(Stat.input, heatRequirement, StatUnit.heatUnits);
    }

    public class HeatDrillBuild extends BurstDrill.BurstDrillBuild{
        public float[] sideHeat = new float[4];
        public float heat = 0f;

        @Override
        public void updateTile(){
            heat = calculateHeat(sideHeat);
            if(timer(timerDump, dumpTime)){
                dump(dominantItem != null && items.has(dominantItem) ? dominantItem : null);
            }

            if(dominantItem == null){
                return;
            }

            timeDrilled += warmup * delta();

            float delay = getDrillTime(dominantItem);

            if(items.total() < itemCapacity && dominantItems > 0 && efficiency > 0){
                float speed = Mathf.lerp(1f, liquidBoostIntensity, optionalEfficiency) * efficiency * Math.min(1, heat / heatRequirement);

                lastDrillSpeed = (speed * dominantItems * warmup) / delay;
                warmup = Mathf.approachDelta(warmup, speed, warmupSpeed);
                progress += delta() * dominantItems * speed * warmup * Math.min(1, heat / heatRequirement);

                if(Mathf.chanceDelta(updateEffectChance * warmup))
                    updateEffect.at(x + Mathf.range(size * 2f), y + Mathf.range(size * 2f));
            }else{
                lastDrillSpeed = 0f;
                warmup = Mathf.approachDelta(warmup, 0f, warmupSpeed);
                return;
            }

            if(dominantItems > 0 && progress >= delay && items.total() < itemCapacity){
                offload(dominantItem);

                progress %= delay;

                if(wasVisible && Mathf.chanceDelta(updateEffectChance * warmup)) drillEffect.at(x + Mathf.range(drillEffectRnd), y + Mathf.range(drillEffectRnd), dominantItem.color);
            }

            super.updateTile();
        }

        public float getHeatAmount(){
            if((heat / heatRequirement) <= 1){
                return (heat / heatRequirement);
            }
            else return 1;
        }
    }
}