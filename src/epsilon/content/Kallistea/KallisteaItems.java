package epsilon.content.Kallistea;

import arc.graphics.*;
import mindustry.type.Item;
import epsilon.world.

public class KallisteaItems {
    public static Item
    Gelionyte, Calcite, Quartz, Fylion, redSand, Magnetite, Tantalum, Wierdium;

    public static final Seq<Item> kallisteaItems = new Seq<>();

    public static void load(){
        Gelionyte = new Item("gelionyte", Color.valueOf("#e675ab")){{
            charge = 0.7f;
            cost = 0.65f;
            hardness = 4;
        }};
        Calcite = new Item("calcite", Color.valueOf("#b59d3c")){{
            flammability = 0.3f;
            cost = 0.85f;
            hardness = 1;
        }};
        Quartz = new Item("quartz", Color.valueOf("#87b6c9")){{
            charge = 0.2f;
            hardness = 2;
        }};
        Fylion = new Item("fylion", Color.valueOf("56c772")){{
            charge = 0.13f;
            flammability = 0.19f;
        }};
        redSand = new Item("red-sand", Color.valueOf("#c76a28")){{
            flammability = 0.1f;
            hardness = 2;
        }};
    }
}