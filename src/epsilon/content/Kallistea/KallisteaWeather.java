package epsilon.content.Kallistea;

import arc.Core;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Lines;
import arc.math.Rand;
import epsilon.graphics.EpsPal;
import mindustry.gen.WeatherState;
import mindustry.type.Weather;
import mindustry.type.weather.ParticleWeather;

public class KallisteaWeather {
    public static class MeteorShowerWeather extends ParticleWeather{

        public MeteorShowerWeather(String name){
            super(name);
            duration = 60f * 30f;
            noiseLayers = 0;
            drawNoise = false;
            baseSpeed = 0f;
            force = 0f;
            color = Color.white;
            density = 50f;
            sizeMin = 8f;
            sizeMax = 20f;
            xspeed = -400f;
            yspeed = -800f;
        }

        @Override
        public void drawOver(WeatherState state){
            super.drawOver(state);
            Rand rand = new Rand();
            float camX = Core.camera.position.x;
            float camY = Core.camera.position.y;
            rand.setSeed(1);
            int amount = (int)(density * state.intensity);
            for(int i = 0; i < amount; i++){
                float x = camX + rand.range(Core.graphics.getWidth());
                float y = camY + rand.range(Core.graphics.getHeight());
                Draw.color(Color.orange);
                Lines.stroke(2f);
                Lines.line(x, y, x + 15f, y + 30f);
            }
            Draw.reset();
        }
    }

    public static void load(){
        Weather fensporStorm = new ParticleWeather("fenspor-storm"){{
            color= EpsPal.fenspor;
            noiseColor = EpsPal.fensporUnits;
            sizeMax = 30;
            sizeMin = 7;
            density = 9000;
            duration = 60f*15f;
            useWindVector = true;
            drawNoise = true;
            baseSpeed = 0.75f;
            noisePath = "fog";
            noiseScale = 200;
            noiseLayerSpeedM = 1.78f;
            noiseLayerAlphaM = 0.83f;
            noiseLayerSclM = 0.63f;
            noiseLayers = 4;
        }};
    }
}
