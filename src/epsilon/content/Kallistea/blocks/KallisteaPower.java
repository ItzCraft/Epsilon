package epsilon.content.Kallistea.blocks;

import arc.graphics.Color;
import epsilon.content.Kallistea.KallisteaItems;
import mindustry.entities.effect.ParticleEffect;
import mindustry.type.Category;
import mindustry.world.Block;
import mindustry.world.blocks.power.ConsumeGenerator;
import mindustry.world.blocks.power.HeaterGenerator;
import mindustry.world.blocks.power.PowerDiode;
import mindustry.world.blocks.power.PowerNode;
import mindustry.world.draw.DrawDefault;
import mindustry.world.draw.DrawMulti;
import mindustry.world.draw.DrawRegion;

import static mindustry.type.ItemStack.with;

public class KallisteaPower{
    public static Block quartzGenerator, thermalGenerator, gelionyteConsumer, quartzNode, energyCapsule, quartzDiode;

    public static void load(){
        quartzGenerator = new ConsumeGenerator("quartz-generator"){{
            requirements(Category.power, with(KallisteaItems.gelionyte, 40, KallisteaItems.calcite, 35));
            health = 90;
            size = 2;
            powerProduction = 45f/60f;
            squareSprite = true;
            consumeItem(KallisteaItems.quartz, 1);
            itemDuration = 45f;
            consumeEffect = new ParticleEffect(){{
               lifetime = 30;
               length = 15;
               sizeFrom = 3.5f;
               sizeTo = 0;
               spin = 5f;
               colorFrom = Color.valueOf("effdff");
               colorTo = Color.valueOf("8b9cd3");
            }};
            drawer = new DrawMulti(
                    new DrawRegion("-bottom"),
                    new DrawRegion("-rotator"){{
                      rotateSpeed = 1.25f;
                    }},
                    new DrawDefault()
            );
        }};

        thermalGenerator = new HeaterGenerator("thermal-generator"){{
           requirements(Category.power, with(KallisteaItems.calcite, 80, KallisteaItems.fylion, 45, KallisteaItems.magnetite, 30));
           health = 200;
           size = 3;
           powerProduction = 250f/60f;
           heatOutput = 40f;

        }};

        quartzNode = new PowerNode("quartz-node"){{
           requirements(Category.power, with(KallisteaItems.quartz, 3, KallisteaItems.calcite, 1));
           health = 30;
           size = 1;
           maxNodes = 5;
           maxRange = 10f;
           laserColor1 = Color.valueOf("83a5c9");
           laserColor2 = Color.valueOf("505e6e");
        }};

        quartzDiode = new PowerDiode("quartz-diode"){{
           requirements(Category.power, with(KallisteaItems.calcite, 40, KallisteaItems.quartz, 16, KallisteaItems.gelionyte, 4));
           health = 50;
           size = 1;
        }};
    }
}
