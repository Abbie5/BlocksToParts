/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.blockstoparts.api.part;

import alexiil.mc.lib.multipart.api.AbstractPart;
import alexiil.mc.lib.multipart.api.MultipartHolder;
import alexiil.mc.lib.multipart.api.PartDefinition;
import juuxel.blockstoparts.api.category.Categorizable;
import juuxel.blockstoparts.api.category.CategorySet;
import juuxel.blockstoparts.api.util.BtpUtil;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BasePart extends AbstractPart implements Categorizable {
    public BasePart(PartDefinition def, MultipartHolder holder) {
        super(def, holder);
    }

    /**
     * {@return the block state matching this part} Also used for {@link #getClosestBlockState()}.
     */
    public abstract BlockState getBlockState();

    protected final World getWorld() {
        return this.holder.getContainer().getMultipartWorld();
    }

    protected final BlockPos getPos() {
        return this.holder.getContainer().getMultipartPos();
    }

    /**
     * @deprecated Replace with {@link #breakPart()}.
     */
    @Deprecated
    protected final void removeAndDrop() {
        breakPart();
    }

    /**
     * Removes this part from the container and drops its loot.
     * Also plays the block break particles and sounds.
     */
    protected void breakPart() {
        holder.remove(
            MultipartHolder.PartRemoval.DROP_ITEMS,
            MultipartHolder.PartRemoval.BREAK_PARTICLES,
            MultipartHolder.PartRemoval.BREAK_SOUND
        );
    }

    @Override
    public ItemStack getPickStack() {
        return new ItemStack(getBlockState().getBlock());
    }

    @Override
    public CategorySet getCategories() {
        return CategorySet.EMPTY;
    }

    @Override
    public boolean canOverlapWith(AbstractPart other) {
        return other instanceof Categorizable && getCategories().canOverlapWith(((Categorizable) other).getCategories());
    }

    @Override
    public void addDrops(ItemDropTarget target, LootContext context) {
        target.dropAll(getBlockState().getDroppedStacks(BtpUtil.toBlockLootContext(context)));
    }

    @Override
    protected final BlockState getClosestBlockState() {
        return getBlockState();
    }
}
