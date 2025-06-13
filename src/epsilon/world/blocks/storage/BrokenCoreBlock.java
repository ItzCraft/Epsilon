package epsilon.world.blocks.storage;

import arc.util.*;
import mindustry.Vars;
import mindustry.world.blocks.storage.CoreBlock;
import mindustry.world.draw.DrawBlock;

public class BrokenCoreBlock extends CoreBlock {
    public float flameTime = 1200f;
    public @Nullable DrawBlock drawer;
    public BrokenCoreBlock(String name){
        super(name);
        emitLight = true;
    }
    // big tysm to thrster for helping me code that
    public class BrokenCoreBuild extends CoreBuild{
        @Override
        public float warmup(){
            return 1f;
        }
        @Override
        public void draw(){
            if(flameTime <= 0){
                super.draw();
            }else{
                drawer.draw(this);
                if(!Vars.state.isPaused()){
                    flameTime -= Time.delta;
                }
            }
        }
    }
}
