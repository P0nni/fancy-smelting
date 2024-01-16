package poney.fs.block.custom;

import net.minecraft.util.StringIdentifiable;

public enum FurnaceFuel implements StringIdentifiable {
    EMPTY("empty"),
    LAVA("lava"),
    MAGMA("magma");

    private final String name;

    private FurnaceFuel(String name) {
        this.name = name;
    }

    public String toString() {
        return this.asString();
    }

    public String asString() {
        return this.name;
    }
}
