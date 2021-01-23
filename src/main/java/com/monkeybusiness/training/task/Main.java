package com.monkeybusiness.training.task;

import com.monkeybusiness.training.task.exception.DataReaderException;
import com.monkeybusiness.training.task.model.entity.Van;
import com.monkeybusiness.training.task.util.creator.VanCreator;
import com.monkeybusiness.training.task.util.parser.Parser;
import com.monkeybusiness.training.task.util.reader.DataReader;

import java.util.List;

public class Main {
  public static void main(String[] args) {
    DataReader dataReader = new DataReader();
    String data = "";
    try {
      data = dataReader.readData("text.txt");
    } catch (DataReaderException e) {
      e.printStackTrace();
    }
    Parser parser = new Parser();
    List<String> list = parser.parseData(data);
    VanCreator creator = new VanCreator();
    List<Van> vanList = creator.create(list);
    for (Van van : vanList) {
      van.run();
    }
  }
}
