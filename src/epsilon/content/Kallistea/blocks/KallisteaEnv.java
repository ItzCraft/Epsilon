package epsilon.content.Kallistea.blocks;

import arc.graphics.Color;
import mindustry.content.Fx;
import mindustry.world.Block;
import mindustry.world.blocks.environment.*;
import epsilon.content.Kallistea.KallisteaItems;
import epsilon.world.blocks.environment.*;
import mindustry.type.Category;
import mindustry.world.meta.Attribute;

import static mindustry.type.ItemStack.with;
import static epsilon.world.EpsAttribute.infection;


public class KallisteaEnv {
    public static Block
            // testing ones
            crystalF,

            //misc
            coreObscurityBrokenBits,

            // ores
            calciteOre, quartzOre,

            //purystal-biome
            purystalErodedFloor, purystalFloor, purystalLightFloor, 
			purystalWall, purystalWallAlt, purystalBoulder, 
			gelionyticWall, gelionyteChunk, gelionyteCluster, 

            // eadstal biome
            eadstonFloor, eadstonLightFloor, eadstonRoughFloor, 
            eadstonWall, eadstonWallAlt, eadstonBoulder, eadstalMagnetiteWall, eadstonWallDeanytic, eadstalDeanyteCluster, eadstonWallFenspor, eadstonFensporBoulder,

			//buahan
			fensporReinforcedFloor, fensporStrandsFloor, fensporVent,
			buahanDeadBush, buahanFensporDeadBush, buahanDeadTree, buahanBush, buahanTree,

            // Merapora biome
            meraporaFloor, meraporaFensporFloor, meraporaWall, meraporaWallAlt, meraporaFensporWall, meraporaFensporWallAlt,
            meraporaBoulder, meraporaFensporBoulder, meraporaVent, meraporaFensporVent, meraporaBunker, meraporaMenhir,

            // Thermalitic biome
            thermaliticFloor, thermaliticDarkFloor, thermaliticBrownFloor, thermaliticVulcanFloor, thermaliticWall,thermaliticWallAlt,
            thermaliticDarkWall, thermaliticBrownWall, thermaliticBrownWallAlt, thermaliticBoulder, thermaliticDarkBoulder, thermaliticBrownBoulder,
            miniVulcan, vulcan,

            // broken facility
            facilityTile1, facilityTile2, facilityTile3, facilityWalls;

    public static void load() {
        {
            {
                // testing blocks
                crystalF = new Floor("crystal-f", 0);

                // misc
                coreObscurityBrokenBits = new Prop("core-obscurity-broken-bits"){{variants=2;breakable=false;}};


                // ores
                calciteOre = new OreBlock("calcite-ore", KallisteaItems.calcite){{variants = 6;}};
                quartzOre = new OreBlock("quartz-ore", KallisteaItems.quartz){{variants = 6;}}; 


                //PURYSTAL BIOME
                purystalErodedFloor = new Floor("purystal-eroded-floor", 6);
                purystalFloor = new Floor("purystal-floor", 6){{this.albedo = 0.35f;}};
                purystalLightFloor = new Floor("purystal-light-floor", 6);
                purystalWall = new StaticWall("purystal-wall"){{variants = 4;
                    purystalErodedFloor.asFloor().wall = this; purystalFloor.asFloor().wall = this; purystalLightFloor.asFloor().wall = this;
                }};
                purystalWallAlt = new StaticWall("purystal-wall-alt"){{variants = 4;
                    purystalErodedFloor.asFloor().wall = this; purystalFloor.asFloor().wall = this; purystalLightFloor.asFloor().wall = this;
                }};
				purystalBoulder = new Prop("purystal-boulder"){{variants = 4; customShadow = true;
                    purystalErodedFloor.asFloor().decoration = this; purystalFloor.asFloor().decoration = this; purystalLightFloor.asFloor().decoration = this;
                }};          
				gelionyticWall = new StaticWall("gelionytic-wall"){{variants = 4;
                    purystalErodedFloor.asFloor().wall = this; purystalFloor.asFloor().wall = this; purystalLightFloor.asFloor().wall = this;
                                itemDrop = KallisteaItems.gelionyte;
                }};
                gelionyteChunk = new BreakableTallBlock("gelionyte-chunk"){{requirements(Category.distribution, with(KallisteaItems.gelionyte, 24)); buildCostMultiplier = 6; variants = 3; clipSize = 148; shadowOffset = -0.68f;}};
                gelionyteCluster = new TallBlock("gelionyte-cluster"){{variants = 2; clipSize = 148; shadowOffset = -0.68f; emitLight = true; lightColor = Color.valueOf("a86bd1"); lightRadius = 40f;}};

                // EADSTON BIOME
                eadstonFloor = new ColorableFloor("eadston-floor", 6, Color.valueOf("818a8c")){{color = Color.valueOf("818a8c");}};
                eadstonLightFloor = new ColorableFloor("eadston-light-floor", 6, Color.valueOf("656c6e")){{color = Color.valueOf("656c6e");}};
                eadstonRoughFloor = new ColorableFloor("eadston-rough-floor", 6, Color.valueOf("474e4f")){{color = Color.valueOf("474e4f");}};
				eadstonWall = new StaticWall("eadston-wall"){{variants = 4;
                    eadstonFloor.asFloor().wall = this; eadstonLightFloor.asFloor().wall = this; eadstonRoughFloor.asFloor().wall = this;
                }};
                eadstonWallAlt = new StaticWall("eadston-wall-alt"){{variants = 4;
                    eadstonFloor.asFloor().wall = this; eadstonLightFloor.asFloor().wall = this; eadstonRoughFloor.asFloor().wall = this;
                }};
				eadstonBoulder = new Prop("eadston-boulder"){{variants = 4; customShadow = true;
                    eadstonFloor.asFloor().decoration = this; eadstonLightFloor.asFloor().decoration = this; eadstonRoughFloor.asFloor().decoration = this;
                }};
				eadstalMagnetiteWall = new StaticWall("eadstal-magnetite-wall"){{
                itemDrop = KallisteaItems.magnetite;
                variants = 4;}};
				eadstonWallDeanytic = new StaticWall("eadston-deanytic-wall"){{variants = 4;
                    eadstonFloor.asFloor().wall = this; eadstonLightFloor.asFloor().wall = this; eadstonRoughFloor.asFloor().wall = this;
                }};
                fensporReinforcedFloor = new Floor("fenspor-reinforced-floor", 6);
                fensporStrandsFloor = new Floor("fenspor-strands-floor", 6);
				eadstalDeanyteCluster = new TallBlock("eadstal-deanyte-cluster"){{variants = 2; clipSize = 148; shadowOffset = -0.68f;}};
				eadstonWallFenspor = new StaticWall("eadston-fenspor-wall"){{variants = 4;
                    fensporReinforcedFloor.asFloor().wall = this; fensporStrandsFloor.asFloor().wall = this;
                }};
				eadstonFensporBoulder = new Prop("eadston-fenspor-boulder"){{variants = 4; customShadow = true;
                    fensporReinforcedFloor.asFloor().decoration = this; fensporStrandsFloor.asFloor().decoration = this;
                }};
                fensporVent = new SteamVent("fenspor-vent"){{variants= 2; parent = blendGroup = fensporReinforcedFloor = fensporStrandsFloor;
                    attributes.set(infection, 1f);}};
                buahanDeadBush = new Prop("buahan-dead-bush"){{variants = 3; customShadow = true;}};
                buahanFensporDeadBush = new Prop("buahan-fenspor-dead-bush"){{variants = 3; customShadow = true;}};
                buahanDeadTree = new TreeBlock("buahan-dead-tree"){{variants = 2; clipSize = 200; shadowOffset = -1.53f;}};
				buahanBush = new Prop("buahan-bush"){{variants = 3; customShadow = true;}};
                buahanTree = new TreeBlock("buahan-tree"){{variants = 2; clipSize = 200; shadowOffset = -1.53f;}};

                // MERAPORA BIOME
                // floors
                meraporaFloor = new Floor("merapora-floor", 6){{itemDrop = KallisteaItems.redSand; this.albedo = 0.5f;}};
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


                // THERMALITIC BIOME
                // floors
                thermaliticFloor = new Floor("thermalitic-floor"){{variants = 6;}};
                thermaliticDarkFloor = new Floor("thermalitic-dark-floor"){{variants = 3;}};
                thermaliticBrownFloor = new Floor("thermalitic-brown-floor"){{variants = 8;}};
                thermaliticVulcanFloor = new Floor("thermalitic-vulcan-floor"){{variants = 6; emitLight = true; lightColor = Color.valueOf("d46108"); lightRadius = 10;}};

                // walls
                thermaliticWall = new StaticWall("thermalitic-wall"){{variants = 4; thermaliticFloor.asFloor().wall = this;}};
                thermaliticWallAlt = new StaticWall("thermalitic-wall-alt"){{variants = 4; thermaliticFloor.asFloor().wall = this;}};
                thermaliticDarkWall = new StaticWall("thermalitic-dark-wall"){{variants = 2; thermaliticDarkFloor.asFloor().wall = this;}};
                thermaliticBrownWall = new StaticWall("thermalitic-brown-wall"){{variants = 4; thermaliticBrownFloor.asFloor().wall = this;}};
                thermaliticBrownWallAlt = new StaticWall("thermalitic-brown-alt-wall"){{variants = 4; thermaliticBrownFloor.asFloor().wall = this;}};

                //decor, etc
                thermaliticBoulder = new Prop("thermalitic-boulder"){{variants = 4; thermaliticFloor.asFloor().decoration = this;}};
                thermaliticDarkBoulder = new Prop("thermalitic-dark-boulder"){{variants = 4; thermaliticDarkFloor.asFloor().decoration = this;}};
                thermaliticBrownBoulder = new Prop("thermalitic-brown-boulder"){{variants = 4; thermaliticBrownFloor.asFloor().decoration = this;}};

                // vulcans
                vulcan = new Vulcan("thermalitic-vulcan"){{variants = 2; parent = blendGroup = thermaliticVulcanFloor; emitLight = true; lightColor = Color.valueOf("d46108"); lightRadius = 45; effect = Fx.fire;}};
                miniVulcan = new Vulcan("thermalitic-vulcan-mini"){{variants = 2; parent = blendGroup = thermaliticVulcanFloor; mini=true; emitLight = true; lightColor = Color.valueOf("d46108"); lightRadius = 25; effect = Fx.fire;}};

                // facility biome
                facilityTile1 = new TiledFloor("facility-tile-1") {{
                }};
                facilityTile2 = new TiledFloor("facility-tile-2"){{
                }};
                facilityTile3 = new TiledFloor("facility-tile-3"){{
                }};
                facilityWalls = new StaticWall("facility-walls"){{
                }};
            }
        }
    }
}
