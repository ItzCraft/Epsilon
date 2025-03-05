package epsilon.world.meta;

import mindustry.world.meta.Stat;
import mindustry.world.meta.StatCat;

public class EpsStats {
  public static final Stat
          infection = new Stat("infection"),
          efficiency1 = new Stat("efficiency-1", StatCat.general), 
          efficiency2 = new Stat("efficiency-2", StatCat.general),
          efficiency3 = new Stat("efficiency-3", StatCat.general);
}
