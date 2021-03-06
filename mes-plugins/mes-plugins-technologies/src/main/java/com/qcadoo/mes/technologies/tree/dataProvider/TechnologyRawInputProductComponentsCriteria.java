/**
 * ***************************************************************************
 * Copyright (c) 2018 RiceFish Limited
 * Project: SmartMES
 * Version: 1.6
 *
 * This file is part of SmartMES.
 *
 * SmartMES is Authorized software; you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation; either version 3 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 * ***************************************************************************
 */
package com.qcadoo.mes.technologies.tree.dataProvider;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.qcadoo.model.api.search.SearchCriterion;
import com.qcadoo.model.api.search.SearchOrder;
import com.qcadoo.model.api.search.SearchProjection;

public final class TechnologyRawInputProductComponentsCriteria {

    public static final String PRODUCT_ALIAS = "product_alias";

    private final Long technologyId;

    private Optional<SearchCriterion> productCriteria = Optional.absent();

    private Optional<SearchProjection> searchProjection = Optional.absent();

    private Optional<SearchOrder> searchOrder = Optional.absent();

    public Optional<SearchCriterion> getProductCriteria() {
        return productCriteria;
    }

    public static TechnologyRawInputProductComponentsCriteria forTechnology(final Long technologyId) {
        return new TechnologyRawInputProductComponentsCriteria(technologyId);
    }

    private TechnologyRawInputProductComponentsCriteria(final Long technologyId) {
        Preconditions.checkArgument(technologyId != null, "Technology id cannot be null.");
        this.technologyId = technologyId;
    }

    public Long getTechnologyId() {
        return technologyId;
    }

    public TechnologyRawInputProductComponentsCriteria setProductCriteria(final SearchCriterion productCriteria) {
        this.productCriteria = Optional.fromNullable(productCriteria);
        return this;
    }

    public Optional<SearchProjection> getSearchProjection() {
        return searchProjection;
    }

    public TechnologyRawInputProductComponentsCriteria setSearchProjection(final SearchProjection searchProjection) {
        this.searchProjection = Optional.fromNullable(searchProjection);
        return this;
    }

    public Optional<SearchOrder> getSearchOrder() {
        return searchOrder;
    }

    public TechnologyRawInputProductComponentsCriteria setSearchOrder(final SearchOrder searchOrder) {
        this.searchOrder = Optional.fromNullable(searchOrder);
        return this;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        TechnologyRawInputProductComponentsCriteria rhs = (TechnologyRawInputProductComponentsCriteria) obj;
        return new EqualsBuilder().append(this.technologyId, rhs.technologyId).append(this.productCriteria, rhs.productCriteria)
                .append(this.searchProjection, rhs.searchProjection).append(this.searchOrder, rhs.searchOrder).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(technologyId).append(productCriteria).append(searchProjection).append(searchOrder)
                .toHashCode();
    }
}
