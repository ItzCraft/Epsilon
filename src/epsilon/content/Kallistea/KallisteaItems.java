package epsilon.content.Kallistea;

import arc.graphics.*;
import mindustry.type.Item;

public class KallisteaItems {
    public static Item
    Gelionyte, Calcite, Quartz, Fylion, Red sand, Magnetite, Tantalum, Wierdium;

    public static final Seq<Item> kallisteaItems = new Seq<>();

    public static void load(){
        Gelionyte = new Item("gelionyte", Color.valueOf("#e675ab")){{
            charge = 0.7f;
            cost = 0.65f;
        }};
        Calcite = new Item("calcite", Color.valueOf()){{
            
        }};
    }
}