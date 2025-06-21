package epsilon.world.blocks.defense;

import epsilon.world.meta.EpsStats;
import mindustry.world.blocks.defense.turrets.PowerTurret;

public class EpsPowerTurret extends PowerTurret {
    public String fraction = "ganieris";

    public EpsPowerTurret(String name){
        super(name);
    }

    @Override
    public void setStats(){
        super.setStats();
        stats.add(EpsStats.fraction, fraction);
    }
}
