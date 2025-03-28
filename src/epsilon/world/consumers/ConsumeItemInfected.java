package epsilon.world.consumers;

import arc.util.Nullable;
import mindustry.world.consumers.ConsumeItemFilter;
import mindustry.gen.*;
import mindustry.type.*;
import epsilon.world.EpsItem;

import static mindustry.Vars.content;

public class ConsumeItemInfected extends ConsumeItemFilter{
    public float minInfected;

    public ConsumeItemInfected(float minInfected){
        this.minInfected = minInfected;
        filter = epsitem -> epsitem.infection >= this.minInfected;
    }

    public ConsumeItemInfected(){
        this(0.25f);
    }

    @Override
    public @Nullable EpsItem getConsumed(Building build){
        for(int i = 0; i < content.items().size; i++){
            EpsItem item = (EpsItem) content.item(i);
            if(build.items.has(item) && this.filter.get(item)){
                return item;
            }
        }
        return null;
    }

    @Override
    public float efficiencyMultiplier(Building build){
        var epsitem = getConsumed(build);
        return epsitem == null ? 0f : epsitem.infection;
    }
}
