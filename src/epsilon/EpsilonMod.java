package epsilon;

import arc.*;
import arc.util.*;
import epsilon.content.Kallistea.blocks.*;
import epsilon.content.Kallistea.*;
import epsilon.graphics.EpsShaders;
import epsilon.ui.*;
import epsilon.ui.dialogs.AboutEpsilonDialog;
import epsilon.world.EpsAttribute;
import epsilon.logic.EpsilonLogic;
import mindustry.content.TechTree;
import mindustry.game.EventType;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.ui.dialogs.*;

import static arc.Core.app;
import static arc.Core.bundle;
import static epsilon.EpsilonVars.*;
import static epsilon.content.Kallistea.EpsilonPlanets.kallistea;
import static mindustry.Vars.*;

public class EpsilonMod extends Mod{
    public static Mods.LoadedMod modahh;

    public EpsilonMod(){
        Events.on(ClientLoadEvent.class, e -> {
            if(testingMode){loadESSD();}
            loadSettings();
            if(!EpsilonVars.hideWarnDialog){
                Time.runTask(10f, () -> {
                    BaseDialog dialog = new BaseDialog("Epsilon");
                    dialog.cont.add(bundle.get("warn-text1")).row();
                    dialog.cont.image(Core.atlas.find("epsilon-icon")).pad(20f).row();
                    dialog.cont.button("OK", dialog::hide).size(100f, 50f);
                    dialog.show();
                });
            }
        });

        Events.on(EventType.FileTreeInitEvent.class, e ->
                app.post(EpsShaders::load)
        );
    }

    @Override
    public void init(){
        super.init();
        EpsStyles.load();
        EpsilonVars.init();
        TeamsUI.init();
    }
        
    @Override
    public void loadContent(){

        modahh = mods.getMod(getClass());
        modahh.meta.displayName = Core.bundle.get("mod.epsilon.name");
        modahh.meta.author = Core.bundle.get("mod.epsilon.author");
        modahh.meta.description = Core.bundle.get("mod.epsilon.description");
        modahh.meta.subtitle = Core.bundle.get("mod.epsilon.subtitle");
        
        EpsMusic.load();
        EpsTeams.load();
        EpsAttribute.load();
        KallisteaItems.load();
        KallisteaUnitTypes.load();
        KallisteaBlocks.load();
        EpsilonPlanets.load();
        KallisteaSectorPresets.load();
        KallisteaTechTree.load();
        EpsilonLogic.init();
    }

    private void loadESSD(){
        ui.menufrag.addButton(Core.bundle.get("epsilon-solar-system-database-title"), Icon.admin, () -> {
            AboutEpsilonDialog database = new AboutEpsilonDialog();
            database.show();
        });
    }

    private void resetTree(TechTree.TechNode root) {
        root.reset();
        root.content.clearUnlock();
    }

    private void loadSettings(){
        ui.settings.addCategory(bundle.get("settings.epsilon-title"), Icon.book, t -> {
            t.checkPref("@settings.testing-mode", false);
            if(testingMode){
                t.button("@settings.epsilon-tech-tree", Icon.tree, () -> {
                    ui.showConfirm("@confirm", "@settings.epsilon-tech-tree.confirm", () -> resetTree(kallistea.techTree));
                });
            }
            t.checkPref("@settings.hide-warn-dialog", false);
            t.checkPref("@settings.detailed-solar-system",false);
        });
    }
}
