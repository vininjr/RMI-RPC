/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#include "chat.h"

bool_t
xdr_mensagem (XDR *xdrs, mensagem *objp)
{
	register int32_t *buf;

	int i;
	 if (!xdr_vector (xdrs, (char *)objp->m, 255,
		sizeof (char), (xdrproc_t) xdr_char))
		 return FALSE;
	 if (!xdr_int (xdrs, &objp->id_mensagem))
		 return FALSE;
	 if (!xdr_int (xdrs, &objp->id_cliente))
		 return FALSE;
	return TRUE;
}
