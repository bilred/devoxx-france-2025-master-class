/*
 * Copyright (C) 2019 José Paumard
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.paumard.devoxxfr2019.A_lambdas.a_lambdas.util;

import org.paumard.devoxxfr2019.A_lambdas.a_lambdas.model.Circle;

import java.util.List;
import java.util.function.Supplier;

public interface CircleFactory extends Supplier<Circle> {

    static CircleFactory createFactory(Supplier<Circle> circleSupplier) {
        return circleSupplier::get;

    }

    static CircleFactory createFactory(Supplier<Circle> circleSupplier, String circleColor) {
        return () -> new Circle( circleColor ) ;
    }

    default List<Circle> getThree() {
        return List.of(new Circle(), new Circle(), new Circle());
    }

    default List<Circle> getThree(Supplier<Circle> circleSupplier) {
       return List.of( circleSupplier.get(), circleSupplier.get(), circleSupplier.get() );
    }


}
