package io.github.masyumero.mekuniverse.common.registry;

import io.github.masyumero.mekuniverse.MekanismUniverse;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;

public interface MekUniverseItems {

    ItemDeferredRegister ITEMS = new ItemDeferredRegister(MekanismUniverse.MODID);

    ItemRegistryObject<Item> SMALL_SOLAR_POWER_SATELLITE = ITEMS.register("small_solar_power_satellite");
    ItemRegistryObject<Item> SOLAR_POWER_SATELLITE_CONSTELLATION = ITEMS.register("solar_power_satellite_constellation", properties -> new Item(properties.stacksTo(4)));

    ItemRegistryObject<Item> ENERGY_GEM = ITEMS.register("energy_gem");
    ItemRegistryObject<Item> ENERGY_CLUSTER = ITEMS.register("energy_cluster");
    ItemRegistryObject<Item> ENERGY_MASS = ITEMS.register("energy_mass");
    ItemRegistryObject<Item> ENERGY_CONDENSE = ITEMS.register("energy_condense");
    ItemRegistryObject<Item> ENERGY_CORE = ITEMS.register("energy_core");
    ItemRegistryObject<Item> ENERGY_DENSE_CORE = ITEMS.register("energy_dense_core");
    ItemRegistryObject<Item> ENERGY_COMPACT_CORE = ITEMS.register("energy_compact_core");
    ItemRegistryObject<Item> ENERGY_SINGULARITY = ITEMS.register("energy_singularity");

    static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
