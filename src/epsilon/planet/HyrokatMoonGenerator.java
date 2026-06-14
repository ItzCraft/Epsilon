package epsilon.planet;

import arc.graphics.*;
import arc.math.*;
import arc.math.geom.*;
import arc.util.Tmp;
import arc.util.noise.*;
import epsilon.content.Kallistea.blocks.KallisteaEnv;
import mindustry.content.Blocks;
import mindustry.maps.generators.PlanetGenerator;
import mindustry.world.Block;

public class HyrokatMoonGenerator extends PlanetGenerator {
    public float heightScl = 0.7f, octaves = 6, persistence = 0.45f, heightPow = 1.1f, heightMult = 0.86f;

    @Override
    public float getHeight(Vec3 position){
        return Mathf.pow(rawHeight(position), heightPow) * heightMult;
    }

    float rawHeight(Vec3 position){
        return Simplex.noise3d(seed, octaves, persistence, 1f/heightScl, 10f + position.x, 10f + position.y, 10f + position.z);
    }

    @Override
    public void getColor(Vec3 position, Color out){
        Block block = rawHeight(position) < 0.2f ? Blocks.cryofluid : rawHeight(position) < 0.3f ? Blocks.ice : rawHeight(position) < 0.45f ? Blocks.iceSnow : Blocks.iceWall;

        out.set(block.mapColor).a(1f - block.albedo);
    }
}
