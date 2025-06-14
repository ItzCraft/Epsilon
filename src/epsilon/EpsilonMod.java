package epsilon;

import arc.*;
import arc.util.*;
import epsilon.content.Kallistea.blocks.*;
import epsilon.content.Kallistea.*;
import epsilon.game.EpsObjectives;
import epsilon.ui.EpsStyles;
import epsilon.ui.dialogs.AboutEpsilonDialog;
import epsilon.ui.TeamsUI;
import epsilon.world.EpsAttribute;
import epsilon.logic.EpsilonLogic;
import mindustry.game.EventType.*;
import mindustry.io.TypeIO;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

import static arc.Core.bundle;
import static mindustry.Vars.ui;

public class EpsilonMod extends Mod{

    public EpsilonMod(){
        Events.on(ClientLoadEvent.class, e -> {
            loadESSD();
            loadSettings();
            if(!EpsilonVars.hideWarnDialog){
                Time.runTask(10f, () -> {
                    BaseDialog dialog = new BaseDialog("Epsilon");
                    dialog.cont.add(bundle.get("warn-text1")).row();
                    dialog.cont.image(Core.atlas.find("epsilon-crystal-f")).pad(20f).row();
                    dialog.cont.button("OK", dialog::hide).size(100f, 50f);
                    dialog.show();
                });
            };
        });
    }

    @Override
    public void init(){
        super.init();
        EpsilonVars.init();
        EpsStyles.load();
        TeamsUI.init();
    }
        
    @Override
    public void loadContent(){
        EpsTeams.load();
        EpsAttribute.load();
        KallisteaItems.load();
        KallisteaUnitTypes.load();
        KallisteaBlocks.load();
        EpsilonPlanets.load();
        KallisteaSectorPresets.load();
        Missions.load();
        KallisteaTechTree.load();
        EpsilonLogic.init();
    }

    private void loadESSD(){
        ui.menufrag.addButton(Core.bundle.get("epsilon-solar-system-database-title"), Icon.admin, () -> {
            AboutEpsilonDialog database = new AboutEpsilonDialog();
            database.show();
        });
    }

    private void loadSettings(){
        ui.settings.addCategory(bundle.get("settings.epsilon-title"), Icon.book, t -> {
            t.checkPref("hide-warn-dialog", false);
            t.checkPref("detailed-solar-system",false);
        });
    }

}