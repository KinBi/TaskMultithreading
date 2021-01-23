package com.monkeybusiness.training.task.util.reader;

import com.monkeybusiness.training.task.exception.DataReaderException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class DataReader {
  private static final Logger LOGGER = LogManager.getLogger();
  public static final String DEFAULT_FILEPATH = "res/data/data.txt";

  public String readData(String filepath) throws DataReaderException {
    Path path = Paths.get(filepath);
    if (!path.toFile().exists()) {
      filepath = DEFAULT_FILEPATH;
      path = Paths.get(filepath);
    }
    String data;
    try {
      data = Files.lines(path).collect(Collectors.joining());
    } catch (IOException e) {
      LOGGER.log(Level.ERROR, e);
      throw new DataReaderException(e);
    }
    return data;
  }
}
