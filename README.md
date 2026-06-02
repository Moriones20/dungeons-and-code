# Dungeons & Code

Juego de mazmorras en **JavaFX**, proyecto universitario en equipo (5 personas).

## Requisitos

- **JDK 25** (el mismo para todo el equipo)
- Maven (o usar el Maven embebido de IntelliJ)

## Arrancar

```bash
mvn javafx:run     # jugar
mvn test           # ejecutar las pruebas (JUnit 5)
```

O desde IntelliJ: abrir como proyecto Maven y ejecutar **`org.poli.Launcher`**
con el botón verde ▶ (no `App`: `App` extiende `Application` y la JVM no puede
arrancarla directamente).

> **¿Ves un aviso amarillo `Unsupported JavaFX configuration...`?** Es normal y
> no rompe nada: sale solo con el botón verde de IntelliJ. Si quieres un arranque
> 100 % limpio, usa `mvn javafx:run`.

## Estructura

```
org.poli
├── App.java        Arranque JavaFX
├── modelo/         Lógica del juego (sin JavaFX, testeable)
├── controlador/    Une modelo y vista (bucle de juego, input)
├── vista/          Pantallas y componentes JavaFX
├── servicio/       Guardado / carga
└── util/           Constantes y utilidades
```

## Cómo colaboramos

Antes de tocar nada, lee **[CONTRIBUTING.md](CONTRIBUTING.md)**: reparto de
tareas, flujo de Git y reglas de Pull Requests.
