package epsilon.content.Kallistea.blocks;

import mindustry.world.Block;
import mindustry.world.blocks.distribution.*;
import epsilon.content.Kallistea.KallisteaItems;

public class KallisteaDistribution{
    public static Block
    transmittingBridge, transmittingJunction, transmittingRouter, transmittingSorter, transmittingOverflowGate, transmittingUnderflowGate;

    public static void load(){
        transmittingBridge = new DuctBridge("transmitting-bridge"){{
            requirements(Category.distribution, ItemStack.with(KallisteaItems.calcite, 1));
            size = 1;
            health = 20;
            speed = 2;
            itemCapacity = 3;
        }};
    }
}