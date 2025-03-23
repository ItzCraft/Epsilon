package epsilon.content.Kallistea.blocks;

import arc.graphics.Color;
import mindustry.world.Block;
import mindustry.world.blocks.environment.*;
import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.environment.*;
import mindustry.type.Category;
import mindustry.type.ItemStack;

import static mindustry.type.ItemStack.with;
import static epsilon.world.EpsAttribute.infection;


public class KallisteaEnv {
    public static Block
            crystalF, calciteOre, quartzOre,

            //purystal-biome
            purystalErodedFloor, purystalFloor, purystalLightFloor,
            purystalWall, purystalWallAlt, gelionyticWall,
            purystalBoulder, gelionyteChunk, gelionyteCluster, buahanBush, buahanTree,

            // eadstal biome
            eadstonFloor, eadstonLightFloor, eadstonRoughFloor, fensporReinforcedFloor, fensporStrandsFloor, fensporVent,
            eadstonWall, eadstonWallAlt, eadstonWallDeanytic, eadstalMagnetiteWall, eadstonWallFenspor,
            eadstonBoulder, eadstonFensporBoulder, eadstalDeanyteCluster, buahanDeadBush, buahanFensporDeadBush, buahanDeadTree, 

            // Merapora biome
            meraporaFloor, meraporaFensporFloor, meraporaWall, meraporaWallAlt, meraporaFensporWall, meraporaFensporWallAlt,
            meraporaBoulder, meraporaFensporBoulder, meraporaVent, meraporaFensporVent, meraporaBunker, meraporaMenhir;

    public static void load() {
        {
            {
                // testing blocks
                crystalF = new Floor("crystal-f", 0);

                // ores
                calciteOre = new OreBlock("calcite-ore", KallisteaItems.calcite){{variants = 6;}};
                quartzOre = new OreBlock("quartz-ore", KallisteaItems.quartz){{variants = 6;}}; 


                //PURYSTAL BIOME
                // floors
                purystalErodedFloor = new Floor("purystal-eroded-floor", 6);
                purystalFloor = new Floor("purystal-floor", 6);
                purystalLightFloor = new Floor("purystal-light-floor", 6);

                // walls
                purystalWall = new StaticWall("purystal-wall"){{variants = 4;
                    purystalErodedFloor.asFloor().wall = this; purystalFloor.asFloor().wall = this; purystalLightFloor.asFloor().wall = this;
                }};
                purystalWallAlt = new StaticWall("purystal-wall-alt"){{variants = 4;
                    purystalErodedFloor.asFloor().wall = this; purystalFloor.asFloor().wall = this; purystalLightFloor.asFloor().wall = this;
                }};
                gelionyticWall = new StaticWall("gelionytic-wall"){{variants = 4;
                    purystalErodedFloor.asFloor().wall = this; purystalFloor.asFloor().wall = this; purystalLightFloor.asFloor().wall = this;
                                itemDrop = KallisteaItems.gelionyte;
                }};

                // decor, etc
                purystalBoulder = new Prop("purystal-boulder"){{variants = 4; customShadow = true;
                    purystalErodedFloor.asFloor().decoration = this; purystalFloor.asFloor().decoration = this; purystalLightFloor.asFloor().decoration = this;
                }};          
                gelionyteChunk = new BreakableTallBlock("gelionyte-chunk"){{requirements(Category.distribution, with(KallisteaItems.gelionyte, 24)); buildCostMultiplier = 6; variants = 3; clipSize = 148; shadowOffset = -0.68f;}};
                gelionyteCluster = new TallBlock("gelionyte-cluster"){{variants = 2; clipSize = 148; shadowOffset = -0.68f;}};
                buahanBush = new Prop("buahan-bush"){{variants = 3; customShadow = true;}};
                buahanTree = new TreeBlock("buahan-tree"){{variants = 2; clipSize = 200; shadowOffset = -1.53f;}};
   
                // EADSTON BIOME
                // floors
                eadstonFloor = new ColorableFloor("eadston-floor", 6, Color.valueOf("818a8c"));
                eadstonLightFloor = new ColorableFloor("eadston-light-floor", 6, Color.valueOf("656c6e"));
                eadstonRoughFloor = new ColorableFloor("eadston-rough-floor", 6, Color.valueOf("474e4f"));
                fensporReinforcedFloor = new Floor("fenspor-reinforced-floor", 6);
                fensporStrandsFloor = new Floor("fenspor-strands-floor", 6);
                fensporVent = new SteamVent("fenspor-vent"){{variants= 2; parent = blendGroup = fensporReinforcedFloor = fensporStrandsFloor;
                    attributes.set(infection, 1f);}};

                // walls
                eadstonWall = new StaticWall("eadston-wall"){{variants = 4;
                    eadstonFloor.asFloor().wall = this; eadstonLightFloor.asFloor().wall = this; eadstonRoughFloor.asFloor().wall = this;
                }};
                eadstonWallAlt = new StaticWall("eadston-wall-alt"){{variants = 4;
                    eadstonFloor.asFloor().wall = this; eadstonLightFloor.asFloor().wall = this; eadstonRoughFloor.asFloor().wall = this;
                }};
                eadstonWallDeanytic = new StaticWall("eadston-deanytic-wall"){{variants = 4;
                    eadstonFloor.asFloor().wall = this; eadstonLightFloor.asFloor().wall = this; eadstonRoughFloor.asFloor().wall = this;
                }};
                eadstalMagnetiteWall = new StaticWall("eadstal-magnetite-wall"){{
            itemDrop = KallisteaItems.magnetite;
            variants = 4;}};
                eadstonWallFenspor = new StaticWall("eadston-fenspor-wall"){{variants = 4;
                    fensporReinforcedFloor.asFloor().wall = this; fensporStrandsFloor.asFloor().wall = this;
                }};

                // decor, etc
                eadstonBoulder = new Prop("eadston-boulder"){{variants = 4; customShadow = true;
                    eadstonFloor.asFloor().decoration = this; eadstonLightFloor.asFloor().decoration = this; eadstonRoughFloor.asFloor().decoration = this;
                }};
                eadstonFensporBoulder = new Prop("eadston-fenspor-boulder"){{variants = 4; customShadow = true;
                    fensporReinforcedFloor.asFloor().decoration = this; fensporStrandsFloor.asFloor().decoration = this;
                }};
                eadstalDeanyteCluster = new TallBlock("eadstal-deanyte-cluster"){{variants = 2; clipSize = 148; shadowOffset = -0.68f;}};
                buahanDeadBush = new Prop("buahan-dead-bush"){{variants = 3; customShadow = true;}};
                buahanFensporDeadBush = new Prop("buahan-fenspor-dead-bush"){{variants = 3; customShadow = true;}};
                buahanDeadTree = new TreeBlock("buahan-dead-tree"){{variants = 2; clipSize = 200; shadowOffset = -1.53f;}};

                // MERAPORA BIOME
                // floors
                meraporaFloor = new Floor("merapora-floor", 6){{itemDrop = KallisteaItems.redSand;}};
                meraporaVent = new SteamVent("merapora-vent"){{variants= 2; parent = blendGroup = meraporaFloor;}};
                meraporaFensporFloor = new Floor("merapora-fenspor-floor", 6);
                meraporaFensporVent = new SteamVent("merapora-fenspor-vent"){{variants= 2; parent = blendGroup = meraporaFensporFloor;
                    attributes.set(infection, 1f);}};

                //walls
                meraporaWall = new StaticWall("merapora-wall"){{variants = 4; meraporaFloor.asFloor().wall = this;}};
                meraporaWallAlt = new StaticWall("merapora-wall-alt"){{variants = 4; meraporaFloor.asFloor().wall = this;}}; 
                meraporaFensporWall = new StaticWall("merapora-fenspor-wall"){{variants = 4; meraporaFensporFloor.asFloor().wall = this;}};
                meraporaFensporWallAlt = new StaticWall("merapora-fenspor-wall-alt"){{variants = 3; meraporaFensporFloor.asFloor().wall = this;}};

                // decor, etc
                meraporaBoulder = new Prop("merapora-boulder"){{variants = 4; customShadow = true; meraporaFloor.asFloor().decoration = this;}};
                meraporaFensporBoulder = new Prop("merapora-fenspor-boulder"){{variants = 4; customShadow = true; meraporaFensporFloor.asFloor().decoration = this;}};
                meraporaBunker = new BunkerVent("merapora-bunker", 2){{parent = blendGroup = meraporaFloor;}};
                meraporaMenhir = new Prop("merapora-menhir"){{variants = 2; breakable = false; meraporaFloor.asFloor().decoration = this;}};
            }
        }
    }
}
