package org.labisd;

import de.rwth.swc.coffee4j.model.InputParameterModel;

import static de.rwth.swc.coffee4j.model.InputParameterModel.inputParameterModel;
import static de.rwth.swc.coffee4j.model.Parameter.parameter;

import de.rwth.swc.coffee4j.engine.generator.ipog.Ipog;
import de.rwth.swc.coffee4j.junit.CombinatorialTest;
import de.rwth.swc.coffee4j.junit.provider.configuration.generator.Generator;
import de.rwth.swc.coffee4j.junit.provider.model.ModelFromMethod;

import static org.junit.jupiter.api.Assertions.*;

class PesoCombinatorialTest {

    private static InputParameterModel model() {
        return inputParameterModel("peso-model")
                .strength(2)
                .parameters(
                        parameter("Gas").values("H", "O"),
                        parameter("Pressione").values(5, 10, 15),
                        parameter("Volume").values(100, 200, 300, 400)
                ).build();
    }

    /* In grado di individuare il bug (un test case fallisce) */
    @CombinatorialTest
    @Generator({Ipog.class})
    @ModelFromMethod("model")
    void testPeso_nonNegativo(String gas, int pressione, int volume) {
        System.out.println(gas + "," + pressione + "," + volume);
        assertTrue(Peso.peso(gas, pressione, volume) >= 0,
            String.format("(%s,%d,%d)", gas, pressione,volume));
    }

    /* Non in grado di individuare il bug (nessun test case fallisce, richiede t=3) */
    @CombinatorialTest
    @Generator({Ipog.class})
    @ModelFromMethod("model")
    void testPesoEsteso_nonNegativo(String gas, int pressione, int volume) {
        assertTrue(Peso.pesoEsteso(gas, pressione, volume) >= 0, 
            String.format("(%s,%d,%d)", gas, pressione, volume));
    }
}
