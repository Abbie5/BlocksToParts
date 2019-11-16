/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package juuxel.blockstoparts.part;

import alexiil.mc.lib.multipart.api.AbstractPart;
import alexiil.mc.lib.multipart.api.MultipartContainer;
import alexiil.mc.lib.multipart.api.MultipartHolder;
import alexiil.mc.lib.multipart.api.PartDefinition;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class BasePart extends AbstractPart implements Categorizable {
    public BasePart(PartDefinition def, MultipartHolder holder) {
        super(def, holder);
    }

    public abstract BlockState getBlockState();

    protected final World getWorld() {
        return this.holder.getContainer().getMultipartWorld();
    }

    protected final BlockPos getPos() {
        return this.holder.getContainer().getMultipartPos();
    }

    protected final void removeAndDrop() {
        DefaultedList<ItemStack> stacks = DefaultedList.of();
        addDrops(stacks);
        MultipartContainer container = this.holder.getContainer();
        World world = container.getMultipartWorld();
        BlockPos pos = container.getMultipartPos();
        ItemScatterer.spawn(world, pos, stacks);
        world.playLevelEvent(2001, pos, Block.getRawIdFromState(getBlockState()));
        this.holder.remove();
    }

    @Override
    public ItemStack getPickStack() {
        return new ItemStack(getBlockState().getBlock());
    }

    @Override
    public Categories getCategories() {
        return Categories.EMPTY;
    }

    @Override
    public boolean canOverlapWith(AbstractPart other) {
        return other instanceof Categorizable && getCategories().canOverlapWith(((Categorizable) other).getCategories());
    }
}
