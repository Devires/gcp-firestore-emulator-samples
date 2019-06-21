package br.com.devires;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.cloud.gcp.core.Credentials;
import org.springframework.cloud.gcp.core.CredentialsSupplier;
import org.springframework.cloud.gcp.core.GcpScope;

@ConfigurationProperties("spring.cloud.gcp.firestore")
public class GcpFirestoreProperties implements CredentialsSupplier {

	/** Overrides the GCP OAuth2 credentials specified in the Core module. */
	@NestedConfigurationProperty
	private final Credentials credentials = new Credentials(GcpScope.DATASTORE.getUrl());

	private String projectId;

	private String emulatorHost;

	@Override
	public Credentials getCredentials() {
		return this.credentials;
	}

	public String getProjectId() {
		return this.projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getEmulatorHost() {
		return emulatorHost;
	}

	public void setEmulatorHost(String emulatorHost) {
		this.emulatorHost = emulatorHost;
	}

}
