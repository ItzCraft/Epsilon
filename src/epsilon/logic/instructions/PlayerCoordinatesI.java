package epsilon.logic.instructions;

import mindustry.logic.LExecutor;
import mindustry.logic.LVar;

import static mindustry.Vars.player;

public class PlayerCoordinatesI implements LExecutor.LInstruction {
    public LVar x,y;

    public PlayerCoordinatesI(LVar x,LVar y){
        this.x = x;
        this.y = y;
    }

    public PlayerCoordinatesI(){}

    @Override
    public void run(LExecutor exec){
            //somewhy it works in WU
            player.unit().x = x.numf()*8;
            player.unit().y = y.numf()*8;
    }
}
