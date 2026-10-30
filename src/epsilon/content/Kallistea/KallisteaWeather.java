package epsilon.content.Kallistea;

import epsilon.graphics.EpsPal;
import epsilon.type.weathers.UnitSpawnWeather;
import mindustry.game.Team;
import mindustry.gen.Sounds;
import mindustry.gen.Unit;
import mindustry.type.Weather;
import mindustry.world.meta.Attribute;

public class KallisteaWeather {
    public static Weather fensporStorm;

    public static void load(){
        fensporStorm = new UnitSpawnWeather("fenspor-storm"){{
            color = noiseColor = EpsPal.fenspor;
            particleRegion = "circle-small";
            drawNoise = true;
            statusGround = false;
            useWindVector = true;
            sizeMax = 4f;
            sizeMin = 2f;
            minAlpha = 0.1f;
            maxAlpha = 0.8f;
            density = 2000f;
            baseSpeed = 4.5f;
            attrs.set(Attribute.light, -0.15f);
            opacityMultiplier = 0.5f;
            force = 0.1f;
            sound = Sounds.wind;
            soundVol = 0.7f;
            units = new UnitStack[]{
                    new UnitStack(KallisteaUnitTypes.sporacrawler, 1f/5f)
            };
            unitChange = 1f/900f;
            unitTeam = EpsTeams.fenspor;
        }};
    }
}
