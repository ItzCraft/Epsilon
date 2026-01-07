package epsilon.graphics.g3d;


import java.nio.FloatBuffer;

import arc.Core;
import arc.graphics.*;
import arc.graphics.g2d.TextureRegion;
import arc.graphics.gl.Shader;
import arc.math.geom.*;
import arc.util.Tmp;
import epsilon.graphics.EpsShaders;
import mindustry.graphics.g3d.*;
import mindustry.type.Planet;

/**A ring-shaped mesh for planets.... @author Arksource */
public class RingMesh extends PlanetMesh{

    public TextureRegion region;
    public Texture texture;
    public Color color = Color.white.cpy();

    private static final Mat3D combinedMat = new Mat3D();

    private static class MeshUtils {
        static final float[] floats = new float[8];
        static Mesh mesh;

        static void begin(int count) {
            mesh = new Mesh(true, count, 0,
                    VertexAttribute.position3,
                    VertexAttribute.normal,
                    VertexAttribute.texCoords
            );

            mesh.getVerticesBuffer().limit(count * 8);
            mesh.getVerticesBuffer().position(0);
        }

        static Mesh end(){
            Mesh last = mesh;

            last.getVerticesBuffer().limit(last
                    .getVerticesBuffer()
                    .position()
            );

            mesh = null;
            return last;
        }

        static void vert(Vec3 a, Vec3 normal, Vec2 texCords){
            FloatBuffer buffer = mesh.getVerticesBuffer();

            floats[0] = a.x;
            floats[1] = a.y;
            floats[2] = a.z;
            floats[3] = normal.x;
            floats[4] = normal.y;
            floats[5] = normal.z;
            floats[6] = texCords.x;
            floats[7] = texCords.y;

            buffer.put(floats);
        }
    }

    public RingMesh(TextureRegion region, Planet planet, int sides, float radiusIn, float radiusOut, Vec3 axis){
        this.planet = planet;
        this.region = region;
        this.texture = region.texture;

        MeshUtils.begin(sides * 12);

        Vec3 plane = new Vec3()
                .set(1, 0, 0)
                .rotate(Vec3.X, 90)
                .rotate(Vec3.X, axis.angle(Vec3.X) + 1)
                .rotate(Vec3.Y, axis.angle(Vec3.Y) + 1)
                .rotate(Vec3.Z, axis.angle(Vec3.Z) + 1)
                .crs(axis);

        Vec3 inv = axis.cpy().unaryMinus();
        Vec3 innerCurrent = Tmp.v31, outerCurrent = Tmp.v32;
        Vec3 innerNext = Tmp.v33, outerNext = Tmp.v34;
        Vec2 tex = Tmp.v1;

        float texU = region.u;
        float texV = region.v;
        float texU2 = region.u2;
        float texV2 = region.v2;

        float uInner = texU;
        float uOuter = texU2;

        for(int i = 0; i < sides; i++){
            float angleCurrent = i * 1f / sides * 360;
            float angleNext = (i + 1f) / sides * 360;

            float vCurrentBase = i * 1f / sides;
            float vNextBase = (i + 1f) / sides;

            float vCurrent = texV + vCurrentBase * (texV2 - texV);
            float vNext = texV + vNextBase * (texV2 - texV);

            innerCurrent.set(plane).rotate(axis, angleCurrent).setLength2(1).scl(radiusIn);
            outerCurrent.set(plane).rotate(axis, angleCurrent).setLength2(1).scl(radiusOut);
            innerNext.set(plane).rotate(axis, angleNext).setLength2(1).scl(radiusIn);
            outerNext.set(plane).rotate(axis, angleNext).setLength2(1).scl(radiusOut);

            // Caras frontales (Triángulo 1)
            MeshUtils.vert(innerCurrent, axis, tex.set(uInner, vCurrent));
            MeshUtils.vert(outerCurrent, axis, tex.set(uOuter, vCurrent));
            MeshUtils.vert(outerNext, axis, tex.set(uOuter, vNext));

            // Caras frontales (Triángulo 2)
            MeshUtils.vert(outerNext, axis, tex.set(uOuter, vNext));
            MeshUtils.vert(innerNext, axis, tex.set(uInner, vNext));
            MeshUtils.vert(innerCurrent, axis, tex.set(uInner, vCurrent));

            // Caras traseras (zona levemente oscura :p)
            MeshUtils.vert(outerNext, inv, tex.set(uOuter, vNext));
            MeshUtils.vert(outerCurrent, inv, tex.set(uOuter, vCurrent));
            MeshUtils.vert(innerCurrent, inv, tex.set(uInner, vCurrent));

            MeshUtils.vert(innerCurrent, inv, tex.set(uInner, vCurrent));
            MeshUtils.vert(innerNext, inv, tex.set(uInner, vNext));
            MeshUtils.vert(outerNext, inv, tex.set(uOuter, vNext));
        }
        mesh = MeshUtils.end();
    }

    private static Shader shader(){
        return EpsShaders.planetTextureShader;
    }

    @Override
    public void preRender(PlanetParams params){
        EpsShaders.planetTextureShader.planet = this.planet;

        if(planet == null || planet.solarSystem == null){
            return;
        }
        EpsShaders.planetTextureShader.lightDir
                .set(planet.solarSystem.position)
                .sub(planet.position)
                .rotate(Vec3.Y, planet.getRotation())
                .nor();

        EpsShaders.planetTextureShader.ambientColor
                .set(planet.solarSystem.lightColor);

        EpsShaders.planetTextureShader.alpha = 1f;
    }

    private void setPlanetInfo(String name, Planet planet){
        if (planet == null) return;

        Vec3 position = planet.position;
        Shader shader = shader();
        shader.setUniformf(name, position.x, position.y, position.z, planet.radius);
    }
    @Override
    public void render(PlanetParams params, Mat3D projection, Mat3D transform){
        preRender(params);

        if (planet == null) return;

        Shader shader = shader();
        shader.bind();

        Mat3D combined = combinedMat.set(projection).mul(transform);
        shader.setUniformMatrix4("u_mat", combined.val);
        shader.setUniformMatrix4("u_proj", projection.val);
        shader.setUniformMatrix4("u_trans", transform.val);

        shader.setUniformf("u_color", color);
        texture.bind(0);
        shader.setUniformi("u_texture", 0);

        shader.setUniformf("u_lightdir", EpsShaders.planetTextureShader.lightDir);
        shader.setUniformf("u_ambientColor", EpsShaders.planetTextureShader.ambientColor);
        shader.setUniformf("u_alpha", EpsShaders.planetTextureShader.alpha);
        shader.setUniformf("u_campos", Core.camera.position);

        setPlanetInfo("u_sun_info", planet.solarSystem);
        setPlanetInfo("u_planet_info", planet);

        shader.apply();

        mesh.render(shader, Gl.triangles);
    }

    @Override
    public void dispose() {
        mesh.dispose();
    }
}