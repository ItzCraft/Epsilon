package epsilon.logic;

import arc.struct.OrderedMap;
import arc.util.Nullable;
import mindustry.content.Fx;
import mindustry.entities.Effect;

public class EpsLogicFx{
    private static OrderedMap<String, EffectEntry> map = new OrderedMap<>();

    static{
        map.putAll(
                "fire", new EffectEntry(Fx.fire)
        );

        map.each((n, e) -> e.name = n);
    }

    public static Iterable<EffectEntry> entries(){
        return map.orderedKeys().map(s -> map.get(s));
    }

    public static @Nullable EffectEntry get(String name){
        return map.get(name);
    }

    /** Adds an effect entry to the map. */
    public static void add(String name, EffectEntry entry){
        entry.name = name;
        map.put(name, entry);
    }

    public static String[] all(){
        return map.orderedKeys().toArray(String.class);
    }

    public static class EffectEntry{
        public String name = "";
        public Effect effect;
        public boolean size, rotate, color;
        public @Nullable Class<?> data;
        /** cached bounds for this effect, negative if unset */
        public float bounds = -1f;

        public EffectEntry(Effect effect){
            this.effect = effect;
        }

        public EffectEntry bounds(float bounds){
            this.bounds = bounds;
            return this;
        }

        public EffectEntry name(String name){
            this.name = name;
            return this;
        }

        public EffectEntry size(){
            size = true;
            return this;
        }

        public EffectEntry rotate(){
            rotate = true;
            return this;
        }

        public EffectEntry color(){
            color = true;
            return this;
        }

        public EffectEntry data(Class<?> data){
            this.data = data;
            return this;
        }
    }
}
