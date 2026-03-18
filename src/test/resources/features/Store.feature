#language: es
@store
Característica: Product - Store

  @test
  Esquema del escenario: Validación del precio de un producto
    Dado estoy en la página de la tienda
    Y me logueo con mi usuario "<usuario>" y clave "<clave>"
    Cuando navego a la categoria "<categoria>" y subcategoria "<subcategoria>"
    Y agrego 2 unidades del primer producto al carrito
    Entonces valido en el popup la confirmación del producto agregado
    Y valido en el popup que el monto total sea calculado correctamente
    Cuando finalizo la compra
    Entonces valido el titulo de la pagina del carrito
    Y vuelvo a validar el calculo de precios en el carrito

    Ejemplos:
      | usuario               | clave         | categoria | subcategoria |
      | erik.test@nttdata.com | erikmcl1234   | Clothes   | Men          |
      | usuario@invalido.com  | claveInvalida  | Clothes   | Men          |
      | erik.test@nttdata.com | erikmcl1234   | Autos     | Sedan        |
