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

import io.micronaut.core.annotation.Introspected;
import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.NotBlank;

/**
 * Base class to extend from in a DynamoDB Single table design.
 */
@Introspected
public class BaseItem implements CompositeKey {
    @NonNull
    @NotBlank
    private final String pk;

    @NonNull
    @NotBlank
    private final String sk;

    /**
     *
     * @param pk Primary Key
     * @param sk Sort Key
     */
    public BaseItem(String pk,
                    String sk) {
        this.pk = pk;
        this.sk = sk;
    }

    @Override
    @NonNull
    public String getPk() {
        return pk;
    }

    @Override
    @NonNull
    public String getSk() {
        return sk;
    }
}
