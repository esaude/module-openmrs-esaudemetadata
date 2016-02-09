package org.openmrs.module.eSaudeMetadata.metadata;

import org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle;
import org.springframework.stereotype.Component;

import static org.openmrs.module.metadatadeploy.bundle.CoreConstructors.packageFile;

/**
 * Created by codehub on 2/5/16.
 */
@Component
public class PackagesMetadata extends AbstractMetadataBundle {

    public static final class _Package {
        public static final String FORMS = "6e1056e0-cbe7-11e5-a327-10c37b209c79";
        public static final String REPORTS = "7dbdb83a-cbe7-11e5-934e-10c37b209c79";
    }

    /**
     * @see org.openmrs.module.metadatadeploy.bundle.AbstractMetadataBundle#install()
     */
    @Override
    public void install() {
        install(packageFile("metadata/eSaudeMetadata_Forms-1.zip", null, _Package.FORMS));
        install(packageFile("metadata/eSaudeMetadata_Reports-1.zip", null, _Package.REPORTS));
    }
}
