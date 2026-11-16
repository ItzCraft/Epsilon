package epsilon;

import arc.*;
import arc.util.*;
import epsilon.content.Kallistea.blocks.*;
import epsilon.content.Kallistea.*;
import epsilon.gen.EntityRegistry;
import epsilon.graphics.EpsShaders;
import epsilon.ui.*;
import epsilon.ui.dialogs.AboutEpsilonDialog;
import epsilon.world.EpsAttribute;
import epsilon.logic.EpsilonLogic;
import mindustry.content.Blocks;
import mindustry.content.TechTree;
import mindustry.ctype.UnlockableContent;
import mindustry.game.EventType;
import mindustry.game.EventType.*;
import mindustry.gen.*;
import mindustry.mod.*;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import mindustry.type.Planet;
import mindustry.ui.dialogs.*;
import mindustry.world.blocks.logic.SwitchBlock;
import mindustry.world.meta.BuildVisibility;

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
            if(Core.settings.getBool("@settings.has-epsilon-data")){
                Time.runTask(10f, () -> {
                    BaseDialog dialog = new BaseDialog("Epsilon");
                    dialog.cont.add(bundle.get("warn-text1")).row();
                    dialog.cont.image(Core.atlas.find("epsilon-icon")).pad(20f).row();
                    dialog.cont.button("OK", dialog::hide).size(100f, 50f);
                    dialog.show();
                    Planet planet = kallistea;
                    for(TechTree.TechNode node : TechTree.all){
                        if(node.rootNode == kallistea.techTree){
                            node.reset();
                        }
                    }
                    content.each(c -> {
                        if(c instanceof UnlockableContent u && u.databaseTabs.contains(planet)){
                            u.clearUnlock();
                        }
                    });
                    planet.clearStats();
                    boolean any = false;
                    for(var sec : planet.sectors){
                        sec.clearInfo();
                        if(sec.save != null){
                            any = true;
                            sec.save.delete();
                            sec.save = null;
                        }
                    }
                    if(any){
                        planet.reloadMeshAsync();
                    }
                    for(var slot : control.saves.getSaveSlots().copy()){
                        if(slot.isSector() && slot.getSector().planet == planet){
                            slot.delete();
                        }
                    }
                });
                Core.settings.put("@settings.has-epsilon-data", false);
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
        EntityRegistry.register();

        modahh = mods.getMod(getClass());
        modahh.meta.displayName = Core.bundle.get("mod.epsilon.name");
        modahh.meta.author = Core.bundle.get("mod.epsilon.author");
        modahh.meta.description = Core.bundle.get("mod.epsilon.description");
        modahh.meta.subtitle = Core.bundle.get("mod.epsilon.subtitle");

        Blocks.worldSwitch = new SwitchBlock("world-switch"){{
            requirements(Category.logic, BuildVisibility.worldProcessorOnly, ItemStack.with(new Object[0]));
            targetable = false;
            privileged = true;
            ignoreBuildDarkness = true;
        }};
        
        EpsMusic.load();
        EpsTeams.load();
        EpsAttribute.load();
        SchematicsLoader.load();
        KallisteaItems.load();
        KallisteaUnitTypes.load();
        KallisteaWeather.load();
        EntityRegistry.registerUnits();
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

    private void loadSettings(){
        ui.settings.addCategory(bundle.get("settings.epsilon-title"), Icon.book, t -> {
            t.checkPref("@settings.testing-mode", false);
            t.checkPref("@settings.hide-warn-dialog", false);
            t.checkPref("@settings.detailed-solar-system",false);
        });
    }
}
