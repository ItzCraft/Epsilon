package epsilon.world.consumers;

import mindustry.world.consumers.ConsumeItemFilter;
import mindustry.gen.*;
import mindustry.type.*;
import epsilon.world.EpsItem;

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
    public float efficiencyMultiplier(Building build){
        var epsitem = getConsumed(build);
        return epsitem == null ? 0f : epsitem.infection;
    }
}
