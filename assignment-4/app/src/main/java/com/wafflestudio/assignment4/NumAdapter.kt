import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.ToJson
import java.io.IOException

class NumAdapter : JsonAdapter<Number>() {

    @ToJson
    override fun toJson(writer: JsonWriter, numericValue: Number?) {
        writer.value(numericValue)
    }

    @FromJson
    override fun fromJson(reader: JsonReader): Number? {
        return when (reader.peek()) {
            JsonReader.Token.NUMBER -> parseNumber(reader)
            JsonReader.Token.STRING -> parseStringAsNumber(reader)
            else -> throw IOException("Invalid token encountered in JSON: ${reader.peek()}")
        }
    }

    private fun parseNumber(reader: JsonReader): Number {
        return reader.nextDouble()
    }

    private fun parseStringAsNumber(reader: JsonReader): Number? {
        return reader.nextString().toDoubleOrNull()
    }
}
