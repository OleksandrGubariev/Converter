package converter;

public class Converter {
    private int codePointCount;

    public byte[] encode(String value) {
        codePointCount = value.codePointCount(0,value.length());
        int[] codePointArray = new int[codePointCount];
        for (int i = 0; i < codePointCount; i++) {
            codePointArray = getCodePoints(value);
        }
        byte[] bytes = new byte[codePointCount];
        for (int element : codePointArray) {
            bytes = getBytes(element);
        }
        return bytes;
    }

    private int[] getCodePoints(String string) {
        int[] codePoints = new int[codePointCount];
        for (int i = 0; i < codePointCount; i++) {
            codePoints[i] = Character.codePointAt(string, i);
        }
        return codePoints;
    }

    private byte[] getBytes(int value) {
        if (value < 0 || value >= 1114111) {
            throw new IndexOutOfBoundsException("Value must be beetwen[0;1114111)");
        }
        byte[] byteArray = new byte[0];
        if (value < 128) {
            byteArray = new byte[1];
            byteArray[0] = (byte) (value);
        } else if (value < 2047) {
            byteArray = new byte[2];
            byteArray[0] = (byte) (value >> 6);
            byteArray[0] |= (1 << 7);
            byteArray[0] |= (1 << 6);
            byteArray[0] &= ~(1 << 5);
            byteArray[1] = (byte) (value);
            byteArray[1] |= (1 << 7);
            byteArray[1] &= ~(1 << 6);
        } else if (value >= 2048 && value < 65535) {
            byteArray = new byte[3];
            byteArray[0] = (byte) (value >> 12);
            byteArray[0] |= (1 << 7);
            byteArray[0] |= (1 << 6);
            byteArray[0] |= (1 << 5);
            byteArray[0] &= ~(1 << 4);
            byteArray[1] = (byte) (value >> 6);
            byteArray[1] |= (1 << 7);
            byteArray[1] &= ~(1 << 6);
            byteArray[2] = (byte) (value);
            byteArray[2] |= (1 << 7);
            byteArray[2] &= ~(1 << 6);
        } else if (value >= 65535) {
            byteArray = new byte[4];
            byteArray[0] = (byte) (value >> 18);
            byteArray[0] |= (1 << 7);
            byteArray[0] |= (1 << 6);
            byteArray[0] |= (1 << 5);
            byteArray[0] |= (1 << 4);
            byteArray[0] &= ~(1 << 3);
            byteArray[1] = (byte) (value >> 12);
            byteArray[1] |= (1 << 7);
            byteArray[1] &= ~(1 << 6);
            byteArray[2] = (byte) (value >> 6);
            byteArray[2] |= (1 << 7);
            byteArray[2] &= ~(1 << 6);
            byteArray[3] = (byte) (value);
            byteArray[3] |= (1 << 7);
            byteArray[3] &= ~(1 << 6);
        }
        return byteArray;
    }

}

