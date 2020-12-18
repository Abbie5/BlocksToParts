/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.blockstoparts.category;

/**
 * A part with a {@link CategorySet}.
 */
public interface Categorizable {
    /**
     * Gets the category set of this part.
     *
     * @return the category set
     */
    CategorySet getCategories();
}
