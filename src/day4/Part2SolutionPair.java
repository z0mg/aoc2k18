package day4;

public class Part2SolutionPair
{
  private Guard mGuard;
  private int mMinute;
  private int mTimesSleptDuringMinute;

  public Part2SolutionPair(Guard aGuard, int aMinute, int aTimesSleptDuringMinute)
  {
    mGuard = aGuard;
    mMinute = aMinute;
    mTimesSleptDuringMinute = aTimesSleptDuringMinute;
  }

  public Guard GetGuard()
  {
    return mGuard;
  }

  public int GetMinute()
  {
    return mMinute;
  }

  public int GetTimesSleptDuringMinute()
  {
    return mTimesSleptDuringMinute;
  }

  @Override
  public String toString()
  {
    return "Part2SolutionPair [mGuard=" + mGuard + ", mMinute=" + mMinute + ", mTimesSleptDuringMinute=" + mTimesSleptDuringMinute + "]";
  }

}