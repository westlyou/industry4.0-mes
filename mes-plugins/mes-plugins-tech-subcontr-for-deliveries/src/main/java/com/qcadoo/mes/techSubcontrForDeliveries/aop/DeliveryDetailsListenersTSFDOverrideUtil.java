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
package com.qcadoo.mes.techSubcontrForDeliveries.aop;

import static com.qcadoo.mes.techSubcontrForDeliveries.constants.DeliveredProductFieldsTSFD.OPERATION;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.qcadoo.mes.deliveries.constants.DeliveredProductFields;
import com.qcadoo.mes.deliveries.constants.OrderedProductFields;
import com.qcadoo.model.api.Entity;

@Service
public class DeliveryDetailsListenersTSFDOverrideUtil {

    public void fillDeliveredProductOperation(final Entity orderedProduct, final Entity deliveredProduct) {
        deliveredProduct.setField(OPERATION, orderedProduct.getBelongsToField(OPERATION));
    }

    public boolean checkIfProductsAndOperationsAreSame(final Entity orderedProduct, final Entity deliveredProduct) {
        return (checkIfProductsAreSame(orderedProduct, deliveredProduct)
                && checkIfOperationsAreSame(orderedProduct, deliveredProduct));
    }

    private boolean checkIfProductsAreSame(final Entity orderedProduct, final Entity deliveredProduct) {
        Entity orderedAdditionalCode = orderedProduct.getBelongsToField(OrderedProductFields.ADDITIONAL_CODE);
        Entity deliveredAdditionalCode = deliveredProduct.getBelongsToField(DeliveredProductFields.ADDITIONAL_CODE);
        boolean additionalCodesMatching = orderedAdditionalCode == null && deliveredAdditionalCode == null;
        if (orderedAdditionalCode != null && deliveredAdditionalCode != null) {
            additionalCodesMatching = orderedAdditionalCode.getId().equals(deliveredAdditionalCode.getId());
        }
        return (orderedProduct.getBelongsToField(OrderedProductFields.PRODUCT).getId()
                .equals(deliveredProduct.getBelongsToField(DeliveredProductFields.PRODUCT).getId())) && additionalCodesMatching;
    }

    private boolean checkIfOperationsAreSame(final Entity orderedProduct, final Entity deliveredProduct) {
        return (checkIfOperationsAreNull(orderedProduct, deliveredProduct)
                || checkIfOperationsAreEqual(orderedProduct, deliveredProduct));
    }

    private boolean checkIfOperationsAreNull(final Entity orderedProduct, final Entity deliveredProduct) {
        return ((orderedProduct.getBelongsToField(OPERATION) == null) && (deliveredProduct.getBelongsToField(OPERATION) == null));
    }

    private boolean checkIfOperationsAreEqual(final Entity orderedProduct, final Entity deliveredProduct) {
        return ((orderedProduct.getBelongsToField(OPERATION) != null) && (deliveredProduct.getBelongsToField(OPERATION) != null)
                && orderedProduct.getBelongsToField(OPERATION).getId()
                        .equals(deliveredProduct.getBelongsToField(OPERATION).getId()));
    }

    public void fillOrderedProductOperation(final Entity orderedProduct, final BigDecimal orderedQuantity,
            final Entity newOrderedProduct) {
        newOrderedProduct.setField(OPERATION, orderedProduct.getBelongsToField(OPERATION));
    }

}
