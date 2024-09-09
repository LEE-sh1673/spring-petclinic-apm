package org.springframework.samples.petclinic;

import ch.qos.logback.classic.spi.ILoggingEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Setter
@Getter
public class LogCrashRequest {

	String projectName;
	String projectVersion;
	String logVersion;
	String body;
	String sendTime;
	String logSource;
	String logType;
	String host;
	String logLevel;
	String txId;
	String spanId;

	private static final String DEFAULT_PROJECT_NAME = "cU5MpAbTEmgLuXYL";
	private static final String DEFAULT_PROJECT_VERSION = "1.0.0";
	private static final String DEFAULT_LOG_VERSION = "v2";
	private static final String DEFAULT_LOG_SOURCE = "http";
	private static final String DEFAULT_LOG_TYPE = "nelo2-http";
	private static final String DEFAULT_HOST = "localhost";

	public LogCrashRequest(final String body) {
		this.projectName = DEFAULT_PROJECT_NAME;
		this.projectVersion = DEFAULT_PROJECT_VERSION;
		this.logVersion = DEFAULT_LOG_VERSION;
		this.body = body;
		this.logSource = DEFAULT_LOG_SOURCE;
		this.logType = DEFAULT_LOG_TYPE;
		this.host = DEFAULT_HOST;
	}

	@Builder
	public LogCrashRequest(final ILoggingEvent iLoggingEvent) {
		this(iLoggingEvent.getFormattedMessage());
		this.logLevel = iLoggingEvent.getLevel().toString();

		Map<String, String> mdcProperties = iLoggingEvent.getMDCPropertyMap();
		String txId = mdcProperties.getOrDefault("TxId", "");
		String spanId = mdcProperties.getOrDefault("SpanId", "");

		this.txId = txId;
		this.spanId = spanId;
	}
}
