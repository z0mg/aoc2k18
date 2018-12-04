package day1;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class Day1Solver
{
  private boolean mIsDupeFound = false;

  public void Solve(boolean aShouldFindDuplicateFrequency) throws IOException
  {
    int currentFrequency = 0;
    Set<Integer> occurredFrequencies = new HashSet<Integer>();
    occurredFrequencies.add(currentFrequency);

    List<String> operations = ReadInput();
    do
    {
      for (String op : operations)
      {
        currentFrequency = ApplyOperation(currentFrequency, op);

        if (IsAlreadySeenFrequency(currentFrequency, occurredFrequencies)
            && aShouldFindDuplicateFrequency)
        {
          System.out.println("got first dupe frequency: " + currentFrequency);
          return;
        }
      }
    }
    while (aShouldFindDuplicateFrequency && !mIsDupeFound);

    System.out.println("final frequency: " + currentFrequency);
  }

  private boolean IsAlreadySeenFrequency(int aCurrentFrequency, Set<Integer> aOccurredFrequencies)
  {
    return !aOccurredFrequencies.add(aCurrentFrequency);
  }

  private List<String> ReadInput() throws IOException
  {
    File localFile = new File("src/day1/input.txt");
    return FileUtils.readLines(localFile);
  }

  private int ApplyOperation(int aCurrentFrequency, String aOpLine)
  {
    int operand = Integer.parseInt(aOpLine);
    return aCurrentFrequency + operand;
  }

  public static void main(String[] args) throws IOException
  {
    Day1Solver solver = new Day1Solver();
    solver.Solve(false);
    solver.Solve(true);
  }

}