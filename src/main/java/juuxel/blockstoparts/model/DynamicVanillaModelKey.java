/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.blockstoparts.model;

import juuxel.blockstoparts.part.BasePart;
import net.minecraft.block.BlockState;

/**
 * A dynamic model key that always refreshes its block state when queried.
 */
public final class DynamicVanillaModelKey extends VanillaModelKey {
    private final BasePart part;

    public DynamicVanillaModelKey(BasePart part) {
        this.part = part;
    }

    public BlockState getState() {
        return part.getBlockState();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return part.equals(((DynamicVanillaModelKey) o).part);
    }

    @Override
    public int hashCode() {
        return part.hashCode();
    }
}
