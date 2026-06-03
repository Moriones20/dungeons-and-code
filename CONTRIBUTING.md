# Cómo trabajamos en Dungeons & Code

Somos un equipo de **5 personas con el mismo nivel y los mismos permisos**.
Como no hay un jefe, **estas reglas hacen de jefe**. Si todos las seguimos,
evitamos pisarnos el código y los conflictos de merge.

---

## 1. Reparto de responsabilidades

Cada persona es **dueña** de su área: revisa los PRs que la tocan y mantiene su paquete.

El juego es tipo **Preguntados** (trivia): se muestra una pregunta, el jugador
responde y se cambia de ventana (siguiente pregunta / pantalla final).
Cada persona es **dueña** de su área: revisa los PRs que la tocan y mantiene su paquete.

| Persona | Área | Paquetes principales |
|---|------|----------------------|
| 1 | Sistema de ventanas y botones | `controlador.Navegador`, `vista.*` |
| 2 | Preguntas y su lógica | `modelo` (`Pregunta`, `Partida`...) |
| 3 | Base de datos de preguntas | `basedatos` (conexión + `RepositorioPreguntas`) |
| 4 | Puntuación y pantalla final | `servicio.ServicioPuntajes`, `vista.resultado` |
| 5 | Aspectos visuales + integración | `vista.componentes`, estilos CSS, coordinación/build |

**Orden de trabajo acordado** (cada paso se apoya en el anterior):
1. Ventanas y botones (navegación entre pantallas).
2. Preguntas y su lógica, de la mano con la base de datos.
3. Puntuación y pantalla final.
4. Aspectos visuales al final.

> Trabaja sobre todo en **tu** paquete. Si necesitas tocar el de otra persona,
> avísale antes para que no estéis editando el mismo archivo a la vez.

---

## 2. Reglas técnicas (acordadas)

- **Java 25.** Todos usamos el **mismo JDK 25**. Comprueba con `java -version`.
- **Código en español:** clases, métodos y variables con nombres en español.
- **Pruebas con JUnit 5** en `src/test/java`, replicando la estructura de paquetes.
- **El paquete `modelo` NO importa JavaFX.** La lógica del juego debe poder
  probarse sin abrir una ventana.

---

## 3. Flujo de Git

**Nadie pushea directo a `main`.** Siempre con rama + Pull Request.

### Pasos para cada tarea

```bash
# 1. Parte siempre de main actualizada
git checkout main
git pull

# 2. Crea tu rama (nombre descriptivo)
git checkout -b feature/navegacion-ventanas

# 3. Trabaja y haz commits pequeños y frecuentes
git add .
git commit -m "feat: cambiar de la pantalla de menu a la de juego"

# 4. Sube tu rama
git push -u origin feature/navegacion-ventanas

# 5. Abre un Pull Request en GitHub hacia main
```

### Nombres de rama

- `feature/<que-haces>` — funcionalidad nueva
- `fix/<que-arreglas>` — corrección de bug
- `refactor/<que-mejoras>` — reorganizar sin cambiar comportamiento

### Mensajes de commit (Conventional Commits)

`feat:` nueva función · `fix:` arreglo · `refactor:` · `test:` · `docs:` · `chore:`

Ejemplo: `fix: la respuesta correcta ahora suma puntaje`

---

## 4. Reglas de los Pull Requests

1. **PRs pequeños.** Una rama = una tarea. Cuanto más pequeño, más fácil de revisar y menos conflictos.
2. **Mínimo 1 aprobación** de **otra persona** antes de fusionar.
3. **No apruebes ni fusiones tu propio PR.** Rotad quién revisa.
4. Antes de pedir revisión: que **compile** y que **pasen los tests** (`mvn test` o desde IntelliJ).
5. Si hay conflictos, los resuelve **quien abrió el PR** (haciendo `git pull` de main sobre su rama).

> **Recomendado:** en GitHub → *Settings → Branches → Add rule* sobre `main`:
> activad *Require a pull request before merging* y *Require approvals (1)*.
> Así la regla "nadie pushea a main" la aplica GitHub sola.

---

## 5. Arrancar el proyecto

- **IntelliJ:** abrir como proyecto Maven y ejecutar **`Launcher.java`** (no `App`).
- **Terminal** (si tienes Maven): `mvn javafx:run` para jugar, `mvn test` para las pruebas.

---

## 6. Evita estos líos clásicos

- **No commitees archivos de `.idea/`** que cambian solos (themes, misc). Ya están en `.gitignore`; si te aparece alguno, no lo añadas.
- **No reformatees** archivos que no estás tocando: genera PRs enormes y conflictos. El `.editorconfig` mantiene el formato uniforme.
- **Haz `git pull` a menudo** para no alejarte de `main`.
