package epsilon.content.Kallistea.blocks;

import mindustry.world.Block;
import mindustry.world.blocks.environment.*;
import epsilon.content.Kallistea.KallisteaItems;


public class KallisteaEnv {
    public static Block
            crystalF,

            //purystal-biome
            purystalErodedFloor, purystalFloor, purystalLightFloor,
            purystalWall, purystalWallAlt, gelionyticWall,
            purystalBoulder, gelionyteCluster, crystallineBush, crystallineTree;
    public static void load() {
        {
            {
                //testing blocks
                crystalF = new Floor("crystal-f", 0);


                //purystal-biome
                //floors
                purystalErodedFloor = new Floor("purystal-eroded-floor", 6);
                purystalFloor = new Floor("purystal-floor", 6);
                purystalLightFloor = new Floor("purystal-light-floor", 6);

                //walls
                purystalWall = new StaticWall("purystal-wall"){{variants = 4;
                    purystalErodedFloor.asFloor().wall = this; purystalFloor.asFloor().wall = this; purystalLightFloor.asFloor().wall = this;
                }};
                purystalWallAlt = new StaticWall("purystal-wall-alt"){{variants = 4;
                    purystalErodedFloor.asFloor().wall = this; purystalFloor.asFloor().wall = this; purystalLightFloor.asFloor().wall = this;
                }};;
                gelionyticWall = new StaticWall("gelionytic-wall"){{variants = 4;
                    purystalErodedFloor.asFloor().wall = this; purystalFloor.asFloor().wall = this; purystalLightFloor.asFloor().wall = this;
                                itemDrop = KallisteaItems.Gelionyte;
                }};
                gelionyteCluster = new TallBlock("gelionyte-cluster"){{variants = 2; clipSize = 148; shadowOffset = -0.68f;}};

                //decor, etc
                purystalBoulder = new Prop("purystal-boulder"){{variants = 4; customShadow = true;
                    purystalErodedFloor.asFloor().decoration = this; purystalFloor.asFloor().decoration = this; purystalLightFloor.asFloor().decoration = this;
                }};
                crystallineBush = new Prop("crystalline-bush"){{variants = 3; customShadow = true;}};
                crystallineTree = new TreeBlock("crystalline-tree"){{variants = 2; clipSize = 200; shadowOffset = -1.53f;}};

            }
        }
    }
}