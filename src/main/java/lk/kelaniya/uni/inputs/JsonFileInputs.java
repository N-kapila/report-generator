package lk.kelaniya.uni.inputs;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class JsonFileInputs implements Inputs {

    @Override
    public JsonFileInputData getInputs() throws InputException {
        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader("config.json")) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONObject json = (JSONObject) obj;

            JsonFileInputData jsonFileInputData = new JsonFileInputData();

            // database config
            JSONObject db = (JSONObject) json.get("database");
            jsonFileInputData.setDatabaseHost((String) db.get("host"));
            jsonFileInputData.setDatabaseName((String) db.get("database-name"));
            jsonFileInputData.setDatabaseUserName((String) db.get("user-name"));
            jsonFileInputData.setGetDatabasePassword((String) db.get("password"));

            // email config
            JSONObject email = (JSONObject) json.get("email");
            jsonFileInputData.setSenderEmail((String)email.get("sender-mail") );
            jsonFileInputData.setSenderName((String)email.get("sender-name") );
            jsonFileInputData.setSmtpHost((String)email.get("smtp-host") );
            jsonFileInputData.setSmtpPort((String)email.get("smtp-port") );
            jsonFileInputData.setSmtpUserName((String)email.get("smtp-username") );
            jsonFileInputData.setSmtpPassword((String)email.get("smtp-password") );
            return jsonFileInputData;

        } catch (FileNotFoundException e) {
            throw new InputException("config file not found");
        } catch (IOException e) {
            throw new InputException("config file not found");
        } catch (ParseException e) {
            throw new InputException("config format error");
        }

    }
}
