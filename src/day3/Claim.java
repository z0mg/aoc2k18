package day3;

public class Claim
{
  private int mId;

  private int mPaddingLeft;
  private int mPaddingTop;

  private int mWidth;
  private int mHeight;

  public Claim(int aId, int aPaddingLeft, int aPaddingTop, int aWidth, int aHeight)
  {
    mId = aId;
    mPaddingLeft = aPaddingLeft;
    mPaddingTop = aPaddingTop;
    mWidth = aWidth;
    mHeight = aHeight;
  }

  public int GetPaddingLeft()
  {
    return mPaddingLeft;
  }

  public int GetPaddingTop()
  {
    return mPaddingTop;
  }

  public int GetWidth()
  {
    return mWidth;
  }

  public int GetHeight()
  {
    return mHeight;
  }

  public int GetId()
  {
    return mId;
  }

  @Override
  public String toString()
  {
    return "Claim [mId=" + mId + ", mPaddingLeft=" + mPaddingLeft + ", mPaddingTop=" + mPaddingTop + ", mWidth=" + mWidth + ", mHeight=" + mHeight
        + "]";
  }

}