package epsilon;

import arc.Core;
import arc.struct.Seq;
import epsilon.audio.EpsSoundControl;
import epsilon.cutscenes.*;
import epsilon.ui.dialogs.EpsEffectsDialog;

import static mindustry.Vars.control;

public class EpsilonVars{
    //will the warn dialog be displayed
    public static boolean  hideWarnDialog = Core.settings.getBool("hide-warn-dialog");

    // if false, only kallistea and epsilon will appear 
    public static boolean  detailedSolarSystem = Core.settings.getBool("detailed-solar-system");

    // NOTE: THIS IS FOR SECTORS ONLY DO NOT TRY TO USE IT ANYWHERE ELSE
    private static final Seq<String> sectorVars = Seq.with(
            "test",
            "testwarning"
    );
    public static boolean getVarForSector(String name){
        return Core.settings.getBool(name);
    }
    public static void setVarForSector(String name){
        Core.settings.put(name, true);
        Core.settings.manualSave();
    }

    public static String musicName;


    public static EpsCore core;
    public static CutsceneControl cutsceneControl;
    public static CutsceneUI cutscene;
    public static EpsEffectsDialog epsEffectsDialog;

    public static void init(){
        cutsceneControl = new CutsceneControl();
        cutscene = new CutsceneUI();
        core = new EpsCore();
        epsEffectsDialog = new EpsEffectsDialog();
        Core.app.addListener(core);
        for(String var : sectorVars){
            if(!Core.settings.has(var)){
                Core.settings.put(var, false);
            }
        }
        control.sound = new EpsSoundControl();
    }
}
