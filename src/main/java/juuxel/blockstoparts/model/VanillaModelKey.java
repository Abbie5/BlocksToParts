/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.blockstoparts.model;

import alexiil.mc.lib.multipart.api.render.PartModelKey;
import net.minecraft.block.BlockState;

/**
 * A model key that pulls the model from a block state.
 */
public abstract class VanillaModelKey extends PartModelKey {
    VanillaModelKey() {
    }

    public abstract BlockState getState();
}
