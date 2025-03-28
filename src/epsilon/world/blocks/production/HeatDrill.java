package epsilon.world.blocks.production;

/*

public class HeatDrill extends Drill{
    /** Base heat requirement for 100% efficiency. */
    public float heatRequirement = 9f;
    /** After heat meets this requirement, excess heat will be scaled by this number.*/
    public float overheatScale = 1f;
    /** Maximum possible efficiency after overheat. *//*
    public float maxEfficiency = 4f;

    public HeatDrill(String name){
        super(name);
    }

    @Override
    public void setBars(){
        super.setBars();

        addBar("heat", (HeatCrafterBuild entity) ->
            new Bar(() ->
            Core.bundle.format("bar.heatpercent", (int)(entity.heat + 0.01f), (int)(entity.efficiencyScale() * 100 + 0.01f)),
            () -> Pal.lightOrange,
            () -> entity.heat / heatRequirement));
    }

    @Override
    public void setStats(){
        super.setStats();

        stats.add(Stat.input, heatRequirement, StatUnit.heatUnits);
    }
}*/