# DISEÑO DE APLICACIÓN NATIVA DE FINANZAS PERSONALES

Diseñar una aplicación móvil nativa de finanzas personales para iOS y Android.

La aplicación debe permitir a los usuarios registrar, administrar y analizar sus ingresos, gastos, cuentas, presupuestos y metas de ahorro.

El diseño debe ser moderno, minimalista, profesional, intuitivo y centrado en la facilidad de uso. La aplicación debe sentirse como un producto financiero real y confiable.

---

# 1. OBJETIVO DE LA APLICACIÓN

La aplicación debe permitir al usuario:

- Consultar su balance financiero general.
- Registrar ingresos.
- Registrar gastos.
- Consultar todos sus movimientos.
- Administrar diferentes cuentas y tarjetas.
- Crear categorías personalizadas.
- Crear presupuestos mensuales.
- Controlar cuánto dinero ha gastado.
- Crear metas de ahorro.
- Consultar estadísticas financieras.
- Visualizar su actividad financiera por fechas.
- Recibir alertas y recordatorios.
- Administrar su perfil y configuración.
- Proteger la información mediante opciones de seguridad.

La experiencia principal debe ser simple y rápida:

INICIAR SESIÓN → VER BALANCE → REGISTRAR INGRESO O GASTO → CONSULTAR MOVIMIENTOS → ANALIZAR FINANZAS.

---

# 2. PLATAFORMA Y TIPO DE DISEÑO

Diseñar una aplicación móvil nativa.

El diseño debe funcionar correctamente en:

- iOS.
- Android.

Utilizar una estructura adaptable a diferentes tamaños de pantalla.

Diseñar inicialmente sobre un frame móvil aproximado de 390 x 844 px.

Mantener márgenes, espaciados y tamaños consistentes.

Utilizar una cuadrícula de espaciado basada preferiblemente en múltiplos de 4 u 8.

Ejemplos:

- 4 px
- 8 px
- 12 px
- 16 px
- 24 px
- 32 px

---

# 3. ESTILO VISUAL GENERAL

La aplicación debe transmitir:

- Confianza.
- Seguridad.
- Claridad.
- Organización.
- Control financiero.
- Simplicidad.
- Modernidad.

El diseño no debe sentirse sobrecargado.

Evitar:

- Demasiados colores.
- Exceso de tarjetas.
- Exceso de sombras.
- Gráficos confusos.
- Pantallas saturadas de información.
- Textos demasiado pequeños.

Utilizar:

- Espacios en blanco.
- Jerarquía visual clara.
- Tarjetas con bordes suaves.
- Iconografía consistente.
- Tipografía moderna y legible.
- Componentes reutilizables.
- Animaciones sutiles cuando sea apropiado.

---

# 4. DESIGN SYSTEM

Crear un sistema de diseño reutilizable antes de diseñar todas las pantallas.

## 4.1 Colores

Definir los siguientes tokens de color:

- Primary
- Primary Light
- Primary Dark
- Secondary
- Background
- Surface
- Surface Secondary
- Text Primary
- Text Secondary
- Border
- Success
- Warning
- Error
- Disabled

El color principal debe transmitir confianza y estabilidad financiera.

Los colores de éxito, advertencia y error deben utilizarse principalmente para comunicar estados.

No depender únicamente del color para transmitir información.

---

## 4.2 Tipografía

Crear una jerarquía tipográfica consistente.

Definir:

- Display
- Heading 1
- Heading 2
- Heading 3
- Title
- Body Large
- Body
- Body Small
- Caption
- Label

Los números relacionados con dinero deben ser fáciles de leer y tener buena jerarquía visual.

---

## 4.3 Componentes

Crear componentes reutilizables para:

### Botones

- Primary button.
- Secondary button.
- Outline button.
- Text button.
- Destructive button.
- Icon button.
- Floating action button.

Estados:

- Default.
- Pressed.
- Disabled.
- Loading.

### Inputs

- Default.
- Focus.
- Filled.
- Error.
- Disabled.

Tipos:

- Texto.
- Correo.
- Contraseña.
- Monto de dinero.
- Búsqueda.

### Otros componentes

- Bottom navigation.
- Top app bar.
- Cards.
- Transaction item.
- Account card.
- Budget card.
- Goal card.
- Category selector.
- Tabs.
- Chips.
- Filter chips.
- Dropdown.
- Bottom sheet.
- Modal.
- Dialog.
- Toast.
- Snackbar.
- Progress bar.
- Circular progress.
- Toggle.
- Switch.
- Checkbox.
- Radio button.
- Date picker.
- Empty state.
- Loading state.
- Error state.

---

# 5. ESTRUCTURA DE NAVEGACIÓN

La navegación principal debe utilizar una barra inferior.

Se recomienda la siguiente estructura:

1. Inicio.
2. Movimientos.
3. Botón central para agregar.
4. Metas.
5. Perfil.

El botón central de agregar debe tener mayor relevancia visual.

Al tocarlo debe abrir un menú o bottom sheet con:

- Registrar ingreso.
- Registrar gasto.
- Registrar transferencia.

La navegación debe permitir regresar fácilmente a la pantalla anterior.

---

# 6. MAPA GENERAL DE LA APLICACIÓN

SPLASH

↓

ONBOARDING

↓

AUTENTICACIÓN

- Iniciar sesión.
- Crear cuenta.
- Recuperar contraseña.
- Crear nueva contraseña.

↓

APLICACIÓN PRINCIPAL

├── INICIO
│   ├── Balance general.
│   ├── Resumen mensual.
│   ├── Últimos movimientos.
│   ├── Presupuestos.
│   └── Acciones rápidas.
│
├── MOVIMIENTOS
│   ├── Todos.
│   ├── Ingresos.
│   ├── Gastos.
│   ├── Transferencias.
│   ├── Filtros.
│   └── Detalle del movimiento.
│
├── AGREGAR
│   ├── Nuevo ingreso.
│   ├── Nuevo gasto.
│   └── Nueva transferencia.
│
├── METAS
│   ├── Lista de metas.
│   ├── Crear meta.
│   ├── Detalle de meta.
│   └── Agregar ahorro.
│
└── PERFIL
    ├── Cuentas.
    ├── Presupuestos.
    ├── Categorías.
    ├── Estadísticas.
    ├── Calendario.
    ├── Notificaciones.
    ├── Seguridad.
    ├── Configuración.
    └── Ayuda.

---

# 7. MÓDULO SPLASH

## Pantalla: Splash Screen

La pantalla debe mostrar:

- Logo de la aplicación.
- Nombre de la aplicación.
- Fondo limpio.
- Indicador de carga opcional.

Objetivo:

Crear una primera impresión profesional.

Estados:

- Cargando aplicación.
- Error de conexión opcional.

---

# 8. MÓDULO ONBOARDING

Crear tres pantallas de onboarding.

## Vista 1: Control financiero

Contenido:

Título:

“Tus finanzas, bajo control.”

Descripción:

Administra tus ingresos, gastos y cuentas desde un solo lugar.

Elementos:

- Ilustración financiera.
- Indicador de progreso.
- Botón siguiente.
- Opción para omitir.

---

## Vista 2: Presupuestos

Título:

“Controla cuánto gastas.”

Descripción:

Crea presupuestos y recibe alertas cuando estés cerca de tus límites.

Elementos:

- Ilustración relacionada con presupuestos.
- Indicador de progreso.
- Botón siguiente.
- Opción anterior.

---

## Vista 3: Metas

Título:

“Alcanza tus metas.”

Descripción:

Define objetivos de ahorro y sigue tu progreso.

Elementos:

- Ilustración relacionada con metas.
- Botón comenzar.
- Indicador de progreso.

---

# 9. MÓDULO DE AUTENTICACIÓN

## Vista: Iniciar sesión

Elementos:

- Logo.
- Título de bienvenida.
- Campo correo electrónico.
- Campo contraseña.
- Botón mostrar u ocultar contraseña.
- Enlace “¿Olvidaste tu contraseña?”
- Botón principal “Iniciar sesión”.
- Enlace “Crear cuenta”.

Estados:

- Vacío.
- Campo activo.
- Error.
- Cargando.
- Credenciales incorrectas.

---

## Vista: Crear cuenta

Campos:

- Nombre completo.
- Correo electrónico.
- Contraseña.
- Confirmar contraseña.

Elementos:

- Indicador de requisitos de contraseña.
- Checkbox de términos opcional.
- Botón crear cuenta.
- Enlace para iniciar sesión.

---

## Vista: Recuperar contraseña

Elementos:

- Explicación.
- Campo correo.
- Botón enviar enlace.

Estados:

- Normal.
- Error.
- Enviando.
- Enlace enviado correctamente.

---

## Vista: Nueva contraseña

Campos:

- Nueva contraseña.
- Confirmar contraseña.

Elementos:

- Requisitos.
- Indicador de fortaleza.
- Botón guardar contraseña.

---

# 10. MÓDULO DE INICIO

Esta debe ser una de las pantallas principales.

## Vista: Dashboard

Estructura:

### Header

- Saludo personalizado.
- Nombre del usuario.
- Botón de notificaciones.

Ejemplo:

“Buenos días, Juan”

### Balance principal

Mostrar:

BALANCE TOTAL

$2.450.000

Debe ser el elemento de mayor jerarquía visual.

Debe incluir una opción para ocultar o mostrar el balance.

---

### Resumen financiero

Mostrar dos tarjetas:

INGRESOS

$3.500.000

Comparación con el período anterior opcional.

GASTOS

$850.000

Comparación con el período anterior opcional.

---

### Acciones rápidas

Botones:

- Agregar gasto.
- Agregar ingreso.
- Transferir.
- Ver estadísticas.

---

### Resumen de presupuesto

Mostrar:

- Presupuesto utilizado.
- Presupuesto restante.
- Categoría más cercana al límite.

Ejemplo:

Alimentación

$420.000 de $500.000

Barra de progreso.

---

### Últimos movimientos

Mostrar aproximadamente entre 3 y 5 movimientos.

Cada movimiento debe incluir:

- Icono.
- Categoría.
- Descripción.
- Fecha.
- Monto.
- Indicador visual de ingreso o gasto.

Incluir botón:

“Ver todos”.

---

# 11. MÓDULO DE MOVIMIENTOS

## Vista: Lista de movimientos

Header:

- Título “Movimientos”.
- Botón de búsqueda.
- Botón de filtros.

Debajo:

Tabs:

- Todos.
- Ingresos.
- Gastos.
- Transferencias.

Los movimientos deben agruparse por fecha.

Ejemplo:

HOY

Alimentación
Restaurante
-$35.000

Transporte
Uber
-$18.000

AYER

Ingreso
Salario
+$3.000.000

---

## Vista: Búsqueda

Mostrar:

- Input de búsqueda.
- Resultados recientes opcionales.
- Historial de búsqueda opcional.

Permitir buscar por:

- Nombre.
- Categoría.
- Descripción.

---

## Vista: Filtros

Abrir mediante bottom sheet o pantalla.

Filtros:

- Tipo.
- Categoría.
- Cuenta.
- Fecha.
- Rango de fechas.
- Rango de monto.

Botones:

- Limpiar filtros.
- Aplicar filtros.

---

## Vista: Detalle de movimiento

Mostrar:

- Icono de categoría.
- Tipo de movimiento.
- Monto.
- Categoría.
- Cuenta.
- Fecha.
- Hora opcional.
- Descripción.
- Identificador opcional.

Acciones:

- Editar.
- Eliminar.

La eliminación debe solicitar confirmación.

---

# 12. MÓDULO DE INGRESOS

## Vista: Nuevo ingreso

Campos:

### Monto

Input numérico grande.

Ejemplo:

$ 0

### Categoría

Ejemplos:

- Salario.
- Freelance.
- Negocio.
- Inversiones.
- Regalo.
- Otros.

### Cuenta

Seleccionar cuenta donde se recibirá el dinero.

### Fecha

Selector de fecha.

### Descripción

Campo opcional.

### Recurrente

Switch.

Si se activa:

- Frecuencia.
- Fecha de inicio.
- Fecha de finalización opcional.

Botón:

“Guardar ingreso”.

---

# 13. MÓDULO DE GASTOS

## Vista: Nuevo gasto

Esta pantalla debe ser muy rápida de utilizar.

Campos:

### Monto

Input principal.

### Categoría

Mostrar categorías mediante grid o lista visual con iconos.

Ejemplos:

- Alimentación.
- Transporte.
- Vivienda.
- Salud.
- Educación.
- Entretenimiento.
- Compras.
- Servicios.
- Suscripciones.
- Otros.

### Cuenta

Seleccionar desde dónde salió el dinero.

### Fecha

Selector de fecha.

### Descripción

Opcional.

### Recurrente

Switch.

Botón:

“Guardar gasto”.

---

# 14. MÓDULO DE TRANSFERENCIAS

## Vista: Nueva transferencia

Campos:

- Cuenta de origen.
- Cuenta de destino.
- Monto.
- Fecha.
- Descripción.

El usuario no debe poder seleccionar la misma cuenta como origen y destino.

Mostrar claramente:

DE

→

PARA

Botón:

“Realizar transferencia”.

---

# 15. MÓDULO DE CUENTAS

## Vista: Lista de cuentas

Mostrar tarjetas para:

- Cuenta bancaria.
- Cuenta de ahorros.
- Efectivo.
- Billetera digital.
- Tarjeta débito.
- Tarjeta crédito.

Cada tarjeta debe mostrar:

- Icono.
- Nombre.
- Tipo.
- Saldo.

Mostrar también:

BALANCE TOTAL

Suma de todas las cuentas disponibles según las reglas financieras definidas.

Botón:

“Agregar cuenta”.

---

## Vista: Detalle de cuenta

Mostrar:

- Nombre.
- Tipo.
- Saldo.
- Gráfico opcional.
- Movimientos relacionados.

Acciones:

- Editar.
- Desactivar.
- Eliminar.

---

## Vista: Agregar cuenta

Campos:

- Nombre.
- Tipo de cuenta.
- Saldo inicial.
- Institución opcional.
- Icono.

Para tarjeta de crédito mostrar campos adicionales:

- Límite de crédito.
- Saldo utilizado.
- Fecha de corte.
- Fecha límite de pago.

---

# 16. MÓDULO DE CATEGORÍAS

## Vista: Lista de categorías

Separar mediante tabs:

- Gastos.
- Ingresos.

Cada categoría debe mostrar:

- Icono.
- Nombre.
- Número de movimientos opcional.

Ejemplo:

GASTOS

🍔 Alimentación

🚗 Transporte

🏠 Vivienda

🎮 Entretenimiento

❤️ Salud

Botón:

“Agregar categoría”.

---

## Vista: Crear categoría

Campos:

- Nombre.
- Tipo: ingreso o gasto.
- Selector de icono.

Opcionalmente:

- Color identificador.

El color no debe ser el único elemento para identificar la categoría.

---

# 17. MÓDULO DE PRESUPUESTOS

## Vista: Lista de presupuestos

Header:

“Presupuestos”

Selector de período:

Septiembre 2026.

Cada tarjeta debe mostrar:

- Categoría.
- Monto gastado.
- Límite.
- Porcentaje utilizado.
- Barra de progreso.

Ejemplo:

ALIMENTACIÓN

$420.000 de $500.000

84% utilizado.

Estados visuales:

- Normal.
- Advertencia.
- Cerca del límite.
- Excedido.

Botón:

“Crear presupuesto”.

---

## Vista: Crear presupuesto

Campos:

- Categoría.
- Límite de gasto.
- Período.
- Fecha de inicio.
- Fecha final opcional.

Opciones:

- Mensual.
- Semanal.
- Personalizado.

---

## Vista: Detalle de presupuesto

Mostrar:

- Monto total.
- Monto gastado.
- Monto restante.
- Porcentaje utilizado.
- Gráfico de progreso.
- Historial de gastos asociados.

Acciones:

- Editar presupuesto.
- Eliminar presupuesto.

---

# 18. MÓDULO DE METAS DE AHORRO

## Vista: Lista de metas

Cada tarjeta debe mostrar:

- Icono o imagen.
- Nombre.
- Monto actual.
- Monto objetivo.
- Porcentaje.
- Fecha objetivo opcional.

Ejemplo:

COMPRAR COMPUTADOR

$2.500.000 de $5.000.000

50% completado.

Mostrar:

- Activas.
- Completadas.

Botón:

“Nueva meta”.

---

## Vista: Crear meta

Campos:

- Nombre.
- Icono.
- Monto objetivo.
- Monto inicial.
- Fecha objetivo.
- Cuenta asociada opcional.

---

## Vista: Detalle de meta

Mostrar:

- Nombre.
- Progreso visual.
- Monto actual.
- Monto faltante.
- Porcentaje.
- Fecha objetivo.
- Estimación de progreso opcional.

Acciones:

- Agregar dinero.
- Retirar dinero.
- Editar.
- Marcar como completada.

---

## Vista: Agregar ahorro

Input:

Monto.

Opcional:

- Seleccionar cuenta de origen.
- Descripción.

Mostrar cuánto faltará después de realizar el aporte.

---

# 19. MÓDULO DE ESTADÍSTICAS

## Vista: Resumen financiero

Header:

“Estadísticas”

Selector:

- Semana.
- Mes.
- Año.
- Personalizado.

Mostrar:

### Resumen

- Ingresos totales.
- Gastos totales.
- Ahorro.
- Balance neto.

---

### Gastos por categoría

Utilizar gráfico circular o donut.

Mostrar leyenda clara.

Ejemplo:

Alimentación — 35%

Vivienda — 25%

Transporte — 20%

Entretenimiento — 10%

Otros — 10%

---

### Evolución

Gráfico de líneas o barras.

Comparar ingresos y gastos por período.

---

### Comparación

Mostrar:

“Gastaste un 12% más que el mes anterior.”

Este tipo de información debe expresarse claramente y no depender únicamente del gráfico.

---

# 20. MÓDULO DE CALENDARIO FINANCIERO

## Vista: Calendario

Mostrar calendario mensual.

Cada día puede contener indicadores de:

- Gastos.
- Ingresos.
- Pagos.
- Eventos financieros.

Al seleccionar un día mostrar:

- Movimientos.
- Total de ingresos.
- Total de gastos.

---

## Vista: Detalle del día

Ejemplo:

10 SEPTIEMBRE

INGRESOS

+$3.000.000

GASTOS

-$120.000

MOVIMIENTOS

Almuerzo.

Transporte.

Suscripción.

---

# 21. MÓDULO DE NOTIFICACIONES

## Vista: Centro de notificaciones

Tipos:

- Presupuesto próximo al límite.
- Presupuesto excedido.
- Pago próximo.
- Meta alcanzada.
- Recordatorio para registrar movimientos.
- Información general.

Cada notificación debe incluir:

- Icono.
- Título.
- Descripción.
- Fecha.
- Estado leído/no leído.

Acciones:

- Marcar como leída.
- Marcar todas como leídas.
- Eliminar.

---

# 22. MÓDULO DE PERFIL

## Vista: Perfil

Mostrar:

- Foto o avatar.
- Nombre.
- Correo.

Opciones:

- Mi perfil.
- Cuentas.
- Presupuestos.
- Categorías.
- Estadísticas.
- Notificaciones.
- Seguridad.
- Configuración.
- Ayuda.
- Cerrar sesión.

Organizar las opciones en grupos visuales.

---

## Vista: Editar perfil

Campos:

- Foto.
- Nombre.
- Correo.

Botón:

“Guardar cambios”.

---

# 23. MÓDULO DE CONFIGURACIÓN

## Vista: Configuración general

Secciones:

### Apariencia

- Tema claro.
- Tema oscuro.
- Usar configuración del sistema.

### Región

- Moneda.
- Idioma.
- Formato de fecha.

### Datos

- Exportar datos.
- Eliminar datos.
- Sincronización.

---

# 24. MÓDULO DE SEGURIDAD

## Vista: Seguridad

Opciones:

- Cambiar contraseña.
- Activar PIN.
- Activar biometría.
- Bloqueo automático.
- Administrar sesiones.

---

## Vista: Configurar PIN

Pantalla numérica para:

- Crear PIN.
- Confirmar PIN.

---

## Vista: Bloqueo de aplicación

Diseñar una pantalla de desbloqueo con:

- Logo.
- Mensaje.
- Input de PIN.
- Acceso mediante biometría cuando esté disponible.

---

# 25. MÓDULO DE AYUDA

## Vista: Centro de ayuda

Mostrar:

- Preguntas frecuentes.
- Cómo registrar un gasto.
- Cómo crear un presupuesto.
- Cómo crear una meta.
- Seguridad de la cuenta.
- Contactar soporte.

---

# 26. ESTADOS VACÍOS

Diseñar estados vacíos para los principales módulos.

## Sin movimientos

Mensaje:

“Aún no tienes movimientos.”

Botón:

“Registrar movimiento”.

---

## Sin presupuestos

Mensaje:

“Aún no has creado un presupuesto.”

Botón:

“Crear presupuesto”.

---

## Sin metas

Mensaje:

“Comienza a ahorrar para algo importante.”

Botón:

“Crear meta”.

---

## Sin cuentas

Mensaje:

“Agrega tu primera cuenta para comenzar.”

Botón:

“Agregar cuenta”.

Los estados vacíos deben incluir una ilustración simple.

---

# 27. ESTADOS DE CARGA

Diseñar estados de carga mediante:

- Skeleton loading.
- Indicador de progreso.
- Spinner cuando sea apropiado.

No dejar espacios vacíos mientras se cargan los datos.

---

# 28. ESTADOS DE ERROR

Diseñar pantallas o componentes para:

- Error de conexión.
- Error al cargar información.
- Error al guardar movimiento.
- Error de autenticación.
- Error inesperado.

Cada error debe incluir:

- Icono.
- Mensaje claro.
- Acción para reintentar cuando sea posible.

Ejemplo:

“No pudimos cargar tus movimientos.”

Botón:

“Intentar nuevamente”.

---

# 29. DIÁLOGOS DE CONFIRMACIÓN

Diseñar confirmaciones para acciones destructivas.

Ejemplo:

“¿Eliminar movimiento?”

“Esta acción no se puede deshacer.”

Botones:

- Cancelar.
- Eliminar.

El botón destructivo debe diferenciarse claramente.

---

# 30. BOTTOM SHEETS

Utilizar bottom sheets para acciones rápidas.

Ejemplo:

Al tocar el botón agregar:

¿QUÉ QUIERES REGISTRAR?

- Ingreso.
- Gasto.
- Transferencia.

Otro ejemplo:

Seleccionar categoría.

Mostrar categorías mediante una lista o grid con iconos.

---

# 31. MODO OSCURO

Diseñar versión para modo oscuro.

El modo oscuro debe incluir:

- Background oscuro.
- Superficies diferenciadas.
- Texto con contraste adecuado.
- Colores de estado accesibles.
- Gráficos legibles.

No simplemente invertir todos los colores.

---

# 32. CRITERIOS GENERALES DE UX

La aplicación debe cumplir las siguientes reglas:

- El usuario debe poder registrar un gasto en pocos pasos.
- Las acciones principales deben ser fáciles de encontrar.
- El balance debe tener una jerarquía visual alta.
- Los ingresos y gastos deben diferenciarse visualmente.
- La información financiera debe ser fácil de comprender.
- Los gráficos deben incluir etiquetas y valores.
- Los mensajes de error deben explicar claramente el problema.
- Las acciones importantes deben proporcionar retroalimentación.
- Las acciones destructivas deben solicitar confirmación.
- Los formularios deben validar la información.
- Los campos obligatorios deben identificarse claramente.
- Los botones deben tener estados normal, presionado, deshabilitado y cargando.
- Las listas deben tener estados con información, vacío, cargando y error.
- La aplicación debe ser consistente visualmente.
- Los componentes repetidos deben reutilizar el mismo diseño.
- Los elementos táctiles deben ser cómodos para dispositivos móviles.
- El texto debe mantener suficiente contraste con el fondo.
- La información no debe depender únicamente del color.
- La navegación debe permitir regresar fácilmente.
- El usuario debe recibir confirmación después de guardar información.

---

# 33. PROTOTIPO PRINCIPAL

Crear un prototipo navegable con el siguiente flujo:

SPLASH

↓

ONBOARDING

↓

INICIAR SESIÓN

↓

DASHBOARD

↓

TOCAR “AGREGAR”

↓

SELECCIONAR “NUEVO GASTO”

↓

SELECCIONAR CATEGORÍA

↓

INGRESAR MONTO

↓

SELECCIONAR CUENTA

↓

GUARDAR

↓

MOSTRAR CONFIRMACIÓN

↓

ACTUALIZAR DASHBOARD

↓

IR A MOVIMIENTOS

↓

VER DETALLE DEL GASTO

---

# 34. PANTALLAS QUE DEBEN GENERARSE

Generar las siguientes vistas completas:

## Inicio y acceso

1. Splash.
2. Onboarding 1.
3. Onboarding 2.
4. Onboarding 3.
5. Iniciar sesión.
6. Crear cuenta.
7. Recuperar contraseña.
8. Nueva contraseña.

## Inicio

9. Dashboard principal.
10. Dashboard con balance oculto.
11. Dashboard sin movimientos.

## Movimientos

12. Lista de movimientos.
13. Lista filtrada.
14. Búsqueda.
15. Filtros.
16. Detalle de movimiento.
17. Editar movimiento.
18. Confirmación de eliminación.
19. Estado sin movimientos.

## Registro financiero

20. Menú agregar.
21. Nuevo ingreso.
22. Seleccionar categoría de ingreso.
23. Nuevo gasto.
24. Seleccionar categoría de gasto.
25. Nueva transferencia.
26. Confirmación de guardado.

## Cuentas

27. Lista de cuentas.
28. Agregar cuenta.
29. Detalle de cuenta.
30. Editar cuenta.
31. Estado sin cuentas.

## Categorías

32. Lista de categorías de gastos.
33. Lista de categorías de ingresos.
34. Crear categoría.
35. Editar categoría.

## Presupuestos

36. Lista de presupuestos.
37. Crear presupuesto.
38. Detalle de presupuesto.
39. Editar presupuesto.
40. Presupuesto excedido.
41. Estado sin presupuestos.

## Metas

42. Lista de metas.
43. Crear meta.
44. Detalle de meta.
45. Agregar ahorro.
46. Meta completada.
47. Estado sin metas.

## Estadísticas

48. Resumen financiero.
49. Gastos por categoría.
50. Evolución mensual.
51. Comparación de períodos.
52. Estado sin datos.

## Calendario

53. Calendario mensual.
54. Detalle del día.
55. Día sin movimientos.

## Notificaciones

56. Lista de notificaciones.
57. Notificaciones leídas.
58. Estado sin notificaciones.

## Perfil y configuración

59. Perfil.
60. Editar perfil.
61. Configuración general.
62. Configuración de notificaciones.
63. Configuración de apariencia.
64. Configuración regional.

## Seguridad

65. Seguridad.
66. Cambiar contraseña.
67. Crear PIN.
68. Confirmar PIN.
69. Pantalla de bloqueo.

## Ayuda

70. Centro de ayuda.
71. Preguntas frecuentes.
72. Detalle de pregunta frecuente.
73. Contactar soporte.

## Estados globales

74. Error de conexión.
75. Error general.
76. Pantalla de carga.
77. Modal de confirmación.
78. Toast de éxito.
79. Snackbar de error.

---

# 35. RESULTADO ESPERADO

El resultado debe ser un sistema de diseño completo y una aplicación móvil coherente.

Todas las pantallas deben compartir:

- Misma identidad visual.
- Misma jerarquía tipográfica.
- Mismo sistema de espaciados.
- Mismos componentes.
- Misma navegación.
- Mismos patrones de interacción.

El diseño debe estar preparado para ser convertido posteriormente en una aplicación móvil nativa para iOS y Android.

Priorizar la experiencia de usuario y la facilidad para registrar y comprender la información financiera.

La aplicación debe sentirse como un producto financiero moderno, confiable y listo para producción, no como un prototipo académico básico.