package epsilon.world.consumers;

import mindustry.world.consumers.ConsumeItemEfficiency;
import mindustry.type.*;
import epsilon.world.EpsItem;

public class ConsumeItemInfected extends ConsumeItemEfficiency{
    public float minInfected;

    public ConsumeItemInfected(float minInfected){
        this.minInfected = minInfected;
        filter = epsitem -> epsitem.infection >= this.minInfected;
    }

    public ConsumeItemInfected(){
        this(0.25f);
    }

    @Override
    public float itemEfficiencyMultiplier(EpsItem item){
        return item.infection;
    }
}
