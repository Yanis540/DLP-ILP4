#include "ilp.h"
#include "proprety.h"

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
        ILP_domain_error("Unexisting Field on Class",target);
   }
   ILP_Object oldValue = *(field);
   *(field) = value; 
   return oldValue; 
}