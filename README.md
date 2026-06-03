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

Juego tipo **Preguntados**: muestra pregunta → respondes → cambia de ventana.
Cada carpeta es una *base* (stub) lista para que su dueño la implemente.

```
org.poli
├── App.java        Arranque JavaFX (abre la ventana — base funcional)
├── Launcher.java   Enciende la app (no tocar)
├── modelo/         Pregunta y lógica de la partida (sin JavaFX, testeable)
├── basedatos/      Repositorio de preguntas (BD)
├── controlador/    Navegador: cambia de pantalla
├── vista/          Pantallas: menu/, juego/, resultado/
├── servicio/       Puntajes
└── util/           Constantes y configuración
```

## Cómo colaboramos

Antes de tocar nada, lee **[CONTRIBUTING.md](CONTRIBUTING.md)**: reparto de
tareas, flujo de Git y reglas de Pull Requests.
