package epsilon;

import arc.Core;
import epsilon.content.Kallistea.EpsTeams;
import epsilon.cutscenes.*;
import mindustry.Vars;

public class EpsilonVars{
    //will the warn dialog be displayed
    public static boolean  hideWarnDialog = Core.settings.getBool("hide-warn-dialog");

    // if false, only kallistea and epsilon will appear 
    public static boolean  detailedSolarSystem = Core.settings.getBool("detailed-solar-system");
    public static boolean mission1 = false;

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
