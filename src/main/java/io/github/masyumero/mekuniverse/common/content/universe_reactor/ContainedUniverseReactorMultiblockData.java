package io.github.masyumero.mekuniverse.common.content.universe_reactor;

import com.fxd927.mekanismelements.common.registries.MSGases;
import io.github.masyumero.mekuniverse.MekanismUniverseLang;
import io.github.masyumero.mekuniverse.api.NBTConstants;
import io.github.masyumero.mekuniverse.common.inventory.slot.CUReactorInventorySlot;
import io.github.masyumero.mekuniverse.common.registry.MekUniverseItems;
import lombok.Getter;
import lombok.Setter;
import mekanism.api.Action;
import mekanism.api.AutomationType;
import mekanism.api.chemical.gas.IGasTank;
import mekanism.api.energy.IEnergyContainer;
import mekanism.api.math.FloatingLong;
import mekanism.common.capabilities.chemical.multiblock.MultiblockChemicalTankBuilder;
import mekanism.common.capabilities.energy.VariableCapacityEnergyContainer;
import mekanism.common.integration.computer.SpecialComputerMethodWrapper;
import mekanism.common.integration.computer.annotation.WrappingComputerMethod;
import mekanism.common.inventory.container.sync.dynamic.ContainerSync;
import mekanism.common.lib.multiblock.MultiblockData;
import mekanism.common.registries.MekanismGases;
import mekanism.common.util.NBTUtils;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.AABB;

import java.util.List;

public class ContainedUniverseReactorMultiblockData extends MultiblockData {

    public static final String OUTPUT_TAB = "output";
    public static final String FUEL_TAB = "fuel";

    private static final int TIER_LIMIT = 26;
    private static final FloatingLong BASE_USE_ENERGY = FloatingLong.create(1_073_741_824L);
    private static final long BASE_HELIUM_GENERATION = 4_000L;

    @ContainerSync
    @Getter
    private boolean fieldActive = false;
    @ContainerSync
    @Getter
    private boolean active = false;

    @ContainerSync
    public IEnergyContainer energyContainer;

    @ContainerSync(tags = OUTPUT_TAB)
    public IEnergyContainer energyOutputContainer;
    @ContainerSync(tags = OUTPUT_TAB)
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getHeliumOutput", "getHeliumOutputCapacity", "getHeliumOutputNeeded", "getHeliumOutputFilledPercentage"}, docPlaceholder = "helium output tank")
    public IGasTank heliumOutputTank;

    @ContainerSync(tags = FUEL_TAB)
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getHydrogen", "getHydrogenCapacity", "getHydrogenNeeded",
            "getHydrogenFilledPercentage"}, docPlaceholder = "hydrogen tank")
    public IGasTank hydrogenTank;
    @ContainerSync(tags = FUEL_TAB)
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getHelium", "getHeliumCapacity", "getHeliumNeeded",
            "getHeliumFilledPercentage"}, docPlaceholder = "helium tank")
    public IGasTank heliumTank;
    @ContainerSync
    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerChemicalTankWrapper.class, methodNames = {"getAntimatter", "getAntimatterCapacity", "getAntimatterNeeded",
            "getAntimatterFilledPercentage"}, docPlaceholder = "antimatter tank")
    public IGasTank antimatterTank;

    @WrappingComputerMethod(wrapper = SpecialComputerMethodWrapper.ComputerIInventorySlotWrapper.class, methodNames = "getDenseCore", docPlaceholder = "denseCore slot")
    final CUReactorInventorySlot reactorSlot;

    final CUReactorInventorySlot reactorSatelliteSlot;

    @Getter
    @Setter
    @ContainerSync
    private int tier;
    @Getter
    @Setter
    @ContainerSync
    private long requestHydrogenAmount;
    @Getter
    @Setter
    @ContainerSync
    private long requestHeliumAmount;
    private final FloatingLong useEnergyAmount = BASE_USE_ENERGY.multiply(tier * 1_000L);
    private final long useAntimatterAmount = 100_000L;

    private AABB deathZone;

    public ContainedUniverseReactorMultiblockData(BlockEntity tile) {
        super(tile);
        long capcapcap = Long.MAX_VALUE;

        gasTanks.add(hydrogenTank = MultiblockChemicalTankBuilder.GAS.create(this, () -> capcapcap, gas -> gas == MekanismGases.HYDROGEN.getChemical(), createSaveAndComparator()));
        gasTanks.add(heliumTank = MultiblockChemicalTankBuilder.GAS.create(this, () -> capcapcap, gas -> gas == MSGases.HELIUM.getChemical(), createSaveAndComparator()));
        gasTanks.add(antimatterTank = MultiblockChemicalTankBuilder.GAS.create(this, () -> useAntimatterAmount, gas -> gas == MekanismGases.ANTIMATTER.getChemical(), createSaveAndComparator()));
        gasTanks.add(heliumOutputTank = MultiblockChemicalTankBuilder.GAS.output(this, () -> capcapcap, gas -> gas == MSGases.HELIUM.getChemical(), this));
        energyContainers.add(energyContainer = VariableCapacityEnergyContainer.create(FloatingLong.create(capcapcap), this));
        energyContainers.add(energyOutputContainer = VariableCapacityEnergyContainer.output(FloatingLong.create(capcapcap), this));
        inventorySlots.add(reactorSlot = CUReactorInventorySlot.at(stack -> stack.getItem() == MekUniverseItems.DENSE_CORE_ENERGY.asItem(), this, 80, 39));
        inventorySlots.add(reactorSatelliteSlot = CUReactorInventorySlot.at(stack -> stack.getItem() == MekUniverseItems.SOLAR_POWER_SATELLITE_CONSTELLATION.asItem(), this, 80, 59));
    }

    @Override
    public void onCreated(Level world) {
        super.onCreated(world);
        deathZone = new AABB(getMinPos().offset(1, 1, 1), getMaxPos());
    }

    @Override
    public void readUpdateTag(CompoundTag tag) {
        super.readUpdateTag(tag);
        NBTUtils.setBooleanIfPresent(tag, NBTConstants.ACTIVE, this::setActive);
        NBTUtils.setBooleanIfPresent(tag, NBTConstants.FIELD_ACTIVE, this::setFieldActive);
        NBTUtils.setIntIfPresent(tag, NBTConstants.TIER, this::setTier);
    }

    @Override
    public void writeUpdateTag(CompoundTag tag) {
        super.writeUpdateTag(tag);
        tag.putBoolean(NBTConstants.ACTIVE, isActive());
        tag.putBoolean(NBTConstants.FIELD_ACTIVE, isFieldActive());
        tag.putInt(NBTConstants.TIER, getTier());
    }

    private boolean hasDenseCore() {
        if (!reactorSlot.isEmpty()) {
            ItemStack denseCore = reactorSlot.getStack();
            return denseCore.getItem() == MekUniverseItems.DENSE_CORE_ENERGY.asItem();
        }
        return false;
    }

    private boolean hasSatellite() {
        if (!reactorSatelliteSlot.isEmpty()) {
            ItemStack satellite = reactorSatelliteSlot.getStack();
            return satellite.getItem() == MekUniverseItems.SOLAR_POWER_SATELLITE_CONSTELLATION.asItem();
        }
        return false;
    }

    @Override
    public boolean tick(Level world) {
        boolean needsPacket = super.tick(world);
        if (!isFieldActive()) {
            if (hasDenseCore() && antimatterTank.getStored() >= useAntimatterAmount) {
                consumeMaterialToVoid();
            }
        }

        if (isFieldActive()) {
            updateRequestGasesAmount();
            if (hydrogenTank.getStored() >= requestHydrogenAmount && heliumTank.getStored() >= requestHeliumAmount) {
                incrementTier();
            }

            if (energyContainer.getEnergy().greaterOrEqual(useEnergyAmount) && hydrogenTank.getStored() >= requestHydrogenAmount / 2 && heliumTank.getStored() >= requestHeliumAmount / 2) {
                consumeMaterialToStar();
            } else {
                decrementTier();
                updateRequestGasesAmount();
                if (getTier() == 0) {
                    setActive(false);
                }
            }

            if (hasSatellite()) {
                if (world.getRandom().nextInt(100000) == 0) {
                    reactorSatelliteSlot.extractItem(1, Action.EXECUTE, AutomationType.INTERNAL);
                }
                if (getSatelliteCount() < getTier()) {
                    heliumOutputTank.insert(MSGases.HELIUM.getStack(BASE_HELIUM_GENERATION * getSatelliteCount()), Action.EXECUTE, AutomationType.INTERNAL);
                    energyOutputContainer.insert(FloatingLong.create(Math.pow(4, getSatelliteCount())), Action.EXECUTE, AutomationType.INTERNAL);
                } else if (getSatelliteCount() >= getTier() && getTier() > 0) {
                    heliumOutputTank.insert(MSGases.HELIUM.getStack(BASE_HELIUM_GENERATION * getTier()), Action.EXECUTE, AutomationType.INTERNAL);
                    energyOutputContainer.insert(FloatingLong.create(Math.pow(4, getTier())), Action.EXECUTE, AutomationType.INTERNAL);
                }
            }
        }

        if (isFieldActive()) {
            fellVoid(world);
        }

        return needsPacket;
    }

    private void fellVoid(Level world) {
        if (world.getRandom().nextInt() % 20 != 0) {
            return;
        }
        List<Entity> entities = getWorld().getEntitiesOfClass(Entity.class, deathZone);

        for (Entity entity : entities) {
            if (entity instanceof Player player) {
                player.displayClientMessage(MekanismUniverseLang.FELL_VOID.translate(), true);
            }
            entity.teleportRelative(0, -10000, 0);
        }
    }

    private void consumeMaterialToStar() {
        energyContainer.extract(useEnergyAmount, Action.EXECUTE, AutomationType.INTERNAL);
        hydrogenTank.extract(requestHydrogenAmount / 2, Action.EXECUTE, AutomationType.INTERNAL);
        heliumTank.extract(requestHeliumAmount / 2, Action.EXECUTE, AutomationType.INTERNAL);
        setActive(true);
    }

    private void consumeMaterialToVoid() {
        antimatterTank.extract(useAntimatterAmount, Action.EXECUTE, AutomationType.INTERNAL);
        reactorSlot.extractItem(1, Action.EXECUTE, AutomationType.INTERNAL);
        setFieldActive(true);
    }

    private void updateRequestGasesAmount() {
        setRequestHydrogenAmount(896L * (1L << (2 * (tier))));
        setRequestHeliumAmount(384L * (1L << (2 * (tier))));
    }

    public void setActive(boolean active) {
        if (this.active != active) {
            this.active = active;
            markDirty();
        }
    }

    public void setFieldActive(boolean active) {
        if (this.fieldActive != active) {
            this.fieldActive = active;
            markDirty();
        }
    }

    private void incrementTier() {
        setTier(Mth.clamp(getTier() + 1, 0, TIER_LIMIT));
    }

    private void decrementTier() {
        setTier(Mth.clamp(getTier() - 1, 0, TIER_LIMIT));
    }

    public int getSatelliteCount() {
        return reactorSatelliteSlot.getCount();
    }

    public FloatingLong getPassiveGeneration(boolean current) {
        if (current) {
            if (hasSatellite()) {
                if (getSatelliteCount() < getTier()) {
                    return FloatingLong.create(Math.pow(4, getSatelliteCount()));
                } else if (getSatelliteCount() >= getTier() && getTier() > 0) {
                    return FloatingLong.create(Math.pow(4, getTier()));
                }
            } else {
                return FloatingLong.create(0);
            }
        }
        if (getTier() == 0) {
            return FloatingLong.create(0);
        }
        return FloatingLong.create(Math.pow(4, getTier()));
    }

    public long getHeliumPerTick(boolean current) {
        if (current) {
            if (hasSatellite()) {
                if (getSatelliteCount() < getTier()) {
                    return BASE_HELIUM_GENERATION * getSatelliteCount();
                } else if (getSatelliteCount() >= getTier() && getTier() > 0) {
                    return BASE_HELIUM_GENERATION * getTier();
                }
            } else {
                return 0;
            }
        }
        if (getTier() == 0) {
            return 0;
        }
        return BASE_HELIUM_GENERATION * getTier();
    }
}
