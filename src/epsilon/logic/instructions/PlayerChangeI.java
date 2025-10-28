package epsilon.logic.instructions;

import epsilon.ui.EpsUi;
import mindustry.gen.Unit;
import mindustry.logic.LExecutor;
import mindustry.logic.LExecutor.LInstruction;
import mindustry.logic.LVar;
import mindustry.type.UnitType;

import static mindustry.Vars.player;

public class PlayerChangeI implements LInstruction {
    public LVar unitVar;

    public PlayerChangeI(LVar unitVar){
        this.unitVar = unitVar;
    }

    public PlayerChangeI(){}

    @Override
    public void run(LExecutor exec){;
        if(unitVar.obj() instanceof UnitType type){
            Unit unit = type.spawn(player.team(), player.x, player.y, 90);
            player.unit(unit);
        }
    }
}