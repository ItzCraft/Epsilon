package epsilon.content.Kallistea.blocks;

import mindustry.content.Fx;
import mindustry.world.Block;
import mindustry.world.blocks.production.*;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.production.*;

import static mindustry.type.ItemStack.with;

public class KallisteaProduction{
    public static Block
    //drills
    pitMiningRig, breakerDrill, thermalDilatationDrill,
    //crafting
    fylionSmelter, tantalumSynthesizer, anveiurForge, sandFilter;

    public static void load(){
        //drills
        pitMiningRig = new Drill("pit-mining-rig"){{
            requirements(Category.production, with(KallisteaItems.gelionyte, 25, KallisteaItems.calcite, 20));
            health = 290;
            size = 3;
            drillTime = 1280;
            rotateSpeed = 1.25f;
            tier = 3;
            squareSprite = false;
        }};
        breakerDrill = new ConfigurableDrill("breaker-drill"){{
            requirements(Category.production, with(KallisteaItems.calcite, 145, KallisteaItems.quartz, 90, KallisteaItems.fylion, 70));
            health = 590;
            size = 4;
            drillTime = 1075;
            rotateSpeed = 2;
            tier = 4;
            damageEff = 7;
            itemCapacity = 25;
            consumePower(1.33f);
        }};
        thermalDilatationDrill = new HeatDrill("thermal-dilatation-drill"){{
            requirements(Category.production, with(KallisteaItems.calcite, 210, KallisteaItems.quartz, 190, KallisteaItems.anveiur, 110, KallisteaItems.magnetite, 95));
            health = 700;
            size = 5;
            drillTime = 700;
            tier = 5;
            itemCapacity = 30;
            consumePower(2.5f);
            heatRequirement = 9;
        }};

        //crafting
        fylionSmelter = new GenericCrafter("fylion-smelter"){{
            requirements(Category.crafting, with(KallisteaItems.calcite, 120, KallisteaItems.quartz, 40));
            health = 400;
            size = 4;
            squareSprite = false;
            hasPower = true;
            hasLiquids = false;
            itemCapacity = 15;
            consumePower(1.2f);
            consumeItems(with(KallisteaItems.calcite, 2, KallisteaItems.quartz, 1));
            craftTime = 80f;
            craftEffect = Fx.fireSmoke; //im lazy to code it rn
            outputItem = new ItemStack(KallisteaItems.fylion, 1);
        }};
        tantalumSynthesizer = new GenericCrafter("tantalum-synthesizer"){{
            requirements(Category.crafting, with(KallisteaItems.calcite, 195, KallisteaItems.fylion, 150, KallisteaItems.magnetite, 90, KallisteaItems.gelionyte, 75));
            health = 685;
            size = 4;
            squareSprite = false;
            hasPower = true;
            hasLiquids = false;
            itemCapacity = 20;
            consumePower(3f);
            consumeItems(with(KallisteaItems.fylion, 1, KallisteaItems.redSand, 3, KallisteaItems.magnetite, 1));
            craftTime = 60f;
            craftEffect = Fx.massiveExplosion; //lazy to code rn
            outputItem = new ItemStack(KallisteaItems.tantalum, 1);
        }};
    } 
}
