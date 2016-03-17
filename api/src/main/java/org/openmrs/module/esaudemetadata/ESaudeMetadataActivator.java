/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.esaudemetadata;


import org.apache.commons.lang.SystemUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.ModuleActivator;
import org.openmrs.module.metadatasharing.ImportConfig;
import org.openmrs.module.metadatasharing.ImportMode;
import org.openmrs.module.metadatasharing.ImportedPackage;
import org.openmrs.module.metadatasharing.MetadataSharing;
import org.openmrs.module.metadatasharing.api.MetadataSharingService;
import org.openmrs.module.metadatasharing.wrapper.PackageImporter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class contains the logic that is run every time this module is either started or stopped.
 */
public class ESaudeMetadataActivator implements ModuleActivator {
	
	protected Log log = LogFactory.getLog(getClass());

	private static final String METADATA_FILENAME_FORMS = "MCH_Forms-1.zip";
	private static final String METADATA_FILENAME_REPORTS = "MCH_Reports-1.zip";
		
	/**
	 * @see ModuleActivator#willRefreshContext()
	 */
	public void willRefreshContext() {
		log.info("Refreshing eSaude Metadata Module");
	}
	
	/**
	 * @see ModuleActivator#contextRefreshed()
	 */
	public void contextRefreshed() {
		log.info("eSaude Metadata Module refreshed");
	}
	
	/**
	 * @see ModuleActivator#willStart()
	 */
	public void willStart() {
		log.info("Starting eSaude Metadata Module");
	}
	
	/**
	 * @see ModuleActivator#started()
	 */
	public void started() {
		try {

			setupInitialData();

		} catch (Exception ex) {
			throw new RuntimeException("Failed to setup initial data", ex);
		}
		log.info("eSaude Metadata Module started");
	}
	
	/**
	 * @see ModuleActivator#willStop()
	 */
	public void willStop() {
		log.info("Stopping eSaude Metadata Module");
	}
	
	/**
	 * @see ModuleActivator#stopped()
	 */
	public void stopped() {
		log.info("eSaude Metadata Module stopped");
	}

	/**
	 * Checks whether the given version of the MDS package has been installed yet, and if not, install it
	 *
	 * @param groupUuid
	 * @param filename
	 * @return whether any changes were made to the db
	 * @throws IOException
	 */
	private boolean installMetadataPackageIfNecessary(String groupUuid, String filename) throws IOException {
		//NameWithNoSpaces-v1.zip
		try {
			Matcher matcher = Pattern.compile("\\w+-(\\d+).zip").matcher(filename);

			if (!matcher.matches())
				throw new RuntimeException("Filename must match PackageNameWithNoSpaces-1.zip");
			Integer version = Integer.valueOf(matcher.group(1));

			ImportedPackage installed = Context.getService(MetadataSharingService.class).getImportedPackageByGroup(groupUuid);
			if (installed != null && installed.getVersion() >= version) {
				log.info("Metadata package " + filename + " is already installed with version " + installed.getVersion());
				return false;
			}

			if (getClass().getClassLoader().getResource(filename) == null) {
				throw new RuntimeException("Cannot find " + filename + " for group " + groupUuid + ". Make sure it's in api/src/main/resources");
			}

			PackageImporter metadataImporter = MetadataSharing.getInstance().newPackageImporter();
			metadataImporter.setImportConfig(ImportConfig.valueOf(ImportMode.PARENT_AND_CHILD));
			metadataImporter.loadSerializedPackageStream(getClass().getClassLoader().getResourceAsStream(filename));
			metadataImporter.importPackage();
			return true;
		} catch (Exception ex) {
			log.error("Failed to install metadata package " + filename, ex);
			return false;
		}
	}

	/**
	 * Public for testing
	 *
	 * @return whether any changes were made to the db
	 * @throws Exception
	 */
	public boolean setupInitialData() throws Exception {
		boolean anyChanges = false;
		anyChanges |= installMetadataPackageIfNecessary("d686696a-42bb-4724-b924-8d39ffa67622", METADATA_FILENAME_FORMS);
		anyChanges |= installMetadataPackageIfNecessary("9a6ce9a3-1758-474d-9b01-3f2023bfc795", METADATA_FILENAME_REPORTS);
		return anyChanges;
	}
		
}
