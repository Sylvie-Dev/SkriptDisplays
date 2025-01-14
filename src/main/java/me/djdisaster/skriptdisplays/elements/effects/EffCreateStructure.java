package me.djdisaster.skriptdisplays.elements.effects;

import ch.njol.skript.Skript;
import ch.njol.skript.classes.Changer;
import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.skript.lang.Variable;
import ch.njol.util.Kleenean;
import me.djdisaster.skriptdisplays.structure.bounding.BoundingBoxAB;
import me.djdisaster.skriptdisplays.structure.internal.DisplayStructure;
import org.bukkit.Location;
import org.bukkit.event.Event;
import org.jetbrains.annotations.Nullable;

public class EffCreateStructure extends Effect {
    static {
        Skript.registerEffect(EffCreateStructure.class, "create [a [new]] structure from %location% to %location% [and [store [it]] in %-object%]");
    }

    private Expression<Location> location;
    private Expression<Location> location2;
    private Variable<?> variable;

    @SuppressWarnings("unchecked")
    @Override
    public boolean init(Expression<?>[] expressions, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        location = (Expression<Location>) expressions[0];
        location2 = (Expression<Location>) expressions[1];
        variable = (Variable<?>) expressions[2];
        return true;
    }

    @Override
    public String toString(@Nullable Event event, boolean debug) {
        return "";
    }

    @Override
    protected void execute(Event event) {
        Location loc = location.getSingle(event);
        Location loc2 = location2.getSingle(event);
        if (loc == null || loc2 == null) return;

        DisplayStructure s = new DisplayStructure(new BoundingBoxAB(loc, loc2));

        if (variable != null) variable.change(event, new Object[]{s}, Changer.ChangeMode.SET);
    }
}
