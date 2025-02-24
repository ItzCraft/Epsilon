package epsilon.content.Kallistea;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.Item;
import epsilon.world.EpsItem;

public class KallisteaItems {
    public static Item
    gelionyte, calcite, quartz, fylion, redSand, magnetite, tantalum, wierdium;

    public static final Seq<Item> kallisteaItems = new Seq<>();

    public static void load(){
        gelionyte = new Item("gelionyte", Color.valueOf("#e675ab")){{
            charge = 0.7f;
            cost = 0.65f;
            hardness = 4;
        }};
        calcite = new Item("calcite", Color.valueOf("#b59d3c")){{
            flammability = 0.3f;
            cost = 0.85f;
            hardness = 1;
        }};
        quartz = new Item("quartz", Color.valueOf("#87b6c9")){{
            charge = 0.2f;
            hardness = 2;
        }};
        fylion = new Item("fylion", Color.valueOf("56c772")){{
            charge = 0.13f;
            flammability = 0.19f;
        }};
        redSand = new Item("red-sand", Color.valueOf("#c76a28")){{
            flammability = 0.1f;
            hardness = 2;
        }};
        magnetite = new EpsItem("magnetite", Color.valueOf("#346391")){{
            explosiveness = 0.4f;
            radioactivity = 0.05f;
            hardness = 3;
            infection = 0.7f;
        }};
        tantalum = new EpsItem("tantalum", Color.valueOf("8d60cc")){{
            charge = 0.05f;
            flammability = 0.08f;
            explosiveness = 0.2f;
            cost = 0.75f;
            infection = 0.2f;
        }};
        wierdium = Item("wierdium", Color.valueOf("82b394")){{
            charge = 0.07f;
            flammability = 0.24f;
            explosiveness = 0.45f;
        }};

        kallisteaItems.addAll(gelionyte, calcite, quartz, fylion, redSand, magnetite, tantalum, wierdium);
    }
}