package data;

public class Evento implements IProbabilidad
{
	private boolean externo; //(true: externo; false: interno) identificador para diferenciar el tipo de evento
	private String nombre;
	private String descripcion;
	private boolean eventoActivo; //(true: activo; false: inactivo) al existir eventos que pueden mantenerse activos por cierto tiempo, no pueden aparecer nuevamente mientras estén activos.
}
