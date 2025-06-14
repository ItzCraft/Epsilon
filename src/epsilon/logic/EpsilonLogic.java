package epsilon.logic;

import arc.graphics.Color;
import epsilon.logic.statements.*;
import mindustry.gen.LogicIO;
import mindustry.logic.LAssembler;
import mindustry.logic.LCategory;

public class EpsilonLogic{
    public static LCategory epsilonCategory;

    public static void init(){
        epsilonCategory = new LCategory("epsilon-category", Color.valueOf("c270bc"));

        LAssembler.customParsers.put("playerchange", PlayerChange::new);
        LAssembler.customParsers.put("setvars", SetVars::new);

        LogicIO.allStatements.addUnique(PlayerChange::new);
        LogicIO.allStatements.addUnique(SetVars::new);
    }
}
