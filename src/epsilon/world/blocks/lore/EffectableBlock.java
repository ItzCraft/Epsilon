package epsilon.world.blocks.lore;

import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.meta.Stat;

public class EffectableBlock extends GenericCrafter{

    public float radius = 5f;
    // turret's range boost mult aka 0.5=+50%
    public float boostMult = 0.5f;

    public EffectableBlock(String name){
        super(name);
    }
    @Override
    public void setStats(){
        super.setStats();
        stats.remove(Stat.input);
        stats.remove(Stat.output);
        stats.remove(Stat.productionTime);
    }


}
