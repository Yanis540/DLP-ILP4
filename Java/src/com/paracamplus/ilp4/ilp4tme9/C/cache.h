#ifndef ILP_CACHE
#define ILP_CACHE
#include "ilp.h"
extern int ILP_cache_call;
extern int ILP_cache_miss;
extern void ILP_print_cache_statistics();

#define ILP_cache(target, receiver, method, arity)                      \
  do {                                                                  \
    ILP_cache_call++;                                                   \
    static ILP_Class            cache_class;                            \
    static ILP_Method           cache_method;                           \
    static int                  cache_arity;                            \
    static ILP_general_function cache_result;                           \
                                                                        \
    if (cache_class != (receiver)->_class ||                            \
        cache_method != (method) ||                                     \
        cache_arity != (arity)) {                                       \
      cache_result = ILP_find_method((receiver), (method), (arity));    \
      cache_class = (receiver)->_class;                                 \
      cache_method = (method);                                          \
      cache_arity = (arity);                                            \
      ILP_cache_miss++;                                                 \
    }                                                                   \
                                                                        \
    (target) = cache_result;                                            \
  } while (0)

#define ILP_find_method_site_cache(target, receiver, method, arity)     \
    ILP_cache(target,receiver,method,arity)                             \

extern ILP_general_function
ILP_find_method_global_cache(ILP_Object receiver, ILP_Method method, int argc);

#endif
