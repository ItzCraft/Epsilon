package epsilon.world;

import mindustry.type.Item;
import epsilon.world.meta.EpsStats;

public class EpsItem extends Item {
  public float Infection;
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
