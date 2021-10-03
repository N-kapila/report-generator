package lk.kelaniya.uni.output;

import lk.kelaniya.uni.inputs.JsonFileInputData;
import lk.kelaniya.uni.inputs.OutputType;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

public class DataOutputFactoryTest {
    @Test
    public void should_return_ExcelDataOutput_object_when_outputType_is_File(){
        DataOutputFactory dataOutputFactory = new DataOutputFactory();
        DataOutput dataOutput = dataOutputFactory.getInstance(
                OutputType.File,
              new  DataResult(),
                new JsonFileInputData(),
                "test@gmail.com"
        );
        assertThat(dataOutput,instanceOf(ExcelDataOutput.class));
    }

}