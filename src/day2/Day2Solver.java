package day2;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class Day2Solver
{
  public void SolvePart1() throws IOException
  {
    int twoLetter = 0;
    int threeLetter = 0;
    List<String> lines = ReadInput();
    for (String line : lines)
    {
      Character threeChar = HasOccurrences(line, 3, null);
      if (threeChar != null)
      {
        threeLetter++;
      }

      Character twoChar = HasOccurrences(line, 2, threeChar);
      if (twoChar != null && GetCharOccurrencesInLine(line, twoChar) < 3)
      {
        twoLetter++;
      }

    }

    System.out.println("two letters: " + twoLetter);
    System.out.println("three letters: " + threeLetter);
    System.out.println("checksum: " + twoLetter * threeLetter);
  }

  private Character HasOccurrences(String aLine, int aHowMany, Character aDifferentFrom)
  {
    Map<String, Integer> seenLetters = new HashMap<String, Integer>();
    for (int i = 0; i < aLine.length(); i++)
    {
      char character = aLine.charAt(i);
      Integer occurrences = seenLetters.get(String.valueOf(character));
      if (occurrences == null)
      {
        occurrences = 1;
      }
      else
      {
        occurrences += 1;
      }

      seenLetters.put(String.valueOf(character), occurrences);

      if (occurrences == aHowMany && (aDifferentFrom == null || character != aDifferentFrom))
      {
        return character;
      }
    }

    return null;
  }

  private List<String> ReadInput() throws IOException
  {
    File localFile = new File("src/day2/input.txt");
    return FileUtils.readLines(localFile);
  }

  private int GetCharOccurrencesInLine(String aLine, Character aChar)
  {
    int occurrences = 0;
    for (int i = 0; i < aLine.length(); i++)
    {
      if (aLine.charAt(i) == aChar)
      {
        occurrences++;
      }
    }

    return occurrences;
  }

  private void SolvePart2() throws IOException
  {
    List<String> lines = ReadInput();
    for (String line : lines)
    {
      for (String line2 : lines)
      {
        if (line.equals(line2))
        {
          continue;
        }

        for (int i = 0; i < line.length(); i++) // they're both the same length
        {
          StringBuilder firstString = new StringBuilder(line);
          StringBuilder secondString = new StringBuilder(line2);

          String adjustedFirstString = firstString.deleteCharAt(i).toString();
          String adjustedSecondString = secondString.deleteCharAt(i).toString();
          if (adjustedFirstString.equals(adjustedSecondString))
          {
            System.out.println("part 2 common substring: " + adjustedFirstString);
            return;
          }
        }
      }
    }

  }

  public static void main(String[] args) throws IOException
  {
    Day2Solver solver = new Day2Solver();
    solver.SolvePart1();
    solver.SolvePart2();
  }

}