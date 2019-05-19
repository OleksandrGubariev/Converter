package converter;

import org.junit.Test;

import java.util.StringJoiner;

import static org.junit.Assert.*;

public class ConverterTest {

    Converter converter = new Converter();

    @Test
    public void testEncodeOneByteCharacter() {
        String utf8BitsActual = encodeToUTF8Bits(converter.encode("$"));
        String utf8BitsExpected = encodeToUTF8Bits("$");
        assertEquals(utf8BitsExpected, utf8BitsActual);
    }

    @Test
    public void testEncodeTwoBytesCharacter() {
        String utf8BitsActual = encodeToUTF8Bits(converter.encode("¢"));
        String utf8BitsExpected = encodeToUTF8Bits("¢");
        assertEquals(utf8BitsExpected, utf8BitsActual);
    }

    @Test
    public void testEncodeThreeBytesCharacter() {
        String utf8BitsActual = encodeToUTF8Bits(converter.encode("€"));
        String utf8BitsExpected = encodeToUTF8Bits("€");
        assertEquals(utf8BitsExpected, utf8BitsActual);
    }

    @Test
    public void testEncodeFourBytesCharacter() {
        String utf8BitsActual = encodeToUTF8Bits(converter.encode("\uD800\uDF48"));
        String utf8BitsExpected = encodeToUTF8Bits("\uD800\uDF48");
        assertEquals(utf8BitsExpected, utf8BitsActual);
    }

    public String encodeToUTF8Bits(byte[] array) {
        StringJoiner bits = new StringJoiner(" ");
        for (byte b : array) {
            bits.add(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }
        return bits.toString();
    }

    public String encodeToUTF8Bits(String value) {
        byte[] array = value.getBytes();
        return encodeToUTF8Bits(array);
    }
}