package com.example.examplemod.client.sparks;

import dev.engine_room.flywheel.api.visual.Effect;
import dev.engine_room.flywheel.api.visual.EffectVisual;
import dev.engine_room.flywheel.api.visualization.VisualizationContext;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

public class SparkShower implements Effect {
    private final ClientLevel level;    // the world as the game window sees it
    private final Vec3 position;        // where the sparks start (x, y, z)

    // Runs once, when someone writes: new SparkShower(level, position)
    public SparkShower(ClientLevel level, Vec3 position) {
        this.level = level;             // "this.level" = my own box
        this.position = position;
    }

    // A "getter": hands out the private position without letting anyone change it.
    // The artist (SparkShowerVisual) needs it to know where to put the sparks.
    public Vec3 position() {
        return position;
    }

    // Effect's question 1: "Which world are you in?"
    @Override
    public LevelAccessor level() {
        return level;
    }

    // Effect's question 2: "Please give me your artist."
    // ctx = Flywheel's toolbox (the artist gets its stamps from it).
    // partialTick = for smooth movement; not used yet.
    @Override
    public EffectVisual<?> visualize(VisualizationContext ctx, float partialTick) {
        return new SparkShowerVisual(ctx, this);
    }
}
