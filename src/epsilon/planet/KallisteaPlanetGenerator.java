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

public class KallisteaPlanetGenerator extends PlanetGenerator {
    public float heightScl = 1.2f, octaves = 8, persistence = 0.7f, heightPow = 2.2f, heightMult = 1.1f;

    @Override
    public float getHeight(Vec3 position) {
        return Mathf.pow(rawHeight(position), heightPow) * heightMult;
    }

    float rawHeight(Vec3 position) {
        return Simplex.noise3d(seed, octaves, persistence, 1f / heightScl, 10f + position.x, 10f + position.y, 10f + position.z);
    }

    @Override
    public void getColor(Vec3 position, Color out) {
        Block block = rawHeight(position) < 0.35f ? Blocks.water : rawHeight(position) < 0.4f ? KallisteaEnv.meraporaFloor : rawHeight(position) < 0.45f ? KallisteaEnv.meraporaFensporFloor : rawHeight(position) < 0.55f ? KallisteaEnv.purystalFloor : rawHeight(position) < 0.6f ? KallisteaEnv.purystalLightFloor : rawHeight(position) < 0.64f ? KallisteaEnv.fensporReinforcedFloor : rawHeight(position) < 0.7f ? KallisteaEnv.eadstonFloor : KallisteaEnv.thermaliticVulcanFloor;

        out.set(block.mapColor).a(1f - block.albedo);
    }
}