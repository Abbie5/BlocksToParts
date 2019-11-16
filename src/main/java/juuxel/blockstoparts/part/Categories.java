/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/. */

package juuxel.blockstoparts.part;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Categories {
    public static final Categories EMPTY = Categories.builder().build();

    private final Set<String> self;
    private final Set<String> overlapping;

    protected Categories(Set<String> self, Set<String> overlapping) {
        this.self = self;
        this.overlapping = overlapping;
    }

    public boolean matches(String category) {
        return self.contains(category);
    }

    public boolean canOverlapWith(String category) {
        return overlapping.contains(category);
    }

    public Set<String> getSelfCategories() {
        return Collections.unmodifiableSet(self);
    }

    public Set<String> getOverlappingCategories() {
        return Collections.unmodifiableSet(overlapping);
    }

    public boolean canOverlapWith(Categories other) {
        for (String category : other.getSelfCategories()) {
            if (canOverlapWith(category)) return true;
        }

        for (String category : other.getOverlappingCategories()) {
            if (matches(category)) return true;
        }

        return false;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Set<String> self = new HashSet<>();
        private final Set<String> overlapping = new HashSet<>();

        protected Builder() {}

        public Builder add(String category) {
            self.add(category);
            return this;
        }

        public Builder overlap(String category) {
            overlapping.add(category);
            return this;
        }

        public Categories build() {
            return new Categories(self, overlapping);
        }
    }
}
