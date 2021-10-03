package lk.kelaniya.uni;

import lk.kelaniya.uni.inputs.*;
import lk.kelaniya.uni.repository.DataRepository;
import lk.kelaniya.uni.repository.DataRepositoryException;
import lk.kelaniya.uni.repository.SqlDataRepository;

public class Main {

    public static void main(String[] args) {
       // Inputs commandLineInputs = new CommandLineInputs(args);
        Inputs jsonFileInputs = new JsonFileInputs();
        try {
          //  CommandLineInputData commandLineInputData = (CommandLineInputData) commandLineInputs.getInputs();
            JsonFileInputData jsonFileInputData = (JsonFileInputData) jsonFileInputs.getInputs();
            DataRepository dataRepository = new SqlDataRepository(jsonFileInputData);
            dataRepository.connect();
        } catch (InputException | DataRepositoryException e) {
           System.out.println(e.getMessage());
        }

//        Inputs jsonFileInpust = new JsonFileInputs();
//        try {
//            JsonFileInputData data =(JsonFileInputData) jsonFileInpust.getInputs();
//            System.out.println(data.getDatabaseHost());
//        } catch (InputException e) {
//          System.out.println(e.getMessage());
//        }


        System.out.println("Welcome to JAVA Report Generator!");
    }

}
