package org.labisd;

import de.rwth.swc.coffee4j.model.InputParameterModel;

import static de.rwth.swc.coffee4j.model.InputParameterModel.inputParameterModel;
import static de.rwth.swc.coffee4j.model.Parameter.parameter;
import static de.rwth.swc.coffee4j.model.constraints.ConstraintBuilder.constrain;

import de.rwth.swc.coffee4j.engine.generator.ipog.Ipog;
import de.rwth.swc.coffee4j.junit.CombinatorialTest;
import de.rwth.swc.coffee4j.junit.provider.configuration.generator.Generator;
import de.rwth.swc.coffee4j.junit.provider.model.ModelFromMethod;

import static org.junit.jupiter.api.Assertions.*;

class PesoConstrainedTest {

    private static InputParameterModel model() {
        return inputParameterModel("peso-model")
                .strength(2)
                .parameters(
                        parameter("Gas").values("H", "O"),
                        parameter("Pressione").values(5, 10, 15),
                        parameter("Volume").values(100, 300, 400)
                ).errorConstraints(
                        constrain("Pressione", "Volume").by((Integer pressione, Integer volume) -> {
                            if (pressione == 10 && volume == 300) 
                            	return false;
                            return true;
                        })
                ).build();
    }

    /** 
     * In grado di individuare il bug:
     * il vincolo ha rimosso (Pressione=10, Volume=300) 
     * ma il bug è causato da (Gas=O, Volume=100) 
     */
    @CombinatorialTest
    @Generator({Ipog.class})
    @ModelFromMethod("model")
    void testPeso_positivo(String gas, int pressione, int volume) {
        assertTrue(Peso.peso(gas, pressione, volume) >= 0,
            String.format("(%s,%d,%d)", gas, pressione, volume));
    }
}
