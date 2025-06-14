package epsilon;

import arc.Core;
import arc.struct.ObjectMap;
import arc.struct.Seq;
import epsilon.content.Kallistea.EpsTeams;
import epsilon.cutscenes.*;
import mindustry.Vars;

public class EpsilonVars{
    private static final Seq<String> sectorVars = Seq.with(
      "test"
    );
    //will the warn dialog be displayed
    public static boolean  hideWarnDialog = Core.settings.getBool("hide-warn-dialog");

    // if false, only kallistea and epsilon will appear 
    public static boolean  detailedSolarSystem = Core.settings.getBool("detailed-solar-system");

    // NOTE: THIS IS FOR OBJECTIVES ONLY DO NOT TRY TO USE IT ANYWHERE ELSE
    public static boolean getVarForSector(String name){
        return Core.settings.getBool(name);
    }
    public static void setVarForSector(String name){
        Core.settings.put(name, true);
        Core.settings.manualSave();
    }



    public static EpsCore core;
    public static CutsceneControl cutsceneControl;
    public static CutsceneUI cutscene;

    public static void init(){
        cutsceneControl = new CutsceneControl();
        cutscene = new CutsceneUI();
        core = new EpsCore();
        Core.app.addListener(core);
        for(String var : sectorVars){
            if(!Core.settings.has(var)){
                Core.settings.put(var, false);
            }
        }
    }
}
