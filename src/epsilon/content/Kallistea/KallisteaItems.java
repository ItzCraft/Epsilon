package epsilon.content.Kallistea;

import arc.graphics.*;
import arc.struct.*;
import mindustry.type.*;
import epsilon.world.EpsItem;

public class KallisteaItems {
    public static Item
    gelionyte, calcite, quartz, fylion, redSand, magnetite, tantalum, anveiur;
    public static Liquid
    spoiledWater;

    public static final Seq<Item> kallisteaItems = new Seq<>();

    public static void load(){
        gelionyte = new Item("item-gelionyte", Color.valueOf("#e675ab")){{
            charge = 0.7f;
            cost = 0.65f;
            hardness = 4;
            frames = 18;
            frameTime = 4f;
        }};
        calcite = new Item("item-calcite", Color.valueOf("ebebb2")){{
            flammability = 0.3f;
            cost = 0.85f;
            hardness = 1;
        }};
        quartz = new Item("item-quartz", Color.valueOf("#87b6c9")){{
            charge = 0.2f;
            hardness = 2;
        }};
        fylion = new Item("item-fylion", Color.valueOf("56c772")){{
            charge = 0.13f;
            flammability = 0.19f;
        }};
        redSand = new Item("item-red-sand", Color.valueOf("#c76a28")){{
            flammability = 0.1f;
            hardness = 2;
        }};
        magnetite = new EpsItem("item-magnetite", Color.valueOf("#346391")){{
            explosiveness = 0.4f;
            radioactivity = 0.05f;
            hardness = 3;
            infection = 0.7f;
        }};
        tantalum = new EpsItem("item-tantalum", Color.valueOf("#8d60cc")){{
            charge = 0.05f;
            flammability = 0.08f;
            explosiveness = 0.2f;
            cost = 0.75f;
            infection = 0.2f;
        }};
        anveiur = new Item("item-anveiur", Color.valueOf("#82b394")){{
            charge = 0.07f;
            flammability = 0.24f;
            explosiveness = 0.45f;
        }};
        spoiledWater = new Liquid("liquid-spoiled-water", Color.valueOf("8462e8")){{
            barColor = Color.valueOf("8462e8");
            boilPoint = 0.5f;
            viscosity = 0.35f;
            heatCapacity = 0.2f;
            gasColor = Color.valueOf("5948ae");
        }};
        kallisteaItems.addAll(gelionyte, calcite, quartz, fylion, redSand, magnetite, tantalum, anveiur);
    }
}