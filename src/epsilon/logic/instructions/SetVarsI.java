package epsilon.logic.instructions;

import arc.util.Log;
import epsilon.EpsilonVars;
import mindustry.logic.LExecutor;
import mindustry.logic.LVar;


public class SetVarsI implements LExecutor.LInstruction{
    public LVar varName;

    public SetVarsI(LVar varName){
        this.varName = varName;
    }

    public SetVarsI(){}

    @Override
    public void run(LExecutor exec){
        if(varName.obj() instanceof String var){
            EpsilonVars.setVarForSector(var);
        }
    }
}
