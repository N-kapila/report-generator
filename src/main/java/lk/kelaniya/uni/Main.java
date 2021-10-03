package lk.kelaniya.uni;

import lk.kelaniya.uni.inputs.*;
import lk.kelaniya.uni.repository.DataRepository;
import lk.kelaniya.uni.repository.SqlDataRepository;

public class Main {

    public static void main(String[] args) {
//        Inputs inputs = new CommandLineInputs(args);
//        DataRepository dataRepository = new SqlDataRepository();
        Inputs jsonFileInpust = new JsonFileInputs();
        try {
            JsonFileInputData data =(JsonFileInputData) jsonFileInpust.getInputs();
            System.out.println(data.getDatabaseHost());
        } catch (InputException e) {
          System.out.println(e.getMessage());
        }


        System.out.println("Welcome to JAVA Report Generator!");
    }

}
