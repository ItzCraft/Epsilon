package epsilon.content.Kallistea.blocks;

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
            crystalF,

            //purystal-biome
            purystalErodedFloor, purystalFloor, purystalLightFloor,
            purystalWall, purystalWallAlt, gelionyticWall,
            purystalBoulder, gelionyteChunk, gelionyteCluster, buahanBush, buahanTree,

            // eadstal biome
            eadstonFloor, eadstonLightFloor, eadstonRoughFloor, fensporReinforcedFloor, fensporStrandsFloor, fensporVent,
            eadstonWall, eadstonWallAlt, eadstonWallDeanytic, eadstonWallFenspor,
            eadstonBoulder, eadstonFensporBoulder, eadstalDeanyteCluster, buahanDeadBush, buahanFensporDeadBush, buahanDeadTree;

    public static void load() {
        {
            {
                //testing blocks
                crystalF = new Floor("crystal-f", 0);


                //PURYSTAL BIOME
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
                }};
                gelionyticWall = new StaticWall("gelionytic-wall"){{variants = 4;
                    purystalErodedFloor.asFloor().wall = this; purystalFloor.asFloor().wall = this; purystalLightFloor.asFloor().wall = this;
                                itemDrop = KallisteaItems.gelionyte;
                }};

                //decor, etc
                purystalBoulder = new Prop("purystal-boulder"){{variants = 4; customShadow = true;
                    purystalErodedFloor.asFloor().decoration = this; purystalFloor.asFloor().decoration = this; purystalLightFloor.asFloor().decoration = this;
                }};          
                gelionyteChunk = new BreakableTallBlock("gelionyte-chunk"){{requirements(Category.distribution, with(KallisteaItems.gelionyte, 24)); buildCostMultiplier = 6; variants = 3; clipSize = 148; shadowOffset = -0.68f;}};
                gelionyteCluster = new TallBlock("gelionyte-cluster"){{variants = 2; clipSize = 148; shadowOffset = -0.68f;}};
                buahanBush = new Prop("buahan-bush"){{variants = 3; customShadow = true;}};
                buahanTree = new TreeBlock("buahan-tree"){{variants = 2; clipSize = 200; shadowOffset = -1.53f;}};
   
                // EADSTON BIOME
                // floors
                eadstonFloor = new Floor("eadston-floor", 6);
                eadstonLightFloor = new Floor("eadston-light-floor", 6);
                eadstonRoughFloor = new Floor("eadston-rough-floor", 6);
                fensporReinforcedFloor = new Floor("fenspor-reinforced-floor", 6);
                fensporStrandsFloor = new Floor("fenspor-strands-floor", 6);
                fensporVent = new SteamVent("fenspor-vent"){{variants= 2; parent = blendGroup = fensporReinforcedFloor = fensporStrandsFloor;
                    attributes.set(infection, 1f);}};

                //walls
                eadstonWall = new StaticWall("eadston-wall"){{variants = 4;
                    eadstonFloor.asFloor().wall = this; eadstonLightFloor.asFloor().wall = this; eadstonRoughFloor.asFloor().wall = this;
                }};
                eadstonWallAlt = new StaticWall("eadston-wall-alt"){{variants = 4;
                    eadstonFloor.asFloor().wall = this; eadstonLightFloor.asFloor().wall = this; eadstonRoughFloor.asFloor().wall = this;
                }};
                eadstonWallDeanytic = new StaticWall("eadston-deanytic-wall"){{variants = 4;
                    eadstonFloor.asFloor().wall = this; eadstonLightFloor.asFloor().wall = this; eadstonRoughFloor.asFloor().wall = this;
                }};
                eadstonWallFenspor = new StaticWall("eadston-fenspor-wall"){{variants = 4;
                    fensporReinforcedFloor.asFloor().wall = this; fensporStrandsFloor.asFloor().wall = this;
                }};

                //decor, etc
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
            }
        }
    }
}
