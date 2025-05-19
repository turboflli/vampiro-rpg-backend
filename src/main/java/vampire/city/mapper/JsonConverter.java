package vampire.city.mapper;



import vampire.city.model.*;

import java.text.SimpleDateFormat;
import java.util.Map;

public class JsonConverter {

    private static SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyy");

    public static User json_toUser(Map<String,Object> json) {
        boolean gm = retriveIsGm(json);
        return new User((String) json.get("username"),
                (String) json.get("senha"),
                gm
        );
    }

    private static boolean retriveIsGm(Map<String, Object> json) {
        Object isGm = json.get("Gm");
        if (isGm == null){
            return false;
        }
        if (isGm instanceof String){
            return Boolean.parseBoolean((String) isGm);//Pela tela vem como String
        } else {
            return (boolean) isGm;//Diretamente na api vem como Integer
        }
    }

}
