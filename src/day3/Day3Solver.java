package day3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class Day3Solver
{
  private Set<Inch> mOverlappingInches;

  public Day3Solver()
  {
    mOverlappingInches = new HashSet<Inch>();
  }

  public void Solve() throws IOException
  {
    List<Claim> claims = new ArrayList<Claim>();

    int allOverlappingInches = 0;

    List<String> lines = ReadInput();
    for (String line : lines)
    {
      Claim claim = ParseRow(line);

      for (Claim compareTo : claims)
      {
        List<Inch> overlappingInches = GetOverlappingInches(claim, compareTo);
        for (Inch inch : overlappingInches)
        {
          if (!IsAlreadyCounted(inch))
          {
            allOverlappingInches++;
          }
        }
      }

      claims.add(claim);
    }

    System.out.println("*part1* overlapping inches: " + allOverlappingInches);

    for (Claim claim : claims)
    {
      boolean isPerfect = true;
      for (Inch inch : mOverlappingInches)
      {
        if (ContainsOverlappedInch(claim, inch))
        {
          isPerfect = false;
          break;
        }
      }
      if (isPerfect)
      {
        System.out.println("*part2* found perfect claim: " + claim);
      }
    }
  }

  private boolean ContainsOverlappedInch(Claim aClaim, Inch aInch)
  {
    for (int width = aClaim.GetPaddingLeft(); width < aClaim.GetPaddingLeft() + aClaim.GetWidth(); width++)
    {
      for (int height = aClaim.GetPaddingTop(); height < aClaim.GetPaddingTop() + aClaim.GetHeight(); height++)
      {
        if (width == aInch.GetXCoordinate() && height == aInch.GetYCoordinate())
        {
          return true;
        }
      }
    }

    return false;
  }

  private boolean IsAlreadyCounted(Inch aInch)
  {
    return !mOverlappingInches.add(aInch);
  }

  private List<Inch> GetOverlappingInches(Claim aClaim, Claim aCompareTo)
  {
    List<Inch> overlappingInches = new ArrayList<Inch>();

    // compare them inch by inch for brevity, could also check if segments overlap?
    for (int width = aClaim.GetPaddingLeft(); width < aClaim.GetPaddingLeft() + aClaim.GetWidth(); width++)
    {
      for (int height = aClaim.GetPaddingTop(); height < aClaim.GetPaddingTop() + aClaim.GetHeight(); height++)
      {
        for (int compareToWidth = aCompareTo.GetPaddingLeft(); compareToWidth < aCompareTo.GetPaddingLeft() + aCompareTo.GetWidth(); compareToWidth++)
        {
          for (int compareToHeight = aCompareTo.GetPaddingTop(); compareToHeight < aCompareTo.GetPaddingTop()
              + aCompareTo.GetHeight(); compareToHeight++)
          {
            if (width == compareToWidth && height == compareToHeight)
            {
              Inch inch = new Inch(width, height);
              overlappingInches.add(inch);
            }
          }
        }
      }
    }

    return overlappingInches;
  }

  private Claim ParseRow(String aLine)
  {
    // #1 @ 16,576: 17x14

    String[] tokens = aLine.split("\\s");

    int id = Integer.valueOf(tokens[0].substring(1));
    int paddingLeft = Integer.valueOf(tokens[2].split("\\,")[0]);
    int paddingTop = Integer.valueOf(tokens[2].split("\\,")[1].replace(":", ""));

    int width = Integer.valueOf(tokens[3].split("x")[0]);
    int height = Integer.valueOf(tokens[3].split("x")[1]);

    return new Claim(id, paddingLeft, paddingTop, width, height);
  }

  private List<String> ReadInput() throws IOException
  {
    //    File localFile = new File("src/day3/input_test.txt");
    File localFile = new File("src/day3/input.txt");
    return FileUtils.readLines(localFile);
  }

  public static void main(String[] args) throws IOException
  {
    Day3Solver solver = new Day3Solver();
    solver.Solve();
  }

}