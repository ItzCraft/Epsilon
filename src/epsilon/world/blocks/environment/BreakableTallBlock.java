package epsilon.world.blocks.environment;

import mindustry.world.blocks.environment.TallBlock;

public class BreakableTallBlock extends TallBlock{
      public BreakableTallBlock(String name){
          super(name);
          breakable = true;
          breakEffect = Fx.breakProp;
          breakSound = Sounds.rockBreak;
      }
}
