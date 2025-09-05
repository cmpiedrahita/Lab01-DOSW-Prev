package com.lab.retos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.List;

class CarreraParalelaTest {

    @Test
    @DisplayName("Analizar dos listas combinadas")
    void analizarListas_ok() {
        Resultados r = CarreraParalela.analizarListas(List.of(5, 2, 9), List.of(4, 2, 7, 10));
        Assertions.assertEquals(10, r.maximo());
        Assertions.assertEquals(2, r.minimo());
        Assertions.assertEquals(7, r.cantidad());
        Assertions.assertTrue(r.mayorMultiploDe2());
        Assertions.assertFalse(r.mayorDivisorDe2()); // 2 % 10 != 0
        Assertions.assertFalse(r.cantidadPar());
        Assertions.assertTrue(r.cantidadImpar());
    }

    @Test
    @DisplayName("Validar excepción cuando no hay elementos")
    void analizarListas_vacio() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                CarreraParalela.analizarListas(List.of(), List.of()));
    }
}
