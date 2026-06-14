package epsilon.graphics;

import arc.files.Fi;
import arc.graphics.Color;
import arc.graphics.g2d.Draw;
import arc.graphics.gl.Shader;
import arc.math.geom.Vec3;
import mindustry.Vars;
import mindustry.graphics.Shaders;
import mindustry.type.Planet;

import static mindustry.Vars.*;


// note: some code is from omaloon
public class EpsShaders{
    public static PlanetTextureShader planetTextureShader;
    public static EpsPlanetShader planet;

    public static void load(){
        planetTextureShader = new PlanetTextureShader();
        planet = new EpsPlanetShader();
    }


    public static Fi file(String name){
        return tree.get("shaders/" + name);
    }


    public static class EpsPlanetShader extends EpsLoadShader{
        public Vec3 lightDir = (new Vec3(1.0F, 1.0F, 1.0F)).nor();
        public Color ambientColor;
        public Vec3 camDir;
        public Vec3 camPos;
        public boolean emissive;
        public Planet planet;

        public EpsPlanetShader() {
            super("planet", "planet");
            this.ambientColor = Color.white.cpy();
            this.camDir = new Vec3();
            this.camPos = new Vec3();
        }

        @Override
        public void apply() {
            this.camDir.set(Vars.renderer.planets.cam.direction).rotate(Vec3.Y, this.planet.getRotation());
            this.setUniformf("u_lightdir", this.lightDir);
            this.setUniformf("u_ambientColor", this.ambientColor.r, this.ambientColor.g, this.ambientColor.b);
            this.setUniformf("u_camdir", this.camDir);
            this.setUniformf("u_campos", Vars.renderer.planets.cam.position);
            this.setUniformf("u_emissive", this.emissive ? 1.0F : 0.0F);
        }
    }

    public static class PlanetTextureShader extends EpsLoadShader{
        public Vec3 lightDir = new Vec3(1, 1, 1).nor();
        public Color ambientColor = Color.white.cpy();
        public Vec3 camDir = new Vec3();
        public float alpha = 1f;
        public Planet planet;

        public PlanetTextureShader(){
            super("circle-mesh", "circle-mesh");
        }

        @Override
        public void apply(){
            camDir.set(renderer.planets.cam.direction).rotate(Vec3.Y, planet.getRotation());

            setUniformf("u_alpha", alpha);
            setUniformf("u_lightdir", lightDir);
            setUniformf("u_ambientColor", ambientColor.r, ambientColor.g, ambientColor.b);
            setPlanetInfo("u_sun_info", planet.solarSystem);
            setPlanetInfo("u_planet_info", planet);
            setUniformf("u_camdir", camDir);
            setUniformf("u_campos", renderer.planets.cam.position);
        }

        private void setPlanetInfo(String name, Planet planet){
            Vec3 position = planet.position;
            Shader shader = this;
            shader.setUniformf(name, position.x, position.y, position.z, planet.radius);
        }
    }
    public static class EpsLoadShader extends Shader {

        public EpsLoadShader(String fragment, String vertex){
            super(
                    file(vertex + ".vert"),
                    file(fragment + ".frag")
            );
        }

        public void set(){
            Draw.shader(this);
        }

        @Override
        public void apply(){
            super.apply();

            setUniformf("u_time_millis", System.currentTimeMillis() / 1000f * 60f);
        }
    }
}
