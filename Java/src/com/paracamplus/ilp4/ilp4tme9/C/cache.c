#include "ilp.h"

int ILP_cache_call = 0;
int ILP_cache_miss = 0;

void ILP_print_cache_statistics()
{
    fprintf(stderr, "Cache Calls of method: %d \n", ILP_cache_call);
    fprintf(stderr, "Cache Miss of method: %d \n", ILP_cache_miss);
    if(ILP_cache_call != 0){
        fprintf(stderr, "Cache Hit ratio: %f \n", ((ILP_cache_call - ILP_cache_miss) * 100.) /ILP_cache_call );
    }else{
        fprintf(stderr, "Cache Hit ratio: 0 \n");
    }

}

ILP_general_function
ILP_find_method_global_cache(ILP_Object receiver, ILP_Method method, int argc)
{
    ILP_cache_call++;

    static ILP_Class cache_class;
    static ILP_Method cache_method;
    static int cache_argc;
    static ILP_general_function cache_result;

     //miss
    if( cache_class != receiver->_class || cache_method != method || cache_argc != argc){
         //mis à jour
         cache_class = receiver->_class;
         cache_method = method;
         cache_argc = argc;
         cache_result = ILP_find_method(receiver, method, argc);
         ILP_cache_miss++;
    }

    return cache_result;
}