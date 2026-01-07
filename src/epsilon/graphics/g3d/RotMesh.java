package epsilon.graphics.g3d;

import arc.graphics.*;
import arc.graphics.gl.Shader;
import arc.math.geom.*;
import arc.util.Time;
import epsilon.graphics.EpsShaders;
import mindustry.graphics.Shaders;
import mindustry.graphics.g3d.*;
import mindustry.type.Planet;

/**
 * Planet mesh which rotates in real time.
 * @author ItzCraft
 **/
public class RotMesh implements GenericMesh{
    protected Planet planet;
    protected Mesh mesh;
    protected Shader shader;

    private float angle;

    public RotMesh(Planet planet, int divisions){
        this.planet = planet;
        this.shader = EpsShaders.planet;
        this.mesh = MeshBuilder.buildHex(planet.generator, divisions, planet.radius, 0.2F);
    }

    @Override
    public void render(PlanetParams params, Mat3D projection, Mat3D transform){
        if(mesh.isDisposed())return;

        Mat3D mat = new Mat3D().set(transform);
        angle += Time.delta / planet.rotateTime;
        mat.rotate(Vec3.Y, angle);

        EpsShaders.planet.planet = planet;
        shader.bind();
        shader.setUniformMatrix4("u_proj", projection.val);
        shader.setUniformMatrix4("u_trans", mat.val);
        shader.setUniformMatrix4("u_normalMat", mat.val);
        EpsShaders.planet.emissive = planet.generator != null && planet.generator.isEmissive();
        EpsShaders.planet.ambientColor.set(planet.solarSystem.lightColor);
        shader.apply();
        mesh.render(shader, Gl.triangles);
    }

    @Override
    public void dispose(){
        if(mesh != null){
            mesh.dispose();
        }
    }
}
