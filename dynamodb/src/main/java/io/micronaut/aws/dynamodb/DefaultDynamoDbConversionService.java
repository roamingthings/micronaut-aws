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

import io.micronaut.core.beans.BeanIntrospection;
import io.micronaut.core.beans.BeanProperty;
import io.micronaut.core.beans.BeanWrapper;
import io.micronaut.core.convert.ConversionService;
import jakarta.inject.Singleton;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.HashMap;
import java.util.Map;

/**
 * {@link io.micronaut.context.annotation.DefaultImplementation} of {@link DynamoDbConversionService} which uses {@link ConversionService} to convert from and to {@link AttributeValue} map.
 * @param <S> Type to converter
 */
@Singleton
public class DefaultDynamoDbConversionService<S> implements DynamoDbConversionService<S> {

    private final ConversionService conversionService;

    public DefaultDynamoDbConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }

    @Override
    public Map<String, AttributeValue> convert(BeanWrapper<S> wrapper) {
        Map<String, AttributeValue> result = new HashMap<>();
        S bean = wrapper.getBean();
        for (BeanProperty<S, ?> beanProperty : wrapper.getBeanProperties()) {
            conversionService.convert(beanProperty.get(bean), AttributeValue.class)
                .ifPresent(attributeValue -> result.put(beanProperty.getName(), attributeValue));
        }
        return result;
    }

    @Override
    public <T> T convert(Map<String, AttributeValue> item, Class<T> targetType) {
        final BeanIntrospection<T> introspection = BeanIntrospection.getIntrospection(targetType);
        Object[] arguments = new Object[introspection.getConstructorArguments().length];
        int counter = 0;
        for (BeanProperty beanProperty : introspection.getBeanProperties()) {
            if (item.containsKey(beanProperty.getName())) {
                arguments[counter++] = conversionService.convert(item.get(beanProperty.getName()), beanProperty.getType())
                    .orElse(null);
            } else {
                arguments[counter++] = null;
            }
        }
        return introspection.instantiate(arguments);
    }
}
