package epsilon.world;

import arc.graphics.Color;
import epsilon.world.meta.EpsStats;
import mindustry.type.Item;

public class EpsItem extends Item{
  public float infection;
  public EpsItem(String name, Color color){
    super(name);
    this.color = color;
  }

  @Override
  public void setStats(){
    super.setStats();
        stats.addPercent(EpsStats.infection, infection);
  }
}
