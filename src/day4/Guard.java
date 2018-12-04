package day4;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Guard
{
  private int mId;
  private Date mStartedSleepingAt;
  private int mSleptMinutes;

  private Map<Integer, Integer> mSleepingDuringMinutes;

  public Guard(int aId)
  {
    mId = aId;
    mSleepingDuringMinutes = new HashMap<Integer, Integer>();
  }

  public int GetId()
  {
    return mId;
  }

  public void SetSleepingFrom(Date aDate)
  {
    mStartedSleepingAt = aDate;
  }

  public void AddSleptMinutes(int aMinutes)
  {
    mSleptMinutes += aMinutes;
  }

  public Date GetStartedSleepingAt()
  {
    return mStartedSleepingAt;
  }

  public int GetSleptMinutes()
  {
    return mSleptMinutes;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + mId;
    result = prime * result + mSleptMinutes;
    result = prime * result + ((mStartedSleepingAt == null) ? 0 : mStartedSleepingAt.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Guard other = (Guard) obj;
    if (mId != other.mId)
      return false;
    if (mSleptMinutes != other.mSleptMinutes)
      return false;
    if (mStartedSleepingAt == null)
    {
      if (other.mStartedSleepingAt != null)
        return false;
    }
    else if (!mStartedSleepingAt.equals(other.mStartedSleepingAt))
      return false;
    return true;
  }

  public void MarkSleepingMinute(int aMinute)
  {
    Integer timesSleepingAtMinute = mSleepingDuringMinutes.get(aMinute);
    if (timesSleepingAtMinute == null)
    {
      timesSleepingAtMinute = 0;
    }

    timesSleepingAtMinute += 1;

    mSleepingDuringMinutes.put(aMinute, timesSleepingAtMinute);
  }

  public Map<Integer, Integer> GetSleepingDuringMinutes()
  {
    return mSleepingDuringMinutes;
  }

  @Override
  public String toString()
  {
    return "Guard [mId=" + mId + ", mStartedWorkingAt=" + mStartedSleepingAt + ", mSleptMinutes=" + mSleptMinutes + ", mSleepingDuringMinutes="
        + mSleepingDuringMinutes + "]";
  }

}