package epsilon.content.Kallistea;

import arc.struct.*;
import epsilon.EpsilonVars;
import epsilon.content.Kallistea.blocks.*;
import epsilon.game.EpsObjectives;
import mindustry.ctype.UnlockableContent;
import mindustry.game.Objectives;
import mindustry.type.Item;
import mindustry.type.ItemStack;

import static epsilon.content.Kallistea.KallisteaSectorPresets.*;
import static epsilon.world.meta.EpsStats.requirements;
import static mindustry.Vars.content;
import static mindustry.content.TechTree.*;

public class KallisteaTechTree {
    public static void load(){

            var costMultipliers = new ObjectFloatMap<Item>();
        for(var item : content.items()) costMultipliers.put(item, 0.9f);

        costMultipliers.put(KallisteaItems.gelionyte, 0.3f);

        EpsilonPlanets.kallistea.techTree = nodeRoot("kallistea", KallisteaStorage.coreObscurity, () -> {
            node(KallisteaItems.gelionyte, Seq.with(new Objectives.Research(KallisteaItems.gelionyte)), () -> {
               node(KallisteaItems.calcite, Seq.with(new Objectives.Produce(KallisteaItems.calcite)), () ->{
                  node(KallisteaItems.quartz, Seq.with(new Objectives.Produce(KallisteaItems.quartz)), () -> {
                        node(KallisteaItems.fylion, Seq.with(new Objectives.Produce(KallisteaItems.fylion)), () -> {
                            node(KallisteaItems.magnetite, Seq.with(new Objectives.Produce(KallisteaItems.magnetite)), () -> {
                               node(KallisteaItems.tantalum, Seq.with(new Objectives.Produce(KallisteaItems.tantalum)), () -> {
                                  node(KallisteaItems.anveiur, Seq.with(new Objectives.Produce(KallisteaItems.anveiur)), () -> {

                                  });
                               });
                            });
                        });
                   });
                  node(KallisteaItems.redSand, Seq.with(new Objectives.Produce(KallisteaItems.redSand)), () -> {});
               });
            });
            node(KallisteaDistribution.transmittingBridge, () -> {
                node(KallisteaDistribution.transmittingJunction, () -> {
                   node(KallisteaDistribution.transmittingRouter, () -> {
                      node(KallisteaDistribution.transmittingSorter);
                      node(KallisteaDistribution.transmittingOverflowGate, () -> {
                          node(KallisteaDistribution.transmittingUnderflowGate);
                      });
                   });
                });
            });
            node(KallisteaProduction.pitMiningRig, () -> {
                node(KallisteaProduction.breakerDrill);
            });
            node(KallisteaSectorPresets.firstRiddles, Seq.with(new Objectives.OnSector(firstRiddles)), () -> {

            });
            node(Missions.mission1, Seq.with(new Objectives.SectorComplete(firstRiddles)), () -> {

            });
        });
    }
}