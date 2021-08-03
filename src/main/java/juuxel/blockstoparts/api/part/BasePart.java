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
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
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

    protected final void removeAndDrop() {
        drop();
        getWorld().syncWorldEvent(2001, getPos(), Block.getRawIdFromState(getBlockState()));
        this.holder.remove();
    }

    private void drop() {
        Vec3d pos = Vec3d.ofCenter(getPos());
        LootContext context = new LootContext.Builder((ServerWorld) getWorld())
            .random(getWorld().getRandom())
            .parameter(LootContextParameters.BLOCK_STATE, getBlockState())
            .parameter(LootContextParameters.ORIGIN, pos)
            .parameter(LootContextParameters.TOOL, ItemStack.EMPTY)
            .build(LootContextTypes.BLOCK);

        ItemDropTarget dropTarget = new ItemDropTarget() {
            @Override
            public void drop(ItemStack stack) {
                drop(stack, pos);
            }

            @Override
            public void drop(ItemStack stack, Vec3d pos) {
                ItemScatterer.spawn(getWorld(), pos.getX(), pos.getY(), pos.getZ(), stack);
            }

            @Override
            public void drop(ItemStack stack, Vec3d pos, Vec3d velocity) {
                drop(stack, pos);
            }

            @Override
            public boolean dropsAsEntity() {
                return false;
            }
        };

        addDrops(dropTarget, context);
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
