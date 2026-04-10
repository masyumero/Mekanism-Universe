package io.github.masyumero.mekuniverse.common.registry;

import com.fxd927.mekanismmoremachine.common.item.EnergyItem;
import io.github.masyumero.mekuniverse.MekanismUniverse;
import mekanism.common.registration.impl.ItemDeferredRegister;
import mekanism.common.registration.impl.ItemRegistryObject;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;

public interface MekUniverseItems {

    ItemDeferredRegister ITEMS = new ItemDeferredRegister(MekanismUniverse.MODID);

    ItemRegistryObject<Item> SMALL_SOLAR_POWER_SATELLITE = ITEMS.register("small_solar_power_satellite");
    ItemRegistryObject<Item> SOLAR_POWER_SATELLITE_CONSTELLATION = ITEMS.register("solar_power_satellite_constellation");

    ItemRegistryObject<EnergyItem> GEM_ENERGY = ITEMS.register("gem_energy", properties ->  new EnergyItem(properties.rarity(Rarity.UNCOMMON)));
    ItemRegistryObject<EnergyItem> CLUSTER_ENERGY = ITEMS.register("cluster_energy", properties ->  new EnergyItem(properties.rarity(Rarity.UNCOMMON)));
    ItemRegistryObject<EnergyItem> MASS_ENERGY = ITEMS.register("mass_energy", properties ->  new EnergyItem(properties.rarity(Rarity.UNCOMMON)));
    ItemRegistryObject<EnergyItem> CONDENSE_ENERGY = ITEMS.register("condense_energy", properties ->  new EnergyItem(properties.rarity(Rarity.RARE)));
    ItemRegistryObject<EnergyItem> CORE_ENERGY = ITEMS.register("core_energy", properties ->  new EnergyItem(properties.rarity(Rarity.RARE)));
    ItemRegistryObject<EnergyItem> DENSE_CORE_ENERGY = ITEMS.register("dense_core_energy", properties ->  new EnergyItem(properties.rarity(Rarity.EPIC)));
    ItemRegistryObject<EnergyItem> COMPACT_CORE_ENERGY = ITEMS.register("compact_core_energy", properties ->  new EnergyItem(properties.rarity(Rarity.EPIC)));
    ItemRegistryObject<EnergyItem> SINGULARITY_ENERGY = ITEMS.register("singularity_energy", properties ->  new EnergyItem(properties.rarity(Rarity.EPIC)));

    static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}