/*
 * Copyright 2022 original authors
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

package io.micronaut.aws.lambda.events.serde

import com.amazonaws.services.lambda.runtime.events.DynamodbEvent
import io.micronaut.context.BeanContext
import io.micronaut.serde.ObjectMapper
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest(startApplication = false)
class DynamodbEventSpec extends Specification {

    @Inject
    ObjectMapper objectMapper

    @Inject
    BeanContext beanContext

    void "test deserialization of sqs recieve message event"() {
        given:
        File f = new File('src/test/resources/dynamodb-update.json')

        expect:
        f.exists()

        when:
        String json = f.text

        then:
        json

        when:
        DynamodbEvent sqsEvent = objectMapper.readValue(json, DynamodbEvent)

        then:
        sqsEvent.getRecords().size() == 3
    }
}
