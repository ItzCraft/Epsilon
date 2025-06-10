package epsilon.world;

import mindustry.type.Item;
import mindustry.world.meta.Stat;

public class Mission extends Item {
    public Mission(String name){
        super(name);
    }
    @Override
    public void setStats(){
        stats.remove(Stat.charge);
        stats.remove(Stat.explosiveness);
        stats.remove(Stat.radioactivity);
        stats.remove(Stat.flammability);
    }
}
