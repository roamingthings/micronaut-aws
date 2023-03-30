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
package io.micronaut.aws.dynamodb.converters;

import io.micronaut.context.annotation.Prototype;
import io.micronaut.core.convert.ConversionContext;
import io.micronaut.core.convert.TypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Locale;
import java.util.Optional;

/**
 * {@link TypeConverter} from {@link AttributeValue} to {@link Locale} with {@link Locale#forLanguageTag(String)}.
 * @author Sergio del Amo
 * @since 4.0.0
 */
@Prototype
public class AttributeValueToLocaleTypeConverter implements TypeConverter<AttributeValue, Locale> {
    private static final Logger LOG = LoggerFactory.getLogger(AttributeValueToLocaleTypeConverter.class);

    @Override
    public Optional<Locale> convert(AttributeValue object, Class<Locale> targetType, ConversionContext context) {
        if (object == null) {
            return Optional.empty();
        }
        String value = object.s();
        if (value == null) {
            return Optional.empty();
        }
        return Optional.of(Locale.forLanguageTag(value));
    }
}
