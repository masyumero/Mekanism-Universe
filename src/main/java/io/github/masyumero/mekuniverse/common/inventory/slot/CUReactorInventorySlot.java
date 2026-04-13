package io.github.masyumero.mekuniverse.common.inventory.slot;

import io.github.masyumero.mekuniverse.common.registry.MekUniverseItems;
import mekanism.api.IContentsListener;
import mekanism.api.annotations.NothingNullByDefault;
import mekanism.common.inventory.slot.BasicInventorySlot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Predicate;

@NothingNullByDefault
public class CUReactorInventorySlot extends BasicInventorySlot {

    public static CUReactorInventorySlot at(Predicate<@NotNull ItemStack> validator, @Nullable IContentsListener listener, int x, int y) {
        Objects.requireNonNull(validator, "Item validity check cannot be null");
        return new CUReactorInventorySlot(validator, listener, x, y);
    }

    protected CUReactorInventorySlot(Predicate<ItemStack> validator, IContentsListener listener, int x, int y) {
        super(notExternal, alwaysTrueBi, validator, listener, x, y);
    }

    @Override
    public int getLimit(ItemStack stack) {
        if (stack.getItem() == MekUniverseItems.SOLAR_POWER_SATELLITE_CONSTELLATION.asItem()) {
            return 26;
        }
        return stack.getMaxStackSize() * 16;
    }
}
