package org.openmrs.module.esaudemetadata.chore;

import org.openmrs.LocationAttributeType;
import org.openmrs.api.AdministrationService;
import org.openmrs.api.LocationService;
import org.openmrs.api.context.Context;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;

@Component("esaudemetadata.chore.retireLocations")
@Requires({UpdateLocations.class})
public class RetireUnUsedLocations extends AbstractChore {

    /**
     * @see org.openmrs.module.esaudemetadata.chore.AbstractChore#perform(java.io.PrintWriter)
     */
    @Override
    public void perform(PrintWriter output) {
        removeNonMatchingLocations();
    }

    private void removeNonMatchingLocations(){
        AdministrationService as = Context.getAdministrationService();
        LocationService locationService = Context.getLocationService();
        LocationAttributeType locationAttributeType = locationService.getLocationAttributeTypeByUuid("132895aa-1c88-11e8-b6fd-7395830b63f3");

        String locations_with_code_set = "SELECT locia.location_id FROM location_attribute locia where locia.attribute_type_id=1 AND locia.value_reference is not null";
        String locations_to_remove = "UPDATE location SET retired=1, retire_reason='Not used' WHERE location_id NOT IN("+locations_with_code_set+")";
        as.executeSQL(locations_to_remove, false);
    }
}
