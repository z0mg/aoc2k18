package day4;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class Day4Solver
{
  private DateFormat kDateFormat = new SimpleDateFormat("yyyy-M-dd HH:mm", Locale.ENGLISH);

  public Day4Solver()
  {
    ;
  }

  public void Solve() throws IOException, ParseException
  {
    Map<Integer, Guard> guards = new HashMap<Integer, Guard>();

    Guard currentGuard = null;

    List<String> lines = ReadInput();

    SortByTime(lines);

    for (String line : lines)
    {
      if (currentGuard == null && !line.contains("Guard"))
      {
        continue;
      }

      if (line.contains("Guard"))
      {
        int guardId = Integer.valueOf(line.split("\\s")[3].replace("#", ""));
        System.out.println("got guard: " + guardId);

        currentGuard = guards.get(guardId);
        if (currentGuard == null)
        {
          currentGuard = new Guard(guardId);

          Date startedAt = GetTimestampFromLine(line);
          currentGuard.SetSleepingFrom(startedAt);

          guards.put(guardId, currentGuard);
        }
      }
      else if (line.contains("wakes up"))
      {
        Date timestamp = GetTimestampFromLine(line);
        System.out.println("guard: " + currentGuard + " woke up at: " + timestamp);

        if (currentGuard.GetStartedSleepingAt() == null)
        {
          System.out.println("guard: " + currentGuard + " wasn't sleeping");
          continue;
        }

        Date asleepAt = GetTimestampFromLine(line);
        System.out.println("guard: " + currentGuard + " fell asleep at: " + asleepAt);

        Calendar asleepAtCalendar = GregorianCalendar.getInstance();
        asleepAtCalendar.setTime(asleepAt);

        Calendar startedWorkingAtCalendar = GregorianCalendar.getInstance();
        startedWorkingAtCalendar.setTime(currentGuard.GetStartedSleepingAt());

        int hourOfDay = startedWorkingAtCalendar.get(Calendar.HOUR_OF_DAY);

        int currentMinute = startedWorkingAtCalendar.get(Calendar.MINUTE);
        while (startedWorkingAtCalendar.getTime().before(asleepAtCalendar.getTime()))
        {
          if (hourOfDay == 0) // maybe he takes a long nap past 00 h
          {
            currentGuard.AddSleptMinutes(1);
            currentGuard.MarkSleepingMinute(currentMinute);
            System.out.println("added minute: " + currentMinute + " to guard: " + currentGuard);
          }

          startedWorkingAtCalendar.add(Calendar.MINUTE, 1);

          currentMinute = startedWorkingAtCalendar.get(Calendar.MINUTE);
          hourOfDay = startedWorkingAtCalendar.get(Calendar.HOUR_OF_DAY);
        }

      }
      else if (line.contains("falls asleep"))
      {
        Date timestamp = GetTimestampFromLine(line);
        currentGuard.SetSleepingFrom(timestamp);
      }
    }

    Guard sleepiestGuard = GetSleepiestGuard(guards);

    int sleepiestMinute = GetGuardSleepiestMinute(sleepiestGuard);

    System.out.println("sleepiest guard: " + sleepiestGuard);
    System.out.println("sleepiest minute: " + sleepiestMinute);

    Part2SolutionPair mostThoroughGuard = GetPart2Solution(guards);
    System.out.println("part 2 guard id: " + mostThoroughGuard.GetGuard().GetId() + ", most slept during minute: " + mostThoroughGuard.GetMinute());
  }

  private Part2SolutionPair GetPart2Solution(Map<Integer, Guard> aGuards)
  {
    Guard mostThoroughGuard = null;
    int mostSleptDuringMinute = -1;
    int mostSleptMinuteTimes = -1;

    for (Guard guard : aGuards.values())
    {
      for (int minute : guard.GetSleepingDuringMinutes().keySet())
      {
        int timesSleptDuringMinute = guard.GetSleepingDuringMinutes().get(minute);
        if (mostSleptMinuteTimes < timesSleptDuringMinute)
        {
          mostThoroughGuard = guard;
          mostSleptDuringMinute = minute;
          mostSleptMinuteTimes = timesSleptDuringMinute;
        }
      }
    }

    return new Part2SolutionPair(mostThoroughGuard, mostSleptDuringMinute, mostSleptMinuteTimes);
  }

  private int GetGuardSleepiestMinute(Guard aSleepiestGuard)
  {
    int sleepiestMinute = -1;
    int timesSleptOnSleepiestMinute = -1;
    for (int minute : aSleepiestGuard.GetSleepingDuringMinutes().keySet())
    {
      int timesSleptOnMinute = aSleepiestGuard.GetSleepingDuringMinutes().get(minute);
      if (timesSleptOnSleepiestMinute < timesSleptOnMinute)
      {
        sleepiestMinute = minute;
        timesSleptOnSleepiestMinute = timesSleptOnMinute;
      }
    }

    return sleepiestMinute;
  }

  private Guard GetSleepiestGuard(Map<Integer, Guard> aGuards)
  {
    Guard sleepiestGuard = aGuards.values().iterator().next();
    for (Guard guard : aGuards.values())
    {
      if (sleepiestGuard.GetSleptMinutes() < guard.GetSleptMinutes())
      {
        sleepiestGuard = guard;
        System.out.println("got sleepier guard: " + guard);
      }
    }

    return sleepiestGuard;
  }

  private Date GetTimestampFromLine(String aLine) throws ParseException
  {
    String date1 = aLine.substring(1, 17);
    return kDateFormat.parse(date1);
  }

  private void SortByTime(List<String> aLines)
  {
    Comparator<String> c = new Comparator<String>()
    {

      @Override
      public int compare(String o1, String o2)
      {
        try
        {
          String date1 = o1.substring(1, 17);
          Date date1Parsed = kDateFormat.parse(date1);

          String date2 = o2.substring(1, 17);
          Date date2Parsed = kDateFormat.parse(date2);

          return date1Parsed.compareTo(date2Parsed);
        }
        catch (ParseException e)
        {
          return -1;
        }

      }
    };

    aLines.sort(c);
  }

  private List<String> ReadInput() throws IOException
  {
    //    File localFile = new File("src/day4/input_test.txt");
    File localFile = new File("src/day4/input.txt");
    return FileUtils.readLines(localFile);
  }

  public static void main(String[] args) throws IOException, ParseException
  {
    Day4Solver solver = new Day4Solver();
    solver.Solve();
  }

}