package io.github.masyumero.mekuniverse.common.content.universe_reactor;

import io.github.masyumero.mekuniverse.api.NBTConstants;
import mekanism.common.lib.multiblock.MultiblockCache;
import net.minecraft.nbt.CompoundTag;

public class ContainedUniverseReactorCache extends MultiblockCache<ContainedUniverseReactorMultiblockData> {

    private boolean fieldActive;
    private boolean active;
    private int tier = -1;

    private int getTier() {
        if (tier == -1) {
            return 0;
        }
        return tier;
    }

    @Override
    public void merge(MultiblockCache<ContainedUniverseReactorMultiblockData> mergeCache, RejectContents rejectContents) {
        super.merge(mergeCache, rejectContents);
        fieldActive |= ((ContainedUniverseReactorCache) mergeCache).fieldActive;
        active |= ((ContainedUniverseReactorCache) mergeCache).active;
        tier = Math.max(tier, ((ContainedUniverseReactorCache) mergeCache).tier);
    }

    @Override
    public void apply(ContainedUniverseReactorMultiblockData data) {
        super.apply(data);
        data.setFieldActive(fieldActive);
        data.setActive(active);if (tier >= 0) {
            data.setTier(tier);
        }
    }

    @Override
    public void sync(ContainedUniverseReactorMultiblockData data) {
        super.sync(data);
        fieldActive = data.isFieldActive();
        active = data.isActive();
        tier = data.getTier();
    }

    @Override
    public void load(CompoundTag nbtTags) {
        super.load(nbtTags);
        fieldActive = nbtTags.getBoolean(NBTConstants.FIELD_ACTIVE);
        active = nbtTags.getBoolean(NBTConstants.ACTIVE);
        tier = nbtTags.getInt(NBTConstants.TIER);
    }

    @Override
    public void save(CompoundTag nbtTags) {
        super.save(nbtTags);
        nbtTags.putBoolean(NBTConstants.FIELD_ACTIVE, fieldActive);
        nbtTags.putBoolean(NBTConstants.ACTIVE, active);
        nbtTags.putInt(NBTConstants.TIER, getTier());
    }
}
