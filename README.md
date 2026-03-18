# NTT Data - Store Automation Testing

Proyecto de automatizacion de pruebas de regresion para la tienda en linea QA Lab de NTT Data.
Construido con Selenium WebDriver, Cucumber BDD y Java siguiendo el patron Page Object Model.

## Tecnologias utilizadas

- Java 17
- Selenium WebDriver 4.8.0
- Cucumber 7.3.4 (BDD)
- JUnit 4.13.1
- Maven

## Estructura del proyecto

```
src/test/
├── java/com/nttdata/
│   ├── core/              # DriverManager - configuracion del WebDriver
│   ├── page/              # Page Objects - locators de la UI
│   ├── steps/             # Logica de cada accion
│   ├── stepsdefinitions/  # Mapeo Gherkin con Java
│   └── runner/            # RunnerTest - punto de entrada
└── resources/
    └── features/          # Escenarios en Gherkin (español)
```

## Escenarios automatizados

| # | Escenario | Resultado esperado |
|---|-----------|-------------------|
| 1 | Login con credenciales validas + compra completa | PASS |
| 2 | Login con credenciales invalidas | FAIL controlado |
| 3 | Navegacion a categoria inexistente "Autos" | FAIL controlado |

## Flujo del escenario principal

1. Navegar a la tienda en linea
2. Iniciar sesion con usuario y contrasena
3. Validar autenticacion exitosa
4. Navegar a categoria y subcategoria
5. Agregar 2 unidades del primer producto al carrito
6. Validar popup de confirmacion y calculo de precio
7. Finalizar compra
8. Validar titulo de pagina del carrito
9. Validar calculo de precios en el carrito

## Requisitos previos

- Java 17 instalado
- Maven instalado
- Google Chrome instalado
- ChromeDriver compatible con tu version de Chrome en la carpeta `drivers/`

## Como ejecutar

```bash
mvn verify
```

El reporte se genera automaticamente en:

```
target/cucumber/cucumber-report.html
```

## Reportes

El proyecto genera:
- **HTML Report**: `target/cucumber/cucumber-report.html`
- **JSON Report**: `target/cucumber/cucumber.json`

Abrir el HTML en el navegador para visualizar los resultados con capturas de pantalla de evidencia por cada step.

## URL de la tienda

https://qalab.bensg.com/store
