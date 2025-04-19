package epsilon.planet;

import arc.math.*;
import arc.math.geom.Vec3;
import arc.struct.Seq;
import arc.util.noise.Simplex;

/*
the code is from Omaloon.
 **/

public abstract class HeightCalc{
    public abstract float height(Vec3 pos,float height);
    public boolean valid(Vec3 pos, float height){
        return true;
    }
    public static class SphereHeight extends HeightCalc{
        public Vec3 pos = new Vec3();
        public float radius = 0.5f;
        public float offset = 0f;
        public boolean set = false;
        @Override
        public float height(Vec3 pos, float height){
            if(!valid(pos, height)) return height;
            if(pos.dst(this.pos) < radius) return offset + height * (set ? 0f : 1f);
            return height;
        }
    }

    public static class NoiseHeight extends HeightCalc{
        public Vec3 offset = new Vec3();
        public int seed;
        public double octaves = 1.0;
        public double persistence = 1.0;
        public double scale = 1.0;
        public float magnitude = 1f;
        public float heightOffset = 0f;
        @Override
        public float height(Vec3 pos, float height){
            return Simplex.noise3d(seed, octaves, persistence, scale, pos.x + offset.x, pos.y + offset.y, pos.z + offset.z) * magnitude + heightOffset + height;
        }
    }

    public static class ClampHeight extends HeightCalc {
        public float min, max;

        public ClampHeight(float min, float max) {
            this.min = min;
            this.max = max;
        }

        @Override
        public float height(Vec3 pos, float height) {
            return Mathf.clamp(height, min, max);
        }
    }

    public static class DotHeight extends HeightCalc{
            public Vec3 dir = new Vec3();
            public float min = -1f;
            public float max = 1f;
            public boolean map = true;
            public Interp interp = Interp.linear;
            public float magnitude = 1;

            @Override
            public float height(Vec3 pos, float height){
                if(!valid(pos, height)) return height;
                float dot = dir.nor().dot(pos);
                dot = Mathf.map(dot, map ? min : -1f, map ? max : 1f, 0f, 1f);
                return interp.apply(dot) * magnitude + height;
            }

            @Override
            public boolean valid(Vec3 pos, float height){
                float dot = dir.nor().dot(pos);
                return dot >= min && dot <= max;
            }
    }
    public static class MultiHeight extends HeightCalc{
            public Seq<HeightCalc> heights;
            public MixType mixType;
            public Operation operation;

            public MultiHeight(Seq<HeightCalc> heights, MixType mixType, Operation operation){
                this.heights = heights;
                this.mixType = mixType;
                this.operation = operation;
            }

            @Override
            public float height(Vec3 pos, float height){
                if(!valid(pos, height)) return height;
                switch(operation){
                    case add -> {
                        return height + rawHeight(pos, height);
                    }
                    case set -> {
                        return rawHeight(pos, height);
                    }
                    case carve -> {
                        return height - rawHeight(pos, height);
                    }
                }
                return height;
            }

            float rawHeight(Vec3 pos, float base){
                switch(mixType){
                    case max -> {
                        return heights.select(pass -> pass.valid(pos, base)).max(pass -> pass.height(pos, base)).height(pos, base);
                    }
                    case average -> {
                        return heights.select(pass -> pass.valid(pos, base)).sumf(pass -> pass.height(pos, base)) / (float)heights.size;
                    }
                    case min -> {
                        return heights.select(pass -> pass.valid(pos, base)).min(pass -> pass.height(pos, base)).height(pos, base);
                    }
                }
                return 0f;
            }

            @Override
            public boolean valid(Vec3 pos, float height){
                return heights.contains(pass -> pass.valid(pos, height));
            }

            public enum MixType{
                max, average, min
            }

            public enum Operation{
                add, set, carve
            }
        }
    }
