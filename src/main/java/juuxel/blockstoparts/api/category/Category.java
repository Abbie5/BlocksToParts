/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.blockstoparts.api.category;

import net.minecraft.util.Identifier;

import java.util.HashMap;
import java.util.Map;

/**
 * An identified category for parts.
 */
public final class Category {
    private static final Map<Identifier, Category> REGISTRY = new HashMap<>();
    private final Identifier id;

    private Category(Identifier id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof Category && id.equals(((Category) obj).id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    /**
     * Gets an existing category or registers one if not found.
     *
     * @param id the category ID
     * @return the category
     */
    public static Category getOrRegister(Identifier id) {
        return REGISTRY.computeIfAbsent(id, Category::new);
    }
}
