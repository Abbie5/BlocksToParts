/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.blockstoparts.impl;

import alexiil.mc.lib.multipart.api.render.PartModelBaker;
import alexiil.mc.lib.multipart.api.render.PartRenderContext;
import juuxel.blockstoparts.api.model.VanillaModelKey;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedQuad;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Environment(EnvType.CLIENT)
final class VanillaPartModel<K extends VanillaModelKey> implements PartModelBaker<K> {
    /**
     * A cache for ForwardingBakedModels that wrap the original models.
     * Multipart-format blockstate files create models that don't really work with passing
     * the model to the fallback consumer, so we have to create wrapping models that give the part's corresponding
     * block state to BakedModel.getQuads.
     */
    private final Map<BlockState, BakedModel> modelWrappers = new HashMap<>();

    private BakedModel getWrapper(K key) {
        BlockState keyState = key.getState();

        if (!modelWrappers.containsKey(keyState)) {
            modelWrappers.put(keyState, new ForwardingBakedModel() {
                {
                    wrapped = MinecraftClient.getInstance()
                        .getBakedModelManager()
                        .getBlockModels()
                        .getModel(keyState);
                }

                @Override
                public List<BakedQuad> getQuads(BlockState blockState, Direction face, Random random) {
                    return super.getQuads(keyState, face, random);
                }
            });
        }

        return modelWrappers.get(keyState);
    }

    @Override
    public void emitQuads(K key, PartRenderContext ctx) {
        ctx.fallbackConsumer().accept(getWrapper(key));
    }
}
