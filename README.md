# Seam Framework


Este proyecto comtiene ejemplos de los tipos de contextos en Seam:

StatelessContext: variables sin estado (como ejbs sin estado); en realidad no es contexto
EventContext: equivale al request context, asociado a pedido JS
PageContext: estado asociado a una pantalla mostrada
ConversationContext: unidad de trabajo para el usuario, puede  durar varios pedidos
SessionContext: asociada a toda la sesión de un usuario
BusinessContext: asociado a un proceso de negocio que puede 
involucrar múltiples interaccionees con múltiples usuarios
ApplicationContext: asociado al estado global del servidor
 