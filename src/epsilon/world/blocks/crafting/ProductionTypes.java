package epsilon.world.blocks.crafting;

public enum ProductionTypes {
    // TODO: make more types
    // Passive, basic production type. Works like in basic mindustry
    passive,

    // Active, increases efficiency, but requires to be activated
    active;

    public static final ProductionTypes[] all = values();
}
