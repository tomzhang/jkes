package com.timeyang.jkes.core.kafka.util;

import com.timeyang.jkes.core.elasticsearch.annotation.Document;
import com.timeyang.jkes.core.support.Config;
import com.timeyang.jkes.core.util.Asserts;
import com.timeyang.jkes.core.util.StringUtils;

/**
 * Kafka Connector Utils
 *
 * @author chaokunyang
 */
public class EsKafkaConnectUtils {

    public static String getConnectorName(Class<?> document) {
        Asserts.check(document.isAnnotationPresent(Document.class), "The class " + document.getCanonicalName() + " must be annotated with " + Document.class.getCanonicalName());

        String prefix = Config.getJkesProperties().getKafkaConnectorPrefix();
        prefix = StringUtils.hasText(prefix) ? StringUtils.trimWhitespace(prefix) : "";
        String connectorName = prefix + "_" + EsKafkaUtils.getTopicWithoutPrefix(document) + "_es_sink";
        return connectorName;
    }

}
