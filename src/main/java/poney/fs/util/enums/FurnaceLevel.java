package poney.fs.util.enums;

import net.minecraft.util.StringIdentifiable;

public enum FurnaceLevel implements StringIdentifiable {
    STONE("stone",1),
    COPPER("copper",1),
    IRON("iron",2),
    GOLD("gold",3),
    DIAMOND("diamond",4),
    EMERALD("emerald",5),
    NETHERITE("netherite",10);

    private final String name;
    private final int level;

    private FurnaceLevel(String name,int level) {
        this.name = name;
        this.level = level;
    }

    public String toString() {
        return this.asString();
    }

    public String asString() {
        return this.name;
    }
    public int asLevel() {
        return this.level;
    }

    public int NextLevel(){
        int next = this.ordinal() + 1;
        return Math.min(next, 6);
    }
}
