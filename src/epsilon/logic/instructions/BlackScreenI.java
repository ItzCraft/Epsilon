package epsilon.logic.instructions;

import epsilon.ui.EpsUi;
import mindustry.logic.*;
import mindustry.type.UnitType;

public class BlackScreenI implements LExecutor.LInstruction{
    public LVar duration;

    public BlackScreenI(LVar duration){
        this.duration = duration;
    }

    public BlackScreenI(){}

    @Override
    public void run(LExecutor exec){
        EpsUi.blackScreen(duration.numf());
    }
}
