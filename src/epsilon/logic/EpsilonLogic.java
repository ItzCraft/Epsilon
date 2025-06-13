package epsilon.logic;

import mindustry.graphics.Pal;
import mindustry.logic.LCategory;

public class EpsilonLogic{
    public static LCategory epsilonCategory;

    public static void init(){
        epsilonCategory = new LCategory("epsilonCategory", Pal.gray);
    }
}
