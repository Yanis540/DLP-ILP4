#include "ilp.h"
#include "proprety.h"

ILP_Object*
ILP_create_dyanmic_field(ILP_Object target, char* property)
{
    ILP_DynamicField newDynamicField = ILP_MALLOC(sizeof(struct ILP_DynamicField));
    newDynamicField->name = property;
    newDynamicField->next = target->_content.asInstance.dynamicField;
    newDynamicField->value = ILP_FALSE;

    target->_content.asInstance.dynamicField = newDynamicField;

    return &(newDynamicField->value);
}

ILP_Object* ILP_lookup_property(ILP_Object target, char* property){
    ILP_Class class = target->_class;
    ILP_Field field = class->_content.asClass.last_field; 

    while(field){
        if(!(strcmp(field->_content.asField.name,property))){
            short i = field->_content.asField.offset;
            return &(target->_content.asInstance.field[i]);
        }
        field = field->_content.asField.previous_field;
    }

    // si il n'est pas trouvé dans les champs stattique, alors on cherche dans les champs dyanmique 
    ILP_DynamicField dfield = target->_content.asInstance.dynamicField; 
    while(dfield){
        if(!(strcmp(dfield->name,property)))
            return &(dfield->value);
        dfield = dfield->next; 
    }
    return NULL; 

}



ILP_Object ILP_has_property(ILP_Object target, ILP_Object property){
    ILP_CheckIfString(property);
    if(ILP_lookup_property(target, property->_content.asString.asCharacter))
        return ILP_TRUE; 
    return ILP_FALSE; 
}
ILP_Object ILP_read_property(ILP_Object target, ILP_Object property){
    ILP_CheckIfString(property);
    ILP_Object * field = ILP_lookup_property(target,property->_content.asString.asCharacter);
    if(!field)
        return ILP_domain_error("Unexisting field on Class",property);
    return *(field);

}
ILP_Object ILP_write_property(ILP_Object target, ILP_Object property,ILP_Object value ){
   ILP_CheckIfString(property);
   ILP_Object * field = ILP_lookup_property(target,property->_content.asString.asCharacter);
   if(!field){
        // create a new field here 
        // ILP_domain_error("Unexisting Field on Class",target); // <== remet le champs statique 
        field = ILP_create_dyanmic_field(target,property->_content.asString.asCharacter);
   }
   ILP_Object oldValue = *(field);
   *(field) = value; 
   return oldValue; 
}