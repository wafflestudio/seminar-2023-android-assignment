import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import java.io.IOException

class NumberAdapter : JsonAdapter<Number>() {

    @FromJson
    override fun fromJson(reader: JsonReader): Number? {
        return when (reader.peek()) {
            JsonReader.Token.NUMBER -> reader.nextDouble()
            JsonReader.Token.STRING -> reader.nextString().toDoubleOrNull()
            else -> throw IOException("Unexpected token $reader")
        }
    }

    @ToJson
    override fun toJson(writer: JsonWriter, value: Number?) {
        writer.value(value)
    }
}