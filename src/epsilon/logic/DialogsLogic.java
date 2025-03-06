package epsilon.logic;

import arc.graphics.*;
import mindustry.logic.*;
import epsilon.*;

public class DialogsLogic{
    public static LCategory dialogCategory;

    public static void init(){
        dialogCategory = new LCategory("Dialogs", Color.valueOf("eb347d"));

        DialogStatements.register();
    } 
}
