/*
 * Copyright 2021 original authors
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
package io.micronaut.tracing.aws.cloudwatch;

import com.amazonaws.xray.AWSXRayRecorder;
import com.amazonaws.xray.metrics.MetricsSegmentListener;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import io.micronaut.core.util.StringUtils;
import io.micronaut.tracing.aws.XRayConfiguration;

import javax.inject.Singleton;

/**
 * Factory that creates {@link MetricsSegmentListener} that is afterwards injected into {@link AWSXRayRecorder} and
 * sends metrics to cloudwatch.
 *
 * @author Pavol Gressa
 * @since 2.5
 */
@Requires(beans = AWSXRayRecorder.class)
@Requires(classes = MetricsSegmentListener.class)
@Requires(property = XRayConfiguration.XRayCloudWatchMetricsConfiguration.PREFIX + ".enabled", notEquals = StringUtils.FALSE)
@Factory
public class MetricsSegmentListenerFactory {

    /**
     * @return segment listener
     */
    @Singleton
    public MetricsSegmentListener build() {
        return new MetricsSegmentListener();
    }

}
