package day3;

public class Inch
{

  private int mXCoordinate;
  private int mYCoordinate;

  public Inch(int aXCoordinate, int aYCoordinate)
  {
    mXCoordinate = aXCoordinate;
    mYCoordinate = aYCoordinate;
  }

  public int GetXCoordinate()
  {
    return mXCoordinate;
  }

  public int GetYCoordinate()
  {
    return mYCoordinate;
  }

  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + mXCoordinate;
    result = prime * result + mYCoordinate;
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
    Inch other = (Inch) obj;
    if (mXCoordinate != other.mXCoordinate)
      return false;
    if (mYCoordinate != other.mYCoordinate)
      return false;
    return true;
  }

}