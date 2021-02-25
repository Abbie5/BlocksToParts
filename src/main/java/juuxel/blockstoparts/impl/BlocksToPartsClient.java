/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.blockstoparts.impl;

import alexiil.mc.lib.multipart.api.render.PartStaticModelRegisterEvent;
import juuxel.blockstoparts.api.model.DynamicVanillaModelKey;
import juuxel.blockstoparts.api.model.StaticVanillaModelKey;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public final class BlocksToPartsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        PartStaticModelRegisterEvent.EVENT.register(renderer -> {
            renderer.register(DynamicVanillaModelKey.class, new VanillaPartModel<>());
            renderer.register(StaticVanillaModelKey.class, new VanillaPartModel<>());
        });
    }
}
