package epsilon.type.weathers;

import arc.math.Mathf;
import mindustry.game.Team;
import mindustry.gen.WeatherState;
import mindustry.type.UnitType;

import static mindustry.Vars.world;

public class UnitSpawnWeather extends SpawnerWeather{
    public UnitStack[] units;
    public float unitChange = 0.05f;
    public Team unitTeam = Team.crux;

    public UnitSpawnWeather(String name){
        super(name);
    }

    @Override
    public void spawnAt(WeatherState state, float x, float y){
        UnitType unit = getUnit();

        var tile = world.tileWorld(x, y);

        if(tile != null && tile.solid()){
            unit.spawn(unitTeam, x, y);
        }
    }

    public UnitType getUnit(){
        for(int i=0;i<units.length;i++){
            var item = (UnitStack)units[i];

            if(Mathf.random()<item.change){
                return item.unit;
            }
        }

        return units[units.length - 1].unit;
    }

    @Override
    public boolean canSpawn(WeatherState state){
        return Mathf.randomBoolean(unitChange * state.intensity);
    }

    public static class UnitStack{
        public UnitType unit;
        public float change;

        public UnitStack(UnitType unit, float change){
            this.unit = unit;
            this.change = change;
        }
    }

    @Deprecated
    public static UnitStack[] with(Object... items) {
        UnitStack[] stacks = new UnitStack[items.length / 2];

        for(int i = 0; i < items.length; i += 2) {
            stacks[i / 2] = new UnitStack((UnitType)items[i], ((Float)items[i + 1]));
        }

        return stacks;
    }
}
