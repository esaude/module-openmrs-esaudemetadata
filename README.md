<br/><br/><br/>
<img src="https://s3-eu-west-1.amazonaws.com/esaude/images/esaude-site-header.png" alt="OpenMRS"/>
<br/><br/><br/>

# eSaude Metadata Module

This project to help the eSaude EMR Platform load its metadata like forms and
reports which are accompanied with the distributions. One needs to include it
among the distributions. It uses the following modules which should have been
installed and started.

### Metadata sharing and metadata mapping

To update the metadata one needs to do the following:

1. Copy the new resource in the api/resource folder with a number in the file
name signifying the version changed/upgraded to.
2. Update EsaudeMetadataUtils but deleting the already exsting name with the new
one. If new resources is added, please provide a new line for each resource.
3. If updating old resource, that is it, but if new resource is being added, you
will need to update the activator file with an additional line in method `setupInitialData()` by providing correct uuid and name as mapped in EsaudeMetadataUtils.

Package the module from it root (`mvn clean package`) and then include it in
your module folder and restart your server.

## License

[MPL 2.0 w/ HD](http://openmrs.org/license/)
