package epsilon.content.Kallistea.blocks;

import arc.graphics.Color;
import arc.math.Interp;
import epsilon.content.Kallistea.EpsFx;
import epsilon.world.blocks.crafting.EpsilonHeatCrafter;
import mindustry.content.*;
import mindustry.entities.effect.*;
import mindustry.graphics.Layer;
import mindustry.world.Block;
import mindustry.world.blocks.production.*;
import mindustry.type.*;
import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.production.*;
import mindustry.world.draw.*;

import static mindustry.type.ItemStack.with;

public class KallisteaProduction{
    public static Block
    //drills
    pitMiningRig, breakerDrill, thermalDilatationDrill, materialBiter,
    //crafting
    fylionSmelter, tantalumSynthesizer, anveiurForge, sandFilter, ozoneChamber;

    public static void load(){
        //drills
        pitMiningRig = new Drill("pit-mining-rig"){{
            requirements(Category.production, with(KallisteaItems.gelionyte, 25, KallisteaItems.calcite, 20));
            health = 290;
            size = 3;
            drillTime = 1280;
            drillEffect = new MultiEffect(
                    Fx.drillSteam,
                    Fx.magmasmoke,
                    Fx.mineImpact
            );
            drillEffectChance = 0.05f;
            rotateSpeed = 1.25f;
            tier = 3;
            squareSprite = false;
            researchCostMultiplier = 0.1f;
        }};
        breakerDrill = new ConfigurableDrill("breaker-drill"){{
            requirements(Category.production, with(KallisteaItems.calcite, 145, KallisteaItems.quartz, 90, KallisteaItems.fylion, 70));
            health = 370;
            size = 4;
            drillTime = 1075;
            drillEffect = new MultiEffect(
                    Fx.drillSteam,
                    new WaveEffect(){{
                        sides = 4;
                        colorFrom = Color.valueOf("3c8249");
                        colorTo = Color.valueOf("a2dead");
                        sizeTo = 45;
                        interp = Interp.sineOut;
                    }},
                    Fx.greenCloud
            );
            drillEffectChance = 0.03f;
            rotateSpeed = 2;
            tier = 4;
            damageEff = 7;
            itemCapacity = 25;
            consumePower(1.33f);
            researchCostMultiplier = 0.25f;
        }};
        thermalDilatationDrill = new HeatDrill("thermal-dilatation-drill"){{
            requirements(Category.production, with(KallisteaItems.calcite, 210, KallisteaItems.quartz, 190, KallisteaItems.anveiur, 110, KallisteaItems.magnetite, 95));
            health = 550;
            size = 5;
            squareSprite = false;
            drillTime = 700;
            tier = 5;
            itemCapacity = 30;
            consumePower(2.5f);
            heatRequirement = 9;
        }};

        materialBiter = new BeamDrill("material-biter"){{
            requirements(Category.production, with(KallisteaItems.calcite, 150, KallisteaItems.fylion, 90, KallisteaItems.gelionyte, 10));
            health = 280;
            size = 3;
            squareSprite = false;
            drillTime = 90;
            range = 4;
            tier = 4;
            itemCapacity = 15;
            consumePower(3f);
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
            craftEffect = new ParticleEffect(){{
                particles = 10;
                lifetime = 600f;
                length = 700f;
                baseLength = 5f;
                sizeFrom = 4f;
                sizeTo = 0f;
                cone = 20f;
                baseRotation = 45f;
                colorFrom = Color.valueOf("709c9c");
                colorTo = Color.valueOf("417d7d");
                interp = Interp.pow2In;
                sizeInterp = Interp.pow2Out;
            }};
            outputItem = new ItemStack(KallisteaItems.fylion, 1);
            drawer = new DrawMulti(
                    new DrawDefault(),
                    new DrawRegion("-top"));
        }};
        tantalumSynthesizer = new GenericCrafter("tantalum-synthesizer"){{
            requirements(Category.crafting, with(KallisteaItems.calcite, 195, KallisteaItems.fylion, 150, KallisteaItems.magnetite, 90, KallisteaItems.gelionyte, 75));
            health = 685;
            size = 4;
            squareSprite = false;
            buildCostMultiplier = 0.6f;
            hasPower = true;
            hasLiquids = false;
            itemCapacity = 20;
            consumePower(3f);
            consumeItems(with(KallisteaItems.fylion, 1, KallisteaItems.redSand, 3, KallisteaItems.magnetite, 1));
            craftTime = 180f;
            updateEffect = EpsFx.purpleFire;
            updateEffectChance = 0.035f;
            craftEffect = new MultiEffect(
                    new RadialEffect() {{
                        amount = 6;
                        lifetime = 45f;
                        effect = new ParticleEffect(){{
                            strokeFrom = 0.6f;
                            strokeTo = 0f;
                            colorFrom = Color.valueOf("ff80ff");
                            colorTo = Color.valueOf("8000ff");
                            line = true;
                        }};
                    }},

                    new ParticleEffect() {{
                        particles = 8;
                        lifetime = 80f;
                        sizeFrom = 2f;
                        sizeTo = 0f;
                        lenFrom = 2f;
                        lenTo = 8f;
                        cone = 25f;
                        length = 40f;
                        baseRotation = 45;
                        colorFrom = Color.valueOf("a480bf");
                        colorTo = Color.valueOf("4b0082");
                        layer = Layer.flyingUnit + 0.1f;
                    }},
                    EpsFx.purpleSmoke
            );
            outputItem = new ItemStack(KallisteaItems.tantalum, 3);
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawDefault()
                    );
        }};
        anveiurForge = new EpsilonHeatCrafter("anveiur-forge"){{
           requirements(Category.crafting, with(KallisteaItems.calcite, 300, KallisteaItems.magnetite, 275, KallisteaItems.quartz, 195, KallisteaItems.tantalum, 40));
           health = 960;
           size = 5;
           squareSprite = false;
           buildCostMultiplier = 0.45f;
           hasPower = true;
           hasLiquids = true;
           itemCapacity = 15;
           consumePower(3.95f);
           consumeItem(KallisteaItems.fylion, 1);
           consumeLiquid(Liquids.ozone, 1.3333f);
           heatRequirement = 60;
           maxEfficiency = 2f;
           craftTime = 90;
           outputItem = new ItemStack(KallisteaItems.anveiur, 1);
        }};
        sandFilter = new HeatCrafter("sand-filter"){{
           requirements(Category.crafting, with(KallisteaItems.calcite, 130, KallisteaItems.magnetite, 90, KallisteaItems.quartz, 50, KallisteaItems.fylion, 35));
           health = 450;
           size = 3;
           squareSprite = false;
           buildCostMultiplier = 0.65f;
           hasPower = true;
           hasLiquids = false;
           itemCapacity = 20;
           consumePower(1.5f);
           consumeItem(KallisteaItems.redSand, 3);
           heatRequirement = 35;
           maxEfficiency = 1.5f;
           craftTime = 110;
           outputItems = with(KallisteaItems.quartz, 1, KallisteaItems.magnetite, 1);
        }};
        ozoneChamber = new GenericCrafter("ozone-chamber"){{
           requirements(Category.crafting, with(KallisteaItems.calcite, 100, KallisteaItems.fylion, 85, KallisteaItems.gelionyte, 15));
           health = 530;
           size = 4;
           squareSprite = false;
           buildCostMultiplier = 0.35f;
           hasPower = true;
           hasLiquids = true;
           liquidCapacity = 100f;
           consumePower(3.7f);
           craftTime = 100f;
           liquidOutputDirections = new int[]{1, 3};
           outputLiquid = new LiquidStack(Liquids.ozone, 0.7f);
        }};
    } 
}