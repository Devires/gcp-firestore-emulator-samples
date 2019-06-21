package br.com.devires;

import java.io.IOException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gcp.core.UserAgentHeaderProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.gax.core.CredentialsProvider;
import com.google.cloud.NoCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;

@Configuration
@ConditionalOnProperty(name = "spring.cloud.gcp.emulator-enabled", havingValue = "true")
@EnableConfigurationProperties(GcpFirestoreProperties.class)
public class GcpFirestoreEmulatorConfig {

	@Bean
	public CredentialsProvider credentialsProvider() {
		return NoCredentials::getInstance;
	}

	@Bean
	public Firestore firestore(GcpFirestoreProperties gcpFirestoreProperties) throws IOException {
		FirestoreOptions.Builder builder = FirestoreOptions.newBuilder()
				.setProjectId(gcpFirestoreProperties.getProjectId()).setHost(gcpFirestoreProperties.getEmulatorHost())
				.setHeaderProvider(new UserAgentHeaderProvider(this.getClass()))
				.setCredentials(credentialsProvider().getCredentials());
		return builder.build().getService();
	}

}
