package co.bassan.app.config.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pbastidas on 1/29/16.
 */
public class CustomDateSerializer extends JsonSerializer<Date> {

    private static final DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {

        if(date == null){
            jsonGenerator.writeString("null");
        }

        jsonGenerator.writeString(df.format(date));

    }
}
