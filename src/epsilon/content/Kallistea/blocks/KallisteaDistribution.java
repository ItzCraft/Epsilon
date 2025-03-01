package epsilon.content.Kallistea.blocks;

import mindustry.world.Block;
import mindustry.world.blocks.distribution.*;
import mindustry.type.Category;
import mindustry.type.ItemStack;
import epsilon.content.Kallistea.KallisteaItems;

import static mindustry.type.ItemStack.with;

public class KallisteaDistribution{
    public static Block
    transmittingBridge, transmittingJunction, transmittingRouter, transmittingSorter, transmittingOverflowGate, transmittingUnderflowGate;

    public static void load(){
        transmittingBridge = new DuctBridge("transmitting-bridge"){{
            requirements(Category.distribution, with(KallisteaItems.calcite, 1));
            size = 1;
            health = 20;
            speed = 2;
            itemCapacity = 3;
        }};
        transmittingJunction = new Junction("transmitting-junction"){{
            requirements(Category.distribution, with(KallisteaItems.calcite, 2));
            size = 1;
            health = 15;
            speed = 3;
        }};
    }
}