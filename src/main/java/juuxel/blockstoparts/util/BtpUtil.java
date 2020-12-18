/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.blockstoparts.util;

import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;

public final class BtpUtil {
    /**
     * Converts a multipart loot context to a block loot context builder.
     *
     * @param context the multipart loot context
     * @return the created builder
     */
    public static LootContext.Builder toBlockLootContext(LootContext context) {
        return new LootContext.Builder(context.getWorld())
            .random(context.getRandom())
            .luck(context.getLuck())
            .parameter(LootContextParameters.BLOCK_STATE, context.get(LootContextParameters.BLOCK_STATE))
            .parameter(LootContextParameters.ORIGIN, context.get(LootContextParameters.ORIGIN))
            .parameter(LootContextParameters.TOOL, context.get(LootContextParameters.TOOL))
            .optionalParameter(LootContextParameters.THIS_ENTITY, context.get(LootContextParameters.THIS_ENTITY))
            .optionalParameter(LootContextParameters.BLOCK_ENTITY, context.get(LootContextParameters.BLOCK_ENTITY))
            .optionalParameter(LootContextParameters.EXPLOSION_RADIUS, context.get(LootContextParameters.EXPLOSION_RADIUS));
    }
}
