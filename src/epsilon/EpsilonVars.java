package epsilon;

import arc.Core;
import epsilon.cutscenes.*;

public class EpsilonVars{
    //will the warn dialog be displayed
    public static boolean  hideWarnDialog = Core.settings.getBool("hide-warn-dialog");

    public static EpsCore core;
    public static CutsceneControl cutsceneControl;
    public static CutsceneUI cutscene;

    public static void init(){
        cutsceneControl = new CutsceneControl();
        cutscene = new CutsceneUI();
        core = new EpsCore();
        Core.app.addListener(core);
    }
}
