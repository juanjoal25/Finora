# Finora

Finora es una aplicación nativa de finanzas personales para Android, escrita en Kotlin con
Jetpack Compose. Esta primera versión (v1) permite registrar ingresos y gastos, consultar el
balance y el historial de movimientos, y gestionar un perfil básico — todo con persistencia
local (sin backend todavía).

## Contenido

- [Stack tecnológico](#stack-tecnológico)
- [Arquitectura](#arquitectura)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Cómo abrir y ejecutar el proyecto](#cómo-abrir-y-ejecutar-el-proyecto)
- [Ejecutar las pruebas](#ejecutar-las-pruebas)
- [Funcionalidades de la v1](#funcionalidades-de-la-v1)
- [Fuera de alcance en la v1](#fuera-de-alcance-en-la-v1-preparado-para-el-futuro)
- [Supuestos y decisiones de diseño](#supuestos-y-decisiones-de-diseño)
- [Diseño visual](#diseño-visual)
- [Versiones de dependencias](#versiones-de-dependencias)

## Stack tecnológico

- **Kotlin** + **Jetpack Compose** + **Material 3** — UI 100% declarativa, nativa de Android.
- **Navigation Compose** — navegación centralizada (sin rutas dispersas en los Composables).
- **ViewModel** + **Kotlin Coroutines** + **Flow / StateFlow** — estado y lógica asíncrona.
- **Hilt** — inyección de dependencias.
- **Room** — persistencia local (usuarios, movimientos, categorías).
- **DataStore (Preferences)** — sesión activa y preferencia de tema (claro/oscuro/sistema).
- **Gradle Kotlin DSL** con catálogo de versiones (`gradle/libs.versions.toml`).

No se usa ninguna dependencia fuera de esta lista: la app es completamente nativa.

## Arquitectura

Clean Architecture + MVVM + Repository Pattern, con inyección de dependencias en todas las
fronteras entre capas:

```
UI (Composable)
   ↓
ViewModel               (presentation)
   ↓
UseCase                 (domain — Kotlin puro, sin Android)
   ↓
Repository (interfaz)   (domain)
   ↓
Repository (impl.)      (data)
   ↓
DAO / DataStore         (data)
```

Reglas que se respetan en todo el proyecto:

- **`domain` no depende de Android ni de Compose.** Solo Kotlin puro (`kotlinx.coroutines`,
  `java.time`, etc.).
- Las interfaces de repositorio viven en `domain`; sus implementaciones en `data`.
- Ningún `ViewModel` accede directamente a un DAO o a DataStore — siempre pasa por un UseCase
  y una interfaz de repositorio.
- Ningún Composable contiene lógica de negocio; toda validación vive en
  [`core/validation/Validator.kt`](app/src/main/java/com/finora/app/core/validation/Validator.kt)
  y se reutiliza desde los UseCases (Registro, Nuevo Ingreso, Nuevo Gasto, Editar movimiento,
  Información personal).
- Los estados de UI son `sealed interface` (`Loading` / `Success` / `Error` / `Empty`), nunca
  banderas booleanas sueltas.

## Estructura del proyecto

```
app/src/main/java/com/finora/app/
├── core/               # Utilidades puras: Result, validación, formatters, constantes
├── domain/             # Modelos, interfaces de repositorio y UseCases (Kotlin puro)
│   ├── model/
│   ├── repository/
│   └── usecase/{auth,transaction,profile,category}/
├── data/               # Implementación con Room + DataStore
│   ├── local/{database,dao,entities,datastore}/
│   ├── mapper/
│   └── repository/
├── di/                 # Módulos de Hilt
├── presentation/
│   ├── theme/          # Paleta de color y tema (claro/oscuro) extraídos del diseño
│   ├── navigation/      # Rutas centralizadas, NavHost, bottom bar
│   ├── components/      # Componentes reutilizables (botones, campos, tarjetas, estados...)
│   ├── auth/{login,register}/
│   ├── home/
│   ├── movements/{list,detail}/
│   ├── transaction/{income,expense}/
│   └── profile/
├── MainActivity.kt
└── FinoraApplication.kt
```

Cada pantalla sigue el patrón `Screen` (Scaffold + estado) → `Content` (layout) →
`components/*` (piezas reutilizables), evitando Composables gigantes.

### Preparado para crecer

La v1 deliberadamente no incluye presupuestos, metas, estadísticas avanzadas, calendario
financiero, notificaciones, cuentas/tarjetas, sincronización bancaria ni inversiones. La
estructura por capas permite agregar estos módulos después sin reestructurar lo existente, por
ejemplo:

```
domain/
 ├── budget/
 ├── goal/
 ├── statistics/
 └── account/
```

cada uno con su propio `model/`, `repository/` y `usecase/`, siguiendo el mismo patrón que
`transaction/`.

## Cómo abrir y ejecutar el proyecto

> **Verificado**: el proyecto ya se compiló y probó de extremo a extremo por línea de comandos
> en esta máquina (`./gradlew assembleDebug` produce `app/build/outputs/apk/debug/app-debug.apk`;
> `./gradlew test` corre las 19 pruebas unitarias sin fallos). El wrapper de Gradle
> (`gradlew`, `gradlew.bat`, `gradle-wrapper.jar`) ya está generado y listo para usar.

1. Abre la carpeta raíz del repositorio directamente en **Android Studio** ("Open" → selecciona
   `Finora/`).
2. Deja que Gradle sincronice (usará el wrapper ya generado, Gradle 9.8.0).
3. Ejecuta la configuración `app` en un emulador o dispositivo Android (API 26+).

También puedes compilar por línea de comandos sin abrir Android Studio:

```bash
./gradlew assembleDebug
```

No se requiere ningún backend ni variable de entorno: todo funciona 100% local desde el primer
arranque.

## Ejecutar las pruebas

```bash
./gradlew test
```

Cubre los UseCases de autenticación y movimientos (validaciones, cálculo de balance, creación,
actualización y eliminación), usando fakes en memoria (`app/src/test/.../testutil/`) — sin
Room ni Hilt en las pruebas unitarias.

## Funcionalidades de la v1

- **Autenticación**: registro, inicio de sesión y cierre de sesión (simulados localmente).
- **Inicio / Dashboard**: saludo, balance (con mostrar/ocultar), resumen de ingresos y gastos,
  últimos 5 movimientos, estado vacío con llamada a la acción.
- **Ingresos y Gastos**: formularios con validación (monto > 0, categoría obligatoria, fecha
  válida, descripción opcional).
- **Movimientos**: listado completo, filtro Todos/Ingresos/Gastos, búsqueda por categoría o
  descripción, detalle, edición y eliminación (con confirmación destructiva).
- **Perfil**: tarjeta de cabecera con avatar, nombre, correo y fecha de alta ("Miembro
  desde"), datos básicos editables (nombre, correo), preferencia de **moneda** (COP, USD, EUR,
  MXN, ARS, PEN — aplicada a todos los montos de la app vía `LocalCurrency`) y de **apariencia**
  (claro/oscuro/sistema) persistidas con DataStore, pantalla "Acerca de Finora" con créditos
  editables (nombre/correo personalizable, precargado con el usuario actual) y versión de la
  app, pantalla de seguridad preparada (sin biometría todavía, ya que no hay backend real que
  proteger), cerrar sesión con confirmación y sin poder volver atrás al Home. "Notificaciones"
  e "Idioma" se muestran como "Próximamente" — están en el diseño pero fuera de alcance de v1.

## Fuera de alcance en la v1 (preparado para el futuro)

Presupuestos, metas de ahorro, estadísticas avanzadas, calendario financiero, notificaciones
financieras, cuentas/tarjetas avanzadas, sincronización bancaria y funciones de inversión.

## Supuestos y decisiones de diseño

Estas decisiones se tomaron porque la especificación original no las precisaba, o porque el
entorno de generación de este proyecto no pudo verificar ciertos detalles en vivo:

- **Categorías de ingreso**: la especificación solo enumera categorías de gasto. Se agregó un
  conjunto razonable por defecto (Salario, Freelance, Inversiones, Ventas, Regalos, Otros) —
  ver [`core/constants/Categories.kt`](app/src/main/java/com/finora/app/core/constants/Categories.kt).
- **Búsqueda y filtros de movimientos**: se implementan como estados dentro de una sola
  pantalla (`MovementsScreen`), no como rutas separadas, porque la lista de rutas de la
  especificación solo define `movements`.
- **Editar movimiento**: reutiliza las pantallas de Nuevo Ingreso/Nuevo Gasto en modo edición
  (argumento opcional `transactionId`), en vez de pantallas de edición dedicadas.
- **Contraseñas**: se almacenan con **PBKDF2WithHmacSHA256** (salt aleatorio por usuario, ~120k
  iteraciones), nunca en texto plano. Esto es una simulación local razonable para v1, **no**
  un reemplazo de autenticación de backend real (sin pepper, sin rate-limiting de servidor).
  `AuthRepository` está diseñada como interfaz de dominio para que un backend real (Firebase,
  Supabase, API propia) pueda reemplazar la implementación local sin tocar el resto de la app
  — solo se cambia `data/repository/AuthRepositoryImpl.kt` y el `@Binds` correspondiente en
  `di/RepositoryModule.kt`.
- **Diseño visual**: `Diseño - Finora/logo/DESIGN.md` documenta el sistema de diseño completo
  ("Fintech Precision") con tokens exactos de color, tipografía y forma; esos valores son la
  fuente de verdad usada en
  [`presentation/theme/Color.kt`](app/src/main/java/com/finora/app/presentation/theme/Color.kt),
  [`Type.kt`](app/src/main/java/com/finora/app/presentation/theme/Type.kt) y
  [`Theme.kt`](app/src/main/java/com/finora/app/presentation/theme/Theme.kt) (ver sección
  siguiente). Los mockups de pantalla en `Diseño - Finora/*/*.svg` están exportados de Figma
  con el texto convertido a paths, así que su contenido textual/tipografía exacta no se pudo
  leer directamente del archivo — el layout y copy de cada pantalla se tomó de la
  especificación funcional en su lugar.
- **Perfil y "Acerca de Finora"**: estas dos pantallas sí se replicaron a partir de mockups de
  referencia (wireframes de estructura/layout, no de color — los tokens de color siguen siendo
  los de DESIGN.md), omitiendo elementos del mockup fuera del alcance v1 (insignia de
  suscripción "Pro", pestaña "Metas"). La sección "Equipo" de Créditos es editable porque el
  mockup no fija nombres reales; se precarga con el usuario con sesión iniciada y se puede
  sobrescribir.
- **Marca Finora en varias pantallas**: Inicio, Movimientos y Perfil comparten un
  `FinoraTopBar` (logo + "Finora" + título) en vez de cada uno definir su propia barra
  superior, para una identidad visual consistente.

## Diseño visual

El sistema de diseño ("Fintech Precision", `Diseño - Finora/logo/DESIGN.md`) define tokens
exactos de Material 3 que la app usa tal cual — no aproximaciones:

| Rol M3 | Color | Uso |
|---|---|---|
| `primary` / `primaryContainer` | `#000F22` / `#0A2540` | Navy institucional: headers, CTAs, balance hero, enlaces |
| `secondary` / `secondaryContainer` | `#006C4A` / `#82F5C1` | Verde: ingresos, estado seleccionado, chips de categoría |
| `tertiary` | `#030046` | Acentos de proyecciones/analítica (reservado para v2) |
| `error` / `errorContainer` | `#BA1A1A` / `#FFDAD6` | Gastos, validaciones, acciones destructivas |
| `surface*` (5 niveles) | `#FAF8FF` → `#DAE2FD` | Fondo, tarjetas y contenedores por elevación |

**Tipografía**: Inter (vía Google Fonts Downloadable Fonts API, con fallback automático a la
tipografía del sistema si no hay Play Services/red) en las 15 categorías del type scale de M3,
mapeadas 1:1 a los tokens `display-hero`, `balance-lg`, `headline-*`, `title-*`, `body-*`,
`label-*` del DESIGN.md.

**Forma**: radios de esquina explícitos por componente, no los valores por defecto de M3 —
botones e inputs 12dp, tarjetas 16dp, hoja inferior 24dp (solo esquinas superiores), chips y
badges en píldora completa.

**Logo**: el ícono de la app (adaptativo) y el logo mostrado en la pantalla de Login se
recrearon como vector drawables a partir de `Diseño - Finora/logo/code.html` (barras de
crecimiento en degradado azul→verde sobre una base navy) — no un ícono genérico.

## Versiones de dependencias

Fijadas en [`gradle/libs.versions.toml`](gradle/libs.versions.toml). Se consultaron en vivo
contra los repositorios oficiales de Maven/Google y **se verificaron compilando el proyecto
de verdad** (`./gradlew assembleDebug` genera un APK; `./gradlew test` corre las 19 pruebas
unitarias, las 19 pasan):

| Componente | Versión |
|---|---|
| Gradle | 9.8.0 |
| Android Gradle Plugin (AGP) | 9.4.1 |
| Kotlin | 2.4.20 |
| KSP | 2.3.12 |
| Compose BOM | 2026.09.00 |
| Hilt | 2.60.1 |
| Room | 2.8.5 |
| Navigation Compose | 2.10.2 |
| DataStore Preferences | 1.2.1 |
| compileSdk / targetSdk | 37 |
| minSdk | 26 |

**Nota sobre AGP 9**: desde la versión 9.0, AGP incluye soporte de Kotlin integrado
("built-in Kotlin"), por lo que `app/build.gradle.kts` **no aplica** el plugin
`org.jetbrains.kotlin.android` (aplicarlo ahora es un error) — solo se aplican
`com.android.application`, `org.jetbrains.kotlin.plugin.compose` (compilador de Compose),
`com.google.devtools.ksp` y `com.google.dagger.hilt.android`. La versión del compilador de
Kotlin que usa AGP internamente se fija en el `build.gradle.kts` raíz vía un bloque
`buildscript` para que coincida con la versión del plugin de Compose.
