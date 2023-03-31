/*
 * Copyright 2017-2023 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.aws.dynamodb;

import io.micronaut.aws.dynamodb.utils.AttributeValueUtils;
import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import io.micronaut.core.annotation.Nullable;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Global Secondary Index 2 Utility class.
 * @author Sergio del Amo
 * @since 4.0.0
 */
@Introspected
public interface GlobalSecondaryIndex2 {

    String KEY_GSI2_PK = "gsi2pk";
    String KEY_GSI2_SK = "gsi2sk";

    @NonNull
    default Optional<Map<String, AttributeValue>> getGsi2() {
        if (getGsi2Pk() == null && getGsi2Sk() == null) {
            return Optional.empty();
        }
        Map<String, AttributeValue> result = new HashMap<>();
        if (getGsi2Pk() != null) {
            result.put(KEY_GSI2_PK, AttributeValueUtils.s(getGsi2Pk()));
        }
        if (getGsi2Sk() != null) {
            result.put(KEY_GSI2_SK, AttributeValueUtils.s(getGsi2Sk()));
        }
        return Optional.of(result);
    }

    /**
     *
     * @return Global Secondary Index 2 Primary Key
     */
    @Nullable
    String getGsi2Pk();

    /**
     *
     * @return Global Secondary Index 2 Sort Key
     */
    @Nullable
    String getGsi2Sk();

}
