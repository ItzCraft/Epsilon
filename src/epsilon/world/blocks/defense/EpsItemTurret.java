package epsilon.world.blocks.defense;

import epsilon.world.meta.EpsStats;
import mindustry.world.blocks.defense.turrets.ItemTurret;

public class EpsItemTurret extends ItemTurret{
    public String fraction = "ganieris";

    public EpsItemTurret(String name){
        super(name);
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(EpsStats.fraction, fraction);
    }
}
