package bg.hidromat.hidromatprotocol.db;

import androidx.room.TypeConverter;


public class HidromatDbConverter {
    public static final String SEPARATOR = ";";

    @TypeConverter
    public String[] gettingListFromString(String signers) {
        return signers.split(SEPARATOR);
    }

    @TypeConverter
    public String writingStringFromList(String[] list) {

        StringBuilder result = new StringBuilder();
        if (list != null && list.length > 0) {
            for (String entity : list) {
                result.append(entity).append(SEPARATOR);
            }

            result.deleteCharAt(result.lastIndexOf(SEPARATOR));
        }


        return result.toString();
    }
}