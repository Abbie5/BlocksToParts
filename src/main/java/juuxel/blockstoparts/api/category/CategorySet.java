/*
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */

package juuxel.blockstoparts.api.category;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * A category set defines which categories a part is in or can overlap with.
 */
public final class CategorySet {
    public static final CategorySet EMPTY = CategorySet.builder().build();

    private final Set<Category> self;
    private final Set<Category> overlapping;

    protected CategorySet(Set<Category> self, Set<Category> overlapping) {
        this.self = self;
        this.overlapping = overlapping;
    }

    public boolean contains(Category category) {
        return self.contains(category);
    }

    public boolean canOverlapWith(Category category) {
        return overlapping.contains(category);
    }

    public Set<Category> getSelfCategories() {
        return Collections.unmodifiableSet(self);
    }

    public Set<Category> getOverlappingCategories() {
        return Collections.unmodifiableSet(overlapping);
    }

    public boolean canOverlapWith(CategorySet other) {
        for (Category category : other.getSelfCategories()) {
            if (canOverlapWith(category)) return true;
        }

        for (Category category : other.getOverlappingCategories()) {
            if (contains(category)) return true;
        }

        return false;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Set<Category> self = new HashSet<>();
        private final Set<Category> overlapping = new HashSet<>();

        protected Builder() {
        }

        public Builder add(Category category) {
            self.add(category);
            return this;
        }

        public Builder overlap(Category category) {
            overlapping.add(category);
            return this;
        }

        public CategorySet build() {
            return new CategorySet(self, overlapping);
        }
    }
}
