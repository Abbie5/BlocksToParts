/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.blockstoparts.api.model;

import net.minecraft.block.BlockState;

/**
 * A static model key that caches its state.
 */
public final class StaticVanillaModelKey extends VanillaModelKey {
    private final BlockState state;

    public StaticVanillaModelKey(BlockState state) {
        this.state = state;
    }

    @Override
    public BlockState getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return state.equals(((StaticVanillaModelKey) o).state);
    }

    @Override
    public int hashCode() {
        return state.hashCode();
    }
}
