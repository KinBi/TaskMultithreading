package com.monkeybusiness.training.task;

import com.monkeybusiness.training.task.model.entity.Van;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
  public static void main(String[] args) {
    List<Van> vanList = new ArrayList<>();
    vanList.add(new Van(3, false));
    vanList.add(new Van(5, false));
    vanList.add(new Van(2, false));
    vanList.add(new Van(8, false));
    vanList.add(new Van(2, false));
    vanList.add(new Van(5, false));
    vanList.add(new Van(7, true));
    vanList.add(new Van(9, true));
    vanList.add(new Van(1, false));
    ExecutorService executorService = Executors.newFixedThreadPool(4 );

    for (Van van : vanList) {
      executorService.submit(van);
    }
    executorService.shutdown();
  }
}
