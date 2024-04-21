/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.blockstoparts.api.util;

import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.loot.context.LootContextParameters;

public final class BtpUtil {
    /**
     * Converts a multipart loot context to a block loot context builder.
     *
     * @param params the multipart loot context
     * @return the created builder
     */
    public static LootContextParameterSet.Builder toBlockLootContext(LootContextParameterSet params) {
        return new LootContextParameterSet.Builder(params.getWorld())
            .luck(params.getLuck())
            .add(LootContextParameters.BLOCK_STATE, params.get(LootContextParameters.BLOCK_STATE))
            .add(LootContextParameters.ORIGIN, params.get(LootContextParameters.ORIGIN))
            .add(LootContextParameters.TOOL, params.get(LootContextParameters.TOOL))
            .addOptional(LootContextParameters.THIS_ENTITY, params.getOptional(LootContextParameters.THIS_ENTITY))
            .addOptional(LootContextParameters.BLOCK_ENTITY, params.getOptional(LootContextParameters.BLOCK_ENTITY))
            .addOptional(LootContextParameters.EXPLOSION_RADIUS, params.getOptional(LootContextParameters.EXPLOSION_RADIUS));
    }
}
