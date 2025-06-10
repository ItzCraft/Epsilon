package epsilon.world.blocks.lore;

import arc.util.Time;
import epsilon.EpsilonVars;
import mindustry.gen.Building;
import mindustry.world.Block;

public class VarActivate extends Block {
    public int delayTime = 10;
    public boolean var = false;

    public VarActivate(String name){
        super(name);
    }

    public class VarActivateBuild extends Building{
        public int delayTimeB = delayTime;
        @Override
        public void updateTile(){
            delayTimeB -= Time.delta;
            if(delayTimeB < 0){
                var = true;
            }
        }
    }
}
