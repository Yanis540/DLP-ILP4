

#ifndef ILP_PROPRETY
#define ILP_PROPRETY
#include "ilp.h"


extern ILP_Object ILP_has_property(ILP_Object target, ILP_Object property);
extern ILP_Object ILP_read_property(ILP_Object target, ILP_Object property);
extern ILP_Object ILP_write_property(ILP_Object target, ILP_Object property,ILP_Object value );



#endif